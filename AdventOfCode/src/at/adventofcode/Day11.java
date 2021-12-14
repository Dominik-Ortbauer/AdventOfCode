package at.adventofcode;

import java.util.Arrays;
import java.util.List;

public class Day11 {
    private static int[][] energyLevels;
    private static int flashCnt = 0;

    public static void partOne() {
        List<String> lines = Main.readLinesFromFile(11);

        energyLevels = new int[lines.size()][0];

        for (int i = 0; i < lines.size(); i++) {
            energyLevels[i] = lines.get(i).chars().map(in -> in - '0').toArray();
        }

        for(int l = 0; l < 100; l++) {
            for (int i = 0; i < energyLevels.length; i++) {
                for (int j = 0; j < energyLevels[i].length; j++) {
                    energyLevels[i][j]++;
                }
            }

            for (int i = 0; i < energyLevels.length; i++) {
                for (int j = 0; j < energyLevels[i].length; j++) {
                    if(energyLevels[i][j] >= 10) {
                        handleFlash(i, j);
                    }
                }
            }

            for (int i = 0; i < energyLevels.length; i++) {
                for (int j = 0; j < energyLevels[i].length; j++) {
                    if(energyLevels[i][j] < 0) {
                        energyLevels[i][j] = 0;
                    }
                }
            }
        }

        System.out.println(flashCnt);
    }

    private static boolean insideArray(int x, int y) {
        return x >= 0 && x < energyLevels.length && y >= 0 && y < energyLevels[0].length;
    }

    private static void handleFlash(int x, int y) {
        if(!insideArray(x, y)) return;

        energyLevels[x][y]++;
        if(energyLevels[x][y] >= 10) {
            flashCnt++;
            energyLevels[x][y] = -1000;
            for (int i = x-1; i <= x+1; i++) {
                for (int j = y-1; j <= y+1; j++) {
                    if(i-x != 0 || j-y != 0) {
                        handleFlash(i, j);
                    }
                }
            }
        }
    }

    public static void partTwo() {
        List<String> lines = Main.readLinesFromFile(11);

        energyLevels = new int[lines.size()][0];

        for (int i = 0; i < lines.size(); i++) {
            energyLevels[i] = lines.get(i).chars().map(in -> in - '0').toArray();
        }

        for(int l = 0; l < 1000; l++) {
            for (int i = 0; i < energyLevels.length; i++) {
                for (int j = 0; j < energyLevels[i].length; j++) {
                    energyLevels[i][j]++;
                }
            }

            for (int i = 0; i < energyLevels.length; i++) {
                for (int j = 0; j < energyLevels[i].length; j++) {
                    if(energyLevels[i][j] >= 10) {
                        handleFlash(i, j);
                    }
                }
            }

            boolean allFlashed = true;
            for(int[] row : energyLevels) {
                for(int i : row) {
                    allFlashed = allFlashed && i < 0;
                }
            }

            if(allFlashed) {
                System.out.println(l);
                return;
            }

            for (int i = 0; i < energyLevels.length; i++) {
                for (int j = 0; j < energyLevels[i].length; j++) {
                    if(energyLevels[i][j] < 0) {
                        energyLevels[i][j] = 0;
                    }
                }
            }
        }
    }
}
