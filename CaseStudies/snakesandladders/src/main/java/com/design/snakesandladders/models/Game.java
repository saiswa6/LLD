package com.design.snakesandladders.models;

import com.design.snakesandladders.exceptions.InvalidDiceNumberException;
import com.design.snakesandladders.strategies.playermovestrategies.PlayerMoveStrategy;
import com.design.snakesandladders.strategies.unlockdicestrategies.UnlockDiceStrategy;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Board board;
    private Dice dice;
    private List<Player> players;
    //private int totalButtonsAllowedPerPerson;
    private GameStatus gameStatus;
    private List<Player> rankingsOfPlayers;
    private int lastPlayerMovedIndex;
    private PlayerMoveStrategy playerMoveStrategy;
    private List<UnlockDiceStrategy> unlockDiceStrategy;

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    private Game(){
        players = new ArrayList<>();
        gameStatus = GameStatus.GAME_IN_PROGRESS;
        rankingsOfPlayers = new ArrayList<>();
        lastPlayerMovedIndex = -1;
        unlockDiceStrategy = new ArrayList<>();
    }

    public static Builder create(){
        return new Builder();
    }

    public void makeMove(){
        lastPlayerMovedIndex +=1;
        lastPlayerMovedIndex = lastPlayerMovedIndex % players.size();

        Player player = players.get(lastPlayerMovedIndex);
        playerMoveStrategy.makeMove(player, dice.rollAndReturnRandomDiceNumber(), board);

        int countOfButtonEnd = 0;
        for(Button button : player.getButtons()){
            if(ButtonStatus.ENDED == button.getButtonStatus()){
                countOfButtonEnd++;
            }
        }
        if(countOfButtonEnd == player.getButtons().size()){
            rankingsOfPlayers.add(player);
        }
        if(rankingsOfPlayers.size() == players.size() - 1){
            gameStatus = GameStatus.GAME_COMPLETED;
            displayRankingsList();
        }
    }

    public void displayRankingsList(){
        int i = 1;
        for(Player player : rankingsOfPlayers){
            System.out.println("Rank "+ i + " : "+ player.name);
        }
    }


    public static class Builder{
        private int sizeOfBoard;
        private int maxDiceNumber;
        private List<Player> players;
        //private int totalButtonsAllowedPerPerson;
        private List<UnlockDiceStrategy> unlockDiceStrategy;
        private PlayerMoveStrategy playerMoveStrategy;

        Builder(){
            players = new ArrayList<>();
            unlockDiceStrategy = new ArrayList<>();
        }

        public Builder setSizeOfBoard(int sizeOfBoard){
            this.sizeOfBoard = sizeOfBoard;
            return this;
        }

        public Builder setMaxDiceNumber(int maxDiceNumber){
            this.maxDiceNumber = maxDiceNumber;
            return this;
        }

        public Builder addPlayer(Player player){
            this.players.add(player);
            return this;
        }

        public Builder addPlayers(List<Player> players){
            this.players.addAll(players);
            return this;
        }

//        public Builder setTotalButtonsAllowedPerPerson(int totalButtonsAllowedPerPerson){
//            this.totalButtonsAllowedPerPerson = totalButtonsAllowedPerPerson;
//            return this;
//        }

        public Builder addUnlockDiceStrategy(UnlockDiceStrategy unlockDiceStrategy){
            this.unlockDiceStrategy.add(unlockDiceStrategy);
            return this;
        }

        public Builder setPlayerMoveStrategy(PlayerMoveStrategy playerMoveStrategy){
            this.playerMoveStrategy = playerMoveStrategy;
            return this;
        }

        public Game build() throws InvalidDiceNumberException {
            if(maxDiceNumber > sizeOfBoard){
                throw new InvalidDiceNumberException();
            }

            Game game = new Game();
            game.board = new Board(sizeOfBoard);
            game.dice = new Dice(maxDiceNumber);
            game.players.addAll(players);
            game.unlockDiceStrategy.addAll(unlockDiceStrategy);
            game.playerMoveStrategy = this.playerMoveStrategy;
            return game;
        }

    }

}
