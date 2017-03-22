package com.cremy.greenrobotutils.library.ui;

import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * This class is a simple factory for the Android Design Support Library {@link Snackbar} component
 * @link http://android-developers.blogspot.co.uk/2015/05/android-design-support-library.html
 * Created by remychantenay on 24/06/2015.
 */
public final class SnackBarUtils {

    /**
     * This is the duration of Snackbar.LENGTH_LONG
     * 3000ms, 3seconds
     */
    public static final int DEFAULT_LENGTH_LONG_DURATION = 3000; // Expressed in ms

    /**
     * Allows to display a simple SnackBar (no action)
     * @param anchorView
     * @param title
     */
    public static void showSimpleSnackbar(View anchorView, String title) {
        Snackbar.make(anchorView, title, Snackbar.LENGTH_LONG).show();
    }

    /**
     * Allows to display a {@link Snackbar} with an action
     * @param anchorView
     * @param title
     * @param actionText
     * @param callback
     */
    public static void showActionSnackbar(View anchorView,
                                          String title,
                                          String actionText,
                                          View.OnClickListener callback) {
        Snackbar.make(anchorView, title, Snackbar.LENGTH_LONG)
                .setAction(actionText, callback)
                .show();
    }

    /**
     * Allows to display a {@link Snackbar} with an action
     * @param anchorView
     * @param title
     * @param actionText
     * @param actionTextColor
     * @param callback
     */
    public static void showActionSnackbar(View anchorView,
                                          String title,
                                          String actionText,
                                          int actionTextColor,
                                          View.OnClickListener callback) {
        Snackbar.make(anchorView, title, Snackbar.LENGTH_LONG)
                .setAction(actionText, callback)
                .setActionTextColor(actionTextColor)
                .show();
    }


    /**
     * Allows to display a SnackBar with an action
     * Note : Do not disappear until the user interact with the {@link Snackbar} button
     * @param anchorView
     * @param title
     * @param _action
     * @param callback
     */
    public static void showActionSnackbarIndefinite(View anchorView,
                                                    String title,
                                                    String _action,
                                                    View.OnClickListener callback) {
        Snackbar.make(anchorView, title, Snackbar.LENGTH_INDEFINITE)
                .setAction(_action, callback)
                .show();
    }




}
