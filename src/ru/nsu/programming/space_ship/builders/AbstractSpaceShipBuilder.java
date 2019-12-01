package ru.nsu.programming.space_ship.builders;
import ru.nsu.programming.space_ship.*;
import ru.nsu.programming.space_ship.Body;
import ru.nsu.programming.space_ship.fuel_tank_and_modules.FuelTank;

public abstract class AbstractSpaceShipBuilder {
    Engine engine;
    FuelTank fuelTank;
    Body body;

    public AbstractSpaceShipBuilder setEngine(Engine engine) {
        this.engine = engine;
        return this;
    }
    public AbstractSpaceShipBuilder setFuelTank(FuelTank fuelTank) {
        this.fuelTank = fuelTank;
        return this;
    }
    public AbstractSpaceShipBuilder setBody(Body body) {
        this.body = body;
        return this;
    }



    public Error status() {
        if (body == null) {
            return Error.NO_BODY;

        } else if (engine == null) {
            return Error.NO_ENGINE;

        } else if (fuelTank == null) {
            return Error.NO_FUEL_TANK;

        } else if (body.getMaxEngineWeight() >= engine.getWeight()) {
            return Error.ENGINE_CANT_BE_INSTALLED;

        } else if (body.getMaxFuelTankWeight() >= fuelTank.getWeight()) {
            return Error.FUEL_TANK_CANT_BE_INSTALLED;

        } else if (body.getMaxWeight() < body.getWeight() + engine.getWeight() + fuelTank.getWeight()) {
            return Error.BODY_IS_OVERLOADED;

        } else {
            return Error.NO_ERROR;
        }
    }



    public boolean canFly() {
        if (status() != Error.NO_ERROR) {
            return false;
        }
        int generalWeight = body.getWeight() + engine.getWeight() + fuelTank.getWeight();
        return engine.getThrottle() > generalWeight;
    }


    public abstract Spaceship getSpaceship();


    protected enum Error {
        NO_ERROR("It's okay"),
        NO_BODY("Body is missing"),
        NO_ENGINE("Engine is missing"),
        NO_FUEL_TANK("Fuel tank is missing"),
        ENGINE_CANT_BE_INSTALLED("Engine can't be installed"),
        FUEL_TANK_CANT_BE_INSTALLED("Fuel tank can't be installed"),
        BODY_IS_OVERLOADED("Body is overloaded"),
        NOT_ENOUGH_THROTTLE("Not enough throttle");
        public final String MESSAGE;

        Error(String message) {
            this.MESSAGE = message;
        }
    }
}
