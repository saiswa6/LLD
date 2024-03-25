package com.design.parkinglot.strategies.spotassignmentstrategy;

import com.design.parkinglot.models.EntryGate;
import com.design.parkinglot.models.ParkingLot;
import com.design.parkinglot.models.ParkingSpot;
import com.design.parkinglot.models.SpotType;

public interface SpotAssignmentStrategy {

    ParkingSpot assignSpot(ParkingLot parkingLott, SpotType spotType, EntryGate entryGate);
}
