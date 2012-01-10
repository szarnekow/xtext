/*******************************************************************************
 * Copyright (c) 2011 Sigasi N.V. (http://www.sigasi.com) and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse public static  License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package org.eclipse.xtext.nodemodel.impl;

import java.util.Arrays;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.SyntaxErrorMessage;
import org.eclipse.xtext.util.Strings;

/**
 * ONLY meant for testing. This is a bit of support to shallowly compare INodes. Especially for nodes containing
 * semantic objects, this is just a sanity check and may give false positives.
 * 
 * @author mark.christiaens - Initial contribution and API
 * 
 * @since 2.3
 */
public class NodeModelEqualityHelper {
	public static boolean isEqual(INode left, INode right, Map<EObject, EObject> correspondanceMap) {
		if (left == right) {
			return true;
		}

		if (left == null) {
			if (right == null) {
				return true;
			}

			return false;
		}

		if (right == null) {
			return false;
		}

		final Class<? extends INode> leftClass = left.getClass();
		final Class<? extends INode> rightClass = right.getClass();

		if (!leftClass.equals(rightClass)) {
			return false;
		}

		if (leftClass.equals(AbstractNode.class)) {
			throw new AssertionError("Instance of abstract class is impossible");
		}

		if (leftClass.equals(CompositeNode.class)) {
			CompositeNode leftActual = (CompositeNode) left;
			CompositeNode rightActual = (CompositeNode) right;
			return equalsCompositeNode(leftActual, rightActual, correspondanceMap);
		}

		if (leftClass.equals(CompositeNodeWithSemanticElement.class)) {
			CompositeNodeWithSemanticElement leftActual = (CompositeNodeWithSemanticElement) left;
			CompositeNodeWithSemanticElement rightActual = (CompositeNodeWithSemanticElement) right;
			return equalsCompositeNodeWithSemanticElement(leftActual, rightActual, correspondanceMap);
		}

		if (leftClass.equals(CompositeNodeWithSyntaxError.class)) {
			CompositeNodeWithSyntaxError leftActual = (CompositeNodeWithSyntaxError) left;
			CompositeNodeWithSyntaxError rightActual = (CompositeNodeWithSyntaxError) right;
			return equalsCompositeNodeWithSyntaxError(leftActual, rightActual, correspondanceMap);
		}

		if (leftClass.equals(CompositeNodeWithSemanticElementAndSyntaxError.class)) {
			CompositeNodeWithSemanticElementAndSyntaxError leftActual = (CompositeNodeWithSemanticElementAndSyntaxError) left;
			CompositeNodeWithSemanticElementAndSyntaxError rightActual = (CompositeNodeWithSemanticElementAndSyntaxError) right;
			return equalsCompositeNodeWithSemanticElementAndSyntaxError(leftActual, rightActual, correspondanceMap);
		}

		if (leftClass.equals(RootNode.class)) {
			RootNode leftActual = (RootNode) left;
			RootNode rightActual = (RootNode) right;
			return equalsRootNode(leftActual, rightActual, correspondanceMap);
		}

		if (leftClass.equals(SyntheticCompositeNode.class)) {
			throw new AssertionError("Found a SyntheticCompositeNode amongst the actual INodes");
		}

		if (leftClass.equals(LeafNode.class)) {
			LeafNode leftActual = (LeafNode) left;
			LeafNode rightActual = (LeafNode) right;
			return equalsLeafNode(leftActual, rightActual, correspondanceMap);
		}

		if (leftClass.equals(HiddenLeafNode.class)) {
			HiddenLeafNode leftActual = (HiddenLeafNode) left;
			HiddenLeafNode rightActual = (HiddenLeafNode) right;
			return equalsHiddenLeafNode(leftActual, rightActual, correspondanceMap);
		}

		if (leftClass.equals(LeafNodeWithSyntaxError.class)) {
			LeafNodeWithSyntaxError leftActual = (LeafNodeWithSyntaxError) left;
			LeafNodeWithSyntaxError rightActual = (LeafNodeWithSyntaxError) right;
			return equalsLeafNodeWithSyntaxError(leftActual, rightActual, correspondanceMap);
		}

		if (leftClass.equals(HiddenLeafNodeWithSyntaxError.class)) {
			HiddenLeafNodeWithSyntaxError leftActual = (HiddenLeafNodeWithSyntaxError) left;
			HiddenLeafNodeWithSyntaxError rightActual = (HiddenLeafNodeWithSyntaxError) right;
			return equalsHiddenLeafNodeWithSyntaxError(leftActual, rightActual, correspondanceMap);
		}

		throw new AssertionError("Got an INode of an unknown type: " + leftClass.getName());
	}

	private static  boolean equalsHiddenLeafNodeWithSyntaxError(HiddenLeafNodeWithSyntaxError leftActual,
			HiddenLeafNodeWithSyntaxError rightActual, Map<EObject, EObject> correspondanceMap) {
		if (!equalsHiddenLeafNode(leftActual, rightActual, correspondanceMap)) {
			return false; 
		}
		
		return equalsSyntaxErrorMessage(leftActual.getSyntaxErrorMessage(), rightActual.getSyntaxErrorMessage()); 
	}

