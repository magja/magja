/**
 *
 */
package com.google.code.magja.service.country;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.google.code.magja.model.country.Country;
import com.google.code.magja.service.RemoteServiceFactory;
import com.google.code.magja.service.ServiceException;

/**
 * @author andre
 *
 */
public class CountryRemoteServiceTest {

	private CountryRemoteService service;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		service = RemoteServiceFactory.getCountryRemoteService();
	}

	/**
	 * Test method for
	 * {@link com.google.code.magja.service.country.CountryRemoteServiceImpl#list()}
	 * .
	 */
	@Test
	public void testList() {

		try {
			List<Country> countries = service.list();

			for (Country country : countries)
				System.out.println(country.toString());

		} catch (ServiceException e) {
			fail(e.getMessage());
		}

	}

}
