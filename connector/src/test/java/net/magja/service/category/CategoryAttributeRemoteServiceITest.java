/**
 *
 */
package net.magja.service.category;

import static org.junit.Assert.assertFalse;

import java.util.List;

import net.magja.soap.MagentoSoapClient;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.magja.model.category.CategoryAttribute;
import net.magja.service.RemoteServiceFactory;
import net.magja.service.ServiceException;
import net.magja.soap.MagentoSoapClient;

/**
 * Test fo rcategory attribute service.
 *
 * @author andre
 */
public class CategoryAttributeRemoteServiceITest {

  private CategoryAttributeRemoteService service;
  private final static Logger log = LoggerFactory.getLogger(CategoryAttributeRemoteServiceITest.class);

  @Before
  public void setUp() throws Exception {
    final RemoteServiceFactory remoteServiceFactory = new RemoteServiceFactory(MagentoSoapClient.getInstance());
    service = remoteServiceFactory.getCategoryAttributeRemoteService();
  }

  @Test
  public void testListAll() throws ServiceException {
    final List<CategoryAttribute> attr = service.listAll("default");
    for (final CategoryAttribute ca : attr) {
      log.info("Attribute {}", ca);
    }
    assertFalse(attr.isEmpty());
  }

}
