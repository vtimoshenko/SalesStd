package com.rzd.pktb.tvs.Cmd;

import com.rzd.pktb.ProdOrders.business.SalesProcess;
import com.rzd.pktb.ProdOrders.fakeDB.simpleSales;
import com.rzd.pktb.ProdOrders.jsonDB.jsonSales;
import com.rzd.pktb.tvs.Console.ConsoleCommand;

/**
 * Created by vtimoshenko on 09.06.2016.
 */
public class sales implements ConsoleCommand {
    public String Run(String[] args) {
        String rets = "";
        if (args.length<2) rets = "second argument must be 'json' or 'fake'";
        else if (args[1].equals("json")) {
            SalesProcess sp = new SalesProcess(new jsonSales());
            rets += sp.startProcess();
        } else if (args[1].equals("fake")) {
            SalesProcess sp = new SalesProcess(new simpleSales());
            rets += sp.startProcess();
        } else rets = "second argument must be 'json' or 'fake'";
        return rets;
    }
}
