package com.smile.taobaodemo.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;

import com.smile.taobaodemo.R;

/**
 * @author Smile Wei
 * @since 2017/12/26.
 */

public class GradientScrollView extends NestedScrollView {
    private int headerHeight;
    private Toolbar toolbar;
    private Drawable headerDrawable;
    private Drawable statusBarDrawable;
    public GradientScrollView(Context context) {
        super(context);
    }

    public GradientScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GradientScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setHeader(Toolbar toolbar, View statusBar) {
        this.toolbar = toolbar;
        headerDrawable = toolbar.getBackground().mutate();
        statusBarDrawable = statusBar.getBackground().mutate();
        headerHeight = getContext().getResources().getDimensionPixelSize(R.dimen.default_scroll_gradient_height);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        float progress = (float) t / headerHeight;
        if (progress > 1f) progress = 1f;
        toolbar.setTitleTextColor(Color.argb((int) (255 * progress), 255, 255, 255));
        headerDrawable.setAlpha((int) (255 * progress));
        statusBarDrawable.setAlpha((int) (255 * progress));
    }
}
