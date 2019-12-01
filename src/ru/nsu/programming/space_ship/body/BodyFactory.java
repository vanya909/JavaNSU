package ru.nsu.programming.space_ship.body;

import ru.nsu.programming.space_ship.Brand;
import ru.nsu.programming.space_ship.Color;

public class BodyFactory {
    public static Body getBody(int weight, int maxWeight, int maxEngineWeight,
                               int maxFuelTankWeight, int cost, Color color, Brand brand) {


        if (maxWeight >= maxEngineWeight + maxFuelTankWeight + weight &&
            maxEngineWeight > 0 && maxFuelTankWeight > 0 &&
            weight > 0 && cost >= 0 && color != null && brand != null) {

            return new Body(weight, maxWeight, maxEngineWeight, maxFuelTankWeight, cost, color, brand);
        } else {
            printStatusBody(weight, maxWeight, maxEngineWeight, maxFuelTankWeight, cost, color, brand);
            return null;
        }
    }

    public static void printStatusBody(int weight, int maxWeight, int maxEngineWeight,
                                       int maxFuelTankWeight, int cost, Color color, Brand brand) {
        StringBuilder errors = new StringBuilder();

        if (maxWeight < maxEngineWeight + maxFuelTankWeight + weight) {
            errors.append("Body: ").append("Max weight too little").append('\n');
        }
        if (maxEngineWeight <= 0) {
            errors.append("Body: ").append("Max engine's weight must be positive").append('\n');
        }
        if (maxFuelTankWeight <= 0) {
            errors.append("Body: ").append("Max fuel tank's weight must be positive").append('\n');
        }
        if (weight <= 0) {
            errors.append("Body: ").append("Weight must be positive").append('\n');
        }
        if (cost < 0) {
            errors.append("Body: ").append("Cost must be positive or zero").append('\n');
        }
        if (color == null) {
            errors.append("Body: ").append("Color can't be null").append('\n');
        }
        if (brand == null) {
            errors.append("Body: ").append("Brand can't be null").append('\n');
        }

        System.out.println(errors);
        System.out.println("Body wasn't made");
    }
}
