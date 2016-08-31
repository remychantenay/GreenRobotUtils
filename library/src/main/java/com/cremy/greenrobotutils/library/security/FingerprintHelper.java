package com.cremy.greenrobotutils.library.security;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.support.annotation.VisibleForTesting;
import android.support.v4.app.ActivityCompat;
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat;
import android.os.CancellationSignal;

import com.cremy.greenrobotutils.library.R;
import com.cremy.greenrobotutils.library.permission.PermissionHelper;

/**
 * This helper allows to use the FingerprintManager with ease
 * @see https://developer.android.com/about/versions/marshmallow/android-6.0.html#fingerprint-authentication
 * Created by chantenr on 10/08/2016.
 */
@TargetApi(Build.VERSION_CODES.M)
public class FingerprintHelper extends FingerprintManager.AuthenticationCallback {
    public final static int RESULT_CODE_SUCCESS_LISTENING = 0;
    public final static int RESULT_CODE_ERR_HARDWARE_NOT_COMPATIBLE = 1;
    public final static int RESULT_CODE_ERR_NO_FINGERPRINTS_ENROLLED = 2;
    public final static int RESULT_CODE_ERR_PERMISSION_NOT_GRANTED = 3;

    private FingerprintManager mFingerprintManager;
    private Callback mCallback;
    private CancellationSignal mCancellationSignal;
    @VisibleForTesting
    boolean mSelfCancelled;

    public FingerprintHelper(FingerprintManager fingerprintManager,
                             Callback callback) {
        this.mCallback = callback;
        this.mFingerprintManager = fingerprintManager;
    }

    public int startListening(Context context,
                              FingerprintManager.CryptoObject cryptoObject) {
        final int state = getDeviceState(context);
        if (state != RESULT_CODE_SUCCESS_LISTENING) {
            return state;
        }

        mCancellationSignal = new CancellationSignal();
        mSelfCancelled = false;
        mFingerprintManager
                .authenticate(cryptoObject, mCancellationSignal, 0 /* flags */, this, null);

        return RESULT_CODE_SUCCESS_LISTENING;
    }

    public void stopListening() {
        if (mCancellationSignal != null) {
            mSelfCancelled = true;
            mCancellationSignal.cancel();
            mCancellationSignal = null;
        }
    }

    @Override
    public void onAuthenticationError(int errMsgId, CharSequence errString) {
        if (!mSelfCancelled) {
            if (mCallback!=null) {
                mCallback.onError(errMsgId, errString);
            }
        }
    }

    @Override
    public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
        if (mCallback!=null) {
            mCallback.onHelp(helpMsgId, helpString);
        }
    }

    @Override
    public void onAuthenticationFailed() {
        if (mCallback!=null) {
            mCallback.onNotRecognized();
        }
    }

    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
        if (mCallback!=null) {
            mCallback.onAuthenticated();
        }
    }

    /**
     * Allows to know if the current device is ready to use the Fingerprint authentication
     * @return
     * FingerprintHelper.RESULT_CODE_SUCCESS
     * FingerprintHelper.RESULT_CODE_ERR_HARDWARE_NOT_COMPATIBLE
     * FingerprintHelper.RESULT_CODE_ERR_NO_FINGERPRINTS_ENROLLED
     *
     */
    private int getDeviceState(Context context) {
        if (!PermissionHelper.isUseFingerprintPermissionGranted(context)) {
            return RESULT_CODE_ERR_PERMISSION_NOT_GRANTED;
        }
        if (!this.mFingerprintManager.isHardwareDetected()) {
            return RESULT_CODE_ERR_HARDWARE_NOT_COMPATIBLE;
        }
        if (!this.mFingerprintManager.hasEnrolledFingerprints()) {
            return RESULT_CODE_ERR_NO_FINGERPRINTS_ENROLLED;
        }

        return RESULT_CODE_SUCCESS_LISTENING;
    }




    public interface Callback {
        void onAuthenticated();
        void onNotRecognized();
        void onError(int errMsgId, CharSequence errString);
        void onHelp(int helpMsgId, CharSequence helpString);
    }
}
