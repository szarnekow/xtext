/*******************************************************************************
 * Copyright (c) 2011 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.xbase.compiler;

import static com.google.common.collect.Lists.*;
import static com.google.common.collect.Maps.*;
import static com.google.common.collect.Sets.*;
import static java.util.Collections.*;

import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * @author Sven Efftinge - Initial contribution and API
 */
public class ScopeStack {
	
	private Stack<Scope>  scopes = new Stack<Scope>();
	
	public void openScope(boolean pseudoScope) {
		scopes.push(new Scope(pseudoScope));
	}
	
	public void closeScope() {
		scopes.pop();
	}
	
	public Object get(String name) {
		if (name == null)
			throw new NullPointerException("name");
		if (scopes.isEmpty())
			return null;
		
		int size = scopes.size();
		int i = size - 1;
		while (i >= 0) {
			Scope currentScope = scopes.get(i--);
			Variable var = currentScope.get(name);
			if (var != null)
				return var.referenced;
		}
		return null;
	}
	
	public String getName(Object referenced) {
		if (referenced == null)
			throw new NullPointerException("referenced");
		if (scopes.isEmpty())
			return null;
		
		int size = scopes.size();
		int i = size - 1;
		while (i >= 0) {
			Scope currentScope = scopes.get(i--);
			for (Variable v : currentScope.variables()) {
				if (v.referenced.equals(referenced))
					return v.name;
			}
		}
		return null;
	}
	
	/**
	 * provides and registeres a fresh variable in the current scope.
	 * It takes parent scopes into account and only reuses names of synthetic variables from parent scopes.
	 * Pseudo scopes are treated as if they were part of their parent scope.
	 */
	public String declareVariable(Object key, String proposedName, boolean synthetic) {
		if (scopes.isEmpty())
			throw new IllegalArgumentException("No scope has been opened yet.");
		Scope currentScope = scopes.peek();
		final Set<String> names = newHashSet();
		boolean scopeClosed = false;
		// add only the non-synthetic variables, since they could be referenced from nested scopes.
		for (Scope scope : reverse(newArrayList(scopes))) {
			for (Variable variable : scope.variables()) {
				if (!scopeClosed || !variable.synthetic)
					names.add(variable.name);
			}
			scopeClosed = scopeClosed || !scope.pseudoScope;
			// if we left the current scope (incl. pseudo scopes) and the variable is not synthetic, we can stop collecting names. 
			// Overriding names from outside is ok in that case. 
			if (scopeClosed && !synthetic)
				break;
		}
		String newName = findNewName(names, proposedName);
		currentScope.addVariable(newName, synthetic, key);
		return newName;
	}
	
	
	protected String findNewName(Set<String> names, String proposedName) {
		if (names.contains(proposedName)) {
			for (int i = 1; i < Integer.MAX_VALUE; i++) {
				String newProposal = proposedName + "_" + i;
				if (!names.contains(newProposal))
					return newProposal;
			}
		}
		return proposedName;
	}

	static class Scope {
		public Scope(boolean pseudoScope) {
			this.pseudoScope = pseudoScope;
		}
		/**
		 * whether this scope is just a pseudo scope. 
		 * I.e. not backed up by a real Java scope.
		 */
		public boolean pseudoScope = false;
		
		private Map<String, Variable> variables = newHashMap();
		
		public void addVariable(String name, boolean synthetic, Object element) {
			if (variables.containsKey(name))
				throw new IllegalArgumentException("variable '"+name+"' already declared in scope.");
			variables.put(name, new Variable(name, synthetic,element));
		}
		
		public Set<String> variableNames() {
			return variables.keySet();
		}
		
		public Iterable<Variable> variables() {
			return variables.values();
		}
		
		public Variable get(String name) {
			return variables.get(name);
		}
		
		@Override
		public String toString() {
			return (pseudoScope?"[PSEUDO]":"")+variables;
		}
	}
	
	final static class Variable {
		Variable(String name2, boolean synthetic2, Object referenced) {
			this.name = name2;
			this.synthetic = synthetic2;
			this.referenced = referenced;
		}
		public String name;
		public boolean synthetic;
		public Object referenced;
		
		@Override
		public String toString() {
			return referenced.getClass().getSimpleName();
		}
	}
}
