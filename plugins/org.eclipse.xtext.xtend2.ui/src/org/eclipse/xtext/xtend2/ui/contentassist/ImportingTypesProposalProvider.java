/*******************************************************************************
 * Copyright (c) 2011 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.xtend2.ui.contentassist;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.ITextViewerExtension;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.xtext.common.types.xtext.ui.JdtTypesProposalProvider;
import org.eclipse.xtext.naming.IQualifiedNameConverter;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.ui.editor.contentassist.ConfigurableCompletionProposal;
import org.eclipse.xtext.ui.editor.contentassist.ConfigurableCompletionProposal.IReplacementTextApplier;
import org.eclipse.xtext.ui.editor.contentassist.ContentAssistContext;
import org.eclipse.xtext.xtend2.xtend2.XtendClass;
import org.eclipse.xtext.xtend2.xtend2.XtendFile;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
public class ImportingTypesProposalProvider extends JdtTypesProposalProvider {

	@Override
	protected IReplacementTextApplier createTextApplier(ContentAssistContext context, IScope typeScope, IQualifiedNameConverter qualifiedNameConverter) {
		return new FQNImporter(context.getResource(), context.getViewer(), typeScope, qualifiedNameConverter);
	}
	
	public static class FQNImporter extends FQNShortener {
		
		private final ITextViewer viewer;

		public FQNImporter(Resource context, ITextViewer viewer, IScope scope, IQualifiedNameConverter qualifiedNameConverter) {
			super(context, scope, qualifiedNameConverter);
			this.viewer = viewer;
		}

		@Override
		public void apply(IDocument document, ConfigurableCompletionProposal proposal) throws BadLocationException {
			String proposalReplacementString = proposal.getReplacementString();
			String replacementString = getActualReplacementString(proposal);
			// there is an import statement - apply computed replacementString
			if (!proposalReplacementString.equals(replacementString)) {
				proposal.setCursorPosition(replacementString.length());
				document.replace(proposal.getReplacementOffset(), proposal.getReplacementLength(), replacementString);
			} else {
				// we could create an import statement if there is no conflict
				XtendFile file = (XtendFile) context.getContents().get(0);
				XtendClass clazz = file.getXtendClass();
				
				QualifiedName qualifiedName = qualifiedNameConverter.toQualifiedName(proposalReplacementString);
				if (qualifiedName.getSegmentCount() == 1) {
					// type resides in default package - no need to hassle with imports
					proposal.setCursorPosition(proposalReplacementString.length());
					document.replace(proposal.getReplacementOffset(), proposal.getReplacementLength(), proposalReplacementString);
					return;
				}
				
				IEObjectDescription description = scope.getSingleElement(qualifiedName.skipFirst(qualifiedName.getSegmentCount() - 1));
				if (description != null) {
					// there exists a conflict - insert fully qualified name
					proposal.setCursorPosition(proposalReplacementString.length());
					document.replace(proposal.getReplacementOffset(), proposal.getReplacementLength(), proposalReplacementString);
					return;
				}
				
				// Import does not introduce ambiguities - add import and insert short name
				String shortName = qualifiedName.getLastSegment();
				int topPixel = -1;
				// store the pixel coordinates to prevent the ui from flickering
				StyledText widget = viewer.getTextWidget();
				if (widget != null)
					topPixel = widget.getTopPixel();
				ITextViewerExtension viewerExtension = null;
				if (viewer instanceof ITextViewerExtension) {
					viewerExtension = (ITextViewerExtension) viewer;
					viewerExtension.setRedraw(false);
				}
				try {
					// compute import statement's offset
					int offset = 0;
					boolean startWithLineBreak = true;
					boolean endWithLineBreak = false;
					if (file.getImports().isEmpty()) {
						startWithLineBreak = false;
						if (clazz == null) {
							offset = document.getLength();
						} else {
							ICompositeNode node = NodeModelUtils.getNode(clazz);
							offset = node.getOffset();
							endWithLineBreak = true;
						}
					} else {
						ICompositeNode node = NodeModelUtils.getNode(file.getImports().get(file.getImports().size() - 1));
						offset = node.getOffset() + node.getLength();
					}
					offset = Math.min(proposal.getReplacementOffset(), offset);
				
					// apply short proposal
					proposal.setCursorPosition(shortName.length());
					document.replace(proposal.getReplacementOffset(), proposal.getReplacementLength(), shortName);
				
					// add import statement
					String importStatement = (startWithLineBreak ? "\nimport " : "import ") + proposalReplacementString;
					if (endWithLineBreak)
						importStatement += "\n\n";
					document.replace(offset, 0, importStatement.toString());
					proposal.setCursorPosition(proposal.getCursorPosition() + importStatement.length());
					
					// set the pixel coordinates
					if (widget != null) {
						int additionalTopPixel = 0;
						if (startWithLineBreak)
							additionalTopPixel += widget.getLineHeight();
						if (endWithLineBreak)
							additionalTopPixel += 2 * widget.getLineHeight();
						widget.setTopPixel(topPixel + additionalTopPixel);
					}
				} finally {
					if (viewerExtension != null)
						viewerExtension.setRedraw(true);
				}
				
			}
		}
		
	}

}