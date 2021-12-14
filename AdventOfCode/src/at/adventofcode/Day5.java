package at.adventofcode;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.List;
import java.util.Vector;

public class Day5 {
    public static void partOne() {
        List<String> lines = Main.readLinesFromFile(5);
        int[][] field = new int[1000][1000];

        for(String s : lines) {
            String[] split = s.split(" -> ");
            String[] split1 = split[0].split(",");
            int x1 = Integer.parseInt(split1[0]);
            int y1 = Integer.parseInt(split1[1]);
            split1 = split[1].split(",");
            int x2 = Integer.parseInt(split1[0]);
            int y2 = Integer.parseInt(split1[1]);

            Point start = new Point(x1, y1);
            Point end = new Point(x2, y2);

            Point tmp = new Point(x2-x1, y2-y1);
            tmp = new Point((int)(tmp.x / tmp.distance(new Point(0, 0))), (int)(tmp.y / tmp.distance(new Point(0, 0))));

            if(x1 == x2 || y1 == y2) {
                Point p = start;
                for(; p.distance(end) > 0; p.translate(tmp.x, tmp.y)) {
                    field[p.x][p.y]++;
                }
                field[p.x][p.y]++;
            }
        }

        int cnt = 0;
        for(int[] row : field) {
            for(int i : row) {
                if(i > 1) {
                    cnt++;
                }
            }
        }

        System.out.println(cnt);
    }

    public static void partTwo() {
        List<String> lines = Main.readLinesFromFile(5);
        int[][] field = new int[1000][1000];

        for(String s : lines) {
            String[] split = s.split(" -> ");
            String[] split1 = split[0].split(",");
            int x1 = Integer.parseInt(split1[0]);
            int y1 = Integer.parseInt(split1[1]);
            split1 = split[1].split(",");
            int x2 = Integer.parseInt(split1[0]);
            int y2 = Integer.parseInt(split1[1]);

            Point start = new Point(x1, y1);
            Point end = new Point(x2, y2);

            Point tmp = new Point(x2-x1, y2-y1);
            tmp = new Point((int)Math.round((tmp.x / tmp.distance(new Point(0, 0)))), (int)Math.round(tmp.y / tmp.distance(new Point(0, 0))));

            Point p = start;
            for(; p.distance(end) > 0; p.translate(tmp.x, tmp.y)) {
                field[p.x][p.y]++;
            }
            field[p.x][p.y]++;
        }

        int cnt = 0;
        for(int[] row : field) {
            for(int i : row) {
                if(i > 1) {
                    cnt++;
                }
            }
        }

        System.out.println(cnt);
    }
}
