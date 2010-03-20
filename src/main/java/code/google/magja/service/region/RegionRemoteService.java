/**
 *
 */
package code.google.magja.service.region;

import java.util.List;

import code.google.magja.model.region.Region;
import code.google.magja.service.GeneralService;
import code.google.magja.service.ServiceException;

/**
 * @author andre
 *
 */
public interface RegionRemoteService extends GeneralService<Region> {

	/**
	 * @param countryId
	 * @return list of all regions of the specified country id
	 * @throws ServiceException
	 */
	public abstract List<Region> list(String countryId) throws ServiceException;

}
