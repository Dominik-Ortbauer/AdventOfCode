package at.adventofcode;

import java.util.*;

public class Day10 {
    public static void partOne() {
        List<String> lines = Main.readLinesFromFile(10);

        brackets.put('(', ')');
        brackets.put('[', ']');
        brackets.put('{', '}');
        brackets.put('<', '>');

        int errCnt = 0;

        for(String s : lines) {
            errCnt += evaluateLine(s);
        }

        System.out.println(errCnt);
    }

    private static Map<Character, Character> brackets = new HashMap<>();

    private static int evaluateLine(String s) {
        Stack<Character> openBrackets = new Stack<>();

        for(char c : s.toCharArray()) {
            if(c == '(' || c == '[' || c == '{' || c == '<') {
                openBrackets.push(c);
            }

            if(c == ')' || c == ']' || c == '}' || c == '>') {
                char top = openBrackets.peek();
                if(c == brackets.get(top)) {
                    openBrackets.pop();
                } else {
                    switch(c) {
                        case ')':
                            return 3;

                        case ']':
                            return 57;

                        case '}':
                            return 1197;

                        case '>':
                            return 25137;
                    }
                }
            }
        }

        return 0;
    }

    public static void partTwo() {
        List<String> lines = Main.readLinesFromFile(10);

        brackets.put('(', ')');
        brackets.put('[', ']');
        brackets.put('{', '}');
        brackets.put('<', '>');

        for(int i = lines.size()-1; i >= 0; i--) {
            if(evaluateLine(lines.get(i)) != 0) {
                lines.remove(lines.get(i));
            }
        }

        List<Long> lineValues = new LinkedList<>();

        for(String s : lines) {
            Stack<Character> remainingLines = evaluateLinePartTwo(s);

            long lineValue = 0;
            while(!remainingLines.isEmpty()) {
                Character cur = remainingLines.pop();
                lineValue *= 5;

                switch (cur) {
                    case '(':
                        lineValue += 1;
                        break;

                    case '[':
                        lineValue += 2;
                        break;

                    case '{':
                        lineValue += 3;
                        break;

                    case '<':
                        lineValue += 4;
                        break;
                }
            }

            lineValues.add(lineValue);
        }

        lineValues.sort(Long::compareTo);

        System.out.println(lineValues.get((lineValues.size())/2));
    }

    private static Stack<Character> evaluateLinePartTwo(String s) {
        Stack<Character> openBrackets = new Stack<>();

        for(char c : s.toCharArray()) {
            if(c == '(' || c == '[' || c == '{' || c == '<') {
                openBrackets.push(c);
            }

            if(c == ')' || c == ']' || c == '}' || c == '>') {
                char top = openBrackets.peek();
                if(c == brackets.get(top)) {
                    openBrackets.pop();
                }
            }
        }

        return openBrackets;
    }
}
