package com.Design.DesignTicTacToe.models;

public class Move {
    private Cell cell;
    private Player player;
    private Symbol symbol;

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public Cell getCell() {
        return cell;
    }

    public Player getPlayer() {
        return player;
    }

    public Symbol getSymbol() {
        return symbol;
    }
}
