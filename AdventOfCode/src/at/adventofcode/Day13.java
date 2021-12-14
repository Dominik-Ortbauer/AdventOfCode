package at.adventofcode;

import java.awt.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Day13 {
    public static void partOne() {
        List<String> lines = Main.readLinesFromFile(13);

        List<Point> dots = new LinkedList<>();
        int foldInstructionStartIndex = 0;
        for(int i = 0; i < lines.size(); i++) {
            String s = lines.get(i);

            if(s.equals("")) {
                foldInstructionStartIndex = i+1;
                break;
            }

            String[] split = s.split(",");
            dots.add(new Point(Integer.parseInt(split[0]), Integer.parseInt(split[1])));
        }

        for (int i = foldInstructionStartIndex; i <= foldInstructionStartIndex; i++) {
            String s = lines.get(i);

            String important = s.split(" ")[2];
            boolean onXAxis = important.split("=")[0].equals("x");
            int foldIndex = Integer.parseInt(important.split("=")[1]);

            if(onXAxis) {
                for (int j = dots.size()-1; j >= 0; j--) {
                    int x = dots.get(j).x;
                    if(x > foldIndex) {
                        Point newLocation = new Point(foldIndex - (x - foldIndex), dots.get(j).y);

                        if(dots.contains(newLocation)) {
                            dots.remove(j);
                        } else {
                            dots.set(j, newLocation);
                        }
                    }
                }
            } else {
                for (int j = dots.size()-1; j >= 0; j--) {
                    int y = dots.get(j).y;
                    if(y > foldIndex) {
                        Point newLocation = new Point(dots.get(j).x, foldIndex + foldIndex - y);

                        if(dots.contains(newLocation)) {
                            dots.remove(j);
                        } else {
                            dots.set(j, newLocation);
                        }
                    }
                }
            }
        }

        System.out.println(dots.size());
    }

    public static void partTwo() {
        List<String> lines = Main.readLinesFromFile(13);

        List<Point> dots = new LinkedList<>();
        int foldInstructionStartIndex = 0;
        for(int i = 0; i < lines.size(); i++) {
            String s = lines.get(i);

            if(s.equals("")) {
                foldInstructionStartIndex = i+1;
                break;
            }

            String[] split = s.split(",");
            dots.add(new Point(Integer.parseInt(split[0]), Integer.parseInt(split[1])));
        }

        for (int i = foldInstructionStartIndex; i < lines.size(); i++) {
            String s = lines.get(i);

            String important = s.split(" ")[2];
            boolean onXAxis = important.split("=")[0].equals("x");
            int foldIndex = Integer.parseInt(important.split("=")[1]);

            if(onXAxis) {
                for (int j = dots.size()-1; j >= 0; j--) {
                    int x = dots.get(j).x;
                    if(x > foldIndex) {
                        Point newLocation = new Point(foldIndex - (x - foldIndex), dots.get(j).y);

                        if(dots.contains(newLocation)) {
                            dots.remove(j);
                        } else {
                            dots.set(j, newLocation);
                        }
                    }
                }
            } else {
                for (int j = dots.size()-1; j >= 0; j--) {
                    int y = dots.get(j).y;
                    if(y > foldIndex) {
                        Point newLocation = new Point(dots.get(j).x, foldIndex + foldIndex - y);

                        if(dots.contains(newLocation)) {
                            dots.remove(j);
                        } else {
                            dots.set(j, newLocation);
                        }
                    }
                }
            }
        }

        int maxX = 0, maxY = 0;

        for(Point p : dots) {
            if(p.x > maxX) maxX = p.x;
            if(p.y > maxY) maxY = p.y;
        }

        char[][] text = new char[maxY+1][maxX+1];

        for (int i = 0; i < text.length; i++) {
            for (int j = 0; j < text[i].length; j++) {
                text[i][j] = ' ';
            }
        }

        for(Point p : dots) {
            text[p.y][p.x] = '#';
        }

        for(char[] row : text) {
            System.out.println(Arrays.toString(row));
        }
    }
}
