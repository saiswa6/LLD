package com.design.parkinglot.dtos;

public class UpdatePLotAddressRequestDto {
    private String address;
    private Long parkingLotId;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getParkingLotId() {
        return parkingLotId;
    }

    public void setParkingLotId(Long parkingLotId) {
        this.parkingLotId = parkingLotId;
    }
}
