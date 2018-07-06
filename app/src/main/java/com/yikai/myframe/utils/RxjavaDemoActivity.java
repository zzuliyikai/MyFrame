package com.yikai.myframe.utils;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.yikai.myframe.R;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.List;
import java.util.Observable;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableObserver;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;
import io.reactivex.MaybeObserver;
import io.reactivex.MaybeOnSubscribe;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleObserver;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Function3;
import io.reactivex.functions.Function4;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class RxjavaDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava_demo);
    }

    public void single(View view) {
        Single.create(new SingleOnSubscribe<String>() {
            @Override
            public void subscribe(SingleEmitter<String> e) throws Exception {

                e.onSuccess("www");
            }
        })
                .observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {

                        Log.d("yikai123", s);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

    public void flowable(View view) {
        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onSubscribe(Subscription s) {
                Log.d("yikai123", "pre");
                s.request(100);
                Log.d("yikai123", "next");
            }

            @Override
            public void onNext(String s) {
                Log.d("yikai123", s);

            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {
                Log.d("yikai123", "onComplete");
            }
        };

        //创建一个被观察者

        Flowable<String> stringFlowable = Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(FlowableEmitter<String> e) throws Exception {
                e.onNext("hello world!");
                e.onComplete();

            }
        }, BackpressureStrategy.BUFFER);

        stringFlowable.subscribeOn(Schedulers.io());
        //      stringFlowable.subscribe(subscriber);
        stringFlowable.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.d("yikai123", s);
            }
        });
    }

    public void completable(View view) {
        Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(CompletableEmitter e) throws Exception {
                Log.d("yikai123", Thread.currentThread().getName() + "");
                e.onComplete();
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        Log.d("yikai123", Thread.currentThread().getName() + "");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    public void maybe(View view) {
        Maybe.create(new MaybeOnSubscribe<String>() {
            @Override
            public void subscribe(MaybeEmitter<String> e) throws Exception {

                e.onSuccess("success");
                Log.d("yikai123", Thread.currentThread().getName());
            }
        })
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new MaybeObserver<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(String s) {
                        Log.d("yikai123", Thread.currentThread().getName());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void observe(View view) {

        io.reactivex.Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {

                e.onNext("你好！"); //ObservableEmitter  发射器
            }
        })
                .subscribeOn(Schedulers.io())   // 被观察者运行在io线程上
                .observeOn(AndroidSchedulers.mainThread())  // 观察者运行在main上
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {

                        Log.d("yikai123", s);
                    }
                });
    }

    public void just(View view) {
        io.reactivex.Observable.just("a", "b", 1, view)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object s) throws Exception {
                        Log.d("yikai123", s.toString());
                    }
                });

    }

    public void from(View view) {
        String[] ss = {"one", "two", "three"};
        io.reactivex.Observable.fromArray(ss)
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.d("yikai123", s);
                    }
                });
    }

    String s1 = "hello";
    String s2 = "rxjava";

    public void defer(View view) {
        //defer延迟加载，真正的创建发生在订阅的时候
        io.reactivex.Observable<String> observable = io.reactivex.Observable.defer(new Callable<ObservableSource<String>>() {
            @Override
            public ObservableSource<String> call() throws Exception {

                return io.reactivex.Observable.just(s1);  //在这并没有真正创建
            }
        });
        s1 = s2;
        observable.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.d("yikai123", s);
            }
        });

    }

    public void empty(View view) {
        //发送一个空的
        io.reactivex.Observable.empty().subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {

            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                Log.d("yikai123", "complete   " + Thread.currentThread().getName());
            }
        });


    }

    public void error(View view) {
        io.reactivex.Observable.error(new RuntimeException("fuck"))
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d("yikai123", "Throwable   " + throwable.getMessage());
                    }
                });

    }

    public void range(View view) {
        io.reactivex.Observable.range(0, 8)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d("yikai123", "Throwable   " + integer);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                }, new Consumer<Disposable>() {

                    @Override
                    public void accept(Disposable disposable) throws Exception {

                        disposable.dispose(); //  设置不是一次性的
                        Log.d("yikai123", "Throwable   " + disposable.isDisposed());

                    }
                });

    }

    public void interval(View view) {
        io.reactivex.Observable.interval(3, TimeUnit.SECONDS)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {

                        Log.d("yikai", aLong + "'");
                    }
                });
    }

    public void buffer(View view) {
        io.reactivex.Observable.range(0, 11)
                .buffer(3)
                .subscribe(new Consumer<List<Integer>>() {

                    @Override
                    public void accept(List<Integer> integers) throws Exception {
                        Log.d("yikai123", integers.toString());
                    }
                });

    }

    public void map(View view) {
        io.reactivex.Observable.just("1", "2", "3")
                .map(new Function<String, Integer>() {
                    @Override
                    public Integer apply(String s) throws Exception {

                        return Integer.valueOf(s);
                    }
                })
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d("yikai123", integer + "");
                    }
                });
    }

    public void flatMap(View view) {
        io.reactivex.Observable.just("hello", "rxjava2", "nice")
                .flatMap(new Function<String, ObservableSource<Integer>>() {
                    @Override
                    public ObservableSource<Integer> apply(String s) throws Exception {
                        Log.d("yikai123", "" + s);
                        return io.reactivex.Observable.just(s.hashCode());
                    }
                })
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d("yikai123", integer + "");
                    }
                });
    }

    public void filter(View view) {

        io.reactivex.Observable.just("hello", "rxjava2", "nice")
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(String s) throws Exception {
                        return s.equals("rxjava2");
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.d("yikai123", s);
                    }
                });


    }

    public void take(View view) {
        io.reactivex.Observable.just("hello", "rxjava2", "nice")
                .take(2)
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.d("yikai123", s);
                    }
                });
    }

    public void skip(View view) {

        io.reactivex.Observable.just("hello", "rxjava2", "nice")
                .skipLast(2)
                .subscribe(
                        new Consumer<String>() {
                            @Override
                            public void accept(String s) throws Exception {
                                Log.d("yikai123", s);
                            }
                        }
                );
    }

    public void elementAt(View view) {
        io.reactivex.Observable.just(1, 2, 3)
                .elementAt(1, 0)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d("yikai123", integer + "");
                    }
                });
    }

    public void distinct(View view) {

        io.reactivex.Observable.just(1, 2, 3, 4, 4, 2)
                .distinct()
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d("yikai123", integer + "");
                    }
                });
    }

    public void startWith(View view) {
        //  Integer [] integers = {7,8,9};
        Integer integers = 2;
        Integer integers1 = 3;
        io.reactivex.Observable.just(1, 2, 3, 4)
                .startWith(integers)
                .startWith(integers1)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d("yikai123", integer + "");
                    }
                });
    }

    public void merge(View view) {
        io.reactivex.Observable.merge(io.reactivex.Observable.just(1), io.reactivex.Observable.just(2, 3), io.reactivex.Observable.just(2, 3))
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d("yikai123", integer + "");
                    }
                });
    }

    public void zip(View view) {

        io.reactivex.Observable.zip(io.reactivex.Observable.just(1, 3, 4),
                io.reactivex.Observable.just(2, 1),
                io.reactivex.Observable.just(2, 4),
                new Function3<Integer, Integer, Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer, Integer integer2, Integer integer3) throws Exception {
                        return integer > integer2 ? integer3 : 0;
                    }
                })
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {

                        Log.d("yikai123", integer + "");

                    }
                });


    }


    public void doOnNext(View view) {

        io.reactivex.Observable<Integer> t = io.reactivex.Observable.just(1, 2, 3)
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d("yikai123", integer + "" + Thread.currentThread().getName());
                    }
                });

        io.reactivex.Observable<Integer> t1 = io.reactivex.Observable.just(4, 5, 6)
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d("yikai123", integer + "");
                    }
                });

        io.reactivex.Observable.concat(t, t1)
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d("yikai123", integer + "");
                    }
                });


    }
}
