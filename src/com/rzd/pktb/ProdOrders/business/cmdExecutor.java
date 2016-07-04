package com.rzd.pktb.ProdOrders.business;

import com.rzd.pktb.JSONCluster.ClusterException;
import com.rzd.pktb.JSONCluster.ClusterOne;
import com.rzd.pktb.ProdOrders.crud.CRUDException;
import com.rzd.pktb.ProdOrders.crud.salesCRUD;
import com.rzd.pktb.ProdOrders.entity.Client;
import com.rzd.pktb.ProdOrders.entity.Manager;
import com.rzd.pktb.ProdOrders.entity.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by SimpleUser on 02.07.2016.
 */
public class cmdExecutor{

    public static String cmdExec(String cmd, salesCRUD db, ClusterOne data){
        try {
            switch (cmd) {
                case "createProducts" : return createProducts(db, data);
                case "createManagers" : return createManagers(db, data);
                case "createClients" : return createClients(db, data);
                case "getProducts" : return getProducts(db, data);
                case "getManagers" : return getManagers(db, data);
                case "getClients" : return getClients(db, data);
                default : return "Unknown CMD:" + cmd;
            }
        } catch (ClusterException cle){
            cle.printStackTrace();
        } catch (CRUDException cre){
            cre.printStackTrace();
        }
        return "OK";
    }

    private static String createProducts(salesCRUD db, ClusterOne data) throws ClusterException, CRUDException {
        StringBuffer buf = new StringBuffer();
        buf.append("ProductsCreated:");
        List<ClusterOne> olist = getListObj(data);
        for (ClusterOne co : olist){
            Product p = entityBuilder.createProduct(co, data.get("office"));
            db.createProduct(p);
            buf.append(p.getInfo());
        }
        return buf.toString();
    }
    private static String getProducts(salesCRUD db, ClusterOne data) throws ClusterException, CRUDException {
        StringBuffer buf = new StringBuffer();
        buf.append("ProductsSelected:");
        int size = data.size("");
        for (int i=0;i<size;i++){
            buf.append(db.readProduct(Integer.parseInt(data.get("" + i))).getInfo());
        }
        return buf.toString();
    }

    private static String createManagers(salesCRUD db, ClusterOne data) throws ClusterException, CRUDException {
        StringBuffer buf = new StringBuffer();
        buf.append("ManagersCreated:");
        List<ClusterOne> olist = getListObj(data);
        for (ClusterOne co : olist){
            Manager p = entityBuilder.createManager(co, data.get("office"));
            db.createManager(p);
            buf.append(p.getInfo());
        }
        return buf.toString();
    }
    private static String getManagers(salesCRUD db, ClusterOne data) throws ClusterException, CRUDException {
        StringBuffer buf = new StringBuffer();
        buf.append("ManagersSelected:");
        int size = data.size("");
        for (int i=0;i<size;i++){
            buf.append(db.readManager(Integer.parseInt(data.get("" + i))).getInfo());
        }
        return buf.toString();
    }

    private static String createClients(salesCRUD db, ClusterOne data) throws ClusterException, CRUDException {
        StringBuffer buf = new StringBuffer();
        buf.append("ClientsCreated:");
        List<ClusterOne> olist = getListObj(data);
        for (ClusterOne co : olist){
            Client p = entityBuilder.createClient(co, data.get("office"));
            db.createClient(p);
            buf.append(p.getInfo());
        }
        return buf.toString();
    }
    private static String getClients(salesCRUD db, ClusterOne data) throws ClusterException, CRUDException {
        StringBuffer buf = new StringBuffer();
        buf.append("ClientsSelected:");
        int size = data.size("");
        for (int i=0;i<size;i++){
            buf.append(db.readClient(Integer.parseInt(data.get("" + i))).getInfo());
        }
        return buf.toString();
    }
    private static List<ClusterOne> getListObj(ClusterOne data) throws ClusterException {
        List<ClusterOne> list = new ArrayList<>();
        int size = data.size("data");
        for (int i=0;i<size;i++){
            list.add(data.getPart("data."+i));
        }
        return list;
    }
}
