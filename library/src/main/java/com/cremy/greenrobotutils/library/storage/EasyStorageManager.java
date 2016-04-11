package com.cremy.greenrobotutils.library.storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;

/**
 * EasyStorageManager (or ESM)
 *
 * This class allows to save JSON formatted data really easily using the SharedPreferences
 * Created by remychantenay on 11/04/2016.
 */
public final class EasyStorageManager {

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

    //region GSON
    private static Gson GSON_INSTANCE = null;


    /**
     * Allows to _SAVE_ an object using JSON (GSON_INSTANCE) format
     *
     * @param _context
     * @param _isAsync -> if true, the returned value will be true as the writing is not synchronous
     * @param _key
     * @param _object
     * @param _classType
     * @return True if the object has been successfully saved, false otherwise
     */
    public static boolean setObject(Context _context,
                                    final boolean _isAsync,
                                    final String _key,
                                    final Object _object,
                                    final Type _classType)
    {
        if (GSON_INSTANCE ==null) {
            GSON_INSTANCE = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        }
        final String json = GSON_INSTANCE.toJson(_object, _classType);
        return PreferencesManager.setString(_context, _isAsync, _key, json);
    }

    /**
     * Allows to _RETRIEVE_ an object using JSON (GSON_INSTANCE) format
     *
     * @param _context
     * @param _key
     * @param _classType
     */
    public static Object getObject(Context _context, final String _key, final Type _classType)
    {
        String str = PreferencesManager.getString(_context, _key);

        if (str==null || str.isEmpty()) {
            return null;
        }

        if (GSON_INSTANCE ==null) {
            GSON_INSTANCE = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        }

        return GSON_INSTANCE.fromJson(str, _classType);
    }
    //endregion
}
