package com.design.parkinglot.repositories;

import com.design.parkinglot.models.Ticket;

import java.util.HashMap;
import java.util.Map;

public class TicketRepository {
    private Map<Long, Ticket> ticketMap = new HashMap<>();
    Long lastCountForId = 0L;

    public Ticket save(Ticket ticket){
        lastCountForId++;
        ticket.setId(lastCountForId);
        ticketMap.put(lastCountForId,ticket);
        return ticket;
    }

    public Ticket getByTicketId(Long ticketId){
        return ticketMap.get(ticketId);
    }
}
