/**
 * @author andre
 *
 */
package com.google.code.magja.service.order;

import java.util.List;
import java.util.Map;

import com.google.code.magja.model.order.Shipment;
import com.google.code.magja.model.order.ShipmentTrack;
import com.google.code.magja.service.GeneralService;
import com.google.code.magja.service.ServiceException;

public interface ShipmentRemoteService extends GeneralService<Shipment> {

	public abstract List<Shipment> list(String filter) throws ServiceException;

	public abstract Shipment getById(Integer id) throws ServiceException;

	public abstract void save(Shipment shipment, String comment, Boolean email, Boolean includeComment) throws ServiceException;

	public abstract void addComment(Shipment shipment, String comment, Boolean email, Boolean includeComment) throws ServiceException;

	public abstract void addTrack(Shipment shipment, ShipmentTrack track) throws ServiceException;

	public abstract void removeTrack(Shipment shipment, ShipmentTrack track) throws ServiceException;

	public abstract Map<String, String> getCarriers(Integer orderId) throws ServiceException;

}
