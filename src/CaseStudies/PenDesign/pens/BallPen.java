package src.CaseStudies.PenDesign.pens;

import src.CaseStudies.PenDesign.Colour;
import src.CaseStudies.PenDesign.refill.Refill;
import src.CaseStudies.PenDesign.refill.RefillPen;
import src.CaseStudies.PenDesign.strategies.writeStaregies.FastWriteBehaviour;
import src.CaseStudies.PenDesign.strategies.writeStaregies.WriteBehaviour;

public class BallPen extends Pen implements RefillPen {

    private Refill refill;
    private boolean canChangeRefill = false;
    public BallPen(WriteBehaviour writeBehaviour) {
        super(PenType.BALL, writeBehaviour);
    }

    public static class Builder{
        private Refill refill;
        private boolean canChangeRefill = false;

        public BallPen.Builder setRefill(Refill refill){
            this.refill = refill;
            return this;
        }

        public BallPen.Builder setCanChangeRefill(boolean value){
            this.canChangeRefill = value;
            return this;
        }

        public BallPen build(){
            BallPen ballPen = new BallPen(new FastWriteBehaviour());
            ballPen.refill = this.refill;
            ballPen.canChangeRefill = this.canChangeRefill;
            return ballPen;
        }
    }

    @Override
    public void write() {

    }

    @Override
    public Colour getColour() {
        return null;
    }

    @Override
    public Refill getRefill() {
        return this.refill;
    }

    @Override
    public boolean canChangeRefill() {
        return this.canChangeRefill;
    }

    @Override
    public void changeRefill(Refill newRefill) {

    }
}
