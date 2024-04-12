package org.example.Streams.Example3;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamExample3 {
    public static void main(String[] args) {
         Stream<String> nameStream = Stream.of("HELLO","EVERYBODY","HOW","ARE","YOU", "DOING");
         Stream<String> filteredStream = nameStream.filter(
                 (String name) -> name.length() <= 3
         );

         List<String> filteredNameList = filteredStream.collect(Collectors.toList());
        System.out.println(filteredNameList);
    }
}
