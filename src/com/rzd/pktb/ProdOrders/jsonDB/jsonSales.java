package com.rzd.pktb.ProdOrders.jsonDB;

import com.rzd.pktb.JSONCluster.ClusterException;
import com.rzd.pktb.JSONCluster.ClusterOne;
import com.rzd.pktb.ProdOrders.crud.CRUDException;
import com.rzd.pktb.ProdOrders.crud.salesCRUD;
import com.rzd.pktb.ProdOrders.entity.*;

import java.util.Date;

/**
 * Created by vtimoshenko on 09.06.2016.
 */
public class jsonSales implements salesCRUD {
    private ClusterOne db;

    private boolean checkDB() throws CRUDException {
        if (db==null) {
            db = new ClusterOne();
            try {
                db.CreateArray("", "Clients");
                db.CreateArray("", "Managers");
                db.CreateArray("", "Orders");
                db.CreateArray("", "Products");
            } catch (ClusterException ce) {
                ce.printErrorReport();
                throw new CRUDException("Problem with JSON DB");
            }
            return false;
        } else {
            return true;
        }
    }

    public void createClient(Client client) throws CRUDException {
        checkDB();
        try {
            db.CreateObject("Clients", "" + client.getId());
            db.put("Clients." + client.getId(), "id", ""+client.getId());
            db.put("Clients." + client.getId(), "FIO", client.getFIO());
        } catch (ClusterException ce) {
            ce.printErrorReport();
            throw new CRUDException("Problem with JSON DB");
        }
    }

    public void createManager(Manager manager) throws CRUDException {
        checkDB();
        try {
            db.CreateObject("Managers", "" + manager.getId());
            db.put("Managers." + manager.getId(), "id", ""+manager.getId());
            db.put("Managers." + manager.getId(), "FIO", manager.getFIO());
        } catch (ClusterException ce) {
            ce.printErrorReport();
            throw new CRUDException("Problem with JSON DB");
        }
    }

    public void createOrder(Order order) throws CRUDException {
        checkDB();
        try {
            db.CreateObject("Orders", "" + order.getId());
            db.put("Orders." + order.getId(), "id", ""+order.getId());
            db.put("Orders." + order.getId(), "date", order.getDate().toString());
            db.put("Orders." + order.getId(), "managerId", ""+order.getManagerId());
            db.CreateArray("Orders." + order.getId(),"Items");
            for (OrderItem item : order.getItems())
            {
                db.CreateObject("Orders." + order.getId() + ".Items", ""+item.getProductId());
                db.put("Orders." + order.getId() + ".Items." + item.getProductId(),"productId", ""+item.getProductId());
                db.put("Orders." + order.getId() + ".Items." + item.getProductId(),"count", ""+item.getCount());
                db.put("Orders." + order.getId() + ".Items." + item.getProductId(),"price", ""+item.getPrice());
            }
        } catch (ClusterException ce) {
            ce.printErrorReport();
            throw new CRUDException("Problem with JSON DB");
        }
    }

    public void createProduct(Product product) throws CRUDException {
        checkDB();
        try {
            db.CreateObject("Products", "" + product.getId());
            db.put("Products." + product.getId(), "id", ""+product.getId());
            db.put("Products." + product.getId(), "vendorCode", product.getVendorCode());
            db.put("Products." + product.getId(), "supplyPrice", ""+product.getSupplyPrice());
        } catch (ClusterException ce) {
            ce.printErrorReport();
            throw new CRUDException("Problem with JSON DB");
        }
    }

    public void deleteClient(int clientId) throws CRUDException {
        checkDB();
        if (db.Exists("Clients." + clientId)) {
            try {
                db.delete("Clients." + clientId);
            } catch (ClusterException ce){
                ce.printErrorReport();
                throw new CRUDException("Problem with JSON DB");
            }
        } else throw new CRUDException("Unknown object");
    }

    public void deleteManager(int managerId) throws CRUDException {
        checkDB();
        if (db.Exists("Managers." + managerId)) {
            try {
                db.delete("Managers." + managerId);
            } catch (ClusterException ce){
                ce.printErrorReport();
                throw new CRUDException("Problem with JSON DB");
            }
        } else throw new CRUDException("Unknown object");
    }

    public void deleteOrder(int orderId) throws CRUDException {
        checkDB();
        if (db.Exists("Orders." + orderId)) {
            try {
                db.delete("Orders." + orderId);
            } catch (ClusterException ce){
                ce.printErrorReport();
                throw new CRUDException("Problem with JSON DB");
            }
        } else throw new CRUDException("Unknown object");
    }

    public void deleteProduct(int productId) throws CRUDException {
        checkDB();
        if (db.Exists("Products." + productId)) {
            try {
                db.delete("Products." + productId);
            } catch (ClusterException ce){
                ce.printErrorReport();
                throw new CRUDException("Problem with JSON DB");
            }
        } else throw new CRUDException("Unknown object");
    }

    public Client getClient(int clientId) throws CRUDException {
        checkDB();
        if (db.Exists("Clients." + clientId)) {
            try {
                Client client = new Client(Integer.parseInt(db.get("Clients." + clientId + ".id")), db.get("Clients." + clientId + ".FIO"));
                return client;
            } catch (ClusterException ce){
                ce.printErrorReport();
                throw new CRUDException("Problem with JSON DB");
            }
        } else throw new CRUDException("Unknown object");
    }

    public Manager getManager(int managerId) throws CRUDException {
        checkDB();
        if (db.Exists("Managers." + managerId)) {
            try {
                Manager manager = new Manager(
                                    Integer.parseInt(db.get("Managers." + managerId + ".id")),
                                    db.get("Managers." + managerId + ".FIO"));
                return manager;
            } catch (ClusterException ce){
                ce.printErrorReport();
                throw new CRUDException("Problem with JSON DB");
            }
        } else throw new CRUDException("Unknown object");
    }

    public Order getOrder(int orderId) throws CRUDException {
        checkDB();
        if (db.Exists("Orders." + orderId)) {
            try {
                Order order = new Order(
                                Integer.parseInt(db.get("Orders." + orderId + ".id")),
                                Integer.parseInt(db.get("Orders." + orderId + ".managerId")),
                                new Date()//parse date
                );
                return order;
            } catch (ClusterException ce){
                ce.printErrorReport();
                throw new CRUDException("Problem with JSON DB");
            }
        } else throw new CRUDException("Unknown object");
    }

    public Product getProduct(int productId) throws CRUDException {
        checkDB();
        if (db.Exists("Products." + productId)) {
            try {
                Product product = new Product(
                                    Integer.parseInt(db.get("Products." + productId + ".id")),
                                    Long.parseLong(db.get("Products." + productId + ".supplyPrice")),
                                    db.get("Products." + productId + ".vendorCode")
                        );
                return product;
            } catch (ClusterException ce){
                ce.printErrorReport();
                throw new CRUDException("Problem with JSON DB");
            }
        } else throw new CRUDException("Unknown object");
    }

    public void updateClient(int clientId, Client client) throws CRUDException {

    }

    public void updateManager(int managerId, Manager manager) throws CRUDException {

    }

    public void updateOrder(int orderId, Order order) throws CRUDException {

    }

    public void updateProduct(int productId, Product product) throws CRUDException {

    }
}
