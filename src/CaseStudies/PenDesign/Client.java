package src.CaseStudies.PenDesign;

import src.CaseStudies.PenDesign.ink.Ink;
import src.CaseStudies.PenDesign.nib.Nib;
import src.CaseStudies.PenDesign.pens.*;
import src.CaseStudies.PenDesign.refill.BallRefill;
import src.CaseStudies.PenDesign.refill.GelRefill;

import java.util.ArrayList;
import java.util.List;

public class Client {
    public static void main(String[] args) {
        List<Pen> pens = new ArrayList<>();

        for(Pen pen : pens){
            if(pen.getType().equals(PenType.BALL)){ //Alternative for instanceOf usage

            }
        }

        // Use Registry Design Pattern to store pen objects prototype and get whenever required

        GelRefill gelRefill= new GelRefill();
        gelRefill.setInk(new Ink());
        gelRefill.setNib(new Nib());

        GelPen gelPen = PenFactory.createGelPen()
                .setCanChangeRefill(true)
                .setRefill(new GelRefill())
                .build();

        BallPen ballPen = PenFactory.createBallPen()
                .setRefill(new BallRefill())
                .setCanChangeRefill(true)
                .build();

        Nib fountainNib = new Nib();
        fountainNib.setRadius(5);
        fountainNib.setShape("Thin");

        FountainPen fountainPen = PenFactory.createFountainPen()
                .setInk(new Ink())
                .setNib(fountainNib)
                .build();


    }
}
