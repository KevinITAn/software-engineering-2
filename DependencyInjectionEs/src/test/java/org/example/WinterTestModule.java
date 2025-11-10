package org.example;

class WinterTestModule extends BaseTestModule {
    @Override
    protected void configure() {
        super.configure();
        // Dipendenza di II livello: ITire è associato a WinterTire
        bind(ITire.class).to(WinterTire.class);
    }
}

