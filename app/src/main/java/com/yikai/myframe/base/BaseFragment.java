package com.yikai.myframe.base;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.trello.rxlifecycle2.components.RxFragment;

/**
 * Created by Administrator on 2018/5/24.
 */

public abstract class BaseFragment<T extends IBasePresenter> extends RxFragment implements IBaseView<T> {


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
