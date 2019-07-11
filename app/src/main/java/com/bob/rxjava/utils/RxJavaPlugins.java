package com.bob.rxjava.utils;

import com.bob.rxjava.BiFunction;
import com.bob.rxjava.Function;
import com.bob.rxjava.impl.Observable;
import com.bob.rxjava.Observer;

public final class RxJavaPlugins {

    static volatile Function<? super Observable, ? extends Observable> onObservableAssembly;
    static volatile BiFunction<? super Observable, ? super Observer, ? extends Observer> onObservableSubscribe;

   public static <T> Observable<T> onAssembly(Observable<T> source) {
       Function<? super Observable, ? extends Observable> f = onObservableAssembly;
       if (f != null) {
           return apply(f, source);
       }
       return source;
   }

    public static <T> Observer<? super T> onSubscribe(Observable source, Observer<? super T> observer) {
        BiFunction<? super Observable, ? super Observer, ? extends Observer> f = onObservableSubscribe;
        if (f != null) {
            return apply(f, source, observer);
        }
        return observer;
    }

    static <T, R> R apply(Function<T, R> f, T t) {
        try {
            return f.apply(t);
        } catch (Throwable ex) {
            throw ExceptionHelper.wrapOrThrow(ex);
        }
    }

    static <T, U, R> R apply(BiFunction<T, U, R> f, T t, U u) {
        try {
            return f.apply(t, u);
        } catch (Throwable ex) {
            throw ExceptionHelper.wrapOrThrow(ex);
        }
    }

}
