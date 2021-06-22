package com.zhpan.sample.nestedscroll;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.LogUtils;

/**
 * @author zhangpan
 * @date 2021/4/14
 */
public class NestedScrollLayout extends NestedScrollView {

  private final FlingHelper mFlingHelper;
  private View topView;

  private ViewGroup contentView;

  private RecyclerView mRecyclerView;
  private int velocityY;
  private boolean isFling;
  private int totalDy;
  private OnNestedScrollChangeListener mNestedScrollChangeListener;

  public NestedScrollLayout(@NonNull Context context) {
    this(context, null);
  }

  public NestedScrollLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

  @Override protected void onScrollChanged(int l, int t, int oldl, int oldt) {
    super.onScrollChanged(l, t, oldl, oldt);
    if (mNestedScrollChangeListener != null) {
      mNestedScrollChangeListener.onScrollChanged(this, l, t, oldl, oldt);
    }
  }

  public NestedScrollLayout(@NonNull Context context, @Nullable AttributeSet attrs,
      int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    mFlingHelper = new FlingHelper(getContext());
    setOnNestedScrollChangeListener((scrollView, scrollX, scrollY, oldScrollX, oldScrollY) -> {
      if (isFling) {
        totalDy = 0;
        isFling = false;
      }
      if (scrollY == (getChildAt(0).getMeasuredHeight() - scrollView.getMeasuredHeight())) {
        dispatchChildFling();
      }
      totalDy += scrollY - oldScrollY;
    });
  }

  private void dispatchChildFling() {
    if (velocityY != 0) {
      double splineFlingDistance = mFlingHelper.getSplineFlingDistance(velocityY);
      if (splineFlingDistance > totalDy) {
        childFling(mFlingHelper.getVelocityByDistance(splineFlingDistance - totalDy));
      }
      totalDy = 0;
      velocityY = 0;
    }
  }

  private void childFling(int velocityY) {
    if (mRecyclerView != null) {
      mRecyclerView.fling(0, velocityY);
    }
  }

  @Override
  public void fling(int velocityY) {
    super.fling(velocityY);
    if (velocityY <= 0) {
      this.velocityY = 0;
    } else {
      isFling = true;
      this.velocityY = velocityY;
    }
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    if (contentView != null) {
      ViewGroup.LayoutParams layoutParams = contentView.getLayoutParams();
      layoutParams.height = getMeasuredHeight();
    }
  }

  @Override
  protected void onFinishInflate() {
    super.onFinishInflate();
    ViewGroup parentView = (ViewGroup) getChildAt(0);
    if (parentView != null && parentView.getChildCount() == 2) {
      topView = parentView.getChildAt(0);
      contentView = (ViewGroup) parentView.getChildAt(1);
      if (contentView != null) {
        mRecyclerView = tryToGetRecyclerView(contentView);
      }
    }
  }

  private RecyclerView tryToGetRecyclerView(ViewGroup viewGroup) {
    for (int i = 0; i < viewGroup.getChildCount(); i++) {
      View view = viewGroup.getChildAt(i);
      if (view instanceof RecyclerView && view.getClass() == NestedRecyclerView.class) {
        return (RecyclerView) viewGroup.getChildAt(i);
      } else if (viewGroup.getChildAt(i) instanceof ViewGroup) {
        RecyclerView childRecyclerView = tryToGetRecyclerView((ViewGroup) viewGroup.getChildAt(i));
        if (childRecyclerView != null) {
          return childRecyclerView;
        }
      }
    }
    return null;
  }



  @Override
  public void onNestedPreScroll(@NonNull View target, int dx, int dy, @NonNull int[] consumed) {
    super.onNestedPreScroll(target, dx, dy, consumed);
  }

  @Override
  public void onNestedPreScroll(@NonNull View target, int dx, int dy, @NonNull int[] consumed,
      int type) {
    // dy>0表示手指向上滑动，如果topView可见，则将其滑动至不可见
    boolean hideTop = dy > 0 && getScrollY() < topView.getMeasuredHeight();
    if (hideTop) {
      // 使用scrollBy滑动NestedScrollLayout
      scrollBy(0, dy);
      // 消费此次滑动
      consumed[1] = dy;
    }
    super.onNestedPreScroll(target, dx, dy, consumed, type);
  }

  public void setOnNestedScrollChangeListener(
      OnNestedScrollChangeListener mScrollViewListener) {
    this.mNestedScrollChangeListener = mScrollViewListener;
  }

  public interface OnNestedScrollChangeListener {
    void onScrollChanged(NestedScrollLayout scrollView, int scrollX, int scrollY, int oldScrollX,
        int oldScrollY);
  }
}
