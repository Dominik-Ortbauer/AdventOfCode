package at.adventofcode;

import java.util.List;

public class Day1 {
    public static void partOne() {
        List<String> lines = Main.readLinesFromFile(1);

        Integer last = 0;
        int cnt = 0;
        for(String s : lines) {
            Integer cur = Integer.parseInt(s);
            if(cur > last) {
                cnt++;
            }
            last = cur;
        }

        System.out.println(cnt-1);
    }

    public static void partTwo() {
        List<String> lines = Main.readLinesFromFile(1);

        Integer last = 0;
        int cnt = 0;
        for(int i = 0; i + 2 < lines.size(); i += 1) {
            Integer cur = Integer.parseInt(lines.get(i));
            cur += Integer.parseInt(lines.get(i+1));
            cur += Integer.parseInt(lines.get(i+2));

            if(cur > last) {
                cnt ++;
            }

            last = cur;
        }

        System.out.println(cnt-1);
    }
}
