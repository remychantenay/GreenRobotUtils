package com.cremy.greenrobotutils.library.ui;

import android.graphics.Typeface;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * This Util class allows to perform some tasks on TabLayout
 * @link http://developer.android.com/reference/android/support/design/widget/TabLayout.html
 * @author remychantenay
 */
public final class TabLayoutUtils {

    /**
     * Allows to change the font of the tabs of a given TabLayout
     * @param _tabLayout
     * @param _typeface
     */
    public static void setCustomFont(TabLayout _tabLayout, Typeface _typeface) {

        ViewGroup vg = (ViewGroup) _tabLayout.getChildAt(0);
        int tabsCount = vg.getChildCount();

        for (int j = 0; j < tabsCount; j++) {
            ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);
            int tabChildsCount = vgTab.getChildCount();

            for (int i = 0; i < tabChildsCount; i++) {
                View tabViewChild = vgTab.getChildAt(i);
                if (tabViewChild instanceof TextView) {
                    TextView v = (TextView) tabViewChild;
                    v.setTypeface(_typeface, Typeface.NORMAL);
                }
            }
        }
    }
}
