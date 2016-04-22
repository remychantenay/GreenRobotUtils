package com.cremy.greenrobotutils.library.ui;

import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by remychantenay on 22/04/2016.
 */
public final class ActivityUtils {
    private final static int SDK_KITKAT = 19;
    private static final int SYSTEM_UI_FLAG_IMMERSIVE = 2048; // Only available >= 4.4 (KitKat)

    /**
     * Allows to set the activity in full screen mode
     *  (No ToolBar and No Notification bar)
     * @param _activity
     */
    public static void setFullScreen(Activity _activity)
    {
        _activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
        _activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    /**
     * Allows to force the screen to stay ON for a given activity
     * @param _activity
     */
    public static void setFlagKeepScreenOn(Activity _activity) {
        _activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }



    /** Allow to set the full immersive mode of an activity
     * NOTE : Only works for <= 4.4 (KitKat)
     * @see http://developer.android.com/training/system-ui/immersive.html
     * @param _activity
     */
    public static void setFullImmersiveMode(Activity _activity) {

        if (Build.VERSION.SDK_INT >= SDK_KITKAT)
        {
            // Set the IMMERSIVE flag.
            // Set the content to appear under the system bars so that the content
            // doesn't resize when the system bars hide and show.
            _activity.getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                            | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                            | SYSTEM_UI_FLAG_IMMERSIVE);
        }
    }


    /**
     * Allows to set the full immersive mode of an activity
     * NOTE : Only works for <= 4.4 (KitKat)
     * @see http://developer.android.com/training/system-ui/immersive.html
     * @param _activity
     */
    public static void hideNavigationBar(Activity _activity) {

        if (Build.VERSION.SDK_INT >= SDK_KITKAT)
        {
            // Set the IMMERSIVE flag.
            // Set the content to appear under the system bars so that the content
            // doesn't resize when the system bars hide and show.
            _activity.getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                            | SYSTEM_UI_FLAG_IMMERSIVE);
        }
    }

    /**
     * Allows to cancel the animation during the close event of a given activity
     * @param _activity
     */
    public static void cancelCloseAnimation(Activity _activity) {
        // We just avoid the activity close animation
        _activity.overridePendingTransition(0, 0);
    }


    /**
     * Allows to set the content of the view resizable when the soft keyboard appear
     * @param _activity
     */
    public static void setResizableContentWhenKeyboardAppear(Activity _activity) {
        _activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
    }
}
