package org.eclipse.xtext.junit4.ui.util;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.Bundle;

public class PluginUtil {

	public static IPath findPathInPlugin(Plugin srcPlugin, String fileName)
			throws IOException {
		URL bundleUrl = srcPlugin.getBundle().getResource(fileName);
		URL fileUrl = FileLocator.toFileURL(bundleUrl);
		Path filePath = new Path(fileUrl.getPath());
		return filePath;
	}

	public static IFile copyFileToWorkspace(Plugin srcPlugin,
			String srcFilePath, IProject project, String destFilePath)
			throws IOException, CoreException {
		IFile destFile = project.getFile(destFilePath);
		return copyFileToWorkspace(srcPlugin, srcFilePath, destFile);
	}

	public static IFile copyFileToWorkspace(Plugin srcPlugin,
			String srcFilePath, IFile destFile) throws IOException,
			CoreException {
		Bundle bundle = srcPlugin.getBundle();
		URL bundleUrl = bundle.getResource(srcFilePath);
		URL fileUrl = FileLocator.toFileURL(bundleUrl);
		InputStream openStream = new BufferedInputStream(fileUrl.openStream());
		if (destFile.exists()) {
			destFile.delete(true, null);
		}
		destFile.create(openStream, true, null);
		return destFile;
	}
}
