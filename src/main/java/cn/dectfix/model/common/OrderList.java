package cn.dectfix.model.common;

import java.util.ArrayList;
import java.util.List;

public class OrderList {
    private List<Order> orders = new ArrayList<>();

    public void addOrder(Order order){
        orders.add(order);
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        String res = "";
        for (Order order:orders){
            res = res + ","+order.toString();
        }

        if(res.length()>0)
            res = res.substring(1,res.length());
        return res;
    }
}
