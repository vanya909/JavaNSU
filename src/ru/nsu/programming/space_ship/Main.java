package ru.nsu.programming.space_ship;

import ru.nsu.programming.space_ship.body.Body;
import ru.nsu.programming.space_ship.body.BodyFactory;
import ru.nsu.programming.space_ship.builders.SpaceshipBuilderCost;
import ru.nsu.programming.space_ship.engine.AbstractEngine;
import ru.nsu.programming.space_ship.engine.EngineFactory;
import ru.nsu.programming.space_ship.fuel_tank.AbstractFuelTank;
import ru.nsu.programming.space_ship.fuel_tank.FuelTankFactory;

public class Main {
    public static void main(String[] args) {
        Body body = BodyFactory.getBody(10_000, 100_000, 10_000,
                              10_000, 100_000, Color.BLACK, Brand.BMW);

        AbstractEngine engine = EngineFactory.getEngine(4000, 30_000, 10_000,
                                              100, 40_000, 5000, Brand.BMW);
        engine = EngineFactory.getAccelerator(Brand.BMW, engine);
        engine = EngineFactory.getArmor(Brand.PORSCHE, engine);

        AbstractFuelTank fuelTank = FuelTankFactory.getFuelTank(4000, 5000, 30_000,
                                                              5000, Brand.BMW);
        fuelTank = FuelTankFactory.getMiniFuelTank(Brand.BMW, fuelTank);
        fuelTank = FuelTankFactory.getArmor(Brand.VOLKSWAGEN, fuelTank);

        System.out.println(body);
        System.out.println(engine);
        System.out.println(fuelTank);
        System.out.println("---------------------");

        SpaceshipBuilderCost sbc = new SpaceshipBuilderCost();
        sbc.setBody(body);
        sbc.setEngine(engine);
        sbc.setFuelTank(fuelTank);
        Spaceship spaceship = sbc.getSpaceship();

        System.out.println(spaceship);
        spaceship.hyperJump(100);
        System.out.println("---------------------");

        spaceship.refuel();
        spaceship.takeOff();
        System.out.println(spaceship);
        spaceship.hyperJump(150);
        System.out.println("---------------------");

        spaceship.hyperJump(100);
        System.out.println(spaceship);
        spaceship.refuel();
        System.out.println("---------------------");

        spaceship.land();
    }
}
