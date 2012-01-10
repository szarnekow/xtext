/*******************************************************************************
 * Copyright (c) 2012 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.generator;

import java.io.CharArrayWriter;

import org.eclipse.emf.common.util.URI;
import org.eclipse.xtext.formatting.IWhitespaceInformationProvider;

import com.google.inject.Inject;

/**
 * Replaces all line breaks with the configured line separator.
 * 
 * @author Jan Koehnlein - Initial contribution and API
 * @since 2.3
 */
public class LineSeparatorHarmonizer implements IFilePostProcessor {

	@Inject
	private IWhitespaceInformationProvider whitespaceInformationProvider;

	public String postProcess(URI fileURI, CharSequence content) {
		String lineSeparator = whitespaceInformationProvider.getLineSeparatorInformation(fileURI).getLineSeparator();
		CharArrayWriter writer = new CharArrayWriter(content.length());
		boolean isLookahead = false;
		char ignoreNext = '\u0000';
		for(int i=0; i<content.length(); ++i) {
			char c = content.charAt(i);
			if (isLookahead) {
				isLookahead = false;
				if (c == ignoreNext)
					continue;
			}
			switch (c) {
				case '\n':
					writer.append(lineSeparator);
					isLookahead = true;
					ignoreNext = '\r';
					break;
				case '\r':
					writer.append(lineSeparator);
					isLookahead = true;
					ignoreNext = '\n';
					break;
				default:
					writer.append(c);
			}
		}
		return new String(writer.toCharArray());
	}
}
