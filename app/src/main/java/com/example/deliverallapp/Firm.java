package com.example.deliverallapp;

import java.io.Serializable;

public class Firm implements Serializable {
    private String name;
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Firm(String name, String address) {
        this.name = name;
        this.address = address;
    }
}
