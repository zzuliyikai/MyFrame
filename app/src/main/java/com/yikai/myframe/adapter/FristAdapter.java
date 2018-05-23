package com.yikai.myframe.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yikai.myframe.R;

import java.util.List;

/**
 * Created by Administrator on 2018/5/23.
 */

public class FristAdapter extends  RecyclerView.Adapter<FristAdapter.ViewHolder>{
    private List<String> mLists;

    public FristAdapter(List lists){
        this.mLists = lists;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTv.setText(mLists.get(position));
    }

    @Override
    public int getItemCount() {
        return mLists.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        protected TextView mTv;
        public ViewHolder(View itemView) {
            super(itemView);
            mTv = itemView.findViewById(R.id.tv);
        }
    }
}
