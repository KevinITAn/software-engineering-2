package org.example;

public interface Publisher {

    void subscribe(Subscriber subscriber);

    void unSubscribe(Subscriber subscriber);

    void notifySubscribers();

}