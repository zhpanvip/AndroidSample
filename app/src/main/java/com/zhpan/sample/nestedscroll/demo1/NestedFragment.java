package com.zhpan.sample.nestedscroll.demo1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zhpan.sample.R;
import com.zhpan.sample.adapter.DataAdapter;

/**
 * @author zhangpan
 * @date 2021/4/14
 */
public class NestedFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View itemView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_nested, container, false);
        RecyclerView recyclerView = itemView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new DataAdapter());
        return itemView;
    }

    public static NestedFragment getInstance() {
        return new NestedFragment();
    }
}
