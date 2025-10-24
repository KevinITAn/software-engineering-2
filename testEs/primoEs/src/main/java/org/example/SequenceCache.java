package org.example;

import java.util.HashMap;
import java.util.Map;

public class SequenceCache {

    Map<Integer,Worker> map=new HashMap<>();

    public int length(int value) throws IllegalArgumentException{
        //questo perchè la funzione sequnce richide numeri > 2
        if(value<2)
            throw new IllegalArgumentException("Il value deve essere maggiore di 2");
        //chiamo direttamente senza creare nuovo worker
        if(map.containsKey(value))
            return map.get(value).sequence();

        //creo prima worker
        Worker workerTmp=new Worker(value);
        map.put(value,workerTmp);
        return workerTmp.sequence();
    }


}