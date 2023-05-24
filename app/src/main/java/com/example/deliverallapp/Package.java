package com.example.deliverallapp;

import java.io.Serializable;

public abstract class Package  implements Serializable {
    private String size;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private boolean isFragile;
    private boolean needCar;

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public boolean isFragile() {
        return isFragile;
    }

    public void setFragile(boolean fragile) {
        isFragile = fragile;
    }

    public boolean isNeedCar() {
        return needCar;
    }

    public void setNeedCar(boolean needCar) {
        this.needCar = needCar;
    }
}
