package com.google.code.magja.service;

import com.google.code.magja.domain.OrderStatus;
import com.google.code.magja.SalesOrderEntity;
import java.net.MalformedURLException;
import java.net.URL;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 * @author kwilson
 */
public class OrderServiceIT {

    OrderService orderService;

    @Before
    public void setup() throws MalformedURLException {
        orderService = new OrderService(new URL(Credentials.URL), Credentials.USER, Credentials.API_KEY);
    }

    @After
    public void cleanUp() {
        orderService.logout();
    }

    @Test
    public void list_new_orders_by_status_returns_only_status_queried() {
        final SalesOrderEntity[] newSalesOrders = orderService.listSalesOrdersByStatus(OrderStatus.New);
        assertTrue(newSalesOrders.length > 0);
        for (SalesOrderEntity salesOrder : newSalesOrders) {
            assertEquals(OrderStatus.New.toString(), salesOrder.getState());
        }
    }
}
