package com.google.code.magja.service.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.code.magja.model.order.InvoiceItem;
import com.google.code.magja.model.order.ShipmentItem;
import org.apache.axis2.AxisFault;

import com.google.code.magja.magento.ResourcePath;
import com.google.code.magja.model.order.Invoice;
import com.google.code.magja.service.GeneralServiceImpl;
import com.google.code.magja.service.ServiceException;
import com.google.code.magja.soap.MagentoSoapClient;

@SuppressWarnings("serial")
public class InvoiceRemoteServiceImpl extends GeneralServiceImpl<Invoice> implements InvoiceRemoteService {

	public InvoiceRemoteServiceImpl(MagentoSoapClient soapClient) {
		super(soapClient);
	}

	private Invoice buildInvoice(Map<String, Object> attributes) throws ServiceException {
        Invoice invoice = new Invoice();
        for (Map.Entry<String, Object> attr : attributes.entrySet()) invoice.set(attr.getKey(), attr.getValue());
        return invoice;
    }


    @Override
    public void addComment(Invoice invoice, String comment, Boolean email,
                           Boolean includeComment) throws ServiceException {
        try {
            soapClient.callArgs(ResourcePath.SalesOrderInvoiceAddComment, new Object[] {
            		invoice.getId(), comment != null ? comment : "",
    				email ? "1" : "0", includeComment ? "1" : "0" });
        } catch (AxisFault e) {
            if(debug) e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
    }


    @Override
    public void save(Invoice invoice, String comment, Boolean email,
                     Boolean includeComment) throws ServiceException {
        Integer id = null;
        try {
            HashMap<Integer, Double> invoiceItems = new HashMap<Integer, Double>();
            for (InvoiceItem invoiceItem : invoice.getItems()) {
                invoiceItems.put(invoiceItem.getOrderItemId(), invoiceItem.getQty());
            }
            id = Integer.parseInt((String) soapClient.callArgs(ResourcePath.SalesOrderInvoiceCreate, new Object[] {
                    invoice.getOrderNumber(), invoiceItems, comment != null ? comment : "", email ? 1 : 0, includeComment ? 1 : 0 }));
        } catch (NumberFormatException e) {
            if(debug) e.printStackTrace();
            throw new ServiceException(e.getMessage());
        } catch (AxisFault e) {
            if(debug) e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }

        if(id == null || id <= 0) throw new ServiceException("Error saving invoice");

        invoice.setId(id);
    }


    @Override
    public Invoice getById(Integer id) throws ServiceException {
        Map<String, Object> result = null;
        try {
            result = soapClient.callSingle(ResourcePath.SalesOrderInvoiceInfo, id);
        } catch (AxisFault e) {
            if(debug) e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }

        return (result != null ? buildInvoice(result) : null);
    }


    @Override
    public List<Invoice> list(String filter) throws ServiceException {

        List<Invoice> shipments = new ArrayList<Invoice>();

        List<Map<String, Object>> results = null;
        try {
            results = soapClient.callSingle(ResourcePath.SalesOrderInvoiceList, filter);
        } catch (AxisFault e) {
            if(debug) e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }

        for (Map<String, Object> result : results)
            shipments.add(buildInvoice(result));

        return shipments;
    }


    @Override
    public void capture(Invoice invoice)
            throws ServiceException {
        try {
            soapClient.callSingle(ResourcePath.SalesOrderInvoiceCapture, invoice.getId());
        } catch (AxisFault e) {
            if(debug) e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }

    }

    @Override
    public void voidInvoice(Invoice invoice)
            throws ServiceException {
        try {
            soapClient.callSingle(ResourcePath.SalesOrderInvoiceVoid, invoice.getId());
        } catch (AxisFault e) {
            if(debug) e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }

    }

    @Override
    public void cancelInvoice(Invoice invoice)
            throws ServiceException {
        try {
            soapClient.callSingle(ResourcePath.SalesOrderInvoiceCancel, invoice.getId());
        } catch (AxisFault e) {
            if(debug) e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }

    }


}
