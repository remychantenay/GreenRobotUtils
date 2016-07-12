package com.cremy.greenrobotutils.library.util;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by remychantenay on 12/07/2016.
 */
public class DateUtil {
    private final static String TAG = "DateUtil";

    public final static String INPUT_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";//2015-08-23T16:15:03Z
    public final static String OUTPUT_FORMAT = "EEEE, d MMM";// Tuesday, 28 Jun


    /**
     * Allows to format a given date
     * @param _dateToFormat
     * @return
     */
    public static String formatTransactionDate(String _dateToFormat) {
        SimpleDateFormat inputDateFormat = new SimpleDateFormat(INPUT_FORMAT);
        SimpleDateFormat outputDateFormat = new SimpleDateFormat(OUTPUT_FORMAT);

        return formatTransactionDate(_dateToFormat, inputDateFormat, outputDateFormat);
    }

    /**
     * Allows to format a given date with SimpleDateFormat given for input and output result
     * @param _dateToFormat
     * @param _inputDateFormat
     * @param _outputDateFormat
     * @return
     */
    public static String formatTransactionDate(String _dateToFormat,
                                               SimpleDateFormat _inputDateFormat,
                                               SimpleDateFormat _outputDateFormat) {

        try {
            Date date = _inputDateFormat.parse(_dateToFormat.replace("z",""));
            return _outputDateFormat.format(date);
        }
        catch (ParseException e) {
            e.printStackTrace();
            Log.e(TAG, "Unable to parse the given date.");
        }

        return null;
    }
}
