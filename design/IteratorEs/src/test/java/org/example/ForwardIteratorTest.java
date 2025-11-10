package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ForwardIteratorTest {

    private List<String> list;
    private MyIterator<String> iterator;

    @BeforeEach
    void setUp() {
        list = new ArrayList<>(Arrays.asList("A", "B", "C"));
        iterator = new ForwardIterator<>(list);
    }

    @Test
    void testHasMoreElementsStatoIniziale() {
        assertTrue(iterator.hasMoreElements());
    }

    @Test
    void testIterazioneCompletaOrdineCorretto() {
        assertTrue(iterator.hasMoreElements());
        assertEquals("A", iterator.nextElement());

        assertTrue(iterator.hasMoreElements());
        assertEquals("B", iterator.nextElement());

        assertTrue(iterator.hasMoreElements());
        assertEquals("C", iterator.nextElement());

        assertFalse(iterator.hasMoreElements());
    }

    @Test
    void testNextElementLanciaEccezioneQuandoFinita() {
        iterator.nextElement(); // A
        iterator.nextElement(); // B
        iterator.nextElement(); // C

        assertFalse(iterator.hasMoreElements());
        assertThrows(RuntimeException.class, () -> iterator.nextElement());
    }

    @Test
    void testComportamentoListaVuota() {
        List<String> emptyList = new ArrayList<>();
        MyIterator<String> emptyIterator = new ForwardIterator<>(emptyList);

        assertFalse(emptyIterator.hasMoreElements());
        assertThrows(RuntimeException.class, () -> emptyIterator.nextElement());
    }

    @Test
    void testRewind() {
        iterator.nextElement(); // A
        iterator.nextElement(); // B

        iterator.rewind();

        assertTrue(iterator.hasMoreElements());
        assertEquals("A", iterator.nextElement()); // Deve ricominciare da "A"
        assertEquals("B", iterator.nextElement());
    }

    @Test
    void testGetIndex() {
        // Iniziale: actualPos = 0. getIndex() = 0
        assertEquals(0, iterator.getIndex());

        iterator.nextElement(); // Ritorna "A" (index 0). actualPos diventa 1
        // getIndex() = (1-1) = 0
        assertEquals(0, iterator.getIndex());

        iterator.nextElement(); // Ritorna "B" (index 1). actualPos diventa 2
        // getIndex() = (2-1) = 1
        assertEquals(1, iterator.getIndex());

        iterator.nextElement(); // Ritorna "C" (index 2). actualPos diventa 3
        // getIndex() = (3-1) = 2
        assertEquals(2, iterator.getIndex());
    }

    @Test
    void testGetIndexSuListaVuota() {
        List<String> emptyList = new ArrayList<>();
        MyIterator<String> emptyIterator = new ForwardIterator<>(emptyList);

        // Iniziale: actualPos = 0. getIndex() = 0
        assertEquals(0, emptyIterator.getIndex());
    }
}