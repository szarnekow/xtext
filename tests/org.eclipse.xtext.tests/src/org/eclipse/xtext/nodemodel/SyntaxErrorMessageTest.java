/*******************************************************************************
 * Copyright (c) 2011 Sigasi N.V. (http://www.sigasi.com) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.nodemodel;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.eclipse.emf.ecore.resource.impl.BinaryResourceImpl;
import org.eclipse.emf.ecore.resource.impl.BinaryResourceImpl.EObjectInputStream;
import org.eclipse.emf.ecore.resource.impl.BinaryResourceImpl.EObjectOutputStream;

import com.google.common.collect.ImmutableMap;

import junit.framework.TestCase;

/** @author Mark Christiaens */

public class SyntaxErrorMessageTest extends TestCase {
	public void testSerialization() throws IOException {
		final String message = "hi";
		String [] issueCodes = { null, "issue" };
		String [][] issueDatas = { null, {null}, {"issue data"}};
		
		for (String[] issueData : issueDatas) {
			for (String issueCode : issueCodes) {
				SyntaxErrorMessage sem = new SyntaxErrorMessage(message, issueCode, issueData);
				ByteArrayOutputStream out = new ByteArrayOutputStream ();
				EObjectOutputStream eout = new BinaryResourceImpl.EObjectOutputStream(out, ImmutableMap.of ()); 
				sem.write(eout, null);
				out.flush (); 
				out.close (); 
				byte[] array = out.toByteArray();
				ByteArrayInputStream in = new ByteArrayInputStream(array); 
				EObjectInputStream ein = new BinaryResourceImpl.EObjectInputStream(in, ImmutableMap.of());
				SyntaxErrorMessage sem2 = SyntaxErrorMessage.read(ein, null);
				assertEquals(sem, sem2); 
			}
		}
	}
}
