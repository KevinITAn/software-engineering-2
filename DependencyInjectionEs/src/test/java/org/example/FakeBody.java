package org.example;

class FakeBody implements IBody {
    // Peso fittizio noto per il test
    private final int fakeWeight = 250;

    public int getWeight() {
        return fakeWeight;
    }
}
