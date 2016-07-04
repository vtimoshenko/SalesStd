package com.rzd.pktb.ProdOrders.fakeDB;

import com.rzd.pktb.ProdOrders.crud.CRUDException;
import com.rzd.pktb.ProdOrders.crud.SalesCRUD;
import com.rzd.pktb.ProdOrders.entity.*;

/**
 * Created by SimpleUser on 08.06.2016.
 */
public class SimpleSales implements SalesCRUD {
    public SimpleSales(){

    }

    @Override
    public boolean createClient(Client client) throws CRUDException {
        if (client==null) throw new CRUDException("client is null");
        return true;
    }

    @Override
    public Client readClient(int clientId) throws CRUDException {
        if (clientId!=0) throw new CRUDException("unknown client with id=" + clientId);
        return new Client(0, "Покупаев Клиент Потребителевич", "Тест");
    }

    @Override
    public boolean updateClient(int clientId, Client client) throws CRUDException {
        if (client==null) throw new CRUDException("client is null");
        if (clientId!=0) throw new CRUDException("unknown client with id=" + clientId);
        return true;
    }

    @Override
    public boolean deleteClient(int clientId) throws CRUDException {
        if (clientId!=0) throw new CRUDException("unknown client with id=" + clientId);
        return true;
    }

    @Override
    public int countClient() {
        return 1;
    }

    @Override
    public int nextIdClient() {
        return 1;
    }

    @Override
    public boolean createManager(Manager manager) throws CRUDException {
        if (manager==null) throw new CRUDException("manager is null");
        return true;
    }

    @Override
    public Manager readManager(int managerId) throws CRUDException {
        if (managerId!=0) throw new CRUDException("unknown manager with id=" + managerId);
        return new Manager(0, "Задвигаев Продаван Втюхович", "Тест");
    }

    @Override
    public boolean updateManager(int managerId, Manager manager) throws CRUDException {
        if (manager==null) throw new CRUDException("manager is null");
        if (managerId!=0) throw new CRUDException("unknown manager with id=" + managerId);
        return true;
    }

    @Override
    public boolean deleteManager(int managerId) throws CRUDException {
        if (managerId!=0) throw new CRUDException("unknown manager with id=" + managerId);
        return true;
    }

    @Override
    public int countManager() {
        return 1;
    }

    @Override
    public int nextIdManager() {
        return 1;
    }

    @Override
    public boolean createOrder(Order order) throws CRUDException {
        if (order==null) throw new CRUDException("order is null");
        return true;
    }

    @Override
    public Order readOrder(int orderId) throws CRUDException {
        if (orderId!=0) throw new CRUDException("unknown order with id=" + orderId);
        Order order = new Order(0, 0, "2000-01-01", "Тест");
        order.addItem(new OrderItem(0, 1, 50000));
        order.addItem(new OrderItem(1, 2, 2500));
        return order;
    }

    @Override
    public boolean updateOrder(int orderId, Order order) throws CRUDException {
        if (order==null) throw new CRUDException("order is null");
        if (orderId!=0) throw new CRUDException("unknown order with id=" + orderId);
        return true;
    }

    @Override
    public boolean deleteOrder(int orderId) throws CRUDException {
        if (orderId!=0) throw new CRUDException("unknown order with id=" + orderId);
        return true;
    }

    @Override
    public int countOrder() {
        return 1;
    }

    @Override
    public int nextIdOrder() {
        return 1;
    }

    @Override
    public boolean createProduct(Product product) throws CRUDException {
        if (product==null) throw new CRUDException("product is null");
        return false;
    }

    @Override
    public Product readProduct(int productId) throws CRUDException {
        if (productId!=0) throw new CRUDException("unknown product with id=" + productId);
        Product product = new Product(0, 90000, "Gibson SG Special", "Тест");
        return product;
    }

    @Override
    public boolean updateProduct(int productId, Product product) throws CRUDException {
        if (product==null) throw new CRUDException("product is null");
        if (productId!=0) throw new CRUDException("unknown product with id=" + productId);
        return true;
    }

    @Override
    public boolean deleteProduct(int productId) throws CRUDException {
        if (productId!=0) throw new CRUDException("unknown product with id=" + productId);
        return true;
    }

    @Override
    public int countProduct() {
        return 1;
    }

    @Override
    public int nextIdProduct() {
        return 1;
    }
}
