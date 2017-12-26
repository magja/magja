/**
 * @author andre
 *
 */
package net.magja.service.category;

import net.magja.model.category.CategoryAttribute;
import net.magja.service.GeneralService;
import net.magja.service.ServiceException;

import java.util.List;

public interface CategoryAttributeRemoteService extends GeneralService<CategoryAttribute> {

	/**
	 * List all categories attributes from a specific store view
	 * @param storeView
	 * @return List<CategoryAttribute>
	 * @throws ServiceException
	 */
	public abstract List<CategoryAttribute> listAll(String storeView) throws ServiceException;



}
