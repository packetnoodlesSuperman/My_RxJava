package com.bob.rxjava;

//发射器
public interface Emitter<T> {

    void onNext(T value);

    void onError(Throwable error);

    void onComplete();

}
