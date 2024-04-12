package org.concurrency.Questions.ImplementBrowser.Imp1;


/*
Implement a thread-safe data structure which can keep track of number of incoming
requests grouped by IP Address over a time window. Add support for grouping by other
attributes such as BrowserAgent.

1st WAY - keep methods as synchronized
2nd WAY - Use reentrant lock
 */


/*
- We are using HashMap<String, Integer> to store the request counts directly.
- ReentrantReadWriteLock is used to provide read-write locking for thread safety.
- incrementRequestCountByIp() and incrementRequestCountByBrowserAgent() methods increment the request count for the given IP Address or Browser Agent.
- getRequestCountByIp() and getRequestCountByBrowserAgent() methods retrieve the request count for the given IP Address or Browser Agent.
- This revised RequestCounter class provides a thread-safe implementation using HashMap and ReentrantReadWriteLock to ensure that concurrent updates are handled safely and efficiently.
 */

import java.util.HashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ThreadSafeBrowser {
    private HashMap<String, Integer> ipMap;
    private HashMap<String,Integer> browserAgentMap;

    private ReentrantReadWriteLock ipLock = new ReentrantReadWriteLock();
    private ReentrantReadWriteLock browserAgentLock = new ReentrantReadWriteLock();

    public ThreadSafeBrowser(){
        ipMap = new HashMap<>();
        browserAgentMap = new HashMap<>();
    }


    public void incrementRequestCountByIp(String ipAddress) {
        ipLock.writeLock().lock();
        try{
            ipMap.put(ipAddress, ipMap.getOrDefault(ipAddress, 0) + 1);
        } finally {
            ipLock.writeLock().unlock();
        }
    }

    public void incrementRequestCountByBrowserAgent(String browserAgent) {
        browserAgentLock.writeLock().lock();
        try{
            browserAgentMap.put(browserAgent, browserAgentMap.getOrDefault(browserAgent, 0) + 1);
        } finally {
            browserAgentLock.writeLock().unlock();
        }
    }

    public int getRequestCountByIp(String ipAddress) {
        ipLock.readLock().lock();
        try {
            {
                return ipMap.getOrDefault(ipAddress,0);
            }
        } finally {
            ipLock.readLock().unlock();
        }
    }

    public int getRequestCountByBrowserAgent(String browserAgent) {
        browserAgentLock.readLock().lock();
        try {
            return browserAgentMap.getOrDefault(browserAgent, 0);
        } finally {
            browserAgentLock.readLock().unlock();
        }
    }

    public static void main(String[] args) {
        ThreadSafeBrowser counter = new ThreadSafeBrowser();

        // Simulate incoming requests
        for (int i = 0; i < 10; i++) {
            counter.incrementRequestCountByIp("192.168.1.1");
            counter.incrementRequestCountByBrowserAgent("Chrome");
        }

        for (int i = 0; i < 5; i++) {
            counter.incrementRequestCountByIp("192.168.1.2");
            counter.incrementRequestCountByBrowserAgent("Firefox");
        }

        // Print request counts
        System.out.println("Requests from IP Address 192.168.1.1: " + counter.getRequestCountByIp("192.168.1.1"));
        System.out.println("Requests from IP Address 192.168.1.2: " + counter.getRequestCountByIp("192.168.1.2"));
        System.out.println("Requests from Browser Agent Chrome: " + counter.getRequestCountByBrowserAgent("Chrome"));
        System.out.println("Requests from Browser Agent Firefox: " + counter.getRequestCountByBrowserAgent("Firefox"));

    /*
    Requests from IP Address 192.168.1.1: 10
    Requests from IP Address 192.168.1.2: 5
    Requests from Browser Agent Chrome: 10
    Requests from Browser Agent Firefox: 5
     */

    }
}
