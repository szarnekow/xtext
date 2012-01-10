/*******************************************************************************
 * Copyright (c) 2009 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.ui.tests.editor.contentassist;

import org.eclipse.xtext.ISetup;
import org.eclipse.xtext.junit4.ui.AbstractContentAssistProcessorTest;
import org.eclipse.xtext.ui.shared.SharedStateModule;
import org.eclipse.xtext.ui.tests.Activator;
import org.eclipse.xtext.ui.tests.enumrules.EnumRulesUiTestLanguageRuntimeModule;
import org.eclipse.xtext.ui.tests.enumrules.EnumRulesUiTestLanguageStandaloneSetup;
import org.eclipse.xtext.ui.tests.enumrules.ui.EnumRulesUiTestLanguageUiModule;
import org.eclipse.xtext.util.Modules2;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
public class EnumsContentAssistTest extends AbstractContentAssistProcessorTest {
	
	@Override
	public ISetup doGetSetup() {
		return new EnumRulesUiTestLanguageStandaloneSetup() {
			@Override
			public Injector createInjector() {
				return Guice.createInjector(Modules2.mixin(
						new EnumRulesUiTestLanguageRuntimeModule(),
						new EnumRulesUiTestLanguageUiModule(Activator.getInstance()),
						new SharedStateModule()));
			}
		};
	}

    @Test public void testEnumCompletion_01() throws Exception {
    	newBuilder().assertText("existing", "generated");
    }
    
    @Test public void testEnumCompletion_02() throws Exception {
    	newBuilder().append("exi").assertText("existing");
    }
    
    @Test public void testEnumCompletion_03() throws Exception {
    	newBuilder().append(" ").assertText("existing", "generated");
    }
    
    @Test public void testEnumCompletion_04() throws Exception {
    	newBuilder().append("existing").assertText("existing");
    }
    
    @Test public void testEnumCompletion_05() throws Exception {
    	newBuilder().append("existing ").assertText("SameName", "DifferentLiteral", "overridden");
    }
    
    @Test public void testEnumCompletion_06() throws Exception {
    	newBuilder().append("existing Same").assertText("SameName");
    }
    
    @Test public void testEnumCompletion_07() throws Exception {
    	newBuilder().append("generated SameName").assertTextAtCursorPosition(0, "existing", "generated");
    }
    
    @Test public void testEnumCompletion_08() throws Exception {
    	newBuilder().append(" generated SameName").assertTextAtCursorPosition(0, "existing", "generated");
    }
    
    @Test public void testEnumCompletion_09() throws Exception {
    	newBuilder().append(" generated SameName").assertTextAtCursorPosition(2, "generated");
    }

}
