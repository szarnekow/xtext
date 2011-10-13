package org.eclipse.xtext.ui.resource.cache;

import static org.eclipse.xtext.ui.internal.Activator.*;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IPath;
import org.eclipse.xtext.resource.cache.AlwaysMissCache;
import org.eclipse.xtext.resource.cache.DefaultCache;
import org.eclipse.xtext.resource.cache.ICache;
import org.eclipse.xtext.resource.cache.ReadWriteLockedCache;
import org.eclipse.xtext.ui.internal.Activator;

import com.google.inject.Inject;

public class Cache extends ReadWriteLockedCache {
	private static final Logger LOGGER = Logger.getLogger(ReadWriteLockedCache.class);
	
	@Inject
	public Cache(DefaultCache delegate) {
		super (initDelegate (delegate));
	}
	
	private static ICache initDelegate(DefaultCache delegate) {
		try {
			delegate.init(calcCacheLocation());
		} catch (IOException e) {
			LOGGER.error("Could not initialize the resource cache", e); 
			return new AlwaysMissCache(); 
		}
		
		return delegate;
	}

	public static File calcCacheLocation() {
		Activator activator = getDefault();
		if (activator == null) {
			return null;
		}
		
		IPath path = activator.getStateLocation().append("resource.cache");
		return path.toFile();
	}
}
