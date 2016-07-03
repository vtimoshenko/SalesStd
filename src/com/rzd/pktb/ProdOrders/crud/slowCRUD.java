package com.rzd.pktb.ProdOrders.crud;

import com.rzd.pktb.ProdOrders.crud.CRUDException;
import com.rzd.pktb.ProdOrders.crud.salesCRUD;
import com.rzd.pktb.ProdOrders.entity.Client;
import com.rzd.pktb.ProdOrders.entity.Manager;
import com.rzd.pktb.ProdOrders.entity.Order;
import com.rzd.pktb.ProdOrders.entity.Product;

import java.util.Random;

/**
 * Created by SimpleUser on 01.07.2016.
 */
public class slowCRUD implements salesCRUD {
    private salesCRUD sales;
    private int minLatency;
    private int maxLatency;

    private Random rand;

    private void sleep() {
        int lat = minLatency + rand.nextInt(maxLatency-minLatency);
        try {
            Thread.sleep(lat);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public slowCRUD(String crudClass, int minL, int maxL){
        minLatency = minL;
        maxLatency = maxL;
        rand = new Random();
        try {
            sales = (salesCRUD)Class.forName(crudClass).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean createClient(Client client) throws CRUDException {
        sleep();
        return sales.createClient(client);
    }

    @Override
    public Client readClient(int clientId) throws CRUDException {
        sleep();
        return sales.readClient(clientId);
    }

    @Override
    public boolean updateClient(int clientId, Client client) throws CRUDException {
        sleep();
        return sales.updateClient(clientId, client);
    }

    @Override
    public boolean deleteClient(int clientId) throws CRUDException {
        sleep();
        return sales.deleteClient(clientId);
    }

    @Override
    public int countClient() throws CRUDException {
        sleep();
        return sales.countClient();
    }

    @Override
    public int nextIdClient() throws CRUDException {
        sleep();
        return sales.nextIdClient();
    }

    @Override
    public boolean createManager(Manager manager) throws CRUDException {
        sleep();
        return sales.createManager(manager);
    }

    @Override
    public Manager readManager(int managerId) throws CRUDException {
        sleep();
        return sales.readManager(managerId);
    }

    @Override
    public boolean updateManager(int managerId, Manager manager) throws CRUDException {
        sleep();
        return sales.updateManager(managerId, manager);
    }

    @Override
    public boolean deleteManager(int managerId) throws CRUDException {
        sleep();
        return sales.deleteManager(managerId);
    }

    @Override
    public int countManager() throws CRUDException {
        sleep();
        return sales.countManager();
    }

    @Override
    public int nextIdManager() throws CRUDException {
        sleep();
        return sales.nextIdManager();
    }

    @Override
    public boolean createOrder(Order order) throws CRUDException {
        sleep();
        return sales.createOrder(order);
    }

    @Override
    public Order readOrder(int orderId) throws CRUDException {
        sleep();
        return sales.readOrder(orderId);
    }

    @Override
    public boolean updateOrder(int orderId, Order order) throws CRUDException {
        sleep();
        return sales.updateOrder(orderId, order);
    }

    @Override
    public boolean deleteOrder(int orderId) throws CRUDException {
        sleep();
        return sales.deleteOrder(orderId);
    }

    @Override
    public int countOrder() throws CRUDException {
        sleep();
        return sales.countOrder();
    }

    @Override
    public int nextIdOrder() throws CRUDException {
        sleep();
        return sales.nextIdOrder();
    }

    @Override
    public boolean createProduct(Product product) throws CRUDException {
        sleep();
        return sales.createProduct(product);
    }

    @Override
    public Product readProduct(int productId) throws CRUDException {
        sleep();
        return sales.readProduct(productId);
    }

    @Override
    public boolean updateProduct(int productId, Product product) throws CRUDException {
        sleep();
        return sales.updateProduct(productId, product);
    }

    @Override
    public boolean deleteProduct(int productId) throws CRUDException {
        sleep();
        return sales.deleteProduct(productId);
    }

    @Override
    public int countProduct() throws CRUDException {
        sleep();
        return sales.countProduct();
    }

    @Override
    public int nextIdProduct() throws CRUDException {
        sleep();
        return sales.nextIdOrder();
    }
}
