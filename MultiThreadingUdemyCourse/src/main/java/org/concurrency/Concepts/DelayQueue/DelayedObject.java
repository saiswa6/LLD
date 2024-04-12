package org.concurrency.Concepts.DelayQueue;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayedObject implements Delayed {
    private String data;
    private long startTime;

    public DelayedObject(String data, long deleayInMilliseconds){
        this.data = data;
        this.startTime = System.currentTimeMillis()+ deleayInMilliseconds;
    }
    @Override
    public long getDelay(TimeUnit unit) {
        long diff = startTime - System.currentTimeMillis();
        return unit.convert(diff, TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {

        if(startTime < ((DelayedObject)o).startTime){
            return -1;
        } else if(startTime > ((DelayedObject)o).startTime){
            return +1;
        }
        return 0;
    }


}
