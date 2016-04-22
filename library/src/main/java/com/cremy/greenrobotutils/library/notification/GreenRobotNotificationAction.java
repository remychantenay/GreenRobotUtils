package com.cremy.greenrobotutils.library.notification;

import android.app.PendingIntent;

/**
 * Allows to encapsulate action adding for Notifications since JellyBean
 * Notification.Builder.addAction(int icon, CharSequence title, PendingIntent intent)
 * Created by remychantenay on 05/11/2014.
 */
public final class GreenRobotNotificationAction {
    private int icon = 0;
    private CharSequence title;
    PendingIntent intent;


    public GreenRobotNotificationAction() {

    }

    public GreenRobotNotificationAction(int _icon, CharSequence _title, PendingIntent _intent) {
        this.icon = _icon;
        this.title = _title;
        this.intent = _intent;
    }

    public GreenRobotNotificationAction(CharSequence _title, PendingIntent _intent) {
        this.title = _title;
        this.intent = _intent;
    }

    public PendingIntent getIntent() {
        return intent;
    }

    public void setIntent(PendingIntent intent) {
        this.intent = intent;
    }

    public CharSequence getTitle() {
        return title;
    }

    public void setTitle(CharSequence title) {
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
