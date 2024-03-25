package com.design.parkinglot.repositories;

import com.design.parkinglot.models.ParkingLot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLotRepository {
    private Map<Long, ParkingLot> parkingLotMap = new HashMap<>();
    Long lastCount = 0L;

    public ParkingLot save(ParkingLot parkingLot){
        lastCount++;
        parkingLot.setId(lastCount);
        parkingLotMap.put(lastCount, parkingLot);
        return parkingLot;
    }

    public ParkingLot getParkingLotById(Long parkingLotId){
        return parkingLotMap.get(parkingLotId);
    }

    public ParkingLot update(Long parkingLotId, ParkingLot parkingLot){
        parkingLotMap.put(parkingLotId, parkingLot);
        return parkingLot;
    }
}
