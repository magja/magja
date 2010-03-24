/**
 *
 */
package code.google.magja.service.order;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import code.google.magja.service.RemoteServiceFactory;

/**
 * @author andre
 *
 */
public class ShipmentRemoteServiceTest {

	private ShipmentRemoteService service;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		service = RemoteServiceFactory.getShipmentRemoteService();
	}

	/**
	 * Test method for {@link code.google.magja.service.order.ShipmentRemoteServiceImpl#addComment(code.google.magja.model.order.Shipment, java.lang.String, java.lang.Boolean, java.lang.Boolean)}.
	 */
	@Test
	public void testAddComment() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link code.google.magja.service.order.ShipmentRemoteServiceImpl#addTrack(code.google.magja.model.order.Shipment, code.google.magja.model.order.ShipmentTrack)}.
	 */
	@Test
	public void testAddTrack() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link code.google.magja.service.order.ShipmentRemoteServiceImpl#getById(java.lang.Integer)}.
	 */
	@Test
	public void testGetById() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link code.google.magja.service.order.ShipmentRemoteServiceImpl#list(java.lang.String)}.
	 */
	@Test
	public void testList() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link code.google.magja.service.order.ShipmentRemoteServiceImpl#removeTrack(code.google.magja.model.order.Shipment, code.google.magja.model.order.ShipmentTrack)}.
	 */
	@Test
	public void testRemoveTrack() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link code.google.magja.service.order.ShipmentRemoteServiceImpl#save(code.google.magja.model.order.Shipment, java.lang.String, java.lang.Boolean, java.lang.Boolean)}.
	 */
	@Test
	public void testSave() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link code.google.magja.service.order.ShipmentRemoteServiceImpl#getCarriers(java.lang.Integer)}.
	 */
	@Test
	public void testGetCarriers() {
		fail("Not yet implemented"); // TODO
	}

}
