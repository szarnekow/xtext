/*******************************************************************************
 * Copyright (c) 2010 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.scoping.impl;

import org.eclipse.emf.ecore.EReference;
import org.eclipse.xtext.scoping.ICaseInsensitivityHelper;
import org.eclipse.xtext.scoping.IgnoreCaseLinking;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
@Singleton
public class CaseInsensitivityHelper implements ICaseInsensitivityHelper {

	@Inject(optional=true)
	@IgnoreCaseLinking
	private boolean isIgnoreCase = false;
	
	public boolean isIgnoreCase(EReference reference) {
		return isIgnoreCase;
	}
	
}
