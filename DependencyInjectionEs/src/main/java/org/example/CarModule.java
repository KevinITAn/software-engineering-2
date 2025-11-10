package org.example;

import com.google.inject.AbstractModule;
import com.google.inject.Provider;
import com.google.inject.Provides;

public class CarModule extends AbstractModule {
    @Override
    protected void configure() {
        //bing all interface
        bind(IEngine.class).to(Engine.class);
        bind(IBody.class).to(Body.class);
        //bind with provides
    }
    //exception we don't know witch tire use
    @Provides
    public Wheel[] provideWheels(Provider<Wheel> wheelProvider){
        Wheel[] wheels = new Wheel[4];
        //for each elment, ask to provide a new instance
        for(int i = 0; i < wheels.length; i++){
            wheels[i] = wheelProvider.get(); //call constructor
        }
        return wheels;
    }
}
