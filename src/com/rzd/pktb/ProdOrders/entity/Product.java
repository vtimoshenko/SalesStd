package com.rzd.pktb.ProdOrders.entity;

/**
 * Created by SimpleUser on 08.06.2016.
 */
public class Product {
    private int id;
    private long supplyPrice;
    private String vendorCode;

    public Product(int id, long supplyPrice, String vendorCode) {
        this.id = id;
        this.supplyPrice = supplyPrice;
        this.vendorCode = vendorCode;
    }

    public String getInfo(){
        return "\r\n#" + id + "(" + vendorCode + ") (price>" + supplyPrice + ")";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getSupplyPrice() {
        return supplyPrice;
    }

    public void setSupplyPrice(long supplyPrice) {
        this.supplyPrice = supplyPrice;
    }

    public String getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }
}
