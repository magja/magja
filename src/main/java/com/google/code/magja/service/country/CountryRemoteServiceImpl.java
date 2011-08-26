/**
 * @author andre
 *
 */
package com.google.code.magja.service.country;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.axis2.AxisFault;

import com.google.code.magja.magento.ResourcePath;
import com.google.code.magja.model.country.Country;
import com.google.code.magja.service.GeneralServiceImpl;
import com.google.code.magja.service.ServiceException;

public class CountryRemoteServiceImpl extends GeneralServiceImpl<Country>
		implements CountryRemoteService {

	private static final long serialVersionUID=1671845484676469453L;

	/*
	 * (non-Javadoc)
	 *
	 * @see com.google.code.magja.service.country.CountryRemoteService#list()
	 */
	@Override
	public List<Country> list() throws ServiceException {

		List<Country> countries = new ArrayList<Country>();

		List<Map<String, Object>> remote_list = null;
		try {
			remote_list = (List<Map<String, Object>>) soapClient.call(
					ResourcePath.CountryList, "");
		} catch (AxisFault e) {
			if(debug) e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

		if (remote_list == null)
			return countries;

		for (Map<String, Object> map : remote_list) {

			Country country = new Country();

			for (Map.Entry<String, Object> attr : map.entrySet())
				country.set(attr.getKey(), attr.getValue());

			countries.add(country);
		}

		return countries;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.google.code.magja.service.country.CountryRemoteService#getCountryByName()
	 */
	@Override
	public Country getCountryByName(String countryName) throws ServiceException {
		List<Country> countries = list();
		for(Country country : countries) {
			if(country.getName().equals(countryName)) {
				return country;
			}
		}

		// Country not found
		throw new ServiceException(countryName + " not found");
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.google.code.magja.service.country.CountryRemoteService#getCountryIdByName()
	 */
	@Override
	public String getCountryIdByName(String countryName) throws ServiceException {
		Country country = getCountryByName(countryName);

		return country.getCountryId();
	}
}
