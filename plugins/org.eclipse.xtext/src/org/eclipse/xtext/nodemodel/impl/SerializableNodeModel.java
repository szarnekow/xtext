/*******************************************************************************
 * Copyright (c) 2011 Sigasi N.V. (http://www.sigasi.com) and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package org.eclipse.xtext.nodemodel.impl;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Date;

import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.serialization.DeserializationConversionContext;
import org.eclipse.xtext.nodemodel.serialization.SerializationConversionContext;
import org.eclipse.xtext.nodemodel.serialization.Util;
import org.eclipse.xtext.resource.XtextResource;

/** @author Mark Christiaens - Initial contribution */ 

public class SerializableNodeModel {
	public int formatVersion;
	public Date date;
	public RootNode root;

	public SerializableNodeModel(XtextResource resource) {
		ICompositeNode rootNode = resource.getParseResult().getRootNode();

		if (rootNode != null) {
			root = (RootNode) rootNode;
		}

		formatVersion = 1;
		date = new Date();
	}

	public SerializableNodeModel() {
	}

	public void writeObjectData(DataOutputStream out, SerializationConversionContext scc) throws IOException {
		String[] grammarIdToURIMap = scc.getGrammarIdToURIMap();

		out.writeInt(grammarIdToURIMap.length);
		for (String string : grammarIdToURIMap) {
			assert string != null; 
			out.writeUTF(string);
		}

		root.write(out, scc);
	}

	public void readObjectData(DataInputStream in, DeserializationConversionContext context) throws IOException {
		int grammarIdToURIMapLength = in.readInt();
		
		String[] grammarIdToURIMap = new String [grammarIdToURIMapLength];
		for (int i = 0; i < grammarIdToURIMapLength; ++i) {
			grammarIdToURIMap[i] = in.readUTF(); 
		}

		assert grammarIdToURIMap != null;
		assert !Util.containsNull(grammarIdToURIMap);

		context.setGrammarIdToURIMap(grammarIdToURIMap);

		root = RootNode.read(in, context); 
	}
}
