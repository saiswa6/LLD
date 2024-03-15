package src.designpatterns.factory.scaler.practicalfactory;

public class PriorityQueueSplitwiseSettleUpStrategy implements SplitwiseStrategy{
    /**
     *
     */
    @Override
    public void settleTransaction() {
        System.out.println("Priority Queue Settled Up");
    }
}
