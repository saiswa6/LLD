package src.designpatterns.prototype.first;

import java.util.ArrayList;
import java.util.List;

public class Client {
    public static void main(String[] args) {
        Bird bird1 = new Bird();
        bird1.setColour("Green");
        bird1.setWeight(33);
        bird1.setBeakType("Sharp");

        Penguin penguin1 = new Penguin();
        penguin1.setBeakType("Hard");
        penguin1.setWalk("Super");

        Sparrow sparrow1 = new Sparrow();
        sparrow1.setSound("Kuhu Kuhu");
        sparrow1.setWeight(66);

        List<Bird> birdList = List.of(bird1, penguin1, sparrow1);
        List<Bird> copies = new ArrayList<>();

        for(Bird eachBird : birdList) {
            copies.add(eachBird.clone());
        }

        System.out.println("Done");

        Penguin sweetPenguin = new Penguin();
        sweetPenguin.setWalk("Lovely");

        Sparrow beautifulSparrow = new Sparrow();
        beautifulSparrow.setSound("Chuk chuk");

        BirdRegistry birdRegistry = new BirdRegistry();
        birdRegistry.create("sweetPenguin", sweetPenguin);
        birdRegistry.create("beautifulSparrow", beautifulSparrow);

        List<String> getBirdsOfType = List.of("sweetPenguin", "beautifulSparrow");
        List<Bird> requestedBirds = new ArrayList<>();

        for(String type : getBirdsOfType){
            requestedBirds.add(birdRegistry.getBird(type));
        }

        System.out.println("Completed");

    }
}
