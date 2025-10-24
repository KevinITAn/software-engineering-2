package org.example;

import java.util.ArrayList;

//wrapped for arrayList
public class MyList<T> implements Publisher {
    private ArrayList<T> innerList=new ArrayList<>();
    private ArrayList<Subscriber> subscribers=new ArrayList<>();

    public void addElement(T ob){
        if(ob==null)
            throw new IllegalArgumentException("Non si puù inserire null");
        innerList.add(ob);
    }
    int length(){
        return innerList.size();
    }
    T getElement(int pos){
        if(pos >= length() || pos<0)
            throw new IndexOutOfBoundsException("Indice < 0 || maggiore degli elementi presenti");
        return innerList.get(pos);
    }
    MyIterator<T> getForwardIterator(){
        return new ForwardIterator<>(innerList) ;
    }
    MyIterator<T> getBackwardIterator(){
        return new BackwardIterator<>(innerList);
    }

    @Override
    public void subscribe(Subscriber subscriber) {
        if(subscriber==null)
            return;
        subscribers.add(subscriber);
    }

    @Override
    public void unSubscribe(Subscriber subscriber) {
        if(subscriber==null || subscribers.contains(subscriber))
            return;
        subscribers.add(subscriber);
    }

    @Override
    public void notifySubscribers() {
        subscribers.forEach(Subscriber::update);
    }
}
