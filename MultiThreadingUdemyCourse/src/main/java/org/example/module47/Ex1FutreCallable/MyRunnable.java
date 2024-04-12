package org.example.module47.Ex1FutreCallable;

import java.util.List;

public class MyRunnable implements Runnable{
    private List<Integer> list;

    MyRunnable(List<Integer> list){
        this.list = list;
    }

    @Override
    public void run() {
        System.out.println("Inside MyRunnable");
        System.out.println("Task2 with runnable and result");
        list.add(300);
    }
}
