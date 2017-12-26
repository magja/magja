package net.magja.service.region;

import net.magja.model.region.Region;
import net.magja.service.GeneralService;
import net.magja.service.ServiceException;

import java.util.List;

/**
 * Region service.
 * @author andre
 * @author Simon Zambrovski
 */
public interface RegionRemoteService extends GeneralService<Region> {

  /**
   * Retrieves all regions for a given country.
   * @param countryId
   * @return list of all regions of the specified country id
   * @throws ServiceException
   */
  List<Region> list(String countryId) throws ServiceException;

}
