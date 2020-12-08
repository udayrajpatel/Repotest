package in.co.rays.controller;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.dto.BaseDTO;
import in.co.rays.dto.MarksheetDTO;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.model.MarksheetModelInt;
import in.co.rays.model.ModelFactory;
import in.co.rays.model.StudentModelInt;
import in.co.rays.util.DataUtility;
import in.co.rays.util.DataValidator;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;

// TODO: Auto-generated Javadoc
/**
 * The Class MarksheetCtl.
 */
@WebServlet(name="MarksheetCtl",urlPatterns={"/ctl/MarksheetCtl"})
public class MarksheetCtl extends BaseCtl{
	
	/** The log. */
	private static Logger log = Logger.getLogger(MarksheetCtl.class);

	/* (non-Javadoc)
	 * @see in.co.rays.controller.BaseCtl#preload(javax.servlet.http.HttpServletRequest)
	 */
	protected void preload(HttpServletRequest request) {
		StudentModelInt model =ModelFactory.getInstance().getStudentModel();
		try {
			List studentList = model.list();
			request.setAttribute("studentList", studentList);
		} catch (ApplicationException e) {
			log.error(e);
		}
	}
	
	
	
	/* (non-Javadoc)
	 * @see in.co.rays.controller.BaseCtl#validate(javax.servlet.http.HttpServletRequest)
	 */
	protected boolean validate(HttpServletRequest request) {
		log.debug("MarksheetCtl Method validate Started");
		boolean pass = true;
		
		if (DataValidator.isNull(request.getParameter("rollNo"))) {
			request.setAttribute("rollNo", PropertyReader.getValue("error.require", "Roll Number"));
			pass = false;
		}
		else if(!DataValidator.isRollNo(request.getParameter("rollNo")))
		{
			request.setAttribute("rollNo","RollNo should be in the formate of 000XX00");
			pass = false ;
		}
		
		if( DataValidator.isNull(request.getParameter("physics"))){
			
			request.setAttribute("physics",PropertyReader.getValue("error.require", "physics") );
		pass=false;
		}
		else if (DataValidator.isNotNull(request.getParameter("physics"))&&!DataValidator.isInteger(request.getParameter("physics"))) 
		{
			request.setAttribute("physics", PropertyReader.getValue("error.integer", "Marks"));
			pass = false;
		}
		else if (DataValidator.isNotNull(request.getParameter("physics"))&&DataUtility.getInt(request.getParameter("physics")) > 100) {
			request.setAttribute("physics", "Marks can not be greater than 100");
			pass = false;
		} 

        if( DataValidator.isNull(request.getParameter("chemistry"))){
			
			request.setAttribute("chemistry",PropertyReader.getValue("error.require", "chemistry") );
		pass=false;
		}
        else if (DataValidator.isNotNull(request.getParameter("chemistry"))&&!DataValidator.isInteger(request.getParameter("chemistry"))) 
		{
			request.setAttribute("chemistry", PropertyReader.getValue("error.integer", "Marks"));
			pass = false;
		}
        else if (DataValidator.isNotNull(request.getParameter("chemistry"))&&DataUtility.getInt(request.getParameter("chemistry")) > 100) {
			request.setAttribute("chemistry", "Marks can not be greater than 100");
			pass = false;
		} 

        if( DataValidator.isNull(request.getParameter("maths"))){
			
			request.setAttribute("maths",PropertyReader.getValue("error.require", "maths") );
		pass=false;
		}
        else if (DataValidator.isNotNull(request.getParameter("maths"))&&!DataValidator.isInteger(request.getParameter("maths"))) 
		{
			request.setAttribute("maths", PropertyReader.getValue("error.integer", "Marks"));
			pass = false;
		}
        else if (DataValidator.isNotNull(request.getParameter("maths"))&&DataUtility.getInt(request.getParameter("maths")) > 100) {
			request.setAttribute("maths", "Marks can not be greater than 100");
			pass = false;
		} 


		if (DataValidator.isNull(request.getParameter("studentId"))) {
			request.setAttribute("studentId", PropertyReader.getValue("error.require", "Student Name"));
			pass = false;
		}

		log.debug("MarksheetCtl Method validate Ended");

		return pass;
	}
	
	
	/* (non-Javadoc)
	 * @see in.co.rays.controller.BaseCtl#populateDTO(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected BaseDTO populateDTO(HttpServletRequest request) {
		log.debug("MarksheetCtl Method populatebean Started");
		
		MarksheetDTO dto = new MarksheetDTO();
		
		dto.setId(DataUtility.getLong(request.getParameter("id")));

		dto.setRollNo(DataUtility.getString(request.getParameter("rollNo")));

        dto.setPhysics(DataUtility.getInt(request.getParameter("physics")));

		dto.setChemistry(DataUtility.getInt(request.getParameter("chemistry")));

		dto.setMaths(DataUtility.getInt(request.getParameter("maths")));

		dto.setStudentId(DataUtility.getLong(request.getParameter("studentId")));
		
		log.debug("MarksheetCtl Method populatebean Ended");
		return dto;

	}

	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	     log.debug("MarksheetCtl Method doGet Started");
	     String op=DataUtility.getString(request.getParameter("operation"));
	     MarksheetModelInt model=ModelFactory.getInstance().getMarksheetModel();
	     long id=DataUtility.getLong(request.getParameter("id"));
	     
	     
	     if (id > 0) {
	            MarksheetDTO dto;
	            try {
	                dto = model.findByPK(id);
	                ServletUtility.setDto(dto, request);
	            } catch (ApplicationException e) {
	                log.error(e);
	                ServletUtility.handleException(e, request, response);
	                return;
	            }
	        }
	        ServletUtility.forward(getView(), request, response);
	        log.debug("MarksheetCtl Method doGet Ended");
}
	
	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("MarksheetCtl Method doPost Started");
        String op = DataUtility.getString(request.getParameter("operation"));

        MarksheetModelInt model=ModelFactory.getInstance().getMarksheetModel();
       
        long id = DataUtility.getLong(request.getParameter("id"));

        
        if (OP_SAVE.equalsIgnoreCase(op)||OP_UPDATE.equalsIgnoreCase(op)) {

            MarksheetDTO dto = (MarksheetDTO) populateDTO(request);
            try {
               
            	if (id > 0) {
                
            		model.update(dto);
                    
            		ServletUtility.setSuccessMessage("Data is successfully Updated",request);

                 } else {
                 
                	long pk = model.add(dto);
                    ServletUtility.setSuccessMessage("Data is successfully saved",request);

                  /*  dto.setId(pk);*/
                }
                ServletUtility.setDto(dto, request);
                
            } catch (ApplicationException e) {
                log.error(e);
                ServletUtility.handleException(e, request, response);
                return;
            } catch (DuplicateRecordException e) {
                ServletUtility.setDto(dto, request);
                ServletUtility.setErrorMessage("Roll Number already exists",request);
            }

        } else if (OP_DELETE.equalsIgnoreCase(op)) {

            MarksheetDTO dto = (MarksheetDTO) populateDTO(request);
            System.out.println("in try");
            try {
                model.delete(dto);
                ServletUtility.redirect(ORSView.MARKSHEET_LIST_CTL, request,
                        response);
                System.out.println("in try");
                return;
            } catch (ApplicationException e) {
                System.out.println("in catch");
                log.error(e);
                ServletUtility.handleException(e, request, response);
                return;
            }

        } 
        else if(OP_CANCEL.equalsIgnoreCase(op)) {

            ServletUtility.redirect(ORSView.MARKSHEET_LIST_CTL, request,
                    response);
            return;

        }
        else if(OP_RESET.equalsIgnoreCase(op)){
        	ServletUtility.redirect(ORSView.MARKSHEET_CTL, request, response);
        	return;
        }
		
        ServletUtility.forward(getView(), request, response);

        log.debug("MarksheetCtl Method doPost Ended");

	
	}


	/* (non-Javadoc)
	 * @see in.co.rays.controller.BaseCtl#getView()
	 */
	@Override
	protected String getView() {
		return ORSView.MARKSHEET_VIEW;
	}


}
