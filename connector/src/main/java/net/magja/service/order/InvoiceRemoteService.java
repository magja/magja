package net.magja.service.order;

import net.magja.model.order.Invoice;
import net.magja.service.GeneralService;
import net.magja.service.ServiceException;

import java.util.List;

/**
 * Invoice service.
 * @author andre
 * @author Simon Zambrovski
 */
public interface InvoiceRemoteService extends GeneralService<Invoice> {

  void save(Invoice shipment, String comment, Boolean email, Boolean includeComment) throws ServiceException;

  void addComment(Invoice invoice, String comment, Boolean email, Boolean includeComment) throws ServiceException;

  Invoice getById(Integer id) throws ServiceException;

  List<Invoice> list(String filter) throws ServiceException;

  void capture(Invoice invoice) throws ServiceException;

  void voidInvoice(Invoice invoice) throws ServiceException;

  void cancelInvoice(Invoice invoice) throws ServiceException;
}
