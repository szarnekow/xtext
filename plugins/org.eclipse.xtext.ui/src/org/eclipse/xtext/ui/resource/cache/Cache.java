package org.eclipse.xtext.ui.resource.cache;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IPath;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.xtext.resource.cache.AlwaysMissCache;
import org.eclipse.xtext.resource.cache.DefaultCache;
import org.eclipse.xtext.resource.cache.ICache;
import org.eclipse.xtext.resource.cache.ReadWriteLockedCache;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class Cache extends ReadWriteLockedCache {
	private static final Logger LOGGER = Logger.getLogger(ReadWriteLockedCache.class);
	
	@Inject
	public Cache(DefaultCache delegate, AbstractUIPlugin myPlugin) {
		super (initDelegate (delegate, myPlugin));
	}
	
	private static ICache initDelegate(DefaultCache delegate, AbstractUIPlugin plugin) {
		try {
			delegate.init(calcCacheLocation(plugin));
		} catch (IOException e) {
			LOGGER.error("Could not initialize the resource cache", e); 
			return new AlwaysMissCache(); 
		}
		
		return delegate;
	}

	public static File calcCacheLocation(AbstractUIPlugin plugin) {
		if (plugin == null) {
			return null; 
		}
		
		IPath path = plugin.getStateLocation().append("resource.cache");
		return path.toFile();
	}
}
