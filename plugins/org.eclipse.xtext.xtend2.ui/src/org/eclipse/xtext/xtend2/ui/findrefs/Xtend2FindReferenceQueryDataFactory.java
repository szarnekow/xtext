/*******************************************************************************
 * Copyright (c) 2011 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.xtend2.ui.findrefs;

import static com.google.common.collect.Sets.*;
import static java.util.Collections.*;
import static org.eclipse.emf.ecore.util.EcoreUtil.*;

import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.resource.IReferenceDescription;
import org.eclipse.xtext.ui.editor.findrefs.FindReferenceQueryDataFactory;
import org.eclipse.xtext.xtend2.linking.IXtend2JvmAssociations;
import org.eclipse.xtext.xtend2.xtend2.XtendClass;
import org.eclipse.xtext.xtend2.xtend2.XtendFunction;

import com.google.common.base.Predicate;
import com.google.inject.Inject;

/**
 * @author Jan Koehnlein - Initial contribution and API
 */
public class Xtend2FindReferenceQueryDataFactory extends FindReferenceQueryDataFactory {

	@Inject
	private IXtend2JvmAssociations xtend2jvmAssociations;

	@Override
	protected Predicate<IReferenceDescription> createResultFilter(EObject target) {
		return new Xtend2ReferenceFilter(getURI(target));
	}

	@Override
	protected Set<URI> createTargetURIs(EObject target) {
		if (target instanceof XtendClass) {
			Set<URI> targetURIs = newLinkedHashSet();
			targetURIs.add(getURI(target));
			targetURIs.add(getURI(xtend2jvmAssociations.getInferredType((XtendClass) target)));
			targetURIs.add(getURI(xtend2jvmAssociations.getInferredConstructor((XtendClass) target)));
			return targetURIs;
		} else if (target instanceof XtendFunction) {
			Set<URI> targetURIs = newLinkedHashSet();
			targetURIs.add(getURI(target));
			targetURIs.add(getURI(xtend2jvmAssociations.getDirectlyInferredOperation((XtendFunction) target)));
			return targetURIs;
		} else {
			return singleton(getURI(target));
		}
	}
}