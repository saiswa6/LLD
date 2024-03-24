package com.design.snakesandladders.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
    private int sizeOfBoard;
    private List<Integer> boardList;
    private Map<Integer, ForeignEntity> integerForeignEntityMap;

    Board(int sizeOfBoard){
        boardList = new ArrayList<>(sizeOfBoard);
        integerForeignEntityMap = new HashMap<>();
    }

    public int getSizeOfBoard() {
        return sizeOfBoard;
    }

    public void setSizeOfBoard(int sizeOfBoard) {
        this.sizeOfBoard = sizeOfBoard;
    }

    public Map<Integer, ForeignEntity> getIntegerForeignEntityMap() {
        return integerForeignEntityMap;
    }

    public void setIntegerForeignEntityMap(Map<Integer, ForeignEntity> integerForeignEntityMap) {
        this.integerForeignEntityMap = integerForeignEntityMap;
    }
}
