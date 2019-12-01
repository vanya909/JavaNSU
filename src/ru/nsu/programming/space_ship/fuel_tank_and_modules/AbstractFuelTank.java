package ru.nsu.programming.space_ship.fuel_tank_and_modules;

import ru.nsu.programming.space_ship.Brand;

public abstract class AbstractFuelTank {


    public abstract int getModulesCount();

    public abstract int getWeight();

    public abstract int getCapacity();

    public abstract int getCost();

    public abstract int getDurability();

    public abstract int getFuel();

    public abstract Brand getBrand();

    public abstract AbstractFuelTank clone();

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Weight is - ").append(getWeight()).append('\n')
          .append("Capacity is something about ").append(getCapacity()).append('\n')
          .append("Max durability is - ").append(getDurability()).append('\n')
          .append("Fuel right now is - ").append(getFuel()).append('\n')
          .append("It will cost you ").append(getCost()).append('\n')
          .append("This fuel tank made by ").append(getBrand()).append(' ').append('©').append('\n');

        return sb.toString();
    }
}
