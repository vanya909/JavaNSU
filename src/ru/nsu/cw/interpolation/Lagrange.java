package ru.nsu.cw.interpolation;

import java.util.ArrayList;

public class Lagrange {

    private static double polynomial2(double x, double x0, double h) {
        return func(x0-h)*(x-x0-h)*(x-x0-3*h) / (8*h*h) -
               func(x0+h)*(x-x0+h)*(x-x0-3*h) / (4*h*h) +
               func(x0+3*h)*(x-x0+h)*(x-x0-h) / (8*h*h);
    }
    private static double polynomial3(double x, double x0, double h) {
        return -func(x0-3*h)*(x-x0+h)*(x-x0-h)*(x-x0-3*h) / (48*h*h*h) +
                func(x0-h)*(x-x0+3*h)*(x-x0-h)*(x-x0-3*h) / (16*h*h*h) -
                func(x0+h)*(x-x0+3*h)*(x-x0+h)*(x-x0-3*h) / (16*h*h*h) +
                func(x0+3*h)*(x-x0+3*h)*(x-x0+h)*(x-x0-h) / (48*h*h*h);
    }

    private static double rh2(double x, double x0, double h) {
        return func(x) - polynomial2(x, x0, h);
    }
    private static double rh3(double x, double x0, double h) {
        return func(x) - polynomial3(x, x0, h);
    }
    public static double error(double x, double x0, double h) {
        return rh3(x, x0, h);
    }

    private static double func(double x) {
        return Math.exp(x);
    }

    public static double m(ArrayList<Double> errors) {
        double errAbsRelation = Math.abs( errors.get(errors.size()-2) / errors.get(errors.size()-1) );

        return Math.log(errAbsRelation) / Math.log(2); // Переход к новому основанию логарифма
    }
    public static double n(ArrayList<Double> errors) {
        double lastErrDifference = Math.abs(errors.get(errors.size()-3) - errors.get(errors.size()-2));
        double errDifference = Math.abs(errors.get(errors.size()-2) - errors.get(errors.size()-1));

        return Math.log(lastErrDifference / errDifference) / Math.log(2);
    }

    private Lagrange() {}
}
