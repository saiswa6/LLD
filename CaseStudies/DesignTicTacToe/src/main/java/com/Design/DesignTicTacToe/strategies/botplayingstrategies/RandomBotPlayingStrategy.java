package com.Design.DesignTicTacToe.strategies.botplayingstrategies;

import com.Design.DesignTicTacToe.models.*;

import java.util.List;

public class RandomBotPlayingStrategy implements BotPlayingStrategy{
    @Override
    public Move makeNextMove(Board board, Player player) {
        for(List<Cell> row : board.getBoard()) {
            for (Cell cell : row) {
                if(cell.isEmpty()){
                    Move move = new Move();
                    move.setCell(cell);
                    move.setPlayer(player);
                    move.setSymbol(player.getSymbol());
                    return move;
                }
            }
        }
        return null;
    }
}
