package com.bob.rxjava;

import java.util.concurrent.Callable;

public interface ScalarCallable<T> extends Callable<T> {

    @Override
    T call();

}
