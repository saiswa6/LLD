package com.Design.DesignTicTacToe.strategies.botplayingstrategies;

import com.Design.DesignTicTacToe.models.Board;
import com.Design.DesignTicTacToe.models.Move;
import com.Design.DesignTicTacToe.models.Player;
import com.Design.DesignTicTacToe.models.Symbol;

public interface BotPlayingStrategy {
    Move makeNextMove(Board board, Player player);
}
