package com.bob.rxjava;

//观察者
public interface Observer<T> {

    void onSubscribe(Disposable d);

    void onNext(T t);

    void onError(Throwable e);

    void onComplete();

}
