package com.rzd.pktb.ProdOrders.entity;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by SimpleUser on 08.06.2016.
 */
public class Order {
    @Field
    private int id;
    @Field
    private int managerId;
    private String date;
    private List<OrderItem> items;
    private String office;

    public void addItem(OrderItem item) {
        items.add(item);
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public Order(int id, int managerId, String date, String office) {
        this.id = id;
        this.managerId = managerId;
        this.date = date;
        items = new LinkedList<OrderItem>();
        this.office = office;
    }

    public String getInfo(){
        StringBuffer buf = new StringBuffer();
        buf.append("\r\nOrder №" + id + " by manager #" + managerId + " (" + date.toString() + ") from " + office);
        for (OrderItem item : items)
        {
            buf.append(item.getInfo() );
        }
        return buf.toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }
}
