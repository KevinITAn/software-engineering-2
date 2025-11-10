package org.example;

import javax.inject.Inject;
import java.util.Arrays;

public class Car {
    //tramite DI

    private final IEngine engine;
    private final IBody body;
    private final Frame frame;
    private final Interior interior;
    private final Wheel[] wheels;

    @Inject
    public Car(IEngine engine, IBody body, Frame frame, Interior interior, Wheel[] wheels) {
        this.engine = engine;
        this.body = body;
        this.frame = frame;
        this.interior = interior;
        this.wheels = wheels;
    }

    public int getWeight() {
        return engine.getWeight()+body.getWeight()+frame.getWeight()+interior.getWeight()+ Arrays.stream(wheels).mapToInt(Wheel::getWeight).sum();
    }

    @Override
    public String toString() {
        return "Car{" +
                "engine=" + engine +
                ", body=" + body +
                ", frame=" + frame +
                ", interior=" + interior +
                ", wheels=" + Arrays.toString(wheels) +
                '}';
    }
}