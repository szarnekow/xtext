/*******************************************************************************
 * Copyright (c) 2011 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.xtend2.ui.builder;

import static com.google.common.collect.Iterables.*;
import static org.eclipse.xtext.util.Strings.*;

import java.io.UnsupportedEncodingException;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.Constants;
import org.eclipse.xtext.builder.IXtextBuilderParticipant;
import org.eclipse.xtext.parser.IEncodingProvider;
import org.eclipse.xtext.resource.IResourceDescription.Delta;
import org.eclipse.xtext.util.StringInputStream;
import org.eclipse.xtext.xbase.compiler.IAppendable.StringBuilderBasedAppendable;
import org.eclipse.xtext.xtend2.compiler.Xtend2Compiler;
import org.eclipse.xtext.xtend2.ui.internal.Xtend2Activator;

import com.google.common.base.Predicate;
import com.google.common.collect.Sets;
import com.google.inject.Inject;
import com.google.inject.name.Named;

/**
 * @author Jan Koehnlein - Initial contribution and API
 */
public class Xtend2BuilderParticipant implements IXtextBuilderParticipant {

	private static final Logger LOG = Logger.getLogger(Xtend2BuilderParticipant.class);

	private Set<String> fileExtensions;

	@Inject
	private Xtend2Compiler compiler;

	@Inject
	private CompilationFileProvider compilationFileProvider;

	@Inject
	private IEncodingProvider encodingProvider;

	@Inject
	private FolderUtil workspaceUtil;

	@Inject
	public void setExtensions(@Named(Constants.FILE_EXTENSIONS) String extensions) {
		String[] split = extensions.split(",");
		this.fileExtensions = Sets.newHashSet();
		for (String string : split) {
			this.fileExtensions.add(string);
		}
	}

	public void build(IBuildContext context, IProgressMonitor monitor) throws CoreException {
		try {
			Iterable<Delta> xtendDeltas = filter(context.getDeltas(), new Predicate<Delta>() {
				public boolean apply(Delta input) {
					return fileExtensions.contains(input.getUri().fileExtension());
				}
			});
			if (!isEmpty(xtendDeltas)) {
				final SubMonitor progress = SubMonitor.convert(monitor, size(xtendDeltas) + 3);
				IFolder targetFolder = compilationFileProvider.getTargetFolder(context.getBuiltProject(), progress.newChild(1));
				wipeTargetFolder(targetFolder, context, progress.newChild(1));
				for (Delta delta : xtendDeltas) {
					processDelta(delta, context, progress.newChild(1));
				}
				workspaceUtil.removeEmptySubFolders(targetFolder, progress.newChild(1));
				context.needRebuild();
			}
		} catch (CoreException ce) {
			throw ce;
		} catch (Exception e) {
			throw new CoreException(new Status(IStatus.ERROR, Xtend2Activator.getInstance().getBundle()
					.getSymbolicName(), "Error during compilation of Xtend2 resources", e));
		}
	}

	protected void processDelta(Delta delta, IBuildContext context, final SubMonitor progress) {
		progress.setWorkRemaining(100);
		URI sourceURI = delta.getUri();
		IFile sourceFile = null;
		try {
			sourceFile = compilationFileProvider.getFile(sourceURI, context.getBuiltProject());
			if (sourceFile.exists() && hasErrors(sourceFile))
				return;
			IFile targetFile = compilationFileProvider.getTargetFile(sourceURI, context.getBuiltProject(),
					progress.newChild(10));
			Resource sourceResource = null;
			if (delta.getNew() != null) {
				sourceResource = context.getResourceSet().getResource(sourceURI, true);
				if (sourceResource == null)
					throw new IllegalStateException("Cannot load source Xtend2 resource "
							+ notNull(sourceURI));
				if (!sourceResource.getErrors().isEmpty())
					return;
			} else if (delta.getOld() != null)
				targetFile.delete(true, progress.newChild(10));
			if (sourceResource != null && !sourceResource.getContents().isEmpty()) {
				compile(sourceResource, targetFile, progress.newChild(80));
			}
		} catch (Exception e) {
			LOG.error("Error compiling " + notNull(sourceURI), e);
		}
	}

	protected boolean hasErrors(IFile sourceFile) throws CoreException {
		return sourceFile.findMaxProblemSeverity(null, true, IResource.DEPTH_ZERO) == IMarker.SEVERITY_ERROR;
	}

	protected void wipeTargetFolder(IFolder targetFolder, IBuildContext context, final SubMonitor progress)
			throws CoreException {
		switch (context.getBuildType()) {
			case CLEAN:
			case RECOVERY:
				workspaceUtil.clearFolder(targetFolder, progress);
				break;
			case INCREMENTAL:
			case FULL:
				break;
		}
	}

	protected void compile(Resource sourceResource, IFile targetFile, final SubMonitor progress) throws CoreException,
			UnsupportedEncodingException {
		progress.setWorkRemaining(100);
		StringBuilderBasedAppendable appendable = new StringBuilderBasedAppendable();
		compiler.compile(sourceResource.getContents().get(0), appendable);
		progress.worked(50);
		String encoding = encodingProvider.getEncoding(sourceResource.getURI());
		workspaceUtil.createParentFolders(targetFile, progress.newChild(10));
		if (targetFile.exists())
			targetFile.setContents(new StringInputStream(appendable.toString(), encoding), true, false, progress.newChild(40));
		else
			targetFile.create(new StringInputStream(appendable.toString(), encoding), true, progress.newChild(40));
		if(!equal(targetFile.getCharset(), encoding))
			targetFile.setCharset(encoding, progress);
	}

}