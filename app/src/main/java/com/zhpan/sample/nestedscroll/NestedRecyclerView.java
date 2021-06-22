package com.zhpan.sample.nestedscroll;

import android.content.Context;
import android.util.AttributeSet;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author zhangpan
 * @date 2021/6/22
 */
public class NestedRecyclerView extends RecyclerView {

  public NestedRecyclerView(@NonNull Context context) {
    super(context);
  }

  public NestedRecyclerView(@NonNull Context context,
      @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  public NestedRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs,
      int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }
}
