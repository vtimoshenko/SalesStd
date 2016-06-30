package com.rzd.pktb.ProdOrders.entity;

/**
 * Created by SimpleUser on 08.06.2016.
 */
public class OrderItem {
    private int productId;
    private int count;
    private long price;

    public OrderItem(int productId, int count, long price) {
        this.productId = productId;
        this.count = count;
        this.price = price;
    }

    public String getInfo(){
        return "\r\n\t" + count + " of #" + productId + " price:" + price;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}
