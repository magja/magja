/**
 *
 */
package com.google.code.magja.service.order;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.axis2.AxisFault;

import com.google.code.magja.magento.ResourcePath;
import com.google.code.magja.model.order.Shipment;
import com.google.code.magja.model.order.ShipmentTrack;
import com.google.code.magja.service.GeneralServiceImpl;
import com.google.code.magja.service.ServiceException;

/**
 * @author andre
 *
 */
public class ShipmentRemoteServiceImpl extends GeneralServiceImpl<Shipment> implements ShipmentRemoteService {

	/**
	 * Build object Shipment from attributes map
	 * @param attributes
	 * @return
	 * @throws ServiceException
	 */
	private Shipment buildShipment(Map<String, Object> attributes) throws ServiceException {
		Shipment shipment = new Shipment();
		for (Map.Entry<String, Object> attr : attributes.entrySet()) shipment.set(attr.getKey(), attr.getValue());
		return shipment;
	}

	/* (non-Javadoc)
	 * @see com.google.code.magja.service.order.ShipmentRemoteService#addComment(com.google.code.magja.model.order.Shipment, java.lang.String, java.lang.Boolean, java.lang.Boolean)
	 */
	@Override
	public void addComment(Shipment shipment, String comment, Boolean email,
			Boolean includeComment) throws ServiceException {

		List<Object> params = new LinkedList<Object>();
		params.add(shipment.getId());
		params.add((comment != null ? comment : ""));
		params.add((email ? "1" : "0"));
		params.add((includeComment ? "1" : "0"));

		try {
			soapClient.call(ResourcePath.SalesOrderShipmentAddComment, params);
		} catch (AxisFault e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see com.google.code.magja.service.order.ShipmentRemoteService#addTrack(com.google.code.magja.model.order.Shipment, com.google.code.magja.model.order.ShipmentTrack)
	 */
	@Override
	public void addTrack(Shipment shipment, ShipmentTrack track)
			throws ServiceException {

		List<Object> params = new LinkedList<Object>();
		params.add(shipment.getId());
		params.add(track.getCarrier());
		params.add(track.getTitle());
		params.add(track.getNumber());

		Integer id = null;
		try {
			id = Integer.parseInt((String) soapClient.call(ResourcePath.SalesOrderShipmentAddTrack, params));
		} catch (NumberFormatException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		} catch (AxisFault e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

		if(id == null || id <= 0) throw new ServiceException("Error saving track");

		track.setId(id);
	}

	/* (non-Javadoc)
	 * @see com.google.code.magja.service.order.ShipmentRemoteService#getById(java.lang.Integer)
	 */
	@Override
	public Shipment getById(Integer id) throws ServiceException {

		Map<String, Object> result = null;
		try {
			result = (Map<String, Object>) soapClient.call(ResourcePath.SalesOrderShipmentInfo, id);
		} catch (AxisFault e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

		return (result != null ? buildShipment(result) : null);
	}

	/* (non-Javadoc)
	 * @see com.google.code.magja.service.order.ShipmentRemoteService#list(java.lang.String)
	 */
	@Override
	public List<Shipment> list(String filter) throws ServiceException {

		List<Shipment> shipments = new ArrayList<Shipment>();

		List<Map<String, Object>> results = null;
		try {
			results = (List<Map<String, Object>>) soapClient.call(ResourcePath.SalesOrderShipmentList, filter);
		} catch (AxisFault e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

		for (Map<String, Object> result : results)
			shipments.add(buildShipment(result));

		return shipments;
	}

	/* (non-Javadoc)
	 * @see com.google.code.magja.service.order.ShipmentRemoteService#removeTrack(com.google.code.magja.model.order.Shipment, com.google.code.magja.model.order.ShipmentTrack)
	 */
	@Override
	public void removeTrack(Shipment shipment, ShipmentTrack track)
			throws ServiceException {

		List<Object> params = new LinkedList<Object>();
		params.add(shipment.getId());
		params.add(track.getId());

		try {
			soapClient.call(ResourcePath.SalesOrderShipmentRemoveTrack, params);
		} catch (AxisFault e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}

	/* (non-Javadoc)
	 * @see com.google.code.magja.service.order.ShipmentRemoteService#save(com.google.code.magja.model.order.Shipment, java.lang.String, java.lang.Boolean, java.lang.Boolean)
	 */
	@Override
	public void save(Shipment shipment, String comment, Boolean email,
			Boolean includeComment) throws ServiceException {

		List<Object> params = (LinkedList<Object>) shipment.serializeToApi();
		params.add((comment != null ? comment : ""));
		params.add((email ? "1" : "0"));
		params.add((includeComment ? "1" : "0"));

		Integer id = null;
		try {
			id = Integer.parseInt((String) soapClient.call(ResourcePath.SalesOrderShipmentCreate, params));
		} catch (NumberFormatException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		} catch (AxisFault e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

		if(id == null || id <= 0) throw new ServiceException("Error saving shipment");

		shipment.setId(id);
	}

	/* (non-Javadoc)
	 * @see com.google.code.magja.service.order.ShipmentRemoteService#getCarriers(java.lang.Integer)
	 */
	@Override
	public Map<String, String> getCarriers(Integer orderId) throws ServiceException {

		try {
			Map<String, String> carriers = (Map<String, String>) soapClient.call(ResourcePath.SalesOrderShipmentGetCarriers, orderId);
			return carriers;
		} catch (AxisFault e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}
}
