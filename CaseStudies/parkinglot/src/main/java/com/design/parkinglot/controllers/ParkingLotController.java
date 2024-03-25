package com.design.parkinglot.controllers;

import com.design.parkinglot.dtos.*;
import com.design.parkinglot.models.ParkingFloor;
import com.design.parkinglot.models.ParkingLot;
import com.design.parkinglot.models.ParkingSpot;
import com.design.parkinglot.models.ParkingSpotStatus;
import com.design.parkinglot.services.ParkingLotService;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotController {
    private ParkingLotService parkingLotService;

    public ParkingLotController(ParkingLotService parkingLotService) {
        this.parkingLotService = parkingLotService;
    }

    public CreateParkingLotResponseDto createParkingLot(CreateParkingLotRequestDto requestDto){
        if(requestDto.getNumberOfFloors() < 1){
            CreateParkingLotResponseDto createParkingLotResponseDto = new CreateParkingLotResponseDto();
            createParkingLotResponseDto.setResponseStatusDto(ResponseStatusDto.FAILURE);
        }

        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setAddress(requestDto.getAddress());
        List<ParkingFloor> parkingFloorList = new ArrayList<>();
        for(int count = 0; count<requestDto.getNumberOfFloors();count++){
            ParkingFloor parkingFloor = new ParkingFloor();
            List<ParkingSpot> parkingSpots = new ArrayList<>();
            for(int i= 0; i < 4; i++){
                ParkingSpot parkingSpot = new ParkingSpot();
                parkingSpot.setParkingSpotStatus(ParkingSpotStatus.AVAILABLE);
                parkingSpot.setParkingFloor(parkingFloor);
                parkingSpots.add(parkingSpot);
            }
            parkingFloor.setParkingSpots(parkingSpots);
            parkingFloorList.add(parkingFloor);
        }
        parkingLot.setParkingFloors(parkingFloorList);

        ParkingLot createdParkingLot = parkingLotService.createParkingLot(parkingLot);

        CreateParkingLotResponseDto responseDto = new CreateParkingLotResponseDto();
        responseDto.setResponseStatusDto(ResponseStatusDto.SUCCESS);
        responseDto.setParkingLot(createdParkingLot);

        return responseDto;
    }


    public UpdatePLotAddressResponseDto updateAddress(UpdatePLotAddressRequestDto requestDto){
        ParkingLot parkingLot =  parkingLotService.updateAddress(requestDto.getParkingLotId(), requestDto.getAddress());
        UpdatePLotAddressResponseDto updatePLotAddressResponseDto = new UpdatePLotAddressResponseDto();
        updatePLotAddressResponseDto.setParkingLot(parkingLot);
        return updatePLotAddressResponseDto;
    }

    public AddSpotResponseDto addSpotToFloorOfParkingLot(AddSpotRequestDto addSpotRequestDto){
        ParkingLot parkingLot = parkingLotService.addSpotToFloorOfParkingLot(addSpotRequestDto.getParkingSpot(), addSpotRequestDto.getParkingLotId());
        AddSpotResponseDto addSpotResponseDto = new AddSpotResponseDto();
        addSpotResponseDto.setParkingLot(parkingLot);
        return addSpotResponseDto;
    }
}
