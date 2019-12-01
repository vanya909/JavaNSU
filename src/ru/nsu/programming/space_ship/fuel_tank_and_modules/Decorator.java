package ru.nsu.programming.space_ship.fuel_tank_and_modules;

import ru.nsu.programming.space_ship.Brand;

public abstract class Decorator extends AbstractFuelTank {

    @Override
    public abstract int getWeight();

    @Override
    public abstract int getCapacity();

    @Override
    public abstract int getCost();

    @Override
    public abstract int getDurability();

    @Override
    public abstract int getFuel();

    @Override
    public abstract Brand getBrand();
}
