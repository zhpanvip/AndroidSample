package com.zhpan.sample.viewpager2.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.integration.testapp.cards.Card;

import com.zhpan.sample.viewpager2.fragment.TestFragment;

/**
 * <pre>
 *   Created by zhpan on 2019/12/21.
 *   Description:
 * </pre>
 */
public class VPFragmentAdapter extends FragmentStatePagerAdapter {


    public VPFragmentAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return TestFragment.Companion.getInstance(Card.Companion.getDECK().get(position),position);
    }

    @Override
    public int getCount() {
        return Card.Companion.getDECK().size();
    }
}
