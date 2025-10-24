package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.concurrent.atomic.AtomicBoolean;

class MyListTest {

    private MyList<String> list;

    @BeforeEach
    void setUp() {
        list = new MyList<>();
    }

    @Test
    void testAddElementAndLength() {
        assertEquals(0, list.length());
        list.addElement("A");
        list.addElement("B");
        assertEquals(2, list.length());
    }

    @Test
    void testAddNullThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> list.addElement(null));
    }

    @Test
    void testGetElementValidIndex() {
        list.addElement("X");
        list.addElement("Y");
        assertEquals("X", list.getElement(0));
        assertEquals("Y", list.getElement(1));
    }

    @Test
    void testGetElementInvalidIndex() {
        list.addElement("Z");
        assertThrows(IndexOutOfBoundsException.class, () -> list.getElement(5));
        assertThrows(IndexOutOfBoundsException.class, () -> list.getElement(-1));
    }

    @Test
    void testSetValueByIndex() {
        list.addElement("A");
        list.addElement("B");
        list.setValue("C", 1);
        assertEquals("C", list.getElement(1));
    }

    @Test
    void testSetValueByInvalidIndexThrows() {
        list.addElement("A");
        assertThrows(IndexOutOfBoundsException.class, () -> list.setValue("X", 10));
    }

    @Test
    void testSetValueByIterator() {
        list.addElement("A");
        list.addElement("B");
        MyIterator<String> it = list.getForwardIterator();
        it.nextElement(); // index 0
        list.setValue("Z", it);
        assertEquals("Z", list.getElement(0));
    }

    @Test
    void testSetValueNullIteratorThrows() {
        assertThrows(NullPointerException.class, () -> list.setValue("X", null));
    }

    @Test
    void testNotifySubscribersIsCalled() {
        list.addElement("A");
        AtomicBoolean wasNotified = new AtomicBoolean(false);
        Subscriber sub = () -> wasNotified.set(true);

        list.subscribe(sub);
        list.setValue("B", 0);

        assertTrue(wasNotified.get(), "Subscriber should have been notified");
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
    void testForwardIteratorNotNull() {
        list.addElement("A");
        assertNotNull(list.getForwardIterator());
    }

    @Test
    void testBackwardIteratorNotNull() {
        list.addElement("A");
        assertNotNull(list.getBackwardIterator());
    }
}
