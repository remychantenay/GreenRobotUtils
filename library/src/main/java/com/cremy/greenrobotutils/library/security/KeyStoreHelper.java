package com.cremy.greenrobotutils.library.security;

import android.annotation.TargetApi;
import android.os.Build;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyPermanentlyInvalidatedException;
import android.security.keystore.KeyProperties;
import android.support.annotation.NonNull;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.UnrecoverableEntryException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;

/**
 * Created by chantenr on 31/08/2016.
 */
@TargetApi(Build.VERSION_CODES.M)
public class KeyStoreHelper {
    private final static String KEY_ANDROID_KEYSTORE = "AndroidKeyStore";
    private static final String KEY_TRANSFORMATION = "AES/GCM/NoPadding";
    private static final String UTF_8 = "UTF-8";

    private KeyStore keyStore;
    private byte[] encryption;
    private byte[] iv;


    public KeyStoreHelper() throws CertificateException,
            NoSuchAlgorithmException,
            KeyStoreException,
            IOException {
        init();
    }

    private void init() throws KeyStoreException,
            CertificateException,
            NoSuchAlgorithmException,
            IOException {
        keyStore = KeyStore.getInstance(KEY_ANDROID_KEYSTORE);
        keyStore.load(null);
    }


    public String decrypt(@NonNull final String secretKey,
                          @NonNull final byte[] encryptedData,
                          @NonNull final byte[] encryptionIv)
            throws UnrecoverableEntryException, NoSuchAlgorithmException, KeyStoreException,
            NoSuchProviderException, NoSuchPaddingException, InvalidKeyException, IOException,
            BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {

        final Cipher cipher = Cipher.getInstance(KEY_TRANSFORMATION);
        final GCMParameterSpec spec = new GCMParameterSpec(128, encryptionIv);
        cipher.init(Cipher.DECRYPT_MODE, getSecretKey(secretKey), spec);

        return new String(cipher.doFinal(encryptedData), UTF_8);
    }


    public byte[] encrypt(@NonNull final String secretKey,
                   @NonNull final String text)
            throws UnrecoverableEntryException, NoSuchAlgorithmException, KeyStoreException,
            NoSuchProviderException, NoSuchPaddingException, InvalidKeyException, IOException,
            InvalidAlgorithmParameterException, SignatureException, BadPaddingException,
            IllegalBlockSizeException {

        final Cipher cipher = Cipher.getInstance(KEY_TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, generateSecretKey(secretKey));

        iv = cipher.getIV();

        return (encryption = cipher.doFinal(text.getBytes(UTF_8)));
    }


    private SecretKey getSecretKey(@NonNull final String secretKey) throws NoSuchAlgorithmException,
            UnrecoverableEntryException, KeyStoreException {
        return ((KeyStore.SecretKeyEntry) keyStore.getEntry(secretKey, null)).getSecretKey();
    }


    private SecretKey generateSecretKey(@NonNull final String secretKey) throws NoSuchAlgorithmException,
            NoSuchProviderException, InvalidAlgorithmParameterException {

        final KeyGenerator keyGenerator = KeyGenerator
                .getInstance(KeyProperties.KEY_ALGORITHM_AES, KEY_ANDROID_KEYSTORE);

        keyGenerator.init(new KeyGenParameterSpec.Builder(secretKey,
                KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT)
                .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
                .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
                .build());

        return keyGenerator.generateKey();
    }


    /**
     * Initialize the {@link Cipher} instance with the created key in the {@link #createKey()}
     * method.
     *
     * @return {@code true} if initialization is successful, {@code false} if the lock screen has
     * been disabled or reset after the key was generated, or if a fingerprint got enrolled after
     * the key was generated.
     */
    public static Cipher initCipher(final String keyName) {
        try {
            KeyStore keyStore = getKeyStoreInstance();
            keyStore.load(null);
            SecretKey key = (SecretKey) keyStore.getKey(keyName, null);

            Cipher cipher = getCipherInstance();
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return cipher;
        } catch (KeyPermanentlyInvalidatedException e) {
            return null;
        } catch (KeyStoreException | CertificateException | UnrecoverableKeyException | IOException
                | NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException("Failed to init Cipher", e);
        }
    }


    /**
     * Creates a symmetric key in the Android Key Store which can only be used after the user has
     * authenticated with fingerprint.
     */
    public static void createKey(final String keyName) {
        // The enrolling flow for fingerprint. This is where you ask the user to set up fingerprint
        // for your flow. Use of keys is necessary if you need to know if the set of
        // enrolled fingerprints has changed.
        try {
            KeyStore keyStore = getKeyStoreInstance();
            KeyGenerator keyGenerator = getKeyGeneratorInstance();

            keyStore.load(null);
            // Set the alias of the entry in Android KeyStore where the key will appear
            // and the constrains (purposes) in the constructor of the Builder
            keyGenerator.init(new KeyGenParameterSpec.Builder(keyName,
                    KeyProperties.PURPOSE_ENCRYPT |
                            KeyProperties.PURPOSE_DECRYPT)
                    .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                    // Require the user to authenticate with a fingerprint to authorize every use
                    // of the key
                    .setUserAuthenticationRequired(true)
                    .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7)
                    .build());
            keyGenerator.generateKey();
        } catch (NoSuchAlgorithmException | InvalidAlgorithmParameterException
                | CertificateException | IOException e) {
            throw new RuntimeException(e);
        }
    }


    private static KeyStore getKeyStoreInstance() {
        try {
            return KeyStore.getInstance(KEY_ANDROID_KEYSTORE);
        } catch (KeyStoreException e) {
            throw new RuntimeException("Failed to get an instance of KeyStore", e);
        }
    }

    private static KeyGenerator getKeyGeneratorInstance() {
        try {
            return KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, KEY_ANDROID_KEYSTORE);
        } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
            throw new RuntimeException("Failed to get an instance of KeyGenerator", e);
        }
    }

    private static Cipher getCipherInstance() {
        try {
            return Cipher.getInstance(KeyProperties.KEY_ALGORITHM_AES + "/"
                    + KeyProperties.BLOCK_MODE_CBC + "/"
                    + KeyProperties.ENCRYPTION_PADDING_PKCS7);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            throw new RuntimeException("Failed to get an instance of Cipher", e);
        }
    }
}
