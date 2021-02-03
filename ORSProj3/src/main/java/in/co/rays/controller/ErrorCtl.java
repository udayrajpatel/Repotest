package in.co.rays.controller;
import in.co.rays.util.ServletUtility;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 * 
 * Error functionality Controller. Performs operation for Error Pages
 * 
 * @author uday
 *
 */

@WebServlet(name = "ErrorCtl", urlPatterns = { "/ErrorCtl" })
public class ErrorCtl extends BaseCtl {

	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(ErrorCtl.class);

	/**
	 * Contains Display logics
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		log.debug("ErrorCtl Method doGet Started");

		ServletUtility.forward(getView(), request, response);

		log.debug("ErrorCtl Method doGetEnded");
		
	}

	/**
	 * 
	 * Contains Submit logics
	 * 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("ErrorCtl Method doGet Started");

		ServletUtility.forward(getView(), request, response);

		log.debug("ErrorCtl Method doPost Ended");
	}

	@Override
	protected String getView() {
		return ORSView.ERROR_VIEW;
	}

}