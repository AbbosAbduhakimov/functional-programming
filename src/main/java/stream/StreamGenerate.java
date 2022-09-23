package stream;

import java.util.stream.Stream;

public class StreamGenerate {

    public static void main(String[] args) {

        Stream<String> stringStream = Stream.empty();
        stringStream.forEach(System.out::println);

        Stream.Builder<String> builder = Stream.builder();

        Stream<String> stringBuilder = builder.add("first").add("second").add("third").build();



        Stream<String> iterate = Stream.iterate("start",(s -> s.concat(s))).limit(5);
        iterate.forEach(System.out::println);

    }
}
