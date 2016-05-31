package com.cremy.greenrobotutils.library.util;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * Created by remychantenay on 31/05/2016.
 */
public class KeyboardUtils {
    /**
     * Allows to force the software keyboard to hide itself
     * @param _context
     * @param _editText
     */
    public static  void hideKeyboard(Context _context, EditText _editText)
    {

        try {
            InputMethodManager imm = (InputMethodManager)_context.getSystemService(
                    Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(_editText.getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Error e) {
            e.printStackTrace();
        }
    }

    /**
     * Allows to force the software keyboard to show up
     * @param _context
     * @param _editText
     */
    public static  void showKeyboard(Context _context, EditText _editText)
    {

        try {
            InputMethodManager imm = (InputMethodManager)_context.getSystemService(
                    Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(_editText, 0);
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Error e) {
            e.printStackTrace();
        }

    }
}
