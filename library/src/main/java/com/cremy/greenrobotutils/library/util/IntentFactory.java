package com.cremy.greenrobotutils.library.util;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Parcelable;

import com.cremy.greenrobotutils.library.R;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by Remy on 02/05/14.
 */
public final class IntentFactory {

    //region INTENT URIs
    public static final String URI_GOOGLE_PLAY_STORE = "market://details?id=";
    public static final String URI_AMAZON_APP_STORE = "amzn://apps/android?p=";
    public static final String URL_AMAZON_APP_STORE_WEB = "http://www.amazon.com/gp/mas/dl/android?p=";
    //endregion

    //region REQUEST CODES
    public static final int REQUEST_CODE_GALLERY_PICTURE_PICKING = 13;
    public static final int REQUEST_CODE_GALLERY_VIDEO_PICKING = 14;
    public static final int REQUEST_CODE_GALLERY_PICTURE_PICKING_WITH_MULTIPLE_SELECTION = 15;
    //endregion

    //region SPECIFIC APPS PACKAGE NAMES
    public static final String FACEBOOK = "katana";
    public static final String TWITTER = "twitter";
    //endregion

    /**
     * Allows to start the android system mail sending intent
     * @param _context
     * @param _recipient
     * @param _subject
     * @param _body
     */
    public static void startSendMailIntentDeprecated(Context _context,
                                                     final String _recipient,
                                                     final String _subject,
                                                     final String _body) {
        Intent intent = new Intent(Intent.ACTION_SEND);

        if (_recipient != null) {
            intent.putExtra(Intent.EXTRA_EMAIL, new String[] { _recipient });
        }
        if (_subject != null) {
            intent.putExtra(Intent.EXTRA_SUBJECT, _subject);
        }
        if (_body != null) {
            intent.putExtra(Intent.EXTRA_TEXT, new String[] { _body });
        }
        intent.setType("message/rfc822");
        _context.startActivity(Intent.createChooser(intent, _context.getResources().getString(R.string.green_robot_utils_intent_mail_title)));
    }

    /**
     * Allows to start the android system mail sending intent
     *  This one seems to work better before Android 4.2, and the Intent chooser is more precise
     * @param _context
     * @param _recipient
     * @param _subject
     * @param _body
     */
    public static void startSendMailIntent(Context _context,
                                           final String _recipient,
                                           final String _subject,
                                           final String _body) {
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto",_recipient, null));

        if (_subject != null) {
            intent.putExtra(Intent.EXTRA_SUBJECT, _subject);
        }
        if (_body != null) {
            intent.putExtra(Intent.EXTRA_TEXT, new String[] { _body });
        }

        _context.startActivity(Intent.createChooser(intent, _context.getResources().getString(R.string.green_robot_utils_intent_mail_title)));
    }

    /**
     * Allows to start the android system share intent
     * @param _context
     * @param _body
     * @param _title
     */
    public static void startShareIntent(Context _context, final String _body, final String _title) {
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(Intent.EXTRA_TEXT, _body);
        _context.startActivity(Intent.createChooser(sharingIntent, _title));
    }

    /**
     * Allows to get a system share pending intent
     * @param _context
     * @param _body
     */
    public static PendingIntent getSharePendingIntent(Context _context, final String _body) {
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(Intent.EXTRA_TEXT, _body);
        return PendingIntent.getActivity(_context, 0, sharingIntent, PendingIntent.FLAG_UPDATE_CURRENT);
    }


    /**
     * Allows to start the android system share intent but with a specified application
     *
     * @param _context
     * @param _body
     * @param _title
     * @param _app e.g IntentFactory.FACEBOOK, IntentFactory.TWITTER
     * @return true if the wanted app was found, false otherwise
     */
    public static boolean startSpecificShareIntent(Context _context,
                                                   final String _body,
                                                   final String _title,
                                                   final String _app) {
        boolean found = false;
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(Intent.EXTRA_TEXT,     _body);
        // gets the list of intents that can be loaded.
        List<ResolveInfo> resInfo = _context.getPackageManager().queryIntentActivities(sharingIntent, 0);
        if (!resInfo.isEmpty()){
            for (ResolveInfo info : resInfo) {
                if (info.activityInfo.packageName.toLowerCase().contains(_app) || info.activityInfo.name.toLowerCase().contains(_app) ) {
                    sharingIntent.setPackage(info.activityInfo.packageName);
                    found = true;
                    break;
                }
            }

            if (!found) {
                return found;
            }
            _context.startActivity(Intent.createChooser(sharingIntent, _title));
        }
        return found;
    }


    /**
     * Allows to launch the android system gallery picture intent
     * @param _activity
     * @param _requestCode
     */
    public static void startImageGalleryPickerIntent(Activity _activity, final int _requestCode) {
        Intent photoPickerIntent = new Intent(Intent.ACTION_GET_CONTENT);
        photoPickerIntent.setType("image/*");
        _activity.startActivityForResult(photoPickerIntent, _requestCode);
    }

