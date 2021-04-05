package in.co.rays.project0.exception;


/**
 * The Class DuplicateRecordException.
 * 
 * @author uday
 *
 */
public class DuplicateRecordException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * param msg
     *            error message
     */
    public DuplicateRecordException(String msg) {
        super(msg);
    }

}
