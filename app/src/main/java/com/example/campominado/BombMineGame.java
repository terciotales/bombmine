package com.example.campominado;

import java.util.ArrayList;
import java.util.List;

public class BombMineGame {
    private final com.example.campominado.MineGrid mineGrid;
    private boolean gameOver;
    private boolean flagMode;
    private boolean clearMode;
    private int flagCount;
    private final int numberBombs;
    private boolean timeExpired;

    public BombMineGame(int size, int numberBombs) {
        this.gameOver = false;
        this.flagMode = false;
        this.clearMode = true;
        this.timeExpired = false;
        this.flagCount = 0;
        this.numberBombs = numberBombs;
        mineGrid = new com.example.campominado.MineGrid(size);
        mineGrid.generateGrid(numberBombs);
    }

    public void handleCellClick(com.example.campominado.Cell cell) {
        if (!gameOver && !isGameWon() && !timeExpired && !cell.isRevealed()) {
            if (clearMode) {
                clear(cell);
            } else if (flagMode) {
                flag(cell);
            }
        }
    }

    public void clear(com.example.campominado.Cell cell) {
        int index = getMineGrid().getCells().indexOf(cell);
        getMineGrid().getCells().get(index).setRevealed(true);

        if (cell.getValue() == com.example.campominado.Cell.BOMB) {
            gameOver = true;
        } else if (cell.getValue() == com.example.campominado.Cell.BLANK) {
            List<com.example.campominado.Cell> toClear = new ArrayList<>();
            List<com.example.campominado.Cell> toCheckAdjacents = new ArrayList<>();

            toCheckAdjacents.add(cell);

            while (toCheckAdjacents.size() > 0) {
                com.example.campominado.Cell c = toCheckAdjacents.get(0);
                int cellIndex = getMineGrid().getCells().indexOf(c);
                int[] cellPos = getMineGrid().toXY(cellIndex);
                for (com.example.campominado.Cell adjacent: getMineGrid().adjacentCells(cellPos[0], cellPos[1])) {
                    if (adjacent.getValue() == com.example.campominado.Cell.BLANK) {
                        if (!toClear.contains(adjacent)) {
                            if (!toCheckAdjacents.contains(adjacent)) {
                                toCheckAdjacents.add(adjacent);
                            }
                        }
                    } else {
                        if (!toClear.contains(adjacent)) {
                            toClear.add(adjacent);
                        }
                    }
                }
                toCheckAdjacents.remove(c);
                toClear.add(c);
            }

            for (com.example.campominado.Cell c: toClear) {
                c.setRevealed(true);
            }
        }
    }

    public void flag(com.example.campominado.Cell cell) {
        cell.setFlagged(!cell.isFlagged());
        int count = 0;
        for (com.example.campominado.Cell c: getMineGrid().getCells()) {
            if (c.isFlagged()) {
                count++;
            }
        }
        flagCount = count;
    }

    public boolean isGameWon() {
        int numbersUnrevealed = 0;
        for (com.example.campominado.Cell c: getMineGrid().getCells()) {
            if (c.getValue() != com.example.campominado.Cell.BOMB && c.getValue() != com.example.campominado.Cell.BLANK && !c.isRevealed()) {
                numbersUnrevealed++;
            }
        }

        return numbersUnrevealed == 0;
    }

    public void toggleMode() {
        clearMode = !clearMode;
        flagMode = !flagMode;
    }

    public void outOfTime() {
        timeExpired = true;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public com.example.campominado.MineGrid getMineGrid() {
        return mineGrid;
    }

    public boolean isFlagMode() {
        return flagMode;
    }

    public boolean isClearMode() {
        return clearMode;
    }

    public int getFlagCount() {
        return flagCount;
    }

    public int getNumberBombs() {
        return numberBombs;
    }
}
