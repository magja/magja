/**
 *
 */
package com.google.code.magja.service.category;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.code.magja.model.category.Category;
import com.google.code.magja.service.RemoteServiceFactory;
import com.google.code.magja.service.ServiceException;
import com.google.code.magja.soap.MagentoSoapClient;

/**
 * Test for category service.
 * 
 * @author andre
 * @author Simon Zambrovski
 *
 */
public class CategoryRemoteServiceITest {

  private transient Logger log = LoggerFactory.getLogger(CategoryRemoteServiceITest.class);
  private CategoryRemoteService service;
  private Category category;
  private boolean deleted;

  @Before
  public void setUp() throws Exception {
    final RemoteServiceFactory remoteServiceFactory = new RemoteServiceFactory(MagentoSoapClient.getInstance());
    service = remoteServiceFactory.getCategoryRemoteService();
    deleted = false;
  }

  @After
  public void cleanUp() throws ServiceException {
    // remove again
    if (!deleted && category != null) {
      service.delete(category.getId());
      deleted = true;
    }
  }

  @Test
  public void testCreate() throws Exception {
    // retrieve root
    final Category parent = service.getDefaultParent();

    // create
    final List<Category> categories = new ArrayList<Category>();
    categories.add(service.getMinimalCategory(0, "Category 1"));
    category = service.linkCategory(categories);
    assertNotNull(category);

    // link with parent
    final List<Category> created = service.create(parent.getId(), category);
    assertFalse(created.isEmpty());
  }

  @Test
  public void testSave() throws Exception {
    category = service.getMinimalCategory(service.getDefaultParent().getId(), "Test Category 1");
    service.save(category);

    assertTrue(category.getId() != null);

    String newCategoryName = "Test Category 1 (updated)";
    category.setName(newCategoryName);
    service.save(category);

    Category reloadCategory = service.getByIdClean(category.getId());
    assertTrue(reloadCategory.getName().equals(newCategoryName));
  }

  @Test
  public void testDelete() throws Exception {
    // first create some category
    category = service.getMinimalCategory(service.getDefaultParent().getId(), "Test Category 2");
    int categoryId = service.save(category);

    // then delete it by id
    service.delete(categoryId);
    // avoid deletion in tear down
    deleted = true;
  }

  @Test
  public void testDeleteAllChildren() throws Exception {
    // first create some category
    category = service.getMinimalCategory(service.getDefaultParent().getId(), "Test Category 2");
    int categoryId = service.save(category);
    // save child
    Category child = service.getMinimalCategory(categoryId, "Child 1");
    service.save(child);
    // delete all children
    service.deleteAllChildren(categoryId);
  }

  @Test
  public void testLinkCategory() throws Exception {
    final List<Category> categories = new ArrayList<Category>();
    categories.add(service.getMinimalCategory(service.getDefaultParent().getId(), "cat1"));
    categories.add(service.getMinimalCategory(service.getDefaultParent().getId(), "cat3"));
    Category linkCategory = service.linkCategory(categories);
    assertNotNull(linkCategory);
  }

  @Test
  public void testGetByIdClean1() throws Exception {
    // first create some category
    category = service.getMinimalCategory(service.getDefaultParent().getId(), "Test Category 2");
    int categoryId = service.save(category);

    Category category = service.getByIdClean(categoryId);
    log.info("testGetByIdClean1 returns {}", category);
    assertNotNull(category);
    assertEquals(Integer.valueOf(categoryId), category.getId());
  }

  @Test
  public void testGetByIdWithChildren() throws Exception {
    // first create some category
    category = service.getMinimalCategory(service.getDefaultParent().getId(), "Test Category 2");
    service.save(category);

    Category category = service.getByIdWithChildren(new Integer(service.getDefaultParent().getId()));
    for (Category child : category.getChildren()) {
      assertNotNull(child.getId());
    }
  }

  @Test
  public void testGetByIdWithParent() throws Exception {
    category = service.getMinimalCategory(service.getDefaultParent().getId(), "Test Category 2");
    int save = service.save(category);
    Category category = service.getByIdWithParent(save);
    if (category != null) {
      if (category.getParent() != null) {
        assertEquals(service.getDefaultParent().getId(), category.getParent().getId());
      } else {
        fail();
      }
    } else {
      fail();
    }

  }

  @Test
  public void testGetByIdWithParentAndChildren() throws Exception {
    category = service.getMinimalCategory(service.getDefaultParent().getId(), "Test Category 2");
    int save = service.save(category);
    Category category = service.getMinimalCategory(save, "Child 1");
    service.save(category);

    category = service.getByIdWithParentAndChildren(save);
    if (category != null) {
      if (category.getParent() != null) {
        log.info("parent: {}", category.getParent().toString());
      } else {
        fail();
      }
      if (category.getChildren() != null) {
        log.info("children: ");
        for (Category child : category.getChildren()) {
          log.info("child {}", child.toString());
        }
      } else {
        fail();
      }
    } else {
      fail();
    }
  }

  @Test
  public void testGetTree() throws Exception {
    category = service.getMinimalCategory(service.getDefaultParent().getId(), "Test Category 2");
    service.save(category);

    Category category = service.getTree(service.getDefaultParent().getId());
    if (category != null) {
      log.info("{}", category.getName());
    } else {
      fail();
    }
  }

  @Test
  public void testSearch() throws Exception {
    category = service.getMinimalCategory(service.getDefaultParent().getId(), "Test Category 2");
    service.save(category);

    Category category = service.getTree(service.getDefaultParent().getId());
    if (category != null) {
      if (category.getChildren() != null) {
        if (!category.getChildren().isEmpty()) {
          List<String> childrenNames = new ArrayList<String>();
          childrenNames.add(category.getChildren().get(0).getName());
          List<Category> categories = service.search(category, childrenNames);
          for (Category cat : categories) {
            log.info("{}: {} ", cat.getId(), cat.getName());
          }
        } else {
          fail();
        }
      } else {
        fail();
      }
    } else {
      fail();
    }
  }

  // FIXME: check why does it happen
  @Ignore("Invalid path error")
  @Test
  public void testListPaths() throws Exception {
    category = service.getMinimalCategory(service.getDefaultParent().getId(), "Test Category 2");
    service.save(category);

    Map<String, Category> paths = service.listPaths();
    log.info("Category paths: {}", paths.keySet());
    log.info("Category objects: {}", paths);
    assertNotNull(paths);
  }

}
