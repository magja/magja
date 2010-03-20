/**
 *
 */
package code.google.magja.service.region;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import code.google.magja.model.region.Region;
import code.google.magja.service.RemoteServiceFactory;
import code.google.magja.service.ServiceException;

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
	 * {@link code.google.magja.service.region.RegionRemoteServiceImpl#list(java.lang.String)}
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
