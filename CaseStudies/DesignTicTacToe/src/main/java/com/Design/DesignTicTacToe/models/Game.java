package com.Design.DesignTicTacToe.models;

import com.Design.DesignTicTacToe.exceptions.EmptyMovesUndoOperationException;
import com.Design.DesignTicTacToe.exceptions.MultipleBotsException;
import com.Design.DesignTicTacToe.strategies.gamewinningstrategies.GameWinningStrategy;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Board board;
    private List<Player> players;
    private List<Move> moves;

    private List<GameWinningStrategy> gameWinningStrategies;

    private GameStatus gameStatus;

    int lastPlayerMovedIndex;

    private Player winner;

    public Player getWinner() {
        return winner;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public Board getBoard() {
        return board;
    }

    private Game(){
        this.players = new ArrayList<>();
        this.moves = new ArrayList<>();
        this.gameWinningStrategies = new ArrayList<>();
        this.lastPlayerMovedIndex = -1;
        this.gameStatus = GameStatus.RUNNING;
    }

    public void makeMove(){
        this.lastPlayerMovedIndex +=1;
        this.lastPlayerMovedIndex %= this.players.size();

        Move move = this.players.get(lastPlayerMovedIndex).makeMove(this.board);
        this.moves.add(move);

        for(GameWinningStrategy strategy : gameWinningStrategies){
            if(strategy.checkIfWon(this.board,this.players.get(lastPlayerMovedIndex), move.getCell())){
                gameStatus = GameStatus.WON;
                winner = this.players.get(lastPlayerMovedIndex);
                return;
            }
        }

        move.getCell().setSymbol(move.getSymbol());

        if(moves.size() == this.board.getDimension() * this.board.getDimension()){
            gameStatus = GameStatus.DRAW;
            return;
        }
    }

    public boolean undo() throws EmptyMovesUndoOperationException {
        if(this.moves.size() == 0){
            throw new EmptyMovesUndoOperationException();
        }

        Move lastMove = this.moves.get(moves.size() - 1);
        Cell relevanrCell = lastMove.getCell();
        relevanrCell.clearCell();
        this.lastPlayerMovedIndex -= 1;
        this.lastPlayerMovedIndex = (this.lastPlayerMovedIndex + this.players.size()) % this.players.size();
        this.moves.remove(lastMove);
        return true;
    }

    public static Builder create(){
        return new Builder();
    }

    public static class Builder{

        // All the parameters of Game will not be there in Builder class. will keep only whatever needed from client
        private List<Player> players;
        private List<GameWinningStrategy> gameWinningStrategies;
        private int dimension;

        Builder(){
            players = new ArrayList<>();
            gameWinningStrategies = new ArrayList<>();
        }

//        public Builder setPlayers(List<Player> players){
//            this.players= players;
//            return this;
//        }

        // Game.Builder.serPlayers
        // Game game = Game.Builder.addPlayer(new HumanPlayer())
        //                          .addPlayer(new Bot()
        //                          .build()


        // Using add instead of setplayer
        public Builder addPlayer(Player player){
            this.players.add(player);
            return this;
        }

        public Builder addPlayers(List<Player> players){
            this.players.addAll(players);
            return this;
        }

        public Builder addGameWinningStrategy(GameWinningStrategy gameWinningStrategy){
            this.gameWinningStrategies.add(gameWinningStrategy);
            return this;
        }

        public Builder addGameWinningStrategies(List<GameWinningStrategy> gameWinningStrategy){
            this.gameWinningStrategies.addAll(gameWinningStrategy);
            return this;
        }

        public Builder setDimension(int dimension){
            this.dimension = dimension;
            return this;
        }

        public boolean checkIfSinglrBotMax(){
            int count  = 0;

            for (Player player : players){
                if(player.playerType == PlayerType.BOT){
                    count++;
                }
            }
            return count <=1;
        }

        public Game build() throws MultipleBotsException {
            if(!checkIfSinglrBotMax()){
                throw new MultipleBotsException();
            }

            /*
            You can throw below exceptions
            if(players.size() < 2){

            }
            if(dimension < 3){

            }
            if(gameWinningStrategies.size() < 1){

            }*/

            Game game = new Game();
            game.players.addAll(this.players);
            game.gameWinningStrategies.addAll(this.gameWinningStrategies);
            game.board = new Board(dimension);
            return game;
        }

    }
}
