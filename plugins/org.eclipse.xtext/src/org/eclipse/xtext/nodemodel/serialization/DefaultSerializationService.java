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
import java.util.Dictionary;

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
import org.eclipse.emf.ecore.resource.impl.BinaryResourceImpl.EObjectInputStream;
import org.eclipse.emf.ecore.resource.impl.BinaryResourceImpl.EObjectOutputStream;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;
import org.eclipse.xtext.nodemodel.impl.InvariantChecker;
import org.eclipse.xtext.nodemodel.impl.InvariantChecker.InconsistentNodeModelException;
import org.eclipse.xtext.nodemodel.impl.SerializableNodeModel;
import org.eclipse.xtext.parser.ParseResult;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.osgi.framework.Bundle;
import org.osgi.framework.Version;

import com.google.common.collect.ImmutableMap;

/**
 * @author Mark Christiaens - Initial contribution
 * 
 * @since 2.1
 */
public class DefaultSerializationService implements ISerializationService {
	private static final Logger LOGGER = Logger.getLogger(DefaultSerializationService.class);

	private static final Version MINIMUM_BINARY_CAPABLE_EMF_VERSION = new Version(2, 6, 0);

	public XtextResource getResource(XtextResourceSet resourceSet, URI uri, InputStream emfIn, InputStream nodeModelIn)
			throws IOException {
		XtextResource xr = getResource(resourceSet, uri, emfIn);
		augmentWithNodeModel(nodeModelIn, xr);

		return xr;
	}

	protected XtextResource getResource(XtextResourceSet resourceSet, URI uri, InputStream emfIn) throws IOException {
		XtextResource xr = (XtextResource) resourceSet.getResource(uri, false);
		if (xr == null) {
			xr = (XtextResource) resourceSet.createResource(uri);
		}

		deserializeEMFModel(emfIn, xr);
		fixupProxies(xr);

		return xr;
	}

	protected void deserializeEMFModel(InputStream in, Resource resource) throws IOException {
		if (isCapableEMFVersion()) {
			EObjectInputStream objectInputStream = new BinaryResourceImpl.EObjectInputStream(in, ImmutableMap.of());
			objectInputStream.loadResource(resource);
		} else {
			XMLResourceImpl intermediate = new XMLResourceImpl();
			intermediate.load(in, ImmutableMap.of());
			resource.getContents().addAll(intermediate.getContents());
		}
	}

	protected void augmentWithNodeModel(InputStream nodeModelIn, XtextResource xr) throws IOException,
			UnsupportedEncodingException {
		if (nodeModelIn == null) {
			return;
		}

		DeserializationConversionContext deserContext = new DeserializationConversionContext(xr);

		SerializableNodeModel nodeModel = new SerializableNodeModel();
		nodeModel.readObjectData(new EObjectInputStream(new BufferedInputStream(nodeModelIn), ImmutableMap.of()), deserContext);

		checkInvariants(nodeModel);

		xr.setParseResult(new ParseResult(getRoot(xr), nodeModel.root, deserContext.hasErrors()));
	}

	protected void checkInvariants(SerializableNodeModel nodeModel) {
		if (LOGGER.isDebugEnabled()) {
			try {
				InvariantChecker checker = new InvariantChecker();
				checker.checkInvariant(nodeModel.root);
			} catch (InconsistentNodeModelException e) {
				LOGGER.error(e);
				throw e;
			}
		}
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
		if (isCapableEMFVersion()) {
			EObjectOutputStream objectOutputStream = new BinaryResourceImpl.EObjectOutputStream(out, ImmutableMap.of());
			objectOutputStream.saveResource(resource);
		} else {
			XMLResourceImpl intermediate = new XMLResourceImpl();
			try {
				intermediate.getContents().addAll(resource.getContents());
				intermediate.save(out, ImmutableMap.of());
			} finally {
				/* TODO: A bit worried whether this could trigger listener updates or something. */
				resource.getContents().addAll(intermediate.getContents());
			}
		}
	}

	protected void serializeNodeModel(XtextResource resource, OutputStream out) throws IOException {
		SerializableNodeModel model = new SerializableNodeModel(resource);

		SerializationConversionContext serContext = new SerializationConversionContext(resource);

		BufferedOutputStream bout = new BufferedOutputStream(out);
		EObjectOutputStream eos = new EObjectOutputStream(bout, ImmutableMap.of());
		model.writeObjectData(eos, serContext);
		bout.flush();
	}

	public static boolean isCapableEMFVersion() {
		Version emfVersion = getEMFVersion();

		if (emfVersion != null) {
			return MINIMUM_BINARY_CAPABLE_EMF_VERSION.compareTo(emfVersion) <= 0;
		}

		return false;
	}

	public static Version getEMFVersion() {
		final Bundle bundle = Platform.getBundle("org.eclipse.emf.common");

		if (bundle == null) {
			return null;
		}

		return bundle.getVersion();
	}

	static protected void fixupProxies(XtextResource xr) {
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

	static protected void fixupProxy(EObject target, URI newResourceURI) {
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
}