package ru.nsu.cw.quadratures;

public enum Quadratures {
    SIN,
    SQRT;

    public double getValue(double x) {
        if (this == SIN) {
            return Math.sin(x);
        } else {
            return Math.sqrt(x);
        }
    }

    public double trapezoidMethod(double step, double lowerLimit, double upperLimit) {
        double result = 0;
        for (double i = lowerLimit; i < upperLimit; i += step) {
            result += trapezoid(i, i + step);
        }

        return result;
    }

    public double simpsonMethod(double step, double lowerLimit, double upperLimit) {
        double result = 0;
        for (double i = lowerLimit; i < upperLimit; i += step) {
            result += quadraticPolynomial(i, i + step);
        }

        return result;
    }

    private double trapezoid(double lowerLimit, double upperLimit) {
        if (this == SIN) {
            lowerLimit *= Math.PI;
            upperLimit *= Math.PI;
        }
        return ((this.getValue(lowerLimit) + this.getValue(upperLimit)) * (upperLimit - lowerLimit)) / 2;
    }

    private double quadraticPolynomial(double lowerLimit, double upperLimit) {
        if (this == SIN) {
            lowerLimit *= Math.PI;
            upperLimit *= Math.PI;
        }
        return ((upperLimit - lowerLimit) / 6) * (this.getValue(lowerLimit) +
                                                  4 * this.getValue((lowerLimit + upperLimit) / 2) +
                                                  this.getValue(upperLimit));
    }

    private boolean isSin() {
        return this == SIN;
    }

    private boolean isSqrt() {
        return this == SQRT;
    }
}
