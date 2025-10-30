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

    @Test
    void testAddElement() {
        list.addElement("A");
        list.addElement("B");
        assertEquals(2, list.length());
    }

    @Test
    void testAddElementNullThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> list.addElement(null));
    }

    @Test
    void testLength() {
        assertEquals(0, list.length());
        list.addElement("A");
        assertEquals(1, list.length());
    }

    @Test
    void testGetElementValid() {
        list.addElement("A");
        list.addElement("B");
        assertEquals("A", list.getElement(0));
        assertEquals("B", list.getElement(1));
    }

    @Test
    void testGetElementIndexOutOfBoundsHigh() {
        list.addElement("A");
        assertThrows(IndexOutOfBoundsException.class, () -> list.getElement(1));
    }

    @Test
    void testGetElementIndexOutOfBoundsLow() {
        list.addElement("A");
        assertThrows(IndexOutOfBoundsException.class, () -> list.getElement(-1));
    }

    @Test
    void testGetForwardIteratorNotNull() {
        assertNotNull(list.getForwardIterator());
    }

    @Test
    void testGetBackwardIteratorNotNull() {
        assertNotNull(list.getBackwardIterator());
    }

    @Test
    void testSetValueByIndex() {
        list.addElement("A");
        list.setValue("B", 0);
        assertEquals("B", list.getElement(0));
    }

    @Test
    void testSetValueByIndexNotifiesSubscribers() {
        list.addElement("A");
        list.subscribe(testClient);
        list.setValue("B", 0);
        assertEquals("Notify arrived!", outContent.toString().trim());
    }

    @Test
    void testSetValueByIndexThrowsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.setValue("B", 0));
    }

    @Test
    void testSetValueByIndexThrowsExceptionAndDoesNotNotify() {
        list.addElement("A");
        list.subscribe(testClient);
        assertThrows(IndexOutOfBoundsException.class, () -> list.setValue("B", 10));
        assertEquals("", outContent.toString().trim());
    }

    @Test
    void testSetValueByIterator() {
        list.addElement("A");
        list.addElement("B");
        MyIterator<String> it = list.getForwardIterator();
        it.nextElement(); // it.getIndex() ora ritorna 0

        list.setValue("C", it);
        assertEquals("C", list.getElement(0));
    }

    @Test
    void testSetValueByIteratorNotifiesSubscribers() {
        list.addElement("A");
        MyIterator<String> it = list.getForwardIterator();
        it.nextElement(); // it.getIndex() ora ritorna 0

        list.subscribe(testClient);
        list.setValue("C", it);

        assertEquals("Notify arrived!", outContent.toString().trim());
    }

    @Test
    void testSetValueByNullIteratorThrowsException() {
        assertThrows(NullPointerException.class, () -> list.setValue("A", null));
    }

    @Test
    void testSubscribe() {
        list.addElement("A");
        list.subscribe(testClient);
        list.setValue("B", 0);
        assertFalse(outContent.toString().trim().isEmpty());
    }

    @Test
    void testSubscribeNullDoesNothing() {
        assertDoesNotThrow(() -> list.subscribe(null));
    }

    @Test
    void testUnSubscribe() {
        list.addElement("A");
        list.subscribe(testClient);
        list.unSubscribe(testClient);
        list.setValue("B", 0);
        assertTrue(outContent.toString().trim().isEmpty());
    }

    @Test
    void testUnSubscribeNullDoesNothing() {
        assertDoesNotThrow(() -> list.unSubscribe(null));
    }

    @Test
    void testNotifySubscribersIsCalledBySetValue() {
        list.addElement("A");
        Client client2 = new Client();
        list.subscribe(testClient);
        list.subscribe(client2);

        list.setValue("B", 0);

        String expectedOutput = "Notify arrived!" + System.lineSeparator() + "Notify arrived!";
        assertEquals(expectedOutput, outContent.toString().trim());
    }

    @Test
    void testAddElementDoesNotNotifySubscribers() {
        list.subscribe(testClient);
        list.addElement("A");
        assertEquals("", outContent.toString().trim());
    }
}