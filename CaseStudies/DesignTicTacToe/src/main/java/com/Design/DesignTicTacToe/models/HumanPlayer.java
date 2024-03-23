package com.Design.DesignTicTacToe.models;

import java.util.Scanner;

public class HumanPlayer extends Player{
    public HumanPlayer(Symbol symbol){
        super(PlayerType.HUMAN, symbol);
    }

    @Override
    public Move makeMove(Board board) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Tell Row Number where you want to make move from 1");
        int row  = scanner.nextInt();

        System.out.println("Tell Column Number where you want to make move from 1");
        int column  = scanner.nextInt();

        Move move = new Move();
        move.setCell(board.getCell(row-1,column-1));
        move.setPlayer(this);
        move.setSymbol(this.getSymbol());
        return move;
    }
}
