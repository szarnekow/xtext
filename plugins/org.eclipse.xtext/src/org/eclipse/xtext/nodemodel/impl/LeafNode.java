/*******************************************************************************
 * Copyright (c) 2010 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.nodemodel.impl;

import java.io.IOException;
import java.util.Collections;

import org.eclipse.emf.ecore.resource.impl.BinaryResourceImpl.EObjectInputStream;
import org.eclipse.emf.ecore.resource.impl.BinaryResourceImpl.EObjectOutputStream;
import org.eclipse.xtext.nodemodel.ILeafNode;
import org.eclipse.xtext.nodemodel.serialization.DeserializationConversionContext;
import org.eclipse.xtext.nodemodel.serialization.SerializationConversionContext;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 * @author Mark Christiaens - Serialization support
 * @noextend This class is not intended to be subclassed by clients.
 */
public class LeafNode extends AbstractNode implements ILeafNode {

	private int totalOffset;
	
	private int totalLength;
	
	public boolean isHidden() {
		return false;
	}

	public int getTotalOffset() {
		return totalOffset;
	}

	public int getTotalLength() {
		return totalLength;
	}
	
	@Override
	public int getOffset() {
		return totalOffset;
	}
	
	@Override
	public int getLength() {
		return totalLength;
	}
	
	protected void basicSetTotalOffset(int offset) {
		this.totalOffset = offset;
	}
	
	protected void basicSetTotalLength(int length) {
		this.totalLength = length;
	}
	
	@Override
	public Iterable<ILeafNode> getLeafNodes() {
		return Collections.<ILeafNode>singletonList(this);
	}

	/** @since 2.1 */ 
	@Override
	protected void readData(EObjectInputStream in, DeserializationConversionContext context) throws IOException {
		super.readData(in, context);
		
		totalLength = in.readCompressedInt();   
	}
	
	/** @since 2.1 */ 
	@Override
	public void write(EObjectOutputStream out, SerializationConversionContext scc) throws IOException {
		super.write(out, scc);
		out.writeCompressedInt(totalLength); 
	}

	/** @since 2.1 */ 
	@Override
	public NodeType getNodeId() {
		return NodeType.LeafNode;
	}
}
