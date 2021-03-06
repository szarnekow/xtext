/*******************************************************************************
 * Copyright (c) 2008 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *******************************************************************************/
package org.eclipse.xtext.generator.formatting;

import java.util.Set;

import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.formatting.IFormatter;
import org.eclipse.xtext.generator.AbstractGeneratorFragment;
import org.eclipse.xtext.generator.BindFactory;
import org.eclipse.xtext.generator.Binding;
import org.eclipse.xtext.generator.IGeneratorFragment;
import org.eclipse.xtext.generator.Naming;
import org.eclipse.xtext.util.Strings;

/**
 * An {@link IGeneratorFragment} to create a formatter for an Xtext language.
 * 
 * @author Sven Efftinge - Initial contribution and API
 */
public class FormatterFragment extends AbstractGeneratorFragment {
	@Override
	public Set<Binding> getGuiceBindingsRt(Grammar grammar) {
		return new BindFactory().addTypeToType(IFormatter.class.getName(), getFormatterName(grammar, getNaming()))
				.getBindings();
	}

	public static String getFormatterName(Grammar grammar, Naming naming) {
		return naming.basePackageRuntime(grammar) + ".formatting." + GrammarUtil.getName(grammar) + "Formatter";
	}

	@Override
	public String[] getExportedPackagesRt(Grammar grammar) {
		return new String[] { Strings.skipLastToken(getFormatterName(grammar, getNaming()), ".") };
	}
}
