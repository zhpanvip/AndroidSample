package com.zhpan.sample.nestedscroll;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

/**
 * @author zhangpan
 * @date 2021/4/14
 */
class NestedFragmentAdapter extends FragmentStateAdapter {

    public NestedFragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return NestedFragment.getInstance();
        } else if (position == 1) {
            return NestedFragment.getInstance();
        } else {
            return NestedFragment.getInstance();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
