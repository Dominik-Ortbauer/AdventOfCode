package at.adventofcode;

import java.util.*;
import java.util.stream.Collectors;

public class Day14 {
    public static void partOne() {
        List<String> lines = Main.readLinesFromFile(14);
        
        Character[] polymer = lines.get(0).chars().mapToObj(c -> (char)c).toArray(Character[]::new);
        
        List<Character> newPolymer = new LinkedList<>();

        Map<String, Character> insertions = new HashMap<>();

        for (int i = 2; i < lines.size(); i++) {
            String[] split = lines.get(i).split(" -> ");
            insertions.put(split[0], split[1].charAt(0));
        }

        for (int i = 0; i < 10; i++) {
            newPolymer.add(polymer[0]);
            for (int j = 0; j < polymer.length-1; j++) {
                Character insert = insertions.get((polymer[j]) + "" + polymer[j + 1]);

                if(insert != null) newPolymer.add(insert);
                newPolymer.add(polymer[j+1]);
            }

            polymer = newPolymer.toArray(Character[]::new);
            newPolymer = new LinkedList<>();
        }

        Map<Character, Integer> cnts = new HashMap<>();

        for(Character c : polymer) {
            cnts.merge(c, 1, Integer::sum);
        }

        Integer highest = 0, lowest = 0;
        for(Integer i : cnts.values()) {
            if(i > highest) {
                highest = i;
            }

            if(i < lowest || lowest == 0) {
                lowest = i;
            }
        }

        System.out.println(cnts.values());
        System.out.println(highest + " - " + lowest + " = " + (highest-lowest));
    }

    public static void partTwo() {
        List<String> lines = Main.readLinesFromFile(14);

        Map<String, Character> insertions = new HashMap<>();
        Map<String, Long> groupedPairs = new HashMap<>();
        Map<String, Long> newGroupedPairs = new HashMap<>();

        for (int i = 2; i < lines.size(); i++) {
            String[] split = lines.get(i).split(" -> ");
            insertions.put(split[0], split[1].charAt(0));

            groupedPairs.put(split[0], 0L);
        }

        for (int i = 0; i < lines.get(0).length()-1; i++) {
            String pair = lines.get(0).substring(i, i+2);

            groupedPairs.merge(pair, 1L, Long::sum);
        }

        for (int i = 0; i < 40; i++) {
            newGroupedPairs = new HashMap<>(groupedPairs);
            for(String pair : groupedPairs.keySet()) {
                long amount = groupedPairs.get(pair);
                newGroupedPairs.merge(pair, -amount, Long::sum);

                String key = pair.charAt(0) + "" + insertions.get(pair);
                newGroupedPairs.merge(key, amount, Long::sum);

                key = insertions.get(pair) + "" + pair.charAt(1);
                newGroupedPairs.merge(key, amount, Long::sum);
            }

            groupedPairs = new HashMap<>(newGroupedPairs);
        }

        Map<Character, Long> cnts = new HashMap<>();

        for(String s : groupedPairs.keySet()) {
            Character one = s.charAt(0);
            Character two = s.charAt(1);

            cnts.merge(one, groupedPairs.get(s), Long::sum);
            cnts.merge(two, groupedPairs.get(s), Long::sum);
        }

        Long highest = 0L, lowest = 0L;

        for(Long l : cnts.values()) {
            if(l > highest) highest = l;
            if(l < lowest || lowest == 0) lowest = l;
        }

        highest = (long) ((highest.doubleValue() / 2d) + 0.5);
        lowest = (long) ((lowest.doubleValue() / 2d) + 0.5);

        System.out.println(groupedPairs.keySet());
        System.out.println(groupedPairs.values());
        System.out.println(highest + " - " + lowest + " = " + (highest-lowest));
    }
}
