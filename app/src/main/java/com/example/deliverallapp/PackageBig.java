package com.example.deliverallapp;

public class PackageBig extends Package{
    private int weight;
    public PackageBig(boolean is_fragile, int weight){
        setSize("big");
        setFragile(is_fragile);
        setNeedCar(true);
        this.weight = weight;
        setType("big");
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

}
