package com.bob.rxjava.utils;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

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

    private void demo1() {
        //创建一个上游 Observable：
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
            public void onSubscribe(Disposable d) { Log.d("TAG", "subscribe"); }
            @Override
            public void onNext(Integer value) { Log.d("TAG", "" + value); }
            @Override
            public void onError(Throwable e) { Log.d("TAG", "error"); }
            @Override
            public void onComplete() { Log.d("TAG", "complete"); }
        };
        //建立连接 只有当上游和下游建立连接后, 上游才开始发送事件. 也就是调用了subscribe() 方法后才开始发送事件
        observable.subscribe(observer);
    }

    private void demo2() {

    }
}
