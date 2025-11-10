package org.example;

class SummerTestModule extends BaseTestModule {
    @Override
    protected void configure() {
        super.configure();
        bind(ITire.class).to(SummerTire.class);
    }
}
