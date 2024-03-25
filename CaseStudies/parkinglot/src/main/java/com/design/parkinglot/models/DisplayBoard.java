package com.design.parkinglot.models;

import java.util.Map;

public class DisplayBoard extends BaseModel{
    private Map<SpotType, Integer> displayMap;

    public Map<SpotType, Integer> getDisplayMap() {
        return displayMap;
    }

    public void setDisplayMap(Map<SpotType, Integer> displayMap) {
        this.displayMap = displayMap;
    }
}
