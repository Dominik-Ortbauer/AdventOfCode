package at.adventofcode;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Day14.partTwo();
    }

    public static List<String> readLinesFromFile(int day) {
        String url = "C:\\Users\\domio\\Documents\\Java\\AdventOfCode\\Input\\Day" + day + "_Input.txt";
        List<String> lines = new LinkedList<>();
        try {
            File file = new File(url);
            Scanner scanner = new Scanner(file);

            while(scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }
        } catch (Exception e) {
            return null;
        }

        return lines;
    }
}
