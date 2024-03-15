package src.designpatterns.factory.scaler.practicalfactory;

public class GiveToNextSplitwiseSettleUpStrategy implements SplitwiseStrategy{
    @Override
    public void settleTransaction() {
        System.out.println("Give To Next Settled up");
    }
}
