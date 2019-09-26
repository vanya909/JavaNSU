package com.NSU.Programming;

import java.util.Arrays;
import java.util.Random;

public class VectorN {
    private byte dimension;
    private double[] coordinates;

    // Сложение
    // Скалярное произведение
    // Умножение на скаляр
    public VectorN addition(VectorN v) throws IllegalArgumentException {
        if (dimension != v.getDimension())
            throw new IllegalArgumentException("Different dimensions!");

        double[] newCoordinates = new double[dimension];
        for (int i = 0; i < dimension; i++)
            newCoordinates[i] = coordinates[i] + v.getI(i);

        return new VectorN(newCoordinates);
    }
    public double scalarProduct(VectorN v) throws IllegalArgumentException {
        if (dimension != v.getDimension())
            throw new IllegalArgumentException("Different dimensions!");

        double scalarProduct = 0;

        for (int i = 0; i < dimension; i++)
            scalarProduct += coordinates[i] * v.getI(i);

        return scalarProduct;
    }
    public VectorN increase(double n) {
        double[] newCoordinates = Arrays.copyOf(coordinates, dimension);
        for (int i = 0; i < dimension; i++)
            newCoordinates[i] *= n;
        return new VectorN(newCoordinates);
    }



    // Модуль вектора
    // Проекция на вектор
    public static double vectorAbs(VectorN v) {
        double result = 0;
        for (int i = 0; i < v.getDimension(); i++)
            result += v.getI(i) * v.getI(i);

        return Math.sqrt(result);
    }
    public double vectorProjection(VectorN v) {
        return this.scalarProduct(v) / vectorAbs(v);
    }


    // Копия вектора
    // Нулевой вектор
    // Случайный единичный вектор
    public static VectorN vectorCopy(VectorN v) {
        return new VectorN(v.getCoordinates());
    }
    public static VectorN zeroVector(byte dimension) {
        double[] coordinates = new double[dimension];
        return new VectorN(coordinates);
    }
    public static VectorN randomUnitVector() {
        Random random = new Random();
        double first = random.nextGaussian();
        double second = random.nextGaussian();
        double third = random.nextGaussian();
        double abs = Math.sqrt(first*first + second*second + third*third);

        return new VectorN(first/abs, second/abs, third/abs);
    }



    public byte getDimension() {
        return dimension;
    }
    public Double getI(int i) {
        try {
            return coordinates[i];
        } catch (IndexOutOfBoundsException OBE) {
            return null;
        }
    }
    public double[] getCoordinates() {
        return coordinates;
    }

    public void setI(int i, double n) {
        try {
            coordinates[i] = n;
        } catch (IndexOutOfBoundsException OBE) {
        }

    }

    public VectorN(double...x) {
        dimension = (byte) x.length;
        coordinates = new double[dimension];
        System.arraycopy(x, 0, coordinates, 0, dimension);
    }

    public static void main(String[] args) {
        System.out.println(new VectorN(1,2,3).getI(5));
    }

    @Override
    public String toString() {
        String result = "";

        for (int i = 0; i < dimension; i++) {
            if (i != dimension - 1)
                result += coordinates[i] + ", ";
            else result += coordinates[i];
        }

        return "(" + result + ")";
    }
}
