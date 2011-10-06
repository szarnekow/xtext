/*******************************************************************************
 * Copyright (c) 2011 Sigasi N.V. (http://www.sigasi.com) and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.modelcache;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

import org.apache.log4j.Logger;

/**
 * @author mark.christiaens - Initial contribution and API
 */
public class ModelCacheIndexUtil {

	public static void write(IModelCacheIndex index, File indexLocation, Logger logger) throws IOException {
		DataOutputStream dos = null;
		try {
			if (indexLocation.exists()) {
				Util.deleteFileOrDirectory(indexLocation);
			}
	
			dos = getIndexStream(indexLocation, logger);
			index.write(dos);
		} finally {
			Util.tryClose(dos, logger);
		}
	}

	public static DataOutputStream getIndexStream(File indexLocation, Logger logger) throws FileNotFoundException, IOException {
		FileOutputStream fos = null;
	
		try {
			fos = new FileOutputStream(indexLocation);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			GZIPOutputStream gos = new GZIPOutputStream(bos);
			BufferedOutputStream bgos = new BufferedOutputStream(gos);
			DataOutputStream dos = new DataOutputStream(bgos);
			return dos;
		} catch (IOException e) {
			Util.tryClose(fos, logger);
			throw e;
		}
	}

}
