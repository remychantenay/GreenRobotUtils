package com.cremy.greenrobotutils.library.ui;

import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * This class is a simple factory for the Android Design Support Library Snackbar component
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
     * @param _anchorView
     * @param _title
     */
    public static void showSimpleSnackbar(View _anchorView, String _title) {
        Snackbar.make(_anchorView, _title, Snackbar.LENGTH_LONG).show();
    }

    /**
     * Allows to display a SnackBar with an action
     * @param _anchorView
     * @param _title
     * @param _actionText
     * @param _listener
     */
    public static void showActionSnackbar(View _anchorView, String _title, String _actionText, View.OnClickListener _listener) {
        Snackbar.make(_anchorView, _title, Snackbar.LENGTH_LONG)
                .setAction(_actionText, _listener)
                .show();
    }

    /**
     * Allows to display a SnackBar with an action
     * @param _anchorView
     * @param _title
     * @param _actionText
     * @param _actionTextColor
     * @param _listener
     */
    public static void showActionSnackbar(View _anchorView, String _title, String _actionText, int _actionTextColor, View.OnClickListener _listener) {
        Snackbar.make(_anchorView, _title, Snackbar.LENGTH_LONG)
                .setAction(_actionText, _listener)
                .setActionTextColor(_actionTextColor)
                .show();
    }


    /**
     * Allows to display a SnackBar with an action
     * Note : Do not disappear until the user interact with the snackbar button
     * @param _anchorView
     * @param _title
     * @param _action
     * @param _listener
     */
    public static void showActionSnackbarIndefinite(View _anchorView, String _title, String _action, View.OnClickListener _listener) {
        Snackbar.make(_anchorView, _title, Snackbar.LENGTH_INDEFINITE)
                .setAction(_action, _listener)
                .show();
    }




}
