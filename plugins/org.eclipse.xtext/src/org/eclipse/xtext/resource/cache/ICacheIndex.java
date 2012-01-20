/*******************************************************************************
 * Copyright (c) 2011 Sigasi N.V. (http://www.sigasi.com) and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package org.eclipse.xtext.resource.cache;

import java.io.DataOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.concurrent.locks.ReadWriteLock;

/**
 * @author Mark Christiaens - Initial contribution
 * 
 * @since 2.1
 */

public interface ICacheIndex {
	public long getTotalOrigContentSize();

	/**
	 * Returns iterator over all stored ICacheEntries sorted by usage time (oldest first). Note, that this may be an
	 * iterator from an internal data structure. Acquire the read lock as long as you are using this iterator!
	 */
	public Iterator<ICacheEntry> getEntriesByAge();

	public ICacheEntry createNewEntry(DigestInfo digestInfo);

	public void write(DataOutputStream stream) throws IOException;

	public ICacheEntry get(BigInteger digest);

	public void remove(BigInteger digest);

	public void add(ICacheEntry entry);

	public int getVersion();

	/**
	 * Lock available to achieve longer transactions. Note that, except for getEntriesByAge, the contract is that the
	 * ICacheIndex should synchronize sufficiently to be thread safe internally.
	 */
	public ReadWriteLock getLock();
}