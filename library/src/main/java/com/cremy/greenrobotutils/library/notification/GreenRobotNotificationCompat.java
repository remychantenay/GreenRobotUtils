package com.cremy.greenrobotutils.library.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

/**
 * Created by remychantenay on 16/02/2016.
 */
public class GreenRobotNotificationCompat {
    public static final int DEFAULT_LED_ON_DURATION_MS = 500;
    public static final int DEFAULT_LED_OFF_DURATION_MS = 2000;

    protected int id;
    protected String title = null;
    protected String content = null;

    protected NotificationManagerCompat mNotificationManager;
    protected Notification notification;

    public GreenRobotNotificationCompat() {}


    /**
     *
     * @param _id
     * @param _title
     * @param _content
     * @param _intent
     * @param _drawableForStatusBar
     * @param _drawableForNotification
     * @param _drawableForWearBackgroundNotification (must bit a 320*320 LIGHTWEIGHT jpeg)
     * @param _largeIcon
     * @param _actions
     * @param _onGoingNotification
     * @param _context
     */
    public GreenRobotNotificationCompat(int _id,
                                        final String _title,
                                        final String _content,
                                        Intent _intent,
                                        int _drawableForStatusBar,
                                        int _drawableForNotification,
                                        int _drawableForWearBackgroundNotification,
                                        Bitmap _largeIcon,
                                        GreenRobotNotificationAction[] _actions,
                                        boolean _onGoingNotification,
                                        Context _context)
    {
        this.id = _id;
        this.title = _title;
        this.content = _content;

        mNotificationManager = NotificationManagerCompat.from(_context);


        // 1. Creating the intent
        PendingIntent pi=PendingIntent.getActivity(_context, 2, _intent, PendingIntent.FLAG_CANCEL_CURRENT);


        // 2. Creating the Wear specific
        NotificationCompat.WearableExtender wearableExtender = new NotificationCompat.WearableExtender()
                .setHintShowBackgroundOnly(false)
                .setBackground(_largeIcon==null?BitmapFactory.decodeResource(_context.getResources(), _drawableForWearBackgroundNotification): _largeIcon);

        // 3. Creating the notification builder
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(_context)
                        .setSmallIcon(_drawableForStatusBar)
                        .setContentTitle(this.title)
                        .setContentText(this.content)
                        .setWhen(System.currentTimeMillis())
                        .setTicker(this.title)
                        .setContentIntent(pi)
                        .setOngoing(_onGoingNotification)
                        .extend(wearableExtender);



        if (_largeIcon != null)
        {
            try {
                mBuilder.setLargeIcon(_largeIcon);
            } catch (NullPointerException e){
                mBuilder.setLargeIcon(BitmapFactory.decodeResource(_context.getResources(), _drawableForNotification));
            }
        }
        else{
            mBuilder.setLargeIcon(BitmapFactory.decodeResource(_context.getResources(), _drawableForNotification));
        }

        // If we need to add actions
        if (_actions != null) {
            final int nbActions = _actions.length;
            for (int i = 0; i < nbActions; i++) {
                mBuilder.addAction(_actions[i].getIcon(), _actions[i].getTitle(), _actions[i].getIntent());
            }
        }


        this.notification = new NotificationCompat.BigTextStyle(mBuilder).bigText(_content).build();
    }



    public void setFlagNoClear() {
        this.notification.flags |= Notification.FLAG_NO_CLEAR;
    }


    public void setFlagAutoCancel() {
        this.notification.flags |= Notification.FLAG_AUTO_CANCEL;
    }


    public void setFlagForegroundNotification() {
        this.notification.flags = Notification.FLAG_ONLY_ALERT_ONCE | Notification.FLAG_NO_CLEAR;
    }

    //region LED related
    public void applyRedLight() {
        this.notification.ledARGB = 0xFFFF0000; // Red
        this.notification.flags |= Notification.FLAG_SHOW_LIGHTS;
        this.notification.ledOnMS = DEFAULT_LED_ON_DURATION_MS;
        this.notification.ledOffMS = DEFAULT_LED_OFF_DURATION_MS;
    }

    public void applyBlueLight() {
        this.notification.ledARGB = 0xFF008aff; // Blue
        this.notification.flags |= Notification.FLAG_SHOW_LIGHTS;
        this.notification.ledOnMS = DEFAULT_LED_ON_DURATION_MS;
        this.notification.ledOffMS = DEFAULT_LED_OFF_DURATION_MS;
    }

    public void applyGreenLight() {
        this.notification.ledARGB = 0xFF08C38B; // Green
        this.notification.flags |= Notification.FLAG_SHOW_LIGHTS;
        this.notification.ledOnMS = DEFAULT_LED_ON_DURATION_MS;
        this.notification.ledOffMS = DEFAULT_LED_OFF_DURATION_MS;
    }

    public void applyOrangeLight() {
        this.notification.ledARGB = 0xFFFF9C42; // Orange
        this.notification.flags |= Notification.FLAG_SHOW_LIGHTS;
        this.notification.ledOnMS = DEFAULT_LED_ON_DURATION_MS;
        this.notification.ledOffMS = DEFAULT_LED_OFF_DURATION_MS;
    }
    //endregion

    /**
     * Allows to set a custom sound for the notification
     * @param _context
     * @param _idRes
     */
    public void setCustomSound(Context _context, int _idRes) {
        this.notification.sound = Uri.parse("android.resource://" + _context.getPackageName() + "/" + _idRes);
    }

    public void applyDefaultSound() {
        this.notification.defaults |= Notification.DEFAULT_SOUND;
    }

    public void startNotify() {
        this.mNotificationManager.notify(String.valueOf(this.id), this.id, this.notification);
    }

    public void startNotify(Notification _notification) {
        this.mNotificationManager.notify(this.id, _notification);
    }


    public static void dismissNotification(Context _context, int _notificationId) {
        NotificationManager mNotificationManager = (NotificationManager) _context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.cancel(String.valueOf(_notificationId), _notificationId);
    }





    /**
     * Allows to show a givin notification WITH custom app sound
     * @param _context
     * @param _notification
     * @param _soundResId (e.g. R.raw.sound)
     */
    public static void showNotificationWithSound(Context _context,
                                                 GreenRobotNotificationCompat _notification,
                                                 final int _soundResId) {
        _notification.setCustomSound(_context, _soundResId);
        _notification.startNotify();
    }

    /**
     * Allows to show a givin notification WITHOUT custom app sound
     * @param _notification
     */
    public static void showNotification(GreenRobotNotificationCompat _notification) {
        _notification.applyDefaultSound();
        _notification.startNotify();
    }



    public Notification getNotification() {
        return notification;
    }
}
