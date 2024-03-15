package src.designpatterns.factory.scaler.practicalfactory;

public class SplitwiseSettleUpStrategyFactory {
    public static SplitwiseStrategy getStrategyForType(SplitwiseSettleUpStrategy splitwiseSettleUpStrategy){
        if(splitwiseSettleUpStrategy == SplitwiseSettleUpStrategy.GIVE_TO_NEXT){
            return new GiveToNextSplitwiseSettleUpStrategy();
        } else if (splitwiseSettleUpStrategy == SplitwiseSettleUpStrategy.PRIORITY_QUEUE){
            return new PriorityQueueSplitwiseSettleUpStrategy();
        }
        return null;
    }
}
