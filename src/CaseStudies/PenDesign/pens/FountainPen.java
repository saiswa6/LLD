package src.CaseStudies.PenDesign.pens;

import src.CaseStudies.PenDesign.Colour;
import src.CaseStudies.PenDesign.ink.Ink;
import src.CaseStudies.PenDesign.nib.Nib;
import src.CaseStudies.PenDesign.strategies.writeStaregies.SmoothWriteBehaviour;
import src.CaseStudies.PenDesign.strategies.writeStaregies.WriteBehaviour;

public class FountainPen extends Pen{

    private Ink ink;
    private Nib nib;
    public FountainPen(WriteBehaviour writeBehaviour){
        super(PenType.GEL, writeBehaviour);
    }

    public static class Builder{
        private Ink ink;
        private Nib nib;

        public Builder setInk(Ink ink){
            this.ink = ink;
            return this;
        }

        public Builder setNib(Nib nib){
            this.nib = nib;
            return this;
        }

        public FountainPen build(){
            FountainPen fountainPen = new FountainPen(new SmoothWriteBehaviour());
            fountainPen.ink = this.ink;
            fountainPen.nib = this.nib;
            return fountainPen;
        }
    }
    @Override
    public void write() {

    }

    @Override
    public Colour getColour() {
        return null;
    }

    public Ink getInk() {
        return ink;
    }

    public void setInk(Ink ink) {
        this.ink = ink;
    }

    public Nib getNib() {
        return nib;
    }

    public void setNib(Nib nib) {
        this.nib = nib;
    }
}
