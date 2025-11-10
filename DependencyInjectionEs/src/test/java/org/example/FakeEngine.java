package org.example;

class FakeEngine implements IEngine {
    // Peso fittizio noto per il test
    private final int fakeWeight = 100;

    @Override
    public int getWeight() {
        return fakeWeight;
    }
}

