package com.example.campominado;

public class BombMineGame {
    private final MineGrid mineGrid;

    public BombMineGame(int size) {
        mineGrid = new MineGrid(size);
        mineGrid.generateGrid();
    }

    public boolean ganhouJogo(Cell cell) {
        return cell.getValue() == Cell.BOMBA;
    }

    public MineGrid getMineGrid() {
        return mineGrid;
    }
}
