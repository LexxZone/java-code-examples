package ru.stateofmind.codeexamples.different;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.System.*;

public class StreamsDifferent {

    public static void main(String[] args) {
        out.println("Method 'generate()':\n");
        generateMethod();
        out.println("\n");
        out.println("Method 'generateWithChangingElements()':\n");
        generateMethodWithChangingElements();
        out.println("\n");
        out.println("Method 'skip()':\n");
        skipMethod();
        out.println("\n");
    }

    /**
     * .generate() - generates elements,
     * .limit() - set limit for generated elements. If there is no limit - will generate elements until memory end.
     */
    private static void generateMethod() {
        Stream<String> list = Stream.generate(() -> "element")
                .limit(15);
        // Let's print in console result
        list.forEach(out::println);
    }

    /**
     * .generate() - generates elements,
     * .limit() - set limit for generated elements. If there is no limit - will generate elements until memory end.
     * Each new element gets number and goes to collection.
     */
    private static void generateMethodWithChangingElements() {
        AtomicInteger i = new AtomicInteger(0);
        List<String> list = Stream.generate(() -> "Element")
                .limit(15)
                .map(e -> e.concat(" " + i.incrementAndGet()))
                .collect(Collectors.toList());
        // Let's print in console result
        list.forEach(out::println);
    }

    /**
     * .skip() - skips elements in stream.
     */
    private static void skipMethod() {
        Stream<String> simpleStream = Stream.of("123", "456", "789")
                .skip(1);
        simpleStream.forEach(e -> out.println(e.concat(" ")));
    }
}
