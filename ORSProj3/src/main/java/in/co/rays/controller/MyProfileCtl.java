package in.co.rays.controller;

import in.co.rays.dto.BaseDTO;
import in.co.rays.dto.UserDTO;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.model.ModelFactory;
import in.co.rays.model.UserModelInt;
import in.co.rays.util.DataUtility;
import in.co.rays.util.DataValidator;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

/**
 * My Profile functionality Controller. Performs operation for update your
 * Profile
 * 
 * @author uday
 *
 */

@WebServlet(name = "MyProfileCtl", urlPatterns = { "/ctl/MyProfileCtl" })

public class MyProfileCtl extends BaseCtl {

	/**
	 * Default serial version ID
	 */

	private static final long serialVersionUID = 1L;

	public static final String OP_CHANGE_MY_PASSWORD = "Change Password";

	private static Logger log = Logger.getLogger(MyProfileCtl.class);

	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("MyProfileCtl Method validate Started");

		boolean pass = true;

		String op = DataUtility.getString(request.getParameter("operation"));

		if (OP_CHANGE_MY_PASSWORD.equalsIgnoreCase(op) || op == null) {

			return pass;
		}

		if (DataValidator.isNull(request.getParameter("firstName"))) {
			// System.out.println("firstName" + request.getParameter("firstName"));
			request.setAttribute("firstName", PropertyReader.getValue("error.require", "First Name"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("firstName"))) {
			request.setAttribute("firstName", "Invalid First Name");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("lastName"))) {
			request.setAttribute("lastName", PropertyReader.getValue("error.require", "Last Name"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("lastName"))) {
			request.setAttribute("lastName", "Invalid Last Name");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("gender"))) {
			request.setAttribute("gender", PropertyReader.getValue("error.require", "Gender"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("mobileNo"))) {
			request.setAttribute("mobileNo", PropertyReader.getValue("error.require", "MobileNo"));
			pass = false;
		} else if (!DataValidator.isPhoneLength(request.getParameter("mobileNo"))) {
			request.setAttribute("mobileNo", "Mobile No must have 10 digits");
			pass = false;
		} else if (!DataValidator.isPhoneNo(request.getParameter("mobileNo"))) {
			request.setAttribute("mobileNo", "Invalid Mobile No");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("dob"))) {
			request.setAttribute("dob", PropertyReader.getValue("error.require", "Date Of Birth"));
			pass = false;
		}

		log.debug("MyProfileCtl Method validate Ended");

		return pass;

	}

	@Override
	protected BaseDTO populateDTO(HttpServletRequest request) {

		log.debug("MyProfileCtl Method populatedto Started");

		UserDTO dto = new UserDTO();

		dto.setId(DataUtility.getLong(request.getParameter("id")));

		dto.setLogin(DataUtility.getString(request.getParameter("login")));
		System.out.println(dto.getLogin());
		dto.setFirstName(DataUtility.getString(request.getParameter("firstName")));
		System.out.println(dto.getFirstName());
		dto.setLastName(DataUtility.getString(request.getParameter("lastName")));
		System.out.println(dto.getLastName());
		dto.setMobileNo(DataUtility.getString(request.getParameter("mobileNo")));
		System.out.println(dto.getMobileNo());
		dto.setGender(DataUtility.getString(request.getParameter("gender")));
		System.out.println(dto.getGender());
		dto.setDob(DataUtility.getDate(request.getParameter("dob")));
		System.out.println(dto.getDob());
		/*
		 * dto.setPassword(DataUtility.getString(request.getParameter("password")));
		 * dto.setConfirmPassword(DataUtility.getString(request.getParameter(
		 * "confirmPassword")));
		 */
		populateDTO(dto, request);

		return dto;
	}

	/**
	 * Display Concept for viewing profile page view
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		log.debug("MyprofileCtl Method doGet Started");

		UserDTO UserDTO = (UserDTO) session.getAttribute("user");
		long id = UserDTO.getId();
		System.out.println(id + "--------");
		String op = DataUtility.getString(request.getParameter("operation"));
		System.out.println(op);
		// get model
		UserModelInt model = ModelFactory.getInstance().getUserModel();
		if (id > 0 || op != null) {
			System.out.println("in id > 0  condition");
			UserDTO dto;
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

		log.debug("MyProfileCtl Method doGet Ended");
	}

	/**
	 * Submit Concept
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("dopost of myprofile start");

		HttpSession session = request.getSession(true);

		log.debug("MyprofileCtl Method doPost Started");

		UserDTO UserDTO = (UserDTO) session.getAttribute("user");
		long id = UserDTO.getId();

		String op = DataUtility.getString(request.getParameter("operation"));

		// get model
		UserModelInt model = ModelFactory.getInstance().getUserModel();

		if (OP_SAVE.equalsIgnoreCase(op)) {
			UserDTO dto = (UserDTO) populateDTO(request);
			try {
				if (id > 0) {
					System.out.println("hello i am in myprofile udate operation");

				//	UserDTO.setLogin(dto.getLogin());
					UserDTO.setFirstName(dto.getFirstName());
					UserDTO.setLastName(dto.getLastName());
					UserDTO.setGender(dto.getGender());
					UserDTO.setMobileNo(dto.getMobileNo());
					UserDTO.setDob(dto.getDob());

					// UserDTO.setPassword(dto.getPassword());
					// UserDTO.setConfirmPassword(dto.getConfirmPassword());

					model.update(UserDTO);

				}
				ServletUtility.setDto(dto, request);
				ServletUtility.setSuccessMessage("Profile has been updated Successfully. ", request);
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			} catch (DuplicateRecordException e) {
				ServletUtility.setDto(dto, request);
				ServletUtility.setErrorMessage("Login id already exists", request);
			}
		} else if (OP_CHANGE_MY_PASSWORD.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.CHANGE_PASSWORD_CTL, request, response);
			return;

		}

		ServletUtility.forward(getView(), request, response);

		log.debug("MyProfileCtl Method doPost Ended");
	}

	@Override
	protected String getView() {
		return ORSView.MY_PROFILE_VIEW;
	}

}