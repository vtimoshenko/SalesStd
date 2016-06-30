package com.rzd.pktb.ProdOrders.entity;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by SimpleUser on 08.06.2016.
 */
public class Order {
    private int id;
    private int managerId;
    private Date date;
    private List<OrderItem> items;

    public void addItem(OrderItem item) {
        items.add(item);
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public Order(int id, int managerId, Date date) {
        this.id = id;
        this.managerId = managerId;
        this.date = date;
        items = new LinkedList<OrderItem>();
    }

    public String getInfo(){
        StringBuffer buf = new StringBuffer();
        buf.append("\r\nOrder №" + id + " by manager #" + managerId + " (" + date.toString() + ")");
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
