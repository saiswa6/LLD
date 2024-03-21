package src.CaseStudies.PenDesign.refill;

public interface RefillPen {
    Refill getRefill();

    boolean canChangeRefill();

    void changeRefill(Refill newRefill);
}
