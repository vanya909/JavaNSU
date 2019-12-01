package ru.nsu.programming.space_ship.fuel_tank;

import ru.nsu.programming.space_ship.Brand;

public class FuelTankFactory {

    public static FuelTank getFuelTank(int weight, int capacity, int cost, int durability, Brand brand) {
        if (weight > 0 && capacity > 0 && cost >= 0 && durability > 0 && brand != null) {
            return new FuelTank(weight, capacity, cost, durability, brand);
        } else {
            printStatusFuelTank(weight, capacity, cost, durability, brand);
            return null;
        }
    }

    public static MiniFuelTank getMiniFuelTank(Brand brand, AbstractFuelTank fuelTank) {
        if (brand != null && fuelTank != null) {
            return new MiniFuelTank(brand, fuelTank);
        } else {
            printStatusMiniFuelTank(brand, fuelTank);
            return null;
        }
    }

    public static Armor getArmor(Brand brand, AbstractFuelTank fuelTank) {
        if (brand != null && fuelTank != null) {
            return new Armor(brand, fuelTank);
        } else {
            printStatusArmor(brand, fuelTank);
            return null;
        }
    }

    private static void printStatusFuelTank(int weight, int capacity, int cost, int durability, Brand brand) {
        StringBuilder errors = new StringBuilder();

        if (weight <= 0) {
            errors.append("Fuel Tank: ").append("Weight must be positive").append('\n');
        }
        if (capacity <= 0) {
            errors.append("Fuel Tank: ").append("Capacity must be positive").append('\n');
        }
        if (cost < 0) {
            errors.append("Fuel Tank: ").append("Cost can't be negative").append('\n');
        }
        if (durability <= 0) {
            errors.append("Fuel Tank: ").append("Durability must be positive").append('\n');
        }
        if (brand == null) {
            errors.append("Fuel Tank: ").append("Brand can't be null").append('\n');
        }

        System.out.println(errors);
        System.out.println("Fuel Tank wasn't made");
    }

    private static void printStatusMiniFuelTank(Brand brand, AbstractFuelTank fuelTank) {
        StringBuilder errors = new StringBuilder();

        if (brand == null) {
            errors.append("Mini Fuel Tank: ").append("Brand can't be null").append('\n');
        }
        if (fuelTank == null) {
            errors.append("Mini Fuel Tank: ").append("Fuel Tank can't be null").append('\n');
        }

        System.out.println(errors);
        System.out.println("Mini Fuel Tank wasn't made");
    }

    private static void printStatusArmor(Brand brand, AbstractFuelTank fuelTank) {
        StringBuilder errors = new StringBuilder();

        if (brand == null) {
            errors.append("Armor: ").append("Brand can't be null").append('\n');
        }
        if (fuelTank == null) {
            errors.append("Armor: ").append("Fuel Tank can't be null").append('\n');
        }

        System.out.println(errors);
        System.out.println("Armor wasn't made");
    }
}
