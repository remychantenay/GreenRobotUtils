package com.cremy.greenrobotutils.library.storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Remy on 28/04/14.
 */
public class PreferencesManager {

    //region Int
    /**
     * Allows to _set_ an Int value into the SharedPreferences.
     * @param _context
     * @param _isAsync -> if true, the returned value will be true as the writing is not synchronous
     * @param _key
     * @param _value
     * @return true if the write is a success, false otherwise (except if _isAsync = true)
     */
    public static boolean setInt(Context _context,
                                 final boolean _isAsync,
                                 final String _key,
                                 int _value) {
        try {
            SharedPreferences.Editor ed = getWriteInstance(_context);
            ed.putInt(_key, _value);
            if (_isAsync) {
                ed.apply();
                return true;
            } else {
                return ed.commit();
            }
        } catch (NullPointerException e)
        {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Allows to _get_ an int value from the SharedPreferences
     * @param _context
     * @param _key
     * @return 0 if nothing found
     */
    public static int getInt(Context _context, String _key)
    {
        int value = 0;
        try {
            final SharedPreferences mPrefs = getReadInstance(_context);
            value = mPrefs.getInt(_key, 0);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return value;
    }
    //endregion

    //region String
    /**
     * Allows to _set_ a String value into the SharedPreferences
     * @param _context
     * @param _isAsync -> if true, the returned value will be true as the writing is not synchronous
     * @param _key
     * @param _value
     * @return true if the write is a success, false otherwise (except if _isAsync = true)
     */
    public static boolean setString(Context _context,
                                    final boolean _isAsync,
                                    final String _key,
                                    final String _value) {
        try {
            SharedPreferences.Editor ed = getWriteInstance(_context);
            ed.putString(_key, _value);
            if (_isAsync) {
                ed.apply();
                return true;
            } else {
                return ed.commit();
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Allows to _get_ a String value from the SharedPreferences
     * @param _context
     * @param _key
     * @return the String, null if not found
     */
    public static String getString(Context _context, final String _key)
    {
        String value = null;
        try {
            final SharedPreferences mPrefs = getReadInstance(_context);
            value = mPrefs.getString(_key, "");
            if (value.isEmpty()) {
                return null;
            }
            return value;
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return value;
    }
    //endregion

    //region Long
    /**
     * Allows to _set_ a long value into the SharedPreferences
     * @param _context
     * @param _isAsync -> if true, the returned value will be true as the writing is not synchronous
     * @param _key
     * @param _value
     * @return true if the write is a success, false otherwise (except if _isAsync = true)
     */
    public static boolean setLong(Context _context,
                                  final boolean _isAsync,
                                  final String _key,
                                  final Long _value) {
        try {
            SharedPreferences.Editor ed = getWriteInstance(_context);
            ed.putLong(_key, _value);
            if (_isAsync) {
                ed.apply();
                return true;
            } else {
                return ed.commit();
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Allows to _get_ a long value from the SharedPreferences
     * @param _context
     * @param _key
     * @return 0 if not found
     */
    public static long getLong(Context _context, final String _key)
    {
        long value = 0;
        try {
            final SharedPreferences mPrefs = getReadInstance(_context);
            value = mPrefs.getLong(_key, 0);
            return value;
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return value;
    }
    //endregion

    //region Boolean

    /*
    * Allows to _set_ a boolean value into the SharedPreferences
     * @param _context
     * @param _isAsync -> if true, the returned value will be true as the writing is not synchronous
     * @param _key
     * @param _value
    * @return true if the write is a success, false otherwise (except if _isAsync = true)
    */
    public static boolean setBoolean(Context _context,
                                          final boolean _isAsync,
                                          final String _key,
                                          final boolean _value) {
        try {
            SharedPreferences.Editor ed = getWriteInstance(_context);
            ed.putBoolean(_key, _value);
            if (_isAsync) {
                ed.apply();
                return true;
            } else {
                return ed.commit();
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        return false;
    }



    /**
     * Allows to _get_ a boolean value from the SharedPreferences
     * @param _context
     * @param _key
     * @param _defaultValue
     * @return false if not found
     */
    public static boolean getBoolean(Context _context, final String _key, final boolean _defaultValue)
    {
        boolean value = _defaultValue;
        try {
            final SharedPreferences mPrefs = getReadInstance(_context);
            value = mPrefs.getBoolean(_key, _defaultValue);
            return value;
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return value;
    }
    //endregion

    //region Other

    /** Allows to clean ALL the SharedPreferences
     *  !!! USE WITH CAUTION !!!
     * @param _context
     * @return
     */
    public static boolean cleanPreferences(Context _context) {
        try {
            SharedPreferences.Editor ed = getWriteInstance(_context);
            ed.clear();
            return ed.commit();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return false;
    }
    //endregion

    //region Instances
    protected static SharedPreferences getReadInstance(Context _context)
    {
        final SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(_context);
        return mPrefs;
    }

    protected static SharedPreferences.Editor getWriteInstance(Context _context)
    {
        final SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(_context);
        SharedPreferences.Editor ed = mPrefs.edit();
        return ed;
    }
    //endregion
}
