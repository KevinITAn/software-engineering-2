package org.example;

import java.util.List;

public class ForwardIterator<T> implements MyIterator<T> {

    private int actualPos;
    private final List<T> internalList;

    public ForwardIterator(List<T> internalList) {
        this.internalList = internalList;
        rewind();
    }

    @Override
    public void rewind() {
        this.actualPos = 0;
    }

    @Override
    public T nextElement() {
        if (!hasMoreElements())
            throw new RuntimeException("index out of bounds");
        T retElement = internalList.get(actualPos);
        actualPos++;
        return retElement;
    }

    @Override
    public boolean hasMoreElements() {
        return actualPos < internalList.size();
    }

    @Override
    public int getIndex() {
        // -1 perché actualPos è già incrementato dopo l’ultimo nextElement()
        return actualPos - 1 >= 0 ? actualPos - 1 : 0;
    }
}
