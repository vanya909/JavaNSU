package ru.nsu.programming.space_ship.engine;

import ru.nsu.programming.space_ship.Brand;

public class EngineFactory {

    public static Engine getEngine(int weight, int throttle, int speed, int hyperJumpDistance,
                                   int cost, int durability, Brand brand) {
        if (weight > 0 && throttle > weight &&
            speed > 0 && hyperJumpDistance > 0 &&
            cost >= 0 && durability > 0 && brand != null) {

            return new Engine(weight, throttle, speed, hyperJumpDistance, cost, durability, brand);
        } else {
            printStatusEngine(weight, throttle, speed, hyperJumpDistance, cost, durability, brand);
            return null;
        }
    }

    public static Accelerator getAccelerator(Brand brand, AbstractEngine engine) {
        if (brand != null && engine != null) {
            return new Accelerator(brand, engine);
        } else {
            printStatusAccelerator(brand, engine);
            return null;
        }
    }

    public static Accelerator getArmor(Brand brand, AbstractEngine engine) {
        if (brand != null && engine != null) {
            return new Accelerator(brand, engine);
        } else {
            printStatusArmor(brand, engine);
            return null;
        }
    }

    private static void printStatusEngine(int weight, int throttle, int speed, int hyperJumpDistance,
                                          int cost, int durability, Brand brand) {
        StringBuilder errors = new StringBuilder();

        if (weight <= 0) {
            errors.append("Engine: ").append("Weight must be positive").append('\n');
        }
        if (throttle <= weight) {
            errors.append("Engine: ").append("Throttle must be greater than weight").append('\n');
        }
        if (speed <= 0) {
            errors.append("Engine: ").append("Speed must be positive").append('\n');
        }
        if (hyperJumpDistance <= 0) {
            errors.append("Engine: ").append("Hyper jump distance must be positive").append('\n');
        }
        if (cost < 0) {
            errors.append("Engine: ").append("Cost can't be negative").append('\n');
        }
        if (durability <= 0) {
            errors.append("Engine: ").append("Durability must be positive").append('\n');
        }
        if (brand == null) {
            errors.append("Engine: ").append("Brand can't be null").append('\n');
        }

        System.out.println(errors);
        System.out.println("Engine wasn't made");
    }

    private static void printStatusAccelerator(Brand brand, AbstractEngine engine) {
        StringBuilder errors = new StringBuilder();

        if (brand == null) {
            errors.append("Accelerator: ").append("Brand can't be null").append('\n');
        }
        if (engine == null) {
            errors.append("Accelerator: ").append("Engine can't be null").append('\n');
        }

        System.out.println(errors);
        System.out.println("Accelerator wasn't made");
    }

    private static void printStatusArmor(Brand brand, AbstractEngine engine) {
        StringBuilder errors = new StringBuilder();

        if (brand == null) {
            errors.append("Armor: ").append("Brand can't be null").append('\n');
        }
        if (engine == null) {
            errors.append("Armor: ").append("Engine can't be null").append('\n');
        }

        System.out.println(errors);
        System.out.println("Armor wasn't made");
    }
}
