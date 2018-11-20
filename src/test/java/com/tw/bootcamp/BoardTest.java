package com.tw.bootcamp;


import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;

public class BoardTest {

    Board board;
    Cell[][] initialBoardStateCells;
    CellCoordinate coordinate;

    @Before
    public void setup() {
        initialBoardStateCells = createCellsWithValue(new Cell(CellState.EMPTY));
        board = new Board(initialBoardStateCells);
        coordinate = new CellCoordinate(0, 0);
    }

    @Test
    public void shouldHave9Cells() {
        assertEquals(9, board.getSize());
    }

    @Test
    public void shouldUpdateCoordinatesOfBoardWithGivenValue() throws CellOverrideException {
        board.set(coordinate, CellState.X);
        CellState state = board.get(coordinate);
        assertEquals(state, CellState.X);
    }

    @Test
    public void shouldHaveEmptyCellsWhenInitialised() {
        Cell[][] cells = board.getCells();
        assertEquals(initialBoardStateCells, cells);
    }

    @Test(expected = CellOverrideException.class)
    public void shouldThrowExceptionWhenOverridingCell() throws CellOverrideException {
        board.set(coordinate, CellState.X);
        board.set(coordinate, CellState.X);
    }

    @Test
    public void shouldReturnTrueIfBoardIsFinished() {
        Cell[][] firstRowFinished = new Cell[][]{
                {new Cell(CellState.X), new Cell(CellState.X), new Cell(CellState.X)},
                {new Cell(CellState.EMPTY), new Cell(CellState.EMPTY), new Cell(CellState.EMPTY)},
                {new Cell(CellState.EMPTY), new Cell(CellState.EMPTY), new Cell(CellState.EMPTY)}
        };
        board.assignGrid(firstRowFinished);
        assertTrue(board.isFinished());

        Cell[][] secondRowFinished = new Cell[][]{
                {new Cell(CellState.EMPTY), new Cell(CellState.EMPTY), new Cell(CellState.EMPTY)},
                {new Cell(CellState.X), new Cell(CellState.X), new Cell(CellState.X)},
                {new Cell(CellState.EMPTY), new Cell(CellState.EMPTY), new Cell(CellState.EMPTY)}
        };
        board.assignGrid(secondRowFinished);
        assertTrue(board.isFinished());

        Cell[][] thirdRowFinished = new Cell[][]{
                {new Cell(CellState.EMPTY), new Cell(CellState.EMPTY), new Cell(CellState.EMPTY)},
                {new Cell(CellState.EMPTY), new Cell(CellState.EMPTY), new Cell(CellState.EMPTY)},
                {new Cell(CellState.X), new Cell(CellState.X), new Cell(CellState.X)}
        };
        board.assignGrid(thirdRowFinished);
        assertTrue(board.isFinished());

        Cell[][] firstColFinished = new Cell[][]{
                {new Cell(CellState.X), new Cell(CellState.EMPTY), new Cell(CellState.EMPTY)},
                {new Cell(CellState.X), new Cell(CellState.EMPTY), new Cell(CellState.EMPTY)},
                {new Cell(CellState.X), new Cell(CellState.EMPTY), new Cell(CellState.EMPTY)}
        };
        board.assignGrid(firstColFinished);
        assertTrue(board.isFinished());

        Cell[][] secondColFinished = new Cell[][]{
                {new Cell(CellState.EMPTY), new Cell(CellState.X), new Cell(CellState.EMPTY)},
                {new Cell(CellState.EMPTY), new Cell(CellState.X), new Cell(CellState.EMPTY)},
                {new Cell(CellState.EMPTY), new Cell(CellState.X), new Cell(CellState.EMPTY)}
        };
        board.assignGrid(secondColFinished);
        assertTrue(board.isFinished());

        Cell[][] thirdColFinished = new Cell[][]{
                {new Cell(CellState.EMPTY), new Cell(CellState.EMPTY), new Cell(CellState.X)},
                {new Cell(CellState.EMPTY), new Cell(CellState.EMPTY), new Cell(CellState.X)},
                {new Cell(CellState.EMPTY), new Cell(CellState.EMPTY), new Cell(CellState.X)}
        };
        board.assignGrid(thirdColFinished);
        assertTrue(board.isFinished());

        Cell[][] firstDiagonalFinished = new Cell[][]{
                {new Cell(CellState.X), new Cell(CellState.EMPTY), new Cell(CellState.EMPTY)},
                {new Cell(CellState.EMPTY), new Cell(CellState.X), new Cell(CellState.EMPTY)},
                {new Cell(CellState.EMPTY), new Cell(CellState.EMPTY), new Cell(CellState.X)}
        };
        board.assignGrid(firstDiagonalFinished);
        assertTrue(board.isFinished());

        Cell[][] secondDiagonalFinished = new Cell[][]{
                {new Cell(CellState.EMPTY), new Cell(CellState.EMPTY), new Cell(CellState.X)},
                {new Cell(CellState.EMPTY), new Cell(CellState.X), new Cell(CellState.EMPTY)},
                {new Cell(CellState.X), new Cell(CellState.EMPTY), new Cell(CellState.EMPTY)}
        };
        board.assignGrid(secondDiagonalFinished);
        assertTrue(board.isFinished());
    }

    @Test
    public void shouldReturnTrueIfBoardIsFilledCompletely() {
        Cell[][] finishedStateCells = createCellsWithValue(new Cell(CellState.X));
        Board board = new Board(finishedStateCells);
        assertTrue(board.isFilled());
    }

    @Test
    public void shouldReturnFalseIfBoardIsNotFilledCompletely() {
        Cell[][] finishedStateCells = createCellsWithValue(new Cell(CellState.EMPTY));
        Board board = new Board(finishedStateCells);
        assertFalse(board.isFilled());
    }

    public static Cell[][] createCellsWithValue(Cell cell) {
        return new Cell[][]{
                {cell, cell, cell},
                {cell, cell, cell},
                {cell, cell, cell}
        };
    }
}