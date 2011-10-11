/*******************************************************************************
 * Copyright (c) 2011 Sigasi N.V. (http://www.sigasi.com) and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package org.eclipse.xtext.resource.cache;

import java.math.BigInteger;

/** @author Mark Christiaens - Initial contribution */ 

public class DigestInfo {
	final private BigInteger digest;
	final private String completeContent;
	
	public DigestInfo(byte [] digest, String completeContent) {
		this (new BigInteger(1, digest), completeContent); 
	}
	
	public DigestInfo (BigInteger digest, String completeContent) {
		this.digest = digest;   
		this.completeContent = completeContent;
	}
	
	public String getCompleteContent() {
		return completeContent;
	}
	
	public BigInteger getDigest() {
		return digest;
	}
	
	@Override
	public String toString() {
		return digest.toString(); 
	}
}
