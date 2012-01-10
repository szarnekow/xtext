package org.eclipse.xtext.xbase.junit.ui;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.Set;


import org.eclipse.core.resources.IWorkspace;
import org.eclipse.emf.common.util.URI;
import org.eclipse.xtext.Constants;
import org.eclipse.xtext.junit4.ui.ContentAssistProcessorTestBuilder;
import org.eclipse.xtext.junit4.ui.util.IResourcesSetupUtil;
import org.eclipse.xtext.junit4.util.ResourceLoadHelper;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.eclipse.xtext.util.StringInputStream;
import org.eclipse.xtext.util.Strings;
import org.eclipse.xtext.xbase.XExpression;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.name.Named;

@SuppressWarnings("restriction")
public abstract class AbstractXbaseContentAssistTest extends Assert implements ResourceLoadHelper {

	@Inject
	protected IWorkspace workspace;

	protected String fileExtension;

	@Before
	public void setUp() throws Exception {
		getInjector().injectMembers(this);
	}

	@After
	public void tearDown() throws Exception {
		if (doCleanWorkspace())
			IResourcesSetupUtil.cleanWorkspace();
	}
	
	protected boolean doCleanWorkspace() {
		return true;
	}
	
	protected abstract Injector getInjector();
	
	public <T> T get(Class<T> clazz) {
		return getInjector().getInstance(clazz);
	}

	protected XExpression expression(String string) throws Exception {
		XtextResource resource = getResourceFor(new StringInputStream(string));
		assertEquals(resource.getErrors().toString(), 0, resource.getErrors().size());
		XExpression result = (XExpression) resource.getContents().get(0);
		return result;
	}
	
	protected XtextResourceSet getResourceSet() {
		return get(XtextResourceSet.class);
	}
	
