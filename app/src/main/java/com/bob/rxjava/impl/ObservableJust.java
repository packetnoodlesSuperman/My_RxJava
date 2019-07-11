package com.bob.rxjava.impl;

import com.bob.rxjava.Observer;
import com.bob.rxjava.ScalarCallable;
import com.bob.rxjava.other.ObservableScalarXMap;

public final class ObservableJust<T> extends Observable<T> implements ScalarCallable<T> {

    private final T value;

    public ObservableJust(final T value) {
        this.value = value;
    }

    @Override
    public T call() {
        return value;
    }

    @Override
    protected void subscribeActual(Observer<? super T> s) {
        ObservableScalarXMap.ScalarDisposable<T> sd = new ObservableScalarXMap.ScalarDisposable<T>(s, value);
        s.onSubscribe(sd);
        sd.run();
    }
}
