package com.yikai.myframe.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2018/6/27.
 */

public abstract class CommonAdapter<T> extends RecyclerView.Adapter<ViewHolder> {
    protected Context mContext;
    protected int mLayout;
    protected List<T> mDatas;
    public CommonAdapter(Context context, int layout, List data) {
        this.mContext = context;
        this.mLayout = layout;
        this.mDatas = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder = ViewHolder.getViewHolder(mContext, parent, mLayout);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        covert(holder, mDatas.get(position),position);
    }

    protected abstract void covert(ViewHolder holder, T t,int position);

    @Override
    public int getItemCount() {
        return mDatas.size();
    }


    @Override
    public int getItemViewType(int position) {
        T t = mDatas.get(position);
        return super.getItemViewType(position);
    }
}
