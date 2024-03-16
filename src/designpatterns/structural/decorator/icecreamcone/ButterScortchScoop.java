package src.designpatterns.structural.decorator.icecreamcone;

public class ButterScortchScoop implements IceCreamCone{
    IceCreamCone iceCreamCone;

    ButterScortchScoop(IceCreamCone iceCreamCone){
        this.iceCreamCone= iceCreamCone;
    }
    @Override
    public int getCost() {
        return iceCreamCone.getCost() + 15;
    }

    @Override
    public String getConstituents() {
        return iceCreamCone.getConstituents() + " ButterScortch Scoop";
    }
}
