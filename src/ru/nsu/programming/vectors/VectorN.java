package ru.nsu.programming.vectors;

import java.util.ArrayList;
import java.util.Arrays;

public class VectorN {
    private byte dimension;
    private double[] coordinates;

    // Сложение
    // Вычитание
    // Скалярное произведение
    // Векторное произведение
    // Умножение на скаляр
    public VectorN add(VectorN v) throws IllegalArgumentException {
        if (dimension != v.getDimension())
            throw new IllegalArgumentException("Different dimensions!");

        double[] newCoordinates = new double[dimension];
        for (int i = 0; i < dimension; i++)
            newCoordinates[i] = coordinates[i] + v.get(i);

        return new VectorN(newCoordinates);
    }

    public VectorN subtract(VectorN v) throws IllegalArgumentException {
        if (dimension != v.getDimension())
            throw new IllegalArgumentException("Different dimensions!");

        double[] newCoordinates = new double[dimension];
        for (int i = 0; i < dimension; i++)
            newCoordinates[i] = coordinates[i] - v.get(i);

        return new VectorN(newCoordinates);
    }

    public double scalarProduct(VectorN v) throws IllegalArgumentException {
        if (dimension != v.getDimension())
            throw new IllegalArgumentException("Different dimensions!");

        double scalarProduct = 0;

        for (int i = 0; i < dimension; i++)
            scalarProduct += coordinates[i] * v.get(i);

        return scalarProduct;
    }

    public VectorN vectorProduct(VectorN v) throws UnsupportedOperationException {
        if (this.getDimension() > 3 || v.getDimension() > 3) {
            throw new UnsupportedOperationException("dimension > 3");
        }
        if (this.getDimension() < 2 || v.getDimension() < 2) {
            throw new UnsupportedOperationException("dimension < 2");
        }

        VectorN u = (this.getDimension() == 3) ? (this) : (new VectorN(this.get(0), this.get(1), 0));

        v = (v.getDimension() == 3) ? (v) : (new VectorN(v.get(0), v.get(1), 0));

        double x = u.get(1) * v.get(2) - u.get(2) * v.get(1);
        double y = u.get(2) * v.get(0) - u.get(0) * v.get(2);
        double z = u.get(0) * v.get(1) - u.get(1) * v.get(0);

        return new VectorN(x, y, z);
    }

    public VectorN increase(double n) {
        double[] newCoordinates = Arrays.copyOf(coordinates, dimension);
        for (int i = 0; i < dimension; i++)
            newCoordinates[i] *= n;
        return new VectorN(newCoordinates);
    }

    // Модуль вектора
    // Проекция на вектор
    // Проекция на ортогональную вектору плоскость
    // Ортогонализация
    public double abs() {
        double result = 0;
        for (int i = 0; i < this.getDimension(); i++)
            result += this.get(i) * this.get(i);

        result = Math.round(result * 10000) / 10000.0; // Округление до 4 знаков
        return Math.sqrt(result);
    }

    public VectorN vectorProjection(VectorN v) {
        return v.increase(this.scalarProduct(v) / (v.abs() * v.abs()));
    }

    public VectorN orthogonalSubspaceProjection(VectorN v) {
        return this.subtract(this.vectorProjection(v));
    }

    public static ArrayList<VectorN> orthogonalization(ArrayList<VectorN> originalVectors) {
        ArrayList<VectorN> orthogonalVectors = new ArrayList<>();

        for (VectorN origVector : originalVectors) {
            VectorN resultVector = origVector;
            for (VectorN newVector : orthogonalVectors) {
                resultVector = resultVector.subtract(resultVector.vectorProjection(newVector));
            }
            orthogonalVectors.add(resultVector);
        }

        return orthogonalVectors;
    }

    // Нулевой вектор
    // Случайный единичный вектор
    public static VectorN zeroVector(byte dimension) {
        double[] coordinates = new double[dimension];
        return new VectorN(coordinates);
    }

    public static VectorN randomUnitVector() {
        double cosA = 2 * Math.random() - 1;
        double cosB = 2 * Math.random() - 1;

        double x = Math.sqrt(1 - cosA * cosA) * cosB;
        double y = Math.sqrt(1 - cosA * cosA) * Math.sqrt(1 - cosB * cosB);
        double z = cosA;

        return new VectorN(x, y, z);
    }

    public byte getDimension() {
        return dimension;
    }

    public Double get(int i) {
        try {
            return coordinates[i];
        } catch (IndexOutOfBoundsException OBE) {
            return null;
        }
    }

    public double[] getCoordinates() {
        return coordinates;
    }

    public void set(int i, double n) {
        try {
            coordinates[i] = n;
        } catch (IndexOutOfBoundsException ignored) {
        }
    }

    public VectorN(double...x) {
        dimension = (byte) x.length;
        coordinates = x;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append('(');
        for (int i = 0; i < dimension; i++) {
            if (i != dimension - 1) {
                sb.append(coordinates[i]).append(", ");
            }
            else {
                sb.append(coordinates[i]);
            }
        }
        sb.append(')');

        return sb.toString();
    }

    @Override
    public VectorN clone() {
        return new VectorN(this.getCoordinates());
    }
}
