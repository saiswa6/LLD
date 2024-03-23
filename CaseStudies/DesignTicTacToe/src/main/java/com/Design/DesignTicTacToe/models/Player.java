package com.Design.DesignTicTacToe.models;

public abstract class Player {
    private Symbol symbol;
    private String name;

    PlayerType playerType;

    Player(PlayerType playerType, Symbol symbol){
        this.playerType = playerType;
        this.symbol = symbol;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public abstract Move makeMove(Board board);
}
