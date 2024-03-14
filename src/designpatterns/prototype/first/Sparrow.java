package src.designpatterns.prototype.first;

public class Sparrow extends Bird{
    private String sound;

    Sparrow(){}

    Sparrow(Sparrow base){
        super(base);
        this.sound = base.sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    @Override
    public Sparrow clone() {
        return new Sparrow(this);
    }
}
