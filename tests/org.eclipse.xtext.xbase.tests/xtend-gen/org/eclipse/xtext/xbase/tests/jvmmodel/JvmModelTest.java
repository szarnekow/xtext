package org.eclipse.xtext.xbase.tests.jvmmodel;

import com.google.inject.Inject;
import java.lang.reflect.Field;
import java.util.ArrayList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.junit4.validation.ValidationTestHelper;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.resource.IResourceDescription;
import org.eclipse.xtext.resource.IResourceDescription.Manager;
import org.eclipse.xtext.xbase.XExpression;
import org.eclipse.xtext.xbase.XbasePackage.Literals;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.tests.jvmmodel.AbstractJvmModelTest;
import org.eclipse.xtext.xbase.validation.IssueCodes;
import org.junit.Assert;
import org.junit.Test;

@SuppressWarnings("all")
public class JvmModelTest extends AbstractJvmModelTest {
  @Inject
  private ValidationTestHelper helper;
  
  @Inject
  private Manager manager;
  
  @Test
  public void testSimple() {
    try {
      {
        XExpression _expression = this.expression("return s.toUpperCase", false);
        final XExpression expression = _expression;
        Resource _eResource = expression.eResource();
        EList<EObject> _contents = _eResource.getContents();
        int _size = _contents.size();
        Assert.assertEquals(2, _size);
        this.helper.assertNoErrors(expression);
      }
    } catch (Exception _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testResourceDescriptionsAreCorrect() {
    try {
      {
        Resource _newResource = this.newResource("return s.toUpperCase");
        final Resource resource = _newResource;
        Field _declaredField = org.eclipse.xtext.resource.DerivedStateAwareResource.class.getDeclaredField("fullyInitialized");
        final Field field = _declaredField;
        field.setAccessible(true);
        Object _get = field.get(resource);
        Assert.assertFalse((((Boolean) _get)).booleanValue());
        IResourceDescription _resourceDescription = this.manager.getResourceDescription(resource);
        final IResourceDescription desc = _resourceDescription;
        Iterable<IEObjectDescription> _exportedObjects = desc.getExportedObjects();
        ArrayList<Iterable<IEObjectDescription>> _newArrayList = CollectionLiterals.<Iterable<IEObjectDescription>>newArrayList(_exportedObjects);
        final ArrayList<Iterable<IEObjectDescription>> list = _newArrayList;
        int _size = list.size();
        Assert.assertEquals(1, _size);
        Object _get_1 = field.get(resource);
        Assert.assertFalse((((Boolean) _get_1)).booleanValue());
      }
    } catch (Exception _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testReturnTypeConformance() {
    try {
      {
        XExpression _expression = this.expression("return");
        final XExpression expression = _expression;
        this.helper.assertError(expression, Literals.XRETURN_EXPRESSION, IssueCodes.INVALID_RETURN);
      }
    } catch (Exception _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
