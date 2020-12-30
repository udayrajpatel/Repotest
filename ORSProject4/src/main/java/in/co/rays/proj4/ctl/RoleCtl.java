package in.co.rays.proj4.ctl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.bean.BaseBean;
import in.co.rays.proj4.bean.RoleBean;
import in.co.rays.proj4.model.CollegeModel;
import in.co.rays.proj4.model.RoleModel;
import in.co.rays.proj4.model.SubjectModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import in.co.rays.proj4.util.PropertyReader;
import in.co.rays.proj4.util.ServletUtility;

/**
 * Role functionality Controller. Performs operation for add, update and get
 * Role
 * 
 * @author uday
 *
 */
@WebServlet(name="RoleCtl",urlPatterns={"/ctl/RoleCtl"})
public class RoleCtl extends BaseCtl 
{
	 private static final long serialVersionUID = 1L;

	    private static Logger log = Logger.getLogger(RoleCtl.class);
	    
	    protected void preload(HttpServletRequest request)
		{
    		log.debug("Rolelist ctl preload debug started");
			RoleModel RoleModel=new RoleModel();
			
			try
			{
				List list=RoleModel.list();
				request.setAttribute("RoleList", list);
				
				
				
			}
			catch (Exception e) 
			{
			log.error(e);	
			}
			log.debug("Rolelist ctl preload debug completed");
		}

	    @Override
	    protected boolean validate(HttpServletRequest request) {

	        log.debug("RoleCtl Method validate Started");

	        boolean pass = true;

	        if (DataValidator.isNull(request.getParameter("name"))) {
	            request.setAttribute("name",
	                    PropertyReader.getValue("error.require", "Name"));
	            pass = false;
	        }else if (!DataValidator.isNameNumber(request.getParameter("name"))) {
	            request.setAttribute("name",
	                    PropertyReader.getValue("error.pass", "Role Name"));
	            pass = false;
	        }

	        if (DataValidator.isNull(request.getParameter("description"))) {
	            request.setAttribute("description",
	                    PropertyReader.getValue("error.require", "Description"));
	            pass = false;
	        }

	        log.debug("RoleCtl Method validate Ended");

	        return pass;
	    }

	    @Override
	    protected BaseBean populateBean(HttpServletRequest request) {

	        log.debug("RoleCtl Method populatebean Started");

	        RoleBean bean = new RoleBean();

	        bean.setId(DataUtility.getLong(request.getParameter("id")));

	        bean.setName(DataUtility.getString(request.getParameter("name")));
	        bean.setDescription(DataUtility.getString(request.getParameter("description")));

	        populateDTO(bean, request);

	        log.debug("RoleCtl Method populatebean Ended");

	        return bean;
	    }

	    /**
	     * Contains Display logics
	     */
	    protected void doGet(HttpServletRequest request,
	            HttpServletResponse response) throws ServletException, IOException {
	        log.debug("RoleCtl Method doGet Started");

	        System.out.println("In do get");

	        String op = DataUtility.getString(request.getParameter("operation"));

	        // get model
	        RoleModel model = new RoleModel();

	        long id = DataUtility.getLong(request.getParameter("id"));
	        if (id > 0 || op != null) {
	            RoleBean bean;
	            try {
	                bean = model.findByPK(id);
	                ServletUtility.setBean(bean, request);
	            } catch (ApplicationException e) {
	                log.error(e);
	                ServletUtility.handleException(e, request, response);
	                return;
	            }
	        }
	        ServletUtility.forward(getView(), request, response);
	        log.debug("RoleCtl Method doGetEnded");
	    }

	    /**
	     * Contains Submit logics
	     */
	    protected void doPost(HttpServletRequest request,
	            HttpServletResponse response) throws ServletException, IOException {
	        log.debug("RoleCtl Method doGet Started");

	        System.out.println("In do post of role ctl");

	        String op = DataUtility.getString(request.getParameter("operation"));

	        // get model
	        RoleModel model = new RoleModel();

	        long id = DataUtility.getLong(request.getParameter("id"));

	        if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {

	            RoleBean bean = (RoleBean) populateBean(request);

	            try {
	                if (id > 0) {
	                    model.update(bean);
	                    ServletUtility.setBean(bean, request);
	    				ServletUtility.setSuccessMessage("Data Successfully Updated....!!!", request);
	                } else {
	                    long pk = model.add(bean);
	                    bean.setId(pk);
	                    ServletUtility.setSuccessMessage("Data is successfully saved", request);

	                }

	                //ServletUtility.setBean(bean, request);
	               
	            } catch (ApplicationException e) {
	                log.error(e);
	                ServletUtility.handleException(e, request, response);
	                return;
	            } catch (DuplicateRecordException e) {
	                ServletUtility.setBean(bean, request);
	                ServletUtility.setErrorMessage("Role already exists", request);
	            }

	        } else if (OP_DELETE.equalsIgnoreCase(op)) {

	            RoleBean bean = (RoleBean) populateBean(request);
	            try {
	                model.delete(bean);
	                ServletUtility.redirect(ORSView.ROLE_LIST_CTL, request,
	                        response);
	                return;
	            } catch (ApplicationException e) {
	                log.error(e);
	                ServletUtility.handleException(e, request, response);
	                return;
	            }

	        } else if (OP_RESET.equalsIgnoreCase(op)) {

	            ServletUtility.redirect(ORSView.ROLE_CTL, request, response); // it will destroy previous request
	            return;   // redirect used here instead of forward bcz redirect carry new request each and every time

	        }
	        
	        else if(OP_CANCEL.equalsIgnoreCase(op))
			  {
				ServletUtility.redirect(ORSView.ROLE_LIST_CTL, request, response);
				return;
			  }

	        ServletUtility.forward(getView(), request, response);

	        log.debug("RoleCtl Method doPOst Ended");
	    }

	@Override
	protected String getView() 
	{
	return ORSView.ROLE_VIEW;
	}

}
