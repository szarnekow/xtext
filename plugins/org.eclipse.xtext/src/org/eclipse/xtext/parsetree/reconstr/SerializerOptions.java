/*******************************************************************************
 * Copyright (c) 2010 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.parsetree.reconstr;

import org.eclipse.xtext.resource.SaveOptions;

/**
 * @deprecated use the immutable {@link SaveOptions} instead.
 */
@Deprecated
public class SerializerOptions {

	private boolean formatting = true;
	private boolean validateConcreteSyntax = true;

	public SerializerOptions() {
	}

	public SerializerOptions(boolean format, boolean validateConcreteSyntax) {
		super();
		this.formatting = format;
		this.validateConcreteSyntax = validateConcreteSyntax;
	}

	public boolean isFormatting() {
		return formatting;
	}

	public SerializerOptions setFormatting(boolean formatting) {
		this.formatting = formatting;
		return this;
	}

	public boolean isValidateConcreteSyntax() {
		return validateConcreteSyntax;
	}

	public SerializerOptions setValidateConcreteSyntax(boolean validateConcreteSyntax) {
		this.validateConcreteSyntax = validateConcreteSyntax;
		return this;
	}
	
	public SaveOptions toSaveOptions() {
		SaveOptions.Builder builder = SaveOptions.newBuilder();
		if (!isValidateConcreteSyntax())
			builder.noValidation();
		if (isFormatting())
			builder.format();
		return builder.getOptions();
	}
}