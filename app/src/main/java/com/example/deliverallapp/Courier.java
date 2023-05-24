package com.example.deliverallapp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Courier implements Serializable {
    private String name;
    private String surname;
    private long paymentAccount;
    private boolean hasCar;
    private boolean canDeliverDocs;
    private String password;
    private String phone;
    private String email;
    private List<Order> orders;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public long getPaymentAccount() {
        return paymentAccount;
    }

    public void setPaymentAccount(long paymentAccount) {
        this.paymentAccount = paymentAccount;
    }

    public boolean isHasCar() {
        return hasCar;
    }

    public void setHasCar(boolean hasCar) {
        this.hasCar = hasCar;
    }

    public boolean isCanDeliverDocs() {
        return canDeliverDocs;
    }

    public void setCanDeliverDocs(boolean canDeliverDocs) {
        this.canDeliverDocs = canDeliverDocs;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    // Добавление нового заказа
    public void addOrder(Order order){
        orders.add(order);
        OurSessionData.deleteOrder(order);

    }

    // Удаление заказа
    public void deleteOrder(Order order_to_delete){
        orders.removeIf(o -> o.equals(order_to_delete));
        OurSessionData.addOrder(order_to_delete);
    }


    // конструктор для создания нового аккаунта
    public Courier(String name, String surname, long paymentAccount, boolean hasCar,
                   boolean canDeliverDocs, String password, String phone, String email) {
        this.name = name;
        this.surname = surname;
        this.paymentAccount = paymentAccount;
        this.hasCar = hasCar;
        this.canDeliverDocs = canDeliverDocs;
        this.password = password;
        this.phone = phone;
        this.email = email;
        orders = new ArrayList<>();
        OurSessionData.addCourier(this);

    }

    // конструктор для восстановления аккаунта из файла
    public Courier(String name, String surname, long paymentAccount,
                   boolean hasCar, boolean canDeliverDocs, String password, String phone,
                   String email, List<Order> orders_) {
        this.name = name;
        this.surname = surname;
        this.paymentAccount = paymentAccount;
        this.hasCar = hasCar;
        this.canDeliverDocs = canDeliverDocs;
        this.password = password;
        this.phone = phone;
        this.email = email;
        orders = new ArrayList<>();
        for (int i = 0; i < orders_.size(); ++i){
            addOrder(orders_.get(i));
        }
        OurSessionData.addCourier(this);

    }
}
