/*******************************************************************************
 * Copyright (c) 2010 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.ui.util;

import org.eclipse.swt.widgets.Display;

/**
 * @author koehnlein - Initial contribution and API
 */
public class DisplayRunHelper {

	public static void runSyncInDisplayThread(Runnable runnable) {
		if (Display.getCurrent() == null) {
			Display.getDefault().syncExec(runnable);
		} else {
			runnable.run();
		}
	}

	public static void runAsyncInDisplayThread(Runnable runnable) {
		if (Display.getCurrent() == null) {
			Display.getDefault().asyncExec(runnable);
		} else {
			runnable.run();
		}
	}
}