/*******************************************************************************
 * Copyright (c) 2011 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.xbase.compiler;

import static com.google.common.collect.Lists.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.jdt.core.compiler.CompilationProgress;
import org.eclipse.jdt.internal.compiler.batch.FileSystem;
import org.eclipse.jdt.internal.compiler.batch.Main;
import org.eclipse.jdt.internal.compiler.env.NameEnvironmentAnswer;
import org.eclipse.xtext.util.Files;
import org.eclipse.xtext.util.Pair;
import org.eclipse.xtext.util.Tuples;
import org.eclipse.xtext.xbase.lib.Functions;
import org.eclipse.xtext.xbase.lib.Functions.Function0;

import com.google.inject.Inject;
import com.google.inject.internal.MoreTypes;

/**
 * @author Sven Efftinge - Initial contribution and API
 */
@SuppressWarnings("restriction")
public class OnTheFlyJavaCompiler {
	
	@Inject
	private ClassLoader parentClassLoader;
	private List<String> classpath = newArrayList();
	
	static class DelegateOutStream extends OutputStream {

		private OutputStream delegate;
		
		public void setDelegate(OutputStream delegate) {
			this.delegate = delegate;
		}

		@Override
		public void write(int b) throws IOException {
			delegate.write(b);
		}

		@Override
		public int hashCode() {
			return delegate.hashCode();
		}

		@Override
		public void write(byte[] b) throws IOException {
			delegate.write(b);
		}

		@Override
		public void write(byte[] b, int off, int len) throws IOException {
			delegate.write(b, off, len);
		}

		@Override
		public boolean equals(Object obj) {
			return delegate.equals(obj);
		}

		@Override
		public void flush() throws IOException {
			delegate.flush();
		}

		@Override
		public void close() throws IOException {
			delegate.close();
		}

		@Override
		public String toString() {
			return delegate.toString();
		}
		
	}
	
	public void setParentClassLoader(ClassLoader parentClassLoader) {
		this.parentClassLoader = parentClassLoader;
	}
	
	public void addClassPathOfClass(Class<?> clazz) {
		final String classNameAsPath = File.separator+clazz.getCanonicalName().replace('.', File.separatorChar);
		URL url = clazz.getResource(classNameAsPath+".class");
		String pathToFolderOrJar = null;
		if (url.getProtocol().startsWith("bundleresource")) {
			try {
				url = resolveBundleResourceURL(url);
			} catch (IOException e) {
				throw new WrappedException(e);
			}
		}
		if (url.getProtocol().startsWith("jar")) {
			try {
				pathToFolderOrJar = new URL(url.getPath().substring(0,url.getPath().indexOf('!'))).toURI().getRawPath();
			} catch (Exception e) {
				throw new WrappedException(e);
			}
		} else {
			String resolvedRawPath;
			try {
				resolvedRawPath = url.toURI().getRawPath();
			} catch (URISyntaxException e) {
				throw new WrappedException(e);
			}
			pathToFolderOrJar = resolvedRawPath.substring(0,resolvedRawPath.indexOf(classNameAsPath));
		}
		this.classpath.add(pathToFolderOrJar);
	}

	protected URL resolveBundleResourceURL(URL url) throws IOException {
		throw new UnsupportedOperationException();
	}
	
	public static class EclipseRuntimeDependentJavaCompiler extends OnTheFlyJavaCompiler {
		@Override
		protected URL resolveBundleResourceURL(URL url) throws IOException {
			return FileLocator.resolve(url);
		}
	}

	public void addClassPath(String classpath) {
		this.classpath.add(classpath);
	}
	
	public String getClasspathArgs() {
		StringBuilder sb = new StringBuilder();
		sb.append("-classpath ");
		for (int i = 0; i < classpath.size(); i++) {
			sb.append(classpath.get(i));
			if (i+1<classpath.size())
				sb.append(File.pathSeparator);
		}
		return sb.toString();
	}

	@SuppressWarnings("unchecked")
	public <RT> Functions.Function0<RT> createFunction(String expression, Class<RT> returnType) {
		return (Function0<RT>) internalCreateFunction(expression, returnType);
	}
	
	@SuppressWarnings("unchecked")
	public <RT, T> Functions.Function1<T,RT> createFunction(String body, Class<RT> returnType, Class<T> paramType) {
		return (Functions.Function1<T,RT>) internalCreateFunction(body, returnType, Tuples.pair((Type) paramType, "p"));
	}
	
	@SuppressWarnings("unchecked")
	public <RT,T1,T2> Functions.Function2<T1,T2,RT> createFunction(String body, Class<RT> returnType, Class<T1> paramType1, Class<T2> paramType2) {
		return (Functions.Function2<T1,T2,RT>) internalCreateFunction(body, returnType, Tuples.pair((Type) paramType2, "p1"), Tuples.pair((Type) paramType2, "p2"));
	}

	protected Object internalCreateFunction(String code, Type returnType, Pair<Type, String>... params) {
		Pair<String, String> fullCode = createFullCode(code, returnType, params);
		Class<?> class1 = compileToClass(fullCode.getFirst(), fullCode.getSecond());
		try {
			return class1.newInstance();
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new WrappedException(e);
		}
	}

