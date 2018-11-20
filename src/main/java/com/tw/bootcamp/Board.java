package com.tw.bootcamp;

public class Board {

    private Cell[][] grid;

    public Board(Cell[][] grid) {
        this.grid = grid;
    }

    public int getSize() {
        return 9;
    }

    public Cell[][] getCells() {
        return grid;
    }

    public void set(CellCoordinate coordinate, CellState state) throws CellOverrideException {
        if (get(coordinate) != CellState.EMPTY) {
            throw new CellOverrideException();
        }
        grid[coordinate.x][coordinate.y] = new Cell(state);
    }

    public CellState get(CellCoordinate coordinate) {
        return grid[coordinate.x][coordinate.y].state;
    }

    public boolean isFinished() {
        return isRowDone() || isColDone() || isDiagonal1Done() || isDiagonal2Done();
    }

    private boolean isColDone() {
        for (int i = 0; i < grid.length; i++) {
            if (CellState.EMPTY != grid[0][i].state && grid[0][i].state == grid[1][i].state && grid[0][i].state == grid[2][i].state)
                return true;
        }
        return false;
    }

    private boolean isRowDone() {
        for (int i = 0; i < grid.length; i++) {
            if (CellState.EMPTY != grid[i][0].state && grid[i][0].state == grid[i][1].state && grid[i][0].state == grid[i][2].state)
                return true;
        }
        return false;
    }

    private boolean isDiagonal1Done() {
        //checkDiagonal1
        boolean diagonal1Done = true;
        for (int i = 0; i < grid.length - 1; i++) {
            if (CellState.EMPTY == grid[i][i].state|| grid[i][i].state != grid[i + 1][i + 1].state) {
                diagonal1Done = false;
                break;
            }
        }
        return diagonal1Done;
    }

    private boolean isDiagonal2Done() {
        boolean diagonal2Done = true;
        for (int i = grid.length - 1; i > 0; i--) {
            if (CellState.EMPTY == grid[i][grid.length - 1 - i].state || grid[i][grid.length - 1 - i].state != grid[i - 1][grid.length - i].state) {
                diagonal2Done = false;
                break;
            }
        }
        return diagonal2Done;
    }


    public boolean isFilled() {
        for (Cell[] cells : grid) {
            for (Cell cell : cells) {
                if (cell.state == CellState.EMPTY)
                    return false;
            }
        }
        return true;
    }

    public void assignGrid(Cell[][] newGrid) {
        this.grid = newGrid;
    }
}
