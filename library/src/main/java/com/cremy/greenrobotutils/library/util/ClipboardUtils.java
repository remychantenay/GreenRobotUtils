package com.cremy.greenrobotutils.library.util;

import android.content.Context;

/**
 * This class allows to manage content on the clipboard
 * Created by remychantenay on 09/04/2015.
 */
public final class ClipboardUtils {


    /**
     * Allows to copy a string content to the clipboard
     * @param _context
     * @param _title
     * @param _str
     */
    public static void copyStringToClipboard(Context _context, final String _title, final String _str) {
        int sdk = android.os.Build.VERSION.SDK_INT;
        if(sdk < android.os.Build.VERSION_CODES.HONEYCOMB) {
            android.text.ClipboardManager clipboard = (android.text.ClipboardManager) _context.getSystemService(Context.CLIPBOARD_SERVICE);
            clipboard.setText(_str);
        } else {
            android.content.ClipboardManager clipboard = (android.content.ClipboardManager) _context.getSystemService(Context.CLIPBOARD_SERVICE);
            android.content.ClipData clip = android.content.ClipData.newPlainText(_title,_str);
            clipboard.setPrimaryClip(clip);
        }
    }
}
