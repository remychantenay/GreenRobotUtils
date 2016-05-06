package com.cremy.greenrobotutils.library.ui.imageview;

import android.annotation.TargetApi;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.widget.ImageView;

import com.cremy.greenrobotutils.library.R;

/**
 * Created by remychantenay on 06/05/2016.
 */
public final class ImageViewUtils {

    /**
     * Allows to wrap a given drawable with a RippleDrawable and set it as image drawable
     * to the given imageView
     * IMPORTANT: If API < 21, the given drawable will be set instead
     * @param _imageView
     * @param _drawable
     * @param _color
     */
    @TargetApi(android.os.Build.VERSION_CODES.LOLLIPOP)
    public static void setImageRippleDrawable(ImageView _imageView, Drawable _drawable, final int _color) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            RippleDrawable rippledImage = new RippleDrawable(ColorStateList.valueOf(_color), _drawable, null);
            _imageView.setImageDrawable(rippledImage);
        } else {
            _imageView.setImageDrawable(_drawable);
        }
    }


    /**
     * Allows to wrap a given drawable with a RippleDrawable and set it as image drawable
     * to the given imageView
     * IMPORTANT: If API < 21, the given drawable will be set instead
     * @param _imageView
     * @param _drawable
     */
    @TargetApi(android.os.Build.VERSION_CODES.LOLLIPOP)
    public static void setImageRippleDrawable(ImageView _imageView, Drawable _drawable) {
        setImageRippleDrawable(_imageView, _drawable, _imageView.getContext().getResources().getColor(R.color.gru_default_ripple_drawable_color));
    }
}
