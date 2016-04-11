package com.cremy.greenrobotutils.library.storage.gson;

import android.content.Context;

import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

/**
 * Created by remychantenay on 11/04/2016.
 */
public class GSONBaseModel implements GSONBaseModelInterface{
    private final static String TAG = "GSONBaseModel";

    //region Serialized Fields
    @SerializedName("id") protected long id = 0;
    //endregion


    /**
     * Allows to _save_ the model into local SharedPreferences
     * @param _context
     * @return true if success, false otherwise
     */
    @Override
    public boolean save(Context _context) {
        return GSONStorageManager.setObject(_context, true, TAG, this, getType());
    }


    @Override
    public Object load(Context _context) {
        GSONBaseModel obj = (GSONBaseModel) GSONStorageManager.getObject(_context, TAG, getType());
        if (obj == null) {
            obj = new GSONBaseModel();
        }

        return obj;
    }


    /**
     * Allows to get the class type
     * Mandatory to use GSON
     * @return
     */
    @Override
    public Type getType() {
        return new TypeToken<GSONBaseModel>() {}.getType();
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
