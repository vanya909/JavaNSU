package ru.nsu.programming.space_ship.engine;

import ru.nsu.programming.space_ship.Brand;

public abstract class AbstractEngine {

    public abstract int getWeight();

    public abstract int getThrottle();

    public abstract int getSpeed();

    public abstract int getHyperJumpDistance();

    public abstract int getCost();

    public abstract int getDurability();

    public abstract Brand getBrand();

    public abstract AbstractEngine clone();

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Weight is - ").append(getWeight()).append('\n')
          .append("Throttle is something about ").append(getThrottle()).append('\n')
          .append("Max durability is - ").append(getDurability()).append('\n')
          .append("Speed is - ").append(getSpeed()).append('\n')
          .append("Hyper jump distance about ").append(getHyperJumpDistance()).append('\n')
          .append("It will cost you ").append(getCost()).append('\n')
          .append('©').append(' ').append("This engine was made by ").append(getBrand()).append('\n');

        return sb.toString();
    }
}
