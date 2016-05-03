package com.cremy.greenrobotutils.library.util;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v7.app.AppCompatActivity;

/**
 * This class allows to easily manage Loaders
 * Created by remychantenay on 26/04/2016.
 */
public final class LoaderUtils {

    /**
     * Allows to re-connect an existing Loader and create a new one
     * @param _activity
     * @param _id
     * @param _args
     * @param _callback
     * @param _forceLoad
     */
    public static void initOrReconnectLoader(AppCompatActivity _activity,
                                      final int _id,
                                      Bundle _args,
                                      LoaderManager.LoaderCallbacks _callback,
                                      boolean _forceLoad) {

        if (_forceLoad) {
            _activity.getSupportLoaderManager().initLoader(_id, _args, _callback).forceLoad();
        } else {
            _activity.getSupportLoaderManager().initLoader(_id, _args, _callback);
        }

    }
}
