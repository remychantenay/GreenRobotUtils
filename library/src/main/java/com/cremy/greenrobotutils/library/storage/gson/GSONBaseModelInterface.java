package com.cremy.greenrobotutils.library.storage.gson;

import android.content.Context;

import java.lang.reflect.Type;

/**
 * Created by remychantenay on 11/04/2016.
 */
public interface GSONBaseModelInterface {

    /**
     * Allows to get the class type
     * Necessary to use GSON
     * To get it:
     * return new TypeToken<User>() {}.getType();
     * @return
     */
    Type getType();

    boolean save(Context _context);
    Object load(Context _context);
}
