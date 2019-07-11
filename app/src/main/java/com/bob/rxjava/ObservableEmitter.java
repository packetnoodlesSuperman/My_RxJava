package com.bob.rxjava;

public interface ObservableEmitter<T> extends Emitter<T> {

    void setDisposable(Disposable d);

    void setCancellable(Cancellable c);

    boolean isDisposed();

}
