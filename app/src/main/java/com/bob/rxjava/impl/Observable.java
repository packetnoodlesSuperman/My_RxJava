package com.bob.rxjava.impl;

import com.bob.rxjava.ObservableOnSubscribe;
import com.bob.rxjava.ObservableSource;
import com.bob.rxjava.Observer;
import com.bob.rxjava.scheduler.Scheduler;
import com.bob.rxjava.utils.RxJavaPlugins;

public abstract class Observable<T> implements ObservableSource<T> {

    /**
     * 创建事件源，但并不生产也不发射事件
     */
    public static <T> Observable<T> create(ObservableOnSubscribe<T> source) {
        return RxJavaPlugins.onAssembly(new ObservableCreate<T>(source));
    }

    public static <T> Observable<T> just(T item) {
        return RxJavaPlugins.onAssembly(new ObservableJust<T>(item));
    }


    protected abstract void subscribeActual(Observer<? super T> observer);

    /**
     * @param observer 观察者被订阅
     */
    @Override
    public final void subscribe(Observer<? super T> observer) {
        try{
            observer = RxJavaPlugins.onSubscribe(this, observer);
            subscribeActual(observer);
        } catch (NullPointerException e) {

        }
    }


    /**
     * 线程调度
     */
//    public final Observable<T> subscribeOn(Scheduler scheduler) {
//        return RxJavaPlugins.onAssembly(new ObservableSubscribeOn<T>(this, scheduler));
//    }
}
