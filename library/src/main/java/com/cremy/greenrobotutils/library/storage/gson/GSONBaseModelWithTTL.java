package com.cremy.greenrobotutils.library.storage.gson;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.util.concurrent.TimeUnit;

/**
 * Created by remychantenay on 10/12/2015.
 */
public class GSONBaseModelWithTTL extends GSONBaseModel {
    private final static String TAG = "GSONBaseModelWithTTL";

    protected int ttl = 360; // In minutes

    @SerializedName("lastTimeChecked") protected long lastTimeChecked = 0; // in ms via System.currentTimeMillis()

    /**
     * Allows to know if we should ask for new data from the server
     * @return
     */
    public boolean isTTLOutdated() {
        long diff = (System.currentTimeMillis() - this.lastTimeChecked);
        diff = TimeUnit.MILLISECONDS.toMinutes(diff);
        if (diff > ttl) {
            Log.i(TAG, "Data outdated, we will contact the server...");
            return true;
        }
        Log.i(TAG, "Data are up-to-date, we won't contact the server...");
        return false;
    }

    public void setLastTimeChecked(long lastTimeChecked) {
        this.lastTimeChecked = lastTimeChecked;
    }
}
