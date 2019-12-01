package ru.nsu.programming.space_ship.fuel_tank_and_modules;

import ru.nsu.programming.space_ship.Brand;

public class FuelTank extends AbstractFuelTank {

    private int weight;
    private int capacity;
    private int cost;
    private int durability;
    private int fuel;
    private Brand brand;

    public FuelTank(int weight, int capacity, int cost, int durability, int fuel, Brand brand) {
        this.weight = weight;
        this.capacity = capacity;
        this.cost = cost;
        this.durability = durability;
        this.fuel = fuel;
        this.brand = brand;
    }


    public void refuel(int n) {

    }


    public int getModulesCount() {
        return 1;
    }

    public int getWeight() {
        return weight;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getCost() {
        return cost;
    }

    public int getDurability() {
        return durability;
    }

    public int getFuel() {
        return fuel;
    }

    public Brand getBrand() {
        return brand;
    }

    public FuelTank clone() {
        return new FuelTank(weight, capacity, cost, durability, fuel, brand);
    }
}
