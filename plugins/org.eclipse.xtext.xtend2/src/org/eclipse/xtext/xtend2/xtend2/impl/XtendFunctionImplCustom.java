/*******************************************************************************
 * Copyright (c) 2010 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.xtend2.xtend2.impl;

import org.eclipse.xtext.util.EmfFormatter;
import org.eclipse.xtext.xtend2.xtend2.Xtend2Package;

/**
 * @author Sven Efftinge - Initial contribution and API
 */
public class XtendFunctionImplCustom extends XtendFunctionImpl {
	@Override
	public String getSimpleName() {
		return getName();
	}
	
	@Override
	public String toString() {
		return EmfFormatter.objToStr(this, Xtend2Package.Literals.XTEND_FUNCTION__EXPRESSION);
	}
}