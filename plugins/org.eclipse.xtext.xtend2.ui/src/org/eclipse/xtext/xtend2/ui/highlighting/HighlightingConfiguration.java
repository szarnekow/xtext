/*******************************************************************************
 * Copyright (c) 2011 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.xtend2.ui.highlighting;

import org.eclipse.swt.graphics.RGB;
import org.eclipse.xtext.ui.editor.syntaxcoloring.DefaultHighlightingConfiguration;
import org.eclipse.xtext.ui.editor.syntaxcoloring.IHighlightingConfigurationAcceptor;
import org.eclipse.xtext.ui.editor.utils.TextStyle;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
public class HighlightingConfiguration extends DefaultHighlightingConfiguration {

	public static final String RICH_TEXT_ID = "xtend2.richText";
	public static final String RICH_TEXT_DELIMITER_ID = "xtend2.richText.delimiter";
	public static final String INSIGNIFICANT_TEMPLATE_TEXT = "xtend2.insignificant.template.text";
	
	@Override
	public void configure(IHighlightingConfigurationAcceptor acceptor) {
		super.configure(acceptor);
		acceptor.acceptDefaultHighlighting(RICH_TEXT_ID, "Template Text", richTextStyle());
		acceptor.acceptDefaultHighlighting(RICH_TEXT_DELIMITER_ID, "Template Delimiter", richTextDelimiterStyle());
		acceptor.acceptDefaultHighlighting(INSIGNIFICANT_TEMPLATE_TEXT, "Insignificant Template Text", insignificantTemplateTextStyle());
	}
	
	public TextStyle richTextStyle() {
		TextStyle textStyle = defaultTextStyle().copy();
		textStyle.setColor(new RGB(0, 128, 0));
		return textStyle;
	}
	
	public TextStyle richTextDelimiterStyle() {
		TextStyle textStyle = defaultTextStyle().copy();
		return textStyle;
	}
	
	public TextStyle insignificantTemplateTextStyle() {
		TextStyle textStyle = defaultTextStyle().copy();
		textStyle.setBackgroundColor(new RGB(255, 255, 220));
		return textStyle;
	}
}