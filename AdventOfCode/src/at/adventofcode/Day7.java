package at.adventofcode;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day7 {
    public static void partOne() {
        List<String> input = Main.readLinesFromFile(7);

        List<Integer> crabs = Arrays.stream(input.get(0).split(",")).map(s -> Integer.parseInt(s)).collect(Collectors.toList());

        int lowest = calcFuelCostForCrabs(crabs, 0);
        for (int i = 1; i < 2000; i++) {
            int fuelCost = calcFuelCostForCrabs(crabs, i);

            if(fuelCost < lowest) {
                lowest = fuelCost;
            }
        }

        System.out.println(lowest);
    }

    private static int calcFuelCostForCrabs(List<Integer> crabs, int toGo) {
        int sum = 0;
        for(Integer in : crabs) {
            sum += Math.abs(toGo - in);
        }

        return sum;
    }

    public static void partTwo() {
        List<String> input = Main.readLinesFromFile(7);

        List<Integer> crabs = Arrays.stream(input.get(0).split(",")).map(s -> Integer.parseInt(s)).collect(Collectors.toList());

        long lowest = calcFuelCostForCrabsTwo(crabs, 0);
        for (int i = 1; i < 2000; i++) {
            long fuelCost = calcFuelCostForCrabsTwo(crabs, i);

            if(fuelCost < lowest) {
                lowest = fuelCost;
            }
        }

        System.out.println(lowest);
    }

    private static long calcFuelCostForCrabsTwo(List<Integer> crabs, int toGo) {
        long sum = 0;
        for(Integer in : crabs) {
            double n = Math.abs(toGo-in);
            sum += (long)((n+1)*(n/2));
        }

        return sum;
    }
}
