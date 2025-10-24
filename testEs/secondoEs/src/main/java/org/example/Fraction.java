package org.example;

public final class Fraction {

    private long num,den;

    public Fraction(long num,long den){
        if(den==0)
            throw new IllegalArgumentException("Il denominatore deve essere diverso da 0");
        this.den=den;
        this.num=num;

        semplifica();
    }

    private void semplifica() {
        long divisor = gcd(Math.abs(num), Math.abs(den));
        num = num / divisor;
        den = den / divisor;
        // Assicura che il denominatore sia positivo
        if (den < 0) {
            num = -num;
            den = -den;
        }
    }


    /*
    L'algoritmo più comune per calcolare il GCD è l'algoritmo di Euclide:
    Dati due numeri a e b (con a ≥ b):

    Dividi a per b e trova il resto r.
    Se r = 0, allora b è il GCD.
    Altrimenti, sostituisci a con b e b con r, e ripeti il processo.
    */
    private long gcd(long a, long b) {
        //se uno dei due è zero, il GCD è l'assoluto dell'altro
        if (a == 0) return Math.abs(b);
        if (b == 0) return Math.abs(a);

        // Usa valori assoluti per semplificare il calcolo
        a = Math.abs(a);
        b = Math.abs(b);

        // Algoritmo di Euclide: continua fino a quando il resto è zero
        while (b != 0) {
            long remainder = a % b;
            a = b;
            b = remainder;
        }

        return a;
    }

    public Fraction mol(Fraction fractionIn){
        long numNew=this.num* fractionIn.num;
        long denNew=this.den * fractionIn.den;
        //controllo se il risultato è valido se è più piccolo ha fatto overflow
        if(Math.abs(numNew) < Math.abs(num) || Math.abs(numNew) < Math.abs(fractionIn.getNum()))
            throw new ArithmeticException("Errore numeratore troppo grande overflow");
        if(Math.abs(denNew) < Math.abs(den) || Math.abs(denNew) < Math.abs(fractionIn.getDen()))
            throw new ArithmeticException("Errore numeratore troppo grande overflow");
        //scompongo il risultato
        Fraction fractionNew=new Fraction(numNew,denNew);
        return fractionNew;
    }

    public Fraction div(Fraction fractionIn){
        Fraction fractionInversa= new Fraction(fractionIn.getDen(), fractionIn.getNum());
        return fractionInversa.mol(this);
    }
    //TODO check overflow non so come si fa
    public Fraction sum(Fraction fractionIn){
        long denNew=this.den*fractionIn.den;
        long numNew = this.num * fractionIn.den + fractionIn.num * this.den;
        return new Fraction(numNew,denNew);
    }
    //TODO check overflow non so come si fa
    public Fraction sott(Fraction fractionIn){
        long newDen=this.den*fractionIn.den;
        long newNum=(this.num/newDen)*this.num - (fractionIn.num/newDen)*fractionIn.num;
        return new Fraction(newNum,newDen);
    }

    @Override
    public String toString() {
        return "Fraction{" +
                "num=" + num +
                ", den=" + den +
                '}';
    }

    public long getNum(){
        return num;
    }

    public long getDen(){
        return den;
    }



}
