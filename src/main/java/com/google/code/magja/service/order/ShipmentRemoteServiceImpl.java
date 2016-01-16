/**
 * @author andre
 *
 */
package com.google.code.magja.service.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.code.magja.model.order.Filter;
import org.apache.axis2.AxisFault;

import com.google.code.magja.magento.ResourcePath;
import com.google.code.magja.model.order.Shipment;
import com.google.code.magja.model.order.ShipmentItem;
import com.google.code.magja.model.order.ShipmentTrack;
import com.google.code.magja.service.GeneralServiceImpl;
import com.google.code.magja.service.ServiceException;
import com.google.code.magja.soap.MagentoSoapClient;
import com.google.common.collect.ImmutableSet;

public class ShipmentRemoteServiceImpl extends GeneralServiceImpl<Shipment> implements ShipmentRemoteService {

    private static final long serialVersionUID = -830461835402137135L;
    
    public ShipmentRemoteServiceImpl(MagentoSoapClient soapClient) {
		super(soapClient);
	}

	/**
     * Build object Shipment from attributes map
     *
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

        Object[] params = new Object[] {
        	shipment.getId(),
        	(comment != null ? comment : ""),
        	(email ? "1" : "0"),
        	(includeComment ? "1" : "0") };

        try {
            soapClient.callArgs(ResourcePath.SalesOrderShipmentAddComment, params);
        } catch (AxisFault e) {
            if (debug) e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
    }

    /* (non-Javadoc)
      * @see com.google.code.magja.service.order.ShipmentRemoteService#addTrack(com.google.code.magja.model.order.Shipment, com.google.code.magja.model.order.ShipmentTrack)
      */
    @Override
    public void addTrack(Shipment shipment, ShipmentTrack track)
            throws ServiceException {
        Integer id = null;
        try {
            id = Integer.parseInt((String) soapClient.callArgs(ResourcePath.SalesOrderShipmentAddTrack, 
            		new Object[] { shipment.getId(), track.getCarrier(),
            			track.getTitle(), track.getNumber() }));
        } catch (NumberFormatException e) {
            if (debug) e.printStackTrace();
            throw new ServiceException(e.getMessage());
        } catch (AxisFault e) {
            if (debug) e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }

        if (id == null || id <= 0) throw new ServiceException("Error saving track");

        track.setId(id);
    }

    /* (non-Javadoc)
      * @see com.google.code.magja.service.order.ShipmentRemoteService#getById(java.lang.Integer)
      */
    @Override
    public Shipment getById(Integer id) throws ServiceException {

        Map<String, Object> result = null;
        try {
            result = soapClient.callSingle(ResourcePath.SalesOrderShipmentInfo, id);
        } catch (AxisFault e) {
            if (debug) e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }

        return (result != null ? buildShipment(result) : null);
    }

    /* (non-Javadoc)
      * @see ShipmentRemoteService#list(com.google.code.magja
      * .model.order.Filter)
      */
    @Override
    public List<Shipment> list(Filter filter) throws ServiceException {

        List<Shipment> shipments = new ArrayList<Shipment>();

        if (filter != null) {
            if (filter.getItems() != null) {
                if (filter.getItems().isEmpty())
                    filter = null;
            } else
                filter = null;
        }

        List<Map<String, Object>> results = null;
        try {
            results = soapClient.callSingle(ResourcePath.SalesOrderShipmentList, (filter != null ? filter
                    .serializeToApi() : ""));
        } catch (AxisFault e) {
            if (debug) e.printStackTrace();
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
        try {
            soapClient.callArgs(ResourcePath.SalesOrderShipmentRemoveTrack, new Object[] {
            		shipment.getId(), track.getId() });
        } catch (AxisFault e) {
            if (debug) e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }

    }

    /* (non-Javadoc)
      * @see com.google.code.magja.service.order.ShipmentRemoteService#create(com.google.code.magja.model.order.Shipment, java.lang.String, java.lang.Boolean, java.lang.Boolean)
      */
    @Override
    public void save(Shipment shipment, String comment, Boolean email,
                     Boolean includeComment) throws ServiceException {
        Integer id = null;
        try {
        	HashMap<Integer, Double> shipmentItems = new HashMap<Integer, Double>();
        	for (ShipmentItem shipmentItem : shipment.getItems()) {
				shipmentItems.put(shipmentItem.getOrderItemId(), shipmentItem.getQty());
			}
            id = Integer.parseInt((String) soapClient.callArgs(ResourcePath.SalesOrderShipmentCreate, new Object[] {
            		shipment.getOrderNumber(), shipmentItems, comment != null ? comment : "", email ? 1 : 0, includeComment ? 1 : 0 }));
        } catch (NumberFormatException e) {
            if (debug) e.printStackTrace();
            throw new ServiceException(e.getMessage());
        } catch (AxisFault e) {
            if (debug) e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }

        if (id == null || id <= 0) throw new ServiceException("Error saving shipment");

        shipment.setId(id);
    }

    /* (non-Javadoc)
      * @see com.google.code.magja.service.order.ShipmentRemoteService#getCarriers(java.lang.Integer)
      */
    @Override
    public Map<String, String> getCarriers(Integer orderId) throws ServiceException {

        try {
            Map<String, String> carriers = soapClient.callSingle(ResourcePath.SalesOrderShipmentGetCarriers, orderId);
            return carriers;
        } catch (AxisFault e) {
            if (debug) e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }

    }

    /* (non-Javadoc)
      * @see ShipmentRemoteService#sendInfo(Shipment, java.lang.String)
      */
    @Override
    public void sendInfo(Shipment shipment, String comment) throws ServiceException {

        Object[] params = new Object[] {
                shipment.getId(),
                (comment != null ? comment : "") };

        try {
            soapClient.callArgs(ResourcePath.SalesOrderShipmentSendInfo, params);
        } catch (AxisFault e) {
            if (debug) e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
    }
}
