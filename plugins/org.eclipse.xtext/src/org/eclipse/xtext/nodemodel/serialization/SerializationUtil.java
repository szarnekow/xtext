/*******************************************************************************
 * Copyright (c) 2011 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.nodemodel.serialization;

import static org.eclipse.xtext.resource.cache.CacheUtil.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.BinaryResourceImpl.EObjectInputStream;
import org.eclipse.emf.ecore.resource.impl.BinaryResourceImpl.EObjectOutputStream;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.nodemodel.SyntaxErrorMessage;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextResourceSet;

/**
 * @author mark.christiaens - Initial contribution and API
 * 
 * @since 2.1
 */
public class SerializationUtil {
	public static void fillIdToEObjectMap(Resource resource, ArrayList<EObject> map) {
		TreeIterator<EObject> allContents = EcoreUtil.getAllContents(resource, false);  
				
		if (allContents.hasNext()) {
			EObject root = allContents.next();
			fillIdToEObjectMap(root, map);
		}
	}

	public static void fillIdToEObjectMap(EObject eObject, ArrayList<EObject> map) {
		map.add(eObject);

		EList<EObject> eContents = eObject.eContents();

		for (EObject child : eContents) {
			fillIdToEObjectMap(child, map);
		}
	}

	public static boolean containsNull(String[] strings) {
		for (String string : strings) {
			if (string == null) {
				return true;
			}
		}

		return false;
	}

	public static long milliDiff(long startLoad, long doneLoad) {
		return (doneLoad - startLoad) / 1000000;
	}

	public static String getCompleteContent(XtextResource xr) throws IOException, UnsupportedEncodingException {
		XtextResourceSet resourceSet = (XtextResourceSet) xr.getResourceSet();
		URIConverter uriConverter = resourceSet.getURIConverter();
		URI uri = xr.getURI();
		String encoding = xr.getEncoding();

		InputStream inputStream = null;

		try {
			inputStream = uriConverter.createInputStream(uri);

			return getCompleteContent(encoding, inputStream);
		} finally {
			org.eclipse.xtext.resource.cache.CacheUtil.tryClose(inputStream, null);
		}
	}

	public static String getCompleteContent(String encoding, InputStream inputStream)
			throws UnsupportedEncodingException, IOException {
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream, encoding);

		StringBuilder sb = new StringBuilder (); 
		char[] buffer = new char[128*KIB];

		int n = inputStreamReader.read(buffer);

		while (n != -1) {
			sb.append (buffer, 0, n);
			n = inputStreamReader.read(buffer);
		}

		return sb.toString();
	}

	public static void write(EObjectOutputStream out, SerializationConversionContext scc,
			SyntaxErrorMessage syntaxErrorMessage) throws IOException {
		if (syntaxErrorMessage == null) {
			out.writeBoolean(true);
		} else {
			out.writeBoolean(false);
			syntaxErrorMessage.write(out, scc);
		}
	}

	public static void writeStringArray(EObjectOutputStream out, String[] ss) throws IOException {
		out.writeBoolean(ss == null);
		if (ss != null) {
			out.writeCompressedInt(ss.length); 
			for (String data : ss) {
				out.writeString(data);
			}
		}
	}

	public static String[] readStringArray(EObjectInputStream in) throws IOException {
		boolean isIssueDataNull = in.readBoolean();
		String[] result = null;
	
		if (!isIssueDataNull) {
			int issueDataLength = in.readCompressedInt();  
			result = new String[issueDataLength];
	
			for (int i = 0; i < issueDataLength; ++i) {
				result[i] = in.readString();
			}
		}
	
		return result;
	}
}
