package com.Design.DesignTicTacToe.models;

public class Cell {
    private int row;
    private int column;
    private Symbol symbol;

    public boolean isEmpty(){
        return symbol == null;
    }

    public void clearCell(){
        this.symbol = null;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }
}
