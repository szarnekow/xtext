/*******************************************************************************
 * Copyright (c) 2011 Sigasi N.V. (http://www.sigasi.com) and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package org.eclipse.xtext.nodemodel.serialization;

import java.io.IOException;
import java.util.ArrayList;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.IGrammarAccess;
import org.eclipse.xtext.resource.XtextResource;

import com.google.common.collect.Lists;

/**
 * @author mark.christiaens - Initial contribution and API
 * 
 * @since 2.1
 */
public class DeserializationConversionContext {
	private EObject[] grammarIdToGrammarElementMap;

	final private ArrayList<EObject> idToEObjectMap;

	final private IGrammarAccess grammarAccess;

	final private String completeContent;

	private boolean hasErrors;

	public DeserializationConversionContext(XtextResource xr) throws IOException {
		this.grammarAccess = xr.getResourceServiceProvider().get(IGrammarAccess.class);
		this.idToEObjectMap = Lists.newArrayList();
		this.completeContent = SerializationUtil.getCompleteContent(xr);
		this.hasErrors = false;
		fillIdToEObjectMap(xr);
	}

	public void setGrammarIdToURIMap(String[] grammarIdToURIMap) {
		grammarIdToGrammarElementMap = new EObject[grammarIdToURIMap.length];

		ResourceSet grammarResourceSet = grammarAccess.getGrammar().eResource().getResourceSet();
		for (int grammarId = 0; grammarId < grammarIdToURIMap.length; ++grammarId) {
			URI uri = URI.createURI(grammarIdToURIMap[grammarId], true);
			EObject grammarElement = grammarResourceSet.getEObject(uri, true);

			if (grammarElement == null) {
				throw new IllegalStateException(
						"Apparently the grammar has changed so that it's no longer possible to identify the "
								+ "serialized grammar elements.  The following grammar element URI is no longer valid: "
								+ uri.toString());
			}

			grammarIdToGrammarElementMap[grammarId] = grammarElement;
		}
	}

	public EObject getGrammarElement(Integer grammarId) {
		if (grammarId >= grammarIdToGrammarElementMap.length) {
			throw new IllegalStateException(
					"Trying to obtain a grammar element that does not (or no longer) exists with id: " + grammarId);
		}

		EObject result = grammarIdToGrammarElementMap[grammarId];

		return result;
	}

	public void fillIdToEObjectMap(Resource resource) {
		SerializationUtil.fillIdToEObjectMap(resource, idToEObjectMap);
	}

	public EObject getSemanticObject(int id) {
		EObject eObject = idToEObjectMap.get(id);

		if (eObject == null) {
			throw new IllegalStateException(
					"Trying to get an EMF object in the EMF resource that does not exist.  We are looking for id: "
							+ id);
		}

		return eObject;
	}

	public void setHasErrors(boolean hasErrors) {
		this.hasErrors = hasErrors;
	}

	boolean hasErrors() {
		return hasErrors;
	}

	public String getCompleteContent() {
		return completeContent;
	}
}