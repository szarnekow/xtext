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

/**
 * @author mark.christiaens - Initial contribution and API
 */
public class NodeModelSerializationUtil {
	public static SyntaxErrorMessage readSyntaxErrorMessage(DataInputStream in, DeserializationConversionContext context)
			throws IOException {
		boolean isNull = in.readBoolean();
		if (isNull) {
			return null;
		}
	
		SyntaxErrorMessage syntaxErrorMessage = SyntaxErrorMessage.read(in, context);
	
		return syntaxErrorMessage;
	}
}
