package org.concurrency.Questions.ImplementBrowser.Imp3;


import java.util.concurrent.ConcurrentHashMap;


/*
To implement a thread-safe data structure that keeps track of the number of incoming requests
grouped by IP Address (and potentially other attributes like BrowserAgent) over a time window, we can use a concurrent hashmap with the IP Address (or BrowserAgent) as the key and a thread-safe counter as the value.

 Explanation:
- ipRequestCounts and browserAgentRequestCounts are ConcurrentHashMap instances that store the request counts for IP Addresses and Browser Agents respectively.
- incrementRequestCountByIp() and incrementRequestCountByBrowserAgent() methods increment the request count for the given IP Address or Browser Agent.
- getRequestCountByIp() and getRequestCountByBrowserAgent() methods retrieve the request count for the given IP Address or Browser Agent.

 */

public class RequestCounterConcurrentMap {

    private final ConcurrentHashMap<String, Integer> ipRequestCounts = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, Integer> browserAgentRequestCounts = new ConcurrentHashMap<>();

    /**
     * Increment the request count for the given IP Address.
     *
     * @param ipAddress the IP Address to increment the request count for
     */
    public void incrementRequestCountByIp(String ipAddress) {
        ipRequestCounts.compute(ipAddress, (k, v) -> (v == null) ? 1 : v + 1);
    }

    /**
     * Increment the request count for the given Browser Agent.
     *
     * @param browserAgent the Browser Agent to increment the request count for
     */
    public void incrementRequestCountByBrowserAgent(String browserAgent) {
        browserAgentRequestCounts.compute(browserAgent, (k, v) -> (v == null) ? 1 : v + 1);
    }

    /**
     * Get the request count for the given IP Address.
     *
     * @param ipAddress the IP Address to get the request count for
     * @return the request count for the given IP Address
     */
    public int getRequestCountByIp(String ipAddress) {
        return ipRequestCounts.getOrDefault(ipAddress, 0);
    }

    /**
     * Get the request count for the given Browser Agent.
     *
     * @param browserAgent the Browser Agent to get the request count for
     * @return the request count for the given Browser Agent
     */
    public int getRequestCountByBrowserAgent(String browserAgent) {
        return browserAgentRequestCounts.getOrDefault(browserAgent, 0);
    }

    public static void main(String[] args) {
        RequestCounterConcurrentMap counter = new RequestCounterConcurrentMap();

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


