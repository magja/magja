package com.google.code.magja.soap;

import java.io.Serializable;
import java.util.List;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import junit.framework.Assert;

import org.apache.axiom.om.OMElement;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterators;

/**
 * Test {@link SoapCallFactory}.
 * @author ceefour
 */
public class SoapCallFactoryTest {

	private transient Logger log = LoggerFactory.getLogger(SoapCallFactoryTest.class);
	private SoapCallFactory soapCallFactory;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		soapCallFactory = new SoapCallFactory();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.google.code.magja.soap.SoapCallFactory#createCall(java.lang.String, java.lang.String, java.lang.Object)}.
	 */
	@Test
	public void createCallList() {
		List<Serializable> args = ImmutableList.of(
				2,
				ImmutableMap.of("name", "Bandana", "description", "Bandana keren untuk penampilan Anda") );
		OMElement element = soapCallFactory.createCall("abc", "catalog_category.create", args);
		log.info("createCallList {}", element);
		Assert.assertNotNull(element);
		Assert.assertEquals(3, Iterators.size(element.getChildElements()));
	}

	/**
	 * Test method for {@link com.google.code.magja.soap.SoapCallFactory#createCall(java.lang.String, java.lang.String, java.lang.Object)}.
	 */
	@Test
	public void createCallArray() {
		Serializable[] args = new Serializable[] {
				2,
				ImmutableMap.of("name", "Bandana", "description", "Bandana keren untuk penampilan Anda") };
		OMElement element = soapCallFactory.createCall("abc", "catalog_category.create", args);
		log.info("createCallArray {}", element);
		Assert.assertNotNull(element);
		Assert.assertEquals(3, Iterators.size(element.getChildElements()));
	}

	/**
	 * Test method for {@link com.google.code.magja.soap.SoapCallFactory#createCall(java.lang.String, java.lang.String, java.lang.Object)}.
	 * @throws ScriptException 
	 */
	@Test
	public void createCallJavascriptArray() throws ScriptException {
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine scriptEngine = manager.getEngineByName("js");
		Object args = scriptEngine.eval("[ 2, {'name': 'Bandana', 'description': 'Bandana keren untuk penampilan Anda'} ]");
		OMElement element = soapCallFactory.createCall("abc", "catalog_category.create", args);
		log.info("createCallJavascriptArray {}", element);
		Assert.assertNotNull(element);
		Assert.assertEquals(3, Iterators.size(element.getChildElements()));
	}

	/**
	 * Test method for {@link com.google.code.magja.soap.SoapCallFactory#createCall(java.lang.String, java.lang.String, java.lang.Object)}.
	 * @throws ScriptException 
	 */
	@Test
	public void createCallJavascriptIntArray() throws ScriptException {
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine scriptEngine = manager.getEngineByName("js");
		Object args = scriptEngine.eval("[ 2, 5, 9 ]");
		OMElement element = soapCallFactory.createCall("abc", "catalog_category.create", args);
		log.info("createCallJavascriptIntArray {}", element);
		Assert.assertNotNull(element);
		Assert.assertEquals(3, Iterators.size(element.getChildElements()));
	}

}
