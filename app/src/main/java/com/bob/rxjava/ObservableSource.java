package com.bob.rxjava;

//被观察者
public interface ObservableSource<T> {

    void subscribe(Observer<? super T> observer);

}
