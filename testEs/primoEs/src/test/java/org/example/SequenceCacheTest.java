package org.example;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class SequenceCacheTest {

    SequenceCache sc;

    @BeforeEach
    public void setUp(){
        sc=new SequenceCache();
    }


    @Test
    public void tesLength(){
        assertEquals(6,sc.length(10));
        assertEquals(6,sc.length(10));
    }


}
