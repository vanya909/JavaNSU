package ru.nsu.programming.space_ship;

public enum Brand {
    PORSCHE(0.5),
    VOLKSWAGEN(1.0),
    BMW(1.5);
    public final double COEFFICIENT;

    Brand(double coefficient) {
        this.COEFFICIENT = coefficient;
    }
}
