package com.cremy.greenrobotutils.library.util;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;

/**
 * This class allows to manage content on the clipboard
 * Created by remychantenay on 09/04/2015.
 */
public final class ClipboardUtils {


    /**
     * Allows to copy a string content to the clipboard via {@link android.content.ClipboardManager}
     * @param context
     * @param title
     * @param content
     */
    public static void copyStringToClipboard(@NonNull Context context,
                                             @NonNull final String title,
                                             @NonNull final String content) {
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            android.text.ClipboardManager clipboard = (android.text.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            clipboard.setText(content);
        } else {
            android.content.ClipboardManager clipboard = (android.content.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            android.content.ClipData clip = android.content.ClipData.newPlainText(title, content);
            clipboard.setPrimaryClip(clip);
        }
    }
}
