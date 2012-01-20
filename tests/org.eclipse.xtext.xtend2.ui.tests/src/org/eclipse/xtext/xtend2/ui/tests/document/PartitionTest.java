/*******************************************************************************
 * Copyright (c) 2011 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.xtend2.ui.tests.document;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.xtext.ui.editor.model.XtextDocument;
import org.eclipse.xtext.xtend2.ui.autoedit.TokenTypeToPartitionMapper;
import org.eclipse.xtext.xtend2.ui.tests.AbstractXtend2UITestCase;
import org.junit.Test;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
public class PartitionTest extends AbstractXtend2UITestCase {

	private XtextDocument document;

	@Override
	public void setUp() throws Exception {
		super.setUp();
		document = get(XtextDocument.class);
		IDocumentPartitioner partitioner = get(IDocumentPartitioner.class);
		partitioner.connect(document);
		document.setDocumentPartitioner(partitioner);
	}
	
	@Override
	public void tearDown() throws Exception {
		document = null;
		super.tearDown();
	}
	
	@Test public void testInitialPartitioning() {
		String input = "class SomeType {\n" + 
				"	def someOperation() '''\n" + 
				"		Dear,\n" + 
				"		bla bla foo\n" + 
				"		\n" + 
				"		Yours sincerely,\n" + 
				"		\n" + 
				"		Joe Developer\n" + 
				"		\n" + 
				"	'''	\n" + 
				"}";
		document.set(input);
		ITypedRegion[] partitions = document.getDocumentPartitioner().computePartitioning(0, input.length());
		assertEquals(3, partitions.length);
		ITypedRegion first = partitions[0];
		assertEquals(0, first.getOffset());
		assertEquals(input.indexOf("'"), first.getLength());
		assertEquals(IDocument.DEFAULT_CONTENT_TYPE, first.getType());
		ITypedRegion second = partitions[1];
		assertEquals(input.indexOf("'"), second.getOffset());
		assertEquals(input.lastIndexOf("'") + 1, second.getLength() + second.getOffset());
		assertEquals(TokenTypeToPartitionMapper.RICH_STRING_LITERAL_PARTITION, second.getType());
		ITypedRegion third = partitions[2];
		assertEquals(input.lastIndexOf("'") + 1, third.getOffset());
		assertEquals(input.length(), third.getLength() + third.getOffset());
		assertEquals(IDocument.DEFAULT_CONTENT_TYPE, third.getType());
	}
	
	@Test public void testPartitioningAfterModify_01() throws BadLocationException {
		String input = "class SomeType {\n" + 
				"	def someOperation() '''\n" + 
				"		Dear,\n" + 
				"		bla bla foo\n" + 
				"		\n" + 
				"		Yours sincerely,\n" + 
				"		\n" + 
				"		Joe Developer\n" + 
				"		\n" + 
				"	'''	\n" + 
				"}";
		document.set(input);
		document.replace(input.indexOf("\t\tYours"), 0, "���");
		ITypedRegion[] partitions = document.getDocumentPartitioner().computePartitioning(0, input.length()  + 3 /*���*/);
		assertEquals(4, partitions.length);
		ITypedRegion first = partitions[0];
		assertEquals(0, first.getOffset());
		assertEquals(input.indexOf("'"), first.getLength());
		assertEquals(IDocument.DEFAULT_CONTENT_TYPE, first.getType());
		ITypedRegion second = partitions[1];
		assertEquals(input.indexOf("'"), second.getOffset());
		assertEquals(input.indexOf("\t\tYours") + 1, second.getLength() + second.getOffset());
		assertEquals(TokenTypeToPartitionMapper.RICH_STRING_LITERAL_PARTITION, second.getType());
		ITypedRegion third = partitions[2];
		assertEquals(input.indexOf("\t\tYours") + 1, third.getOffset());
		assertEquals(input.lastIndexOf("'") + 1 + 3 /*���*/, third.getLength() + third.getOffset());
		assertEquals(TokenTypeToPartitionMapper.RICH_STRING_LITERAL_PARTITION, third.getType());
		ITypedRegion forth = partitions[3];
		assertEquals(input.lastIndexOf("'") + 1 + 3 /*���*/, forth.getOffset());
		assertEquals(input.length() + 3 /*���*/, forth.getLength() + forth.getOffset());
		assertEquals(IDocument.DEFAULT_CONTENT_TYPE, forth.getType());
	}
	
	@Test public void testPartitioningAfterModify_02() throws BadLocationException {
		String input = "class SomeType {\n" + 
				"	def someOperation() '''\n" + 
				"		Dear,\n" + 
				"		bla bla foo\n" + 
				"		\n" + 
				"		Yours sincerely,\n" + 
				"		\n" + 
				"		Joe Developer\n" + 
				"		\n" + 
				"	'''	\n" + 
				"}";
		document.set(input);
		document.replace(input.indexOf("\t\tYours"), 0, "���");
		document.replace(input.indexOf("Joe") + 3 /*���*/, 0, "\t");
		ITypedRegion[] partitions = document.getDocumentPartitioner().computePartitioning(0, input.length()  + 4 /*��� + \t*/);
		assertEquals(4, partitions.length);
		ITypedRegion first = partitions[0];
		assertEquals(0, first.getOffset());
		assertEquals(input.indexOf("'"), first.getLength());
		assertEquals(IDocument.DEFAULT_CONTENT_TYPE, first.getType());
		ITypedRegion second = partitions[1];
		assertEquals(input.indexOf("'"), second.getOffset());
		assertEquals(input.indexOf("\t\tYours") + 1, second.getLength() + second.getOffset());
		assertEquals(TokenTypeToPartitionMapper.RICH_STRING_LITERAL_PARTITION, second.getType());
		ITypedRegion third = partitions[2];
		assertEquals(input.indexOf("\t\tYours") + 1, third.getOffset());
		assertEquals(input.lastIndexOf("'") + 1 + 4 /*��� + \t*/, third.getLength() + third.getOffset());
		assertEquals(TokenTypeToPartitionMapper.RICH_STRING_LITERAL_PARTITION, third.getType());
		ITypedRegion forth = partitions[3];
		assertEquals(input.lastIndexOf("'") + 1 + 4 /*��� + \t*/, forth.getOffset());
		assertEquals(document.getLength(), forth.getLength() + forth.getOffset());
		assertEquals(input.length() + 4 /*��� + \t*/, forth.getLength() + forth.getOffset());
		assertEquals(IDocument.DEFAULT_CONTENT_TYPE, forth.getType());
	}
}
