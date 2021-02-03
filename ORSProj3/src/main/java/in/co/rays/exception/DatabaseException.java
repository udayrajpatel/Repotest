package in.co.rays.exception;

/**
 * DatabaseException is propogated by DAO classes when an unhandled Database
 * exception occurred
 * 
 * @author uday
 *
 */

public class DatabaseException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * @param msg
     *            : Error message
     */
    public DatabaseException(String msg) {
        super(msg);
    }
}
