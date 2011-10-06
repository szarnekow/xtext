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
		this.completeContent = Util.getCompleteContent(xr);
		this.hasErrors = false; 
		fillIdToEObjectMap(xr);
	}

	public void setGrammarIdToURIMap(String[] grammarIdToURIMap) {
		assert grammarIdToURIMap != null;
		assert !Util.containsNull(grammarIdToURIMap);
		
		grammarIdToGrammarElementMap = new EObject [grammarIdToURIMap.length]; 

		for (int grammarId = 0; grammarId < grammarIdToURIMap.length; ++grammarId) {
			URI uri = URI.createURI(grammarIdToURIMap[grammarId], true);
			ResourceSet grammarResourceSet = grammarAccess.getGrammar().eResource().getResourceSet(); 
			EObject grammarElement = grammarResourceSet.getEObject(uri, true);
			
			assert grammarElement != null; 

			grammarIdToGrammarElementMap[grammarId] = grammarElement;
		}
	}

	public EObject getGrammarElement(Integer grammarId) {
		EObject result = grammarIdToGrammarElementMap[grammarId];

		assert result != null;

		return result;
	}

	public void fillIdToEObjectMap(Resource resource) {
		Util.fillIdToEObjectMap(resource, idToEObjectMap); 
	}
	
	public EObject getSemanticObject(int id) {
		EObject eObject = idToEObjectMap.get(id);

		assert eObject != null;

		return eObject;
	}
	
	public void setHasErrors(boolean hasErrors) {
		this.hasErrors = hasErrors;
	}
	
	boolean hasErrors () {
		return hasErrors; 
	}
	
	public String getCompleteContent() {
		return completeContent;
	}
}
