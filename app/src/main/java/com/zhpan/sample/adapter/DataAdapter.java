package com.zhpan.sample.adapter;

import com.zhpan.bannerview.BaseBannerAdapter;
import com.zhpan.bannerview.BaseViewHolder;
import com.zhpan.library.model.Article;
import com.zhpan.sample.R;

/**
 * <pre>
 *   Created by zhangpan on 2020/4/16.
 *   Description:
 * </pre>
 */

public class DataAdapter extends BaseBannerAdapter<Article> {

  public DataAdapter() {
    for (int i = 0; i < 20; i++) {
      mList.add(new Article("Item " + i));
    }
  }

  @Override protected void bindData(BaseViewHolder<Article> holder, Article data, int position,
      int pageSize) {
    holder.setText(R.id.tv_title, data.getTitle());
  }

  @Override public int getLayoutId(int viewType) {
    return R.layout.item_behavior;
  }
}
