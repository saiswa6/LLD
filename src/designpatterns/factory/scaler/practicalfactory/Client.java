package src.designpatterns.factory.scaler.practicalfactory;

public class Client {
    public static void main(String[] args) {
        SplitwiseStrategy splitwiseStrategy = new GiveToNextSplitwiseSettleUpStrategy();
        splitwiseStrategy.settleTransaction(); // Give To Next Settled up

        SplitwiseStrategy splitwiseStrategy2 = new PriorityQueueSplitwiseSettleUpStrategy();
        splitwiseStrategy2.settleTransaction(); // Priority Queue Settled Up


        /*
        SplitwiseStrategy splitwiseStrategy = new GiveToNextSplitwiseSettleUpStrategy();
        splitwiseStrategy.settleTransaction(); // Give To Next Settled up

        SplitwiseStrategy splitwiseStrategy2 = new PriorityQueueSplitwiseSettleUpStrategy();
        splitwiseStrategy2.settleTransaction(); // Priority Queue Settled Up
        */

    }
}
