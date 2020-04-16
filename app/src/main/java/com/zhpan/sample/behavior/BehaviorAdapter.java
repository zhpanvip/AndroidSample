package com.zhpan.sample.behavior;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zhpan.sample.R;

/**
 * <pre>
 *   Created by zhangpan on 2020/4/16.
 *   Description:
 * </pre>
 */


public class BehaviorAdapter extends RecyclerView.Adapter<BehaviorViewHolder> {
    @NonNull
    @Override
    public BehaviorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_behavior, parent, false);
        return new BehaviorViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull BehaviorViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 20;
    }
}
