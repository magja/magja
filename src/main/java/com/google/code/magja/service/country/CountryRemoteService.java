/**
 * @author andre
 *
 */
package com.google.code.magja.service.country;

import java.util.List;

import com.google.code.magja.model.country.Country;
import com.google.code.magja.service.GeneralService;
import com.google.code.magja.service.ServiceException;

public interface CountryRemoteService extends GeneralService<Country> {

	/**
	 * @return list of all countries
	 * @throws ServiceException
	 */
	public abstract List<Country> list() throws ServiceException;

	/**
	 * @return get country by name
	 * @throws ServiceException
	 */
	public abstract Country getCountryByName(String countryName) throws ServiceException;

	/**
	 * @return get country id by name
	 * @throws ServiceException
	 */
	public abstract String getCountryIdByName(String countryName) throws ServiceException;
}
