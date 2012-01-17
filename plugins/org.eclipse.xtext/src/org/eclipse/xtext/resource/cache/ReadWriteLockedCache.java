/*******************************************************************************
 * Copyright (c) 2011 Sigasi N.V. (http://www.sigasi.com) and others. All rights
 * reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package org.eclipse.xtext.resource.cache;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.eclipse.xtext.resource.XtextResource;

/** 
 * @author Mark Christiaens - Initial contribution 
 * @since 2.3
 */
public class ReadWriteLockedCache implements ICache {
	final private ICache delegate;

	final private ReadWriteLock lock;

	public ReadWriteLockedCache(ICache delegate) {
		this.delegate = delegate;
		this.lock = new ReentrantReadWriteLock(true);
	}

	public XtextResource load(XtextResource xr, byte[] content, String encoding, boolean addNodeModel)
			throws IOException {
		lock.readLock().lock();
		try {
			return delegate.load(xr, content, encoding, addNodeModel);
		} finally {
			lock.readLock().unlock();
		}
	}

	public void clear() throws IOException {
		lock.writeLock().lock();
		try {
			delegate.clear();
		} finally {
			lock.writeLock().unlock();
		}

	}

	public void init(File cacheLocation) throws IOException {
		lock.writeLock().lock();
		try {
			delegate.init(cacheLocation);
		} finally {
			lock.writeLock().unlock();
		}
	}

	public void add(XtextResource xr, byte[] content, String encoding) throws IOException {
		lock.writeLock().lock();
		try {
			delegate.add(xr, content, encoding);
		} finally {
			lock.writeLock().unlock();
		}
	}

}
