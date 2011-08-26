/**
 * @author andre
 *
 */
package com.google.code.magja.service.category;

import java.util.List;

import com.google.code.magja.model.category.CategoryAttribute;
import com.google.code.magja.service.GeneralService;
import com.google.code.magja.service.ServiceException;

public interface CategoryAttributeRemoteService extends GeneralService<CategoryAttribute> {

	/**
	 * List all categories attributes from a specific store view
	 * @param storeView
	 * @return List<CategoryAttribute>
	 * @throws ServiceException
	 */
	public abstract List<CategoryAttribute> listAll(String storeView) throws ServiceException;



}
