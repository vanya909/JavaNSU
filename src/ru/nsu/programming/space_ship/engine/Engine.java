package ru.nsu.programming.space_ship.engine;

import ru.nsu.programming.space_ship.Brand;

public class Engine extends AbstractEngine {
    private final int weight;
    private final int throttle;
    private final int speed;
    private final int hyperJumpDistance;
    private final int cost;
    private final int durability;
    private final Brand brand;

    Engine(int weight, int throttle, int speed, int hyperJumpDistance, int cost, int durability, Brand brand) {
        this.weight = weight;
        this.throttle = throttle;
        this.speed = speed;
        this.hyperJumpDistance = hyperJumpDistance;
        this.cost = cost;
        this.durability = durability;
        this.brand = brand;
    }

    public int getWeight() {
        return weight;
    }

    public int getThrottle() {
        return throttle;
    }

    public int getSpeed() {
        return speed;
    }

    public int getHyperJumpDistance() {
        return hyperJumpDistance;
    }

    public int getCost() {
        return cost;
    }

    public int getDurability() {
        return durability;
    }

    public Brand getBrand() {
        return brand;
    }


    public Engine clone() {
        return new Engine(weight, throttle, speed, hyperJumpDistance, cost, durability, brand);
    }
}
