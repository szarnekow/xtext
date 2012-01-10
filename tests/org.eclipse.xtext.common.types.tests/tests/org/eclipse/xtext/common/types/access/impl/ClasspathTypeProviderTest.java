/*******************************************************************************
 * Copyright (c) 2009 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.common.types.access.impl;

import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.xtext.common.types.JvmDeclaredType;
import org.eclipse.xtext.common.types.JvmGenericType;
import org.eclipse.xtext.common.types.JvmType;
import org.eclipse.xtext.common.types.access.IMirror;
import org.eclipse.xtext.common.types.access.TypeResource;
import org.junit.After;
import org.junit.Test;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
public class ClasspathTypeProviderTest extends AbstractTypeProviderTest {

	private ResourceSet resourceSet;
	private ClasspathTypeProvider typeProvider;

	@Override
	public void setUp() throws Exception {
		super.setUp();
		resourceSet = new ResourceSetImpl();
		typeProvider = new ClasspathTypeProvider(getClass().getClassLoader(), resourceSet, null);
	}
	
	@After
	public void tearDown() throws Exception {
		resourceSet = null;
		typeProvider = null;
	}
	
	@Test public void testSetup_01() {
		Map<String, Object> map = resourceSet.getResourceFactoryRegistry().getProtocolToFactoryMap();
		assertSame(getTypeProvider(), map.get(URIHelperConstants.PROTOCOL));
	}
	
	@Test public void testCreateResource_01() {
		URI primitivesURI = URI.createURI("java:/Primitives"); 
		TypeResource resource = getTypeProvider().createResource(primitivesURI);
		assertNotNull(resource);
		assertFalse(resource.isLoaded());
		assertTrue(resource.getContents().isEmpty());
	}
	
	@Test public void testCreateResource_02() {
		URI primitivesURI = URI.createURI("java:/Primitives"); 
		TypeResource resource = (TypeResource) resourceSet.createResource(primitivesURI);
		assertNotNull(resource);
		assertFalse(resource.isLoaded());
		assertTrue(resource.getContents().isEmpty());
	}
	
	@Test public void testGetResource_01() {
		URI primitivesURI = URI.createURI("java:/Primitives"); 
		TypeResource resource = (TypeResource) resourceSet.getResource(primitivesURI, true);
		assertNotNull(resource);
		assertTrue(resource.isLoaded());
		assertEquals(9, resource.getContents().size());
	}
	
	@Test public void testGetResource_02() {
		URI primitivesURI = URI.createURI("java:/Primitives"); 
		TypeResource resource = (TypeResource) resourceSet.getResource(primitivesURI, false);
		assertNull(resource);
	}
	
	@Test public void testGetResource_03() {
		URI primitivesURI = URI.createURI("java:/Primitives"); 
		TypeResource createdResource = (TypeResource) resourceSet.createResource(primitivesURI);
		TypeResource resource = (TypeResource) resourceSet.getResource(primitivesURI, false);
		assertSame(createdResource, resource);
		assertFalse(resource.isLoaded());
		assertTrue(resource.getContents().isEmpty());
	}
	
	@Test public void testGetResource_04() {
		URI primitivesURI = URI.createURI("java:/Primitives"); 
		TypeResource createdResource = (TypeResource) resourceSet.createResource(primitivesURI);
		TypeResource resource = (TypeResource) resourceSet.getResource(primitivesURI, true);
		assertSame(createdResource, resource);
		assertTrue(resource.isLoaded());
		assertEquals(9, resource.getContents().size());
	}
	
	@Test public void testCreateMirror_01() {
		URI uri = URI.createURI("java:/Objects/java.util.Map");
		IMirror mirror = getTypeProvider().createMirror(uri);
		assertNotNull(mirror);
		assertTrue(mirror instanceof ClassMirror);
		assertEquals("java.util.Map", ((ClassMirror) mirror).getMirroredClass().getName());
	}
	
	@Test public void testCreateMirror_02() {
		URI uri = URI.createURI("java:/Primitives");
		IMirror mirror = getTypeProvider().createMirror(uri);
		assertNotNull(mirror);
		assertTrue(mirror instanceof PrimitiveMirror);
	}
	
	@Test public void testCreateMirror_03() {
		URI uri = URI.createURI("java:/Something");
		try {
			getTypeProvider().createMirror(uri);
			fail("Expected IllegalArgumentException");
		} catch (IllegalArgumentException ex) {
			// ok
		}
	}
	
	@Test public void testCreateMirror_04() {
		URI uri = URI.createURI("java:/Primitives").appendFragment("int");
		try {
			getTypeProvider().createMirror(uri);
			fail("Expected IllegalArgumentException");
		} catch (IllegalArgumentException ex) {
			// ok
		}
	}
	
	@Test public void testCreateMirror_05() {
		URI uri = URI.createURI("java:/Objects/java.lang.does.not.exist");
		assertNull(getTypeProvider().createMirror(uri));
	}
	
	@Test public void testBug337307() {
		String typeName = "ClassWithDefaultPackage";
		JvmType type = getTypeProvider().findTypeByName(typeName);
		assertNotNull(type);
		assertTrue(type instanceof JvmGenericType);
		assertEquals(typeName, type.getIdentifier());
		assertEquals(typeName, type.getQualifiedName());
		assertEquals(typeName, type.getSimpleName());
		assertNull(((JvmDeclaredType) type).getPackageName());
		diagnose(type);
		Resource resource = type.eResource();
		getAndResolveAllFragments(resource);
		recomputeAndCheckIdentifiers(resource);
	}
//TODO - this one fails on the server	
//	@Test public void testFindTypeByName_$ImmutableList() {
//		String typeName = "com.google.inject.internal.util.$ImmutableList";
//		JvmGenericType type = (JvmGenericType) getTypeProvider().findTypeByName(typeName);
//		assertNotNull(type);
//		Iterable<String> innerTypes = transform(filter(type.getMembers(), JvmType.class), new Function<JvmType, String>() {
//			public String apply(JvmType input) {
//				return input.getSimpleName();
//			}
//		});
//		assertTrue("Missing member type $Builder", contains(innerTypes, "Builder"));
//		assertTrue("Missing member type $EmptyImmutableList", contains(innerTypes, "EmptyImmutableList"));
//		assertTrue("Missing member type $RegularImmutableList", contains(innerTypes, "RegularImmutableList"));
//		assertTrue("Missing member type $SerializedForm", contains(innerTypes, "SerializedForm"));
//		assertEquals(4, size(innerTypes));
//		diagnose(type);
//		Resource resource = type.eResource();
//		getAndResolveAllFragments(resource);
//		recomputeAndCheckIdentifiers(resource);
//
//	}
	
	@Override
	public ClasspathTypeProvider getTypeProvider() {
		return typeProvider;
	}
	
	@Override
	protected String getCollectionParamName() {
		return "p0";
	}
	
}
