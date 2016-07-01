package com.rzd.pktb.ProdOrders.business;

import com.rzd.pktb.ProdOrders.crud.CRUDException;
import com.rzd.pktb.ProdOrders.crud.salesCRUD;
import com.rzd.pktb.ProdOrders.entity.*;

import java.util.Date;

/**
 * Created by SimpleUser on 08.06.2016.
 */
public class SalesProcess {
    public salesCRUD db;

    public SalesProcess(salesCRUD dataSource){
        this.db = dataSource;
    }

    public String startProcess(){
        /*try {

            db.createClient(new Client(0, "Иванов Иван Иваныч"));
            db.createClient(new Client(1, "Петров Петр Петрович"));

            db.createManager(new Manager(0, "Толкаев Продаван Втюхович"));

            db.createProduct(new Product(0, 50000, "Gibson SG Special"));
            db.createProduct(new Product(1, 90000, "Gibson Les Paul Standart"));
            db.createProduct(new Product(2, 70000, "Gibson Explorer"));
            db.createProduct(new Product(3, 60000, "Fender American Standart Stratocaster"));
            db.createProduct(new Product(4, 80000, "Fender Telecaster"));

            Order order = new Order(0, 0, new Date());
            order.addItem(new OrderItem(0,2,1000000));
            order.addItem(new OrderItem(3,4,2000000));
            db.createOrder(order);

            StringBuffer checkObjects = new StringBuffer();

            checkObjects.append(db.getClient(1).getInfo());

            checkObjects.append(db.getManager(0).getInfo());
            checkObjects.append(db.getManager(0).getInfo());

            checkObjects.append(db.getProduct(2).getInfo());

            checkObjects.append(db.getOrder(0).getInfo());

            return checkObjects.toString();
        } catch (CRUDException e) {
            e.printStackTrace();
            return "Something wrong";
        }
     */return "";
    }
}
