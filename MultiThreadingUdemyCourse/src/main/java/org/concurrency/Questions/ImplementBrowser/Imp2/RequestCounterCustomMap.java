package org.concurrency.Questions.ImplementBrowser.Imp2;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/*
    Below is an implementation of a thread-safe RequestCounter class using a custom ConcurrentHashMap-like data structure to keep track of
    the number of incoming requests grouped by IP Address and Browser Agent. The custom ConcurrentHashMap implementation uses separate chaining with locks for each bucket to ensure thread safety.


    Explanation:
    - ipEntries and browserAgentEntries are arrays of Entry objects to store the request counts for IP Addresses and Browser Agents respectively.
    - ipLocks and browserAgentLocks are arrays of ReentrantReadWriteLock objects to provide read-write locking for each bucket.
    - incrementRequestCountByIp() and incrementRequestCountByBrowserAgent() methods increment the request count for the given IP Address or Browser Agent.
    - getRequestCountByIp() and getRequestCountByBrowserAgent() methods retrieve the request count for the given IP Address or Browser Agent.
    - addEntry() method adds a new Entry to the corresponding array.
    - hash() method generates a hash value for the given key.
    - Entry is a simple linked list node containing a key, count, and reference to the next node.
    - This RequestCounter class provides a thread-safe implementation without using Java's built-in collections by maintaining concurrency using ReentrantReadWriteLock for each bucket in the custom ConcurrentHashMap-like data structure.
 */

public class RequestCounterCustomMap {

    private static final int DEFAULT_CAPACITY = 16;
    private final Entry[] ipEntries;
    private final Entry[] browserAgentEntries;
    private final ReentrantReadWriteLock[] ipLocks;
    private final ReentrantReadWriteLock[] browserAgentLocks;

    public RequestCounterCustomMap() {
        this.ipEntries = new Entry[DEFAULT_CAPACITY];
        this.browserAgentEntries = new Entry[DEFAULT_CAPACITY];
        this.ipLocks = new ReentrantReadWriteLock[DEFAULT_CAPACITY];
        this.browserAgentLocks = new ReentrantReadWriteLock[DEFAULT_CAPACITY];
        for (int i = 0; i < DEFAULT_CAPACITY; i++) {
            this.ipLocks[i] = new ReentrantReadWriteLock();
            this.browserAgentLocks[i] = new ReentrantReadWriteLock();
        }
    }

    public void incrementRequestCountByIp(String ipAddress) {
        int hash = hash(ipAddress.hashCode());
        int index = hash % ipEntries.length;
        ipLocks[index].writeLock().lock();
        try {
            Entry current = ipEntries[index];
            while (current != null && !current.key.equals(ipAddress)) {
                current = current.next;
            }
            if (current != null) {
                current.count++;
            } else {
                addEntry(ipAddress, index, ipEntries);
            }
        } finally {
            ipLocks[index].writeLock().unlock();
        }
    }

    public void incrementRequestCountByBrowserAgent(String browserAgent) {
        int hash = hash(browserAgent.hashCode());
        int index = hash % browserAgentEntries.length;
        browserAgentLocks[index].writeLock().lock();
        try {
            Entry current = browserAgentEntries[index];
            while (current != null && !current.key.equals(browserAgent)) {
                current = current.next;
            }
            if (current != null) {
                current.count++;
            } else {
                addEntry(browserAgent, index, browserAgentEntries);
            }
        } finally {
            browserAgentLocks[index].writeLock().unlock();
        }
    }

    public int getRequestCountByIp(String ipAddress) {
        int hash = hash(ipAddress.hashCode());
        int index = hash % ipEntries.length;
        ipLocks[index].readLock().lock();
        try {
            Entry current = ipEntries[index];
            while (current != null && !current.key.equals(ipAddress)) {
                current = current.next;
            }
            return current != null ? current.count : 0;
        } finally {
            ipLocks[index].readLock().unlock();
        }
    }

    public int getRequestCountByBrowserAgent(String browserAgent) {
        int hash = hash(browserAgent.hashCode());
        int index = hash % browserAgentEntries.length;
        browserAgentLocks[index].readLock().lock();
        try {
            Entry current = browserAgentEntries[index];
            while (current != null && !current.key.equals(browserAgent)) {
                current = current.next;
            }
            return current != null ? current.count : 0;
        } finally {
            browserAgentLocks[index].readLock().unlock();
        }
    }

    private void addEntry(String key, int index, Entry[] entries) {
        Entry newEntry = new Entry(key);
        newEntry.next = entries[index];
        entries[index] = newEntry;
    }

    private int hash(int hashCode) {
        return hashCode & 0x7fffffff; // Ensure positive hash value
    }

    private static class Entry {
        String key;
        int count;
        Entry next;

        Entry(String key) {
            this.key = key;
            this.count = 1;
        }
    }

    public static void main(String[] args) {
        RequestCounterCustomMap counter = new RequestCounterCustomMap();

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
    }
}



