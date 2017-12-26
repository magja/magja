package net.magja.service.order;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import net.magja.soap.MagentoSoapClient;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.magja.model.order.Shipment;
import net.magja.model.order.ShipmentItem;
import net.magja.model.order.ShipmentTrack;
import net.magja.service.RemoteServiceFactory;
import net.magja.service.ServiceException;
import net.magja.soap.MagentoSoapClient;

/**
 * Shipment Service Test.
 *
 * @author andre
 * @author Simon Zambrovski
 */
public class ShipmentRemoteServiceITest {

  private final static Logger log = LoggerFactory.getLogger(ShipmentRemoteServiceITest.class);
  private ShipmentRemoteService service;

  @Before
  public void setUp() throws Exception {
    final RemoteServiceFactory remoteServiceFactory = new RemoteServiceFactory(MagentoSoapClient.getInstance());
    service = remoteServiceFactory.getShipmentRemoteService();
  }

  @Ignore
  @Test
  public void testAddComment() {
    // TODO
    fail("Not yet implemented");
  }

  @Test
  public void testAddTrack() throws ServiceException {
    Shipment shipment = service.getById(100000005);

    ShipmentTrack shipmentTrack = new ShipmentTrack();
    shipmentTrack.setCarrier("custom");
    shipmentTrack.setTitle("JNE");
    shipmentTrack.setNumber("1234");
    service.addTrack(shipment, shipmentTrack);
  }

  @Test
  public void testGetById() throws ServiceException {
    Shipment shipment = service.getById(100000005);
    log.debug("Shipment: {}", shipment);
  }

  @Ignore
  @Test
  public void testList() {
    // TODO
    fail("Not yet implemented");
  }

  @Ignore
  @Test
  public void testRemoveTrack() {
    // TODO
    fail("Not yet implemented");
  }

  @Test
  public void save() throws ServiceException {
    Shipment shipment = new Shipment();
    shipment.setCustomerId(3);
    // shipment.setId(1);

    final List<ShipmentItem> shipmentItems = new ArrayList<ShipmentItem>();
    shipmentItems.add(new ShipmentItem("obatiks_isabella-accessory-42", "Isabella Accessory 42", 18, 1070, 10.0, 11000.0, 1.0));
    // shipmentItems.add(new ShipmentItem("zibalabel_t14", "Bag T14", 105, 200,
    // 3.0, 1650000.0, 1.0));

    shipment.setItems(shipmentItems);
    shipment.setOrderNumber("100000081");
    // shipment.setShipmentId(1);
    shipment.setTotalQty(2.0);

    log.info("Creating new shipment is {}", shipment);
    service.save(shipment, "sudah dikirimkan", false, false);

    log.info("ShipmentID is {}", shipment.getId());

    ShipmentTrack shipmentTrack = new ShipmentTrack();
    shipmentTrack.setCarrier("custom");
    shipmentTrack.setTitle("JNE");
    shipmentTrack.setNumber("1234");
    service.addTrack(shipment, shipmentTrack);

  }

  @Ignore
  @Test
  public void testGetCarriers() {
    // TODO
    fail("Not yet implemented");
  }

  @Ignore
  @Test
  public void testSendInfo() {
    // TODO
    fail("Not yet implemented");
  }

}
