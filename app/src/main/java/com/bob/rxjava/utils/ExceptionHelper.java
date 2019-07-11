package com.bob.rxjava.utils;

public final class ExceptionHelper {

    public static RuntimeException wrapOrThrow(Throwable error) {
        if (error instanceof Error) {
            throw (Error)error;
        }
        if (error instanceof RuntimeException) {
            return (RuntimeException)error;
        }
        return new RuntimeException(error);
    }

}