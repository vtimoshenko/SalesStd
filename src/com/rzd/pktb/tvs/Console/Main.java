package com.rzd.pktb.tvs.Console;

import com.rzd.pktb.tvs.Cmd.sales;
import com.rzd.pktb.tvs.Cmd.test;

/**
 * Created by vtimoshenko on 07.06.2016.
 */
public class Main {
    public static void main (String[] args){
        ConsoleDialog dial = new ConsoleDialog();
        dial.addCmd(new test());
        dial.addCmd(new sales());
        dial.run();
    }
}
