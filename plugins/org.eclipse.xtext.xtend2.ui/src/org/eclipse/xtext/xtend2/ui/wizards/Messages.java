/*******************************************************************************
 * Copyright (c) 2011 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.xtend2.ui.wizards;

import org.eclipse.osgi.util.NLS;

/**
 * @author skomp - Initial contribution and API
 */
public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.xtext.xtend2.ui.wizards.messages"; //$NON-NLS-1$
	public static String ERROR_CREATING_PACKAGE;
	public static String ERROR_CREATING_CLASS;
	public static String XTEND_CLASS_WIZARD_DESCRIPTION;
	public static String TYPE_EXISTS_0;
	public static String TYPE_EXISTS_1;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