	public XtextResource getResourceFor(InputStream stream) {
		try {
			XtextResource result = (XtextResource) getResourceSet().createResource(URI.createURI("Test." + fileExtension));
			result.load(stream, null);
			return result;
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Inject
	protected void setExtensions(@Named(Constants.FILE_EXTENSIONS) String extensions) {
		this.fileExtension = extensions.split(",")[0];
	}
	
	@Test public void testEmptyInput() throws Exception {
		newBuilder().assertText(getKeywordsAndStatics());
	}
	
	protected String[] getKeywordsAndStatics() {
		return KEYWORDS_AND_STATICS;
	}
	
	protected static String[] KEYWORDS_AND_STATICS = {
		"if", 
		"while", "for", "do",
		"true", "false",
		"typeof",
		"try",
		"switch",
		"new",
		"throw",
		"return",
		"null",
		// Collection Literals
		"emptyList",
		"emptySet",
		"emptyMap",
		"newImmutableList()",
		"newImmutableSet()",
		"newImmutableMap()",
		"newArrayList()",
		"newLinkedList()",
		"newHashSet()",
		"newLinkedHashSet()",
		"newTreeSet()",
		"newHashMap()",
		"newLinkedHashMap()",
		"newTreeMap()",
		// InputOutput,
		"print()",
		"println()",
	};
	
	protected static String[] STRING_OPERATORS = {
		"==", "!=",
		"+",
		"<=", ">=", "<", ">",
		"->"
	};
	
	protected static String[] CAST_INSTANCEOF = {
		"as", "instanceof"
	};
	
	protected static final String[] STRING_FEATURES;
	protected static final String[] STATIC_STRING_FEATURES;
	
	static {
		List<String> features = Lists.newArrayList();
		List<String> staticFeatures = Lists.newArrayList();
		for(Method method: String.class.getMethods()) {
			List<String> list = features;
			if (Modifier.isStatic(method.getModifiers()))
				list = staticFeatures;
			if (method.getParameterTypes().length == 0) {
				if (method.getName().startsWith("get") && method.getParameterTypes().length == 0) {
					list.add(Strings.toFirstLower(method.getName().substring(3)));
				} else if (method.getName().startsWith("is") && method.getParameterTypes().length == 0) {
					list.add(Strings.toFirstLower(method.getName().substring(2)));
				} else {
					list.add(method.getName());
				}
			} else {
				list.add(method.getName() + "()");				
			}
		}
		// compareTo(T) is actually overridden by compareTo(String) but contained twice in String.class#getMethods
		features.remove("compareTo()");
		Set<String> featuresAsSet = Sets.newHashSet(features);
		Set<String> staticFeaturesAsSet = Sets.newHashSet(staticFeatures);
		for(Field field: String.class.getFields()) {
			Set<String> asSet = featuresAsSet;
			List<String> list = features;
			if (Modifier.isStatic(field.getModifiers())) {
				list = staticFeatures;
				asSet = staticFeaturesAsSet;
			}
			if (asSet.add(field.getName()))
				list.add(field.getName());
		}
		// StringExtensions
		features.add("toFirstLower");
		features.add("toFirstUpper");
		features.add("nullOrEmpty");
		// Object extensions
		features.add("identityEquals()");
		STRING_FEATURES = features.toArray(new String[features.size()]);
		STATIC_STRING_FEATURES = staticFeatures.toArray(new String[staticFeatures.size()]);
	}
	
	public String[] getStringFeatures() {
		return STRING_FEATURES;
	}
	
	public String[] getStaticStringFeatures() {
		return STATIC_STRING_FEATURES;
	}

	protected String[] expect(String[]... arrays) {
		List<String> expectation = Lists.newArrayList();
		for(String[] array: arrays) {
			expectation.addAll(Arrays.asList(array));
		}
		return expectation.toArray(new String[expectation.size()]);
	}
	
	@Test public void testOnStringLiteral_01() throws Exception {
		newBuilder().append("''").assertText(expect(STRING_OPERATORS, CAST_INSTANCEOF));
	}
	
	@Test public void testOnStringLiteral_02() throws Exception {
		newBuilder().append("''.").assertText(getStringFeatures());
	}
	
	@Test public void testOnStringLiteral_03() throws Exception {
		newBuilder().append("''.").assertTextAtCursorPosition(".", expect(STRING_OPERATORS, CAST_INSTANCEOF));
	}
	
	@Test public void testOnStringLiteral_04() throws Exception {
		newBuilder().append("''+''").assertTextAtCursorPosition("+", expect(STRING_OPERATORS, CAST_INSTANCEOF));
	}
	
	@Test public void testOnStringLiteral_05() throws Exception {
		newBuilder().append("''+''").assertTextAtCursorPosition("+''", 1, expect(new String[]{"+"}, getKeywordsAndStatics()));
	}
	
	@Test public void testOnStringLiteral_06() throws Exception {
		newBuilder().append("''==''").assertTextAtCursorPosition("==", 1, "==");
	}
	
	@Test public void testOnStringLiteral_07() throws Exception {
		newBuilder().append("''==''").assertTextAtCursorPosition("==", 2, expect(new String[]{"=="}, getKeywordsAndStatics()));
	}
	
	@Test public void testOnStringLiteral_08() throws Exception {
		newBuilder().append("''<=''").assertTextAtCursorPosition("<=", 1, expect(new String[]{"<", "<="}, getKeywordsAndStatics()));
	}
	
	@Test public void testOnStringLiteral_09() throws Exception {
		newBuilder().append("''<=''").assertTextAtCursorPosition("<=", 2, expect(new String[]{"<="}, getKeywordsAndStatics()));
	}
	
	@Test public void testOnStringLiteral_10() throws Exception {
		newBuilder().append("'' ").assertText(expect(STRING_OPERATORS, CAST_INSTANCEOF));
	}
	
	@Test public void testOnStringLiteral_11() throws Exception {
		newBuilder().append("''. ").assertText(getStringFeatures());
	}
	
	@Test public void testOnStringLiteral_12() throws Exception {
		newBuilder().append("'' .").assertTextAtCursorPosition(".", expect(STRING_OPERATORS, CAST_INSTANCEOF));
	}
	
	@Test public void testOnStringLiteral_13() throws Exception {
		newBuilder().append("'' + ''").assertTextAtCursorPosition("+", expect(STRING_OPERATORS, CAST_INSTANCEOF));
	}
	
	@Test public void testOnStringLiteral_14() throws Exception {
		newBuilder().append("'' + ''").assertTextAtCursorPosition("+ ''", 2, getKeywordsAndStatics());
		newBuilder().append("'' + ''").assertTextAtCursorPosition("+ ''", 1, expect(new String[]{"+"}, getKeywordsAndStatics()));
	}
	
	@Test public void testOnStringLiteral_15() throws Exception {
		newBuilder().append("'' == ''").assertTextAtCursorPosition("==", 1, "==");
	}
	
	@Test public void testOnStringLiteral_16() throws Exception {
		newBuilder().append("'' == ''").assertTextAtCursorPosition("==", 2, expect(new String[]{"=="}, getKeywordsAndStatics()));
		newBuilder().append("'' == ''").assertTextAtCursorPosition("==", 3, getKeywordsAndStatics());
	}
	
	@Test public void testOnStringLiteral_17() throws Exception {
		newBuilder().append("'' <= ''").assertTextAtCursorPosition("<=", 1, expect(new String[]{"<", "<="}, getKeywordsAndStatics()));
	}
	
	@Test public void testOnStringLiteral_18() throws Exception {
		newBuilder().append("'' <= ''").assertTextAtCursorPosition("<=", 2, expect(new String[]{"<="}, getKeywordsAndStatics()));
		newBuilder().append("'' <= ''").assertTextAtCursorPosition("<=", 3, getKeywordsAndStatics());
	}
	
	@Test public void testOnStringLiteral_19() throws Exception {
		newBuilder().append("''.toString").assertText(expect(STRING_OPERATORS, new String[]{"toString"}));
	}
	
	@Test public void testOnStringLiteral_20() throws Exception {
		newBuilder().append("''.toString.").assertText(getStringFeatures());
	}
	
	@Test public void testOnStringLiteral_21() throws Exception {
		newBuilder().append("''.toString.").assertTextAtCursorPosition("g.", 1, expect(STRING_OPERATORS, new String[]{"toString"}));
	}
	
	@Test public void testOnStringLiteral_22() throws Exception {
		newBuilder().append("''.toString+''").assertTextAtCursorPosition("+", expect(STRING_OPERATORS, new String[]{"toString"}));
	}
	
	@Test public void testOnStringLiteral_23() throws Exception {
		newBuilder().append("''.toString+''").assertTextAtCursorPosition("+''", 1, expect(new String[]{"+"}, getKeywordsAndStatics()));
	}
	
	@Test public void testOnStringLiteral_24() throws Exception {
		newBuilder().append("''.toString==''").assertTextAtCursorPosition("==", 1, expect(new String[] {"=="}, getKeywordsAndStatics()));
	}
	
	@Test public void testOnStringLiteral_25() throws Exception {
		newBuilder().append("''.toString==''").assertTextAtCursorPosition("==", 2, expect(new String[]{"=="}, getKeywordsAndStatics()));
	}
	
	@Test public void testOnStringLiteral_26() throws Exception {
		newBuilder().append("''.toString<=''").assertTextAtCursorPosition("<=", 1, expect(new String[]{"<", "<="}, getKeywordsAndStatics()));
	}
	
	@Test public void testOnStringLiteral_27() throws Exception {
		newBuilder().append("''.toString<=''").assertTextAtCursorPosition("<=", 2, expect(new String[]{"<="}, getKeywordsAndStatics()));
	}
	
	@Test public void testOnStringLiteral_28() throws Exception {
		newBuilder().append("''.toString.toString").assertTextAtCursorPosition(".", expect(STRING_OPERATORS, CAST_INSTANCEOF));
	}
	
	@Test public void testOnStringLiteral_29() throws Exception {
		newBuilder().append("''.toString.toString.toString").assertTextAtCursorPosition("g.", 1, expect(STRING_OPERATORS, new String[]{"toString"}));
	}
	
	@Test public void testOnStringLiteral_30() throws Exception {
		newBuilder().append("('')").assertText(expect(STRING_OPERATORS, CAST_INSTANCEOF));
	}
	
	@Test public void testOnStringLiteral_31() throws Exception {
		newBuilder().append("('').").assertText(getStringFeatures());
	}
	
	@Test public void testOnStringLiteral_32() throws Exception {
		newBuilder().append("(''.toString)").assertText(expect(STRING_OPERATORS, CAST_INSTANCEOF));
	}
	
	@Test public void testOnStringLiteral_33() throws Exception {
		newBuilder().append("(''.toString )").assertTextAtCursorPosition(")", expect(STRING_OPERATORS, CAST_INSTANCEOF));
	}
	
	@Test public void testOnStringLiteral_34() throws Exception {
		newBuilder().append("''.toString ").assertText(expect(STRING_OPERATORS, CAST_INSTANCEOF));
	}

	@Test public void testOnStringLiteral_35() throws Exception {
		newBuilder().append("''.toString .").assertTextAtCursorPosition("g .", 2, expect(STRING_OPERATORS, CAST_INSTANCEOF));
	}
	
	@Test public void testOnStringLiteral_36() throws Exception {
		newBuilder().append("''.toString +''").assertTextAtCursorPosition("+", expect(STRING_OPERATORS, CAST_INSTANCEOF));
	}
	
	@Test public void testOnStringLiteral_37() throws Exception {
		newBuilder().append("''.toString ==''").assertTextAtCursorPosition("==", 1, expect(new String[] {"=="}, getKeywordsAndStatics()));
	}
	
	@Test public void testOnStringLiteral_38() throws Exception {
		newBuilder().append("''.toString.toString").assertTextAtCursorPosition("g.", 1, expect(STRING_OPERATORS, new String[]{"toString"}));
	}
	
	@Test public void testAfterBinaryOperation_01() throws Exception {
		newBuilder().append("''+''").assertText(expect(STRING_OPERATORS, CAST_INSTANCEOF));
	}
	
	@Test public void testAfterBinaryOperation_02() throws Exception {
		newBuilder().append("'' + ''+''").assertTextAtCursorPosition("''+", 2, expect(STRING_OPERATORS, CAST_INSTANCEOF));
	}
	
	@Test public void testAfterBinaryOperation_03() throws Exception {
		newBuilder().append("(''+'')").assertText(expect(STRING_OPERATORS, CAST_INSTANCEOF));
	}

	@Test public void testAfterBinaryOperation_04() throws Exception {
		newBuilder().append("(''+'').").assertText(getStringFeatures());
	}
	
	@Test public void testAfterBinaryOperation_05() throws Exception {
		newBuilder().append("((''+''))").assertText(expect(STRING_OPERATORS, CAST_INSTANCEOF));
	}
	
	@Test public void testAfterBinaryOperation_06() throws Exception {
		newBuilder().append("((''+''))").assertTextAtCursorPosition("))", 1, expect(STRING_OPERATORS, CAST_INSTANCEOF));
	}
	
	@Test public void testAfterBinaryOperation_07() throws Exception {
		newBuilder().append("((''+''))").assertTextAtCursorPosition("))", expect(STRING_OPERATORS, CAST_INSTANCEOF));
	}

	@Test public void testAfterBinaryOperation_08() throws Exception {
		newBuilder().append("((''+(''.bytes)))").assertTextAtCursorPosition(")", "==", "!=", "+=", "+", "->", "bytes");
	}
	
	@Test public void testAfterBinaryOperation_09() throws Exception {
		newBuilder().append("((''+''.bytes))").assertTextAtCursorPosition(")", "==", "!=", "+=", "+", "->", "bytes");
	}
	
	@Test public void testAfterBinaryOperation_10() throws Exception {
		newBuilder().append("((''+null))").assertTextAtCursorPosition(")", "null", "!=", "==", "->");
	}
	
	// TODO: limit to static features
	@Test public void testStaticFeatures_01() throws Exception {
		newBuilder().append("String::").assertText(getStaticStringFeatures());
	}
	
	@Test public void testNull() throws Exception {
		newBuilder().append("null").assertText("null", "!=", "==", "->");
	}
	
	@Test public void testForLoop_01() throws Exception {
		newBuilder().append("for (String s: null) ").assertText(expect(new String[]{"s"}, getKeywordsAndStatics()));
	}
	
	@Test public void testForLoop_02() throws Exception {
		newBuilder().append("for (String string: null) string").assertTextAtCursorPosition(") string", 6, "string");
	}
	
	@Test public void testForLoop_03() throws Exception {
		newBuilder().append("for (String string: null) ''+").assertText(expect(new String[] {"string", "+"}, getKeywordsAndStatics()));
	}
	
	@Test public void testForLoop_04() throws Exception {
		newBuilder().append("for (String string: ").assertText(getKeywordsAndStatics());
	}
	
	@Test public void testForLoop_05() throws Exception {
		newBuilder().append("for (String string: )").assertTextAtCursorPosition(")", getKeywordsAndStatics());
	}
	
	@Test public void testClosure_01() throws Exception {
		newBuilder().append("[String a, String b|").assertText(expect(new String[]{"a", "b"}, getKeywordsAndStatics(), new String[] {"val", "var"}));
	}
	
	@Test public void testCatchParameter_01() throws Exception {
		newBuilder().append("try {} catch(NullPointerException e) e").assertTextAtCursorPosition(") e", 2, expect(new String[]{"e"}, getKeywordsAndStatics()));
	}
	
	@Test public void testCatchParameter_02() throws Exception {
		newBuilder().append("try {} catch(NullPointerException e) ").assertText(expect(new String[]{"e"}, getKeywordsAndStatics()));
	}
	
	@Test public void testCamelCase_01() throws Exception {
		newBuilder().append("newLLis").assertText("newLinkedList()");
	}
	
	@Test public void testCamelCase_02() throws Exception {
		newBuilder().append("new ArrBloQu").assertText("java.util.concurrent.ArrayBlockingQueue");
	}
	
	protected ContentAssistProcessorTestBuilder newBuilder() throws Exception {
		return new ContentAssistProcessorTestBuilder(getInjector(), this);
	}
	
}
