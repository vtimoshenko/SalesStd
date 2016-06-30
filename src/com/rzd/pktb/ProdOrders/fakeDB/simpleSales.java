package com.rzd.pktb.ProdOrders.fakeDB;

import com.rzd.pktb.ProdOrders.crud.CRUDException;
import com.rzd.pktb.ProdOrders.crud.salesCRUD;
import com.rzd.pktb.ProdOrders.entity.*;

import java.util.Date;

/**
 * Created by SimpleUser on 08.06.2016.
 */
public class simpleSales implements salesCRUD {

    public void createClient(Client client) throws CRUDException {
        if (client==null) throw new CRUDException("Client is null");
        else return;
    }

    public void createManager(Manager manager) throws CRUDException {
        if (manager==null) throw new CRUDException("Manager is null");
        else return;
    }

    public void createOrder(Order order) throws CRUDException {
        if (order==null) throw new CRUDException("Order is null");
        else return;
    }

    public void createProduct(Product product) throws CRUDException {
        if (product==null) throw new CRUDException("Product is null");
        else return;
    }

    public void deleteClient(int clientId) throws CRUDException {
        if (clientId<0) throw new CRUDException("Invalid Id");
        else return;
    }

    public void deleteManager(int managerId) throws CRUDException {
        if (managerId<0) throw new CRUDException("Invalid Id");
        else return;
    }

    public void deleteOrder(int orderId) throws CRUDException {
        if (orderId<0) throw new CRUDException("Invalid Id");
        else return;
    }

    public void deleteProduct(int productId) throws CRUDException {
        if (productId<0) throw new CRUDException("Invalid Id");
        else return;
    }

    public Client getClient(int clientId) throws CRUDException {
        Client client = new Client(0, "Пупкин Василий Виссарионович");
        return client;
    }

    public Manager getManager(int managerId) throws CRUDException {
        Manager manager = new Manager(0, "Зильберштейн Акакий петрович");
        return manager;
    }

    public Order getOrder(int orderId) throws CRUDException {
        Order order = new Order(0, 0, new Date());
        order.addItem(new OrderItem(0, 1, 50000));
        order.addItem(new OrderItem(1, 2, 2500));
        return order;
    }

    public Product getProduct(int productId) throws CRUDException {
        Product product = new Product(0, 90000, "Gibson SG Special");
        return product;
    }

    public void updateClient(int clientId, Client client) throws CRUDException {
        if (clientId<0) throw new CRUDException("Invalid Id");
        if (client==null) throw new CRUDException("Client is null");
        else return;
    }

    public void updateManager(int managerId, Manager manager) throws CRUDException {
        if (managerId<0) throw new CRUDException("Invalid Id");
        if (manager==null) throw new CRUDException("Manager is null");
        else return;
    }

    public void updateOrder(int orderId, Order order) throws CRUDException {
        if (orderId<0) throw new CRUDException("Invalid Id");
        if (order==null) throw new CRUDException("Order is null");
        else return;
    }

    public void updateProduct(int productId, Product product) throws CRUDException {
        if (productId<0) throw new CRUDException("Invalid Id");
        if (product==null) throw new CRUDException("Product is null");
        else return;
    }


}
