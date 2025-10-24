package org.example;

import java.util.List;

public class BackwardIterator<T> implements MyIterator<T> {

    private int actualPos;
    private final List<T> internalList;

    public BackwardIterator(List<T> internalList) {
        this.internalList = internalList;
        rewind();
    }

    @Override
    public void rewind() {
        this.actualPos = internalList.size() - 1;
    }

    @Override
    public T nextElement() {
        if (!hasMoreElements())
            throw new RuntimeException("index out of bounds");
        T retElement = internalList.get(actualPos);
        actualPos--;
        return retElement;
    }

    @Override
    public boolean hasMoreElements() {
        return actualPos >= 0;
    }

    @Override
    public int getIndex() {
        return actualPos + 1;
    }
}
