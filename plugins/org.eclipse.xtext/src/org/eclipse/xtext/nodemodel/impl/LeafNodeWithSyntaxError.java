/*******************************************************************************
 * Copyright (c) 2010 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.nodemodel.impl;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.eclipse.xtext.nodemodel.SyntaxErrorMessage;
import org.eclipse.xtext.nodemodel.serialization.DeserializationConversionContext;
import org.eclipse.xtext.nodemodel.serialization.SerializationConversionContext;
import org.eclipse.xtext.nodemodel.serialization.Util;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 * @author Mark Christiaens - Serialization support
 * @noextend This class is not intended to be subclassed by clients.
 */
public class LeafNodeWithSyntaxError extends LeafNode {
	private SyntaxErrorMessage syntaxErrorMessage;

	@Override
	public SyntaxErrorMessage getSyntaxErrorMessage() {
		return syntaxErrorMessage;
	}

	protected void basicSetSyntaxErrorMessage(SyntaxErrorMessage syntaxErrorMessage) {
		this.syntaxErrorMessage = syntaxErrorMessage;
	}

	@Override
	protected void readData(DataInputStream in, DeserializationConversionContext context) throws IOException {
		super.readData(in, context);
		syntaxErrorMessage = NodeModelSerializationUtil.readSyntaxErrorMessage(in, context);
		context.setHasErrors(true);
	}

	@Override
	public void write(DataOutputStream out, SerializationConversionContext scc) throws IOException {
		super.write(out, scc);
		Util.write(out, scc, syntaxErrorMessage);
	}

	@Override
	NodeType getNodeId() {
		return NodeType.LeafNodeWithSyntaxError;
	}
}
