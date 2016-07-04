package com.rzd.pktb.ProdOrders.business;

import com.rzd.pktb.JSONCluster.ClusterException;
import com.rzd.pktb.JSONCluster.ClusterOne;
import com.rzd.pktb.ProdOrders.crud.salesCRUD;
import com.rzd.pktb.ProdOrders.crud.slowCRUD;

import java.util.*;
import java.util.concurrent.*;

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

    public String startParallelProcess(List<String> messages){
        System.out.println(new Date());
        Queue<Future> results = new ConcurrentLinkedQueue<Future>();
        ExecutorService service = Executors.newFixedThreadPool(4);
        for (String msg : messages){
            ClusterOne message = new ClusterOne();
            try {
                message.GetFromString(msg);
                //МНОГОПОТОЧНОСТЬ
                String cmd = message.get("cmd");
                String office = message.get("office");
                if (!officeDs.containsKey(office)) continue;
                results.add( service.submit(new Callable() {
                    public String call() throws ClusterException {
                        return cmdExecutor.cmdExec(cmd,ds.get(officeDs.get(office)),message/*.getPart("data")*/);
                    }
                }) );
            } catch (ClusterException e) {
                e.printStackTrace();
            }
        }
        try {
            while (results.size()>0){
                for (Future f : results){
                    if (f.isDone()){
                        System.out.println((String)f.get());
                        results.remove(f);
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        service.shutdown();
        System.out.println(new Date());
        return "";
    }

    public String startSequenceProcess(List<String> messages){
        System.out.println(new Date());
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
            ClusterOne message = new ClusterOne();
            try {
                message.GetFromString(msg);
                String cmd = message.get("cmd");
                String office = message.get("office");
                if (!officeDs.containsKey(office)) continue;
                System.out.println(cmdExecutor.cmdExec(cmd,ds.get(officeDs.get(office)),message/*.getPart("data")*/));
            } catch (ClusterException e) {
                e.printStackTrace();
            }
        }
        System.out.println(new Date());
        return "";
    }
}
