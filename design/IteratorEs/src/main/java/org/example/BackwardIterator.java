package org.example;

import java.util.List;

public class BackwardIterator<T> implements MyIterator<T>{

    private int actualPos;
    private final List<T> internalList;

    public BackwardIterator(List<T> internalList) {
        this.internalList = internalList;
        rewind();
    }

    @Override
    public void rewind() {
        this.actualPos=0;
    }

    @Override
    public T nextElement() {
        if(!hasMoreElements())
            throw new RuntimeException("index out of bound");
        actualPos++;
        return internalList.get(actualPos);
    }

    @Override
    public boolean hasMoreElements() {
        return internalList.size() == actualPos;
    }

}
