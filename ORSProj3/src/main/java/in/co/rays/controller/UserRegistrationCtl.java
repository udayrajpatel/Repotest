package in.co.rays.controller;
import in.co.rays.dto.BaseDTO;
import in.co.rays.dto.RoleDTO;
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
import org.apache.log4j.Logger;

/**
 * User registration functionality Controller. Performs operation for User
 * Registration
 * 
 * @author uday
 * 
 */
@WebServlet(name = "UserRegistrationCtl", urlPatterns = { "/UserRegistrationCtl" })
public class UserRegistrationCtl extends BaseCtl {

	/**
	 * Default serial version ID
	 */
	private static final long serialVersionUID = 1L;

	public static final String OP_SIGN_UP = "Sign Up";

	private static Logger log = Logger.getLogger(UserRegistrationCtl.class);

	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("UserRegistrationCtl Method validate Started");

		boolean pass = true;

		String login = request.getParameter("login");
		String dob = request.getParameter("dob");
		String password = request.getParameter("password");

		if (DataValidator.isNull(request.getParameter("firstName"))) {
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
		if (DataValidator.isNull(login)) {
			request.setAttribute("login", PropertyReader.getValue("error.require", "Login Id"));
			pass = false;
		} else if (!DataValidator.isEmail(login)) {
			request.setAttribute("login", PropertyReader.getValue("error.email", "Login"));
			pass = false;
		}
		if (DataValidator.isNull(password)) {
			request.setAttribute("password", PropertyReader.getValue("error.require", "Password"));
			pass = false;
		} else if (!DataValidator.isPasswordLength(password)) {
			request.setAttribute("password", "Password should be 8 to 12 characters");
			pass = false;
		} else if (!DataValidator.isPassword(password)) {
			request.setAttribute("password", "Must contain uppercase, lowercase, digit & special character");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("confirmPassword"))) {
			request.setAttribute("confirmPassword", PropertyReader.getValue("error.require", "Confirm Password"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("gender"))) {
			request.setAttribute("gender", PropertyReader.getValue("error.require", "Gender"));
			pass = false;
		}
		if (DataValidator.isNull(dob)) {
			request.setAttribute("dob", PropertyReader.getValue("error.require", "Date of Birth"));
			pass = false;
		} else if (!DataValidator.isDate(dob)) {
			request.setAttribute("dob", PropertyReader.getValue("error.date", "Date of Birth"));
			pass = false;
		}
		if (!request.getParameter("password").equals(request.getParameter("confirmPassword"))
				&& !"".equals(request.getParameter("confirmPassword"))) {
			request.setAttribute("confirmPassword", "Password and Confirm Password must be Same!");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("mobileNo"))) {
			request.setAttribute("mobileNo", PropertyReader.getValue("error.require", "Mobile No"));
			pass = false;
		} else if (!DataValidator.isPhoneLength(request.getParameter("mobileNo"))) {
			request.setAttribute("mobileNo", "Mobile No must have 10 digits");
			pass = false;
		} else if (!DataValidator.isPhoneNo(request.getParameter("mobileNo"))) {
			request.setAttribute("mobileNo", "Invalid Mobile No");
			pass = false;
		}

		log.debug("UserRegistrationCtl Method validate Ended");

		return pass;
	}

	@Override
	protected BaseDTO populateDTO(HttpServletRequest request) {

		log.debug("UserRegistrationCtl Method populatedto Started");

		UserDTO dto = new UserDTO();

		dto.setId(DataUtility.getLong(request.getParameter("id")));

		dto.setRoleId(RoleDTO.STUDENT);

		dto.setFirstName(DataUtility.getString(request.getParameter("firstName")));

		dto.setLastName(DataUtility.getString(request.getParameter("lastName")));

		dto.setLogin(DataUtility.getString(request.getParameter("login")));

		dto.setPassword(DataUtility.getString(request.getParameter("password")));

		dto.setConfirmPassword(DataUtility.getString(request.getParameter("confirmPassword")));

		dto.setGender(DataUtility.getString(request.getParameter("gender")));

		dto.setDob(DataUtility.getDate(request.getParameter("dob")));

		dto.setMobileNo(DataUtility.getString(request.getParameter("mobileNo")));

		populateDTO(dto, request);

		log.debug("UserRegistrationCtl Method populatedto Ended");

		return dto;
	}

	/**
	 * Display concept of user registration
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		log.debug("UserRegistrationCtl Method doGet Started");
		ServletUtility.forward(getView(), request, response);

	}

	/**
	 * Submit concept of user registration
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("in get method");
		log.debug("UserRegistrationCtl Method doPost Started");
		String op = DataUtility.getString(request.getParameter("operation"));
		// get model
		UserModelInt model = ModelFactory.getInstance().getUserModel();
		DataUtility.getLong(request.getParameter("id"));
		if (OP_SIGN_UP.equalsIgnoreCase(op)) {
			UserDTO dto = (UserDTO) populateDTO(request);
			try {
				long pk = model.registerUser(dto);
				dto.setId(pk);
				request.getSession().setAttribute("UserDTO", dto);
				ServletUtility.setSuccessMessage("Registration successful!", request);
				ServletUtility.forward(getView(), request, response);
				return;
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			} catch (DuplicateRecordException e) {
				log.error(e);
				ServletUtility.setDto(dto, request);
				ServletUtility.setErrorMessage("Login id already exists", request);
				ServletUtility.forward(getView(), request, response);
			}
		} else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.USER_REGISTRATION_CTL, request, response);
			return;
		}

		log.debug("UserRegistrationCtl Method doPost Ended");
	}

	@Override
	protected String getView() {
		return ORSView.USER_REGISTRATION_VIEW;
	}

}