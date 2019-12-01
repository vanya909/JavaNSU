package ru.nsu.programming.space_ship.engine;

import ru.nsu.programming.space_ship.Brand;

public class Armor extends AbstractEngine {
    private AbstractEngine engine;
    private Brand brand;

    Armor(Brand brand, AbstractEngine engine) {
        this.engine = engine;
        this.brand = brand;
    }

    @Override
    public int getWeight() {
        return engine.getWeight() + (int) (800 * brand.COEFFICIENT);
    }

    @Override
    public int getThrottle() {
        return engine.getThrottle();
    }

    @Override
    public int getSpeed() {
        return engine.getSpeed();
    }

    @Override
    public int getHyperJumpDistance() {
        return engine.getHyperJumpDistance();
    }

    @Override
    public int getCost() {
        return engine.getCost() + (int) (16_000 * brand.COEFFICIENT);
    }

    @Override
    public int getDurability() {
        return engine.getDurability() + (int) (3000 * brand.COEFFICIENT);
    }

    @Override
    public Brand getBrand() {
        return engine.getBrand();
    }

    @Override
    public AbstractEngine clone() {
        return new Armor(brand, engine.clone());
    }
}
