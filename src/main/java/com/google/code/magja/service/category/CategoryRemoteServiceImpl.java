/**
 *
 */
package code.google.magja.service.category;

import java.util.Map;

import org.apache.axis2.AxisFault;

import code.google.magja.magento.ResourcePath;
import code.google.magja.model.category.Category;
import code.google.magja.service.GeneralServiceImpl;
import code.google.magja.service.ServiceException;

/**
 * @author andre
 *
 */
public class CategoryRemoteServiceImpl extends GeneralServiceImpl<Category> implements CategoryRemoteService {

	/**
	 * Load children for the category
	 * @param category
	 * @throws ServiceException
	 */
	private void loadChildren(Category category) throws ServiceException {
		if(category.get("children") != null) {
			String str_children = (String) category.get("children");
			String[] arr_children = str_children.split(",");
			for (String str_child : arr_children) {
				Category child = getByIdClean(new Integer(str_child));
				if(child != null) category.addChild(child);
			}
		}
	}

	/**
	 * load parent for the category
	 * @param category
	 * @throws ServiceException
	 */
	private void loadParent(Category category) throws ServiceException {
		if(category.get("parent_id") != null) {
			Category parent = getByIdClean((Integer) category.get("parent_id"));
			category.setParent(parent);
		}
	}

	/* (non-Javadoc)
	 * @see code.google.magja.service.category.CategoryRemoteService#getByIdClean(java.lang.Integer)
	 */
	@Override
	public Category getByIdClean(Integer id) throws ServiceException {

		Category category = new Category();

		if(id == null) return null;

		Map<String, Object> cat;

		try {
			cat = (Map<String, Object>) soapClient.call(ResourcePath.CategoryInfo, id);
		} catch (AxisFault e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

		if(cat == null) return null;

		for (Map.Entry<String, Object> attribute : cat.entrySet())
			category.set(attribute.getKey(), attribute.getValue());

		return category;
	}

	/* (non-Javadoc)
	 * @see code.google.magja.service.category.CategoryRemoteService#getByIdWithChildren(java.lang.Integer)
	 */
	@Override
	public Category getByIdWithChildren(Integer id) throws ServiceException {

		Category category = getByIdClean(id);

		// load category children
		loadChildren(category);

		return category;
	}

	/* (non-Javadoc)
	 * @see code.google.magja.service.category.CategoryRemoteService#getByIdWithParent(java.lang.Integer)
	 */
	@Override
	public Category getByIdWithParent(Integer id) throws ServiceException {

		Category category = getByIdClean(id);

		// load category parent
		loadParent(category);

		return category;
	}

	/* (non-Javadoc)
	 * @see code.google.magja.service.category.CategoryRemoteService#getByIdWithParentAndChildren(java.lang.Integer)
	 */
	@Override
	public Category getByIdWithParentAndChildren(Integer id) throws ServiceException {

		Category category = getByIdClean(id);

		// load category parent and children
		loadChildren(category);
		loadParent(category);

		return category;
	}

}
