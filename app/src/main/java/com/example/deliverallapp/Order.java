package com.example.deliverallapp;

import java.io.Serializable;

public class Order  implements Serializable {
    private int ID;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    private Firm firm;
    private Package aPackage;
    private String addressFrom;
    private String addressTo;
    private int price;

    public Firm getFirm() {
        return firm;
    }

    public void setFirm(Firm firm) {
        this.firm = firm;
    }

    public Package getaPackage() {
        return aPackage;
    }

    public void setaPackage(Package aPackage) {
        this.aPackage = aPackage;
    }

    public String getAddressFrom() {
        return addressFrom;
    }

    public void setAddressFrom(String addressFrom) {
        this.addressFrom = addressFrom;
    }

    public String getAddressTo() {
        return addressTo;
    }

    public void setAddressTo(String addressTo) {
        this.addressTo = addressTo;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Order(int ID, Firm firm, Package aPackage, String addressTo, int price) {
        this.ID = ID;
        this.firm = firm;
        this.aPackage = aPackage;
        this.addressFrom = firm.getAddress();
        this.addressTo = addressTo;
        this.price = price;
        if (!hasOrder(this)){
            OurSessionData.addOrder(this);
        }


    }
    private boolean hasOrder(Order order){
        for (int i = 0; i < OurSessionData.getCourierList().size(); ++i){
            for (int j = 0; j < OurSessionData.getCourierList().get(i).getOrders().size(); ++j){
                if (order.ID == OurSessionData.getCourierList().get(i).getOrders().get(j).ID){
                    return true;
                }
            }
        }
        return false;
    }

}
