package src.CaseStudies.PenDesign.pens;

import src.CaseStudies.PenDesign.Colour;
import src.CaseStudies.PenDesign.refill.Refill;
import src.CaseStudies.PenDesign.refill.RefillPen;
import src.CaseStudies.PenDesign.strategies.writeStaregies.SmoothWriteBehaviour;
import src.CaseStudies.PenDesign.strategies.writeStaregies.WriteBehaviour;

public class Marker extends Pen implements RefillPen {
    private Refill refill;
    private boolean canChangeRefill = false;

    public Marker(WriteBehaviour writeBehaviour){
        super(PenType.MARKER, writeBehaviour);
    }

    public static class Builder{
        private Refill refill;
        private boolean canChangeRefill = false;

        public Marker.Builder setRefill(Refill refill){
            this.refill = refill;
            return this;
        }

        public Marker.Builder setCanChangeRefill(boolean value){
            this.canChangeRefill = value;
            return this;
        }

        public Marker build(){
            Marker markerPen = new Marker(new SmoothWriteBehaviour());
            markerPen.refill = this.refill;
            markerPen.canChangeRefill = this.canChangeRefill;
            return markerPen;
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
