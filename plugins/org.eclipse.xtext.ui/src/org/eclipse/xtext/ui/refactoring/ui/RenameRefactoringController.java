/*******************************************************************************
 * Copyright (c) 2011 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.ui.refactoring.ui;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.xtext.resource.IGlobalServiceProvider;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.ui.refactoring.IRenameStrategy;
import org.eclipse.xtext.util.Strings;
import org.eclipse.xtext.util.concurrent.IUnitOfWork;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

/**
 * @author Jan Koehnlein - Initial contribution and API
 */
@Singleton
public class RenameRefactoringController {

	@Inject(optional = true)
	private IWorkbench workbench;

	@Inject
	private Provider<RenameLinkedMode> renameLinkedModeProvider;

	@Inject
	private IRenameSupport.Factory renameSupportFactory;

	@Inject
	private IGlobalServiceProvider globalServiceProvider;

	private RenameLinkedMode activeLinkedMode;

	private IRenameElementContext renameElementContext;

	private LinkedEditingUndoSupport undoSupport;

	private String newName;
	
	public void initialize(IRenameElementContext renameElementContext) {
		this.renameElementContext = renameElementContext;
		this.newName = null;
	}

	public void startRefactoring(RefactoringType refactoringType) {
		ViewFreezer freezer = null;
		try {
			if (refactoringType == RefactoringType.LINKED_EDITING) {
				startLinkedEditing();
				return;
			}
			if (activeLinkedMode != null)
				this.newName = activeLinkedMode.getCurrentName();
			freezer = new ViewFreezer(getXtextEditor().getInternalSourceViewer());
			freezer.freeze();
			// Cancel undoable right now because the freezer would show the old
			// state and not the new one
			if (undoSupport != null) {
				undoSupport.undoDocumentChanges();
				undoSupport = null;
			}
			cancelLinkedMode();
			switch (refactoringType) {
				case REFACTORING_DIRECT:
					startDirectRefactoring();
					break;
				case REFACTORING_DIALOG:
					startRefactoringWithDialog(false);
					break;
				case REFACTORING_PREVIEW:
					startRefactoringWithDialog(true);
					break;
				default:
					throw new IllegalStateException("Invalid refactoring type " + refactoringType.toString());
			}
		} catch (InterruptedException exc) {
			// canceling by the user is ok
			restoreOriginalSelection();
		} finally {
			if (freezer != null)
				freezer.release();
		}
	}

	protected void startLinkedEditing() {
		if (activeLinkedMode != null) {
			if (activeLinkedMode.isSameRenameElementContext(renameElementContext)) {
				startRefactoring(RefactoringType.REFACTORING_DIALOG);
				return;
			} else {
				cancelLinkedMode();
			}
		}

		try {
			final XtextEditor xtextEditor = getXtextEditor();
			if (xtextEditor != null) {
				workbench.getProgressService().run(false, true, new IRunnableWithProgress() {
					public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
						RenameLinkedMode newLinkedMode = renameLinkedModeProvider.get();
						if (newLinkedMode.start(renameElementContext, monitor)) {
							activeLinkedMode = newLinkedMode;
							undoSupport = new LinkedEditingUndoSupport(xtextEditor);
						}
					}
				});
				if (activeLinkedMode == null) {
					startRefactoring(RefactoringType.REFACTORING_DIALOG);
				}
			}
		} catch (Exception exc) {
			// unwrap invocation target exceptions
			if (exc.getCause() instanceof RuntimeException)
				throw (RuntimeException) exc.getCause();
			if (exc instanceof RuntimeException)
				throw (RuntimeException) exc;
			throw new WrappedException(exc);
		}
	}

	public RenameLinkedMode getActiveLinkedMode() {
		return activeLinkedMode;
	}

	/**
	 * Linked mode is unrecoverable canceled.
	 */
	public void cancelLinkedMode() {
		if (activeLinkedMode != null) {
			activeLinkedMode.linkedModeLeft();
			activeLinkedMode = null;
		}
	}

	protected void startDirectRefactoring() throws InterruptedException {
		if (Strings.isEmpty(newName))
			restoreOriginalSelection();
		else
			createRenameSupport(renameElementContext, newName).startDirectRefactoring();
	}

	protected void startRefactoringWithDialog(final boolean previewOnly) throws InterruptedException {
		if (Strings.isEmpty(newName))
			newName = getOriginalName(getXtextEditor());
		if (Strings.isEmpty(newName))
			restoreOriginalSelection();
		else
			createRenameSupport(renameElementContext, newName).startRefactoringWithDialog(previewOnly);
	}

	protected String getOriginalName(final XtextEditor xtextEditor) {
		return xtextEditor.getDocument().readOnly(new IUnitOfWork<String, XtextResource>() {
			public String exec(XtextResource state) throws Exception {
				EObject targetElement = state.getResourceSet().getEObject(renameElementContext.getTargetElementURI(),
						false);
				IRenameStrategy.Provider strategyProvider = globalServiceProvider.findService(targetElement,
						IRenameStrategy.Provider.class);
				if (strategyProvider != null) {
					IRenameStrategy strategy = strategyProvider.get(targetElement, renameElementContext);
					if (strategy != null)
						return strategy.getOriginalName();
				}
				return null;
			}
		});
	}

	protected IRenameSupport createRenameSupport(IRenameElementContext context, String name) {
		return renameSupportFactory.create(context, newName);
	}

	protected void restoreOriginalSelection() {
		ISelection originalSelection = renameElementContext.getTriggeringEditorSelection();
		if (originalSelection instanceof ITextSelection) {
			getXtextEditor().getInternalSourceViewer().setSelectedRange(
					((ITextSelection) originalSelection).getOffset(), ((ITextSelection) originalSelection).getLength());
		}
	}

	protected XtextEditor getXtextEditor() {
		IEditorPart part = renameElementContext.getTriggeringEditor();
		if (part instanceof XtextEditor) {
			return (XtextEditor) part;
		}
		return null;
	}
}
