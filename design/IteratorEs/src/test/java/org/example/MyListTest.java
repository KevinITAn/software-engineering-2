package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class MyListTest {

    private MyList<String> list;
    private Client testClient;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private static final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        list = new MyList<>();
        testClient = new Client();
        outContent.reset();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    //TEST ITERATOR PATTERN

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
        it.nextElement();
        list.setValue("Z", it);
        assertEquals("Z", list.getElement(0));
    }

    @Test
    void testSetValueNullIteratorThrows() {
        assertThrows(NullPointerException.class, () -> list.setValue("X", null));
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

    //TEST OBSERVER PATTERN

    @Test
    void testSetValueNotifiesSubscriber() {
        list.addElement("A");
        list.subscribe(testClient);
        list.setValue("B", 0);

        assertEquals("Notify arrived!", outContent.toString().trim());
    }

    @Test
    void testAddElementDoesNotNotify() {
        list.subscribe(testClient);
        list.addElement("B");

        assertEquals("", outContent.toString().trim());
    }

    @Test
    void testUnsubscribeStopsNotifications() {
        list.addElement("A");
        list.subscribe(testClient);
        list.unSubscribe(testClient);
        list.setValue("B", 0);

        assertEquals("", outContent.toString().trim());
    }

    @Test
    void testMultipleSubscribersReceiveNotify() {
        list.addElement("A");

        Client client2 = new Client();

        list.subscribe(testClient);
        list.subscribe(client2);
        list.setValue("B", 0);

        String expectedOutput = "Notify arrived!" + System.lineSeparator() + "Notify arrived!";
        assertEquals(expectedOutput, outContent.toString().trim());
    }

    @Test
    void testSetValueThrowsExceptionAndDoesNotNotify() {
        list.addElement("A");
        list.subscribe(testClient);

        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.setValue("X", 10);
        });

        assertEquals("", outContent.toString().trim());
    }
}