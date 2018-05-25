package com.yikai.myframe.base;

import com.trello.rxlifecycle2.LifecycleTransformer;

/**
 * Created by Administrator on 2018/5/24.
 */

public interface IBaseView<T> {

    /**
     * 显示加载
     */
    void onShowLoading();

    /**
     * 隐藏加载
     */
    void onHideLoading();

    /**
     * 显示网络加载错误
     */
    void onShowNetError();

    /**
     * 设置 presenter
     * @param presenter
     */
    void setPresenter(T presenter);

    /**
     * 绑定生命周期
     * @param <T>
     * @return
     */
    <T> LifecycleTransformer<T> bindToLife();

}
