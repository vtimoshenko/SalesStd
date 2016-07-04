package com.rzd.pktb.ProdOrders.jsonDB;

import com.rzd.pktb.JSONCluster.ClusterException;
import com.rzd.pktb.JSONCluster.ClusterOne;
import com.rzd.pktb.ProdOrders.crud.CRUDException;
import com.rzd.pktb.ProdOrders.crud.SalesCRUD;
import com.rzd.pktb.ProdOrders.entity.*;

/**
 * Created by vtimoshenko on 09.06.2016.
 */
public class JsonSales implements SalesCRUD {
    private ClusterOne db;
    private String fileName;

    public JsonSales(){
        this.fileName = "JsonDB.txt";
    }

    public JsonSales(String fname) {
        this.fileName = fname;
    }

    private void load(){
        try {
            db.getFromFile(this.fileName);
        } catch (ClusterException e) {
            e.printStackTrace();
        }
    }
    private void save(){
        try {
            db.setToFile(this.fileName);
        } catch (ClusterException e) {
            e.printStackTrace();
        }
    }

    private boolean checkDB() throws CRUDException {
        if (db==null) {
            db = new ClusterOne();
            try {
                db.createObject("", "Clients");
                db.createObject("", "Managers");
                db.createObject("", "Orders");
                db.createObject("", "Products");
            } catch (ClusterException ce) {
                ce.printErrorReport();
                throw new CRUDException("Problem with JSON DB");
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean createClient(Client client) throws CRUDException {
        if (client==null) throw new CRUDException("client is null");
        checkDB();
        try {
            db.createObject("Clients", "" + client.getId());
            db.put("Clients." + client.getId(), "id", ""+client.getId());
            db.put("Clients." + client.getId(), "FIO", client.getFIO());
            db.put("Clients." + client.getId(), "office", client.getOffice());
        } catch (ClusterException ce) {
            ce.printErrorReport();
            throw new CRUDException("Problem with JSON DB");
        }
        return true;
    }

    @Override
    public boolean createManager(Manager manager) throws CRUDException {
        if (manager==null) throw new CRUDException("manager is null");
        checkDB();
        try {
            db.createObject("Managers", "" + manager.getId());
            db.put("Managers." + manager.getId(), "id", ""+manager.getId());
            db.put("Managers." + manager.getId(), "FIO", manager.getFIO());
            db.put("Managers." + manager.getId(), "office", manager.getOffice());
        } catch (ClusterException ce) {
            ce.printErrorReport();
            throw new CRUDException("Problem with JSON DB");
        }
        return true;
    }

    @Override
    public boolean createOrder(Order order) throws CRUDException {
        if (order==null) throw new CRUDException("order is null");
        checkDB();
        try {
            db.createObject("Orders", "" + order.getId());
            db.put("Orders." + order.getId(), "id", ""+order.getId());
            db.put("Orders." + order.getId(), "date", order.getDate());
            db.put("Orders." + order.getId(), "managerId", ""+order.getManagerId());
            db.put("Orders." + order.getId(), "office", order.getOffice());
            db.createArray("Orders." + order.getId(),"Items");
            for (OrderItem item : order.getItems())
            {
                db.createObject("Orders." + order.getId() + ".Items", ""+item.getProductId());
                db.put("Orders." + order.getId() + ".Items." + item.getProductId(),"productId", ""+item.getProductId());
                db.put("Orders." + order.getId() + ".Items." + item.getProductId(),"count", ""+item.getCount());
                db.put("Orders." + order.getId() + ".Items." + item.getProductId(),"price", ""+item.getPrice());
            }
        } catch (ClusterException ce) {
            ce.printErrorReport();
            throw new CRUDException("Problem with JSON DB");
        }
        return true;
    }

    @Override
    public boolean createProduct(Product product) throws CRUDException {
        if (product==null) throw new CRUDException("product is null");
        checkDB();
        try {
            db.createObject("Products", "" + product.getId());
            db.put("Products." + product.getId(), "id", ""+product.getId());
            db.put("Products." + product.getId(), "vendorCode", product.getVendorCode());
            db.put("Products." + product.getId(), "supplyPrice", ""+product.getSupplyPrice());
            db.put("Products." + product.getId(), "office", product.getOffice());
        } catch (ClusterException ce) {
            ce.printErrorReport();
            throw new CRUDException("Problem with JSON DB");
        }
        return true;
    }

    @Override
    public boolean deleteClient(int clientId) throws CRUDException {
        checkDB();
        if (db.exists("Clients." + clientId)) {
            try {
                db.delete("Clients." + clientId);
            } catch (ClusterException ce){
                ce.printErrorReport();
                throw new CRUDException("Problem with JSON DB");
            }
        } else throw new CRUDException("Unknown object");
        return true;
    }

    @Override
    public int countClient() throws CRUDException {
        checkDB();
        try {
            return db.size("Clients");
        } catch (ClusterException ce) {
            ce.printStackTrace();
            throw new CRUDException("Problem with JSON DB");
        }
    }

    @Override
    public int nextIdClient() throws CRUDException {
        return -1;
    }

    @Override
    public boolean deleteManager(int managerId) throws CRUDException {
        checkDB();
        if (db.exists("Managers." + managerId)) {
            try {
                db.delete("Managers." + managerId);
            } catch (ClusterException ce){
                ce.printErrorReport();
                throw new CRUDException("Problem with JSON DB");
            }
        } else throw new CRUDException("Unknown object");
        return true;
    }

    @Override
    public int countManager() throws CRUDException {
        checkDB();
        try {
            return db.size("Managers");
        } catch (ClusterException ce) {
            ce.printStackTrace();
            throw new CRUDException("Problem with JSON DB");
        }
    }

    @Override
    public int nextIdManager() throws CRUDException {
        return -1;
    }

    @Override
    public boolean deleteOrder(int orderId) throws CRUDException {
        checkDB();
        if (db.exists("Orders." + orderId)) {
            try {
                db.delete("Orders." + orderId);
            } catch (ClusterException ce){
                ce.printErrorReport();
                throw new CRUDException("Problem with JSON DB");
            }
        } else throw new CRUDException("Unknown object");
        return true;
    }

    @Override
    public int countOrder() throws CRUDException {
        checkDB();
        try {
            return db.size("Orders");
        } catch (ClusterException ce) {
            ce.printStackTrace();
            throw new CRUDException("Problem with JSON DB");
        }
    }

    @Override
    public int nextIdOrder() throws CRUDException {
        return -1;
    }

    @Override
    public boolean deleteProduct(int productId) throws CRUDException {
        checkDB();
        if (db.exists("Products." + productId)) {
            try {
                db.delete("Products." + productId);
            } catch (ClusterException ce){
                ce.printErrorReport();
                throw new CRUDException("Problem with JSON DB");
            }
        } else throw new CRUDException("Unknown object");
        return true;
    }

    @Override
    public int countProduct() throws CRUDException {
        checkDB();
        try {
            return db.size("Products");
        } catch (ClusterException ce) {
            ce.printStackTrace();
            throw new CRUDException("Problem with JSON DB");
        }
    }

    @Override
    public int nextIdProduct() throws CRUDException {
        return -1;
    }

    @Override
    public Client readClient(int clientId) throws CRUDException {
        checkDB();
        if (db.exists("Clients." + clientId)) {
            try {
                Client client = new Client(
                                    Integer.parseInt(db.get("Clients." + clientId + ".id")),
                                    db.get("Clients." + clientId + ".FIO"),
                                    db.get("Clients." + clientId + ".office"));
                return client;
            } catch (ClusterException ce){
                ce.printErrorReport();
                throw new CRUDException("Problem with JSON DB");
            }
        } else throw new CRUDException("Unknown object");
    }

    @Override
    public Manager readManager(int managerId) throws CRUDException {
        checkDB();
        if (db.exists("Managers." + managerId)) {
            try {
                Manager manager = new Manager(
                                    Integer.parseInt(db.get("Managers." + managerId + ".id")),
                                    db.get("Managers." + managerId + ".FIO"),
                                    db.get("Managers." + managerId + ".office"));
                return manager;
            } catch (ClusterException ce){
                ce.printErrorReport();
                throw new CRUDException("Problem with JSON DB");
            }
        } else throw new CRUDException("Unknown object");
    }

    @Override
    public Order readOrder(int orderId) throws CRUDException {
        checkDB();
        if (db.exists("Orders." + orderId)) {
            try {
                Order order = new Order(
                                Integer.parseInt(db.get("Orders." + orderId + ".id")),
                                Integer.parseInt(db.get("Orders." + orderId + ".managerId")),
                                db.get("Orders." + orderId + ".date"),
                                db.get("Orders." + orderId + ".office"));
                return order;
            } catch (ClusterException ce){
                ce.printErrorReport();
                throw new CRUDException("Problem with JSON DB");
            }
        } else throw new CRUDException("Unknown object");
    }

    @Override
    public Product readProduct(int productId) throws CRUDException {
        checkDB();
        if (db.exists("Products." + productId)) {
            try {
                Product product = new Product(
                                    Integer.parseInt(db.get("Products." + productId + ".id")),
                                    Long.parseLong(db.get("Products." + productId + ".supplyPrice")),
                                    db.get("Products." + productId + ".vendorCode"),
                                    db.get("Products." + productId + ".office"));
                return product;
            } catch (ClusterException ce){
                ce.printErrorReport();
                throw new CRUDException("Problem with JSON DB");
            }
        } else throw new CRUDException("Unknown object");
    }

    @Override
    public boolean updateClient(int clientId, Client client) throws CRUDException {
        return false;//лень писать пока
    }
    @Override
    public boolean updateManager(int managerId, Manager manager) throws CRUDException {
        return false;//лень писать пока
    }

    @Override
    public boolean updateOrder(int orderId, Order order) throws CRUDException {
        return false;//лень писать пока
    }

    @Override
    public boolean updateProduct(int productId, Product product) throws CRUDException {
        return false;//лень писать пока
    }
}
