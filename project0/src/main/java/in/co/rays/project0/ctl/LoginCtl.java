package in.co.rays.project0.ctl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import in.co.rays.project0.dto.RoleDTO;
import in.co.rays.project0.dto.UserDTO;
import in.co.rays.project0.exception.*;
import in.co.rays.project0.form.ForgetPasswordForm;
import in.co.rays.project0.form.LoginForm;
import in.co.rays.project0.form.UserRegistrationForm;
import in.co.rays.project0.service.UserServiceInt;

/**
 * The Class LoginCtl.
 */
@Controller
public class LoginCtl extends BaseCtl {

	/** The Constant OP_SIGNIN. */
	protected static final String OP_SIGNIN = "SignIn";

	/** The Constant OP_SIGNUP. */
	protected static final String OP_SIGNUP = "SignUp";

	/** The service. */
	@Autowired
	private UserServiceInt service;

	/** The message source. */
	@Autowired
	private MessageSource messageSource;

	/**
	 * Gets the gender list.
	 *
	 * @return the gender list
	 */

	@ModelAttribute(value = "genderList")
	public Map<String, String> getgenderList() {

		Map<String, String> genderList = new HashMap<String, String>();

		genderList.put("Male", "Male");

		genderList.put("Female", "Female");

		return genderList;

	}

	/**
	 * Display.
	 *
	 * @param form    the form
	 * @param session the session
	 * @param model   the model
	 * @param locale  the locale
	 * @return the string
	 */

	@RequestMapping(value = "/Login", method = RequestMethod.GET)

	public String Display(@ModelAttribute("form") LoginForm form, HttpSession session, Model model, Locale locale) {

		String enterEmail = messageSource.getMessage("label.enteremail", null, locale);

		model.addAttribute("enteremail", enterEmail);

		String enterPassword = messageSource.getMessage("label.enterpassword", null, locale);

		model.addAttribute("enterpassword", enterPassword);

		if (session.getAttribute("user") != null) {

			session.invalidate();

			String msg = messageSource.getMessage("label.logout", null, locale);

			model.addAttribute("success", msg);

		}

		return "Login";

	}

	/**
	 * Submit
	 * 
	 * @param locale
	 * @param form
	 * @param bindingResult
	 * @param session
	 * @param request
	 * @param model
	 * @return
	 */

	@RequestMapping(value = "/Login", method = RequestMethod.POST)

	public String submit(Locale locale, @ModelAttribute("form") @Valid LoginForm form, BindingResult bindingResult,
			HttpSession session, HttpServletRequest request, Model model) {

		System.out.println("in method" + form.getOperation());

		String enterEmail = messageSource.getMessage("label.enteremail", null, locale);

		model.addAttribute("enteremail", enterEmail);

		String enterPassword = messageSource.getMessage("label.enterpassword", null, locale);

		model.addAttribute("enterpassword", enterPassword);

		if (OP_SIGNIN.equalsIgnoreCase(form.getOperation())) {

			System.out.println("in login method" + form.getOperation());

			if (bindingResult.hasErrors()) {

				return "Login";
			}

			UserDTO dto = new UserDTO();

			dto.setPassword(form.getPassword());

			dto.setEmailId(form.getEmailId());

			// System.out.println(dto.getPassword());
			// System.out.println(dto.getEmailId());

			dto = service.authenticate(dto);

			System.out.println(dto + "authenticate method in loginCtl");

			System.out.println("in again ..." + dto);

			if (dto != null) {

				UserDTO udto = service.findByPK(dto.getId());
				// System.out.println(udto+"..........");
				// dto.setLastLogin(new Timestamp(new Date().getTime()));
				// dto.setLastLoginIP(request.getRemoteAddr());

				System.out.println("--->>>" + udto);

				service.update(udto);

				session.setAttribute("user", dto);

				RoleDTO rdto = service.getRole(dto);

				System.out.println("---->>" + rdto);

				session.setAttribute("role", rdto.getRoleName());

				if (form.getUri() == "" || form.getUri() == null) {

					return "redirect:/WelcomeCtl";

				} else {

					return "redirect:/" + form.getUri().replace("/project0/", "");

				}
			} else {

				String msg = messageSource.getMessage("login.error", null, locale);
				model.addAttribute("error", msg);

			}
		}
		if (OP_SIGNUP.equalsIgnoreCase(form.getOperation())) {

			return "redirect:/Registration";
		}
		System.out.println("operation" + OP_FORGET + ".." + form.getOperation());

		if (OP_FORGET.equalsIgnoreCase(form.getOperation())) {

			return "redirect:/ForgetPassword";
		}
		return "Login";
	}

