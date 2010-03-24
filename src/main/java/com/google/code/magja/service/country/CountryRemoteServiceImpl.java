/**
 *
 */
package code.google.magja.service.country;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.axis2.AxisFault;

import code.google.magja.magento.ResourcePath;
import code.google.magja.model.country.Country;
import code.google.magja.service.GeneralServiceImpl;
import code.google.magja.service.ServiceException;

/**
 * @author andre
 *
 */
public class CountryRemoteServiceImpl extends GeneralServiceImpl<Country>
		implements CountryRemoteService {

	/*
	 * (non-Javadoc)
	 *
	 * @see code.google.magja.service.country.CountryRemoteService#list()
	 */
	@Override
	public List<Country> list() throws ServiceException {

		List<Country> countries = new ArrayList<Country>();

		List<Map<String, Object>> remote_list = null;
		try {
			remote_list = (List<Map<String, Object>>) soapClient.call(
					ResourcePath.CountryList, "");
		} catch (AxisFault e) {
			e.printStackTrace();
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

}
