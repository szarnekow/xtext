/*******************************************************************************
 * Copyright (c) 2009 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.common.types.access.impl;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.common.types.JvmType;
import org.eclipse.xtext.common.types.access.IMirror;
import org.eclipse.xtext.common.types.access.TypeResource;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
public class ClasspathTypeProvider extends AbstractJvmTypeProvider {

	private final ClassFinder classFinder;
	
	private final DeclaredTypeFactory declaredTypeFactory;
	
	private final ClassURIHelper uriHelper;
	
	public ClasspathTypeProvider(ClassLoader classLoader, ResourceSet resourceSet, IndexedJvmTypeAccess indexedJvmTypeAccess) {
		super(resourceSet, indexedJvmTypeAccess);
		classFinder = createClassFinder(classLoader);
		uriHelper = createClassURIHelper();
		declaredTypeFactory = createDeclaredTypeFactory();
	}
	
	@Deprecated
	public ClasspathTypeProvider(ClassLoader classLoader, ResourceSet resourceSet) {
		this(classLoader, resourceSet, null);
	}

	protected ClassFinder createClassFinder(ClassLoader classLoader) {
		return new ClassFinder(classLoader);
	}

	protected DeclaredTypeFactory createDeclaredTypeFactory() {
		return new DeclaredTypeFactory(uriHelper);
	}

	protected ClassURIHelper createClassURIHelper() {
		return new ClassURIHelper();
	}
	
	public ClassURIHelper getClassURIHelper() {
		return uriHelper;
	}
	
	public DeclaredTypeFactory getDeclaredTypeFactory() {
		return declaredTypeFactory;
	}

	public ClassFinder getClassFinder() {
		return classFinder;
	}

	@Override
	public JvmType findTypeByName(String name) {
		try {
			Class<?> clazz = classFinder.forName(name);
			URI resourceURI = uriHelper.createResourceURI(clazz);
			IndexedJvmTypeAccess indexedJvmTypeAccess = getIndexedJvmTypeAccess();
			if (indexedJvmTypeAccess != null) {
				URI proxyURI = resourceURI.appendFragment(uriHelper.getFragment(clazz));
				EObject candidate = indexedJvmTypeAccess.getIndexedJvmType(proxyURI, getResourceSet());
				if (candidate instanceof JvmType)
					return (JvmType) candidate;
			}
			TypeResource result = (TypeResource) getResourceSet().getResource(resourceURI, true);
			return findTypeByClass(clazz, result);
		}
		catch (ClassNotFoundException e) {
			return null;
		}
	}
	
	@Override
	protected IMirror createMirrorForFQN(String name) {
		try {
			Class<?> clazz = classFinder.forName(name);
			return createMirror(clazz);
		} catch (ClassNotFoundException e) {
			return null;
		}
	}
	
	public ClassMirror createMirror(Class<?> clazz) {
		return ClassMirror.createClassMirror(clazz, declaredTypeFactory);
	}
	
	public JvmType findTypeByClass(Class<?> clazz, Resource resource) {
		// TODO: Maybe iterate the resource without computing a fragment
		String fragment = uriHelper.getFragment(clazz);
		JvmType result = (JvmType) resource.getEObject(fragment);
		if (result == null) {
			throw new IllegalStateException("Resource has not been loaded");
		} 
		return result;
	}

}
