package com.cremy.greenrobotutils.library.device;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * This class allows to get some information about the current app
 * @author CHANTENAY Remy
 * @mail remy.chantenay at gmail.com
 */
public final class CurrentApp {

    /**
     * Allows to get the current app version name
     * (e.g. 1.54)
     * @param _context
     * @return 0 if context is NULL or PackageManager.NameNotFoundException raised
     */
    public static String getAppVersionName(Context _context) {
        String result = "0";
        if(_context == null) {
            return result;
        }

        PackageInfo pInfo;
        try {
            pInfo = _context.getPackageManager().getPackageInfo(_context.getPackageName(), 0);
            return pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            return result;
        }
    }

    /**
     * Allows to get the current app version name
     * (e.g. 1054)
     * @param _context
     * @return 0 if context is NULL or PackageManager.NameNotFoundException raised
     */
    public static int getAppVersionCode(Context _context) {
        int result = 0;
        if(_context == null) {
            return result;
        }

        PackageInfo pInfo;
        try {
            pInfo = _context.getPackageManager().getPackageInfo(_context.getPackageName(), 0);
            return pInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            return result;
        }
    }
}
