package com.cremy.greenrobotutils.library.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by remychantenay on 30/10/2015.
 */
public final class AlertDialogUtil {

    /**
     * Allows to display a simple informal AlertDialog to the user
     * @param _context
     * @param _title
     * @param _content
     */
    public static void displayInformalAlertDialog(Context _context, final String _title, final String _content, final String _buttonClose) {
        AlertDialog.Builder ad = new AlertDialog.Builder(_context);
        ad.setTitle(_title);
        ad.setCancelable(true);
        ad.setMessage(_content);
        ad.setPositiveButton(_buttonClose, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            } });

        ad.show();
    }
}
