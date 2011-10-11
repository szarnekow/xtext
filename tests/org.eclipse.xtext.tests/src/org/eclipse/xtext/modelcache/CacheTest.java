/*******************************************************************************
 * Copyright (c) 2008 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.modelcache;

import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.junit.AbstractXtextTests;
import org.eclipse.xtext.linking.lazy.LazyLinkingResource;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.NodeModelTest;
import org.eclipse.xtext.nodemodel.impl.NodeModelEqualityHelper;
import org.eclipse.xtext.nodemodel.impl.InvariantChecker;
import org.eclipse.xtext.nodemodel.serialization.SerializationUtil;
import org.eclipse.xtext.nodemodel.util.NodeIterable;
import org.eclipse.xtext.nodemodel.util.NodeIterator;
import org.eclipse.xtext.nodemodel.util.NodeTreeIterator;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.eclipse.xtext.util.CancelIndicator;
import org.eclipse.xtext.util.StringInputStream;

import com.google.common.collect.Maps;
import com.google.inject.Inject;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
public class CacheTest extends AbstractXtextTests {
	private TempModelCache modelCache;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		with(CachingStandaloneSetup.class);
		modelCache = get(TempModelCache.class);
	}

	public void testLoadFromCache() throws Exception {
		final String simpleModel = "spielplatz 1 { kind ( Bob 0 ) kind ( Isabella error ) } ";
		final URI uri = URI.createURI("mytestmodel." + getCurrentFileExtension());

		XtextResource resourceUncached = getResourceAndExpect(getAsStream(simpleModel), uri, 1);
		assertFalse(modelCache.isLastAccessHit());

		XtextResource resourceCached = getResourceAndExpect(getAsStream(simpleModel), uri, 1);
		assertTrue(modelCache.isLastAccessHit());

		EcoreUtil.EqualityHelper eq = new EcoreUtil.EqualityHelper();
		final EList<EObject> contentsUncached = resourceUncached.getContents();
		final EList<EObject> contentsCached = resourceCached.getContents();
		
		assertTrue (contentsUncached.size () > 0); 
		assertEquals(contentsUncached.size(), contentsCached.size ()); 
		
		final EObject topUncached = contentsUncached.get(0);
		final EObject topCached = contentsCached.get(0);
		assertTrue(eq.equals(topCached, topUncached));
		
		Map <EObject, EObject> correspondanceMap = buildCorrespondenceMap (contentsUncached, contentsCached); 
		
		InvariantChecker ic = new InvariantChecker (); 

		final ICompositeNode rootUncached = resourceUncached.getParseResult().getRootNode();
		ic.checkInvariant(rootUncached);
		
		final ICompositeNode rootCached = resourceCached.getParseResult().getRootNode();
		ic.checkInvariant(rootCached);
		
		NodeTreeIterator itUncached = new NodeTreeIterator(rootUncached); 
		NodeTreeIterator itCached = new NodeTreeIterator (rootCached);
		
		while (itUncached.hasNext()) {
			assertTrue (itCached.hasNext()); 
			INode uncachedNode = itUncached.next(); 
			INode cachedNode = itCached.next(); 
			
			assertTrue (NodeModelEqualityHelper.isEqual(uncachedNode, cachedNode, correspondanceMap)); 
		}
		
		assertFalse (itUncached.hasNext()); 
		assertFalse (itCached.hasNext()); 
	}

	private Map<EObject, EObject> buildCorrespondenceMap(EList<EObject> left, EList<EObject> right) {
		Map <EObject, EObject> result = Maps.newHashMap(); 
		
		buildCorrespondenceMap (left, right, result); 
		
		return result; 
	}

	private void buildCorrespondenceMap(EList<EObject> left, EList<EObject> right, Map<EObject, EObject> result) {
		if (left.size() != right.size ()) {
			throw new IllegalArgumentException("Left and right lists should have the same size"); 
		}
		
		Iterator<EObject> leftIt = left.iterator();
		Iterator<EObject> rightIt = right.iterator();
		
		while (leftIt.hasNext()) {
			EObject l = leftIt.next ();
			EObject r = rightIt.next ();
			
			result.put(l, r); 
			
			EList<EObject> lContents = l.eContents(); 
			EList<EObject> rContents = r.eContents(); 
			buildCorrespondenceMap(lContents, rContents, result); 
		}		
		
		return; 
	}

	@Override
	protected XtextResource doGetResource(InputStream in, URI uri) throws Exception {
		XtextResourceSet rs = get(XtextResourceSet.class);
		rs.setClasspathURIContext(getClass());
		XtextResource resource = (XtextResource) getResourceFactory().createResource(uri);
		rs.getResources().add(resource);
		SyntheticModelAwareURIConverter URIConvertor = new SyntheticModelAwareURIConverter();
		String inContent = SerializationUtil.getCompleteContent(resource.getEncoding(), in);
		URIConvertor.addModel(uri.toString(), inContent);
		rs.setURIConverter(URIConvertor);
		StringInputStream newIn = new StringInputStream (inContent); 

		resource.load(newIn, null);
		if (resource instanceof LazyLinkingResource) {
			((LazyLinkingResource) resource).resolveLazyCrossReferences(CancelIndicator.NullImpl);
		} else {
			EcoreUtil.resolveAll(resource);
		}
		return resource;
	}
}
