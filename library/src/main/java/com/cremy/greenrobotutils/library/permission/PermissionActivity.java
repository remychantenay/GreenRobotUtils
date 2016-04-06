package com.cremy.greenrobotutils.library.permission;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

/**
 * Inherit from this activity allows to handle easily the new Marshmallow permission grant and deny
 * process.
 * @author remychantenay
 */
public class PermissionActivity extends AppCompatActivity {

    public final static int REQUEST_CODE_PERMISSION_CAMERA = 142;
    public final static int REQUEST_CODE_PERMISSION_GET_ACCOUNTS = 143;
    public final static int REQUEST_CODE_PERMISSION_READ_PHONE_STATE = 144;
    public final static int REQUEST_CODE_PERMISSION_WRITE_EXTERNAL_STORAGE = 145;

    // Manifest.permission.CAMERA
    public static final String CAMERA = "android.permission.CAMERA";
    // Manifest.permission.GET_ACCOUNTS
    public static final String GET_ACCOUNTS = "android.permission.GET_ACCOUNTS";
    // Manifest.permission.READ_PHONE_STATE
    public static final String READ_PHONE_STATE = "android.permission.READ_PHONE_STATE";
    // Manifest.permission.WRITE_EXTERNAL_STORAGE
    public static final String WRITE_EXTERNAL_STORAGE = "android.permission.WRITE_EXTERNAL_STORAGE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        // If request is cancelled, the result arrays are empty.
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            this.permissionGranted(requestCode);
        } else {
            this.permissionDenied(requestCode);
        }
        return;
    }

    public void permissionGranted(int _requestCode) {
        // To Override
    }

    public void permissionDenied(int _requestCode) {
        // To Override
    }


    /**
     * Allows to check if the current version is at least Marshmallow,
     * If it's not, no need to ask for any permission
     * @return
     */
    private static boolean isMarshmallowOrAbove() {
        if (Build.VERSION.SDK_INT >= 23) {
            return true;
        }
        return false;

    }
    /**
     * Allows to know if the CAMERA permission is granted or not
     * @param _context
     * @return true if granted, false otherwise
     */
    public static boolean isCameraPermissionGranted(Context _context) {

        if (!isMarshmallowOrAbove()) {
            return true;
        }
        return (ContextCompat.checkSelfPermission(_context, CAMERA) == PackageManager.PERMISSION_GRANTED);
    }

    /**
     * Allows to know if the GET_ACCOUNTS permission is granted or not
     * @param _context
     * @return true if granted, false otherwise
     */
    public static boolean isGetAccountsPermissionGranted(Context _context) {

        if (!isMarshmallowOrAbove()) {
            return true;
        }
        return (ContextCompat.checkSelfPermission(_context, GET_ACCOUNTS) == PackageManager.PERMISSION_GRANTED);
    }

    /**
     * Allows to know if the READ_PHONE_STATE permission is granted or not
     * @param _context
     * @return true if granted, false otherwise
     */
    public static boolean isReadPhoneStatePermissionGranted(Context _context) {

        if (!isMarshmallowOrAbove()) {
            return true;
        }
        return (ContextCompat.checkSelfPermission(_context, READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED);
    }

    /**
     * Allows to know if the WRITE_EXTERNAL_STORAGE permission is granted or not
     * @param _context
     * @return true if granted, false otherwise
     */
    public static boolean isWriteExternalStoragePermissionGranted(Context _context) {

        if (!isMarshmallowOrAbove()) {
            return true;
        }
        return (ContextCompat.checkSelfPermission(_context, WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
    }



    /**
     * Allows to ask for the CAMERA permission
     * @param _activity
     */
    public static void requestCameraPermission(Activity _activity) {
        ActivityCompat.requestPermissions(_activity, new String[]{CAMERA}, REQUEST_CODE_PERMISSION_CAMERA);
    }

    /**
     * Allows to ask for the GET_ACCOUNTS permission
     * @param _activity
     */
    public static void requestGetAccountsPermission(Activity _activity) {
        ActivityCompat.requestPermissions(_activity, new String[]{GET_ACCOUNTS}, REQUEST_CODE_PERMISSION_GET_ACCOUNTS);
    }

    /**
     * Allows to ask for the READ_PHONE_STATE permission
     * @param _activity
     */
    public static void requestReadPhoneStatePermission(Activity _activity) {
        ActivityCompat.requestPermissions(_activity, new String[]{READ_PHONE_STATE}, REQUEST_CODE_PERMISSION_READ_PHONE_STATE);
    }

    /**
     * Allows to ask for the WRITE_EXTERNAL_STORAGE permission
     * @param _activity
     */
    public static void requestWriteExternalStoragePermission(Activity _activity) {

        ActivityCompat.requestPermissions(_activity, new String[]{WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_PERMISSION_WRITE_EXTERNAL_STORAGE);
    }
}
