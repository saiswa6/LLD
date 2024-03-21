package src.CaseStudies.PenDesign.pens;

import src.CaseStudies.PenDesign.Colour;
import src.CaseStudies.PenDesign.refill.Refill;
import src.CaseStudies.PenDesign.refill.RefillPen;
import src.CaseStudies.PenDesign.strategies.writeStaregies.SmoothWriteBehaviour;
import src.CaseStudies.PenDesign.strategies.writeStaregies.WriteBehaviour;

public class GelPen extends Pen implements RefillPen {
    private Refill refill;
    private boolean canChangeRefill = false;

    private GelPen(WriteBehaviour writeBehaviour){
        super(PenType.GEL, writeBehaviour);
    }

    public static class Builder{
        private Refill refill;
        private boolean canChangeRefill = false;

        public Builder setRefill(Refill refill){
            this.refill = refill;
            return this;
        }

        public Builder setCanChangeRefill(boolean value){
            this.canChangeRefill = value;
            return this;
        }

        public GelPen build(){
            GelPen gelPen = new GelPen(new SmoothWriteBehaviour());
            gelPen.refill = this.refill;
            gelPen.canChangeRefill = this.canChangeRefill;
            return gelPen;
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
