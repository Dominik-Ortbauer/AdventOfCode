package at.adventofcode;

import java.util.LinkedList;
import java.util.List;

public class Day3 {
    public static void partOne() {
        List<String> lines = Main.readLinesFromFile(3);

        boolean[][] bits = new boolean[lines.get(0).length()][lines.size()];

        boolean[] gamma = new boolean[lines.get(0).length()];

        for(int i = 0; i < bits.length; i++) {
            int ones = 0;
            int zeros = 0;
            for(int j = 0; j < bits[i].length; j++) {
                if(lines.get(j).toCharArray()[i] == '0') {
                    zeros++;
                } else {
                    ones++;
                }
            }

            if(ones > zeros) {
                gamma[i] = true;
            } else {
                gamma[i] = false;
            }
        }

        int gammaDecimal = 0;
        int epsilonDecimal = 0;
        for(boolean bit : gamma) {
            gammaDecimal = gammaDecimal * 2 + (bit ? 1 : 0);
            epsilonDecimal = epsilonDecimal * 2 + (bit ? 0 : 1);
        }

        System.out.println(gammaDecimal * epsilonDecimal);
    }

    public static void partTwo() {
        List<String> lines = Main.readLinesFromFile(3);

        String[] validLines = lines.toArray(new String[0]);

        int i = 0;
        while(validLines.length > 1) {
            int ones = 0;
            int zeros = 0;
            for(String s : validLines) {
                if(s.toCharArray()[i] == '0') {
                    zeros++;
                } else {
                    ones++;
                }
            }

            List<String> newLines = new LinkedList<>();
            for(String s : validLines) {
                if(s.toCharArray()[i] == (ones >= zeros ? '1' : '0')) {
                    newLines.add(s);
                }
            }
            validLines = newLines.toArray(new String[0]);

            i++;
        }

        String[] validLines2 = lines.toArray(new String[0]);
        i = 0;
        while(validLines2.length > 1) {
            int ones = 0;
            int zeros = 0;
            for(String s : validLines2) {
                if(s.toCharArray()[i] == '0') {
                    zeros++;
                } else {
                    ones++;
                }
            }

            List<String> newLines = new LinkedList<>();
            for(String s : validLines2) {
                if(s.toCharArray()[i] == (zeros > ones ? '1' : '0')) {
                    newLines.add(s);
                }
            }
            validLines2 = newLines.toArray(new String[0]);

            i++;
        }

        int oxygen = 0;
        int co2 = 0;
        for(char c : validLines[0].toCharArray()) {
            oxygen = oxygen * 2 + (c == '1' ? 1 : 0);
        }

        for(char c : validLines2[0].toCharArray()) {
            co2 = co2 * 2 + (c == '1' ? 1 : 0);
        }

        System.out.println(oxygen * co2);
    }
}
