package org.concurrency.Concepts.DebuggingRound;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
//Do not change anything in the main
// For Debugging round, write all the concepts on paper and keep it to get in mind and try.
public class Solution {

    static HashMap<Integer, ArrayList<Integer>> accountTranactionMap = new HashMap<Integer, ArrayList<Integer>>();

    static HashMap<Integer, Integer> results = new HashMap<Integer, Integer>();


    static class  TransactionFactory implements  Runnable{

        int numTransactions = 100;

        @Override
        public void run() {
            System.out.println("Entering Transactionfactory");
            int i = numTransactions;

            while (i > 0) {
                try{
                    int randomAccountNumber = ThreadLocalRandom.current().nextInt(0,100);
                    int randomAmount = ThreadLocalRandom.current().nextInt(-1000,1000);

                    ArrayList<Integer> values = accountTranactionMap.get(randomAccountNumber);
                    if(values == null){
                        values = new ArrayList<>();
                        accountTranactionMap.put(randomAccountNumber, values);
                        results.put(randomAccountNumber, 0);
                        i--;
                    }
                    values.add(randomAmount);
                    results.put(randomAccountNumber, results.get(randomAccountNumber) + randomAmount);
                    Thread.sleep(1);
                } catch (InterruptedException ie){
                    ie.printStackTrace();;
                }
            }
            System.out.println("Exiting TransactionFactory");
        }
    }

    static class  Aggregator implements  Runnable{

        int m_account ;
        Aggregator(int m_account){
            this.m_account = m_account;
        }


        @Override
        public void run() {
            System.out.println("Aggregator running for account " + m_account);
            int sum =0;
            int prevSize = 0;
            List<Integer> amounts =  accountTranactionMap.get(m_account);
            if(amounts != null && amounts.size() > prevSize) {
                while(amounts.size() > prevSize) {
                    sum+=amounts.get(prevSize);
                    prevSize++;
                }
            }
            System.out.println("Aggregation for account "+m_account + " is " + sum);
            if(results.get(m_account) != null && sum != results.get(m_account)){
                System.out.println("Aggregation is incorrect. Expected :" + results.get(m_account) + " Found " + sum);
            } else {
                System.out.println("Aggregation is correct");
            }
        }
    }

    static class Printer implements Runnable{
        int m_account;

        Printer(int m_account){
            this.m_account = m_account;
        }


        @Override
        public void run() {
            System.out.println("Printer running for Account " + m_account+ " value " + accountTranactionMap.get(m_account));
        }
    }
    public static void main(String[] args) {
        Thread transactionFactory = new Thread(new TransactionFactory());
        Thread aggregator2 = new Thread(new Aggregator(2));
        Thread aggregator3 = new Thread(new Aggregator(3));
        Thread printer2 = new Thread(new Printer(2));
        Thread printer3 = new Thread(new Printer(3));

        transactionFactory.start();
        aggregator2.start();
        aggregator3.start();

        printer2.start();
        printer3.start();

    }
}
