package com.cremy.greenrobotutils.library.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;

/**
 * Created by remychantenay on 30/10/2015.
 */
public final class AlertDialogUtil {

    /**
     * Allows to display a simple informal AlertDialog
     * @param context
     * @param title
     * @param content
     * @param ctaText
     */
    public static void displayInformalAlertDialog(@NonNull Context context,
                                                  @NonNull String title,
                                                  @NonNull String content,
                                                  @NonNull String ctaText) {
        AlertDialog.Builder ad = new AlertDialog.Builder(context);
        ad.setTitle(title);
        ad.setCancelable(true);
        ad.setMessage(content);
        ad.setPositiveButton(ctaText, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            } });

        ad.show();
    }
}
