package org.eclipse.xtext.ui.resource.cache;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IPath;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.xtext.resource.cache.AlwaysMissCache;
import org.eclipse.xtext.resource.cache.CacheUtil;
import org.eclipse.xtext.resource.cache.DefaultCache;
import org.eclipse.xtext.resource.cache.ICache;
import org.eclipse.xtext.resource.cache.ReadWriteLockedCache;
import org.osgi.framework.Version;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class Cache extends ReadWriteLockedCache {
	private static final Logger LOGGER = Logger.getLogger(ReadWriteLockedCache.class);

	@Inject
	public Cache(DefaultCache delegate, AbstractUIPlugin myPlugin) {
		super(initDelegate(delegate, myPlugin));
	}

	private static ICache initDelegate(DefaultCache delegate, AbstractUIPlugin plugin) {
		try {
			final File cacheLocation = calcCacheLocation(plugin);
			
			if (cacheLocation == null) {
				throw new IOException("Could not establish the location for the resource cache");
			}

			if (!cacheLocation.exists()) {
				File cacheBaseDir = calcCacheBaseLocation(plugin);
				if (cacheBaseDir == null) {
					throw new IOException("Could not establish the location for the resource cache");
				}
				CacheUtil.deleteFileOrDirectory(cacheBaseDir);
				CacheUtil.mkdir(cacheBaseDir);
			}

			delegate.init(cacheLocation);
		} catch (IOException e) {
			LOGGER.error("Could not initialize the resource cache", e);
			return new AlwaysMissCache();
		}

		return delegate;
	}

	protected static File calcCacheBaseLocation(AbstractUIPlugin plugin) {
		IPath cacheBasePath = calcCacheBasePath(plugin);
		
		if (cacheBasePath == null) {
			return null; 
		}
		
		File cacheBaseDir = cacheBasePath.toFile();
		return cacheBaseDir;
	}

	public static File calcCacheLocation(AbstractUIPlugin plugin) {
		IPath cacheBasePath = calcCacheBasePath(plugin);

		if (cacheBasePath == null) {
			return null;
		}

		Version version = plugin.getBundle().getVersion();
		String versionString = version.toString();
		IPath cacheLocation = cacheBasePath.append(versionString);

		return cacheLocation.toFile();
	}

	public static IPath calcCacheBasePath(AbstractUIPlugin plugin) {
		if (plugin == null) {
			return null;
		}

		IPath pluginPath = plugin.getStateLocation();
		IPath cacheBasePath = pluginPath.append("resource.cache");

		return cacheBasePath;
	}
}
