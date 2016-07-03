package com.rzd.pktb.ProdOrders.business;

import com.rzd.pktb.JSONCluster.ClusterException;
import com.rzd.pktb.JSONCluster.ClusterOne;
import com.rzd.pktb.ProdOrders.crud.salesCRUD;
import com.rzd.pktb.ProdOrders.crud.slowCRUD;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by SimpleUser on 08.06.2016.
 */
public class SalesProcess {
    public HashMap<String, salesCRUD> ds;
    public HashMap<String, String> officeDs;

    public SalesProcess(String settingsFile){
        ClusterOne sets = new ClusterOne();
        ds = new HashMap<>();
        officeDs = new HashMap<>();
        try {
            sets.GetFromFile(settingsFile);
            int dsc = sets.size("dataSources");
            for (int dsi=0;dsi<dsc;dsi++){
                String name = sets.get("dataSources." + dsi + ".name");
                String Cname = sets.get("dataSources." + dsi + ".type");
                int minL = Integer.parseInt(sets.get("dataSources." + dsi + ".minLat"));
                int maxL = Integer.parseInt(sets.get("dataSources." + dsi + ".maxLat"));
                ds.put(name, new slowCRUD(Cname, minL, maxL));
                int ofc = sets.size("dataSources." + dsi + ".offices");
                for (int ofi=0;ofi<ofc;ofi++){
                    String office = sets.get("dataSources." + dsi + ".offices." + ofi);
                    officeDs.put(office, name);
                }
            }
        } catch (ClusterException e) {
            System.out.println("Invalid settings file");
        }
    }

    public String startProcess(List<String> messages){
//        System.out.println(messages.size() + "<-- size");
/*
        Iterator<String> keys =  ds.keySet().iterator();
        while (keys.hasNext()) {
            String key = keys.next();
            System.out.println(key + ":" + ds.get(key).getClass().getName());
        }
        keys =  officeDs.keySet().iterator();
        while (keys.hasNext()) {
            String key = keys.next();
            System.out.println(key + ":" + officeDs.get(key));
        }
*/


        for (String msg : messages){
//            System.out.println("for call");
            ClusterOne message = new ClusterOne();
            try {
                message.GetFromString(msg);
                //ТУТ ДОЛЖНА БЫТЬ МНОГОПОТОЧНОСТЬ
                String cmd = message.get("cmd");
                String office = message.get("office");
                if (!officeDs.containsKey(office)) continue;
                String result = cmdExecutor.cmdExec(cmd,ds.get(officeDs.get(office)),message.getPart("data"), office);
                System.out.println(result);
            } catch (ClusterException e) {
                e.printStackTrace();
            }
        }
        return "";
    }
}
