/*******************************************************************************
 * Copyright (c) 2011 Sigasi N.V. (http://www.sigasi.com) and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package org.eclipse.xtext.modelcache;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Comparator;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextResourceSet;

import com.google.common.collect.Lists;

/** @author Mark Christiaens - Initial contribution */

public class Util {
	public static final int KIB = 1024;
	public static final int MIB = 1024 * KIB;
	public static final int GIB = 1024 * MIB;

	static public void deleteFileOrDirectory(File fileOrDir) throws IOException {
		if (fileOrDir.exists()) {
			if (fileOrDir.isDirectory()) {
				File[] files = fileOrDir.listFiles();
				for (File file : files) {
					deleteFileOrDirectory(file);
				}
			}

			boolean deleted = fileOrDir.delete();

			if (!deleted) {
				throw new IOException("Could not delete directory: " + fileOrDir.toString());
			}
		}
	}

	static public void mkdir(File dir) throws IOException {
		if (!dir.mkdir()) {
			throw new IOException("Could not create directory: " + dir.toString());
		}
	}

	public static class AgeComparator implements Comparator<ICacheEntry> {
		public static final AgeComparator AGE_COMPARATOR = new AgeComparator();

		public int compare(ICacheEntry o1, ICacheEntry o2) {
			if (o1 != null && o2 != null) {
				long time1 = o1.getLastUsageTime();
				long time2 = o2.getLastUsageTime();

				if (time1 == time2) {
					return 0;
				}

				if (time1 < time2) {
					return -1;
				}

				return 1;
			}

			assert false;
			return 0;
		}
	}

	public static void removeAllResourcesWithURI(ResourceSet resourceSet, URI uri) {
		List<Resource> toRemove = Lists.newArrayList();
		EList<Resource> resources = resourceSet.getResources();
		for (Resource resource : resources) {
			if (resource.getURI().equals(uri)) {
				toRemove.add(resource);
			}
		}

		for (Resource resource : toRemove) {
			resourceSet.getResources().remove(resource);
		}
	}

	static public DigestInfo calcDigest(XtextResourceSet resourceSet, URI uri) throws IOException,
			UnsupportedEncodingException {
		XtextResource xR = (XtextResource) resourceSet.getResource(uri, false);
		if (xR == null) {
			xR = (XtextResource) resourceSet.createResource(uri);
		}

		String completeContent = org.eclipse.xtext.nodemodel.serialization.Util.getCompleteContent(xR);

		return calcDigest(completeContent);
	}

	public static DigestInfo calcDigest(String completeContent) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-1");
			md.update(completeContent.getBytes());

			return new DigestInfo(md.digest(), completeContent);
		} catch (NoSuchAlgorithmException e) {
			/* Should be impossible */
			e.printStackTrace();
			return null; 
		}
	}

	public static void tryClose(Closeable stream, Logger logger) throws IOException {
		if (stream != null) {
			try {
				stream.close();
			} catch (Exception e) {
				if (logger != null) {
					logger.error("Could not close an stream for a cache entry: " + e);
				}
			}
		}
	}
}
