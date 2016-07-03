package com.rzd.pktb.tvs;

import com.rzd.pktb.ProdOrders.business.SalesProcess;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by SimpleUser on 01.07.2016.
 */
public class startPoint {
    public static void main(String[] args) {
        SalesProcess sp = new SalesProcess("Settings.txt");
        List<String> sList;
        try {
            sList = Files.readAllLines(Paths.get("inputCmd.txt"));
            sp.startProcess(sList);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
