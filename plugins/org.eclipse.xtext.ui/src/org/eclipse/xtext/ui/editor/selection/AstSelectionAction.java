/*******************************************************************************
 * Copyright (c) 2011 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.ui.editor.selection;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.util.ITextRegion;
import org.eclipse.xtext.util.TextRegion;
import org.eclipse.xtext.util.concurrent.IUnitOfWork;

/**
 * @author Michael Clay - Initial contribution and API
 */
public abstract class AstSelectionAction extends Action {
	private XtextEditor xtextEditor;

	public XtextEditor getXtextEditor() {
		return xtextEditor;
	}

	public AstSelectionAction(String text, XtextEditor xtextEditor) {
		super(text);
		Assert.isNotNull(xtextEditor);
		this.xtextEditor = xtextEditor;
	}

	@Override
	public void run() {
		ITextSelection selection = (ITextSelection) xtextEditor.getSelectionProvider().getSelection();
		ITextRegion currentEditorSelection = new TextRegion(selection.getOffset(), selection.getLength());
		ITextRegion nextSelection = xtextEditor.getDocument().readOnly(createTextSelectionWork(currentEditorSelection));
		if (nextSelection == null || nextSelection == ITextRegion.EMPTY_REGION
				|| nextSelection.equals(currentEditorSelection)) {
			return;
		}
		xtextEditor.selectAndReveal(nextSelection.getOffset(), nextSelection.getLength());
	}

	protected IUnitOfWork<ITextRegion, XtextResource> createTextSelectionWork(final ITextRegion selection) {
		return new IUnitOfWork<ITextRegion, XtextResource>() {
			public ITextRegion exec(XtextResource xtextResource) throws Exception {
				ITextRegion result = selection;
				if (xtextResource.getParseResult() != null && xtextResource.getParseResult().getRootNode() != null) {
					result = internalSelect(xtextResource, selection);
				}
				return result;
			}
		};
	}
	
	protected abstract ITextRegion internalSelect(XtextResource xtextResource, ITextRegion textRegion);

}
