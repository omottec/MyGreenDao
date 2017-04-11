package com.github.omottec.greendao.dao;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.omottec.greendao.R;
import com.github.omottec.greendao.recycler.ClickRecyclerAdapter;
import com.github.omottec.greendao.recycler.ClickViewHolder;
import com.github.omottec.greendao.recycler.OnItemClickListener;
import com.github.omottec.greendao.recycler.OnItemLongClickListener;

import java.util.ArrayList;
import java.util.List;


public class GoodsAdapter extends ClickRecyclerAdapter<GoodsAdapter.GoodViewHolder> {
    private List<Good> dataset;

    static class GoodViewHolder extends ClickViewHolder {

        public TextView text;
        public TextView desc;

        public GoodViewHolder(View itemView, OnItemClickListener onItemClickListener, OnItemLongClickListener onItemLongClickListener) {
            super(itemView, onItemClickListener, onItemLongClickListener);
            text = (TextView) itemView.findViewById(R.id.textViewGoodText);
            desc = (TextView) itemView.findViewById(R.id.textViewGoodDesc);
        }
    }

    public GoodsAdapter() {
        this.dataset = new ArrayList<Good>();
    }

    public void setGoods(@NonNull List<Good> goods) {
        dataset = goods;
        notifyDataSetChanged();
    }

    public Good getGood(int position) {
        return dataset.get(position);
    }

    @Override
    public GoodViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_note, parent, false);
        return new GoodViewHolder(view, this, this);
    }

    @Override
    public void onBindViewHolder(GoodViewHolder holder, int position) {
        Good good = dataset.get(position);
        holder.text.setText(good.getName());
        holder.desc.setText(good.getDesc());
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
