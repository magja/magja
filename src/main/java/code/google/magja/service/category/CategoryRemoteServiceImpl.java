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

		// TODO load category children

		return category;
	}

	/* (non-Javadoc)
	 * @see code.google.magja.service.category.CategoryRemoteService#getByIdWithParent(java.lang.Integer)
	 */
	@Override
	public Category getByIdWithParent(Integer id) throws ServiceException {

		Category category = getByIdClean(id);

		// TODO load category parent

		return category;
	}

	/* (non-Javadoc)
	 * @see code.google.magja.service.category.CategoryRemoteService#getByIdWithParentAndChildren(java.lang.Integer)
	 */
	@Override
	public Category getByIdWithParentAndChildren(Integer id) throws ServiceException {

		Category category = getByIdClean(id);

		// TODO load category parent and children

		return category;
	}

}
