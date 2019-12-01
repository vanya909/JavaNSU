package ru.nsu.programming.space_ship.factories;
import ru.nsu.programming.space_ship.*;

public class EngineFactory {
    public Engine getEngine(Brand brand) {
        switch (brand) {
            case PORSCHE:
                return new Engine(80, 650, 1300,
                        480, 6900, 1200, Brand.PORSCHE);
            case BMW:
                return new Engine(100, 700, 1100,
                         500, 7000, 800, Brand.BMW);
            case VOLKSWAGEN:
                return new Engine(140, 900, 1050,
                        850, 9000, 1700, Brand.VOLKSWAGEN);
        }

        return null;
    }
}
