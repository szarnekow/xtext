/*******************************************************************************
 * Copyright (c) 2008 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *******************************************************************************/

package org.eclipse.xtext.resource;

import java.net.URL;
import java.net.URLClassLoader;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.xtext.junit4.AbstractXtextTests;
import org.junit.Before;
import org.junit.Test;


public class ClassloaderClasspathUriResolverTest extends AbstractXtextTests {
	private ClassloaderClasspathUriResolver _resolver;

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
		_resolver = new ClassloaderClasspathUriResolver();
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(
				"ecore", new XMIResourceFactoryImpl());
	}

	@Test public void testClasspathUriForFile() {
		URI classpathUri = URI
				.createURI("classpath:/org/eclipse/xtext/resource/mydsl.ecore");
		URL rootURL = getClass().getClassLoader().getResource(
				"org/eclipse/xtext/resource/mydsl.ecore");
		String expectedUri = rootURL.toString();
		normalizeUriAndLoadResource(classpathUri, expectedUri);
	}

	@Test public void testClasspathUriForFileWithFragment() {
		URI classpathUri = URI
				.createURI("classpath:/org/eclipse/xtext/resource/mydsl.ecore#/");
		URL rootURL = getClass().getClassLoader().getResource(
				"org/eclipse/xtext/resource/mydsl.ecore");
		String expectedUri = rootURL.toString() + "#/";
		normalizeUriAndLoadResource(classpathUri, expectedUri);
	}

	@Test public void testClasspathUriForFileInJar() {
		URI classpathUri = URI.createURI("classpath:/model/simple.ecore");
		URL rootURL = getClass().getClassLoader().getResource(
				"model/simple.ecore");
		String expectedUri = rootURL.toString();
		normalizeUriAndLoadResource(classpathUri, expectedUri);
	}

	@Test public void testClasspathUriForFileInJarWithFragment() {
		URI classpathUri = URI.createURI("classpath:/model/simple.ecore#/");
		URL rootURL = getClass().getClassLoader().getResource(
				"model/simple.ecore");
		String expectedUri = rootURL.toString() + "#/";
		normalizeUriAndLoadResource(classpathUri, expectedUri);
	}

	private void normalizeUriAndLoadResource(URI classpathUri,
			String expectedUri) {
		URI normalizedUri = _resolver.resolve(getClass().getClassLoader(), classpathUri);
		assertEquals(expectedUri, normalizedUri.toString());
		ResourceSet resourceSet = new ResourceSetImpl();
		Resource resource = resourceSet.getResource(normalizedUri, true);
		assertNotNull("Classpth URI ot registered", resource);
		assertTrue("Resource not loaded", resource.isLoaded());
	}

	@Test public void testBug293760() throws Exception {
		ClassloaderClasspathUriResolver resolver = new ClassloaderClasspathUriResolver();
		URL resource = getClass().getResource("mydsl.ecore");
		URL url = new URL(resource.toString().replace("mydsl.ecore", "some folder/"));
		URLClassLoader loader = new URLClassLoader(new URL[]{url});
		URI uri = resolver.findResourceOnClasspath(loader, URI.createURI("classpath:/pack/ClassLoaderClasspathUriResolverTest.txt"));
		assertNotNull(uri);
	}
}
