package org.example;

import java.util.Iterator;
import java.util.ListIterator;

public interface MyIterator<T> {

    void rewind();
    T nextElement();
    boolean hasMoreElements();
    int getIndex();
}
