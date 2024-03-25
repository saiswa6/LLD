package com.design.parkinglot.services;

import com.design.parkinglot.models.ParkingLot;
import com.design.parkinglot.models.ParkingSpot;
import com.design.parkinglot.repositories.ParkingLotRepository;

public class ParkingLotService {
    private ParkingLotRepository parkingLotRepository;
    public ParkingLotService(ParkingLotRepository parkingLotRepository) {
        this.parkingLotRepository = parkingLotRepository;
    }

    public ParkingLot createParkingLot(ParkingLot parkingLot){
        return parkingLotRepository.save(parkingLot);
    }

    public ParkingLot updateAddress(Long parkingLotId, String address){
        ParkingLot parkingLot = parkingLotRepository.getParkingLotById(parkingLotId);
        parkingLot.setAddress(address);
        ParkingLot updatedParkingLot = parkingLotRepository.update(parkingLotId,parkingLot);
        return updatedParkingLot;
    }

    public ParkingLot addSpotToFloorOfParkingLot(ParkingSpot parkingSpot, Long parkingLotId){
        ParkingLot parkingLot1 = parkingLotRepository.getParkingLotById(parkingLotId);
        parkingLot1.getParkingFloors().get(0).getParkingSpots().add(parkingSpot);
        return parkingLot1;
    }

}
