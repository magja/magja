package com.google.code.magja.service.region;

import java.util.List;

import com.google.code.magja.model.region.Region;
import com.google.code.magja.service.GeneralService;
import com.google.code.magja.service.ServiceException;

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