	public Class<?> compileToClass(String classname, String code) {
		String tempDir = System.getProperty("java.io.tmpdir");
		if (tempDir == null)
			tempDir = "./";
		tempDir += File.separator;
		final String classNameAsPath = classname.replace('.', File.separatorChar);
		final File srcFile = new File(tempDir + classNameAsPath + ".java");
		final File targetFile = new File(tempDir + classNameAsPath + ".class");
		try {
			Files.writeStringIntoFile(srcFile.getCanonicalPath(), code);
			errorStream.setDelegate(new ByteArrayOutputStream());
			StringBuilder sb = new StringBuilder(getComplianceLevelArg());
			sb.append(" ");
			sb.append(getClasspathArgs());
			sb.append(" ");
			sb.append(srcFile.getCanonicalPath());
			boolean compile = compile(sb.toString());
			if (!compile)
				throw new IllegalArgumentException("Couldn't compile : " + errorStream.toString());
			URLClassLoader loader = new URLClassLoader(new URL[] { targetFile.getParentFile().toURI().toURL() }, parentClassLoader);
			Class<?> class1 = loader.loadClass(classname);
			return class1;
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new WrappedException(e);
		} finally {
			if (srcFile.exists())
				srcFile.delete();
			if (targetFile.exists())
				targetFile.delete();
		}
	}

	protected boolean compile(String arguments) {
//		return BatchCompiler.compile(sb.toString(), new PrintWriter(new OutputStreamWriter(System.out)), new PrintWriter(
//				new OutputStreamWriter(errorStream)), null);
		return getMain().compile(Main.tokenize(arguments));
	}

	private DelegateOutStream errorStream =  new DelegateOutStream();
	
	protected Main getMain() {
		return new PatchedMain(new PrintWriter(new OutputStreamWriter(System.out)), new PrintWriter(
				new OutputStreamWriter(errorStream)), false /* systemExit */, null /* options */, null);
	}

	protected String getComplianceLevelArg() {
		return "-1.5";
	}

	protected Pair<String, String> createFullCode(String statementCode, Type returnType, Pair<Type, String>... params) {
		String className = "_$GeneratedClass";
		StringBuilder sb = new StringBuilder("public class ").append(className).append(" implements ")
				.append("org.eclipse.xtext.xbase.lib.Functions.Function").append(params.length).append("<");
		for (Pair<Type, String> type : params) {
			sb.append(toString(type.getFirst())).append(",");
		}
		sb.append(toString(returnType));
		sb.append("> {\n");
		sb.append("public ").append(toString(returnType));
		sb.append(" apply(");

		for (int i = 0; i < params.length; i++) {
			Pair<Type, String> pair = params[i];
			sb.append(toString(pair.getFirst())).append(" ").append(pair.getSecond());
			if (i + 1 < params.length)
				sb.append(",");
		}
		sb.append(") {\n");
		sb.append(statementCode);
		sb.append("\n}}");
		return Tuples.pair(className, sb.toString());
	}

	protected String toString(Type returnType) {
		return MoreTypes.toString(returnType);
	}
	
	private static PatchedFileSystem fileSystem;
	
	public void clearClassPath() {
		fileSystem.getDelegate().cleanup();
		fileSystem = null;
	}
	
	/**
	 * HACK - reuse the classpath, since it is super expensive to reopen and scan the zips.
	 * 
	 * @author Sven Efftinge - Initial contribution and API
	 */
	static class PatchedMain extends Main {

		@SuppressWarnings("rawtypes")
		public PatchedMain(PrintWriter outWriter, PrintWriter errWriter, boolean systemExitWhenFinished,
				Map customDefaultOptions, CompilationProgress compilationProgress) {
			super(outWriter, errWriter, systemExitWhenFinished, customDefaultOptions, compilationProgress);
		}

		@Override
		public FileSystem getLibraryAccess() {
			if (fileSystem == null) {
				fileSystem =  new PatchedFileSystem(super.getLibraryAccess());
			}
			return fileSystem;
		}
		
	}
	
	static class PatchedFileSystem extends FileSystem {
		
		private FileSystem delegate;
		
		public PatchedFileSystem(FileSystem delegate) {
			super(new String[0],new String[0],"ISO-8859-1");
			this.delegate = delegate;
		}
		
		public FileSystem getDelegate() {
			return delegate;
		}

		@Override
		public void cleanup() {
			//DO nothing. the original implmentaion closes zips and sets the references to null
		}

		@Override
		public int hashCode() {
			return delegate.hashCode();
		}

		@Override
		public boolean equals(Object obj) {
			return delegate.equals(obj);
		}

		@Override
		public String toString() {
			return delegate.toString();
		}

		@Override
		public NameEnvironmentAnswer findType(char[][] compoundName) {
			return delegate.findType(compoundName);
		}

		@Override
		public char[][][] findTypeNames(char[][] packageName) {
			return delegate.findTypeNames(packageName);
		}

		@Override
		public NameEnvironmentAnswer findType(char[][] compoundName, boolean asBinaryOnly) {
			return delegate.findType(compoundName, asBinaryOnly);
		}

		@Override
		public NameEnvironmentAnswer findType(char[] typeName, char[][] packageName) {
			return delegate.findType(typeName, packageName);
		}

		@Override
		public boolean isPackage(char[][] compoundName, char[] packageName) {
			return delegate.isPackage(compoundName, packageName);
		}
		
		

	}
	
}