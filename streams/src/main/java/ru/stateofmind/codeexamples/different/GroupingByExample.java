package ru.stateofmind.codeexamples.different;

import java.util.Arrays;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.summarizingLong;

public class GroupingByExample {

    private static class Planet {
        private String name;
        private long age;
        private PlanetType type;

        Planet(String name, long age, PlanetType type) {
            this.name = name;
            this.age = age;
            this.type = type;
        }

        PlanetType getType() {
            return this.type;
        }
        long getAge() {
            return this.age;
        }
    }

    private enum PlanetType {
        STONE,
        GAS,
        ICE,
        UNKNOWN
    }

    private static class PlanetClass {
        private PlanetType planetType;
        private long age;
    }

    private List<Planet> planets = Arrays.asList(
            new Planet("ArcaNine", 6000000000L, PlanetType.STONE),
            new Planet("Carith", 7500000000L, PlanetType.ICE),
            new Planet("Roya", 4000000000L, PlanetType.STONE),
            new Planet("Trionity", 9000000000L, PlanetType.GAS),
            new Planet("Fira", 9000000000L, PlanetType.UNKNOWN),
            new Planet("GingroEight", 3000000000L, PlanetType.ICE),
            new Planet("Roulgan", 2000000000L, PlanetType.STONE),
            new Planet("Giras", 11000000000L, PlanetType.GAS));

    public static void main(String[] args) {
        GroupingByExample groupingByExample = new GroupingByExample();
        groupingByExample.groupBySimpleType();
        groupingByExample.groupByTypeWithStatistics();
    }

    private void groupBySimpleType() {
        Map<PlanetType, List<Planet>> planetsByType = planets.stream()
                .collect(Collectors.groupingBy(Planet::getType));
        planetsByType.forEach(
                (key, value) -> System.out.println(key.toString() + " " +
                        value.stream()
                                .map(planet -> planet.name)
                                .collect(Collectors.joining(" | ", "[", "]")))
        );
    }

    private void groupByTypeWithStatistics() {
        Map<PlanetType, LongSummaryStatistics> planetsByType = planets.stream()
                .collect(Collectors.groupingBy(Planet::getType,
                        summarizingLong(Planet::getAge)));
    }
}
