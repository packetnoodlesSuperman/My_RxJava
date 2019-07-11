package com.bob.rxjava.other;

import com.bob.rxjava.Observer;
import com.bob.rxjava.QueueDisposable;

import java.util.concurrent.atomic.AtomicInteger;

public final class ObservableScalarXMap {

    public static final class ScalarDisposable<T> extends AtomicInteger implements QueueDisposable<T>, Runnable {

        final Observer<? super T> observer;
        final T value;

        public ScalarDisposable(Observer<? super T> observer, T value) {
            this.observer = observer;
            this.value = value;
        }

        @Override
        public void dispose() {

        }

        @Override
        public boolean isDisposed() {
            return false;
        }

        @Override
        public void run() {

        }
    }

}
