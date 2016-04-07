package com.cremy.greenrobotutils.library.manufacturer.samsung;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

/**
 * Allows to use the Samsung BadgeProvider (Notifications badge counter on TouchWizz)
 * IMPORTANT :
 * In order to use this provider the app need these permissions (in the manifest)
 * <uses-permission android:name="com.sec.android.provider.badge.permission.READ" />
 * <uses-permission android:name="com.sec.android.provider.badge.permission.WRITE" />
 *
 * Created by remychantenay on 04/11/2015.
 */
public final class SamsungBadgeHelper {
    private final static String TAG = "SamsungBadgeHelper";
    private final static String BRAND_NAME = "samsung";
    private final static String PROVIDER_URI = "content://com.sec.badge/apps";

    private final static String CONTENT_VALUE_KEY_PACKAGE = "package";
    private final static String CONTENT_VALUE_KEY_CLASS = "class";
    private final static String CONTENT_VALUE_KEY_BADGECOUNT = "badgecount";

    /**
     * Allows to SET a badge count using the Samsung Badge Provider
     * @param _context
     * @param _className (Name of your activity declared in the manifest as android.intent.action.MAIN)
     * @param _value
     * @return true if everything worked fine, false otherwise
     */
    public static boolean setBadgeCount(Context _context, String _className, int _value) {

        try {
            if (Build.BRAND.equalsIgnoreCase(BRAND_NAME)) {
                Log.i(TAG, "Device is a Samsung device. We can use the BadgeProvider.");
                ContentValues cv = new ContentValues();
                cv.put(CONTENT_VALUE_KEY_PACKAGE, _context.getPackageName());
                cv.put(CONTENT_VALUE_KEY_CLASS, _className);
                cv.put(CONTENT_VALUE_KEY_BADGECOUNT, _value);

                try {
                    if (_context.getContentResolver().insert(Uri.parse(PROVIDER_URI), cv) != null) {
                        return true;
                    }
                } catch (IllegalArgumentException e) {
                    Log.e(TAG, e.getMessage());
                }
            } else {
                Log.w(TAG, "Device is not a Samsung device. We can't use the BadgeProvider | " + Build.BRAND);
            }
            return false;
        }
        catch (SecurityException e) {
            e.printStackTrace();
        }

        return false;
    }


    /**
     * Allows to CLEAN the badge count using the Samsung Badge Provider
     * @param _context
     * @return true if everything worked fine, false otherwise
     */
    public static boolean cleanBadgeCount(Context _context) {
        try {
            if (Build.BRAND.equalsIgnoreCase(BRAND_NAME)) {
                Log.i(TAG, "Device is a Samsung device. We can use the BadgeProvider");
                ContentValues cv = new ContentValues();
                cv.put(CONTENT_VALUE_KEY_BADGECOUNT, 0);
                try {
                    if (_context.getContentResolver().update(Uri.parse(PROVIDER_URI), cv, "package=?", new String[] {_context.getPackageName()}) != -1) {
                        return true;
                    }
                } catch (IllegalArgumentException e) {
                    Log.e(TAG, e.getMessage());
                }
            } else {
                Log.w(TAG, "Device is not a Samsung device. We can't use the BadgeProvider | " + Build.BRAND);
            }
            return false;
        }
        catch (SecurityException e) {
            e.printStackTrace();
        }
        return false;
    }
}
