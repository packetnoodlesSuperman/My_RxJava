package com.bob.rxjava.utils;

import com.bob.rxjava.Disposable;

import java.util.concurrent.atomic.AtomicReference;

public enum DisposableHelper implements Disposable {

    DISPOSED;

    public static boolean dispose(AtomicReference<Disposable> field) {
        Disposable current = field.get();
        Disposable d = DISPOSED;
        if (current != d) {
            current = field.getAndSet(d);
            if (current != d) {
                if (current != null) {
                    current.dispose();
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public void dispose() {
        // deliberately no-op 故意不行动
    }

    @Override
    public boolean isDisposed() {
        return false;
    }
}
