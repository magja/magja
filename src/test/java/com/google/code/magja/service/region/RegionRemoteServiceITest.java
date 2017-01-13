package com.google.code.magja.service.region;

import static org.junit.Assert.assertFalse;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.code.magja.model.region.Region;
import com.google.code.magja.service.RemoteServiceFactory;
import com.google.code.magja.service.ServiceException;
import com.google.code.magja.soap.MagentoSoapClient;

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
