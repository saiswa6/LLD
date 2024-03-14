package src.designpatterns.prototype.first;

import java.util.HashMap;
import java.util.Map;

public class BirdRegistry {
    Map<String, Bird> birdRegistry = new HashMap<>();

    public void create(String type, Bird bird) {
        birdRegistry.put(type, bird);
    }

    public Bird getBird(String type) {
        return birdRegistry.get(type).clone();
    }
}
