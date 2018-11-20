package com.tw.bootcamp;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;

import static org.junit.Assert.assertEquals;

public class PlayerTest {

    Board board;
    Cell[][] initialBoardStateCells;
    private Player player1;
    CellState symbol;

    InputReader reader = Mockito.mock(InputReader.class);

    @Before
    public void setup() {
        initialBoardStateCells = new Cell[][]{
                {new Cell(CellState.EMPTY), new Cell(CellState.EMPTY), new Cell(CellState.EMPTY)},
                {new Cell(CellState.EMPTY), new Cell(CellState.EMPTY), new Cell(CellState.EMPTY)},
                {new Cell(CellState.EMPTY), new Cell(CellState.EMPTY), new Cell(CellState.EMPTY)}
        };
        symbol = CellState.X;
        board = new Board(initialBoardStateCells);

        Mockito.when(reader.getCoordinates()).then((Answer<CellCoordinate>) invocation -> new CellCoordinate(0, 1));
        player1 = new Player(reader);
        player1.assignBoard(board);
        player1.assignSymbol(symbol);
    }

    @Test
    public void shouldHaveCellBoardAndSymbol() {
        assertEquals(player1.getBoard(), board);
        assertEquals(player1.getSymbol(), symbol);
    }

    @Test
    public void shouldBeAbleToPlayAndChangeBoard() throws CellOverrideException {
        CellCoordinate coordinate = player1.play();
        Board board = player1.getBoard();
        assertEquals(player1.getSymbol(), board.get(coordinate));
    }
}