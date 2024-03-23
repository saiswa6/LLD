package com.Design.DesignTicTacToe.models;

import com.Design.DesignTicTacToe.factories.botplayingstrategyfactory.BotPlayingStrategyFactory;
import com.Design.DesignTicTacToe.strategies.botplayingstrategies.BotPlayingStrategy;

import java.util.PrimitiveIterator;

public class Bot extends Player{
    private BotDifficultyLevel botDifficultyLevel;
    private BotPlayingStrategy botPlayingStrategy;

    public Bot(Symbol symbol, BotDifficultyLevel botDifficultyLevel){
        super(PlayerType.BOT, symbol);
        this.botDifficultyLevel = botDifficultyLevel;
        this.botPlayingStrategy = new BotPlayingStrategyFactory().createBotPlayingStrategyForDifficultyLevel(botDifficultyLevel);
    }

    @Override
    public Move makeMove(Board board) {
        return this.botPlayingStrategy.makeNextMove(board,this);
    }
}
