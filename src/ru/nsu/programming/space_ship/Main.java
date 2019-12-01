package ru.nsu.programming.space_ship;

import ru.nsu.programming.space_ship.fuel_tank_and_modules.*;

public class Main {
    public static void main(String[] args) {
        AbstractFuelTank fuelTank = new FuelTank(1, 1, 1, 1, 1, Brand.BMW);
        fuelTank = new MiniFuelTank(Brand.BMW, fuelTank);
        fuelTank = new MiniFuelTank(Brand.PORSCHE, fuelTank);

        System.out.println(fuelTank);
    }
}
