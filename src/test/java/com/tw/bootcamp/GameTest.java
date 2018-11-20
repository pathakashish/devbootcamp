package com.tw.bootcamp;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class GameTest {

    Game game;
    Player player1, player2;
    Board board;
    Cell[][] initialCells;
    InputReader reader1 = Mockito.mock(InputReader.class);
    InputReader reader2 = Mockito.mock(InputReader.class);
    int[] inputMoves1 = new int[]{
            0, 0, // Player 1
            2, 0, // Player 1
            1, 2, // Player 1
    };
    int index1 = 0;
    int[] inputMoves2 = new int[]{
            1, 1, // Player 2
            0, 1, // Player 2
            2, 1 // Player 2
    };
    int index2 = 0;

    @Before
    public void setup() {
        Mockito.when(reader1.getCoordinates()).then((Answer<CellCoordinate>) invocation -> new CellCoordinate(inputMoves1[index1++], inputMoves1[index1++]));
        Mockito.when(reader2.getCoordinates()).then((Answer<CellCoordinate>) invocation -> new CellCoordinate(inputMoves2[index2++], inputMoves2[index2++]));
        player1 = new Player(reader1);
        player2 = new Player(reader2);
        initialCells = BoardTest.createCellsWithValue(new Cell(CellState.EMPTY));
        board = new Board(initialCells);
        game = new Game();
    }

    @Test
    public void shouldStartWithSameBoardForBothGivenPlayers() {
        game.init(board, player1, player2);
        assertEquals(board, player1.getBoard());
        assertEquals(board, player2.getBoard());
        assertEquals(CellState.X, player1.getSymbol());
        assertEquals(CellState.O, player2.getSymbol());
    }

    @Test
    public void winnerWithCompleteColumn() throws CellOverrideException {
        game.init(board, player1, player2);
        game.start();
        assertEquals(player2, game.getWinner());
    }

    @Test
    public void winnerWithCompleteDiagonal2() throws CellOverrideException {
        Mockito.when(reader1.getCoordinates())
                .thenReturn(new CellCoordinate(1, 0))
                .thenReturn(new CellCoordinate(1, 2))
                .thenReturn(new CellCoordinate(2, 2));
        Player player1 = new Player(reader1);

        Mockito.when(reader2.getCoordinates())
                .thenReturn(new CellCoordinate(0, 2))
                .thenReturn(new CellCoordinate(1, 1))
                .thenReturn(new CellCoordinate(2, 0));
        Player player2 = new Player(reader2);
        game.init(board, player1, player2);
        game.start();
        assertEquals(player2, game.getWinner());
    }

    @Test
    public void winnerWithCompleteRow() throws CellOverrideException {
        Mockito.when(reader1.getCoordinates())
                .thenReturn(new CellCoordinate(0, 1))
                .thenReturn(new CellCoordinate(2, 1))
                .thenReturn(new CellCoordinate(1, 1));
        Player player1 = new Player(reader1);

        Mockito.when(reader2.getCoordinates())
                .thenReturn(new CellCoordinate(1, 0))
                .thenReturn(new CellCoordinate(2, 0));
        Player player2 = new Player(reader2);
        game.init(board, player1, player2);
        game.start();
        assertEquals(player1, game.getWinner());
    }

    @Test
    public void winnerWithCompleteDiagonal1() throws CellOverrideException {
        Mockito.when(reader1.getCoordinates())
                .thenReturn(new CellCoordinate(0, 0))
                .thenReturn(new CellCoordinate(2, 2))
                .thenReturn(new CellCoordinate(1, 1));
        Player player1 = new Player(reader1);

        Mockito.when(reader2.getCoordinates())
                .thenReturn(new CellCoordinate(0, 1))
                .thenReturn(new CellCoordinate(2, 1));
        Player player2 = new Player(reader2);
        game.init(board, player1, player2);
        game.start();
        assertEquals(player1, game.getWinner());
    }

    @Test
    public void matchDraws() throws CellOverrideException {
        Mockito.when(reader1.getCoordinates())
                .thenReturn(new CellCoordinate(0, 0))
                .thenReturn(new CellCoordinate(2, 1))
                .thenReturn(new CellCoordinate(2, 0))
                .thenReturn(new CellCoordinate(1, 2))
                .thenReturn(new CellCoordinate(0, 1));
        Player player1 = new Player(reader1);

        Mockito.when(reader2.getCoordinates())
                .thenReturn(new CellCoordinate(1, 1))
                .thenReturn(new CellCoordinate(2, 2))
                .thenReturn(new CellCoordinate(0, 2))
                .thenReturn(new CellCoordinate(1, 0));
        Player player2 = new Player(reader2);
        game.init(board, player1, player2);
        game.start();
        assertTrue(game.isADraw());
        assertNull(game.getWinner());
    }
}