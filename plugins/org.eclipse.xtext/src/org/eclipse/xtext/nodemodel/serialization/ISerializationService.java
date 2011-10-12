/*******************************************************************************
 * Copyright (c) 2011 Sigasi N.V. (http://www.sigasi.com) and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package org.eclipse.xtext.nodemodel.serialization;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.eclipse.emf.common.util.URI;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextResourceSet;

import com.google.inject.ImplementedBy;

/**
 * @author Mark Christiaens - Initial contribution
 * 
 * @since 2.1
 */

@ImplementedBy(DefaultSerializationService.class)
public interface ISerializationService {
	public abstract void write(XtextResource resource, OutputStream emfOut, OutputStream nodeModelOut)
			throws IOException;

	public abstract XtextResource getResource(XtextResourceSet resourceSet, URI uri, InputStream emfIn,
			InputStream nodeModelIn) throws IOException;
}