/*******************************************************************************
 * Copyright (c) 2011 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.xtend2.tests.typing;

import java.util.Iterator;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.common.types.JvmIdentifiableElement;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.eclipse.xtext.util.StringInputStream;
import org.eclipse.xtext.xbase.XExpression;
import org.eclipse.xtext.xbase.typing.ITypeProvider;
import org.eclipse.xtext.xtend2.tests.AbstractXtend2TestCase;
import org.eclipse.xtext.xtend2.xtend2.XtendFile;
import org.eclipse.xtext.xtend2.xtend2.XtendFunction;

import com.google.inject.Inject;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
public class TypeProviderErrorTest extends AbstractXtend2TestCase {

	@Inject
	private ITypeProvider typeProvider;
	
	public void testNoException_01() throws Exception {
		XtendFunction function = function(
				"richStrings_04() {\n" + 
				"	'''�FORa:'1'.toCharArray��FOR a:'1'.toCharArray�foobar�ENDFOR��ENDFOR�'''\n" + 
				"}\n");
		Iterator<Object> contents = EcoreUtil.getAllContents(function.eResource(), true);
		while(contents.hasNext()) {
			EObject object = (EObject) contents.next();
			if (object instanceof JvmIdentifiableElement) {
				// no ecxeption
				typeProvider.getTypeForIdentifiable((JvmIdentifiableElement) object);
			}
		}
		
	}
	
	public void testNoException_02() throws Exception {
		XtendFile file = file("package org.eclipse.xtext.xtend2.tests.typing\n" + 
				"import org.eclipse.emf.ecore.EClass\n" + 
				"class NoException {\n" + 
				"	dispatch transform(EClass model) {\n" + 
				"		model.ETypeParameters.map(e|transform");
		Iterator<Object> contents = EcoreUtil.getAllContents(file.eResource(), true);
		while(contents.hasNext()) {
			EObject object = (EObject) contents.next();
			if (object instanceof XExpression) {
				XExpression expression = (XExpression) object;
				typeProvider.getExpectedType(expression);
			}
		}
	}
	
	@Override
	protected XtendFile file(String string, boolean validate) throws Exception {
		if (validate)
			return super.file(string, validate);
		XtextResourceSet set = get(XtextResourceSet.class);
		String fileName = getFileName(string);
		Resource resource = set.createResource(URI.createURI(fileName + ".xtend"));
		resource.load(new StringInputStream(string), null);
		XtendFile file = (XtendFile) resource.getContents().get(0);
		return file;
	}
	
}
