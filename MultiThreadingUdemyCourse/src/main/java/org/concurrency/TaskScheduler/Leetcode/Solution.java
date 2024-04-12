package org.concurrency.TaskScheduler.Leetcode;

import java.util.*;

// Leetcode : https://leetcode.com/problems/task-scheduler/editorial/
  //   https://leetcode.com/problems/task-scheduler-ii/description/
// SOlution Neetcode : https://www.youtube.com/watch?v=s8p8ukTyA2I
class Solution {
    public int leastInterval(char[] tasks, int n) {
        Map<Character, Integer> map = new HashMap<>();
        for(Character c : tasks){
            map.put(c, map.getOrDefault(c, 0) + 1);
        } // map to note frequencies of each character

        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>((a, b) ->
                b - a
        );  // create  a max heap

        maxHeap.addAll(map.values());

        Queue<Pair<Integer, Integer>> q = new ArrayDeque<Pair<Integer, Integer>>(); // to note the at which the task comes out.
        int time = 0;

        while (!maxHeap.isEmpty() || !q.isEmpty()) { // check both simultaneously
            time++;

            if(!maxHeap.isEmpty()){
                Integer temp = maxHeap.poll();
                if(temp - 1 > 0) {

                    q.add(new Pair(temp -1 , time + n));
                }

            }
            if(!q.isEmpty() && q.peek().getValue() == time){
                maxHeap.add(q.peek().getKey());
                q.poll();
            }
        }

        return time;
    }

    private class Pair<T, T1> {
        private Integer key;
        private Integer value;

        public Pair(Integer key, Integer value) {
            this.key = key;
            this.value = value;
        }

        public Integer getKey() {
            return key;
        }

        public void setKey(Integer key) {
            this.key = key;
        }

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }
    }
}