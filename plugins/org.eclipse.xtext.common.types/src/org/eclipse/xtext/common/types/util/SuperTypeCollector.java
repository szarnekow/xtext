/*******************************************************************************
 * Copyright (c) 2009 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.common.types.util;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import org.eclipse.xtext.common.types.JvmAnyTypeReference;
import org.eclipse.xtext.common.types.JvmArrayType;
import org.eclipse.xtext.common.types.JvmConstraintOwner;
import org.eclipse.xtext.common.types.JvmDeclaredType;
import org.eclipse.xtext.common.types.JvmDelegateTypeReference;
import org.eclipse.xtext.common.types.JvmGenericArrayTypeReference;
import org.eclipse.xtext.common.types.JvmLowerBound;
import org.eclipse.xtext.common.types.JvmMultiTypeReference;
import org.eclipse.xtext.common.types.JvmParameterizedTypeReference;
import org.eclipse.xtext.common.types.JvmPrimitiveType;
import org.eclipse.xtext.common.types.JvmSpecializedTypeReference;
import org.eclipse.xtext.common.types.JvmType;
import org.eclipse.xtext.common.types.JvmTypeConstraint;
import org.eclipse.xtext.common.types.JvmTypeReference;
import org.eclipse.xtext.common.types.TypesFactory;

import com.google.common.collect.Sets;
import com.google.inject.Inject;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 * @author Sven Efftinge
 */
public class SuperTypeCollector {

	public interface SuperTypeAcceptor {
		/**
		 * @param superType a found super type
		 * @param distance the distance to the starting type. StringBuilder has a distance 1 to 
		 * AbstractStringBuilder, distance 1 and 2 to CharSequence and distance 2 to Appendable.
		 */
		boolean accept(JvmTypeReference superType, int distance);
	}
	
	@Inject
	private TypesFactory factory;
	
	@Inject
	private TypeReferences typeReferences;
	
	public SuperTypeCollector() {}

	public SuperTypeCollector(TypesFactory factory) {
		this.factory = factory;
	}
	
	protected JvmTypeReference newRef(JvmType type) {
		JvmParameterizedTypeReference reference = factory.createJvmParameterizedTypeReference();
		reference.setType(type);
		return reference;
	}

	public Set<JvmTypeReference> collectSuperTypes(JvmType type) {
		return collectSuperTypes(newRef(type));
	}

	public Set<JvmTypeReference> collectSuperTypes(JvmTypeReference type) {
		final Set<JvmTypeReference> result = Sets.newLinkedHashSet();
		final Set<JvmType> rawTypes = Sets.newHashSet();
		doCollectSupertypeData(type, new SuperTypeAcceptor() {
			public boolean accept(JvmTypeReference superType, int distance) {
				JvmType rawType = superType.getType();
				if (rawType != null && !rawType.eIsProxy() && rawTypes.add(superType.getType())) {
					result.add(superType);
					return true;
				}
				return false;
			}
		});
		return result;
	}
	
	public void collectSuperTypes(JvmTypeReference type, SuperTypeAcceptor acceptor) {
		doCollectSupertypeData(type, acceptor);
	}

	public Set<String> collectSuperTypeNames(JvmType type) {
		return collectSuperTypeNames(newRef(type));
	}

	public Set<JvmType> collectSuperTypesAsRawTypes(JvmTypeReference type) {
		final Set<JvmType> result = Sets.newLinkedHashSet();
		doCollectSupertypeData(type, new SuperTypeAcceptor() {
			public boolean accept(JvmTypeReference superType, int distance) {
				JvmType rawType = superType.getType();
				if (rawType != null && !rawType.eIsProxy()) {
					boolean notYetSeen = result.add(superType.getType());
					return notYetSeen;
				}
				return false;
			}
		});
		return result;
	}

	public Set<String> collectSuperTypeNames(JvmTypeReference type) {
		final Set<String> result = Sets.newLinkedHashSet();
		doCollectSupertypeData(type, new SuperTypeAcceptor() {
			public boolean accept(JvmTypeReference superType, int distance) {
				String name = getSuperTypeName(superType);
				if (name != null)
					return result.add(name);
				return false;
			}
			
			public String getSuperTypeName(JvmTypeReference typeReference) {
				if (typeReference instanceof JvmParameterizedTypeReference) {
					JvmType rawType = typeReference.getType();
					if (rawType != null && !rawType.eIsProxy()) {
						return rawType.getIdentifier();
					}
					return null;
				} else {
					return typeReference.getIdentifier();
				}
			}
		});
		return result;
	}

	public void doCollectSupertypeData(JvmTypeReference type, SuperTypeAcceptor acceptor) {
		if (type != null) {
			Implementation implementation = new Implementation(acceptor, typeReferences);
			implementation.doSwitch(type);
		}
	}

