package at.adventofcode;

import java.util.LinkedList;
import java.util.List;

public class Day8 {
    public static void partOne() {
        List<String> input = Main.readLinesFromFile(8);

        int cnt = 0;

        for(String s : input) {
            String[] digits = s.split(" \\| ")[1].split(" ");

            for(String digit : digits) {
                if(digit.length() == 2 || digit.length() == 4 || digit.length() == 7 || digit.length() == 3) {
                    cnt++;
                }
            }
        }

        System.out.println(cnt);
    }

    public static void partTwo() {
        List<String> input = Main.readLinesFromFile(8);

        int sum = 0;

        for(String s : input) {
            String[] split = s.split(" \\| ");
            String[] digits = split[0].split(" ");
            String[] number = split[1].split(" ");

            char[] code = new char[7];

            code[0] = getTop(digits);
            code[3] = getMiddle(digits);
            code[1] = getTopLeft(digits, code[3]);
            code[5] = getBottomRight(digits);
            code[2] = getTopRight(digits, code[5]);
            code[6] = getBottom(digits, code[0], code[3]);
            code[4] = getBottomLeft(digits, code);

            sum += getNumber(number, code);
        }

        System.out.println(sum);
    }

    private static int getNumber(String[] number, char[] code) {
        int num = 0;

        for(String s : number) {
            num *= 10;

            String newCode = "";

            for(char c : s.toCharArray()) {
                newCode += String.valueOf(code).indexOf(c);
            }

            num += Digit.findDigit(newCode);
        }

        return num;
    }

    private enum Digit {
        one("25", 1),
        seven("025", 7),
        four("1235", 4),
        two("02346", 2),
        three("02356", 3),
        five("01356", 5),
        nine("012356", 9),
        zero("012456", 0),
        six("013456", 6),
        eight("0123456", 8);

        private String mCode;
        private int mValue;

        Digit(String code, int value) {

            mCode = code;
            mValue = value;
        }

        public static int findDigit(String input) {
            for(Digit d : Digit.values()) {
                boolean allIncluded = true;

                for(char c : input.toCharArray()) {
                    if(!d.mCode.contains(String.valueOf(c))) allIncluded = false;
                }

                if(allIncluded) return d.mValue;
            }

            return 0;
        }
    }

    private static char getBottomLeft(String[] digits, char[] code) {
        String eight = null;

        for(String s : digits) {
            if(s.length() == 7) eight = s;
        }

        for(char c : eight.toCharArray()) {
            if(!String.valueOf(code).contains(String.valueOf(c))) return c;
        }

        return 0;
    }

    private static char getBottom(String[] digits, char top, char middle) {
        List<String> relevant = new LinkedList<>(); //2, 3, 5

        for(String s : digits) {
            if(s.length() == 5) relevant.add(s);
        }

        String horizontalBars = getOverlap(relevant.toArray(new String[0]));

        for(char c : horizontalBars.toCharArray()) {
            if(c != top && c != middle) return c;
        }

        return 0;
    }

    private static char getTopRight(String[] digits, char bottomRight) {
        String one = null;

        for(String s : digits) {
            if(s.length() == 2) one = s;
        }

        for(char c : one.toCharArray()) {
            if(c != bottomRight) return c;
        }

        return 0;
    }

    private static char getBottomRight(String[] digits) {
        List<String> relevant = new LinkedList<>(); //will be filled with 0, 1, 6 and 9

        for(String s : digits) {
            if(s.length() == 6 || s.length() == 2) {
                relevant.add(s);
            }
        }

        return getOverlap(relevant.toArray(String[]::new)).charAt(0);
    }

    private static String getOverlap(String[] input) {
        String res = "";

        for (String s : input) {
            for(char c : s.toCharArray()) {
                boolean allContain = true;
                for(String s1 : input) {
                    if(!s1.contains(String.valueOf(c))) allContain = false;
                }

                if(allContain) {
                    res += c;
                }
            }
        }

        return res;
    }

    private static char getTopLeft(String[] digits, char middle) {
        String one = null, four = null;

        for(String s : digits) {
            if(s.length() == 2) one = s;
            if(s.length() == 4) four = s;
        }

        for(char c : four.toCharArray()) {
            if(!one.contains(String.valueOf(c)) && c != middle) return c;
        }

        return 0;
    }

    private static char getTop(String[] digits) {
        String one = null, seven = null;

        for(String s : digits) {
            if(s.length() == 2) one = s;
            if(s.length() == 3) seven = s;
        }

        for(char c : seven.toCharArray()) {
            if(!one.contains(String.valueOf(c))) {
                return c;
            }
        }

        return 0;
    }

    private static char getMiddle(String[] digits) {
        List<String> relevant = new LinkedList<>(); //will be filled with 2, 3, 4, and 5

        for(String s : digits) {
            if(s.length() == 5 || s.length() == 4) {
                relevant.add(s);
            }
        }

        for(String s : relevant) {
            for(char c : s.toCharArray()) {
                boolean allContain = true;

                for(String s1 : relevant) {
                    if(!s1.contains(String.valueOf(c))) {
                        allContain = false;
                    }
                }

                if(allContain) {
                    return c;
                }
            }
        }

        return 0;
    }
}
