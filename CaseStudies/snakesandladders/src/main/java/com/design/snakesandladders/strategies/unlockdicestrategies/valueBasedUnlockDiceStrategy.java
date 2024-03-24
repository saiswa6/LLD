package com.design.snakesandladders.strategies.unlockdicestrategies;

public class valueBasedUnlockDiceStrategy implements UnlockDiceStrategy{
    @Override
    public boolean canUnlock(int value) {
        return value == 1 || value == 6;
    }
}
