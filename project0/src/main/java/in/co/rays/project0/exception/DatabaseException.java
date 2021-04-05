package in.co.rays.project0.exception;

/**
 * 
 * The Class DatabaseException.
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
     * param msg
     *   : Error message
     */
    public DatabaseException(String msg) {
        super(msg);
    }
    
}
