package at.adventofcode;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Day6 {
    public static void partOne() {
        List<String> input = Main.readLinesFromFile(6);

        List<Integer> fish = Arrays.stream(input.get(0).split(",")).map(s -> Integer.parseInt(s)).collect(Collectors.toList());

        for(int i = 0; i < 80; i++) {
            for (int j = 0; j < fish.size(); j++) {
                if(fish.get(j) <= 0) {
                    fish.set(j, 7);
                    fish.add(9);
                }

                fish.set(j, fish.get(j)-1);
            }
        }

        System.out.println(fish.size());
    }

    public static void partTwo() {
        List<String> input = Main.readLinesFromFile(6);

        List<Integer> fish = Arrays.stream(input.get(0).split(",")).map(s -> Integer.parseInt(s)).collect(Collectors.toList());

        BigInteger[] groupedFish = new BigInteger[9];

        for (int i = 0; i < groupedFish.length; i++) {
            groupedFish[i] = new BigInteger("0");
        }

        for(Integer i : fish) {
            groupedFish[i] = groupedFish[i].add(new BigInteger("1"));
        }

        for(int i = 0; i < 256; i++) {
            BigInteger tmp = groupedFish[0];

            for(int j = 0; j < groupedFish.length-1; j++) {
                groupedFish[j] = groupedFish[j+1];
            }

            groupedFish[8] = tmp;
            groupedFish[6] = groupedFish[6].add(tmp);
        }

        BigInteger sum = new BigInteger("0");
        for(BigInteger l : groupedFish) {
            sum = sum.add(l);
        }

        System.out.println(sum);
    }
}
