package com.bob.rxjava;

public interface ObservableOnSubscribe<T> {

    void subscribe(ObservableEmitter<T> emitter) throws Exception;

}
