package com.example.campominado;

public class Cell {
    public static final int BOMBA = -1;
    public static final int VAZIO = 0;

    private final int value;
    private boolean foiRevelado;

    public Cell(int value) {
        this.value = value;
        this.foiRevelado = false;
    }

    public int getValue() {
        return value;
    }

    public boolean foiRevelado() {
        return foiRevelado;
    }

    public void setRevelado(boolean revelado) {
        foiRevelado = revelado;
    }
}
