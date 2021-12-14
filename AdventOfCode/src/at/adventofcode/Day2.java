package at.adventofcode;

import java.util.List;

public class Day2 {
    public static void partOne() {
        List<String> input = Main.readLinesFromFile(2);

        int horizontal = 0, depth = 0, aim = 0;

        for(String s : input) {
            String[] split = s.split(" ");
            int value = Integer.parseInt(split[1]);

            switch (split[0]) {
                case "forward":
                    horizontal += value;
                    break;

                case "up":
                    depth -= value;
                    break;

                case "down":
                    depth += value;
                    break;
            }
        }

        System.out.println(horizontal * depth);
    }

    public static void partTwo() {
        List<String> input = Main.readLinesFromFile(2);

        int horizontal = 0, depth = 0, aim = 0;

        for(String s : input) {
            String[] split = s.split(" ");
            int value = Integer.parseInt(split[1]);

            switch (split[0]) {
                case "forward":
                    horizontal += value;
                    depth += aim * value;
                    break;

                case "up":
                    aim -= value;
                    break;

                case "down":
                    aim += value;
                    break;
            }
        }

        System.out.println(horizontal * depth);
    }
}
