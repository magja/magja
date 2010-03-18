/**
 *
 */
package code.google.magja.service.category;

import java.util.List;

import code.google.magja.model.category.CategoryAttribute;
import code.google.magja.service.GeneralService;
import code.google.magja.service.ServiceException;

/**
 * @author andre
 *
 */
public interface CategoryAttributeRemoteService extends GeneralService<CategoryAttribute> {

	/**
	 * List all categories attributes from a specific store view
	 * @param storeView
	 * @return List<CategoryAttribute>
	 * @throws ServiceException
	 */
	public abstract List<CategoryAttribute> listAll(String storeView) throws ServiceException;



}
