package org.example;


public final class Worker {

    private final int sequenceStart;

    public Worker(int sequenceStart) {
        this.sequenceStart = sequenceStart;
    }

    public static int conversion(int input) {
        return input % 2 == 0 ? input / 2 : (input * 3) + 1;
    }

    public int sequence() throws IllegalArgumentException {
        if (sequenceStart <= 2)
            throw new IllegalArgumentException("Il sequenceStart deve essere maggiore di 2");

        int tmpStart=sequenceStart;
        int cnt = 1;
        do {
            cnt++;
            tmpStart = conversion(tmpStart);
        } while (tmpStart > 2);
        return cnt;

    }
    /*
    public static int sequence(int sequenceStart) throws IllegalArgumentException {
        if(sequenceStart<=2)
            throw new IllegalArgumentException("Il sequenceStart deve essere maggiore di 2");

        int cnt=1;
        do{
            cnt++;
            sequenceStart=conversion(sequenceStart);
        }while(sequenceStart > 2);
        return cnt;
    }
    */

    public void SequenceCache(){

    }

}
