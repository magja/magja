package com.google.code.magja.service.country;

import java.util.List;

import com.google.code.magja.model.country.Country;
import com.google.code.magja.service.GeneralService;
import com.google.code.magja.service.ServiceException;

/**
 * Country service.
 * @author andre
 * @author Simon Zambrovski
 */
public interface CountryRemoteService extends GeneralService<Country> {

  /**
   * @return list of all countries
   * @throws ServiceException
   */
  List<Country> list() throws ServiceException;

  /**
   * @return get country by name
   * @throws ServiceException
   */
  Country getCountryByName(String countryName) throws ServiceException;

  /**
   * @return get country id by name
   * @throws ServiceException
   */
  String getCountryIdByName(String countryName) throws ServiceException;
}
