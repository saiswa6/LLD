package src.designpatterns.structural.decorator.icecreamcone;

public class OrangeCone implements IceCreamCone{
    IceCreamCone iceCreamCone;
    OrangeCone(){ // This is used for Base Cone

    }
    OrangeCone(IceCreamCone iceCreamCone){ // This is used for empty cone on empty cone.
        this.iceCreamCone = iceCreamCone;
    }
    @Override
    public int getCost() {
        return  iceCreamCone != null ? iceCreamCone.getCost() + 6 : 6;
    }

    @Override
    public String getConstituents() {
        return iceCreamCone != null ? iceCreamCone.getConstituents() + " Orange Cone " : " Orange Cone ";
    }
}
