/*******************************************************************************
 * Copyright (c) 2011 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.xtend2.ui.tests.folding;

import java.util.Collection;

import org.eclipse.core.resources.IFile;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.ui.editor.folding.FoldedPosition;
import org.eclipse.xtext.ui.editor.folding.IFoldingRegionProvider;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;
import org.eclipse.xtext.xtend2.ui.tests.AbstractXtend2UITestCase;
import org.eclipse.xtext.xtend2.ui.tests.WorkbenchTestHelper;
import org.junit.Test;

import com.google.inject.Inject;

/**
 * @author Holger Schill - Initial contribution and API
 */
public class Xtend2FoldingRegionProviderTest extends AbstractXtend2UITestCase {

	@Inject
	private WorkbenchTestHelper testHelper;

	@Inject
	private IFoldingRegionProvider foldingRegionProvider;

	@Test public void testNoFoldingIfOnlyOneImport() throws Exception {
		String content = "package test\nimport java.util.List\nclass Bar{}";
		IFile iFile = testHelper.createFile("test/Bar",content);
		Collection<FoldedPosition> foldingRegions = foldingRegionProvider.getFoldingRegions(openFileAndReturnDocument(iFile));
		assertTrue(foldingRegions.isEmpty());
	}
	
	@Test public void testFoldingOfMoreThanOneImports() throws Exception {
		String content = "package test\nimport java.util.List\nimport java.util.Set\nclass Bar{}";
		IFile iFile = testHelper.createFile("test/Bar",content);
		Collection<FoldedPosition> foldingRegions = foldingRegionProvider.getFoldingRegions(openFileAndReturnDocument(iFile));
		assertEquals(1, foldingRegions.size());
		FoldedPosition first = foldingRegions.iterator().next();
		assertEquals(13, first.getOffset());
		assertEquals(43, first.length);
	}
	
	protected IXtextDocument openFileAndReturnDocument(IFile iFile) throws Exception {
		XtextEditor editor = testHelper.openEditor(iFile);
		IXtextDocument document = editor.getDocument();
		return document;
	}
	
	@Override
	public void tearDown() throws Exception {
		testHelper.tearDown();
		super.tearDown();
	}
}
