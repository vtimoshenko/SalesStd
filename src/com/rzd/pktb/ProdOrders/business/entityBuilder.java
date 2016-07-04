package com.rzd.pktb.ProdOrders.business;

import com.rzd.pktb.JSONCluster.ClusterException;
import com.rzd.pktb.JSONCluster.ClusterOne;
import com.rzd.pktb.ProdOrders.entity.Client;
import com.rzd.pktb.ProdOrders.entity.Manager;
import com.rzd.pktb.ProdOrders.entity.Product;

/**
 * Created by SimpleUser on 02.07.2016.
 */
public class EntityBuilder {

    public static Client createClient(ClusterOne data, String office){
        try {
            int id = Integer.parseInt(data.get("id"));
            String FIO = data.get("FIO");
            return new Client(id, FIO, office);
        } catch (ClusterException e) {
            return null;
        }
    }

    public static Product createProduct(ClusterOne data, String office){
        try {
            int id = Integer.parseInt(data.get("id"));
            long supplyPrice = Long.parseLong(data.get("supplyPrice"));
            String vendorCode = data.get("vendorCode");
            return new Product(id, supplyPrice, vendorCode, office);
        } catch (ClusterException e) {
            return null;
        }
    }

    public static Manager createManager(ClusterOne data, String office){
        try {
            int id = Integer.parseInt(data.get("id"));
            String FIO = data.get("FIO");
            return new Manager(id, FIO, office);
        } catch (ClusterException e) {
            return null;
        }
    }
}
