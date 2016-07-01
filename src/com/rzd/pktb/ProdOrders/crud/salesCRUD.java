package com.rzd.pktb.ProdOrders.crud;

import com.rzd.pktb.ProdOrders.entity.Client;
import com.rzd.pktb.ProdOrders.entity.Manager;
import com.rzd.pktb.ProdOrders.entity.Order;
import com.rzd.pktb.ProdOrders.entity.Product;

/**
 * Created by SimpleUser on 08.06.2016.
 */
public interface salesCRUD {

    boolean createClient(Client client) throws CRUDException;
    Client  readClient(int clientId) throws CRUDException;
    boolean updateClient(int clientId, Client client) throws CRUDException;
    boolean deleteClient(int clientId) throws CRUDException;
    int     countClient() throws CRUDException;
    int     nextIdClient() throws CRUDException;

    boolean createManager(Manager manager) throws CRUDException;
    Manager readManager(int managerId) throws CRUDException;
    boolean updateManager(int managerId, Manager manager) throws CRUDException;
    boolean deleteManager(int managerId) throws CRUDException;
    int     countManager() throws CRUDException;
    int     nextIdManager() throws CRUDException;

    boolean createOrder(Order order) throws CRUDException;
    Order   readOrder(int orderId) throws CRUDException;
    boolean updateOrder(int orderId, Order order) throws CRUDException;
    boolean deleteOrder(int orderId) throws CRUDException;
    int     countOrder() throws CRUDException;
    int     nextIdOrder() throws CRUDException;

    boolean createProduct(Product product) throws CRUDException;
    Product readProduct(int productId) throws CRUDException;
    boolean updateProduct(int productId, Product product) throws CRUDException;
    boolean deleteProduct(int productId) throws CRUDException;
    int     countProduct() throws CRUDException;
    int     nextIdProduct() throws CRUDException;
}
