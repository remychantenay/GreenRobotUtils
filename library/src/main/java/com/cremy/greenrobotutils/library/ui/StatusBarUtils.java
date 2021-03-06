package com.cremy.greenrobotutils.library.ui;

import android.graphics.Color;
import android.os.Build;
import android.support.v4.graphics.ColorUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by remychantenay on 22/04/2016.
 */
public final class StatusBarUtils {
    private final static int SDK_LOLLIPOP = 21;
    private final static int SDK_JELLY_BEAN = 16;

    /**
     * Allows to change dynamically the status bar color
     * NOTE : Only available from API 21 (Lollipop)
     * @param _window
     * @param _color
     */
    public static void setStatusBarColor(Window _window, int _color) {
        if (Build.VERSION.SDK_INT >= SDK_LOLLIPOP) {
            _window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            _window.setStatusBarColor(_color);
        }
    }

    /**
     * Allows to change dynamically the status bar color
     * IMPORTANT : Will make automatically the color darker
     * To be different than the toolbar for instance
     * NOTE : Only available from API 21 (Lollipop)
     * @param _window
     * @param _color
     */
    public static void setDarkerStatusBarColor(Window _window, int _color) {
        if (Build.VERSION.SDK_INT >= SDK_LOLLIPOP) {
            _window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            float[] hsv = new float[3];
            Color.colorToHSV(_color, hsv);
            hsv[2] *= 0.8f; // value component
            _color = Color.HSVToColor(hsv);
            _window.setStatusBarColor(_color);
        }
    }


    /**
     * Allows to HIDE the status bar ONLY
     * @param _window
     */
    public static void hideStatusBar(Window _window) {
        if (Build.VERSION.SDK_INT < SDK_JELLY_BEAN)
        {
            _window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else {
            View decorView = _window.getDecorView();
            // Hide the status bar.
            int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }

    /**
     * Allows to DISPLAY the status bar
     * @param _window
     */
    public static void displayStatusBar(Window _window) {
        if (Build.VERSION.SDK_INT < SDK_JELLY_BEAN)
        {
            _window.setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        } else {
            View decorView = _window.getDecorView();
            // Hide the status bar.
            int uiOptions = View.SYSTEM_UI_FLAG_VISIBLE;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }

    /**
     * Allows to set the light status bar
     * NOTE : Only available from API 23 (Marshmallow)
     * @param _view
     */
    public void setLightStatusBar(View _view) {
        if (Build.VERSION.SDK_INT >= 23) {
            int flags = _view.getSystemUiVisibility();
            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            _view.setSystemUiVisibility(flags);
        }
    }

    /**
     * Allows to know if a given color is light or not
     * Useful if we want to know if we should apply the light status bar or not
     * @param _color
     * @return true if the given _color is light, false otherwise
     */
    public boolean isLight(int _color) {
        float[] hsl = new float[3];
        ColorUtils.colorToHSL(_color, hsl);
        return hsl[0] > 0.5f;
    }
}
