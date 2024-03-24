package com.design.snakesandladders.strategies.playermovestrategies;

import com.design.snakesandladders.models.Board;
import com.design.snakesandladders.models.Button;
import com.design.snakesandladders.models.Player;

public interface PlayerMoveStrategy {
    void makeMove(Player player, int maxDiceValue, Board board);
}
