package com.yikai.myframe.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.trello.rxlifecycle2.components.RxFragment;

/**
 * Created by Administrator on 2018/5/24.
 */

public abstract class BaseFragment<T extends IBasePresenter> extends RxFragment implements IBaseView<T> {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(attachLayoutId(), container, false);
        initView(view);
        initData();
        return view;
    }

    protected abstract void initView(View view);

    protected abstract void initData();

    protected abstract int attachLayoutId();

    @Override
    public void onShowLoading() {

    }

    @Override
    public void onHideLoading() {

    }

    @Override
    public void onShowNetError() {

    }

    @Override
    public void setPresenter(T presenter) {

    }

    @Override
    public <T> LifecycleTransformer<T> bindToLife(){
        return bindUntilEvent(FragmentEvent.DESTROY);
    }
}
