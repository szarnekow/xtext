/*******************************************************************************
 * Copyright (c) 2011 Sigasi N.V. (http://www.sigasi.com) and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package org.eclipse.xtext.nodemodel.serialization;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.Date;
import java.util.Dictionary;

import javax.swing.plaf.metal.MetalBorders.MenuItemBorder;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.BinaryResourceImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;
import org.eclipse.xtext.IGrammarAccess;
import org.eclipse.xtext.nodemodel.impl.InvariantChecker;
import org.eclipse.xtext.parser.ParseResult;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.osgi.framework.Bundle;

import com.google.common.collect.ImmutableMap;
import com.google.inject.Inject;
import com.google.inject.Provider;

/** @author Mark Christiaens - Initial contribution */
public class DefaultSerializationService implements ISerializationService {
	private static final Logger LOGGER = Logger.getLogger(DefaultSerializationService.class);

	public XtextResource getResource(XtextResourceSet resourceSet, URI uri, InputStream emfIn, InputStream nodeModelIn)
			throws IOException {
		XtextResource xr = getResource(resourceSet, uri, emfIn);
		augmentWithNodeModel(nodeModelIn, xr);

		return xr;
	}

	protected XtextResource getResource(XtextResourceSet resourceSet, URI uri, InputStream emfIn) throws IOException {
		Resource carrier = deserializeEMFModel(emfIn);

		XtextResource xr = (XtextResource) resourceSet.getResource(uri, false);
		if (xr == null) {
			xr = (XtextResource) resourceSet.createResource(uri);
		}

		moveContentToResource(carrier, xr);

		fixupProxies(xr);

		return xr;
	}

	private void fixupProxies(XtextResource xr) {
		URI uri = xr.getURI();
		TreeIterator<EObject> allContents = xr.getAllContents();
		while (allContents.hasNext()) {
			EObject object = allContents.next();
			EClass c = object.eClass();
			EList<EReference> references = c.getEAllReferences();
			for (EReference reference : references) {
				if (!reference.isContainment()) {
					if (reference.isMany()) {
						InternalEList<EObject> targets = (InternalEList<EObject>) object.eGet(reference, false);
						for (int i = 0; i < targets.size(); ++i) {
							EObject target = targets.basicGet(i);
							fixupProxy(target, uri);
						}
					} else {
						EObject target = (EObject) object.eGet(reference, false);
						fixupProxy(target, uri);
					}
				}
			}
		}
	}

	private void fixupProxy(EObject target, URI newResourceURI) {
		if (target != null) {
			if (target.eIsProxy()) {
				InternalEObject internal = (InternalEObject) target;
				URI proxyURI = internal.eProxyURI();
				URI newProxyURI = newResourceURI.appendFragment(proxyURI.fragment());
				internal.eSetProxyURI(newProxyURI);
				//				System.out.println(proxyURI + " -> " + newProxyURI);
			}
		}
	}

	protected void augmentWithNodeModel(InputStream nodeModelIn, XtextResource xr) throws IOException,
			UnsupportedEncodingException {
		if (nodeModelIn == null) {
			return;
		}

		DeserializationConversionContext deserContext = new DeserializationConversionContext(xr);

		SerializableNodeModel nodeModel = new SerializableNodeModel();
		nodeModel.readObjectData(new DataInputStream(new BufferedInputStream(nodeModelIn)), deserContext);

		assert invariantsOK(nodeModel);

		xr.setParseResult(new ParseResult(getRoot(xr), nodeModel.root, deserContext.hasErrors()));
	}

	protected void moveContentToResource(Resource carrier, XtextResource xr) {
		EObject root = getRoot(carrier);

		if (root != null) {
			xr.getContents().clear();
			xr.getContents().add(root);
		}
	}

	protected EObject getRoot(Resource resource) {
		TreeIterator<EObject> allContents = resource.getAllContents();
		if (allContents.hasNext()) {
			return allContents.next();
		}

		return null;
	}

	public void write(XtextResource resource, OutputStream emfOut, OutputStream nodeModelOut) throws IOException {
		serializeEMF(resource, emfOut);
		serializeNodeModel(resource, nodeModelOut);
	}

	protected void serializeEMF(XtextResource resource, OutputStream out) throws IOException {
		Resource intermediate = getIntermediateResource();
		saveThroughIntermediate(resource, out, intermediate);
	}

	private static Resource getIntermediateResource() {
		if (isCapableEMFVersion()) {
			return new BinaryResourceImpl();
		}

		return new XMLResourceImpl();
	}

	protected void saveThroughIntermediate(XtextResource resource, OutputStream out, Resource intermediate)
			throws IOException {
		EList<EObject> contents = resource.getContents();

		if (contents.size() > 0) {
			EObject root = contents.get(0);
			intermediate.getContents().add(root);
			assert resource.getContents().isEmpty();

			try {
				intermediate.save(out, ImmutableMap.of());
			} finally {
				resource.getContents().add(root);
			}
		} else {
			intermediate.save(out, ImmutableMap.of());
		}
	}

	protected void serializeNodeModel(XtextResource resource, OutputStream out) throws IOException {
		SerializableNodeModel model = new SerializableNodeModel(resource);

		SerializationConversionContext serContext = new SerializationConversionContext(resource);

		DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(out));
		try {
			model.writeObjectData(dos, serContext);
		} finally {
			dos.flush();
		}
	}

	protected Resource deserializeEMFModel(InputStream in) throws IOException {
		Resource intermediate = getIntermediateResource();
		
		intermediate.load(in, ImmutableMap.of());
		
		return intermediate; 
	}

	public static boolean isCapableEMFVersion() {
		int[] emfVersion = getEMFVersion();

		if (emfVersion != null) {
			if (emfVersion[0] >= 2) {
				if (emfVersion[1] >= 6) {
					return true;
				}
			}
		}

		return false;
	}

	private static int[] getEMFVersion() {
		final Bundle bundle = Platform.getBundle("org.eclipse.emf.common");
		
		if (bundle == null) {
			return null; 
		}
		
		final Dictionary<String, String> headers = bundle.getHeaders();
		
		if (headers == null) {
			return null; 
		}
		
		String version = headers.get("Bundle-Version");
		
		if (version == null) {
			return null; 
		}

		String[] split = version.split("\\.");

		if (split.length < 3) {
			return null;
		}

		int[] numericVersion = new int[3];

		try {
			for (int i = 0; i < 3; i++) {
				numericVersion[i] = Integer.parseInt(split[i]);
			}
		} catch (NumberFormatException e) {
			return null;
		}

		return numericVersion;
	}

	private boolean invariantsOK(SerializableNodeModel nodeModel) {
		try {
			InvariantChecker checker = new InvariantChecker();
			checker.checkInvariant(nodeModel.root);
		} catch (Exception e) {
			return false;
		}

		return true;
	}
}
