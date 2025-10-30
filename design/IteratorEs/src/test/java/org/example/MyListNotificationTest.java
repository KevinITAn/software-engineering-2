package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

// Importa solo asserzioni JUNIT 5 (Jupiter)
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test dedicati al sistema di notifiche (Publisher/Subscriber)
 * tra MyList e Client.
 */
public class MyListNotificationTest {

    private MyList<Integer> testList;
    private Client testClient;

    // Buffer per catturare System.out
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private static final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        testList = new MyList<>();
        testClient = new Client();
        // Questo NON deve notificare
        testList.addElement(100);
        outContent.reset();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }


    @Test
    void testSetValueNotifiesSubscriber() {

        testList.subscribe(testClient);


        testList.setValue(99, 0);


        assertEquals("Notify arrived!", outContent.toString().trim());
    }


    @Test
    void testAddElementDoesNotNotify() {

        testList.subscribe(testClient);

        testList.addElement(200);


        assertEquals("", outContent.toString().trim());
    }


    @Test
    void testUnsubscribeStopsNotifications() {

        testList.subscribe(testClient);

        testList.unSubscribe(testClient);
        testList.setValue(99, 0);

        assertEquals("", outContent.toString().trim());
    }


    @Test
    void testMultipleSubscribersReceiveNotify() {

        Client client2 = new Client();
        testList.subscribe(testClient);
        testList.subscribe(client2);


        testList.setValue(99, 0);


        String expectedOutput = "Notify arrived!" + System.lineSeparator() + "Notify arrived!";
        assertEquals(expectedOutput, outContent.toString().trim());
    }


    @Test
    void testSetValueThrowsExceptionAndDoesNotNotify() {

        testList.subscribe(testClient);

        assertThrows(IndexOutOfBoundsException.class, () -> {
            testList.setValue(99, 10);
        });

    }
}