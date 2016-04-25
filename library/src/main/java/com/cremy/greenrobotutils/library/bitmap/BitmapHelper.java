package com.cremy.greenrobotutils.library.bitmap;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by remychantenay on 11/03/2016.
 */
public final class BitmapHelper {


    /**
     * Allows to get a bitmap (SCALED, i.e SHARP) from the resources
     * @param _res
     * @param _resId
     * @return
     */
    public static Bitmap getScaledResourceBitmap(Resources _res, int _resId) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;
        return BitmapFactory.decodeResource(_res, _resId, options);
    }
}
