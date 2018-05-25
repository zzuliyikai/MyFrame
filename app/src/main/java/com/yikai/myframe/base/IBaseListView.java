package com.yikai.myframe.base;

import com.trello.rxlifecycle2.LifecycleTransformer;

/**
 * Created by Administrator on 2018/5/24.
 */

public interface IBaseListView<T> extends IBaseView<T> {

    /**
     * 显示加载
     */
    @Override
    void onShowLoading();

    /**
     * 隐藏加载
     */
    @Override
    void onHideLoading();

    /**
     * 显示网络加载错误
     */
    @Override
    void onShowNetError();

    /**
     * 设置 presenter
     * @param presenter
     */
    @Override
    void setPresenter(T presenter);

    /**
     * 绑定生命周期
     * @param <T>
     * @return
     */
    @Override
    <T> LifecycleTransformer<T> bindToLife();
}
