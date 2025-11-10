package org.example;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CarTest {

    private Car car;

    @Test
    void testTotalWeightCalculationSummer() {
        Injector injector = Guice.createInjector(new SummerTestModule());
        Car car = injector.getInstance(Car.class);

        int expectedWeight = 419;

        int actualWeight = car.getWeight();

        assertEquals(expectedWeight, actualWeight);
    }
    @Test
    void testTotalWeightCalculationWinter() {
        Injector injector = Guice.createInjector(new WinterTestModule());
        Car car = injector.getInstance(Car.class);

        int expectedWeight = 423;

        int actualWeight = car.getWeight();

        assertEquals(expectedWeight, actualWeight);
    }
}
