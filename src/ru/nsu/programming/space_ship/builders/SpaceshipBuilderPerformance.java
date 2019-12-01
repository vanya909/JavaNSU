package ru.nsu.programming.space_ship.builders;
import ru.nsu.programming.space_ship.*;

public class SpaceshipBuilderPerformance extends AbstractSpaceShipBuilder {

    public void printFlightPerformance() {
        if (canFly()) {
            System.out.println("Throttle is - " + engine.getThrottle());
            System.out.println("Max speed is something about " + engine.getSpeed());
            System.out.println("You can fly on distance " + engine.getHyperJumpDistance() + " using hyper jump");
        } else {
            System.out.println("It can not fly!");
        }
    }

    @Override
    public Spaceship getSpaceship() {
        if (status() == Error.NO_ERROR) {
            printFlightPerformance();
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
