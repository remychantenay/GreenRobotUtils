package com.cremy.greenrobotutils.library.storage.gson;

import com.google.gson.Gson;

/**
 * GSONHelper
 *
 * This class allows to save JSON formatted data really easily using the SharedPreferences
 * Created by remychantenay on 11/04/2016.
 */
public final class GSONHelper {

    private static Gson GSON_INSTANCE = null;

    /**
     * Allows to get the Gson object instance using the Singleton pattern
     * @return
     */
    public static Gson getInstance() {
        if (GSON_INSTANCE ==null) {
            //GSON_INSTANCE = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
            GSON_INSTANCE = new Gson();
        }

        return GSON_INSTANCE;
    }


    /**
     * Allows to _serialize_ an object to a JSON string formatted
     * @param _object
     * @param _class
     * @return
     */
    public static String toJSON(Object _object, Class<?> _class) {
        return getInstance().toJson(_object, _class);
    }

    /**
     * Allows to _unserialize_ aJSON string formatted to an object
     * @param _json
     * @param _class
     * @return
     */
    public static Object fromJSON(final String _json, Class<?> _class) {
        return getInstance().fromJson(_json, _class);
    }
}
