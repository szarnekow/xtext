package org.eclipse.xtext.parsetree.reconstr;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.junit4.AbstractXtextTests;
import org.eclipse.xtext.resource.SaveOptions;
import org.junit.Test;

public class WhitespacePreservingCallbackTest extends AbstractXtextTests {
	@Override
	public void setUp() throws Exception {
		super.setUp();
		with(ComplexReconstrTestLanguageStandaloneSetup.class);
	}

	@Test public void testSimple() throws Exception {
		check("a");
	}

	@Test public void testHiddenInBetween() throws Exception {
		check("a \t /* foo bar */ + b");
	}


//	FIXME: Make this test work again

//	@Test public void testFail1() throws Exception {
//		IAstFactory f = getASTFactory();
//		failsWith(f.create("Add"), XtextSerializationException.class);
//	}

//	FIXME: Make this test work again

//	@Test public void testFail2() throws Exception {
//		IAstFactory f = getASTFactory();
//		EObject add = f.create("Add");
//
//		// one operand INVALID
//		EObject atom1 = f.create("Atom");
//		f.set(atom1, "name", "x");
//		f.add(add, "addOperands", atom1);
//		failsWith(add, XtextSerializationException.class);
//
//		// two operands VALID
//		EObject atom2 = f.create("Atom");
//		f.set(atom2, "name", "x");
//		f.add(add, "addOperands", atom2);
//		assertNotNull(serialize(add));
//
//		// three operands INVALID
//		EObject atom3 = f.create("Atom");
//		f.set(atom3, "name", "x");
//		f.add(add, "addOperands", atom3);
//		failsWith(add, XtextSerializationException.class);
//	}

	private void check(String m1) throws Exception {
		assertEquals(m1, parseAndSerialize(m1));
	}

	private String parseAndSerialize(String model) throws Exception {
		EObject result = getModel(model);
		return getSerializer().serialize(result, SaveOptions.defaultOptions());
	}

	@SuppressWarnings("unused")
	private void failsWith(EObject o, Class<? extends RuntimeException> clazz) {
		try {
			get(Serializer.class).serialize(o);
			fail("Should fail with "+clazz.getSimpleName());
		} catch (RuntimeException e) {
			if (!clazz.isInstance(e)) {
				throw e;
			}
		}
	}
}
