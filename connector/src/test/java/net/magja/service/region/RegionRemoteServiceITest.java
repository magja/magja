package net.magja.service.region;

import static org.junit.Assert.assertFalse;

import java.util.List;

import net.magja.soap.MagentoSoapClient;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.magja.model.region.Region;
import net.magja.service.RemoteServiceFactory;
import net.magja.service.ServiceException;
import net.magja.soap.MagentoSoapClient;

/**
 * Test for region service.
 *
 * @author andre
 * @author Simon Zambrovski
 */
public class RegionRemoteServiceITest {

  private final static Logger log = LoggerFactory.getLogger(RegionRemoteServiceITest.class);

  private RegionRemoteService service;

  @Before
  public void setUp() throws Exception {
    final RemoteServiceFactory remoteServiceFactory = new RemoteServiceFactory(MagentoSoapClient.getInstance());
    service = remoteServiceFactory.getRegionRemoteService();
  }

  @Test
  public void testList() throws ServiceException {

    final List<Region> regions = service.list("US");
    for (Region region : regions) {
      log.info("{}", region);
    }
    assertFalse(regions.isEmpty());
  }

}
