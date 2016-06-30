package com.rzd.pktb.ProdOrders.entity;

/**
 * Created by SimpleUser on 08.06.2016.
 */
public class Client {
    private int id;
    private String FIO;

    public Client(int id, String FIO) {
        this.id = id;
        this.FIO = FIO;
    }

    public String getInfo(){
        return "\r\nClient " + FIO + " (id=" + id + ")";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }
}
