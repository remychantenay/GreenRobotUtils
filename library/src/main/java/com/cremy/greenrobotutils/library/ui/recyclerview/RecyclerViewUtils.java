package com.cremy.greenrobotutils.library.ui.recyclerview;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.cremy.greenrobotutils.library.bitmap.BitmapHelper;

/**
 * Created by remychantenay on 11/03/2016.
 */
public final class RecyclerViewUtils {

    /**
     * Allows to set an item touch callback on a given recyclerview
     * @param _recyclerView
     * @param _callback
     */
    public static void setItemTouchCallback(RecyclerView _recyclerView, ItemTouchHelper.SimpleCallback _callback) {
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(_callback);
        itemTouchHelper.attachToRecyclerView(_recyclerView);
    }


    /**
     * Allows to draw a color filled rectangle on the RecyclerView item swiped area
     * @param _color
     * @param _itemView
     * @param _dX
     * @param _canvas
     */
    public static void drawRectOnSwipe(View _itemView,
                                       Canvas _canvas,
                                       final int _color,
                                       final float _dX) {
        Paint p = new Paint();
        p.setColor(_color);

        // 1. We draw a rectangle with a filled color
        if (_dX > 0) // Right swipe
        {
            _canvas.drawRect((float) _itemView.getLeft(), (float) _itemView.getTop(), _dX,
                    (float) _itemView.getBottom(), p);
        }
        else { // Left swipe
            _canvas.drawRect((float) _itemView.getRight() + _dX, (float) _itemView.getTop(),
                    (float) _itemView.getRight(), (float) _itemView.getBottom(), p);
        }
    }


    /**
     * Allows to draw a bitmap on the RecyclerView item swiped area
     * @param _itemView
     * @param _canvas
     * @param _res
     * @param _resId
     * @param _paint
     */
    public static void drawBitmap(View _itemView,
                                  Canvas _canvas,
                                  Resources _res,
                                  final int _resId,
                                  Paint _paint) {

        // 1. Loading the bitmap
        final Bitmap bitmap = BitmapHelper.getScaledResourceBitmap(_res, _resId);

        // 2. Setting the top position
        int positionTop = _itemView.getTop();
        final int itemHeight = _itemView.getHeight();
        positionTop = positionTop+((itemHeight/2)-(bitmap.getHeight()/2));

        // 3. Drawing a resource bitmap
        _canvas.drawBitmap(bitmap, 25, positionTop, _paint);
    }
}
