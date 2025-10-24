package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FractionTest {

    @Test
    public void testConstructor(){
        //create valid test no use of gcd
        Fraction fraction1=new Fraction(1,1);
        Fraction fraction2=new Fraction(8,3);
        assertEquals(1,fraction1.getNum());
        assertEquals(1,fraction1.getDen());
        assertEquals(8,fraction2.getNum());
        assertEquals(3,fraction2.getDen());
        //create invalid test
        assertThrows(IllegalArgumentException.class,()->new Fraction(1,0));
    }

    //faccio il test tramite il costruttore
    @Test
    public void testGcd(){
        Fraction fraction1=new Fraction(2,1);
        Fraction fraction2 = new Fraction(4,2);
        Fraction fraction3 = new Fraction(10,100);
        assertEquals(2,fraction1.getNum());
        assertEquals(1,fraction1.getDen());

        assertEquals(2,fraction2.getNum());
        assertEquals(1,fraction2.getDen());

        assertEquals(1,fraction3.getNum());
        assertEquals(10,fraction3.getDen());
    }

    @Test
    public void testMol(){
        Fraction fractionBig=new Fraction(Long.MAX_VALUE,Long.MAX_VALUE-1);
        Fraction fraction1=new Fraction(1,2);
        Fraction fraction2=new Fraction(10,3);
        Fraction result=fraction1.mol(fraction2);
        //5/3
        assertEquals(5,result.getNum());
        assertEquals(3,result.getDen());

        assertThrows(ArithmeticException.class,()->fractionBig.mol(fractionBig));

    }

    @Test
    public void testDev(){
        Fraction fractionBig=new Fraction(Long.MAX_VALUE,Long.MAX_VALUE-1);
        Fraction fraction1=new Fraction(1,2);
        Fraction fraction2=new Fraction(10,3);
        Fraction fraction3=new Fraction(0,1);
        Fraction result=fraction1.div(fraction2);

        assertEquals(3,result.getNum());
        assertEquals(20,result.getDen());
        //lanciata dalla moltiplicazione
        assertThrows(ArithmeticException.class,()->fractionBig.div(fractionBig));
        assertThrows(IllegalArgumentException.class,()->fraction2.div(fraction3));//inverte fraction3 quindi num 0

    }

    @Test
    public void testSum(){
        Fraction fraction1=new Fraction(1,2);
        Fraction fraction2=new Fraction(1,3);
        Fraction result=fraction1.sum(fraction2);

        assertEquals(5,result.getNum());
        assertEquals(6,result.getDen());
    }

    @Test
    public void testSott(){
        Fraction fraction1=new Fraction(1,2);
        Fraction fraction2=new Fraction(1,3);
        Fraction result=fraction1.sott(fraction2);

        assertEquals(1,result.getNum());
        assertEquals(6,result.getDen());
    }





}