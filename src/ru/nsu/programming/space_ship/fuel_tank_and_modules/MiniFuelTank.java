package ru.nsu.programming.space_ship.fuel_tank_and_modules;

import ru.nsu.programming.space_ship.Brand;

public class MiniFuelTank extends Decorator {
    AbstractFuelTank fuelTank;
    private Brand brand;

    public int fuel;


    public MiniFuelTank(Brand brand, AbstractFuelTank fuelTank) {
        this.fuelTank = fuelTank;
        this.brand = brand;
    }

    @Override
    public int getModulesCount() {
        return fuelTank.getModulesCount() + 1;
    }

    @Override
    public int getCapacity() {
        return fuelTank.getCapacity() + (int) (200 * brand.COEFFICIENT);
    }

    @Override
    public int getDurability() {
        return fuelTank.getDurability();
    }

    @Override
    public int getFuel() {
        return fuelTank.getFuel();
    }

    @Override
    public Brand getBrand() {
        return fuelTank.getBrand();
    }

    @Override
    public int getWeight() {
        return fuelTank.getWeight() + (int) (50 * brand.COEFFICIENT);
    }

    @Override
    public int getCost() {
        return fuelTank.getCost() + (int) (400 * brand.COEFFICIENT);
    }

    @Override
    public AbstractFuelTank clone() {
        return new MiniFuelTank(brand, fuelTank.clone());
    }
}
