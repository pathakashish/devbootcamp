package com.tw.bootcamp;

public class Player {

    private Board board;
    private CellState assignedSymbol;
    private InputReader reader;

    public Player(InputReader reader) {
        this.reader = reader;
    }

    public Board getBoard() {
        return board;
    }

    public CellCoordinate play() throws CellOverrideException {
        CellCoordinate coordinate = reader.getCoordinates();
        board.set(coordinate, assignedSymbol);
        return coordinate;
    }

    public CellState getSymbol() {
        return assignedSymbol;
    }

    public void assignBoard(Board board) {
        this.board = board;
    }

    public void assignSymbol(CellState symbol) {
        this.assignedSymbol = symbol;
    }
}
