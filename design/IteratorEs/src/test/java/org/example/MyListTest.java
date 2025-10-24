package org.example;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class MyListTest {
    MyList<Integer> testList=new MyList<>();

    @BeforeEach
    public void setup(){
        testList=new MyList<>();
    }

    @Test
    public void testAddElement(){
        assertThrows(IllegalArgumentException.class,()->testList.addElement(null));
        testList.addElement(1);
        assertEquals(1,testList.length());
    }

    @Test
    public void testLength(){
        assertEquals(0,testList.length());
        testList.addElement(1);
        assertEquals(1,testList.length());
    }

    @Test
    public void testGetElement(){
        assertEquals(0,testList.length());
        testList.addElement(1);
        assertEquals(Integer.valueOf(1),testList.getElement(0));
        //test exception
        assertThrows(IndexOutOfBoundsException.class,()->testList.getElement(-1));
        assertThrows(IndexOutOfBoundsException.class,()->testList.getElement(10));
    }






}
