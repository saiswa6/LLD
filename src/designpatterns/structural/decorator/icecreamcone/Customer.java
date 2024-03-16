package src.designpatterns.structural.decorator.icecreamcone;

public class Customer {
    public static void main(String[] args) {

        /*
        Similar Assignment Question
        // Implement Pizza Creation System
        // ThinCrust
        // Normal
        // Pan
        // 5 toppings
        // you can have more than 1 base but can't sandwich
         */
        IceCreamCone iceCreamCone = new ChoclateScoop(new ButterScortchScoop(new BlueCone(new OrangeCone())));
        System.out.println("Icecream cost : - "+ iceCreamCone.getCost()); // Icecream cost : - 42
        System.out.println("Icecream constitutents : - "+ iceCreamCone.getConstituents()); // Icecream constitutents : -  Orange Cone  Blue Cone  ButterScortch Scoop Chocolate Scoop

        IceCreamCone chocobar = new ChoclateScoop(new ButterScortchScoop(new BlueCone()));
        IceCreamCone kesarPista = new ButterScortchScoop(new ButterScortchScoop(new OrangeCone()));

        System.out.println("chocobar Icecream cost : - "+ chocobar.getCost());
        System.out.println("chocobar constitutents : - "+ chocobar.getConstituents());

        System.out.println("kesarpista Icecream cost : - "+ kesarPista.getCost());
        System.out.println("kesarPista constitutents : - "+ kesarPista.getConstituents());

        IceCreamCone chocowithkesarPista = new ChoclateScoop(kesarPista);
    }


}
