package com.design.parkinglot.models;

public abstract class BaseModel {
    private Long Id;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }
}
