/*******************************************************************************
 * Copyright (c) 2011 Sigasi N.V. (http://www.sigasi.com) and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.resource.cache;

import org.eclipse.xtext.testlanguages.ReferenceGrammarTestLanguageStandaloneSetup;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * @author mark.christiaens - Initial contribution and API
 */
public class CachingStandaloneSetup extends ReferenceGrammarTestLanguageStandaloneSetup {
	@Override
	public Injector createInjector() {
		return Guice.createInjector(new CachingRuntimeModule());
	}
}
