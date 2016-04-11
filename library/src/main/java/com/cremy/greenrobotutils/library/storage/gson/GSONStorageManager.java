package com.cremy.greenrobotutils.library.storage.gson;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.cremy.greenrobotutils.library.storage.PreferencesManager;

import java.lang.reflect.Type;

/**
 * GSONStorageManager
 *
 * This class allows to save JSON formatted data really easily using the SharedPreferences
 * Created by remychantenay on 11/04/2016.
 */
public final class GSONStorageManager {

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
        final String json = GSONHelper.getInstance().toJson(_object, _classType);

        final SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(_context);
        SharedPreferences.Editor ed = mPrefs.edit();
        ed.putString(_key, json);
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
        final SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(_context);
        String str = mPrefs.getString(_key, "");

        if (str.isEmpty()) {
            return null;
        }

        return GSONHelper.getInstance().fromJson(str, _classType);
    }
}
