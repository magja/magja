package com.google.code.magja.service.order;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.code.magja.model.order.Shipment;
import com.google.code.magja.model.order.ShipmentItem;
import com.google.code.magja.model.order.ShipmentTrack;
import com.google.code.magja.service.RemoteServiceFactory;
import com.google.code.magja.service.ServiceException;
import com.google.code.magja.model.order.Filter;

/**
 * @author andre
 *
 */
public class ShipmentRemoteServiceTest {
	private transient Logger log = LoggerFactory
			.getLogger(ShipmentRemoteServiceTest.class);

	private ShipmentRemoteService service;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		service = RemoteServiceFactory.getSingleton().getShipmentRemoteService();
	}

	/**
	 * Test method for {@link com.google.code.magja.service.order.ShipmentRemoteServiceImpl#addComment(com.google.code.magja.model.order.Shipment, java.lang.String, java.lang.Boolean, java.lang.Boolean)}.
	 */
	@Test
	public void testAddComment() {
		
	}

	/**
	 * Test method for {@link com.google.code.magja.service.order.ShipmentRemoteServiceImpl#addTrack(com.google.code.magja.model.order.Shipment, com.google.code.magja.model.order.ShipmentTrack)}.
	 * @throws ServiceException 
	 */
	@Test
	public void testAddTrack() throws ServiceException {
		Shipment shipment = service.getById(100000005);
		
		ShipmentTrack shipmentTrack = new ShipmentTrack();
		shipmentTrack.setCarrier("custom");
		shipmentTrack.setTitle("JNE");
		shipmentTrack.setNumber("1234");
		service.addTrack(shipment, shipmentTrack);
	}

	/**
	 * Test method for {@link com.google.code.magja.service.order.ShipmentRemoteServiceImpl#getById(java.lang.Integer)}.
	 * @throws ServiceException 
	 */
	@Test
	public void testGetById() throws ServiceException {
		Shipment shipment = service.getById(100000005);
		log.debug("Shipment: {}", shipment);
	}

	/**
	 * Test method for {@link com.google.code.magja.service.order.ShipmentRemoteServiceImpl#list(Filter)}.
	 */
	@Test
	public void testList() {
		//fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link com.google.code.magja.service.order.ShipmentRemoteServiceImpl#removeTrack(com.google.code.magja.model.order.Shipment, com.google.code.magja.model.order.ShipmentTrack)}.
	 */
	@Test
	public void testRemoveTrack() {
		//fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link com.google.code.magja.service.order.ShipmentRemoteServiceImpl#save(com.google.code.magja.model.order.Shipment, java.lang.String, java.lang.Boolean, java.lang.Boolean)}.
	 * @throws ServiceException 
	 */
	@Test
	public void save() throws ServiceException {
		Shipment shipment = new Shipment();
		shipment.setCustomerId(3);
//		shipment.setId(1);
		
		final List<ShipmentItem> shipmentItems = new ArrayList<ShipmentItem>();
		shipmentItems.add(new ShipmentItem("obatiks_isabella-accessory-42", "Isabella Accessory 42", 18, 1070, 10.0, 11000.0, 1.0));
//		shipmentItems.add(new ShipmentItem("zibalabel_t14", "Bag T14", 105, 200, 3.0, 1650000.0, 1.0));
		
		shipment.setItems(shipmentItems);
		shipment.setOrderNumber("100000081");
//		shipment.setShipmentId(1);
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

	/**
	 * Test method for {@link com.google.code.magja.service.order.ShipmentRemoteServiceImpl#getCarriers(java.lang.Integer)}.
	 */
	@Test
	public void testGetCarriers() {
		//fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link com.google.code.magja.service.order.ShipmentRemoteServiceImpl#sendInfo(com.google.code.magja.model.order.Shipment, java.lang.String)}.
	 */
	@Test
	public void testSendInfo() {
		//fail("Not yet implemented"); // TODO
	}

}
