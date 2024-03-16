package src.designpatterns.structural.decorator.icecreamcone;

import java.security.InvalidParameterException;

public class BlueCone implements IceCreamCone{
    IceCreamCone iceCreamCone;
    BlueCone(){ // This is used for Base Cone

    }

    BlueCone(IceCreamCone iceCreamCone){ // This is used for empty cone on empty cone.
        // To restrict for one cone
        if(iceCreamCone.getConstituents().contains("Cone")){
            throw new InvalidParameterException("More than one cone base are used");
        }
        this.iceCreamCone = iceCreamCone;
    }
    @Override
    public int getCost() {
        // To restrict cost,add if condition here and throw exception
        if(iceCreamCone.getCost() > 100){
            throw new InvalidParameterException("Cost of Icecream cone grater than 100");
        }
        return  iceCreamCone != null ? iceCreamCone.getCost() + 9 : 9;
    }

    @Override
    public String getConstituents() {
        return iceCreamCone != null ? iceCreamCone.getConstituents() + " Blue Cone " : " Blue Cone ";
    }
}
