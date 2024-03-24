package com.design.snakesandladders.models;

public abstract class ForeignEntity {
    private ForeignEntityType foreignEntityType;
    private int from;
    private int to;

    public ForeignEntityType getForeignEntityType() {
        return foreignEntityType;
    }

    public void setForeignEntityType(ForeignEntityType foreignEntityType) {
        this.foreignEntityType = foreignEntityType;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    ForeignEntity(ForeignEntityType foreignEntityType){
        this.foreignEntityType = foreignEntityType;
    }

}
