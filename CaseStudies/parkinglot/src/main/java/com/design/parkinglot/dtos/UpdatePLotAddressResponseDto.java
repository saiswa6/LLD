package com.design.parkinglot.dtos;

import com.design.parkinglot.models.ParkingLot;

import java.util.PrimitiveIterator;

public class UpdatePLotAddressResponseDto {

    private ParkingLot parkingLot;

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }
}
