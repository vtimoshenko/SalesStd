package com.rzd.pktb.ProdOrders.crud;

import com.rzd.pktb.ProdOrders.entity.Client;
import com.rzd.pktb.ProdOrders.entity.Manager;
import com.rzd.pktb.ProdOrders.entity.Order;
import com.rzd.pktb.ProdOrders.entity.Product;

/**
 * Created by SimpleUser on 08.06.2016.
 */
public interface salesCRUD {
    void createClient(Client client) throws CRUDException;
    void createManager(Manager manager) throws CRUDException;
    void createOrder(Order order) throws CRUDException;
    void createProduct(Product product) throws CRUDException;

    void deleteClient(int clientId) throws CRUDException;
    void deleteManager(int managerId) throws CRUDException;
    void deleteOrder(int orderId) throws CRUDException;
    void deleteProduct(int productId) throws CRUDException;

    Client getClient(int clientId) throws CRUDException;
    Manager getManager(int managerId) throws CRUDException;
    Order getOrder(int orderId) throws CRUDException;
    Product getProduct(int productId) throws CRUDException;

    void updateClient(int clientId, Client client) throws CRUDException;
    void updateManager(int managerId, Manager manager) throws CRUDException;
    void updateOrder(int orderId, Order order) throws CRUDException;
    void updateProduct(int productId, Product product) throws CRUDException;
}
