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

    // 事件源，生产事件的接口，由我们自己实现
    public ObservableCreate(ObservableOnSubscribe<T> source) {
        this.source = source;
    }

    /**
     * @param observer 调用Subscribe是 传入的observer 观察者
     */
    @Override
    protected void subscribeActual(Observer<? super T> observer) {
        // 发射器
        CreateEmitter<T> parent = new CreateEmitter<T>(observer);
        //直接回调了观察者的onSubscribe
        observer.onSubscribe(parent);

        try {
            // 调用了事件源subscribe方法生产事件，同时将发射器传给事件源。
            // 现在我们明白了，数据源生产事件的subscribe方法只有在observable.subscribe(observer)被执行后才执行的。
            // 换言之，事件流是在订阅后才产生的。
            //而observable被创建出来时并不生产事件，同时也不发射事件。
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
        public void onNext(T t) {
            if (t == null) {
                //在这里抛出异常
                return;
            }
            if (!isDisposed()) {
                observer.onNext(t);
            }

        }

        @Override
        public void onError(Throwable error) {

        }

        @Override
        public void onComplete() {
            if (!isDisposed()) {
                try {
                    observer.onComplete();
                } finally {
                    dispose();
                }
            }
        }
    }
}
