package com.cremy.greenrobotutils.library.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Remy on 21/09/2014.
 */
public final class MD5Helper {

    private static final String ALGORITHM = "MD5";


    /**
     * Allows to generate the MD5 sum for digest
     * @param _strToHash
     * @return
     */
    public static String getMD5Hash(String _strToHash) {
        try {
            // Create MD5 Hash
            MessageDigest digest = MessageDigest.getInstance(ALGORITHM);
            digest.update(_strToHash.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            final int length = messageDigest.length;
            for (int i = 0; i < length; i++) {
                String h = Integer.toHexString(0xFF & messageDigest[i]);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }

            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
