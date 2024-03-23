package com.Design.DesignTicTacToe.models;

import java.util.ArrayList;
import java.util.List;

public class Board {
    public Board(int dimension){
        this.dimension = dimension;

        board = new ArrayList<>();
        for(int i = 0; i< dimension;i++){
            board.add(new ArrayList<>());
            for(int j=0;j < dimension; j++){
                board.get(i).add(new Cell());
            }
        }
    }
    private List<List<Cell>> board;
    private int dimension;

    public List<List<Cell>> getBoard() {
        return board;
    }

    public int getDimension() {
        return dimension;
    }

    public void setBoard(List<List<Cell>> board) {
        this.board = board;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }
    Cell getCell(int i, int j) {
        return board.get(i).get(j);
    }

    public void printBoard(){
        for(List<Cell> row : board){
            for(Cell cell: row){
                if(cell.getSymbol() == null){
                    System.out.print("|   |");
                } else {
                    System.out.print("| "+ cell.getSymbol().getCharacter()+ " |");
                }
            }
            System.out.println();
        }
    }
}
