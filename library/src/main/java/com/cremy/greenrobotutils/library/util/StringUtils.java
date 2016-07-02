package com.cremy.greenrobotutils.library.util;


import java.util.Map;
import java.util.NavigableMap;
import java.util.Random;
import java.util.TreeMap;

/**
 * Allows to perform some things on strings
 * @author CHANTENAY Remy
 * @mail remy.chantenay at gmail.com
 *
 */
public class StringUtils {

	public static final String NONE_VALUE = "none";

    private static final CharSequence SET_ALPHABET = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvXxYyZz";
    private static final String SET_NUMERIC = "0123456789";
    private static final String SET_ALPHANUMERIC = SET_ALPHABET+SET_NUMERIC;

    public static final String COMMA = ",";

    /**
     * Allows to generate a random ALPHABETICAL character
     * @return
     */
    public static Character getRandomAlphabeticalCharacter() {
        Random r = new Random();
        final int index = r.nextInt(SET_ALPHABET.length());
        return SET_ALPHABET.charAt(index);
    }


    /**
     * Allows to generate a random ALPHANUMERIC character
     * @return
     */
    public static Character getRandomAlphaNumericCharacter() {
        Random r = new Random();
        final int index = r.nextInt(SET_ALPHANUMERIC.length());
        return SET_ALPHANUMERIC.charAt(index);
    }


    /**
     * Allows to generate a random set of ALPHABETICAL characters
     * @param _count is the number of wanted characters
     * @return
     */
    public static String getManyRandomAlphabeticalCharacter(int _count) {
        if (_count <= 0) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        Random r = new Random();
        for (int i = 0; i < _count; i++) {
            final int index = r.nextInt(SET_ALPHABET.length());
            sb.append(SET_ALPHABET.charAt(index));
        }

        return sb.toString();
    }



    private static final NavigableMap<Long, String> suffixes = new TreeMap<>();
    static {
        suffixes.put(1_000L, "k");
        suffixes.put(1_000_000L, "M");
        suffixes.put(1_000_000_000L, "G");
        suffixes.put(1_000_000_000_000L, "T");
        suffixes.put(1_000_000_000_000_000L, "P");
        suffixes.put(1_000_000_000_000_000_000L, "E");
    }


    /**
     * Method allowing to abbreviate a large numeric value e.g. :
     * 1000 to 1k
     * 5821 to 5.8k
     * 10500 to 10k
     * 101800 to 101k
     * 2000000 to 2m
     * 7800000 to 7.8m
     * 92150000 to 92m
     * 123200000 to 123m
     * @param value
     * @return
     */
    public static String AbbreviateLargeNumericValue(long value) {
        //Long.MIN_VALUE == -Long.MIN_VALUE so we need an adjustment here
        if (value == Long.MIN_VALUE) return AbbreviateLargeNumericValue(Long.MIN_VALUE + 1);
        if (value < 0) return "-" + AbbreviateLargeNumericValue(-value);
        if (value < 1000) return Long.toString(value); //deal with easy case

        Map.Entry<Long, String> e = suffixes.floorEntry(value);
        Long divideBy = e.getKey();
        String suffix = e.getValue();

        long truncated = value / (divideBy / 10); //the number part of the output times 10
        boolean hasDecimal = truncated < 100 && (truncated / 10d) != (truncated / 10);
        return hasDecimal ? (truncated / 10d) + suffix : (truncated / 10) + suffix;
    }


    public static String AbbreviateLargeNumericValue(int value) {
        return AbbreviateLargeNumericValue((long) value);
    }


    public static String unSlash(String _str) {
        return _str.replaceAll("\\\\", "");
    }


    /**
     * Allows to get a comma separated String from a given numeric value
     * e.g.
     * 1000 -> 1,000
     * 10000 -> 10,000
     * 100000 -> 100,000
     * 1000000 -> 1,000,000
     * @return
     */
    public static String separateNumericValueWithComma(final int _val) {
        String strValue = "0";

        try {
            strValue = String.valueOf(_val);
            final int strLength = strValue.length();
            if (strLength == 9) {
                strValue = strValue.substring(0, 3) + "," + strValue.substring(3, strValue.length());
                strValue = strValue.substring(0, 7) + "," + strValue.substring(7, strValue.length());
                return strValue;
            }

            if (strLength == 8) {
                strValue = strValue.substring(0, 2) + "," + strValue.substring(2, strValue.length());
                strValue = strValue.substring(0, 6) + "," + strValue.substring(6, strValue.length());
                return strValue;
            }
            if (strLength == 7) {
                strValue = strValue.substring(0, 1) + "," + strValue.substring(1, strValue.length());
                strValue = strValue.substring(0, 5) + "," + strValue.substring(5, strValue.length());
                return strValue;
            }
            if (strLength == 6) {
                strValue = strValue.substring(0, 3) + "," + strValue.substring(3, strValue.length());
                return strValue;
            }
            if (strLength == 5) {
                strValue = strValue.substring(0, 2) + "," + strValue.substring(2, strValue.length());
                return strValue;
            }
            if (strLength == 4) {
                strValue = strValue.substring(0, 1) + "," + strValue.substring(1, strValue.length());
                return strValue;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return strValue;
    }

    /**
     * Allows to transform a given String setting every character in lower case except the first one
     * e.g. HELLO -> Hello
     * e.g. hELLO -> Hello
     * ...
     * @param _string
     * @return
     */
    public static String toLowerCaseExceptFirstChar(String _string) {
        char c[] = _string.toLowerCase().toCharArray();
        c[0] = Character.toUpperCase(c[0]);
        _string = String.copyValueOf(c);
        return _string;
    }
}
