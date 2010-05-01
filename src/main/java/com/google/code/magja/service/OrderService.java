package com.google.code.magja.service;

import com.google.code.magja.MagjaException;
import com.google.code.magja.domain.OrderStatus;
import com.google.code.magja.AssociativeEntity;
import com.google.code.magja.Filters;
import com.google.code.magja.SalesOrderEntity;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kwilson
 */
public class OrderService extends BaseService {

    public OrderService(URL url, String user, String password) {
        super(url, user, password);
    }

    public SalesOrderEntity[] listSalesOrdersByStatus(OrderStatus state) {
        login();
        return queryForSalesOrders("state", state.toString());
    }

    public void holdSalesOrder(SalesOrderEntity salesOrder) {
        login();
        try {
            getPort().salesOrderHold(getSessionId(), salesOrder.getIncrement_id());
        } catch (RemoteException ex) {
            throw new RuntimeException(ex);
        }
    }

    private SalesOrderEntity[] queryForSalesOrders(String... keyValuePairs) {

        if (keyValuePairs.length % 2 == 1) {
            throw new IllegalArgumentException("Odd number of key-value pair arguments");
        }

        List<AssociativeEntity> filters = new ArrayList<AssociativeEntity>(keyValuePairs.length / 2);
        for (int i = 0; i < keyValuePairs.length; i += 2) {
            AssociativeEntity keyValuePair = new AssociativeEntity();
            keyValuePair.setKey(keyValuePairs[i]);
            keyValuePair.setValue(keyValuePairs[i+1]);
            filters.add(keyValuePair);
        }
        Filters f = new Filters();
        f.setFilter(filters.toArray(new AssociativeEntity[filters.size()]));
        try {
            return getPort().salesOrderList(getSessionId(), f);
        } catch (Exception ex) {
            throw new MagjaException(ex);
        }

    }
}
