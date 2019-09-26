package com.NSU.CW;

public class Lagrange {

    public static double polynomial2(double x, double x0, double h) {
        return func(x0-h)*(x-x0-h)*(x-x0-3*h) / (8*h*h) -
               func(x0+h)*(x-x0+h)*(x-x0-3*h) / (4*h*h) +
               func(x0+3*h)*(x-x0+h)*(x-x0-h) / (8*h*h);
    }

    public static double polynomial3(double x, double x0, double h) {
        return -func(x0-3*h)*(x-x0+h)*(x-x0-h)*(x-x0-3*h) / 48*h*h*h +
                func(x0-h)*(x-x0+3*h)*(x-x0-h)*(x-x0-3*h) / 16*h*h*h -
                func(x0+h)*(x-x0+3*h)*(x-x0+h)*(x-x0-3*h) / 16*h*h*h +
                func(x0+3*h)*(x-x0+3*h)*(x-x0+h)*(x-x0-h) / 48*h*h*h;
    }

    public static double rh2(double x, double x0, double h) {
        return func(x) - polynomial2(x, x0, h);
    }

    public static double rh3(double x, double x0, double h) {
        return func(x) - polynomial3(x, x0, h);
    }

    private static double func(double x) {
        return Math.exp(x);
    }

    private Lagrange() {}
}