    /**
     * Allows to launch the android system gallery picture intent
     *  Excluding the given package name
     *
     * @param _activity
     * @param _excludePackageName
     */
    public static void startGalleryPicturePickerIntent(Activity _activity, String _excludePackageName) {
        List<Intent> targetedShareIntents = new ArrayList<Intent>();
        Intent photoPickerIntent = new Intent(Intent.ACTION_GET_CONTENT);
        photoPickerIntent.setType("image/*");

        List<ResolveInfo> resInfo = _activity.getPackageManager().queryIntentActivities(photoPickerIntent, 0);
        if (!resInfo.isEmpty()) {
            for (ResolveInfo info : resInfo) {
                Intent currentIntent = new Intent(Intent.ACTION_GET_CONTENT);
                currentIntent.setType("image/*");
                if (!info.activityInfo.packageName.toLowerCase().contains(_excludePackageName) && !info.activityInfo.name.toLowerCase().contains(_excludePackageName)) {
                    currentIntent.setPackage(info.activityInfo.packageName);
                    targetedShareIntents.add(currentIntent);
                }
            }
            photoPickerIntent = Intent.createChooser(targetedShareIntents.remove(0), "Source");
            photoPickerIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, targetedShareIntents.toArray(new Parcelable[]{}));
        }

        _activity.startActivityForResult(photoPickerIntent, REQUEST_CODE_GALLERY_PICTURE_PICKING);
    }


    /**
     * Allows to start the android system image gallery intent for multiple selection
     * IMPORTANT : ONLY AVAILABLE FOR >= API 18 (4.3)
     *
     * @param _activity
     */
    public static void startImageGalleryForMultipleSelection(Activity _activity) {

        if (Build.VERSION.SDK_INT <19){
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
            _activity.startActivityForResult(Intent.createChooser(intent, "Select"),REQUEST_CODE_GALLERY_PICTURE_PICKING_WITH_MULTIPLE_SELECTION);
        } else {
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("image/*");
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
            _activity.startActivityForResult(intent, REQUEST_CODE_GALLERY_PICTURE_PICKING_WITH_MULTIPLE_SELECTION);
        }
    }


    /**
     * Allows to start the Google Play Store intent with a given app package name
     * @param _context
     */
    public static void startCurrentAppGooglePlayStoreIntent(Context _context) throws ActivityNotFoundException {
        if (_context== null) {
            return;
        }
        try {
            _context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(URI_GOOGLE_PLAY_STORE + _context.getPackageName())));
        } catch (ActivityNotFoundException e) {
            throw e;
        }
    }


    /**
     * Allows to get the Google Play Store intent with a given app package name
     * @param _context
     * @return Google Play Store intent
     */
    public static Intent getCurrentAppGooglePlayStoreIntent(Context _context) throws ActivityNotFoundException {
        if (_context== null) {
            return null;
        }
        return new Intent(Intent.ACTION_VIEW, Uri.parse(URI_GOOGLE_PLAY_STORE + _context.getPackageName()));
    }

    /**
     * Allows to start the Amazon App Store with the page of the current app
     * @param _context
     */
    public static void startCurrentAppAmazonAppStoreIntent(Context _context) throws ActivityNotFoundException {
        try {
            _context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(URI_AMAZON_APP_STORE + _context.getPackageName())));
        } catch (ActivityNotFoundException e) {
            try {
                _context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(URL_AMAZON_APP_STORE_WEB + _context.getPackageName())));
            } catch (ActivityNotFoundException e2) {
                throw e;
            }
        }
    }

    /**
     * Allows to start a browser intent with a given URL
     * @param _context
     * @param _destinationUrl
     */
    public static void startBrowserIntent(Context _context, final String _destinationUrl) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(_destinationUrl));
        _context.startActivity(browserIntent);
    }


    /**
     * Allows to start the dialer intent with a given number
     * @param _context
     * @param _number
     */
    public static void startDialerIntent(Context _context, final String _number)
    {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:"+_number));
        _context.startActivity(intent);
    }

    /**
     * Allows to start the sms intent with a given number
     * @param _context
     * @param _number
     * @param _body (Can be NULL)
     */
    public static void startSMSIntent(Context _context, final String _number, final String _body)
    {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("sms:"+_number));
        if (_body != null) {
            intent.putExtra("sms_body", _body);
        }
        _context.startActivity(intent);
    }


    /**
     * Allows to start the YouTube app intent
     * @param _ctx
     * @param _link
     */
    public static void startYouTubeAppIntent(Context _ctx, final String _link)
    {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(_link));
        _ctx.startActivity(intent);
    }

    /**
     * Allows to start the video player intent with a given url
     * @param _context
     * @param _videoUrl
     */
    public static void startVideoPlayerIntent(Context _context, final String _videoUrl)
    {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setDataAndType(Uri.parse(_videoUrl), "video/*");
        _context.startActivity(i);
    }

    /**
     * Allows to start the gallery picker for videos ("video/*")
     * @param _activity
     * @param _requestCode
     */
    public static void startVideoPickerIntent(Activity _activity, final int _requestCode)
    {
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.setType("video/*");
        _activity.startActivityForResult(i, _requestCode);
    }
}
