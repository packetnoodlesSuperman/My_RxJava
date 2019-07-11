package com.bob.rxjava.impl;

import com.bob.rxjava.ObservableOnSubscribe;
import com.bob.rxjava.ObservableSource;
import com.bob.rxjava.Observer;
import com.bob.rxjava.utils.RxJavaPlugins;

public abstract class Observable<T> implements ObservableSource<T> {

    public static <T> Observable<T> create(ObservableOnSubscribe<T> source) {
        return RxJavaPlugins.onAssembly(new ObservableCreate<T>(source));
    }

    protected abstract void subscribeActual(Observer<? super T> observer);

    @Override
    public final void subscribe(Observer<? super T> observer) {
        try{
            observer = RxJavaPlugins.onSubscribe(this, observer);
            subscribeActual(observer);
        } catch (NullPointerException e) {

        }
    }
}
