package at.adventofcode;

import java.util.*;

public class Day12 {
    public static void partOne() {
        List<String> lines = Main.readLinesFromFile(12);

        Map<String, List<String>> connections = new HashMap<>();

        for(String s : lines) {
            String[] split = s.split("-");
            if(connections.containsKey(split[0])) {
                connections.get(split[0]).add(split[1]);
            } else {
                List<String> list = new LinkedList<>();
                list.add(split[1]);
                connections.put(split[0], list);
            }

            if(connections.containsKey(split[1])) {
                connections.get(split[1]).add(split[0]);
            } else {
                List<String> list = new LinkedList<>();
                list.add(split[0]);
                connections.put(split[1], list);
            }
        }

        List<String> alreadyVisited = new LinkedList<>();

        int paths = countPaths("start", connections, alreadyVisited);

        System.out.println(paths);
    }

    private static int countPaths(String cur, Map<String, List<String>> connections, List<String> alreadyVisited) {
        if(cur.equals("end")) return 1;
        if(alreadyVisited.contains(cur)) return 0;

        if(cur.charAt(0) >= 'a' && cur.charAt(0) <= 'z') {
            alreadyVisited.add(cur);
        }

        int sum = 0;
        for(String s : connections.get(cur)) {
            sum += countPaths(s, connections, new LinkedList<>(alreadyVisited));
        }

        return sum;
    }

    public static void partTwo() {
        List<String> lines = Main.readLinesFromFile(12);

        Map<String, List<String>> connections = new HashMap<>();

        for(String s : lines) {
            String[] split = s.split("-");
            if(connections.containsKey(split[0])) {
                connections.get(split[0]).add(split[1]);
            } else {
                List<String> list = new LinkedList<>();
                list.add(split[1]);
                connections.put(split[0], list);
            }

            if(connections.containsKey(split[1])) {
                connections.get(split[1]).add(split[0]);
            } else {
                List<String> list = new LinkedList<>();
                list.add(split[0]);
                connections.put(split[1], list);
            }
        }

        List<String> alreadyVisited = new LinkedList<>();

        int paths = countPathsTwo("start", connections, alreadyVisited, false);

        System.out.println(paths);
    }

    private static int countPathsTwo(String cur, Map<String, List<String>> connections, List<String> alreadyVisited, boolean doubleUsed) {
        if(cur.equals("end")) return 1;
        if(alreadyVisited.contains(cur)) {
            if(doubleUsed || cur.equals("start")) return 0;
            doubleUsed = true;
        } else if(cur.charAt(0) >= 'a' && cur.charAt(0) <= 'z') {
            alreadyVisited.add(cur);
        }

        int sum = 0;
        for(String s : connections.get(cur)) {
            sum += countPathsTwo(s, connections, new LinkedList<>(alreadyVisited), doubleUsed);
        }

        return sum;
    }
}
