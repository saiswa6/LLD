package com.design.snakesandladders.models;

import java.util.ArrayList;
import java.util.List;

public class Player {
    List<Button> buttons;
    String name;
    public Player(String name, int numberOfButtons){
        this.name = name;
        buttons = new ArrayList<>();
        for(int i = 0;i < numberOfButtons; i++){
            buttons.add(new Button());
        }
    }

    public List<Button> getButtons() {
        return buttons;
    }

    public String getName() {
        return name;
    }
}
