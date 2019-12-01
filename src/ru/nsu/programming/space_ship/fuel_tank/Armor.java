package ru.nsu.programming.space_ship.fuel_tank;

import ru.nsu.programming.space_ship.Brand;

public class Armor extends AbstractFuelTank {
    private AbstractFuelTank fuelTank;
    private Brand brand;


    Armor(Brand brand, AbstractFuelTank fuelTank) {
        this.fuelTank = fuelTank;
        this.brand = brand;
    }


    @Override
    protected void setFuel(int n) {
        fuelTank.setFuel(n);
    }

    @Override
    public int getModulesCount() {
        return fuelTank.getModulesCount() + 1;
    }

    @Override
    public int getCapacity() {
        return fuelTank.getCapacity();
    }

    @Override
    public int getDurability() {
        return fuelTank.getDurability() + (int) (3000 * brand.COEFFICIENT);
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
        return fuelTank.getWeight() + (int) (1000 * brand.COEFFICIENT);
    }

    @Override
    public int getCost() {
        return fuelTank.getCost() + (int) (18_000 * brand.COEFFICIENT);
    }

    @Override
    public AbstractFuelTank clone() {
        return new Armor(brand, fuelTank.clone());
    }
}
