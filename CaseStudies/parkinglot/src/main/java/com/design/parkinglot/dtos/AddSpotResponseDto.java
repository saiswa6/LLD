package com.design.parkinglot.dtos;

import com.design.parkinglot.models.ParkingLot;

public class AddSpotResponseDto extends ResponseDto{
    private ParkingLot parkingLot;

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }
}
