/*******************************************************************************
 * Copyright (c) 2010 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.ui.editor.outline.impl;

import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.graphics.Image;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;
import org.eclipse.xtext.ui.editor.outline.IOutlineNode;
import org.eclipse.xtext.util.ITextRegion;
import org.eclipse.xtext.util.concurrent.IUnitOfWork;

import com.google.common.collect.Lists;

/**
 * @author koehnlein - Initial contribution and API
 */
public abstract class AbstractOutlineNode implements IOutlineNode {

	private Image image;

	private Object text;

	private AbstractOutlineNode parent;

	private List<IOutlineNode> children;

	private boolean isLeaf = false;

	private ITextRegion textRegion;

	protected AbstractOutlineNode(IOutlineNode parent, Image image, Object text, boolean isLeaf) {
		this.text = text == null ? "<unnamed>" : text;
		this.image = image;
		this.isLeaf = isLeaf;
		setParent(parent);
		textRegion = ITextRegion.EMPTY_REGION;
	}

	protected void setParent(IOutlineNode newParent) {
		Assert.isLegal(newParent == null || newParent instanceof AbstractOutlineNode);
		if (parent != null)
			parent.removeChild(this);
		parent = (AbstractOutlineNode) newParent;
		if (parent != null)
			parent.addChild(this);
	}

	protected boolean addChild(IOutlineNode outlineNode) {
		if (children == null)
			children = Lists.newArrayList();
		isLeaf = false;
		return children.add(outlineNode);
	}

	protected boolean removeChild(IOutlineNode outlineNode) {
		if (children == null)
			return false;
		return children.remove(outlineNode);
	}

	public List<IOutlineNode> getChildren() {
		if (isLeaf)
			return Collections.emptyList();
		if (children == null) {
			readOnly(new IUnitOfWork.Void<EObject>() {
				@Override
				public void process(EObject eObject) throws Exception {
					getTreeProvider().createChildren(AbstractOutlineNode.this, eObject);
				}
			});
			if(children == null) {
				// tree provider did not create any child
				isLeaf = true;
				return Collections.emptyList();
			}
		}
		return Collections.unmodifiableList(children);
	}

	public IOutlineNode getParent() {
		return parent;
	}

	public boolean hasChildren() {
		return !isLeaf;
	}

	public Object getText() {
		return text;
	}

	public void setText(Object text) {
		this.text = text;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public IXtextDocument getDocument() {
		if (parent != null) {
			return parent.getDocument();
		}
		return null;
	}

	public IOutlineTreeStructureProvider getTreeProvider() {
		if (parent != null) {
			return parent.getTreeProvider();
		}
		return null;
	}

	public void setTextRegion(ITextRegion textRegion) {
		this.textRegion = textRegion;
	}

	public ITextRegion getFullTextRegion() {
		return textRegion;
	}

	public ITextRegion getSignificantTextRegion() {
		return textRegion;
	}

	@Override
	public String toString() {
		return "[" + getClass().getSimpleName() + "] " + text.toString();
	}

	@SuppressWarnings("rawtypes")
	public Object getAdapter(Class adapterType) {
		return Platform.getAdapterManager().getAdapter(this, adapterType);
	}

	protected URI getEObjectURI() {
		return null;
	}

	public <T> T readOnly(final IUnitOfWork<T, EObject> work) {
		if (getEObjectURI() != null) {
			return getDocument().readOnly(new IUnitOfWork<T, XtextResource>() {
				public T exec(XtextResource state) throws Exception {
					EObject eObject = state.getEObject(getEObjectURI().fragment());
					return work.exec(eObject);
				}

			});
		} else {
			return null;
		}
	}

	public <T> T modify(IUnitOfWork<T, EObject> work) {
		throw new UnsupportedOperationException("Elements cannot be modified in content outline");
	}

}