/**
 *
 */
package com.google.code.magja.service.region;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.google.code.magja.model.region.Region;
import com.google.code.magja.service.RemoteServiceFactory;
import com.google.code.magja.service.ServiceException;

/**
 * @author andre
 *
 */
public class RegionRemoteServiceTest {

	private RegionRemoteService service;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		service = RemoteServiceFactory.getRegionRemoteService();
	}

	/**
	 * Test method for
	 * {@link com.google.code.magja.service.region.RegionRemoteServiceImpl#list(java.lang.String)}
	 * .
	 */
	@Test
	public void testList() {

		try {
			List<Region> regions = service.list("US");

			for (Region region : regions)
				System.out.println(region.toString());

		} catch (ServiceException e) {
			fail(e.getMessage());
		}
	}

}
