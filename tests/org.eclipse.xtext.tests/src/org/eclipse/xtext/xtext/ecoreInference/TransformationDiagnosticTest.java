/*******************************************************************************
 * Copyright (c) 2010 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.xtext.ecoreInference;

import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.impl.LeafNode;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Heiko Behrens - Initial contribution and API
 */
public class TransformationDiagnosticTest extends Assert {

	@Test public void testEnumCode() throws Exception {
		String expected = "org.eclipse.xtext.xtext.ecoreInference.TransformationErrorCode.AliasForMetamodelAlreadyExists";
		assertEquals(expected, TransformationErrorCode.AliasForMetamodelAlreadyExists.getFullyQualifiedCode());
	}
	
	@Test public void testDiagnostic() throws Exception {
		INode node = new LeafNode();
		TransformationDiagnostic diagnostic = new TransformationDiagnostic(node, "message", TransformationErrorCode.FeatureWithDifferentConfigurationAlreadyExists);
		String expected = "org.eclipse.xtext.xtext.ecoreInference.TransformationErrorCode.FeatureWithDifferentConfigurationAlreadyExists";
		assertEquals(expected, diagnostic.getCode());
	}
}
