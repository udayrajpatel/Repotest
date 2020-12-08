package in.co.rays.controller;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.util.ServletUtility;

// TODO: Auto-generated Javadoc
/**
 * Welcome functionality Controller. Performs operation for Show Welcome page
 *  
 * @author SUNRAYS Technologies
 */
 @WebServlet(name="WelcomeCtl",urlPatterns={"/WelcomeCtl"})
public class WelcomeCtl extends BaseCtl {
 
    /** The log. */
    private static Logger log = Logger.getLogger(WelcomeCtl.class);
 
    /**
     * Do get.
     *
     * @param request the request
     * @param response the response
     * @throws ServletException the servlet exception
     * @throws IOException Signals that an I/O exception has occurred.
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        log.debug("WelcomeCtl Method doGet Started");
 
        ServletUtility.forward(ORSView.WELCOME_VIEW, request, response);
 
        log.debug("WelcomeCtl Method doGet Ended");
    }
 
    /* (non-Javadoc)
     * @see in.co.rays.controller.BaseCtl#getView()
     */
    @Override
    protected String getView() {
        return ORSView.WELCOME_VIEW;
    }
 
}
