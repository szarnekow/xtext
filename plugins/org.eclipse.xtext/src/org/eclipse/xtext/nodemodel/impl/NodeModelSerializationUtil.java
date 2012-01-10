/*******************************************************************************
 * Copyright (c) 2011 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.nodemodel.impl;

import java.io.DataInputStream;
import java.io.IOException;

import org.eclipse.xtext.nodemodel.SyntaxErrorMessage;
import org.eclipse.xtext.nodemodel.serialization.DeserializationConversionContext;
import org.eclipse.xtext.nodemodel.serialization.SerializationUtil;

/**
 * @author mark.christiaens - Initial contribution and API
 * 
 * @since 2.3
 * @noextend This class is not intended to be subclassed by clients.
 * @noinstantiate This class is not intended to be instantiated by clients.
 */
class NodeModelSerializationUtil {
	
	static SyntaxErrorMessage readSyntaxErrorMessage(DataInputStream in, DeserializationConversionContext context)
			throws IOException {
		boolean isNull = in.readBoolean();
		if (isNull) {
			return null;
		}
		SyntaxErrorMessage result = SerializationUtil.readSyntaxErrorMessage(in, context);
		return result;
	}

	static AbstractNode createChildNode(AbstractNode.NodeType type) {
		switch (type) {
			case CompositeNode:
				return new CompositeNode();
			case CompositeNodeWithSemanticElement:
				return new CompositeNodeWithSemanticElement();
			case CompositeNodeWithSemanticElementAndSyntaxError:
				return new CompositeNodeWithSemanticElementAndSyntaxError();
			case CompositeNodeWithSyntaxError:
				return new CompositeNodeWithSyntaxError();
			case HiddenLeafNode:
				return new HiddenLeafNode();
			case HiddenLeafNodeWithSyntaxError:
				return new HiddenLeafNodeWithSyntaxError();
			case LeafNode:
				return new LeafNode();
			case LeafNodeWithSyntaxError:
				return new LeafNodeWithSyntaxError();
			case RootNode:
				return new RootNode();
			default:
				throw new IllegalArgumentException("Trying to construct a non-existing INode");
		}
	}
}
