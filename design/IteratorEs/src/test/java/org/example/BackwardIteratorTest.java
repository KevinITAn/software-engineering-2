package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BackwardIteratorTest {

    private List<String> list;
    private MyIterator<String> iterator;

    @BeforeEach
    void setUp() {
        list = new ArrayList<>(Arrays.asList("A", "B", "C"));
        iterator = new BackwardIterator<>(list);
    }

    @Test
    void testHasMoreElementsStatoIniziale() {
        assertTrue(iterator.hasMoreElements());
    }

    @Test
    void testIterazioneCompletaOrdineInverso() {
        assertTrue(iterator.hasMoreElements());
        assertEquals("C", iterator.nextElement());

        assertTrue(iterator.hasMoreElements());
        assertEquals("B", iterator.nextElement());

        assertTrue(iterator.hasMoreElements());
        assertEquals("A", iterator.nextElement());

        assertFalse(iterator.hasMoreElements());
    }

    @Test
    void testNextElementLanciaEccezioneQuandoFinita() {
        iterator.nextElement(); // C
        iterator.nextElement(); // B
        iterator.nextElement(); // A

        assertFalse(iterator.hasMoreElements());
        assertThrows(RuntimeException.class, () -> iterator.nextElement());
    }

    @Test
    void testComportamentoListaVuota() {
        List<String> emptyList = new ArrayList<>();
        MyIterator<String> emptyIterator = new BackwardIterator<>(emptyList);

        assertFalse(emptyIterator.hasMoreElements());
        assertThrows(RuntimeException.class, () -> emptyIterator.nextElement());
    }

    @Test
    void testRewind() {
        iterator.nextElement(); // C
        iterator.nextElement(); // B

        assertNotNull(iterator.nextElement()); // A
        assertFalse(iterator.hasMoreElements());

        iterator.rewind();

        assertTrue(iterator.hasMoreElements());
        assertEquals("C", iterator.nextElement());
    }

    @Test
    void testGetIndex() {
        // Iniziale: actualPos = 2. getIndex() = 2 + 1 = 3
        assertEquals(3, iterator.getIndex());

        iterator.nextElement(); // Ritorna "C", actualPos diventa 1
        // getIndex() = 1 + 1 = 2
        assertEquals(2, iterator.getIndex());

        iterator.nextElement(); // Ritorna "B", actualPos diventa 0
        // getIndex() = 0 + 1 = 1
        assertEquals(1, iterator.getIndex());

        iterator.nextElement(); // Ritorna "A", actualPos diventa -1
        // getIndex() = -1 + 1 = 0
        assertEquals(0, iterator.getIndex());
    }

    @Test
    void testGetIndexSuListaVuota() {
        List<String> emptyList = new ArrayList<>();
        MyIterator<String> emptyIterator = new BackwardIterator<>(emptyList);

        // Iniziale: actualPos = -1. getIndex() = -1 + 1 = 0
        assertEquals(0, emptyIterator.getIndex());
    }
}