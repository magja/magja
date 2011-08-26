/**
 * @author andre
 *
 */
package com.google.code.magja.service.region;

import java.util.List;

import com.google.code.magja.model.region.Region;
import com.google.code.magja.service.GeneralService;
import com.google.code.magja.service.ServiceException;

public interface RegionRemoteService extends GeneralService<Region> {

	/**
	 * @param countryId
	 * @return list of all regions of the specified country id
	 * @throws ServiceException
	 */
	public abstract List<Region> list(String countryId) throws ServiceException;

}
