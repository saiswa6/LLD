package src.designpatterns.prototype.first;

public class Bird implements Cloneable<Bird>{
    private String colour;
    private String beakType;
    private int weight;

    Bird(){}

    public Bird(Bird base) {
        this.colour = base.colour;
        this.beakType = base.beakType;
        this.weight = base.weight;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public void setBeakType(String beakType) {
        this.beakType = beakType;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public Bird clone() {
        Bird copy =  new Bird();
        copy.weight = this.weight;
        copy.colour = this.colour;
        copy.beakType = this.beakType;
        return copy;
    }
}
