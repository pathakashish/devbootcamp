package com.tw.bootcamp;

public class Game {
    private Player player1;
    private Player player2;
    private Board board;
    private Player currentPlayer;

    public void init(Board board, Player player1, Player player2) {
        this.board = board;

        this.player1 = player1;
        this.player1.assignBoard(board);
        this.player1.assignSymbol(CellState.X);

        this.player2 = player2;
        this.player2.assignBoard(board);
        this.player2.assignSymbol(CellState.O);

        this.currentPlayer = player1;
    }

    public Player getWinner() {
        if(board.isFinished())
            return currentPlayer == player1 ? player2 : player1;
        else
            return null;
    }

    public void start() throws CellOverrideException {
        play();
    }

    private void play() throws CellOverrideException {
        if(board.isFilled() || board.isFinished())
            return;
        currentPlayer.play();
        currentPlayer = currentPlayer == player1 ? player2 : player1;
        play();
    }

    public boolean isADraw() {
        return board.isFilled() && !board.isFinished();
    }
}
