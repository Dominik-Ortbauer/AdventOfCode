package at.adventofcode;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day4 {
    public static void partOne() {
        List<String> input = Main.readLinesFromFile(4);

        List<Integer> draws = Arrays.stream(input.get(0).split(",")).map(s -> Integer.parseInt(s)).collect(Collectors.toList());

        Integer[][][] boards = new Integer[(input.size()-1)/6][5][5];
        Boolean[][][] drew = new Boolean[(input.size()-1)/6][5][5];

        for (int i = 0; i < drew.length; i++) {
            for (int j = 0; j < drew[i].length; j++) {
                for (int k = 0; k < drew[i][j].length; k++) {
                    drew[i][j][k] = false;
                }
            }
        }

        int boardCnt = 0;
        int boardLineCnt = 0;
        for(int i = 2; i < input.size(); i++) {
            String s = input.get(i);

            if(s == "") {
                boardCnt++;
                boardLineCnt = 0;
            } else {
                String[] tmp = s.trim().split("\\s+");
                boards[boardCnt][boardLineCnt] = Arrays.stream(s.trim().split("\\s+")).map(st -> Integer.parseInt(st)).toArray(Integer[]::new);
                boardLineCnt++;
            }
        }

        for (int l = 0; l < draws.size(); l++) {
            for (int i = 0; i < boards.length; i++) {
                for (int j = 0; j < boards[i].length; j++) {
                    for (int k = 0; k < boards[i][j].length; k++) {
                        if(boards[i][j][k] == draws.get(l)) {
                            drew[i][j][k] = true;

                            int finishedBoard = findCompleteBoard(drew);
                            if(finishedBoard != -1) {
                                Integer[][] board = boards[finishedBoard];

                                int sum = calcUnmarkedBoardSum(board, drew[finishedBoard]);

                                System.out.println(sum * draws.get(l));
                                return;
                            }
                        }
                    }
                }
            }


        }
    }

    public static void partTwo() {
        List<String> input = Main.readLinesFromFile(4);

        List<Integer> draws = Arrays.stream(input.get(0).split(",")).map(s -> Integer.parseInt(s)).collect(Collectors.toList());

        Integer[][][] boards = new Integer[(input.size()-1)/6][5][5];
        Boolean[][][] drew = new Boolean[(input.size()-1)/6][5][5];

        for (int i = 0; i < drew.length; i++) {
            for (int j = 0; j < drew[i].length; j++) {
                for (int k = 0; k < drew[i][j].length; k++) {
                    drew[i][j][k] = false;
                }
            }
        }

        int boardCnt = 0;
        int boardLineCnt = 0;
        for(int i = 2; i < input.size(); i++) {
            String s = input.get(i);

            if(s == "") {
                boardCnt++;
                boardLineCnt = 0;
            } else {
                String[] tmp = s.trim().split("\\s+");
                boards[boardCnt][boardLineCnt] = Arrays.stream(s.trim().split("\\s+")).map(st -> Integer.parseInt(st)).toArray(Integer[]::new);
                boardLineCnt++;
            }
        }

        for (int l = 0; l < draws.size(); l++) {
            for (int i = 0; i < boards.length; i++) {
                if(boards[i] != null) {
                    for (int j = 0; j < boards[i].length; j++) {
                        for (int k = 0; k < boards[i][j].length; k++) {
                            if(boards[i][j][k] == draws.get(l)) {
                                drew[i][j][k] = true;
                            }
                        }
                    }
                }

                int finishedBoard = findCompleteBoard(drew);
                if(finishedBoard != -1) {
                    int nullCnt = 0;

                    for(Integer[][] t : boards) {
                        if(t == null) nullCnt++;
                    }

                    if(boards.length - nullCnt <= 1) {
                        int sum = calcUnmarkedBoardSum(boards[finishedBoard], drew[finishedBoard]);
                        System.out.println(sum * draws.get(l));
                        return;
                    }
                    boards[finishedBoard] = null;
                    drew[finishedBoard] = null;
                }
            }
        }
    }

    public static int calcUnmarkedBoardSum(Integer[][] board, Boolean[][] drew) {
        int sum = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if(!drew[i][j]) {
                    sum += board[i][j];
                }
            }
        }

        return sum;
    }

    public static int findCompleteBoard(Boolean[][][] boards) {
        for(int l = 0; l < boards.length; l++) {
            if(boards[l] != null) {
                Boolean[][] board = boards[l];
                boolean allTrue = true;
                for (int i = 0; i < board.length; i++) {
                    for (int j = 0; j < board[i].length; j++) {
                        allTrue = allTrue && board[i][j];
                    }

                    if(allTrue) {
                        return l;
                    }
                    allTrue = true;
                }



                allTrue = true;
                for (int i = 0; i < board.length; i++) {
                    for (int j = 0; j < board[i].length; j++) {
                        allTrue = allTrue && board[j][i];
                    }

                    if(allTrue) {
                        return l;
                    }

                    allTrue = true;
                }
            }
        }

        return -1;
    }
}