	public static class Implementation extends TypesSwitch<Boolean> {

		private boolean collecting = false;
		private SuperTypeAcceptor acceptor;
		private int level;
		private final TypeReferences references;

		public Implementation(SuperTypeAcceptor acceptor, TypeReferences references) {
			this.acceptor = acceptor;
			this.references = references;
			this.level = 0;
		}

		@Override
		public Boolean caseJvmTypeReference(JvmTypeReference object) {
			if (!object.eIsProxy()) {
				if (!collecting || acceptor.accept(object, level)) {
					collecting = true;
					if (object.getType() != null)
						doSwitch(object.getType());
				}
			}
			return null;
		}
		
		@Override
		public Boolean caseJvmGenericArrayTypeReference(JvmGenericArrayTypeReference object) {
			if (!object.eIsProxy()) {
				collecting = true;
				level++;
				final SuperTypeAcceptor original = acceptor;
				try {
					acceptor = new SuperTypeAcceptor() {
						public boolean accept(JvmTypeReference superType, int distance) {
							JvmTypeReference arraySuperType = references.createArrayType(superType);
							if (references.is(superType, Object.class)) {
								original.accept(superType, distance + 1);
								original.accept(references.getTypeForName(Serializable.class, superType.getType()), distance + 1);
								original.accept(references.getTypeForName(Cloneable.class, superType.getType()), distance + 1);
							}
							return original.accept(arraySuperType, distance);
						}
					};
					if (object.getComponentType() != null)
						doSwitch(object.getComponentType());
				} finally {
					acceptor = original;
				}
				JvmArrayType rawArrayType = object.getType();
				if (rawArrayType != null) {
					JvmType rawType = rawArrayType.getComponentType();
					while(rawType instanceof JvmArrayType) {
						rawType = ((JvmArrayType) rawType).getComponentType();
					}
					if (rawType instanceof JvmPrimitiveType) {
						doSwitch(references.getTypeForName(Serializable.class, rawType));
						doSwitch(references.getTypeForName(Cloneable.class, rawType));
					}
				}
				level--;
			}
			return null;
		}
		
		@Override
		public Boolean caseJvmMultiTypeReference(JvmMultiTypeReference object) {
			if (!object.eIsProxy()) {
				collecting = true;
				level++;
				for(JvmTypeReference reference: object.getReferences()) {
					doSwitch(reference);
				}
				level--;
			}
			return Boolean.FALSE;
		}
		
		@Override
		public Boolean caseJvmDelegateTypeReference(JvmDelegateTypeReference object) {
			if (!object.eIsProxy()) {
				collecting = true;
				doSwitch(object.getDelegate());
			}
			return Boolean.FALSE;
		}
		
		@Override
		public Boolean caseJvmSpecializedTypeReference(JvmSpecializedTypeReference object) {
			if (!object.eIsProxy()) {
				collecting = true;
				level++;
				JvmTypeReference equivalent = object.getEquivalent();
				if (equivalent != null)
					doSwitch(equivalent);
				level--;
			}
			return Boolean.FALSE;
		}
		
		@Override
		public Boolean caseJvmAnyTypeReference(JvmAnyTypeReference object) {
			return Boolean.FALSE;
		}
		
		@Override
		public Boolean caseJvmDeclaredType(JvmDeclaredType object) {
			if (!object.eIsProxy()) {
				level++;
				for (JvmTypeReference superType : object.getSuperTypes()) {
					doSwitch(superType);
				}
				level--;
			}
			return null;
		}
		
		@Override
		public Boolean caseJvmTypeConstraint(JvmTypeConstraint object) {
			if (object.getTypeReference() != null)
				return doSwitch(object.getTypeReference());
			return Boolean.TRUE;
		}
		
		@Override
		public Boolean caseJvmConstraintOwner(JvmConstraintOwner object) {
			if (!object.eIsProxy()) {
				List<JvmTypeConstraint> constraints = object.getConstraints();
				boolean boundProcessed = false;
				if (!constraints.isEmpty()) {
					for(JvmTypeConstraint constraint: constraints) {
						if (constraint instanceof JvmLowerBound) {
							doSwitch(constraint);
							boundProcessed = true;
						}
					}
					if (!boundProcessed) { 
						for(JvmTypeConstraint constraint: constraints) {
							doSwitch(constraint);
							boundProcessed = true;
						}
					}
				}
				if (!boundProcessed) {
					JvmType objectType = references.findDeclaredType(Object.class, object);
					doSwitch(references.createTypeRef(objectType));
				}
			}
			return Boolean.TRUE;
		}
		
	}

	public boolean isSuperType(JvmDeclaredType subType, JvmDeclaredType superType) {
		if (subType==null || superType == null)
			return false;
		return collectSuperTypesAsRawTypes(newRef(subType)).contains(superType);
	}

}
