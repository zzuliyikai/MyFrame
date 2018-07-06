package com.yikai.myframe.utils;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.yikai.myframe.R;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RxjavaRequestDataActivity extends AppCompatActivity {

    private List<String> mList = new ArrayList<>();
    private RecyclerView mRv;
    private MyAdapter mMyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava_request_data);

        mRv = findViewById(R.id.recycler_data);
        mRv.setLayoutManager(new LinearLayoutManager(this));
        mMyAdapter = new MyAdapter(this, mList);
        mRv.setAdapter(mMyAdapter);

    }

    public void concat(View view) {
        //先请求缓存，然后在请求网络可以看出串行执行，耗时2.5s
        mList.clear();
        Observable.concat(getCacheData(500), getNetWorkData(2000))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<String>>() {
                    @Override
                    public void accept(List<String> strings) throws Exception {
                        Log.d("yikai123", strings.toString());

                        mList.clear();
                        mList.addAll(strings);
                        mMyAdapter.notifyDataSetChanged();
                    }
                });
    }

    public void concateager(View view) {
        //如果缓存先加载出来则显示缓存内容，然后显示网络内容，但是如果缓存内容后出来则网络数据会等待缓存数据返回才会加载出来
        mList.clear();
        List<Observable<List<String>>> observables = new ArrayList<>();
        observables.add(getCacheData(500));
        observables.add(getNetWorkData(2000));

        Observable.concatEager(observables)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<String>>() {

                    @Override
                    public void accept(List<String> strings) throws Exception {
                        mList.clear();
                        mList.addAll(strings);
                        mMyAdapter.notifyDataSetChanged();
                    }
                });

    }

    public void merge(View view) {
        //与concateager相似，但是当缓存数据返回的时候它并不会去等待，但是如果网络数据先回来，怎缓存数据会盖过网络数据
        mList.clear();
        Observable.merge(getCacheData(5000),getNetWorkData(2000))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<String>>() {
                    @Override
                    public void accept(List<String> strings) throws Exception {
                        mList.clear();
                        mList.addAll(strings);
                        mMyAdapter.notifyDataSetChanged();
                    }
                });
    }

    public void publish(View view) {
        mList.clear();

        getNetWorkData(2000).publish(new Function<Observable<List<String>>, ObservableSource<List<String>>>() {
            @Override
            public ObservableSource<List<String>> apply(Observable<List<String>> network) throws Exception {
                return Observable.merge(network,getCacheData(500)).takeUntil(network);
            }
        })
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<List<String>>() {
            @Override
            public void accept(List<String> strings) throws Exception {
                mList.clear();
                mList.addAll(strings);
                mMyAdapter.notifyDataSetChanged();
            }
        });
    }

    private Observable<List<String>> getNetWorkData(final long sleepTime){
        return Observable.create(new ObservableOnSubscribe<List<String>>() {

            @Override
            public void subscribe(ObservableEmitter<List<String>> e) throws Exception {
                Thread.sleep(sleepTime);
                List<String> list = new ArrayList<>();

                for (int i = 0; i < 15; i++) {
                    list.add("网络数据，第"+i+"条");
                }
                e.onNext(list);

                e.onComplete();
            }
        }).subscribeOn(Schedulers.io());
    }

    private Observable<List<String>> getCacheData(final long sleepTime){
        return Observable.create(new ObservableOnSubscribe<List<String>>() {

            @Override
            public void subscribe(ObservableEmitter<List<String>> e) throws Exception {
                Thread.sleep(sleepTime);
                List<String> list = new ArrayList<>();

                for (int i = 0; i < 15; i++) {
                    list.add("缓存数据，第"+i+"条");
                }
                e.onNext(list);
                e.onComplete();
            }
        })
                .onErrorResumeNext(new Function<Throwable, ObservableSource<? extends List<String>>>() {
                    @Override
                    public ObservableSource<? extends List<String>> apply(Throwable throwable) throws Exception {
                        List<String> list = new ArrayList<>();
                        list.add("缓存获取失败");
                        return Observable.just(list);
                    }
                })
                .subscribeOn(Schedulers.io());
    }



}
