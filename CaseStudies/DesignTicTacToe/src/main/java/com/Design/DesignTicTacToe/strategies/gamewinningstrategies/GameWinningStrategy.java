package com.Design.DesignTicTacToe.strategies.gamewinningstrategies;

import com.Design.DesignTicTacToe.models.Board;
import com.Design.DesignTicTacToe.models.Cell;
import com.Design.DesignTicTacToe.models.Player;

public interface GameWinningStrategy {

    boolean checkIfWon(Board board, Player player, Cell moveCell);
}
