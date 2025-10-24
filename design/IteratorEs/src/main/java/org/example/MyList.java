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
            throw new IndexOutOfBoundsException("Index out of bounds!");
        return innerList.get(pos);
    }

    MyIterator<T> getForwardIterator(){
        return new ForwardIterator<>(innerList) ;
    }

    MyIterator<T> getBackwardIterator(){
        return new BackwardIterator<>(innerList);
    }

    public void setValue(T newValue, int index){
        if(index>=length() || index<0)
            throw new IndexOutOfBoundsException("index out of bounds!");
        innerList.set(index,newValue);
        //notify subscriber
        notifySubscribers();
    }

    public void setValue(T newValue, MyIterator<T> iterator){
        if(iterator==null)
            throw new NullPointerException("Iterator is null");
        setValue(newValue, iterator.getIndex());
    }

    @Override
    public void subscribe(Subscriber subscriber) {
        if(subscriber==null)
            return;
        subscribers.add(subscriber);
    }

    @Override
    public void unSubscribe(Subscriber subscriber) {
        if(subscriber==null)
            return;
        subscribers.remove(subscriber);
    }

    @Override
    public void notifySubscribers() {
        subscribers.forEach(Subscriber::update);
    }
}
