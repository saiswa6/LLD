package com.design.snakesandladders.controllers;

import com.design.snakesandladders.exceptions.InvalidDiceNumberException;
import com.design.snakesandladders.models.Game;
import com.design.snakesandladders.models.GameStatus;
import com.design.snakesandladders.models.Player;
import com.design.snakesandladders.strategies.playermovestrategies.PlayerMoveStrategy;
import com.design.snakesandladders.strategies.unlockdicestrategies.UnlockDiceStrategy;

import java.util.List;

public class GameController {

    public Game create(int sizeOfBoard, int maxDiceNumber, List<Player> players, UnlockDiceStrategy unlockDiceStrategy, PlayerMoveStrategy playerMoveStrategy) throws InvalidDiceNumberException {
        Game game = null;

        try{
            game = Game.create().addPlayers(players).setSizeOfBoard(sizeOfBoard).
                    setMaxDiceNumber(maxDiceNumber).addUnlockDiceStrategy(unlockDiceStrategy).setPlayerMoveStrategy(playerMoveStrategy).build();
        } catch (Exception e){
            System.out.println("Something Fishy Happened");
            e.printStackTrace();
        }
        return game;
    }

    public void makeMove(Game game){
        game.makeMove();
    }

    public GameStatus getGameStatus(Game game){
        return game.getGameStatus();
    }

    public void display(){

    }
}
