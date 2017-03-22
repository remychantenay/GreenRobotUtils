package com.cremy.greenrobotutils.library.ui;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.cremy.greenrobotutils.library.R;

/**
 * Created by remychantenay on 22/04/2016.
 */
public final class ActivityUtils {
    private final static int SDK_KITKAT = 19;
    private static final int SYSTEM_UI_FLAG_IMMERSIVE = 2048; // Only available >= 4.4 (KitKat)

    /**
     * Allows to set the activity in full screen mode
     *  (No ToolBar and No Notification bar)
     * @param activity
     */
    public static void setFullScreen(@NonNull Activity activity) throws IllegalArgumentException
    {
        if (activity == null) {
            throw new IllegalArgumentException("The provided activity can't be null.");
        }
        activity.requestWindowFeature(Window.FEATURE_NOtitle);
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    /**
     * Allows to force the screen to stay ON for a given activity
     * @param activity
     */
    public static void setFlagKeepScreenOn(@NonNull Activity activity) throws IllegalArgumentException {
        if (activity == null) {
            throw new IllegalArgumentException("The provided activity can't be null.");
        }
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }



    /** Allow to set the full immersive mode of an activity
     * NOTE : Only works for <= 4.4 (KitKat)
     * @see http://developer.android.com/training/system-ui/immersive.html
     * @param activity
     */
    public static void setFullImmersiveMode(Activity activity) throws IllegalArgumentException {
        if (activity == null) {
            throw new IllegalArgumentException("The provided activity can't be null.");
        }

        if (Build.VERSION.SDK_INT >= SDK_KITKAT)
        {
            // Set the IMMERSIVE flag.
            // Set the content to appear under the system bars so that the content
            // doesn't resize when the system bars hide and show.
            activity.getWindow().getDecorView().setSystemUiVisibility(
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
     * @param activity
     */
    public static void hideNavigationBar(Activity activity)
            throws IllegalArgumentException {
        if (activity == null) {
            throw new IllegalArgumentException("The provided activity can't be null.");
        }

        if (Build.VERSION.SDK_INT >= SDK_KITKAT)
        {
            // Set the IMMERSIVE flag.
            // Set the content to appear under the system bars so that the content
            // doesn't resize when the system bars hide and show.
            activity.getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                            | SYSTEM_UI_FLAG_IMMERSIVE);
        }
    }

    /**
     * Allows to cancel the animation during the close event of a given activity
     * @param activity
     */
    public static void cancelCloseAnimation(@NonNull Activity activity)
            throws IllegalArgumentException {
        if (activity == null) {
            throw new IllegalArgumentException("The provided activity can't be null.");
        }

        // We just avoid the activity close animation
        activity.overridePendingTransition(0, 0);
    }


    /**
     * Allows to set the content of the view resizable when the soft keyboard appear
     * @param activity
     */
    public static void setResizableContentWhenKeyboardAppear(@NonNull Activity activity)
            throws IllegalArgumentException {
        if (activity == null) {
            throw new IllegalArgumentException("The provided activity can't be null.");
        }

        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
    }


    /**
     * Allows to change the toolbar back arrow and set a _white_ one
     * @param context
     * @param actionBar
     */
    public static void setToolbarCustomWhiteBackArrow(Context context, ActionBar actionBar) {
        actionBar.setDisplayHomeAsUpEnabled(true);
        final Drawable upArrow = ContextCompat.getDrawable(context, R.drawable.ic_action_back_arrow_white);
        actionBar.setHomeAsUpIndicator(upArrow);
    }

    /**
     * Allows to change the toolbar back arrow and set a _black_ one
     * @param context
     * @param actionBar
     */
    public static void setToolbarCustomBlackBackArrow(Context context, ActionBar actionBar) {
        actionBar.setDisplayHomeAsUpEnabled(true);
        final Drawable upArrow = ContextCompat.getDrawable(context, R.drawable.ic_action_back_arrow_black);
        actionBar.setHomeAsUpIndicator(upArrow);
    }


    /**
     * Allows to change the toolbar hamburger/drawer menu icon to a _white_ one
     * @param context
     * @param actionBarDrawerToggle
     */
    public static void setToolbarCustomWhiteMenuIcon(Context context,
                                                     ActionBarDrawerToggle actionBarDrawerToggle) {
        actionBarDrawerToggle.setDrawerIndicatorEnabled(false);
        actionBarDrawerToggle.setHomeAsUpIndicator(ContextCompat.getDrawable(context, R.drawable.ic_menu_white_24dp));
    }


    /**
     * Allows to apply a fragment into a container view (view a given view id, e.g R.id.container)
     * @param activity
     * @param fragment
     * @param idView
     */
    public static void applyFragment(FragmentActivity activity, Fragment fragment, int idView)
    {
        android.support.v4.app.FragmentManager fragmentManager = activity.getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(idView, fragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();
    }

    /**
     * Allows to add a fragment into a container view (view a given view id, e.g R.id.container)
     * @param activity
     * @param fragment
     * @param idView
     * @param fragmentTag
     */
    public static void addFragment(FragmentActivity activity,
                                   Fragment fragment,
                                   int idView,
                                   String fragmentTag)
    {
        android.support.v4.app.FragmentManager fragmentManager = activity.getSupportFragmentManager();
        fragmentManager.beginTransaction().add(idView, fragment, fragmentTag).commit();
    }

    /**
     * Allows to get a fragment with a given tag
     * @param activity
     * @param fragmentTag
     */
    public static Fragment getFragmentByTag(FragmentActivity activity, String fragmentTag)
    {
        android.support.v4.app.FragmentManager fragmentManager = activity.getSupportFragmentManager();
        return fragmentManager.findFragmentByTag(fragmentTag);
    }
}
