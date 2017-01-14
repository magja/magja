package com.google.code.magja.service.order;

import java.util.List;
import java.util.Map;

import com.google.code.magja.model.order.Filter;
import com.google.code.magja.model.order.Shipment;
import com.google.code.magja.model.order.ShipmentTrack;
import com.google.code.magja.service.GeneralService;
import com.google.code.magja.service.ServiceException;
/**
 * Shipment service.
 * @author andre
 * @author Simon Zambrovski
 */
public interface ShipmentRemoteService extends GeneralService<Shipment> {

  List<Shipment> list(Filter filter) throws ServiceException;

  Shipment getById(Integer id) throws ServiceException;

  void save(Shipment shipment, String comment, Boolean email, Boolean includeComment) throws ServiceException;

  void addComment(Shipment shipment, String comment, Boolean email, Boolean includeComment) throws ServiceException;

  void addTrack(Shipment shipment, ShipmentTrack track) throws ServiceException;

  void removeTrack(Shipment shipment, ShipmentTrack track) throws ServiceException;

  Map<String, String> getCarriers(Integer orderId) throws ServiceException;

  void sendInfo(com.google.code.magja.model.order.Shipment shipment, String comment) throws ServiceException;

}
