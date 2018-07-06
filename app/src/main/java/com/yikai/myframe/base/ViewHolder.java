package com.yikai.myframe.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2018/6/27.
 */

public class ViewHolder extends RecyclerView.ViewHolder {

    private Context mContext;
    private View mConvertView;
    private SparseArray<View> mViews;

    public ViewHolder(Context context, View itemView, ViewGroup parent) {
        super(itemView);
        this.mContext = context;
        this.mConvertView = itemView;
        mViews = new SparseArray<View>();
    }

    public static ViewHolder getViewHolder(Context context,ViewGroup parent,int layoutid){
        View inflate = LayoutInflater.from(context).inflate(layoutid, parent, false);
        ViewHolder viewHolder = new ViewHolder(context,inflate,parent);
        return viewHolder;
    }

    public <T extends View> T getView(int viewId){
        View view = mViews.get(viewId);
        if (view == null){
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId,view);
        }
        return (T) view;
    }




    public ViewHolder setOnClickListener(int viewId,
                                         View.OnClickListener listener)
    {
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }
}
