package in.co.rays.controller;
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
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
/**
 * Marksheet functionality Controller. Performs operation for add, update,
 * delete and get Marksheet
 * 
 * @author uday
 *
 */

@WebServlet(name = "MarksheetCtl", urlPatterns = { "/ctl/MarksheetCtl" })

public class MarksheetCtl extends BaseCtl {

	/**
	 * Default serial version ID
	 */
	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(MarksheetCtl.class);

	@Override
	protected void preload(HttpServletRequest request) {
		StudentModelInt model = ModelFactory.getInstance().getStudentModel();
		try {
			@SuppressWarnings("rawtypes")
			List l = model.list();
			request.setAttribute("studentList", l);
		} catch (ApplicationException e) {
			log.error(e);
		}

	}

	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("MarksheetCtl Method validate Started");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("studentId"))) {
			request.setAttribute("studentId", PropertyReader.getValue("error.require", "Student Name"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("rollNo"))) {
			request.setAttribute("rollNo", PropertyReader.getValue("error.require", "Roll Number"));
			pass = false;
		} else if (!DataValidator.isRollNo(request.getParameter("rollNo"))) {
			request.setAttribute("rollNo", "Roll No is invalid");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("physics"))) {
			request.setAttribute("physics", PropertyReader.getValue("error.require", "Marks"));
			pass = false;
		} else if (DataValidator.isNotNull(request.getParameter("physics"))
				&& !DataValidator.isInteger(request.getParameter("physics"))) {
			request.setAttribute("physics", PropertyReader.getValue("error.integer", "Marks"));
			pass = false;
		} else if (DataUtility.getInt(request.getParameter("physics")) > 100
				|| DataUtility.getInt(request.getParameter("physics")) < 0) {
			request.setAttribute("physics", "Marks should be in 0 to 100");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("chemistry"))) {
			request.setAttribute("chemistry", PropertyReader.getValue("error.require", "Marks"));
			pass = false;
		} else if (DataValidator.isNotNull(request.getParameter("chemistry"))
				&& !DataValidator.isInteger(request.getParameter("chemistry"))) {
			request.setAttribute("chemistry", PropertyReader.getValue("error.integer", "Marks"));
			pass = false;
		} else if (DataUtility.getInt(request.getParameter("chemistry")) > 100
				|| DataUtility.getInt(request.getParameter("chemistry")) < 0) {
			request.setAttribute("chemistry", "Marks should be in 0 to 100");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("maths"))) {
			request.setAttribute("maths", PropertyReader.getValue("error.require", "Marks"));
			pass = false;
		} else if (DataValidator.isNotNull(request.getParameter("maths"))
				&& !DataValidator.isInteger(request.getParameter("maths"))) {
			request.setAttribute("maths", PropertyReader.getValue("error.integer", "Marks"));
			pass = false;
		} else if (DataUtility.getInt(request.getParameter("maths")) > 100
				|| DataUtility.getInt(request.getParameter("maths")) < 0) {
			request.setAttribute("maths", "Marks should be in 0 to 100");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("studentId"))) {
			request.setAttribute("studentId", PropertyReader.getValue("error.require", "Student Name"));
			pass = false;
		}

		log.debug("MarksheetCtl Method validate Ended");

		return pass;
	}

	@Override
	protected BaseDTO populateDTO(HttpServletRequest request) {

		log.debug("MarksheetCtl Method populatedto Started");

		MarksheetDTO dto = new MarksheetDTO();

		dto.setId(DataUtility.getLong(request.getParameter("id")));

		dto.setRollNo(DataUtility.getString(request.getParameter("rollNo")));

		dto.setName(DataUtility.getString(request.getParameter("name")));

		if (request.getParameter("physics") != null && request.getParameter("physics").length() != 0) {
			dto.setPhysics(DataUtility.getInt(request.getParameter("physics")));
		}
		if (request.getParameter("chemistry") != null && request.getParameter("chemistry").length() != 0) {
			dto.setChemistry(DataUtility.getInt(request.getParameter("chemistry")));
		}
		if (request.getParameter("maths") != null && request.getParameter("maths").length() != 0) {
			dto.setMaths(DataUtility.getInt(request.getParameter("maths")));
		}
		dto.setStudentId(DataUtility.getLong(request.getParameter("studentId")));

		populateDTO(dto, request);

		System.out.println("Population done");

		log.debug("MarksheetCtl Method populatedto Ended");

		return dto;
	}

	/**
	 * Contains Display logics
	 */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("MarksheetCtl Method doGet Started");
		System.out.println("doget run");
		@SuppressWarnings("unused")
		
		String op = DataUtility.getString(request.getParameter("operation"));
		
		MarksheetModelInt model = ModelFactory.getInstance().getMarksheetModel();
		
		long id = DataUtility.getLong(request.getParameter("id"));
		
		if (id > 0) {
			
			MarksheetDTO dto;
			try {
				dto = model.findByPK(id);
				
				System.out.println("Id = " + id + "  in dto ="+ dto.getId()+"|||" + dto);
				
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

	/**
	 * Contains Submit logics
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.debug("MarksheetCtl Method doPost Started");

		String op = DataUtility.getString(request.getParameter("operation"));
		// get model
		
		MarksheetModelInt model = ModelFactory.getInstance().getMarksheetModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		if (OP_SAVE.equalsIgnoreCase(op)) {

			MarksheetDTO dto = (MarksheetDTO) populateDTO(request);
			
			try {
				long pk = model.add(dto);
				dto.setId(pk);
				
         System.out.println("i am in dopost of save operation");
				
                ServletUtility.setDto(dto, request);
				ServletUtility.setSuccessMessage("Data is successfully saved", request);

				
			} catch (ApplicationException e) {
				
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
				
			} catch (DuplicateRecordException e) {
				ServletUtility.setDto(dto, request);
				ServletUtility.setErrorMessage("Roll no already exists", request);
				
			}

		} else if (OP_UPDATE.equalsIgnoreCase(op)) {

			MarksheetDTO dto = (MarksheetDTO) populateDTO(request);
			try {
				
				if (id > 0) {
					
					model.update(dto);
					
				}
				
				ServletUtility.setDto(dto, request);
				
				ServletUtility.setSuccessMessage("Data is successfully updated", request);
				

			} catch (ApplicationException e) {
				
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
				
			} catch (DuplicateRecordException e) {
				
				ServletUtility.setDto(dto, request);
				ServletUtility.setErrorMessage("Roll no already exists", request);
				
			}

		} else if (OP_DELETE.equalsIgnoreCase(op)) {

			MarksheetDTO dto = (MarksheetDTO) populateDTO(request);
			System.out.println("in try");
			try {
				model.delete(dto);
				ServletUtility.redirect(ORSView.MARKSHEET_LIST_CTL, request, response);
				 ServletUtility.setSuccessMessage("Data is successfully deleted", request);
				System.out.println("in try");
				return;
			} catch (ApplicationException e) {
				System.out.println("in catch");
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}

		} else if (OP_CANCEL.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.MARKSHEET_LIST_CTL, request, response);
			return;

		} else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.MARKSHEET_CTL, request, response);
			return;
		}
		ServletUtility.forward(getView(), request, response);

		log.debug("MarksheetCtl Method doPost Ended");
	}

	@Override
	protected String getView() {
		
		return ORSView.MARKSHEET_VIEW;
		
	}

}
