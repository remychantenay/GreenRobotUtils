package com.cremy.greenrobotutils.library.ui.imageview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * SquareImageView allows to get a perfectly squared imageview which is useful mainly in GridViews
 * Created by remychantenay on 24/12/2015.
 */
public final class SquareImageView extends ImageView {
    public SquareImageView(Context context) {
        super(context);
    }

    public SquareImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredWidth(), getMeasuredWidth()); //Snap to width
    }
}
