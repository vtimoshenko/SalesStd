package com.rzd.pktb.ProdOrders.crud;

/**
 * Created by SimpleUser on 08.06.2016.
 */
public class CRUDException extends Exception {
    private String message;
    public CRUDException(String message){
        this.message = message;
    }
    public String getMessage() {
        return this.message;
    }
}
