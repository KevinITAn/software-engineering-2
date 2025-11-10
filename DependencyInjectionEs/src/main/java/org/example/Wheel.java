package org.example;

import javax.inject.Inject;

public class Wheel implements IWeighable {
    private ITire itire;

    @Inject
    public Wheel(ITire itire) {
        this.itire = itire;
    }

    @Override
    public int getWeight() {
        return 5+itire.getWeight();
    }
}
