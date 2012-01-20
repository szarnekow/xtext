/*******************************************************************************
 * Copyright (c) 2011 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.xbase.ui.tests;

import java.io.InputStream;
import java.util.Collections;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.xtext.Constants;
import org.eclipse.xtext.junit4.ui.util.IResourcesSetupUtil;
import org.eclipse.xtext.junit4.ui.util.JavaProjectSetupUtil;
import org.eclipse.xtext.junit4.util.ResourceLoadHelper;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.eclipse.xtext.ui.XtextProjectHelper;
import org.eclipse.xtext.ui.util.PluginProjectFactory;
import org.eclipse.xtext.util.StringInputStream;
import org.eclipse.xtext.xbase.XExpression;
import org.eclipse.xtext.xbase.ui.internal.XtypeActivator;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.name.Named;

/**
 * @author Jan Koehnlein - Initial contribution and API
 */
@SuppressWarnings("restriction")
public abstract class AbstractXbaseUITestCase extends Assert implements ResourceLoadHelper {

	@Inject
	protected IWorkspace workspace;

	protected String fileExtension;

	static Injector injector = XtypeActivator.getInstance().getInjector("org.eclipse.xtext.xbase.Xbase");
	
	@Before
	public void setUp() throws Exception {
		getInjector().injectMembers(this);
	}

	@After
	public void tearDown() throws Exception {
		if (doCleanWorkspace())
			IResourcesSetupUtil.cleanWorkspace();
	}
	
	protected boolean doCleanWorkspace() {
		return true;
	}
	
	public Injector getInjector() {
		return injector;
	}
	
	public <T> T get(Class<T> clazz) {
		return getInjector().getInstance(clazz);
	}

	protected XExpression expression(String string) throws Exception {
		XtextResource resource = getResourceFor(new StringInputStream(string));
		assertEquals(resource.getErrors().toString(), 0, resource.getErrors().size());
		XExpression result = (XExpression) resource.getContents().get(0);
		return result;
	}
	
	public XtextResource getResourceFor(InputStream stream) {
		try {
			XtextResourceSet set = get(XtextResourceSet.class);
			XtextResource result = (XtextResource) set.createResource(URI.createURI("Test." + fileExtension));
			result.load(stream, null);
			return result;
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Inject
	protected void setExtensions(@Named(Constants.FILE_EXTENSIONS) String extensions) {
		this.fileExtension = extensions.split(",")[0];
	}
	
	public static IProject createPluginProject(String name) throws CoreException {
		Injector injector = XtypeActivator.getInstance().getInjector("org.eclipse.xtext.xbase.Xbase");
		PluginProjectFactory projectFactory = injector.getInstance(PluginProjectFactory.class);
		projectFactory.setProjectName(name);
		projectFactory.addFolders(Collections.singletonList("src"));
		projectFactory.addBuilderIds(
			JavaCore.BUILDER_ID, 
			"org.eclipse.pde.ManifestBuilder",
			"org.eclipse.pde.SchemaBuilder",
			XtextProjectHelper.BUILDER_ID);
		projectFactory.addProjectNatures(JavaCore.NATURE_ID, "org.eclipse.pde.PluginNature", XtextProjectHelper.NATURE_ID);
		projectFactory.addRequiredBundles(Collections.singletonList("org.eclipse.xtext.xbase.lib"));
		IProject result = projectFactory.createProject(new NullProgressMonitor(), null);
		JavaProjectSetupUtil.makeJava5Compliant(JavaCore.create(result));
		return result;
	}
}
