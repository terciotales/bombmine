package com.example.campominado;

public class BombMineGame {
    private final com.example.campominado.MineGrid mineGrid;
    private final boolean gameOver;

    public BombMineGame(int size) {
        this.gameOver = false;
        mineGrid = new com.example.campominado.MineGrid(size);
        mineGrid.generateGrid();
    }

    public boolean ganhouJogo() {
        boolean ganhou = false;
        for (com.example.campominado.Cell c: getMineGrid().getCells()) {
            if (c.getValue() == com.example.campominado.Cell.BOMBA) {
                ganhou = true;
                break;
            }
        }

        return ganhou;
    }

    public boolean perdeuJogo() {
        return gameOver;
    }

    public com.example.campominado.MineGrid getMineGrid() {
        return mineGrid;
    }
}
