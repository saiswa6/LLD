package src.designpatterns.structural.decorator.icecreamcone;

public class ChoclateScoop implements IceCreamCone{
    IceCreamCone iceCreamCone;

    ChoclateScoop(IceCreamCone iceCreamCone){
        this.iceCreamCone = iceCreamCone;
    }
    @Override
    public int getCost() {
        return iceCreamCone.getCost() + 12;
    }

    @Override
    public String getConstituents() {
        return iceCreamCone.getConstituents() + " Chocolate Scoop";
    }
}
