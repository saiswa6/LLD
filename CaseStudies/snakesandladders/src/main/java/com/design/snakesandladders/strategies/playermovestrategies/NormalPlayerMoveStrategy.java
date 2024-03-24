package com.design.snakesandladders.strategies.playermovestrategies;

import com.design.snakesandladders.models.*;
import com.design.snakesandladders.strategies.unlockdicestrategies.UnlockDiceStrategy;
import com.design.snakesandladders.strategies.unlockdicestrategies.valueBasedUnlockDiceStrategy;

import java.util.Random;
import java.util.Scanner;

public class NormalPlayerMoveStrategy implements PlayerMoveStrategy{
    @Override
    public void makeMove(Player player, int randomDiceNumber, Board board) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("After Dice is rolled, Random number is "+ randomDiceNumber + " for player "+ player.getName()+ ". Which button you want to move from 1 to "+ player.getButtons().size() +" ?");
        int buttonNumber = scanner.nextInt();
        Button selectedButtonByPlayer = player.getButtons().get(buttonNumber - 1);
        int nextMovePosition = selectedButtonByPlayer.getPosition() + randomDiceNumber;

        if(selectedButtonByPlayer.getPosition() == 0 || selectedButtonByPlayer.getButtonStatus() == ButtonStatus.LOCKED){
            UnlockDiceStrategy unlockDiceStrategy = new valueBasedUnlockDiceStrategy();
            if(unlockDiceStrategy.canUnlock(randomDiceNumber)){
                selectedButtonByPlayer.setButtonStatus(ButtonStatus.IN_GAME);
            } else {
                selectedButtonByPlayer.setButtonStatus(ButtonStatus.LOCKED);
            }
        }

        ForeignEntity foreignEntity = board.getIntegerForeignEntityMap().get(nextMovePosition);
        if(foreignEntity == null){
            selectedButtonByPlayer.setPosition(nextMovePosition);
        } else if (foreignEntity.getForeignEntityType() == ForeignEntityType.LADDER || foreignEntity.getForeignEntityType() == ForeignEntityType.FROG
                   || foreignEntity.getForeignEntityType() == ForeignEntityType.SNAKE) {
            nextMovePosition = foreignEntity.getTo();
            selectedButtonByPlayer.setPosition(nextMovePosition);
        }

        if (nextMovePosition >= board.getSizeOfBoard()){
            selectedButtonByPlayer.setButtonStatus(ButtonStatus.ENDED);
        }

    }
}
