/*******************************************************************************
 * Copyright (c) 2010 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.xtext.ui;

import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.text.Region;
import org.eclipse.xtext.AbstractRule;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.XtextPackage;
import org.eclipse.xtext.parsetree.NodeUtil;
import org.eclipse.xtext.resource.EObjectAtOffsetHelper;
import org.eclipse.xtext.resource.ILocationInFileProvider;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.hyperlinking.HyperlinkHelper;
import org.eclipse.xtext.ui.editor.hyperlinking.IHyperlinkAcceptor;
import org.eclipse.xtext.ui.editor.hyperlinking.XtextHyperlink;
import org.eclipse.xtext.util.TextLocation;

import com.google.common.collect.Sets;
import com.google.inject.Inject;

/**
 * Provides hyper links from rule without explicit return type to the
 * classifier and from overriding rules to the base rule.
 * @author Sebastian Zarnekow - Initial contribution and API
 */
public class XtextHyperlinkHelper extends HyperlinkHelper {

	@Inject
	private ILocationInFileProvider locationInFileProvider;
	
	@Override
	public void createHyperlinksByOffset(XtextResource resource, int offset, IHyperlinkAcceptor acceptor) {
		super.createHyperlinksByOffset(resource, offset, acceptor);
		TextLocation location = new TextLocation();
		EObject objectAtOffset = EObjectAtOffsetHelper.resolveElementAt(resource, offset, location);
		if (objectAtOffset instanceof AbstractRule) {
			TextLocation nameLocation = locationInFileProvider.getLocation(objectAtOffset, XtextPackage.Literals.ABSTRACT_RULE__NAME, 0);
			if (nameLocation != null && nameLocation.contains(offset)) {
				AbstractRule rule = (AbstractRule) objectAtOffset;
				createLinksToBase(nameLocation, rule, acceptor);
				if (rule.getType() != null && rule.getType().getClassifier() != null && NodeUtil.getNode(rule.getType()) == null) {
					createHyperlinksTo(resource, toRegion(nameLocation), rule.getType().getClassifier(), acceptor);
				}
			}
		}
	}

	protected void createLinksToBase(TextLocation nameLocation,	AbstractRule rule, IHyperlinkAcceptor acceptor) {
		Set<AbstractRule> visited = Sets.newHashSet();
		Grammar grammar = GrammarUtil.getGrammar(rule);
		for(Grammar used: grammar.getUsedGrammars()) {
			String ruleName = rule.getName();
			AbstractRule overwritten = GrammarUtil.findRuleForName(used, ruleName);
			if (overwritten != null && visited.add(overwritten)) {
				URIConverter uriConverter = rule.eResource().getResourceSet().getURIConverter();
				String hyperlinkText = getLabelProvider().getText(rule) + " - " + GrammarUtil.getGrammar(overwritten).getName();
				URI uri = uriConverter.normalize(EcoreUtil.getURI(overwritten));

				XtextHyperlink result = getHyperlinkProvider().get();
				result.setHyperlinkRegion(toRegion(nameLocation));
				result.setURI(uri);
				result.setHyperlinkText(hyperlinkText);
				result.setTypeLabel("Go To Base");
				acceptor.accept(result);
			}
		}
	}
	
	protected Region toRegion(TextLocation location) {
		return new Region(location.getOffset(), location.getLength());
	}
	
}