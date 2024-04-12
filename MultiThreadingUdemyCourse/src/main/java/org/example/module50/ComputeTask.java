package org.example.module50;

import java.util.concurrent.RecursiveTask;

public class ComputeTask extends RecursiveTask<Integer> {
    int start;
    int end;

    ComputeTask(int start, int end){
        this.start = start;
        this.end = end;
    }
    @Override
    protected Integer compute() {

        if(end - start <= 4){
            int totalSum = 0;
            for(int i = start; i <= end; i++){
                totalSum += i;
            }
            return totalSum;
        } else {
            //split the task
            int mid = (start + end)/2;
            ComputeTask leftTask = new ComputeTask(start, mid);
            ComputeTask rightTask = new ComputeTask(mid + 1, end);

            // Fork the subtasks for parallel execution
            leftTask.fork();
            rightTask.fork();

            // Combine the results of subtask

            int leftResult = leftTask.join();;
            int rightResult = rightTask.join();

            // Combine the results;
            return leftResult + rightResult;
        }

    }
}
