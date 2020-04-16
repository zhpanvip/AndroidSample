package com.zhpan.sample.behavior;

import android.animation.ArgbEvaluator;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.Nullable;

import com.blankj.utilcode.util.BarUtils;
import com.zhpan.sample.R;
/**
 * <pre>
 *   Created by zhangpan on 2020/4/13.
 *   Description: 跟随RecyclerView或ScrollView滚动颜色渐变的Toolbar。
 *   必须用在{@link androidx.coordinatorlayout.widget.CoordinatorLayout}中
 *   并且必须为{@link BehaviorToolbar}添加{@link ToolbarBehavior}作为layout_behavior，颜色渐变才有效。
 *   关于状态栏:Activity的them需要设置为R.style.ranslucentStatus
 * </pre>
 */
public class BehaviorToolbar extends RelativeLayout {
    private View mStatusBar;
    private RelativeLayout mTitleBar;
    private View mViewLine;
    private ImageView mNavigation;
    private TextView mTitle;
    private @ColorInt
    int titleBarColor;
    private @ColorInt
    int lightTitleColor;
    private @ColorInt
    int lineColor;
    private @ColorInt
    int darkTitleColor;
    private @ColorInt
    int statusBarColor;

    private int navigationIcon;
    private int darkNavigationIcon;
    private int headerHeight;
    private ArgbEvaluator mArgbEvaluator;

    public BehaviorToolbar(Context context) {
        this(context, null);
    }

    public BehaviorToolbar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BehaviorToolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.behavior_tool_bar, this);
        mNavigation = findViewById(R.id.navigation);
        mViewLine = findViewById(R.id.tool_bar_line);
        mStatusBar = findViewById(R.id.status_bar);
        mTitleBar = findViewById(R.id.title_layout);
        mTitle = findViewById(R.id.toolbar_title);
        mArgbEvaluator = new ArgbEvaluator();
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BehaviorToolbar);
            statusBarColor = typedArray.getColor(R.styleable.BehaviorToolbar_atb_status_bar_color,
                    getColor(R.color.status_bar_color));
            darkTitleColor = typedArray.getColor(R.styleable.BehaviorToolbar_atb_dark_title_color,
                    getColor(R.color.dark_gray));
            lightTitleColor = typedArray.getColor(R.styleable.BehaviorToolbar_atb_light_title_color,
                    Color.TRANSPARENT);
            titleBarColor = typedArray.getColor(R.styleable.BehaviorToolbar_atb_title_bar_color,
                    getColor(R.color.white));
            navigationIcon = typedArray.getResourceId(R.styleable.BehaviorToolbar_atb_navigationIcon,
                    R.drawable.ic_action_back_white);
            darkNavigationIcon = typedArray.getResourceId(
                    R.styleable.BehaviorToolbar_atb_darkNavigationIcon, R.drawable.ic_action_back);
            lineColor = typedArray.getColor(R.styleable.BehaviorToolbar_atb_line_color,
                    getColor(R.color.background));
            typedArray.recycle();
        }
        mNavigation.setImageResource(navigationIcon);
        mTitle.setTextColor(lightTitleColor);
        ViewGroup.LayoutParams layoutParams = mStatusBar.getLayoutParams();
        layoutParams.height = BarUtils.getStatusBarHeight();
    }

    private @ColorInt
    int getColor(@ColorRes int colorRes) {
        return getResources().getColor(colorRes);
    }

    public void setTitle(CharSequence title) {
        mTitle.setText(title);
    }

    public void setStatusBarVisibility(int visibility) {
        mStatusBar.setVisibility(visibility);
    }

    public void setNavigationOnClickListener(OnClickListener onClickListener) {
        mNavigation.setOnClickListener(onClickListener);
    }

    public int getHeaderHeight() {
        return headerHeight;
    }

    public void setHeaderHeight(int headerHeight) {
        this.headerHeight = headerHeight;
    }

    public void setupWithHeader(final View view) {
        if (view != null) {
            view.getViewTreeObserver()
                    .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                        @Override
                        public void onGlobalLayout() {
                            view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                            headerHeight = view.getHeight() - mTitleBar.getHeight();
                        }
                    });
        }
    }

    public void setStatusBarColor(int statusBarColor) {
        this.statusBarColor = statusBarColor;
    }

    public void setNavigationIcon(int navigationIcon) {
        this.navigationIcon = navigationIcon;
        mNavigation.setImageResource(navigationIcon);
    }

    public int getNavigationIcon() {
        return navigationIcon;
    }


    /****************************** 以下方法仅提供给ToolbarBehavior使用 *********************************/

    void setToolbarTransparent() {
        mTitleBar.setBackgroundColor(Color.TRANSPARENT);
        mStatusBar.setBackgroundColor(Color.TRANSPARENT);
        mTitle.setTextColor(lightTitleColor);
        mViewLine.setBackgroundColor(Color.TRANSPARENT);
    }

    void setToolbarFullColor(Context context) {
        mTitleBar.setBackgroundColor(titleBarColor);
        mStatusBar.setBackgroundColor(statusBarColor);
        mTitle.setTextColor(darkTitleColor);
        mNavigation.setImageResource(darkNavigationIcon);
        mViewLine.setBackgroundColor(lineColor);
        if (context instanceof Activity) {
            BarUtils.setStatusBarLightMode((Activity) context, false);
        }
    }

    void changingColorGradually(Context context, float fraction) {
        mStatusBar.setBackgroundColor(changeAlpha(fraction, statusBarColor));
        mTitleBar.setBackgroundColor(changeAlpha(fraction, titleBarColor));
        mTitle.setTextColor(colorEvaluator(fraction, lightTitleColor, darkTitleColor));
        mViewLine.setBackgroundColor(changeAlpha(fraction, lineColor));
        double TEXT_COLOR_ALPHA = 0.75;
        if (fraction < TEXT_COLOR_ALPHA) {
            mNavigation.setImageResource(navigationIcon);
            if (context instanceof Activity) {
                BarUtils.setStatusBarLightMode((Activity) context, true);
            }
        } else {
            mNavigation.setImageResource(darkNavigationIcon);
            if (context instanceof Activity) {
                BarUtils.setStatusBarLightMode((Activity) context, false);
            }
        }
    }

    private int changeAlpha(float fraction, int color) {
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        int alpha = (int) (Color.alpha(color) * fraction);
        return Color.argb(alpha, red, green, blue);
    }

    private int colorEvaluator(float fraction, int startColor, int endColor) {
        return (int) mArgbEvaluator.evaluate(fraction, startColor, endColor);
    }

}
