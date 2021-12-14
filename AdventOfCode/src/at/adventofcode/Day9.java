package at.adventofcode;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Day9 {
    public static void partOne() {
        List<String> lines = Main.readLinesFromFile(9);

        int cnt = 0;

        for(int i = 0; i < lines.size(); i++) {
            for(int j = 0; j < lines.get(i).length(); j++) {
                char self = lines.get(i).charAt(j);
                boolean isLowest = true;
                if(i-1 >= 0) {
                    if(lines.get(i-1).charAt(j) <= self) {
                        isLowest = false;
                    }
                }

                if(i+1 < lines.size()) {
                    if(lines.get(i+1).charAt(j) <= self) {
                        isLowest = false;
                    }
                }

                if(j-1 >= 0) {
                    if(lines.get(i).charAt(j-1) <= self) {
                        isLowest = false;
                    }
                }

                if(j+1 < lines.get(i).length()) {
                    if(lines.get(i).charAt(j+1) <= self) {
                        isLowest = false;
                    }
                }

                if(isLowest) {
                    int danger = self - '0';
                    cnt += (danger + 1);
                }
            }
        }

        System.out.println(cnt);
    }

    public static void partTwo() {
        List<String> lines = Main.readLinesFromFile(9);

        List<Integer> basins = new LinkedList<>();

        for(int i = 0; i < lines.size(); i++) {
            for(int j = 0; j < lines.get(i).length(); j++) {
                char self = lines.get(i).charAt(j);
                boolean isLowest = true;
                if(i-1 >= 0) {
                    if(lines.get(i-1).charAt(j) <= self) {
                        isLowest = false;
                    }
                }

                if(i+1 < lines.size()) {
                    if(lines.get(i+1).charAt(j) <= self) {
                        isLowest = false;
                    }
                }

                if(j-1 >= 0) {
                    if(lines.get(i).charAt(j-1) <= self) {
                        isLowest = false;
                    }
                }

                if(j+1 < lines.get(i).length()) {
                    if(lines.get(i).charAt(j+1) <= self) {
                        isLowest = false;
                    }
                }

                if(isLowest) {
                    basins.add(cntBasinSize(i, j, lines));
                }
            }
        }

        basins.sort(Comparator.reverseOrder());

        System.out.println(basins.get(0) * basins.get(1) * basins.get(2));
    }

    static class MyPoint {
        int x;
        int y;

        public MyPoint(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            MyPoint myPoint = (MyPoint) o;
            return x == myPoint.x && y == myPoint.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    static List<MyPoint> alreadySeen = new LinkedList<>();

    private static int cntBasinSize(int row, int col, List<String> lines) {
        if(alreadySeen.contains(new MyPoint(row, col)) ||
        row < 0 || row >= lines.size() ||
        col < 0 || col >= lines.get(0).length() ||
        lines.get(row).charAt(col) == '9') return 0;

        int size = 0;

        alreadySeen.add(new MyPoint(row, col));

        size += cntBasinSize(row-1, col, lines);
        size += cntBasinSize(row+1, col, lines);
        size += cntBasinSize(row, col-1, lines);
        size += cntBasinSize(row, col+1, lines);

        return size+1;
    }
}
