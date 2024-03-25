package com.design.parkinglot;

import com.design.parkinglot.controllers.ParkingLotController;
import com.design.parkinglot.controllers.TicketController;
import com.design.parkinglot.dtos.*;
import com.design.parkinglot.models.*;
import com.design.parkinglot.repositories.ParkingLotRepository;
import com.design.parkinglot.repositories.TicketRepository;
import com.design.parkinglot.services.ParkingLotService;
import com.design.parkinglot.services.TicketService;
import com.design.parkinglot.strategies.spotassignmentstrategy.RandomSpotAssignmentStrategy;
import com.design.parkinglot.strategies.spotassignmentstrategy.SpotAssignmentStrategy;

public class Client {
    public static void main(String[] args) {
        ObjectRegistry.setObjectsregistry("parkingLotRepository", new ParkingLotRepository());
        ObjectRegistry.setObjectsregistry("parkingLotService", new ParkingLotService((ParkingLotRepository) ObjectRegistry.getObjectsregistry("parkingLotRepository")));
        ObjectRegistry.setObjectsregistry("parkingLotController", new ParkingLotController((ParkingLotService) ObjectRegistry.getObjectsregistry("parkingLotService")));


        ParkingLotController parkingLotController = (ParkingLotController) ObjectRegistry.getObjectsregistry("parkingLotController");
        CreateParkingLotRequestDto requestDto = new CreateParkingLotRequestDto();
        requestDto.setAddress("Pune");
        requestDto.setNumberOfFloors(4);

        CreateParkingLotResponseDto responseDto = parkingLotController.createParkingLot(requestDto);

        System.out.println(requestDto);


        UpdatePLotAddressRequestDto updatePLotAddressRequestDto = new UpdatePLotAddressRequestDto();
        updatePLotAddressRequestDto.setParkingLotId(1L);
        updatePLotAddressRequestDto.setAddress("Hyderabad");
        UpdatePLotAddressResponseDto responseDto1 = parkingLotController.updateAddress(updatePLotAddressRequestDto);
        System.out.println(responseDto1);

        ObjectRegistry.setObjectsregistry("ticketRepository", new TicketRepository());
        ObjectRegistry.setObjectsregistry("spotAssignmentStrategy", new RandomSpotAssignmentStrategy());
        ObjectRegistry.setObjectsregistry("ticketService", new TicketService((TicketRepository) ObjectRegistry.getObjectsregistry("ticketRepository"),
                (ParkingLotRepository)ObjectRegistry.getObjectsregistry("parkingLotRepository"),
                (RandomSpotAssignmentStrategy)ObjectRegistry.getObjectsregistry("spotAssignmentStrategy")));
        ObjectRegistry.setObjectsregistry("ticketController", new TicketController((TicketService) ObjectRegistry.getObjectsregistry("ticketService")));

        // Assignment
        // Add a spot of a type to a floor of a Parking Lot

        AddSpotRequestDto addSpotRequestDto = new AddSpotRequestDto();
        ParkingSpot parkingSpot = new ParkingSpot();
        parkingSpot.setParkingFloor(new ParkingFloor());
        parkingSpot.setSpotNumber(6);
        parkingSpot.setParkingSpotStatus(ParkingSpotStatus.AVAILABLE);
        addSpotRequestDto.setParkingSpot(parkingSpot);
        addSpotRequestDto.setParkingLotId(1L);
        AddSpotResponseDto addSpotResponseDto = parkingLotController.addSpotToFloorOfParkingLot(addSpotRequestDto);
        System.out.println(addSpotResponseDto);

        //Assignment
        // Ticket Controller
        // Generate Ticket

        TicketController ticketController = (TicketController) ObjectRegistry.getObjectsregistry("ticketController");
        EntryGate entryGate = new EntryGate();
        entryGate.setGateNumber(4);
        entryGate.setGateStatus(GateStatus.OPEN);

        GenerateTicketRequestDto generateTicketRequestDto= new GenerateTicketRequestDto();
        generateTicketRequestDto.setSpotType(SpotType.CAR_PREMIUM);
        generateTicketRequestDto.setParkingLotId(1L);
        generateTicketRequestDto.setEntryGate(entryGate);

        GenerateTicketResponseDto generateTicketResponseDto = ticketController.generateTicket(generateTicketRequestDto);

        System.out.println(generateTicketResponseDto);

        // Assignment
        // Create a New Vehicle -> Vehicle Controller

    }
}
