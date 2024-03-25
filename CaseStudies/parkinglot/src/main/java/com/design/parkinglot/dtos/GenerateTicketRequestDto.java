package com.design.parkinglot.dtos;

import com.design.parkinglot.models.EntryGate;
import com.design.parkinglot.models.SpotType;
import com.design.parkinglot.models.Vehicle;

import java.util.Date;

public class GenerateTicketRequestDto {
    private Vehicle vehicle;
    private EntryGate entryGate;
    private Long parkingLotId;

    private SpotType spotType;

    public SpotType getSpotType() {
        return spotType;
    }

    public void setSpotType(SpotType spotType) {
        this.spotType = spotType;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public EntryGate getEntryGate() {
        return entryGate;
    }

    public void setEntryGate(EntryGate entryGate) {
        this.entryGate = entryGate;
    }


    public Long getParkingLotId() {
        return parkingLotId;
    }

    public void setParkingLotId(Long parkingLotId) {
        this.parkingLotId = parkingLotId;
    }
}
