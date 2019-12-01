package ru.nsu.programming.space_ship.fuel_tank;

import ru.nsu.programming.space_ship.Brand;

public class FuelTank extends AbstractFuelTank {

    private final int weight;
    private final int capacity;
    private final int cost;
    private final int durability;
    private int fuel;
    private final Brand brand;

    FuelTank(int weight, int capacity, int cost, int durability, Brand brand) {
        this.weight = weight;
        this.capacity = capacity;
        this.cost = cost;
        this.durability = durability;
        this.fuel = 0;
        this.brand = brand;
    }

    @Override
    protected void setFuel(int n) {
        fuel = n;
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
        FuelTank tmp = new FuelTank(weight, capacity, cost, durability, brand);
        tmp.setFuel(this.getFuel());

        return tmp;
    }
}
