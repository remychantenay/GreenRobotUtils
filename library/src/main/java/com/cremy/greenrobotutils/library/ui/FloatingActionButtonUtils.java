package com.cremy.greenrobotutils.library.ui;

import android.content.res.ColorStateList;
import android.support.design.widget.FloatingActionButton;

/**
 * Created by remychantenay on 21/05/2016.
 */
public class FloatingActionButtonUtils {

    /**
     * Allows to set a simple background color to a given {@link FloatingActionButton}
     * @param _fab
     * @param _color
     */
    public static void setFabBackgroundColor(FloatingActionButton _fab, int _color) {
        int[][] states = {
                {android.R.attr.state_enabled},
                {android.R.attr.state_checked},
                {-android.R.attr.state_checked},
                {android.R.attr.state_pressed}
        };
        int[] colors = {
                _color,
                _color,
                _color,
                _color,
        };
        ColorStateList colorStateList = new ColorStateList(states, colors);
        _fab.setBackgroundTintList(colorStateList);
    }
}
