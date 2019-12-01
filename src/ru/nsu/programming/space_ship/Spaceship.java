package ru.nsu.programming.space_ship;

import ru.nsu.programming.space_ship.body.Body;
import ru.nsu.programming.space_ship.engine.AbstractEngine;
import ru.nsu.programming.space_ship.engine.Engine;
import ru.nsu.programming.space_ship.fuel_tank.AbstractFuelTank;
import ru.nsu.programming.space_ship.fuel_tank.FuelTank;

public class Spaceship {
    private Body body;
    private AbstractEngine engine;
    private AbstractFuelTank fuelTank;
    private boolean isLanded = true;

    public Spaceship(Body body, AbstractEngine engine, AbstractFuelTank fuelTank) {
        this.engine = engine;
        this.body = body;
        this.fuelTank = fuelTank;
    }

    public void refuel() {
        if (isLanded) {
            System.out.println("Your ship was refueled\n");
            fuelTank.refuel();
        } else {
            System.out.println("You should land first\n");
        }
    }

    public void takeOff() {
        if (fuelTank.getFuel() >= 100 && isLanded) {
            System.out.println("The ship took off\n");
            isLanded = false;
            fuelTank.useFuel(100);
        } else if (!isLanded) {
            System.out.println("You've already taken off\n");
        } else {
            System.out.println("Not enough fuel\n");
        }
    }

    public void land() {
        if (isLanded) {
            System.out.println("You already landed\n");
        } else {
            isLanded = true;
            System.out.println("You landed\n");
        }
    }

    public void hyperJump(int n) {
        if (n > engine.getHyperJumpDistance()) {
            System.out.println("You can't jump on such a big distance\n");
        } else if (fuelTank.getFuel() < n) {
            System.out.println("You don't have enough fuel\n");
        } else if (isLanded) {
            System.out.println("You should take off first\n");
        } else {
            System.out.println("You've just jumped on distance " + n + '\n');
            fuelTank.useFuel(n);
        }
    }

    public void repaint(Color color) {
        engine = engine.clone();
        fuelTank = fuelTank.clone();
        body = body.repaint(color);
        System.out.println("Your ship was repainted\n");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Your ship's weight is ").append(body.getWeight() + engine.getWeight() + fuelTank.getWeight())
                                                                                                   .append('\n')
          .append("Max speed is ").append(engine.getSpeed()).append('\n')
          .append("You can jump on distance ").append(engine.getHyperJumpDistance()).append('\n')
          .append('©').append(' ').append("Your ship's engine was made by ").append(engine.getBrand()).append('\n')
          .append("Capacity of your tank is ").append(fuelTank.getCapacity()).append('\n')
          .append("Right now your fuel is ").append(fuelTank.getFuel()).append('\n')
          .append('©').append(' ').append("Your ship's fuel tank was made by ").append(fuelTank.getBrand()).append('\n')
          .append("Max weight which you can load on your body is ").append(body.getMaxWeight()).append('\n')
          .append("Color of your ship is ").append(body.getColor()).append('\n')
          .append('©').append(' ').append("Your ship's body was made by ").append(body.getBrand()).append('\n');

        return sb.toString();
    }
}
