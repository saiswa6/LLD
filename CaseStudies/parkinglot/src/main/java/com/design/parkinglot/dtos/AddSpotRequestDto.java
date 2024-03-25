package com.design.parkinglot.dtos;

import com.design.parkinglot.models.ParkingLot;
import com.design.parkinglot.models.ParkingSpot;

public class AddSpotRequestDto {

    private ParkingSpot parkingSpot;
    private Long parkingLotId;

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public void setParkingSpot(ParkingSpot parkingSpot) {
        this.parkingSpot = parkingSpot;
    }

    public Long getParkingLotId() {
        return parkingLotId;
    }

    public void setParkingLotId(Long parkingLotId) {
        this.parkingLotId = parkingLotId;
    }
}
