package src.designpatterns.prototype.first;

public class Penguin extends Bird{
    private String walk;

    public void setWalk(String walk) {
        this.walk = walk;
    }

    Penguin(){}

    Penguin(Penguin base){
        super(base);
        this.walk = base.walk;
    }

    @Override
    public Penguin clone() {
        return new Penguin(this);
    }
}
