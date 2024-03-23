package com.Design.DesignTicTacToe.controller;

import com.Design.DesignTicTacToe.exceptions.EmptyMovesUndoOperationException;
import com.Design.DesignTicTacToe.models.Game;
import com.Design.DesignTicTacToe.models.GameStatus;
import com.Design.DesignTicTacToe.models.Player;
import com.Design.DesignTicTacToe.strategies.gamewinningstrategies.GameWinningStrategy;

import java.util.List;

public class GameController {
    public Game createGame(int dimensionOfBoard, List<Player> players, List<GameWinningStrategy> strategies){
        Game game = null;

        try{
            game = Game.create().setDimension(dimensionOfBoard)
                    .addPlayers(players)
                    .addGameWinningStrategies(strategies)
                    .build();
        } catch (Exception e){
            System.out.println("We did something wrong");
            e.printStackTrace();
        }
        return game;
    }

    public void makeMove(Game game){
        game.makeMove();
    }

    public boolean undo(Game game) throws EmptyMovesUndoOperationException {
        return game.undo();
    }

    public Player getWinner(Game game){
        return game.getWinner();
    }

    public GameStatus getGameStatus(Game game){
        return game.getGameStatus();
    }

    public void display(Game game){
        game.getBoard().printBoard();
    }
}
