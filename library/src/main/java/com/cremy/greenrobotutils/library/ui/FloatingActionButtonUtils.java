package com.cremy.greenrobotutils.library.ui;

import android.content.res.ColorStateList;
import android.support.design.widget.FloatingActionButton;

/**
 * Created by remychantenay on 21/05/2016.
 */
public class FloatingActionButtonUtils {

    /**
     * Allows to set a simple background color to a given {@link FloatingActionButton}
     * @param fab
     * @param color
     */
    public static void setFabBackgroundColor(FloatingActionButton fab, int color) {
        int[][] states = {
                {android.R.attr.state_enabled},
                {android.R.attr.state_checked},
                {-android.R.attr.state_checked},
                {android.R.attr.state_pressed}
        };
        int[] colors = {
                color,
                color,
                color,
                color,
        };
        ColorStateList colorStateList = new ColorStateList(states, colors);
        fab.setBackgroundTintList(colorStateList);
    }
}
