package ru.nsu.programming.space_ship.body;

import ru.nsu.programming.space_ship.Brand;
import ru.nsu.programming.space_ship.Color;

public class Body {
    private final int weight;
    private final int maxWeight;
    private final int maxEngineWeight;
    private final int maxFuelTankWeight;
    private final int cost;
    private final Color color;
    private final Brand brand;

    Body(int weight, int maxWeight,
                 int maxEngineWeight, int maxFuelTankWeight,
                 int cost, Color color, Brand brand) {
        this.weight = weight;
        this.maxWeight = maxWeight;
        this.maxEngineWeight = maxEngineWeight;
        this.maxFuelTankWeight = maxFuelTankWeight;
        this.cost = cost;
        this.color = color;
        this.brand = brand;
    }

    public int getWeight() {
        return weight;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public int getMaxEngineWeight() {
        return maxEngineWeight;
    }

    public int getMaxFuelTankWeight() {
        return maxFuelTankWeight;
    }

    public int getCost() {
        return cost;
    }

    public Color getColor() {
        return color;
    }

    public Brand getBrand() {
        return brand;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Weight is - ").append(weight).append('\n')
          .append("Max weight is something about ").append(maxWeight).append('\n')
          .append("Max size for the engine you able to install is - ").append(maxEngineWeight).append('\n')
          .append("For fuel tank this size is - ").append(maxFuelTankWeight).append('\n')
          .append("It will cost you ").append(cost).append('\n')
          .append("The color is ").append(color).append('\n')
          .append('©').append(' ').append("This body was made by ").append(brand).append('\n');

        return sb.toString();
    }

    public Body repaint(Color color) {
        return new Body(weight, maxWeight, maxEngineWeight, maxFuelTankWeight, cost, color, brand);
    }
    public Body clone() {
        return new Body(weight, maxWeight, maxEngineWeight, maxFuelTankWeight, cost, color, brand);
    }
}
