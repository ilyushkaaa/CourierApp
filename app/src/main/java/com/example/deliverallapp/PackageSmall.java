package com.example.deliverallapp;

public class PackageSmall extends Package{
    public PackageSmall(boolean is_fragile, boolean needCar){
        setSize("small");
        setFragile(is_fragile);
        setNeedCar(needCar);
        setType("small");
    }
}
