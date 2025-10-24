package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.*;

class MyListSubscriberTest {

    private MyList<String> list;

    @BeforeEach
    void setUp() {
        list = new MyList<>();
        list.addElement("A");
        list.addElement("B");
    }

    @Test
    void testSingleSubscriberIsNotifiedOnSetValue() {
        AtomicBoolean wasCalled = new AtomicBoolean(false);
        Subscriber sub = () -> wasCalled.set(true);

        list.subscribe(sub);
        list.setValue("Z", 0);

        assertTrue(wasCalled.get(), "Subscriber should be notified after setValue()");
    }

    @Test
    void testMultipleSubscribersAreNotified() {
        AtomicInteger notifyCount = new AtomicInteger(0);
        Subscriber s1 = notifyCount::incrementAndGet;
        Subscriber s2 = notifyCount::incrementAndGet;
        Subscriber s3 = notifyCount::incrementAndGet;

        list.subscribe(s1);
        list.subscribe(s2);
        list.subscribe(s3);

        list.setValue("X", 1);
        assertEquals(3, notifyCount.get(), "All subscribers should have been notified");
    }

    @Test
    void testNotifySubscribersMethodCallsUpdate() {
        AtomicInteger counter = new AtomicInteger(0);
        Subscriber sub = counter::incrementAndGet;

        list.subscribe(sub);
        list.notifySubscribers();
        assertEquals(1, counter.get(), "notifySubscribers() should call update()");
    }

    @Test
    void testSubscribeNullDoesNothing() {
        assertDoesNotThrow(() -> list.subscribe(null));
    }

    @Test
    void testUnsubscribeNullDoesNothing() {
        assertDoesNotThrow(() -> list.unSubscribe(null));
    }

    @Test
    void testUnsubscribeRemovesSubscriber() {
        AtomicBoolean notified = new AtomicBoolean(false);
        Subscriber sub = () -> notified.set(true);
        list.subscribe(sub);
        list.unSubscribe(sub);
        list.setValue("K", 1);
        assertFalse(notified.get(), "Subscriber should not be notified after unsubscribe()");
    }
}
