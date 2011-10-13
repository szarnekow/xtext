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

import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;

import com.google.inject.Singleton;

/** @author Mark Christiaens - Initial contribution */ 

@Singleton
public class ReadWriteLockedCache implements ICache {
	final private ICache delegate;

	final private ReadWriteLock lock;

	public ReadWriteLockedCache(ICache delegate) {
		this.delegate = delegate;
		this.lock = new ReentrantReadWriteLock(true);
	}

	public Resource load(ResourceSet resourceSet, URI uri,
			boolean requireNodeModel) throws IOException {
		lock.readLock().lock();
		try {
			return delegate.load(resourceSet, uri, requireNodeModel);
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

	public void add(ResourceSet resourceSet, URI uri)
			throws IOException {
		lock.writeLock().lock();
		try {
			delegate.add(resourceSet, uri);
		} finally {
			lock.writeLock().unlock();
		}
	}
	
	public ReadWriteLock getLock() {
		return lock;
	}
}
