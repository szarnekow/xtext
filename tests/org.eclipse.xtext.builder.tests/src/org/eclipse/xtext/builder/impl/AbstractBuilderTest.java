/*******************************************************************************
 * Copyright (c) 2009 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.builder.impl;

import static org.eclipse.xtext.builder.impl.BuilderUtil.*;
import static org.eclipse.xtext.junit4.ui.util.IResourcesSetupUtil.*;

import java.util.List;

import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.intro.IIntroManager;
import org.eclipse.xtext.builder.tests.Activator;
import org.eclipse.xtext.resource.IResourceDescription;
import org.eclipse.xtext.resource.IResourceDescription.Event;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;

import com.google.common.collect.Lists;
import com.google.inject.Injector;

/**
 * @author Sven Efftinge - Initial contribution and API
 */
@SuppressWarnings("restriction")
public abstract class AbstractBuilderTest extends Assert implements IResourceDescription.Event.Listener {
	public final String F_EXT = ".buildertestlanguage";
	private volatile List<Event> events = Lists.newArrayList();

	@Before
	public void setUp() throws Exception {
		assertEquals(0, countResourcesInIndex());
		assertEquals(0, root().getProjects().length);
		if (PlatformUI.isWorkbenchRunning()) {
			IIntroManager introManager = PlatformUI.getWorkbench().getIntroManager();
			if (introManager.getIntro() != null) {
				introManager.closeIntro(introManager.getIntro());
			}
		}
	}

	@After
	public void tearDown() throws Exception {
		cleanWorkspace();
		waitForAutoBuild();
		events.clear();
		getBuilderState().removeListener(this);
		assertEquals(0, countResourcesInIndex());
		assertEquals(0, root().getProjects().length);
	}

	public void descriptionsChanged(Event event) {
		this.events.add(event);
	}
	
	public List<Event> getEvents() {
		return events;
	}
	
	public <T> T getInstance(Class<T> type) {
		Injector injector = Activator.getInstance().getInjector("org.eclipse.xtext.builder.tests.BuilderTestLanguage");
		return injector.getInstance(type);
	}
}
