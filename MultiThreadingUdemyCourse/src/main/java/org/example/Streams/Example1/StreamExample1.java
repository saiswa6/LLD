package org.example.Streams.Example1;

import java.util.ArrayList;
import java.util.List;

public class StreamExample1 {
    public static void main(String[] args){
        List<Integer> salaryList = new ArrayList<>();
        salaryList.add(3000);
        salaryList.add(2000);
        salaryList.add(5000);
        salaryList.add(4099);
        salaryList.add(1116);
        salaryList.add(6666);

        /*
        // Normal Way
        int count = 0;

        for(Integer salary : salaryList){
            if(salary > 3000){
                count++;
            }
        }

        System.out.println("Total Employee with salary > 3000: " + count);
        */


        // Using Streams
        long output = salaryList.stream().filter(
                (Integer salary) -> salary > 3000
        ).count();

        System.out.println("Total Employee with salary > 3000: " + output);
    }
}
