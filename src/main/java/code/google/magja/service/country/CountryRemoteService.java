/**
 *
 */
package code.google.magja.service.country;

import java.util.List;

import code.google.magja.model.country.Country;
import code.google.magja.service.GeneralService;
import code.google.magja.service.ServiceException;

/**
 * @author andre
 *
 */
public interface CountryRemoteService extends GeneralService<Country> {

	/**
	 * @return list of all countries
	 * @throws ServiceException
	 */
	public abstract List<Country> list() throws ServiceException;

}
