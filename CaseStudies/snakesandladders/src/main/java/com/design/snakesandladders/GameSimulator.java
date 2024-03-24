package com.design.snakesandladders;

import com.design.snakesandladders.controllers.GameController;
import com.design.snakesandladders.exceptions.InvalidDiceNumberException;
import com.design.snakesandladders.models.Game;
import com.design.snakesandladders.models.GameStatus;
import com.design.snakesandladders.models.Player;
import com.design.snakesandladders.strategies.playermovestrategies.NormalPlayerMoveStrategy;
import com.design.snakesandladders.strategies.unlockdicestrategies.valueBasedUnlockDiceStrategy;

import java.util.List;

public class GameSimulator {
    public static void main(String[] args) throws InvalidDiceNumberException {
        Player player1 = new Player("Sai", 2);
        Player player2 = new Player("Ram", 2);



        GameController gameController  = new GameController();
        Game game = gameController.create(100,6, List.of(player1,player2),new valueBasedUnlockDiceStrategy(), new NormalPlayerMoveStrategy());

        while (gameController.getGameStatus(game) == GameStatus.GAME_IN_PROGRESS){
            System.out.println("Game Simulator");
            gameController.makeMove(game);
        }

        System.out.println("Game Completed");
    }
}
