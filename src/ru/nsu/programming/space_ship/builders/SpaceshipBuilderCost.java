package ru.nsu.programming.space_ship.builders;
import ru.nsu.programming.space_ship.*;

public class SpaceshipBuilderCost extends AbstractSpaceShipBuilder {

    @Override
    public Error status() {
        if (engine.getThrottle() > body.getWeight() + engine.getWeight() + fuelTank.getWeight()) {
            return super.status();
        } else {
            return Error.NOT_ENOUGH_THROTTLE;
        }
    }

    public void printCost() {
        System.out.println("The cost is - " + engine.getCost() + body.getCost() + fuelTank.getCost());
    }

    @Override
    public Spaceship getSpaceship() {
        if (status() == Error.NO_ERROR) {
            printCost();
            try {
                return new Spaceship(engine, body, fuelTank);
            } finally {
                engine = engine.clone();
                body = body.clone();
                fuelTank = fuelTank.clone();
            }
        } else {
            throw new IllegalStateException(status().MESSAGE);
        }
    }
}
