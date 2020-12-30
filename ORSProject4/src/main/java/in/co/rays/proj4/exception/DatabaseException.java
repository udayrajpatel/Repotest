package in.co.rays.proj4.exception;

/**
 * DatabaseException is propogated by DAO classes when an unhandled Database
 * exception occurred
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 * 
 */

public class DatabaseException extends Exception {

	/**
	 * @param msg
	 */
	public DatabaseException(String msg) {
		super(msg);
	}

}
