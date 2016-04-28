package com.cremy.greenrobotutils.library.storage.gson;

/** Allow to ensure that the serialize/unserialize process will be respected by the class
 * 
 * @author Remy
 *
 */
public interface GsonInterface {

	public String toJSON(Class<?> _class);
	public Object fromJSON(String _json, Class<?> _class);
}
