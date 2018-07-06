package com.yikai.myframe.utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yikai.myframe.R;

import java.util.List;

/**
 * Created by Administrator on 2018/7/6.
 */

class MyAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<String> mList;
    private ItemOnClickListener mItemOnClickListener;
    public MyAdapter(Context context, List<String> list) {
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(inflate);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        myViewHolder.tv1.setText(mList.get(position));

        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemOnClickListener != null){
                    mItemOnClickListener.ItemOnclickListener(v,position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{

        protected TextView tv1;
        public MyViewHolder(View itemView) {
            super(itemView);
            tv1 = itemView.findViewById(R.id.tv1);
        }
    }

    public void setItemOnClickListener(ItemOnClickListener itemOnClickListener){
        this.mItemOnClickListener = itemOnClickListener;
    }

    interface ItemOnClickListener{

        void ItemOnclickListener(View v,int position);


    }


}
