package com.google.code.magja.soap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.apache.axiom.om.OMElement;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.code.magja.magento.ResourcePath;
import com.google.code.magja.model.product.ProductUpdatePrice;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterators;

/**
 * Test {@link SoapCallFactory}.
 * 
 * @author ceefour
 */
public class SoapCallFactoryTest {

  private final static Logger log = LoggerFactory.getLogger(SoapCallFactoryTest.class);
  private SoapCallFactory soapCallFactory;

  @Before
  public void setUp() throws Exception {
    soapCallFactory = new SoapCallFactory();
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void createCallList() {
    List<Serializable> args = ImmutableList.of(2, ImmutableMap.of("name", "Bandana", "description", "Bandana keren untuk penampilan Anda"));
    OMElement element = soapCallFactory.createCall("abc", "catalog_category.create", args);
    log.info("createCallList {}", element);
    assertNotNull(element);
    assertEquals(3, Iterators.size(element.getChildElements()));
  }

  @Test
  public void createCallArray() {
    Serializable[] args = new Serializable[] { 2, ImmutableMap.of("name", "Bandana", "description", "Bandana keren untuk penampilan Anda") };
    OMElement element = soapCallFactory.createCall("abc", "catalog_category.create", args);
    log.info("createCallArray {}", element);
    assertNotNull(element);
    assertEquals(3, Iterators.size(element.getChildElements()));
  }

  @Test
  public void createCallJavascriptArray() throws ScriptException {
    ScriptEngineManager manager = new ScriptEngineManager();
    ScriptEngine scriptEngine = manager.getEngineByName("js");
    Object args = scriptEngine.eval("[ 2, {'name': 'Bandana', 'description': 'Bandana keren untuk penampilan Anda'} ]");
    OMElement element = soapCallFactory.createCall("abc", "catalog_category.create", args);
    log.info("createCallJavascriptArray {}", element);
    assertNotNull(element);
    assertEquals(3, Iterators.size(element.getChildElements()));
  }

  @Test
  public void createCallJavascriptIntArray() throws ScriptException {
    ScriptEngineManager manager = new ScriptEngineManager();
    ScriptEngine scriptEngine = manager.getEngineByName("js");
    Object args = scriptEngine.eval("[ 2, 5, 9 ]");
    OMElement element = soapCallFactory.createCall("abc", "catalog_category.create", args);
    log.info("createCallJavascriptIntArray {}", element);
    assertNotNull(element);
    assertEquals(3, Iterators.size(element.getChildElements()));
  }

  @Test
  @Ignore("Assertion failing")
  public void createCallProductUpdatePrice() throws ScriptException {
    ImmutableList<ProductUpdatePrice> products = ImmutableList.of(new ProductUpdatePrice("ajah_a", new BigDecimal(500000), new BigDecimal(600000)),
        new ProductUpdatePrice("zibalabel_b_06", new BigDecimal(500000), new BigDecimal(600000)));
    OMElement element = soapCallFactory.createCall("abc", ResourcePath.ProductUpdatePrice.getPath(), new Object[] { products });
    log.info("createCallProductUpdatePrice {}", element);
    assertNotNull(element);
    assertEquals(1, Iterators.size(element.getChildElements()));
  }

}
