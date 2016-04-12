package com.cremy.greenrobotutils.library.storage.gson;

import android.content.Context;

import java.lang.reflect.Type;

/**
 * Created by remychantenay on 11/04/2016.
 */
public class GSONBaseModel  {
    private final static String TAG = "GSONBaseModel";

    /**
     * Allows to _save_ the model into local SharedPreferences
     * @param _context
     * @param _tag
     * @param _classType
     * @param _object
     * @return
     */
    public static boolean save(Context _context, final String _tag, final Type _classType, Object _object) {
        return GSONStorageManager.setObject(_context, false, _tag, _object, _classType);
    }


    /**
     * Allows to _save_ ASYNCHRONOUSLY the model into local SharedPreferences
     *
     * @param _context
     * @param _tag
     * @param _classType
     * @param _object
     * @return
     */
    public static boolean saveAsync(Context _context, final String _tag, final Type _classType, Object _object) {
        return GSONStorageManager.setObject(_context, true, _tag, _object, _classType);
    }


    /**
     * Allows to _load_ the model from local SharedPreferences
     * @param _context
     * @param _tag
     * @param _classType
     * @return
     */
    public static Object load(Context _context, final String _tag, final Type _classType) {
        return GSONStorageManager.getObject(_context, _tag, _classType);
    }
}
