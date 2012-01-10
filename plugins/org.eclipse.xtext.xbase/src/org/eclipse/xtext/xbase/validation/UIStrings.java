/*******************************************************************************
 * Copyright (c) 2011 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.xbase.validation;

import static com.google.common.collect.Iterables.*;

import java.util.List;

import org.eclipse.xtext.common.types.JvmAnyTypeReference;
import org.eclipse.xtext.common.types.JvmExecutable;
import org.eclipse.xtext.common.types.JvmFormalParameter;
import org.eclipse.xtext.common.types.JvmIdentifiableElement;
import org.eclipse.xtext.common.types.JvmOperation;
import org.eclipse.xtext.common.types.JvmTypeParameter;
import org.eclipse.xtext.common.types.JvmTypeParameterDeclarator;
import org.eclipse.xtext.common.types.JvmTypeReference;
import org.eclipse.xtext.xbase.XAbstractFeatureCall;
import org.eclipse.xtext.xbase.XConstructorCall;
import org.eclipse.xtext.xbase.XExpression;
import org.eclipse.xtext.xbase.impl.FeatureCallToJavaMapping;
import org.eclipse.xtext.xbase.typing.ITypeProvider;

import com.google.common.base.Function;
import com.google.inject.Inject;

/**
 * UI presentable string representation of Xbase elements.
 * 
 * @author Jan Koehnlein - Initial contribution and API
 */
public class UIStrings {

	@Inject
	private ITypeProvider typeProvider;
	
	@Inject
	private FeatureCallToJavaMapping featureCallToJavaMapping;
	
	public String signature(JvmExecutable executable) {
		StringBuilder b = new StringBuilder(executable.getSimpleName());
		b.append(parameters(executable));
		if(executable instanceof JvmOperation) 
			b.append(" : ").append(((JvmOperation) executable).getReturnType().getSimpleName());
		return b.toString();
	}
	
	public String parameters(JvmIdentifiableElement element) {
		if (element instanceof JvmExecutable) {
			return "(" + parameterTypes(((JvmExecutable)element).getParameters()) + ")";
		}
		return "";
	}

	public String arguments(XAbstractFeatureCall featureCall) {
		List<XExpression> arguments = featureCallToJavaMapping.getActualArguments(featureCall);
		return "(" + expressionTypes(arguments) + ")";
	}

	public String arguments(XConstructorCall constructorCall) {
		return "(" + expressionTypes(constructorCall.getArguments()) + ")";
	}

	public String typeParameters(Iterable<? extends JvmTypeParameter> typeParams) {
		if (!isEmpty(typeParams)) {
			return "<" + toString(typeParams) + ">";
		}
		return "";
	}
	
	public String typeParameters(JvmIdentifiableElement element) {
		if (element instanceof JvmTypeParameterDeclarator) {
			return "<" + toString(((JvmTypeParameterDeclarator) element).getTypeParameters()) + ">";
		}
		return "";
	}
	
	public String typeArguments(XAbstractFeatureCall featureCall) {
		return "<" + referencesToString(featureCall.getTypeArguments()) + ">";
	}

	public String typeArguments(XConstructorCall constructorCall) {
		return "<" + referencesToString(constructorCall.getTypeArguments()) + ">";
	}

	protected String toString(Iterable<? extends JvmIdentifiableElement> elements) {
		StringBuilder buffer = new StringBuilder();
		boolean needsSeparator = false;
		for (JvmIdentifiableElement type : elements) {
			if (needsSeparator)
				buffer.append(", ");
			needsSeparator = true;
			if(type != null) {
				buffer.append(type.getSimpleName());
			} else 
				buffer.append("[null]");
		}
		return buffer.toString();
	}
	
	protected String referencesToString(Iterable<? extends JvmTypeReference> elements) {
		StringBuilder buffer = new StringBuilder();
		boolean needsSeparator = false;
		for (JvmTypeReference typeRef : elements) {
			if (needsSeparator)
				buffer.append(", ");
			needsSeparator = true;
			if(typeRef != null) {
				if (typeRef instanceof JvmAnyTypeReference)
					buffer.append("Object");
				else
					buffer.append(typeRef.getSimpleName());
			} else 
				buffer.append("[null]");
		}
		return buffer.toString();
	}

	protected String expressionTypes(Iterable<XExpression> expressions) {
		return referencesToString(transform(expressions, new Function<XExpression, JvmTypeReference>() {
			public JvmTypeReference apply(XExpression from) {
				return typeProvider.getType(from);
			}
		}));
	}

	protected String parameterTypes(Iterable<JvmFormalParameter> parameters) {
		return referencesToString(transform(parameters, new Function<JvmFormalParameter, JvmTypeReference>() {
			public JvmTypeReference apply(JvmFormalParameter from) {
				return from.getParameterType();
			}
		}));
	}
	
}
