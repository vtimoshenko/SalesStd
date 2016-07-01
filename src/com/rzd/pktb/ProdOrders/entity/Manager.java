package com.rzd.pktb.ProdOrders.entity;

/**
 * Created by SimpleUser on 08.06.2016.
 */
public class Manager {
    private int id;
    private String FIO;
    private String office;

    public Manager(int id, String FIO, String office) {
        this.id = id;
        this.FIO = FIO;
        this.office = office;
    }

    public String getInfo(){
        return "\r\nManager " + FIO + " (id=" + id + ") from " + office;
    }

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }
}
