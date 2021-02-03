package in.co.rays.controller;
import in.co.rays.dto.BaseDTO;
import in.co.rays.dto.FacultyDTO;
import in.co.rays.exception.ApplicationException;
import in.co.rays.model.FacultyModelInt;
import in.co.rays.model.ModelFactory;
import in.co.rays.model.SubjectModelInt;
import in.co.rays.util.DataUtility;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Faculty List functionality Controller. Performs operation for list, search
 * and delete operations of Faculty
 * 
 * @author uday
 *
 */
@WebServlet(name = "FacultyListCtl", urlPatterns = { "/ctl/FacultyListCtl" })
public class FacultyListCtl extends BaseCtl {

	/**
	 * Default serial version ID
	 */
	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(FacultyListCtl.class);

	@SuppressWarnings("rawtypes")
	@Override
	protected void preload(HttpServletRequest request) {
		SubjectModelInt subjectModel = ModelFactory.getInstance().getSubjectModel();

		try {
			List subjectList = subjectModel.list();
			request.setAttribute("subjectList", subjectList);

		} catch (ApplicationException e) {
			log.error(e);
		}

	}

	@Override
	protected BaseDTO populateDTO(HttpServletRequest request) {

		FacultyDTO dto = new FacultyDTO();

		dto.setFirstName(DataUtility.getString(request.getParameter("firstName")));
		dto.setLastName(DataUtility.getString(request.getParameter("lastName")));
		dto.setEmail(DataUtility.getString(request.getParameter("email")));
		dto.setSubjectId(DataUtility.getLong(request.getParameter("subject")));

		return dto;
	}

	/**
	 * Contains Display logics
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("FacultyListCtl doGet Start");
		List list = null;
		List next = null;

		int pageNo = 1;

		int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));

		FacultyDTO dto = (FacultyDTO) populateDTO(request);

		@SuppressWarnings("unused")
		String op = DataUtility.getString(request.getParameter("operation"));

		FacultyModelInt model = ModelFactory.getInstance().getFacultyModel();
		try {
			list = model.search(dto, pageNo, pageSize);
			next = model.search(dto, pageNo + 1, pageSize);
			// ServletUtility.setList(list, request);
			if (list == null || list.size() == 0) {
				ServletUtility.setErrorMessage("No record found ", request);
			}
			request.setAttribute("nextListSize", next.size());
			ServletUtility.setList(list, request);
			ServletUtility.setDto(dto, request);
			Collections.sort(list);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.forward(getView(), request, response);

		} catch (ApplicationException e) {
			log.error(e);
			e.printStackTrace();
			ServletUtility.handleException(e, request, response);
			return;
		}
		log.debug("FacultyListCtl doGet End");
	}

	/**
	 * Contains Submit logics
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("FacultyListCtl doPost Start");
		List list = null;
		List next = null;

		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));
		pageNo = (pageNo == 0) ? 1 : pageNo;
		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;

		FacultyDTO dto = (FacultyDTO) populateDTO(request);
		String op = DataUtility.getString(request.getParameter("operation"));
		FacultyModelInt model = ModelFactory.getInstance().getFacultyModel();

		// get the selected checkbox ids array for delete list
		String[] ids = request.getParameterValues("ids");

		try {

			if (OP_SEARCH.equalsIgnoreCase(op) || "Next".equalsIgnoreCase(op) || "Previous".equalsIgnoreCase(op)) {

				if (OP_SEARCH.equalsIgnoreCase(op)) {
					pageNo = 1;
				} else if (OP_NEXT.equalsIgnoreCase(op)) {
					pageNo++;
				} else if (OP_PREVIOUS.equalsIgnoreCase(op) && pageNo > 1) {
					pageNo--;
				}
			} else if (OP_NEW.equalsIgnoreCase(op)) {
				ServletUtility.redirect(ORSView.FACULTY_CTL, request, response);
				return;
			} else if (OP_DELETE.equalsIgnoreCase(op)) {
				pageNo = 1;
				if (ids != null && ids.length > 0) {
					FacultyDTO deletedto = new FacultyDTO();
					for (String id : ids) {
						deletedto.setId(DataUtility.getLong(id));
						model.delete(deletedto);
						ServletUtility.setSuccessMessage("Data is deleted successfully", request);
					}
				} else {
					ServletUtility.setErrorMessage("Select at least one record", request);
				}
			} else if (OP_RESET.equalsIgnoreCase(op)) {
				ServletUtility.redirect(ORSView.FACULTY_LIST_CTL, request, response);
				return;
			} else if (OP_BACK.equalsIgnoreCase(op)) {
				ServletUtility.redirect(ORSView.WELCOME_CTL, request, response);
				return;
			}

			list = model.search(dto, pageNo, pageSize);
			Collections.sort(list);
			next = model.search(dto, pageNo + 1, pageSize);
			ServletUtility.setList(list, request);
			if (!OP_DELETE.equalsIgnoreCase(op)) {
				if (list == null || list.size() == 0) {
					ServletUtility.setErrorMessage("No record found ", request);
				}
			}
			ServletUtility.setList(list, request);
			ServletUtility.setDto(dto, request);
			request.setAttribute("nextListSize", next.size());
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.forward(getView(), request, response);

		} catch (ApplicationException e) {
			log.error(e);
			ServletUtility.handleException(e, request, response);
			return;
		}
		log.debug("FacultyListCtl doGet End");
	}

	@Override
	protected String getView() {
		return ORSView.FACULTY_LIST_VIEW;
	}
}