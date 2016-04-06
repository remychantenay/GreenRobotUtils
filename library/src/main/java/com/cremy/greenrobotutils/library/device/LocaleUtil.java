package com.cremy.greenrobotutils.library.device;

import android.content.Context;


/**
 * Allows to get some information regarding the device locale
 * 
 * @author CHANTENAY Remy
 * @mail remy.chantenay at gmail.com
 *
 */
public final class LocaleUtil {
	public final static String TAG = "LocaleUtil";
	
	private final static String FR = "fr";
	private final static String EN = "en";
	private final static String FRA = "FRA";
	private final static String ENG = "ENG";
	
	/**
	 * Allows to get the locale of the device
	 * (e.g. en_GB)
	 * @param _context
	 * @return locale
	 */
	public static String getLocale(Context _context)
	{
		if (_context==null) {
			throw new NullPointerException("Context should not be NULL");
		}
		final String locale = _context.getResources().getConfiguration().locale.toString();
		return locale;
	}
	
	
	/**
	 * Allows to get the locale prefix only
	 * For instance : "en_GB" locale will return "en" only
	 * 
	 * @param _context
	 * @return locale prefix
	 */
	public static String getLocalePrefix(Context _context)
	{
		if (_context==null) {
			throw new NullPointerException("Context should not be NULL");
		}
		try {
			String locale = _context.getResources().getConfiguration().locale.toString();
			return locale.substring(0, 2);
		} catch (Exception e) {
			throw e;
		} catch (Error e) { throw e;}
	}
}
