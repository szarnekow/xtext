/*******************************************************************************
 * Copyright (c) 2010 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.ui.editor.outline.actions;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.xtext.ui.editor.outline.IOutlineNode;
import org.eclipse.xtext.ui.editor.outline.impl.OutlineFilterAndSorter;
import org.eclipse.xtext.ui.editor.outline.impl.OutlineFilterAndSorter.IFilter;
import org.eclipse.xtext.ui.editor.outline.impl.OutlinePage;

/**
 * @author Jan Koehnlein - Initial contribution and API
 */
public abstract class AbstractFilterOutlineContribution extends AbstractToggleOutlineContribution {

	private OutlineFilterAndSorter outlineFilterAndSorter;

	private IFilter filter;

	private TreeViewer treeViewer;

	protected abstract boolean apply(IOutlineNode node);

	protected IFilter getFilter() {
		if (filter == null) {
			filter = new IFilter() {
				public boolean apply(IOutlineNode node) {
					return AbstractFilterOutlineContribution.this.apply(node);
				}

				public boolean isEnabled() {
					return isPropertySet();
				}
			};
		}
		return filter;
	}
	
	@Override
	protected void stateChanged(boolean newState) {
		if(!treeViewer.getTree().isDisposed()) 
			treeViewer.refresh();
	}

	@Override
	public void register(OutlinePage outlinePage) {
		super.register(outlinePage);
		outlineFilterAndSorter = outlinePage.getFilterAndSorter();
		outlineFilterAndSorter.addFilter(getFilter());
		treeViewer = outlinePage.getTreeViewer();
	}
	
	@Override
	public void deregister(OutlinePage outlinePage) {
		super.deregister(outlinePage);
		outlineFilterAndSorter.removeFilter(getFilter());
	}
}
