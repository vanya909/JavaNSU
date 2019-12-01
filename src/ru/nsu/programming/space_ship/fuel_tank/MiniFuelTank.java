package ru.nsu.programming.space_ship.fuel_tank;

import ru.nsu.programming.space_ship.Brand;

public class MiniFuelTank extends AbstractFuelTank {
    private AbstractFuelTank fuelTank;
    private Brand brand;
    private int fuel;


    MiniFuelTank(Brand brand, AbstractFuelTank fuelTank) {
        this.fuelTank = fuelTank;
        this.brand = brand;
        this.fuel = fuelTank.getFuel();
    }


    @Override
    protected void setFuel(int n) {
        fuel = n;
    }

    @Override
    public int getModulesCount() {
        return fuelTank.getModulesCount() + 1;
    }

    @Override
    public int getCapacity() {
        return fuelTank.getCapacity() + (int) (1250 * brand.COEFFICIENT);
    }

    @Override
    public int getDurability() {
        return fuelTank.getDurability();
    }

    @Override
    public int getFuel() {
        return fuel;
    }

    @Override
    public Brand getBrand() {
        return fuelTank.getBrand();
    }

    @Override
    public int getWeight() {
        return fuelTank.getWeight() + (int) (1000 * brand.COEFFICIENT);
    }

    @Override
    public int getCost() {
        return fuelTank.getCost() + (int) (8000 * brand.COEFFICIENT);
    }

    @Override
    public AbstractFuelTank clone() {
        return new MiniFuelTank(brand, fuelTank.clone());
    }
}
