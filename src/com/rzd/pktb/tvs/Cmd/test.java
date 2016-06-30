package com.rzd.pktb.tvs.Cmd;

import com.rzd.pktb.tvs.Console.ConsoleCommand;

/**
 * Created by vtimoshenko on 08.06.2016.
 */
public class test implements ConsoleCommand{
    public String Run (String[] args)
    {
        String rets = "";
        if (args.length<2) rets = "No second argument!";
        else rets = "You are pushing " + args[1];
        return rets;
    }
}
