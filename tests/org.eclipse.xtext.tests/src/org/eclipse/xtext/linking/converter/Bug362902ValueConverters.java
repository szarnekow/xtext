/*******************************************************************************
 * Copyright (c) 2012 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.linking.converter;

import org.eclipse.xtext.common.services.DefaultTerminalConverters;
import org.eclipse.xtext.conversion.IValueConverter;
import org.eclipse.xtext.conversion.ValueConverter;
import org.eclipse.xtext.conversion.ValueConverterException;
import org.eclipse.xtext.nodemodel.INode;

/**
 * @author Holger Schill - Initial contribution and API
 */
public class Bug362902ValueConverters extends DefaultTerminalConverters {
	@Override
	@ValueConverter(rule = "MyId")
	public IValueConverter<String> ID() {
		return new IValueConverter<String>() {

			public String toValue(String string, INode node) throws ValueConverterException {
				if (string != null && string.length() > 3) {
					throw new ValueConverterException("ID too long", node, null);
				}
				return string;
			}

			public String toString(String value) throws ValueConverterException {
				return value;
			}
		};
	}
}
