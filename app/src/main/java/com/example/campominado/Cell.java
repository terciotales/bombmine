package com.example.campominado;

public class Cell {
    public static final int BOMBA = -1;
    public static final int VAZIO = 0;

    private final int value;

    public Cell(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
