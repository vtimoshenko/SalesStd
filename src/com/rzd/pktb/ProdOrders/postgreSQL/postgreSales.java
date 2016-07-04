package com.rzd.pktb.ProdOrders.postgreSQL;

import com.rzd.pktb.ProdOrders.DB.DBException;
import com.rzd.pktb.ProdOrders.DB.DBInterface;
import com.rzd.pktb.ProdOrders.crud.CRUDException;
import com.rzd.pktb.ProdOrders.crud.SalesCRUD;
import com.rzd.pktb.ProdOrders.entity.Client;
import com.rzd.pktb.ProdOrders.entity.Manager;
import com.rzd.pktb.ProdOrders.entity.Order;
import com.rzd.pktb.ProdOrders.entity.Product;

/**
 * Created by vtimoshenko on 04.07.2016.
 */
public class PostgreSales implements SalesCRUD {
    DBInterface db;

    public PostgreSales() {
        try {
            db = new DBInterface("org.postgresql.Driver","jdbc:postgresql://localhost:5432/SalesStd","postgres","fishing");
        } catch (DBException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean createClient(Client client) throws CRUDException {
        return false;
    }

    @Override
    public Client readClient(int clientId) throws CRUDException {
        return null;
    }

    @Override
    public boolean updateClient(int clientId, Client client) throws CRUDException {
        return false;
    }

    @Override
    public boolean deleteClient(int clientId) throws CRUDException {
        return false;
    }

    @Override
    public int countClient() throws CRUDException {
        return 0;
    }

    @Override
    public int nextIdClient() throws CRUDException {
        return 0;
    }

    @Override
    public boolean createManager(Manager manager) throws CRUDException {
        return false;
    }

    @Override
    public Manager readManager(int managerId) throws CRUDException {
        return null;
    }

    @Override
    public boolean updateManager(int managerId, Manager manager) throws CRUDException {
        return false;
    }

    @Override
    public boolean deleteManager(int managerId) throws CRUDException {
        return false;
    }

    @Override
    public int countManager() throws CRUDException {
        return 0;
    }

    @Override
    public int nextIdManager() throws CRUDException {
        return 0;
    }

    @Override
    public boolean createOrder(Order order) throws CRUDException {
        return false;
    }

    @Override
    public Order readOrder(int orderId) throws CRUDException {
        return null;
    }

    @Override
    public boolean updateOrder(int orderId, Order order) throws CRUDException {
        return false;
    }

    @Override
    public boolean deleteOrder(int orderId) throws CRUDException {
        return false;
    }

    @Override
    public int countOrder() throws CRUDException {
        return 0;
    }

    @Override
    public int nextIdOrder() throws CRUDException {
        return 0;
    }

    @Override
    public boolean createProduct(Product product) throws CRUDException {
        if (product==null) throw new CRUDException("product is null");
        String query = "insert into sales.products (\"id\", \"supplyPrice\", \"vandorCode\", \"office\") values (" +
                product.getId() + ", " +
                product.getSupplyPrice() + ", '" +
                product.getVendorCode()+ "', '" +
                product.getOffice() + "')";
        try {
            db.MakeUpdate(query);
        } catch (DBException e) {
            e.printStackTrace();
            throw new CRUDException(e.getErrorReport());
        }
        return false;
    }

    @Override
    public Product readProduct(int productId) throws CRUDException {
        return null;
    }

    @Override
    public boolean updateProduct(int productId, Product product) throws CRUDException {
        return false;
    }

    @Override
    public boolean deleteProduct(int productId) throws CRUDException {
        return false;
    }

    @Override
    public int countProduct() throws CRUDException {
        return 0;
    }

    @Override
    public int nextIdProduct() throws CRUDException {
        return 0;
    }
}
