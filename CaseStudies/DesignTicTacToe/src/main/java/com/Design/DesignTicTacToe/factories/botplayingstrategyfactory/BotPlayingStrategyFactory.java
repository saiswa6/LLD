package com.Design.DesignTicTacToe.factories.botplayingstrategyfactory;

import com.Design.DesignTicTacToe.models.BotDifficultyLevel;
import com.Design.DesignTicTacToe.strategies.botplayingstrategies.BotPlayingStrategy;
import com.Design.DesignTicTacToe.strategies.botplayingstrategies.RandomBotPlayingStrategy;

public class BotPlayingStrategyFactory {

    public BotPlayingStrategy createBotPlayingStrategyForDifficultyLevel(BotDifficultyLevel botDifficultyLevel){
        return switch (botDifficultyLevel) {
            case EASY,MEDIUM,HARD -> new RandomBotPlayingStrategy();
        };
    }
}
