package com.cremy.greenrobotutils.library.storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * StringStorageManager
 *
 * This class allows to save String data really easily using the SharedPreferences
 * Created by remychantenay on 11/04/2016.
 */
public final class StringStorageManager {

    //region String
    /**
     * Allows to _SAVE_ some data using simple String format
     *
     * @param _context
     * @param _key
     * @param _value
     * @return True if the data has been successfully saved, false otherwise
     */
    public static void setString(Context _context, final String _key, final String _value)
    {
        final SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(_context);
        SharedPreferences.Editor ed = mPrefs.edit();
        ed.putString(_key, _value);
        ed.apply();
    }

    /**
     * Allows to _DELETE_ an element into the shared preferences
     *
     * @param _context
     * @param _key
     * @return True if the data has been successfully deleted, false otherwise
     */
    public static void remove(Context _context, final String _key)
    {
        final SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(_context);
        SharedPreferences.Editor ed = mPrefs.edit();
        ed.putString(_key, "");
        ed.apply();
    }
    //endregion
}
