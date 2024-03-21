package src.CaseStudies.PenDesign.ink;

import src.CaseStudies.PenDesign.Colour;

import java.util.List;

public class Ink {
    private Colour colour;
    private List<InkFeature> inkFeature;

    public Colour getColour() {
        return colour;
    }

    public void setColour(Colour colour) {
        this.colour = colour;
    }

    public List<InkFeature> getInkFeature() {
        return inkFeature;
    }

    public void setInkFeature(List<InkFeature> inkFeature) {
        this.inkFeature = inkFeature;
    }
}
