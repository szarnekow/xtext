/*******************************************************************************
 * Copyright (c) 2011 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.xtend2.validation;

/**
 * @author Sven Efftinge - Initial contribution and API
 * @author Jan Koehnlein
 */
public final class IssueCodes {

	private IssueCodes() {
	}

	protected static final String ISSUE_CODE_PREFIX = "org.eclipse.xtext.xtend2.validation.IssueCodes.";

	public static final String ANNOTATION_WRONG_TARGET = ISSUE_CODE_PREFIX + "wrong_annotation_target";
	public static final String DISPATCH_FUNC_WITHOUT_PARAMS = ISSUE_CODE_PREFIX + "case_function_without_params";
	public static final String DISPATCH_FUNC_WITH_TYPE_PARAMS = ISSUE_CODE_PREFIX + "case_function_with_type_params";
	public static final String DISPATCH_FUNC_NAME_STARTS_WITH_UNDERSCORE = ISSUE_CODE_PREFIX
			+ "case_func_name_starts_with_underscore";
	public static final String DISPATCH_FUNCTIONS_WITH_DIFFERENT_VISIBILITY = "dispatch_functions_with_different_visibility";
	public static final String DISPATCH_FUNCTIONS_MIXED_STATIC_AND_NON_STATIC = "dispatch_functions_mixed_static_and_non_static";
	public static final String DISPATCH_FUNCTIONS_STATIC_EXPECTED = "dispatch_functions_static_expected";
	public static final String DISPATCH_FUNCTIONS_NON_STATIC_EXPECTED = "dispatch_functions_non_static_expected";
	public static final String SINGLE_DISPATCH_FUNCTION = ISSUE_CODE_PREFIX + "single_case_function";
	public static final String DISPATCH_PLAIN_FUNCTION_NAME_CLASH = ISSUE_CODE_PREFIX + "dispatch_plain_function_name_clash";
	public static final String WRONG_PACKAGE = ISSUE_CODE_PREFIX + "wrong_package";
	public static final String WRONG_FILE = ISSUE_CODE_PREFIX + "wrong_file";
	public static final String CLASS_EXPECTED = ISSUE_CODE_PREFIX + "class_expected";
	public static final String INTERFACE_EXPECTED = ISSUE_CODE_PREFIX + "interface_expected";
	public static final String DUPLICATE_METHOD = ISSUE_CODE_PREFIX + "duplicate_method";
	public static final String DUPLICATE_FIELD = ISSUE_CODE_PREFIX + "duplicate_field";
	public static final String MISSING_OVERRIDE = ISSUE_CODE_PREFIX + "missing_override";
	public static final String OBSOLETE_OVERRIDE = ISSUE_CODE_PREFIX + "obsolete_override";
	public static final String INCONSISTENT_INDENTATION = ISSUE_CODE_PREFIX + "inconsistent_indentation";

	public static final String XTEND_LIB_NOT_ON_CLASSPATH = ISSUE_CODE_PREFIX + "xtend_lib_not_on_classpath";

	public static final String XBASE_LIB_NOT_ON_CLASSPATH = ISSUE_CODE_PREFIX + "xbase_lib_not_on_classpath";

	public static final String CLASS_MUST_BE_ABSTRACT = ISSUE_CODE_PREFIX + "class_must_be_defined_abstract";
	public static final String DUPLICATE_PARAMETER_NAME = ISSUE_CODE_PREFIX + "duplicate_parameter_name";

	public static final String OVERRIDDEN_FINAL = ISSUE_CODE_PREFIX + "overridden_final";
	public static final String OVERRIDE_REDUCES_VISIBILITY = ISSUE_CODE_PREFIX + "override_reduces_visibility";
	public static final String INCOMPATIBLE_THROWS_CLAUSE = ISSUE_CODE_PREFIX + "incompatible_throws_clause";
	public static final String CYCLIC_INHERITANCE = ISSUE_CODE_PREFIX + "cyclic_inheritance";

	public static final String IMPORT_WILDCARD_DEPRECATED = ISSUE_CODE_PREFIX + "import_wildcard_deprecated";
	public static final String IMPORT_DUPLICATE = ISSUE_CODE_PREFIX + "import_duplicate";
	public static final String IMPORT_UNUSED = ISSUE_CODE_PREFIX + "import_unsued";
	public static final String FIELD_LOCALLY_NEVER_READ = "field_locally_never_read";
	public static final String FUNCTION_LOCALLY_NEVER_USED = "function_locally_never_used";
	public static final String EXCEPTION_NOT_THROWABLE = "exception_not_throwable";
	public static final String EXCEPTION_DECLARED_TWICE = "exception_declared_twice";

	public static final String MISSING_CONSTRUCTOR = ISSUE_CODE_PREFIX + "missing_constructor";
	public static final String MUST_INVOKE_SUPER_CONSTRUCTOR = ISSUE_CODE_PREFIX + "must_invoke_super_constructor";
	public static final String CONSTRUCTOR_TYPE_PARAMS_NOT_SUPPORTED = ISSUE_CODE_PREFIX
			+ "constructor_type_params_not_supported";

	public static final String INVALID_USE_OF_STATIC = ISSUE_CODE_PREFIX + "invalid_use_of_static";
	public static final String INVALID_USE_OF_TYPEPARAMS = ISSUE_CODE_PREFIX + "invalid_use_of_typeParams";

	public static final String INVALID_MEMBER_NAME = ISSUE_CODE_PREFIX + "invalid_member_name";
	public static final String LEFT_HAND_SIDE_MUST_BE_VARIABLE = "left_hand_side_must_be_variable";
}
