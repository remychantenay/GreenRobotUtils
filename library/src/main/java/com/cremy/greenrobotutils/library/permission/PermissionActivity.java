package com.cremy.greenrobotutils.library.permission;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Inherit from this activity allows to handle easily the new Marshmallow permission grant and deny
 * process.
 * @author remychantenay
 */
public abstract class PermissionActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        // If request is cancelled, the result arrays are empty.
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            this.onPermissionGranted(requestCode);
        } else {
            this.onPermissionDenied(requestCode);
        }
        return;
    }

    /**
     * This callback will be triggered if the asked permission is GRANTED
     * @param _requestCode
     */
    public abstract void onPermissionGranted(int _requestCode);

    /**
     * This callback will be triggered if the asked permission is DENIED
     * @param _requestCode
     */
    public abstract void onPermissionDenied(int _requestCode);

}
