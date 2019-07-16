package com.bob.rxjava.utils;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bob.rxjava.Disposable;
import com.bob.rxjava.impl.Observable;
import com.bob.rxjava.ObservableEmitter;
import com.bob.rxjava.ObservableOnSubscribe;
import com.bob.rxjava.Observer;
import com.bob.rxjava.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        demo1();
        demo2();
    }

    public static void main(String[] args) {
        demo1();
        demo2();
    }

    private static void demo1() {
        //创建一个上游 Observable：
        /**
         * Observable.create(new ObservableOnSubscribe<Integer>() 其实等同于 下面
         * new ObservableCreate<T>(source)
         * new ObservableOnSubscribe<Integer>()
         */
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                //ObservableEmitter 发射器
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        });
        //创建一个下游 Observer
        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) { System.out.println("subscribe"); }
            @Override
            public void onNext(Integer value) { System.out.println("" + value); }
            @Override
            public void onError(Throwable e) { System.out.println("error"); }
            @Override
            public void onComplete() { System.out.println("complete"); }
        };
        //建立连接 只有当上游和下游建立连接后, 上游才开始发送事件. 也就是调用了subscribe() 方法后才开始发送事件
        observable.subscribe(observer);
    }

    private static void demo2() {
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                //ObservableEmitter 发射器
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        })
//                .subscribeOn(Schedulers.io())
                ;
    }
}
