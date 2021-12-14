package at.adventofcode;

import org.junit.Assert;
import org.junit.Test;

public class Day4_UT {
    @Test
    public void testFindCompleteBoardShallNotFindCompleteBoard() {
        Boolean[][][] tmp = {{{true, false}, {false, false}}};

        Assert.assertEquals(-1, Day4.findCompleteBoard(tmp));
    }

    @Test
    public void testFindCompleteBoardWithFirstRowCompleted() {
        Boolean[][][] tmp = {{{true, true}, {false, false}}};

        Assert.assertEquals(0, Day4.findCompleteBoard(tmp));
    }

    @Test
    public void testFindCompleteBoardWithFirstColumnCompleted() {
        Boolean[][][] tmp = {{{false, true}, {false, true}}};

        Assert.assertEquals(0, Day4.findCompleteBoard(tmp));
    }

    @Test
    public void testFindCompleteBoardShallReturn1() {
        Boolean[][][] tmp = {{{false, false}, {false, false}}, {{true, false}, {true, false}}};

        Assert.assertEquals(1, Day4.findCompleteBoard(tmp));
    }

    @Test
    public void testCalcBoardSumShallReturn7() {
        Boolean[][] tmp = {{true, false}, {true, false}};
        Integer[][] board = {{98, 4}, {6, 3}};

        Assert.assertEquals(7, Day4.calcUnmarkedBoardSum(board, tmp));
    }

    @Test
    public void testCalcBoardSumShallReturn3() {
        Boolean[][] tmp = {{true, true}, {true, false}};
        Integer[][] board = {{98, 4}, {6, 3}};

        Assert.assertEquals(3, Day4.calcUnmarkedBoardSum(board, tmp));
    }
}
