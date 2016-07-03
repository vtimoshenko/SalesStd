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

/**
 * Created by SimpleUser on 02.07.2016.
 */
public class cmdExecutor {
    public static String cmdExec(String cmd, salesCRUD db, ClusterOne data, String office){
        try {
            switch (cmd) {
                case "createProducts" : return createProducts(db, data, office);
                case "createManagers" : return createManagers(db, data, office);
                case "createClients" : return createClients(db, data, office);
                default : return "Unknown CMD:" + cmd;
            }
        } catch (ClusterException cle){
            cle.printStackTrace();
        } catch (CRUDException cre){
            cre.printStackTrace();
        }
        return "OK";
    }

    private static String createProducts(salesCRUD db, ClusterOne data, String office) throws ClusterException, CRUDException {
        StringBuffer buf = new StringBuffer();
        buf.append("ProductsCreated:");
        List<ClusterOne> olist = getListObj(data);
        for (ClusterOne co : olist){
            Product p = entityBuilder.createProduct(co, office);
            db.createProduct(p);
            buf.append(p.getInfo());
        }
        return buf.toString();
    }
    private static String createManagers(salesCRUD db, ClusterOne data, String office) throws ClusterException, CRUDException {
        StringBuffer buf = new StringBuffer();
        buf.append("ManagersCreated:");
        List<ClusterOne> olist = getListObj(data);
        for (ClusterOne co : olist){
            Manager p = entityBuilder.createManager(co, office);
            db.createManager(p);
            buf.append(p.getInfo());
        }
        return buf.toString();
    }
    private static String createClients(salesCRUD db, ClusterOne data, String office) throws ClusterException, CRUDException {
        StringBuffer buf = new StringBuffer();
        buf.append("ClientsCreated:");
        List<ClusterOne> olist = getListObj(data);
        for (ClusterOne co : olist){
            Client p = entityBuilder.createClient(co, office);
            db.createClient(p);
            buf.append(p.getInfo());
        }
        return buf.toString();
    }



    private static List<ClusterOne> getListObj(ClusterOne data) throws ClusterException {
        List<ClusterOne> list = new ArrayList<>();
        int size = data.size("");
        for (int i=0;i<size;i++){
            list.add(data.getPart(i+""));
        }
        return list;
    }



}
