package com.bob.rxjava;

public interface Function<T, R> {

    R apply(T t) throws Exception;

}
