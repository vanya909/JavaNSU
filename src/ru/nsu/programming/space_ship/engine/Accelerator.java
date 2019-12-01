package ru.nsu.programming.space_ship.engine;

import ru.nsu.programming.space_ship.Brand;

public class Accelerator extends AbstractEngine {
    private AbstractEngine engine;
    private Brand brand;

    Accelerator(Brand brand, AbstractEngine engine) {
        this.engine = engine;
        this.brand = brand;
    }

    @Override
    public int getWeight() {
        return engine.getWeight() + (int) (400 * brand.COEFFICIENT);
    }

    @Override
    public int getThrottle() {
        return engine.getThrottle();
    }

    @Override
    public int getSpeed() {
        return engine.getSpeed() + (int) (2000 * brand.COEFFICIENT);
    }

    @Override
    public int getHyperJumpDistance() {
        return engine.getHyperJumpDistance();
    }

    @Override
    public int getCost() {
        return engine.getCost() + (int) (8000 * brand.COEFFICIENT);
    }

    @Override
    public int getDurability() {
        return engine.getDurability();
    }

    @Override
    public Brand getBrand() {
        return engine.getBrand();
    }

    @Override
    public AbstractEngine clone() {
        return new Accelerator(brand, engine.clone());
    }
}
