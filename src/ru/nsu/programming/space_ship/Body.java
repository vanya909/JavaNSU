package ru.nsu.programming.space_ship;

public class Body {
    private final int weight;
    private final int maxWeight;
    private final int maxEngineWeight;
    private final int maxFuelTankWeight;
    private final int cost;
    private final Color color;
    private final Brand brand;

    private Body(int weight, int maxWeight,
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


    public static Body getBody(int weight, int maxWeight,
                               int maxEngineWeight, int maxFuelTankWeight,
                               int cost, Color color, Brand brand) {
        if (weight > 0 && maxWeight > weight && maxEngineWeight > 0 &&
            maxFuelTankWeight > 0 && maxFuelTankWeight + maxEngineWeight < maxWeight &&
            cost >= 0 && color != null && brand != null) {
            return new Body(weight, maxWeight, maxEngineWeight, maxFuelTankWeight, cost, color, brand);
        } else {
            return null;
        }
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
          .append("This body made by ").append(brand).append('©').append('\n');

        return sb.toString();
    }

    public Body clone() {
        return new Body(weight, maxWeight, maxEngineWeight, maxFuelTankWeight, cost, color, brand);
    }
}
