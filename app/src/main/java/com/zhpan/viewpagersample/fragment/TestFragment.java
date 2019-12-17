package com.zhpan.viewpagersample.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.integration.testapp.cards.Card;
import androidx.viewpager2.integration.testapp.cards.CardView;

/**
 * <pre>
 *   Created by zhangpan on 2019-12-17.
 *   Description:
 * </pre>
 */
public class TestFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        CardView cardView = new CardView(inflater, container);
        cardView.bind(Card.Companion.fromBundle(getArguments()));
        return cardView.getView();
    }

    public static TestFragment getInstance(Card card) {
        TestFragment fragment = new TestFragment();
        Bundle bundle = card.toBundle();
        fragment.setArguments(bundle);
        return fragment;
    }
}
