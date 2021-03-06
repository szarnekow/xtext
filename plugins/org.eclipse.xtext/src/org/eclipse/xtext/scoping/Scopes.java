/*******************************************************************************
 * Copyright (c) 2009 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *******************************************************************************/
package org.eclipse.xtext.scoping;

import java.util.ArrayList;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.EObjectDescription;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.scoping.impl.SimpleScope;
import org.eclipse.xtext.util.SimpleAttributeResolver;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;

/**
 * This class contains static utility functions to create and work on {@link IScope} and {@link IScopedElement}
 * 
 * @author Sven Efftinge - Initial contribution and API
 * @author Jan Koehnlein - introduced QualifiedName
 */
public class Scopes {

	public static Iterable<IEObjectDescription> selectCompatible(Iterable<IEObjectDescription> exportedObjects, final EClass clazz) {
		return Iterables.filter(exportedObjects, new Predicate<IEObjectDescription>() {
			public boolean apply(IEObjectDescription input) {
				return EcoreUtil2.isAssignableFrom(clazz,input.getEClass());
			}
		});
	}

	/**
	 * creates a scope using {@link SimpleAttributeResolver#NAME_RESOLVER} to compute the names and sets
	 * {@link IScope#NULLSCOPE} as parent scope
	 */
	public static IScope scopeFor(Iterable<? extends EObject> elements) {
		return scopeFor(elements, IScope.NULLSCOPE);
	}

	/**
	 * creates a scope using {@link SimpleAttributeResolver#NAME_RESOLVER} to compute the names
	 */
	public static IScope scopeFor(Iterable<? extends EObject> elements, IScope outer) {
		return scopeFor(elements, QualifiedName.wrapper(SimpleAttributeResolver.NAME_RESOLVER), outer);
	}

	/**
	 * creates a scope using the passed function to compute the names and sets the passed scope as the parent scope
	 */
	public static <T extends EObject> IScope scopeFor(Iterable<? extends T> elements,
			final Function<T, QualifiedName> nameComputation, IScope outer) {
		return new SimpleScope(outer,scopedElementsFor(elements, nameComputation));
	}

	/**
	 * transforms an {@link Iterable} of {@link EObject}s into an {@link Iterable} of {@link IScopedElement}s computing
	 * the {@link EAttribute} 'name' to compute the {@link IScopedElement}'s name. If not existent the object is
	 * filtered out.
	 */
	public static Iterable<IEObjectDescription> scopedElementsFor(Iterable<? extends EObject> elements) {
		return scopedElementsFor(elements, QualifiedName.wrapper(SimpleAttributeResolver.NAME_RESOLVER));
	}

	/**
	 * transforms an {@link Iterable} of {@link EObject}s into an {@link Iterable} of {@link IScopedElement}s computing
	 * the name of the elements using the passed {@link Function} If the passed function returns null the object is
	 * filtered out.
	 */
	public static <T extends EObject> Iterable<IEObjectDescription> scopedElementsFor(Iterable<? extends T> elements,
			final Function<T, QualifiedName> nameComputation) {
		Iterable<IEObjectDescription> transformed = Iterables.transform(elements,
				new Function<T, IEObjectDescription>() {
					public IEObjectDescription apply(T from) {
						final QualifiedName qualifiedName = nameComputation.apply(from);
						if (qualifiedName != null)
							return new EObjectDescription(qualifiedName, from, null);
						return null;
					}
				});
		return Iterables.filter(transformed, Predicates.notNull());
	}
	
	/**
	 * indexes the IEObject description using the given
	 */
	public static <T> Multimap<T,IEObjectDescription> index(Iterable<IEObjectDescription> descriptions, Function<IEObjectDescription,T> indexer) {
		ArrayList<IEObjectDescription> list = Lists.newArrayList(descriptions);
		LinkedHashMultimap<T, IEObjectDescription> multimap = LinkedHashMultimap.create(list.size(),1);
		for (IEObjectDescription desc : list) {
			multimap.put(indexer.apply(desc), desc);
		}
		return multimap;
	}
	
	/**
	 * indexes the IEObject description using the given
	 */
	public static Multimap<QualifiedName,IEObjectDescription> index(Iterable<IEObjectDescription> descriptions) {
		return index(descriptions, new Function<IEObjectDescription, QualifiedName>() {
			public QualifiedName apply(IEObjectDescription from) {
				return from.getName().toLowerCase();
			}
		});
	}

	public static Iterable<IEObjectDescription> filterDuplicates(Iterable<IEObjectDescription> filtered) {
		Map<QualifiedName, IEObjectDescription> result = Maps.newLinkedHashMap();
		for (IEObjectDescription e : filtered) {
			QualifiedName qualifiedName = e.getName();
			if (result.containsKey(qualifiedName)) {
				result.put(qualifiedName, null);
			} else {
				result.put(qualifiedName, e);
			}
		}
		return Iterables.filter(result.values(), Predicates.notNull());
	}

}
