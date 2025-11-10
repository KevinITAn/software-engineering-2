package org.example;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class Client {
    public static void main(String[] args) {
        //create injector
        Injector injector = Guice.createInjector(new CarModule());//it used to bind
        Car myCar = injector.getInstance(Car.class);
        System.out.println("I create car with inject" + myCar + "\n It's weight:" + myCar.getWeight());
    }
}
