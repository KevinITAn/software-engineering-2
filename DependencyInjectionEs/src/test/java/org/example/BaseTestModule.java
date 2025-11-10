package org.example;

import com.google.inject.AbstractModule;
import com.google.inject.Provider;
import com.google.inject.Provides;

//DONT USE just scheleton for summer/winter
class BaseTestModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(IEngine.class).to(FakeEngine.class);
        bind(IBody.class).to(FakeBody.class);
    }
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