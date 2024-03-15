package src.designpatterns.factory.scaler.practicalfactory;

public class SplitwiseSettleUp {
    public void settleUp(SplitwiseSettleUpStrategy splitwiseSettleUpStrategy){
        SplitwiseStrategy splitwiseStrategy = SplitwiseSettleUpStrategyFactory.getStrategyForType(splitwiseSettleUpStrategy);
        splitwiseStrategy.settleTransaction();


        /*   // Violates SRP
        if(splitwiseSettleUpStrategy == SplitwiseSettleUpStrategy.GIVE_TO_NEXT){
            SplitwiseStrategy splitwiseStrategy= new GiveToNextSplitwiseSettleUpStrategy();
            splitwiseStrategy.settleTransaction();
        } else if(splitwiseSettleUpStrategy == SplitwiseSettleUpStrategy.PRIORITY_QUEUE){
            SplitwiseStrategy splitwiseStrategy = new PriorityQueueSplitwiseSettleUpStrategy();
            splitwiseStrategy.settleTransaction();
        }*/
    }
}
