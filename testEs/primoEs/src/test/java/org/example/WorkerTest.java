package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WorkerTest {

    private Worker testWorker10;
    private Worker testWorker3;
    private Worker testWorker1;

    @BeforeEach
    public void setUp() {
        this.testWorker10 = new Worker(10);
        this.testWorker3 = new Worker(3);
        this.testWorker1 = new Worker(1);
    }

    @Test
    public void testConversion() {
        assertEquals(1, Worker.conversion(2));
        assertEquals(0, Worker.conversion(0));
        assertEquals(4, Worker.conversion(1));
        assertEquals(-2, Worker.conversion(-1));
    }

    @Test
    public void testSequence() {
        //test normale
        assertEquals(6, testWorker10.sequence()); //10 5 16 8 4 2
        assertEquals(7,testWorker3.sequence()); // 3 10 5 16 8 4 2
        //test eccezzione
        assertThrows(IllegalArgumentException.class, () -> testWorker1.sequence());
    }
}