/**
 *
 */
package com.google.code.magja.service.category;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.code.magja.model.category.Category;
import com.google.code.magja.service.RemoteServiceFactory;

/**
 * @author andre
 *
 */
public class CategoryRemoteServiceTest {

	private transient Logger log = LoggerFactory.getLogger(CategoryRemoteServiceTest.class);
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
    public void testGetByIdClean1() throws Exception {
        Category category = service.getByIdClean(new Integer(81));
        log.info("testGetByIdClean1 returns {}", category);
        Assert.assertNotNull(category);
        Assert.assertEquals((Integer)1, category.getId());
    }

    /**
     * Test method for {@link com.google.code.magja.service.category.CategoryRemoteServiceImpl#getByIdClean(java.lang.Integer)}.
     */
    @Test
    public void testGetByIdClean2() throws Exception {
        Category category = service.getByIdClean(new Integer(2));
        log.info("testGetByIdClean2 returns {}", category);
        Assert.assertNotNull(category);
        Assert.assertEquals((Integer)2, category.getId());
    }

    /**
     * Test method for {@link com.google.code.magja.service.category.CategoryRemoteServiceImpl#getByIdWithChildren(java.lang.Integer)}.
     */
    @Test
    public void testGetByIdWithChildren() throws Exception {
        Category category = service.getByIdWithChildren(new Integer(81));
        for (Category child : category.getChildren()) {
            assertTrue(child.getId() != null);
        }
    }

    /**
     * Test method for {@link com.google.code.magja.service.category.CategoryRemoteServiceImpl#getByIdWithParent(java.lang.Integer)}.
     */
    @Test
    public void testGetByIdWithParent() throws Exception {
        Category category = service.getByIdWithParent(new Integer(81));
        if (category != null) if (category.getParent() != null) System.out.println(category.getParent().toString());
    }

    /**
     * Test method for {@link com.google.code.magja.service.category.CategoryRemoteServiceImpl#getByIdWithParentAndChildren(java.lang.Integer)}.
     */
    @Test
    public void testGetByIdWithParentAndChildren() throws Exception {
        Category category = service.getByIdWithParentAndChildren(new Integer(81));
        if(category != null) {
            if (category.getParent() != null) System.out.println("parent: " + category.getParent().toString());
            if (category.getChildren() != null) {
                System.out.println("children: ");
                for (Category child : category.getChildren()) System.out.println(child.toString());
            }
        }
    }

	/**
	 * Test method for
	 * {@link com.google.code.magja.service.category.CategoryRemoteServiceImpl#getTree(java.lang.Integer)}.
	 */
	@Test
	public void testGetTree() throws Exception {
		Category category = service.getTree(new Integer(87));
		if(category != null) System.out.println(category.getName());
		log.debug("Category 87: {}", category);
	}
	
	/**
	 * Test method for search category
	 * {@link com.google.code.magja.service.category.CategoryRemoteServiceImpl#search(com.google.code.magja.model.category.Category, java.util.ArrayList)}
	 */
	@Test
	public void testSearch() throws Exception {
		Category category = service.getTree(new Integer(81));

		if (category != null) {
			if(category.getChildren() != null) {
				if(!category.getChildren().isEmpty()) {
					List<String> childrenNames = new ArrayList<String>();
					childrenNames.add(category.getChildren().get(0).getName());
		
					List<Category> categories = service.search(category, childrenNames);
		
					for (Category cat : categories) {
						System.out.println(cat.getId() + ":" + cat.getName());
					}
				}
			}
		}
	}
	
    /**
     * Test method for
     * {@link com.google.code.magja.service.category.CategoryRemoteServiceImpl#save(com.google.code.magja.model.category.Category)}
     * .
     */
    @Test
    public void testSave() throws Exception {
        Category category = service.getMinimalCategory(service.getDefaultParent().getId(), "Test Category 1");
        service.save(category);

        assertTrue(category.getId() != null);
        
        String newCategoryName = "Test Category 1 (updated)";
        category.setName(newCategoryName);
        service.save(category);
        Category reloadCategory = service.getByIdClean(category.getId());
        assertTrue(reloadCategory.getName().equals(newCategoryName));
    }

    /**
     * Test method for
     * {@link com.google.code.magja.service.category.CategoryRemoteServiceImpl#delete(java.lang.Integer)}
     */
    @Test
    public void testDelete() throws Exception {
        // first create some category
        Category category = service.getMinimalCategory(service.getDefaultParent().getId(), "Test Category 2");
        int categoryId = service.save(category);

        // then delete it by id
        service.delete(categoryId);
    }

    /**
     * Test method for
     * {@link com.google.code.magja.service.category.CategoryRemoteServiceImpl#deleteAllChildren(java.lang.Integer)}
     */
    @Test
    public void testDeleteAllChildren() throws Exception {
        service.deleteAllChildren(2);
    }

    /**
     * Test method for
     * {@link com.google.code.magja.service.category.CategoryRemoteServiceImpl#create(java.lang.Integer, java.util.ArrayList)}
     */
    @Test
    public void testCreate() throws Exception {
        Category parent = service.getDefaultParent();

		List<Category> categories = new ArrayList<Category>();
		categories.add(service.getMinimalCategory(0, "Category 1"));
/*
		categories.add(service.getRequiredCategory(0, "Sub Category 2", new String "name", "price"}, "name", true, true));
		categories.add(service.getRequiredCategory(0, "Under Sub Category 3", new String[] {"name"}, "name", true, true));
*/

		Category category = service.linkCategory(categories);
		
		service.create(parent.getId(), category);
    }
    
    /**
     * Test method for
     * {@link com.google.code.magja.service.category.CategoryRemoteServiceImpl#linkCategory(java.util.List)}
     */
    @Test
    public void testLinkCategory() throws Exception {
		List<Category> categories = new ArrayList<Category>();
		categories.add(service.getMinimalCategory(0, "cat1"));
//		categories.add(service.getRequiredCategory(0, "cat2", new String[] {"name", "price"}, "name", true, true));
		categories.add(service.getMinimalCategory(0, "cat3"));
    	
    	service.linkCategory(categories);
    }

	/**
	 * Test method for
	 * {@link com.google.code.magja.service.category.CategoryRemoteServiceImpl#listPaths()}.
	 */
	@Test
	public void testListPaths() throws Exception {
		Map<String, Category> paths = service.listPaths();
		log.info("Category paths: {}", paths.keySet());
		log.info("Category objects: {}", paths);
		Assert.assertNotNull(paths);
	}
	
}
