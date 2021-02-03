package in.co.rays.exception;
/**
 * DuplicateRecordException thrown when a duplicate record occurred
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
     * @param msg
     *            error message
     */
    public DuplicateRecordException(String msg) {
        super(msg);
    }

}
