package org.concurrency.Questions.ImplementSendEmailService.Imp1;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        for(Integer x : list) {
            System.out.println(" Address of " + x + " is " + System.identityHashCode(x));
        }

        list.remove(3);

        for(Integer x : list) {
            System.out.println(" Address of " + x + " is " + System.identityHashCode(x));
        }
    }
}
