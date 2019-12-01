package ru.nsu.programming.space_ship;

import ru.nsu.programming.space_ship.fuel_tank_and_modules.FuelTank;

public class Spaceship {
    Engine engine;
    Body body;
    FuelTank fuelTank;

    public Spaceship(Engine engine, Body body, FuelTank fuelTank) {
        this.engine = engine;
        this.body = body;
        this.fuelTank = fuelTank;
    }
}
