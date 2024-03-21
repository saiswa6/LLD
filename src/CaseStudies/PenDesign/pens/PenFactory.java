package src.CaseStudies.PenDesign.pens;

public class PenFactory {
    public static GelPen.Builder createGelPen(){
        return new GelPen.Builder();
    }

    public static BallPen.Builder createBallPen(){
        return new BallPen.Builder();
    }

    public static FountainPen.Builder createFountainPen(){
        return new FountainPen.Builder();
    }
}
