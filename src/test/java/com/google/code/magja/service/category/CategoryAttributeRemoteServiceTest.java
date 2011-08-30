/**
 *
 */
package com.google.code.magja.service.category;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.google.code.magja.model.category.CategoryAttribute;
import com.google.code.magja.service.RemoteServiceFactory;
import com.google.code.magja.service.ServiceException;

/**
 * @author andre
 *
 */
public class CategoryAttributeRemoteServiceTest {

	private CategoryAttributeRemoteService service;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		service = RemoteServiceFactory.getCategoryAttributeRemoteService();
	}

	/**
	 * Test method for {@link com.google.code.magja.service.category.CategoryAttributeRemoteServiceImpl#listAll(java.lang.String)}.
	 */
	@Test
	public void testListAll() {
		try {
			List<CategoryAttribute> attr = service.listAll("default");

			for (CategoryAttribute categoryAttribute : attr)
				System.out.println(categoryAttribute.toString());

		} catch (ServiceException e) {
			fail(e.getMessage());
		}
	}

}
