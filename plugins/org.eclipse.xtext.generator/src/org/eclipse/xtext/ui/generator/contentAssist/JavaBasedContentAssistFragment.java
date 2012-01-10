/*******************************************************************************
 * Copyright (c) 2008, 2009 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.ui.generator.contentAssist;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.eclipse.xpand2.XpandExecutionContext;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.generator.AbstractGeneratorFragment;
import org.eclipse.xtext.generator.BindFactory;
import org.eclipse.xtext.generator.Binding;
import org.eclipse.xtext.generator.IGeneratorFragment;

/**
 * A {@link IGeneratorFragment} to generate a java-based content assist for an Xtext language.
 *
 * @author Jan Koehnlein
 */
public class JavaBasedContentAssistFragment extends AbstractGeneratorFragment {

	private boolean inherit = true;

	@Override
	public Set<Binding> getGuiceBindingsUi(Grammar grammar) {
		return new BindFactory()
			.addTypeToType("org.eclipse.xtext.ui.editor.contentassist.IContentProposalProvider",
					getProposalProviderClassName(grammar))
			.getBindings();
	}

	private String getProposalProviderClassName(Grammar grammar) {
		return getNaming().basePackageUi(grammar)+ ".contentassist." + GrammarUtil.getName(grammar) + "ProposalProvider";
	}

	@Override
	public String[] getRequiredBundlesUi(Grammar grammar) {
		return new String[]{
				"org.eclipse.xtext.ui"
		};
	}

	@Override
	public String[] getImportedPackagesUi(Grammar grammar) {
		return new String[] { "org.apache.log4j" };
	}

	@Override
	public String[] getExportedPackagesUi(Grammar grammar) {
		return new String[] { getNaming().packageName(getProposalProviderClassName(grammar)) };
	}
	
	@Override
	protected List<Object> getParameters(Grammar grammar) {
		return Collections.<Object>singletonList(inherit);
	}

	/**
	 * Decide whether to inherit content assist from the super language.
	 *
	 * @param inherit
	 */
	public void setInherit(boolean inherit) {
		this.inherit = inherit;
	}

	public boolean isInherit() {
		return inherit;
	}

	@Override
	public void generate(Grammar grammar, XpandExecutionContext ctx) {
		super.generate(grammar, ctx);
	}
}
