/*******************************************************************************
 * Copyright (c) 2011 Sigasi N.V. (http://www.sigasi.com) and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package org.eclipse.xtext.resource.cache;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Iterator;

/** @author Mark Christiaens - Initial contribution */ 

public interface ICacheIndex {
	public long getTotalOrigContentSize();

	public Iterator<ICacheEntry> getEntriesByAge ();

	public ICacheEntry createNewEntry(DigestInfo digestInfo, File contentDirectory) throws IOException;

	public void write(DataOutputStream stream) throws IOException;

	public ICacheEntry get(BigInteger digest);

	public void remove(BigInteger digest);

	public void add(ICacheEntry entry);
	
	public int getVersion (); 
}