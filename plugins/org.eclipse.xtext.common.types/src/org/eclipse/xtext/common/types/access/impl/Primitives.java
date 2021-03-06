/*******************************************************************************
 * Copyright (c) 2009 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.common.types.access.impl;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
public class Primitives {

	public static Class<?> forName(String name) {
		for(Class<?> candidate: ALL_PRIMITIVE_TYPES) {
			if (name.equals(candidate.getName()))
				return candidate;
		}
		return null;
	}
	
	public static Class<?>[] ALL_PRIMITIVE_TYPES = {
		Boolean.TYPE,
		Integer.TYPE,
		Float.TYPE,
		Byte.TYPE,
		Character.TYPE,
		Double.TYPE,
		Short.TYPE,
		Long.TYPE,
		Void.TYPE
	};
	
}
