package com.example.campominado;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MineGrid {
    private final List<Cell> cells;
    private final int size;

    public MineGrid(int size) {
        this.size = size;
        this.cells = new ArrayList<>();
        for (int i = 0; i < size * size; i++) {
            cells.add(new Cell(Cell.VAZIO));
        }
    }

    public void generateGrid() {
        int x = new Random().nextInt(size);
        int y = new Random().nextInt(size);

        if (cellAt(x, y).getValue() == Cell.VAZIO) {
            cells.set(x + (y * size), new Cell(Cell.BOMBA));
        }
    }

    public Cell cellAt(int x, int y) {
        if (x < 0 || x >= size || y < 0 || y >= size) {
            return null;
        }
        return cells.get(x + (y * size));
    }

    public List<Cell> getCells() {
        return cells;
    }
}
