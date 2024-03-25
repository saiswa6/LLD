package com.design.parkinglot.models;

public class ElectricParkingSpot extends ParkingSpot{
    private ElectricCharger electricCharger;

    public ElectricCharger getElectricCharger() {
        return electricCharger;
    }

    public void setElectricCharger(ElectricCharger electricCharger) {
        this.electricCharger = electricCharger;
    }
}
