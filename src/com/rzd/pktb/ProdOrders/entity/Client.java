package com.rzd.pktb.ProdOrders.entity;

/**
 * Created by SimpleUser on 08.06.2016.
 */
public class Client {
    @Field
    private int id;
    @Field
    private String FIO;
    @Field
    private String office;

    public Client(int id, String FIO, String office) {
        this.id = id;
        this.FIO = FIO;
        this.office = office;
    }

    public String getInfo(){
        return "\r\nClient " + FIO + " (id=" + id + ") from " + office;
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

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }
}