	private static  boolean equalsLeafNodeWithSyntaxError(LeafNodeWithSyntaxError leftActual,
			LeafNodeWithSyntaxError rightActual, Map<EObject, EObject> correspondanceMap) {
		if (!equalsLeafNode (leftActual, rightActual, correspondanceMap)) {
			return false;
		}
		
		return equalsSyntaxErrorMessage(leftActual.getSyntaxErrorMessage(), rightActual.getSyntaxErrorMessage()); 
	}

	private static  boolean equalsHiddenLeafNode(HiddenLeafNode leftActual, HiddenLeafNode rightActual, Map<EObject, EObject> correspondanceMap) {
		return equalsLeafNode(leftActual, rightActual, correspondanceMap); 
	}

	private static  boolean equalsLeafNode(LeafNode leftActual, LeafNode rightActual, Map<EObject, EObject> correspondanceMap) {
		if (!equalsAbstractNode(leftActual, rightActual, correspondanceMap)) {
			return false;
		}

		return leftActual.getTotalLength() == rightActual.getTotalLength()
				&& leftActual.getTotalOffset() == rightActual.getTotalOffset();
	}

	private static  boolean equalsRootNode(RootNode leftActual, RootNode rightActual, Map<EObject, EObject> correspondanceMap) {
		if (!equalsCompositeNodeWithSemanticElementAndSyntaxError(leftActual, rightActual, correspondanceMap)) {
			return false;
		}

		if (!Strings.equal(leftActual.getCompleteContent(), rightActual.getCompleteContent())) {
			return false;
		}

		return Arrays.equals(leftActual.basicGetLineBreakOffsets(), rightActual.basicGetLineBreakOffsets());
	}

	private static  boolean equalsCompositeNodeWithSemanticElementAndSyntaxError(
			CompositeNodeWithSemanticElementAndSyntaxError leftActual,
			CompositeNodeWithSemanticElementAndSyntaxError rightActual, Map<EObject, EObject> correspondanceMap) {
		if (!equalsCompositeNodeWithSemanticElement(leftActual, rightActual, correspondanceMap)) {
			return false;
		}

		return equalsSyntaxErrorMessage(leftActual.getSyntaxErrorMessage(), rightActual.getSyntaxErrorMessage());
	}

	private static  boolean equalsCompositeNodeWithSyntaxError(CompositeNodeWithSyntaxError leftActual,
			CompositeNodeWithSyntaxError rightActual, Map<EObject, EObject> correspondanceMap) {
		if (!equalsCompositeNode(leftActual, rightActual, correspondanceMap)) {
			return false;
		}

		return equalsSyntaxErrorMessage(leftActual.getSyntaxErrorMessage(), rightActual.getSyntaxErrorMessage());
	}

	private static  boolean equalsSyntaxErrorMessage(SyntaxErrorMessage left, SyntaxErrorMessage right) {
		if (left == null) {
			if (right == null) {
				return true;
			}
			return false;
		} else {
			if (right == null) {
				return false;
			}

			return left.equals(right);
		}
	}

	private static  boolean equalsCompositeNodeWithSemanticElement(CompositeNodeWithSemanticElement leftActual,
			CompositeNodeWithSemanticElement rightActual, Map<EObject, EObject> correspondanceMap) {
		if (!equalsCompositeNode(leftActual, rightActual, correspondanceMap)) {
			return false;
		}

		return equalsSemanticElement(leftActual.basicGetSemanticElement(), rightActual.basicGetSemanticElement(), correspondanceMap);
	}

	/* Just have a sanity check that we are dealing with objects of the same class. */
	private static  boolean equalsSemanticElement(EObject left, EObject right, Map<EObject, EObject> correspondanceMap) {
		if (left == null) {
			if (right == null) {
				return true;
			}
			return false;
		} else {
			if (right == null) {
				return false;
			}

			return correspondanceMap.get(left) == right; 
		}
	}

	private static  boolean equalsCompositeNode(CompositeNode leftActual, CompositeNode rightActual, Map<EObject, EObject> correspondanceMap) {
		if (!equalsAbstractNode(leftActual, rightActual, correspondanceMap)) {
			return false;
		}

		return leftActual.getLookAhead() == rightActual.getLookAhead();
	}

	private static  boolean equalsAbstractNode(AbstractNode left, AbstractNode right, Map<EObject, EObject> correspondanceMap) {
		if (left.basicGetGrammarElement() == right.basicGetGrammarElement()) {
			return true;
		}

		if (left.basicGetGrammarElement() instanceof EObject) {
			EObject leftObject = (EObject) left.basicGetGrammarElement();
			if (right.basicGetGrammarElement() instanceof EObject) {
				EObject rightObject = (EObject) right.basicGetGrammarElement();
				return leftObject == rightObject;
			}

			return false;
		}

		if (left.basicGetGrammarElement() instanceof EObject[]) {
			EObject[] leftArray = (EObject[]) left.basicGetGrammarElement();
			if (right.basicGetGrammarElement() instanceof EObject[]) {
				EObject[] rightArray = (EObject[]) right.basicGetGrammarElement();

				return Arrays.equals(leftArray, rightArray);
			}

			return false;
		}

		return false;
	}
}
