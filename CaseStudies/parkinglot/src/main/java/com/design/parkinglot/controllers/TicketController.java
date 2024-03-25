package com.design.parkinglot.controllers;

import com.design.parkinglot.dtos.GenerateTicketRequestDto;
import com.design.parkinglot.dtos.GenerateTicketResponseDto;
import com.design.parkinglot.models.Ticket;
import com.design.parkinglot.services.TicketService;

public class TicketController {
    private TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    public GenerateTicketResponseDto generateTicket(GenerateTicketRequestDto generateTicketRequestDto){

        Ticket ticket = ticketService.generateTicket(generateTicketRequestDto.getParkingLotId(), generateTicketRequestDto.getVehicle(),generateTicketRequestDto.getSpotType(),
                generateTicketRequestDto.getEntryGate());

        GenerateTicketResponseDto generateTicketResponseDto = new GenerateTicketResponseDto();
        generateTicketResponseDto.setTicket(ticket);
        return generateTicketResponseDto;
    }
}