	/**
	 * Display.
	 *
	 * @param model  the model
	 * @param form   the form
	 * @param locale the locale
	 * @return the string
	 */
	@RequestMapping(value = "/Registration", method = RequestMethod.GET)
	public String display(Model model, @ModelAttribute("form") UserRegistrationForm form, Locale locale) {
		String enteremail = messageSource.getMessage("label.enteremail", null, locale);
		model.addAttribute("enteremail", enteremail);

		String enterpassword = messageSource.getMessage("label.enterpassword", null, locale);
		model.addAttribute("enterpassword", enterpassword);

		String enterdob = messageSource.getMessage("label.enterdob", null, locale);
		model.addAttribute("enterdob", enterdob);

		String enterfirstName = messageSource.getMessage("label.enterfname", null, locale);
		model.addAttribute("enterfirstName", enterfirstName);

		String enterLastName = messageSource.getMessage("label.enterlname", null, locale);
		model.addAttribute("enterLastName", enterLastName);

		String enterconPassword = messageSource.getMessage("label.entercpassword", null, locale);
		model.addAttribute("enterconPassword", enterconPassword);

		String enterMobile = messageSource.getMessage("label.entermob", null, locale);
		model.addAttribute("enterMobile", enterMobile);

		return "UserRegistration";
	}

	/**
	 * Submit.
	 *
	 * @param locale        the locale
	 * @param form          the form
	 * @param bindingResult the binding result
	 * @param model         the model
	 * @return the string
	 */

	@RequestMapping(value = "/Registration", method = RequestMethod.POST)
	public String submit(Locale locale, @ModelAttribute("form") @Valid UserRegistrationForm form,
			BindingResult bindingResult, Model model) {
		String enteremail = messageSource.getMessage("label.enteremail", null, locale);
		model.addAttribute("enteremail", enteremail);

		String enterpassword = messageSource.getMessage("label.enterpassword", null, locale);
		model.addAttribute("enterpassword", enterpassword);

		String enterdob = messageSource.getMessage("label.enterdob", null, locale);
		model.addAttribute("enterdob", enterdob);

		String enterfirstName = messageSource.getMessage("label.enterfname", null, locale);
		model.addAttribute("enterfirstName", enterfirstName);

		String enterLastName = messageSource.getMessage("label.enterlname", null, locale);
		model.addAttribute("enterLastName", enterLastName);

		String enterconPassword = messageSource.getMessage("label.entercpassword", null, locale);
		model.addAttribute("enterconPassword", enterconPassword);

		String enterMobile = messageSource.getMessage("label.entermob", null, locale);
		model.addAttribute("enterMobile", enterMobile);

		System.out.println("in post method" + form.getOperation() + "mmmm");

		if (OP_SIGNUP.equalsIgnoreCase(form.getOperation())) {

			System.out.println("in registration method");
			if (bindingResult.hasErrors()) {
				return "UserRegistration";
			}

			UserDTO dto = (UserDTO) form.getDto();
			System.out.println("User Registration form" + dto.getConfirmPassword() + "..." + dto.getPassword());
			;
			if (!dto.getPassword().equals(dto.getConfirmPassword())) {
				String conPwdMatch = messageSource.getMessage("label.conPwdMatch", null, locale);
				model.addAttribute("conPwdMatch", conPwdMatch);

				return "UserRegistration";
			}
			try {

				// System.out.println(">>>>" + form.getDob());
				dto.setRoleId(RoleDTO.STUDENT);
				dto.setCreatedBy("root");
				dto.setModifiedBy("root");
				dto.setCreatedDateTime(new Timestamp(new Date().getTime()));
				dto.setModifiedDateTime(new Timestamp(new Date().getTime()));
				service.registerUser(dto);
				String msg = messageSource.getMessage("message.success", null, locale);
				model.addAttribute("success", msg);
				form.populate(new UserDTO());
				return "UserRegistration";

			} catch (Exception e) {
				String msg = messageSource.getMessage("error.loginid", null, locale);
				model.addAttribute("error", msg);

			}
			return "redirect:/UserRegistration";

		}
		if (OP_RESET.equalsIgnoreCase(form.getOperation())) {
			return "redirect:/Registration";
		}
		return "UserRegistration";
	}

	/**
	 * Display.
	 *
	 * @param form   the form
	 * @param model  the model
	 * @param locale the locale
	 * @return the string
	 */
	@RequestMapping(value = "/ForgetPassword", method = RequestMethod.GET)
	public String display(@ModelAttribute("form") ForgetPasswordForm form, Model model, Locale locale) {
		System.out.println("in forgot password ");
		String enteremail = messageSource.getMessage("label.enteremail", null, locale);
		model.addAttribute("enteremail", enteremail);
		return "ForgetPassword";

	}

	/**
	 * Submit.
	 *
	 * @param locale        the locale
	 * @param form          the form
	 * @param bindingResult the binding result
	 * @param model         the model
	 * @return the string
	 */
	@RequestMapping(value = "/ForgetPassword", method = RequestMethod.POST)
	public String submit(Locale locale, @ModelAttribute("form") @Valid ForgetPasswordForm form,
			BindingResult bindingResult, Model model) {

		System.out.println("kjkjkjk");
		if (OP_CANCEL.equalsIgnoreCase(form.getOperation())) {
			return "redirect:/Login";
		}
		if (OP_GO.equalsIgnoreCase(form.getOperation())) {
			if (bindingResult.hasErrors()) {
				return "ForgetPassword";
			}
			try {
				boolean flag = false;
				flag = service.forgetPassword(form.getLogin());

				if (flag) {
					model.addAttribute("success", "Password has been sent to your registered Email ID!!");
				} else {

					String msg = messageSource.getMessage("forgetpass.error", null, locale);
					model.addAttribute("error", msg);

				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return "ForgetPassword";

	}
}
