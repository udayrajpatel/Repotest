package in.co.rays.controller;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import in.co.rays.util.ServletUtility;

@WebServlet(name="WelcomeCtl",urlPatterns={"/WelcomeCtl"})
public class WelcomeCtl extends BaseCtl{

	/**
	 * 
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
    
	private static Logger log=Logger.getLogger(WelcomeCtl.class);
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
		
		log.debug("welcome doget method is strated");
		ServletUtility.forward(getView(), request, response);
		log.debug("WelcomeCtl method doGet Ended");
		
		
	}
	@Override
	protected String getView() {
		
		return ORSView.WELCOME_VIEW;
		
	}

	
}
