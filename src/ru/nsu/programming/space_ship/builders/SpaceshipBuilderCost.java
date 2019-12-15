package ru.nsu.programming.space_ship.builders;
import ru.nsu.programming.space_ship.*;
import ru.nsu.programming.space_ship.engine.Engine;

public class SpaceshipBuilderCost extends AbstractSpaceShipBuilder {

    @Override
    public Error status() {
        if (body == null) {
            return Error.NO_BODY;
        } else if (engine == null) {
            return Error.NO_ENGINE;
        } else if (fuelTank == null) {
            return Error.NO_FUEL_TANK;
        } else if (engine.getThrottle() <= body.getWeight() + engine.getWeight() + fuelTank.getWeight()) {
            return Error.NOT_ENOUGH_THROTTLE;
        } else {
            return super.status();
        }
    }

    private void printCost() {
        System.out.println("The cost is - " + (engine.getCost() + body.getCost() + fuelTank.getCost()));
    }

    @Override
    public Spaceship getSpaceship() {
        if (status() == Error.NO_ERROR) {
            printCost();
            return new Spaceship(body.clone(), engine.clone(), fuelTank.clone());
        } else {
            throw new IllegalStateException(status().MESSAGE);
        }
    }
}
