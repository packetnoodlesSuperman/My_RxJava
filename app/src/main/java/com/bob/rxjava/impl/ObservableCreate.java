package com.bob.rxjava.impl;

import com.bob.rxjava.Cancellable;
import com.bob.rxjava.Disposable;
import com.bob.rxjava.ObservableEmitter;
import com.bob.rxjava.ObservableOnSubscribe;
import com.bob.rxjava.Observer;
import com.bob.rxjava.utils.DisposableHelper;

import java.util.concurrent.atomic.AtomicReference;

public final class ObservableCreate<T> extends Observable<T> {

    final ObservableOnSubscribe<T> source;

    public ObservableCreate(ObservableOnSubscribe<T> source) {
        this.source = source;
    }

    @Override
    protected void subscribeActual(Observer<? super T> observer) {
        CreateEmitter<T> parent = new CreateEmitter<T>(observer);
        observer.onSubscribe(parent);

        try {
            source.subscribe(parent);
        } catch (Throwable ex) {

        }
    }


    static final class CreateEmitter<T> extends AtomicReference<Disposable> implements ObservableEmitter<T>, Disposable {
        private static final long serialVersionUID = -3434801548987643227L;
        final Observer<? super T> observer;

        CreateEmitter(Observer<? super T> observer) {
            this.observer = observer;
        }

        @Override
        public void dispose() {
            DisposableHelper.dispose(this);
        }

        @Override
        public void setDisposable(Disposable d) {

        }

        @Override
        public void setCancellable(Cancellable c) {

        }

        @Override
        public boolean isDisposed() {
            return false;
        }

        @Override
        public void onNext(T value) {

        }

        @Override
        public void onError(Throwable error) {

        }

        @Override
        public void onComplete() {

        }
    }
}
