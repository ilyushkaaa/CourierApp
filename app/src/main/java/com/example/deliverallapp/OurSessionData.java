package com.example.deliverallapp;

import java.util.ArrayList;
import java.util.List;

public class OurSessionData {
    private static Courier curCourier;
    public static Courier getCurCourier() {
        return curCourier;
    }

    public static void setCurCourier(Courier curCour) {
        curCourier = curCour;
    }
    private static List<Courier> courierList = new ArrayList<>();
    private static List<Order> availableOrders = new ArrayList<>();

    public static List<Courier> getCourierList() {
        return courierList;
    }

    public static void setCourierList(List<Courier> courierList) {
        OurSessionData.courierList = courierList;
    }

    public static List<Order> getAvailableOrders() {
        return availableOrders;
    }


    public static void setAvailableOrders(List<Order> availableOrders) {
        OurSessionData.availableOrders = availableOrders;
    }

    public static void addOrder(Order order){
        availableOrders.add(order);
    }
    public static void deleteOrder(Order order){
        availableOrders.removeIf(o -> o.equals(order));
    }
    public static void addCourier(Courier courier){
        courierList.add(courier);
    }
    public static void deleteCourier(Courier courier){
        courierList.removeIf(c -> c.equals(courier));
    }

}
