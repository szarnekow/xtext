/*******************************************************************************
 * Copyright (c) 2011 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.xtend2.ui.highlighting;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.texteditor.ITextEditorActionConstants;
import org.eclipse.xtext.ui.PluginImageHelper;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.ui.editor.actions.IActionContributor;

import com.google.inject.Inject;

/**
 * @author Jan Koehnlein - Initial contribution and API
 */
public class ShowWhitespaceCharactersActionContributor implements IActionContributor {

	@Inject
	private PluginImageHelper imageHelper;

	public void contributeActions(XtextEditor editor) {
		IAction action = editor.getAction(ITextEditorActionConstants.SHOW_WHITESPACE_CHARACTERS);
		action.setImageDescriptor(ImageDescriptor.createFromImage(imageHelper
				.getImage("full/etool16/show_whitespace_chars.gif")));
		action.setDisabledImageDescriptor(ImageDescriptor.createFromImage(imageHelper
				.getImage("full/dtool16/show_whitespace_chars.gif")));
		IToolBarManager toolBarManager = editor.getEditorSite().getActionBars().getToolBarManager();
		toolBarManager.add(action);
	}

}