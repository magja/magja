/**
 * based on source code from k5
 * http://www.magentocommerce.com/boards/viewthread/37982/
 *
 * @author Pawel Konczalski <mail@konczalski.de>
 *
 * You are free to use it under the terms of the GNU General Public License
 */

/*
 * Category API
 * http://www.magentocommerce.com/wiki/doc/webservices-api/api/catalog_category
 */
package code.google.magja.magento.category;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import code.google.magja.magento.Connection;
import code.google.magja.magento.ResourcePath;
import code.google.magja.magento.Utils;


public class Category extends Connection {

	/*
	 * constructor
	 */
	public Category() {
		super();
	}

	/*
	 * create category
	 */
	public int create(int parentId, String categoryName) {
		return create(parentId, new String[] { categoryName });
	}

	/*
	 * create category from String array Similar sub category will by filtered
	 * by default
	 */
	public int create(int parentId, String[] categoryNames) {
		return create(parentId, categoryNames, true);
	}

	/*
	 * create category from String array
	 */
	public int create(int parentId, String[] categoryNames, boolean filterSimilar) {
		CategoryProperties categoryProperties;

		String previousCategoryName = "";
		for (int i = 0; i < categoryNames.length; i++) {
			if (filterSimilar) {
				if (previousCategoryName.equals(categoryNames[i])) {
					categoryNames[i] = "";
				}
			}

			if (categoryNames[i].length() > 0) {
				categoryProperties = new CategoryProperties(categoryNames[i]);

				categoryProperties = new CategoryProperties();
				categoryProperties.setName(categoryNames[i]);
				categoryProperties.setAvailable_sort_by("name");
				categoryProperties.setDefault_sort_by("name");
				categoryProperties.setIs_active(1);
				categoryProperties.setIs_anchor(1);

				parentId = create(parentId, categoryProperties);

				previousCategoryName = categoryNames[i];
			}
		}

		return parentId;
	}

	/*
	 * create category
	 */
	public int create(int parentId, CategoryProperties mcp) {
		// examining whether category already exists
		int childernId = getChildrenId(parentId, mcp.getName());
		if (childernId > -1) {
			update(childernId, mcp);

			return childernId;
		} else {
			// create category
			List<Object> newCategory = new LinkedList<Object>();
			newCategory.add(parentId);
			newCategory.add(mcp.getProperties());

			// create category
			try {
				int newCategoryId = Integer.parseInt((String) client.call(ResourcePath.CategoryCreate, newCategory));

				return newCategoryId;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			return -1;
		}
	}

	/**
	 * Assign product to category Create new product and return product id
	 *
	 * @param categoryId
	 *            category ID
	 * @param itemSku
	 *            product SKU
	 * @return boolean
	 */
	public boolean assignProduct(int categoryId, String itemSku) {
		List<Object> list = new LinkedList<Object>();
		list.add(categoryId);
		list.add(itemSku);

		try {
			return (Boolean) client.call(ResourcePath.CategoryAssignProduct, list);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return false;
	}

	/*
	 * delete sub category by name
	 */
	public boolean delete(int parentId, String categoryName) {
		int childernId = getChildrenId(parentId, categoryName);

		return delete(parentId, childernId);
	}

	/*
	 * delete sub category by id
	 */
	public boolean delete(int parentId, int childernId) {
		List<Object> category = new LinkedList<Object>();
		category.add(childernId);

		try {
			return (Boolean) client.call(ResourcePath.CategoryDelete, category);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return false;
	}

	/*
	 * delete all sub categorys
	 */
	public void deleteAll(int parentId) {
		int[] childrenIds = getAllChildrenIds(parentId);

		for (int i = 0; i < childrenIds.length; i++) {
			delete(parentId, childrenIds[i]);
		}

	}

	/*
	 * update category properties
	 */
	public void update(int categoryId, CategoryProperties properties) {
		/*
		 * ToDo: write updateCategory function
		 */
	}

	/*
	 * get category info
	 */
	public String getInfo(int categoryId) {
		try {
			Map<String, Object> category = (Map<String, Object>) client.call(ResourcePath.CategoryInfo, categoryId);

			return Utils.dump(category);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return "";
	}

	/*
	 * get category tree info
	 */
	public String getTree(int categoryId) {
		try {
			Map<String, Object> categoryTree = (Map<String, Object>) client.call(ResourcePath.CategoryTree, categoryId);

			return Utils.dump(categoryTree);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return "";
	}

	/*
	 * get sub category id from name
	 */
	public int getChildrenId(int categoryId, String categoryName) {
		try {
			Map<String, Object> categoryTree = (Map<String, Object>) client.call(ResourcePath.CategoryTree, categoryId);
			List<Object> newCategory = (LinkedList<Object>) categoryTree.get("children");

			for (int i = 0; i < newCategory.size(); i++) {
				Map<String, Object> catProperties = (HashMap) newCategory.get(i);
				if (catProperties.get("name").equals(categoryName)) {
					return Integer.parseInt((String) catProperties.get("category_id"));
				}

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return -1;
	}

	/*
	 * get all sub category ids
	 */
	public int[] getAllChildrenIds(int categoryId) {
		try {
			Map<String, Object> categoryTree = (Map<String, Object>) client.call(ResourcePath.CategoryTree, categoryId);

			List<Object> newCategory = (LinkedList<Object>) categoryTree.get("children");

			int[] childrenIds = new int[newCategory.size()];
			for (int i = 0; i < newCategory.size(); i++) {
				Map<String, Object> catProperties = (HashMap) newCategory.get(i);
				childrenIds[i] = Integer.parseInt((String) catProperties.get("category_id"));
			}

			return childrenIds;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return new int[] {};
	}

	/*
	 * main
	 */
	public static void main(String[] args) {
		// login
		Category c = new Category();

		// example, get category tree info
		String categoryTree = c.getTree(2);
		System.out.println("*** DEBUG *** getTree:" + categoryTree);

		// example, get category info
		String categoryInfo = c.getInfo(2);
		System.out.println("*** DEBUG *** getInfo:" + categoryInfo);

		// example, create category
		CategoryProperties cp = new CategoryProperties();
		cp.setName("testName3");
		cp.setDescription("categories_description");
		cp.setMeta_description("categories_description 1234");
		cp.setMeta_keywords("categories meta aa bb cc");
		cp.setAvailable_sort_by("position");
		cp.setDefault_sort_by("position");
		cp.setIs_active(1);
		int categoryId = c.create(2, cp);
		System.out.println("*** DEBUG *** newCategoryId:" + categoryId);

		// example, delete category
		boolean state = c.delete(4, "Phones");
		System.out.println("*** DEBUG *** deleteCategory:" + state);

	}
}
