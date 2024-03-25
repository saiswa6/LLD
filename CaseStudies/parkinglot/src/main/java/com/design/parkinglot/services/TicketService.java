package com.design.parkinglot.services;

import com.design.parkinglot.models.*;
import com.design.parkinglot.repositories.ParkingLotRepository;
import com.design.parkinglot.repositories.TicketRepository;
import com.design.parkinglot.strategies.spotassignmentstrategy.SpotAssignmentStrategy;

import java.util.Date;

public class TicketService {
    private TicketRepository ticketRepository;
    private ParkingLotRepository parkingLotRepository;

    private SpotAssignmentStrategy spotAssignmentStrategy;

    public TicketService(TicketRepository ticketRepository, ParkingLotRepository parkingLotRepository, SpotAssignmentStrategy spotAssignmentStrategy) {
        this.ticketRepository = ticketRepository;
        this.parkingLotRepository = parkingLotRepository;
        this.spotAssignmentStrategy = spotAssignmentStrategy;
    }

    public Ticket generateTicket(Long parkingLotId, Vehicle vehicle, SpotType spotType, EntryGate entryGate){
        ParkingLot parkingLot = parkingLotRepository.getParkingLotById(parkingLotId);

        ParkingSpot parkingSpot = spotAssignmentStrategy.assignSpot(parkingLot, spotType, entryGate);
        if(parkingSpot == null){
            return null;
        }

        Ticket ticket = new Ticket();
        ticket.setEntryGate(entryGate);
        ticket.setEntryTime(new Date());
        ticket.setVehicle(vehicle);
        ticket.setParkingSpot(parkingSpot);
        ticket.setOperator(entryGate.getOperator());

        ticketRepository.save(ticket);

        return ticket;

    }
}
