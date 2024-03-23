package com.Design.DesignTicTacToe.strategies.gamewinningstrategies;

import com.Design.DesignTicTacToe.models.Board;
import com.Design.DesignTicTacToe.models.Cell;
import com.Design.DesignTicTacToe.models.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderOneGameWinningStrategy implements GameWinningStrategy{
    private List<HashMap<Character, Integer>> rowCharCounts;
    private List<HashMap<Character, Integer>> colCharCounts;

    public void initializeCounts(Board board){
        rowCharCounts = new ArrayList<>();
        colCharCounts = new ArrayList<>();

        for(int i=0; i < board.getDimension();i++){
            rowCharCounts.add(new HashMap<>());
            colCharCounts.add(new HashMap<>());
        }
    }
    @Override
    public boolean checkIfWon(Board board, Player player, Cell moveCell) {
        if(rowCharCounts == null){
            initializeCounts(board);
        }
        int row = moveCell.getRow();
        int col = moveCell.getColumn();

        if(!rowCharCounts.get(row).containsKey(moveCell.getSymbol().getCharacter())){
            rowCharCounts.get(row).put(moveCell.getSymbol().getCharacter(),0);
        }

        if(!colCharCounts.get(col).containsKey(moveCell.getSymbol().getCharacter())){
            colCharCounts.get(col).put(moveCell.getSymbol().getCharacter(),0);
        }

        rowCharCounts.get(row).put(moveCell.getSymbol().getCharacter(),
                rowCharCounts.get(row).get(moveCell.getSymbol().getCharacter()) + 1);

        colCharCounts.get(row).put(moveCell.getSymbol().getCharacter(),
                colCharCounts.get(row).get(moveCell.getSymbol().getCharacter()) + 1);

        if(rowCharCounts.get(row).get(moveCell.getSymbol().getCharacter()).equals(board.getDimension())){
            return true;
        }

        if(rowCharCounts.get(col).get(moveCell.getSymbol().getCharacter()).equals(board.getDimension())){
            return true;
        }

        return false;
    }
}
