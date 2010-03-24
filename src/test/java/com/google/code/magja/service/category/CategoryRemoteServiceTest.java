/**
 *
 */
package com.google.code.magja.service.category;

import org.junit.Before;
import org.junit.Test;

import com.google.code.magja.model.category.Category;
import com.google.code.magja.service.RemoteServiceFactory;

/**
 * @author andre
 *
 */
public class CategoryRemoteServiceTest {

	private CategoryRemoteService service;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		service = RemoteServiceFactory.getCategoryRemoteService();
	}

	/**
	 * Test method for {@link com.google.code.magja.service.category.CategoryRemoteServiceImpl#getByIdClean(java.lang.Integer)}.
	 */
	@Test
	public void testGetByIdClean() throws Exception {
		Category category = service.getByIdClean(new Integer(2));
		if(category != null) System.out.println(category.toString());
	}

	/**
	 * Test method for {@link com.google.code.magja.service.category.CategoryRemoteServiceImpl#getByIdWithChildren(java.lang.Integer)}.
	 */
	@Test
	public void testGetByIdWithChildren() throws Exception {
		Category category = service.getByIdWithChildren(new Integer(2));
		for (Category child : category.getChildren()) {
			System.out.println(child.toString());
		}
	}

	/**
	 * Test method for {@link com.google.code.magja.service.category.CategoryRemoteServiceImpl#getByIdWithParent(java.lang.Integer)}.
	 */
	@Test
	public void testGetByIdWithParent() throws Exception {
		Category category = service.getByIdWithParent(new Integer(2));
		if(category != null) System.out.println(category.toString());
	}

	/**
	 * Test method for {@link com.google.code.magja.service.category.CategoryRemoteServiceImpl#getByIdWithParentAndChildren(java.lang.Integer)}.
	 */
	@Test
	public void testGetByIdWithParentAndChildren() throws Exception {
		Category category = service.getByIdWithParentAndChildren(new Integer(2));
		if(category != null) System.out.println(category.toString());
	}

}
