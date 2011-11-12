package com.google.code.magja.service.order;

import com.google.code.magja.magento.ResourcePath;
import com.google.code.magja.model.order.Invoice;
import com.google.code.magja.service.GeneralServiceImpl;
import com.google.code.magja.service.ServiceException;
import org.apache.axis2.AxisFault;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class InvoiceRemoteServiceImpl extends GeneralServiceImpl<Invoice> implements InvoiceRemoteService {


    private Invoice buildInvoice(Map<String, Object> attributes) throws ServiceException {
        Invoice invoice = new Invoice();
        for (Map.Entry<String, Object> attr : attributes.entrySet()) invoice.set(attr.getKey(), attr.getValue());
        return invoice;
    }


    @Override
    public void addComment(Invoice invoice, String comment, Boolean email,
                           Boolean includeComment) throws ServiceException {

        List<Object> params = new LinkedList<Object>();
        params.add(invoice.getId());
        params.add((comment != null ? comment : ""));
        params.add((email ? "1" : "0"));
        params.add((includeComment ? "1" : "0"));

        try {
            soapClient.call(ResourcePath.SalesOrderInvoiceAddComment, params);
        } catch (AxisFault e) {
            if(debug) e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
    }


    @Override
    public void save(Invoice invoice, String comment, Boolean email,
                     Boolean includeComment) throws ServiceException {

        List<Object> params = (LinkedList<Object>) invoice.serializeToApi();
        params.add((comment != null ? comment : ""));
        params.add((email ? "1" : "0"));
        params.add((includeComment ? "1" : "0"));

        Integer id = null;
        try {
            id = Integer.parseInt((String) soapClient.call(ResourcePath.SalesOrderInvoiceCreate, params));
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
            result = (Map<String, Object>) soapClient.call(ResourcePath.SalesOrderInvoiceInfo, id);
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
            results = (List<Map<String, Object>>) soapClient.call(ResourcePath.SalesOrderInvoiceList, filter);
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

        List<Object> params = new LinkedList<Object>();
        params.add(invoice.getId());

        try {
            soapClient.call(ResourcePath.SalesOrderInvoiceCapture, params);
        } catch (AxisFault e) {
            if(debug) e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }

    }

    @Override
    public void voidInvoice(Invoice invoice)
            throws ServiceException {

        List<Object> params = new LinkedList<Object>();
        params.add(invoice.getId());

        try {
            soapClient.call(ResourcePath.SalesOrderInvoiceVoid, params);
        } catch (AxisFault e) {
            if(debug) e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }

    }

    @Override
    public void cancelInvoice(Invoice invoice)
            throws ServiceException {

        List<Object> params = new LinkedList<Object>();
        params.add(invoice.getId());

        try {
            soapClient.call(ResourcePath.SalesOrderInvoiceCancel, params);
        } catch (AxisFault e) {
            if(debug) e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }

    }


}
