package src.CaseStudies.PenDesign.refill;

import src.CaseStudies.PenDesign.Colour;
import src.CaseStudies.PenDesign.ink.Ink;
import src.CaseStudies.PenDesign.nib.Nib;

public abstract class Refill {
    private Ink ink;
    private Nib nib;
    RefillType refillType;

    abstract Colour getColour();

    abstract void getLeftOverInk();

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

    public RefillType getRefillType() {
        return refillType;
    }

    public void setRefillType(RefillType refillType) {
        this.refillType = refillType;
    }
}
