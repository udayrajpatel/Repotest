package in.co.rays.exception;

// TODO: Auto-generated Javadoc
/**
* ApplicationException is propogated from Service classes when an business
* logic exception occurered.
*
* @author 
*
*/
public class ApplicationException extends Exception {

   /**
    * Instantiates a new application exception.
    *
    * @param msg Error message
    */
   public ApplicationException(String msg) {
       super(msg);
   }
}