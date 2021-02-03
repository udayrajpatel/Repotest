package test;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import in.co.rays.dto.UserDTO;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.exception.RecordNotFoundException;
import in.co.rays.model.UserModelHibImpl;
import in.co.rays.model.UserModelInt;

/**
 * User Model Test classes
 * 
 * @author uday
 *
 */

public class UserModelTest {

	/**
	 * Model object to test
	 * 
	 */

	public static UserModelInt model = new UserModelHibImpl();

//	public static UserModelJDBCImpl model = new UserModelJDBCImpl();

	/**
	 * Main method to call test methods.
	 * 
	 * @param args
	 * @throws ParseException
	 */
	public static void main(String[] args) {
		try {
			
			     testAdd();
//				 testDelete();
//				 testUpdate();
//				 testFindByPK();
//				 testFindByLogin();
//				 testSearch();
//				 testList();
//				 testGetRoles();  
//				 testAuthenticate();
//				 testchangePassword();
//				 testRegisterUser();
//				 testforgetPassword();
//				 testresetPassword();
			     
		
		} catch (Exception e) {
			
		}
	}

	/**
	 * Tests add a User
	 * 
	 * @throws ParseException
	 */
	public static void testAdd() throws ParseException {

		try {

			UserDTO dto = new UserDTO();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

			// dto.setId(8L);
			dto.setFirstName("Roshani");
			dto.setLastName("sharma");
			dto.setLogin("roshani@gmail.com");
			dto.setPassword("56767");
			dto.setDob(sdf.parse("06-01-1998"));
			dto.setRoleId(4L);
			dto.setUnSuccessfulLogin(2);
			dto.setGender("FeMale");
			dto.setLastLogin(new Timestamp(new Date().getTime()));
			dto.setLock("Yes");
			dto.setMobileNo("9826048456");
			dto.setCreatedBy("Admin");
			dto.setModifiedBy("Admin");
			dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
			dto.setModifiedDatetime(new Timestamp(new Date().getTime()));
            
			
			long pk = model.add(dto);
			
			System.out.println("Successfully add");
			System.out.println(dto.getDob());
			
			UserDTO addedDto = model.findByPK(pk);
			if (addedDto == null) {
				
				System.out.println("Test add fail");
			}
		} catch (ApplicationException e) {
			
			e.printStackTrace();
			
		} catch (DuplicateRecordException e) {
			
			e.printStackTrace();
			
		}

	}

	/**
	 * Tests delete a User
	 */
	public static void testDelete() {

		try {
			UserDTO dto = new UserDTO();
			long pk = 2L;
			dto.setId(pk);
			model.delete(dto);
			UserDTO deletedDto = model.findByPK(pk);
			if (deletedDto != null) {
				System.out.println("Test Delete fail");
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Tests update a User
	 */
	public static void testUpdate() throws ParseException {

		try {
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			UserDTO dto = model.findByLogin("roshani@gmail.com");
			dto.setFirstName("Reena");
			dto.setLastName("Singh");
			dto.setLogin("Reena@gmail.com");
			dto.setMobileNo("9986512345");
			dto.setDob(sdf.parse("21/15/1900"));
			dto.setRoleId(2);
			model.update(dto);

			UserDTO updatedDTO = model.findByLogin(dto.getLogin());
			if (!"Roshani".equals(updatedDTO.getFirstName())) {
				System.out.println("Test Update fail");
			} else {
				System.out.println("UPDATE ***SUCCESS***");
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		} catch (DuplicateRecordException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Tests find a User by PK.
	 */
	public static void testFindByPK() {
		try {
			UserDTO dto = new UserDTO();
			long pk = 15L;
			dto = model.findByPK(pk);
			if (dto == null) {
				System.out.println("Test Find By PK fail");
			}
			System.out.println(dto.getId());
			System.out.println(dto.getFirstName());
			System.out.println(dto.getLastName());
			System.out.println(dto.getLogin());
			System.out.println(dto.getPassword());
			System.out.println(dto.getDob());
			System.out.println(dto.getRoleId());
			System.out.println(dto.getUnSuccessfulLogin());
			System.out.println(dto.getGender());
			System.out.println(dto.getLastLogin());
			System.out.println(dto.getLock());
			System.out.println(dto.getMobileNo());
			System.out.println(dto.getCreatedBy());
			System.out.println(dto.getModifiedBy());
			System.out.println(dto.getCreatedDatetime());
			System.out.println(dto.getModifiedDatetime());
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Tests find a User by Login.
	 */
	public static void testFindByLogin() {
		UserDTO dto = new UserDTO();
		try {
			dto = model.findByLogin("programmer.ajayraghu@gmail.com");
			if (dto == null) {
				System.out.println("Test Find By Login fail");
			}
			System.out.println(dto.getId());
			System.out.println(dto.getFirstName());
			System.out.println(dto.getLastName());
			System.out.println(dto.getLogin());
			System.out.println(dto.getPassword());
			System.out.println(dto.getDob());
			System.out.println(dto.getRoleId());
			System.out.println(dto.getUnSuccessfulLogin());
			System.out.println(dto.getGender());
			System.out.println(dto.getLastLogin());
			System.out.println(dto.getLock());
			System.out.println(dto.getMobileNo());
			System.out.println(dto.getCreatedBy());
			System.out.println(dto.getModifiedBy());
			System.out.println(dto.getCreatedDatetime());
			System.out.println(dto.getModifiedDatetime());
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Tests get List.
	 */
	public static void testList() {

		try {
			UserDTO dto = new UserDTO();
			List list = new ArrayList();
			list = model.list(0, 10);
			if (list.size() < 0) {
				System.out.println("Test list fail");
			}
			Iterator it = list.iterator();
			while (it.hasNext()) {
				dto = (UserDTO) it.next();
				System.out.println("|" + dto.getId() + "||" + dto.getFirstName() + "|" + dto.getLastName() + "|"
						+ dto.getLogin() + "|" + dto.getPassword() + "|" + dto.getDob() + "|" + dto.getRoleId() + "|"
						+ dto.getUnSuccessfulLogin() + "|" + dto.getGender() + "|" + dto.getLastLogin() + "|"
						+ dto.getLock() + "|" + dto.getMobileNo() + "|" + dto.getCreatedBy() + "|" + dto.getModifiedBy()
						+ "|" + dto.getCreatedDatetime() + "|" + dto.getModifiedDatetime() + "||");
			}

		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Tests get Roles.
	 */
	public static void testGetRoles() {

		try {
			UserDTO dto = new UserDTO();
			List list = new ArrayList();
			dto.setRoleId(5L);
			list = model.getRoles(dto);
			if (list.size() < 0) {
				System.out.println("Test Get Roles fail");
			}
			Iterator it = list.iterator();
			while (it.hasNext()) {
				dto = (UserDTO) it.next();
				System.out.println(dto.getId());
				System.out.println(dto.getFirstName());
				System.out.println(dto.getLastName());
				System.out.println(dto.getLogin());
				System.out.println(dto.getPassword());
				System.out.println(dto.getDob());
				System.out.println(dto.getRoleId());
				System.out.println(dto.getUnSuccessfulLogin());
				System.out.println(dto.getGender());
				System.out.println(dto.getLastLogin());
				System.out.println(dto.getLock());
				System.out.println(dto.getMobileNo());
				System.out.println(dto.getCreatedBy());
				System.out.println(dto.getModifiedBy());
				System.out.println(dto.getCreatedDatetime());
				System.out.println(dto.getModifiedDatetime());
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * UserDTO dto = new UserDTO(); Tests get Search
	 */
	public static void testSearch() {

		try {
			UserDTO dto = new UserDTO();
			List list = new ArrayList();
//			dto.setLogin("programmer.ajayraghu@gmail.com");
			dto.setFirstName("a");
			list = model.search(dto, 0, 0);
			if (list.size() < 0) {
				System.out.println("Test Search fail");
			}
			Iterator it = list.iterator();
			while (it.hasNext()) {
				dto = (UserDTO) it.next();
				System.out.println(dto.getId());
				System.out.println(dto.getFirstName());
				System.out.println(dto.getLastName());
				System.out.println(dto.getLogin());
				System.out.println(dto.getPassword());
				System.out.println(dto.getDob());
				System.out.println(dto.getRoleId());
				System.out.println(dto.getUnSuccessfulLogin());
				System.out.println(dto.getGender());
				System.out.println(dto.getLastLogin());
				System.out.println(dto.getLock());
				System.out.println(dto.getMobileNo());
				System.out.println(dto.getCreatedBy());
				System.out.println(dto.getModifiedBy());
				System.out.println(dto.getCreatedDatetime());
				System.out.println(dto.getModifiedDatetime());
			}

		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Tests authenticate User.
	 */
	public static void testAuthenticate() {

		try {
			
			UserDTO dto = new UserDTO();
			dto.setLogin("uday@gmail.com");
			dto.setPassword("9756787656");
			dto = model.authenticate(dto.getLogin(),dto.getPassword());
			if (dto != null) {
				
				System.out.println("Successfully login");

			} else {
				
				System.out.println("Invalied login Id & password");
			}

		} catch (ApplicationException e) {
			
			e.printStackTrace();
			
		}
	}

	/**
	 * Tests add a User UserDTO dto = new UserDTO();
	 * 
	 * @throws ParseException
	 */
	public static void testchangePassword() {

		try {
			UserDTO dto = model.findByLogin("programmer.ajayraghu@gmail.com");
			String oldPassword = dto.getPassword();
			dto.setPassword("pass123");
			String newPassword = dto.getPassword();
			try {
				model.changePassword(dto.getId(), oldPassword, newPassword);
			} catch (RecordNotFoundException e) {
				e.printStackTrace();
			}

		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Tests add a User register
	 * 
	 * @throws ParseException
	 */

	public static void testRegisterUser() throws ParseException {
		try {
			UserDTO dto = new UserDTO();
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

			// dto.setId(8L);
			dto.setFirstName("chetan");
			dto.setLastName("pemal");
			dto.setLogin("chetan.pemal13@gmail.com");
			dto.setPassword("chetan123");
			dto.setConfirmPassword("chetan123");
			dto.setDob(sdf.parse("02/09/1991"));
			dto.setGender("Male");
			dto.setRoleId(1);
			long pk = model.registerUser(dto);
			System.out.println("Successfully register");
			System.out.println(dto.getFirstName());
			System.out.println(dto.getLogin());
			System.out.println(dto.getLastName());
			System.out.println(dto.getDob());
			UserDTO registerDto = model.findByPK(pk);
			if (registerDto == null) {
				System.out.println("Test add fail");
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		} catch (DuplicateRecordException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Tests fogetPassword
	 * 
	 * @throws ParseException
	 */
	public static void testforgetPassword() {
		try {
			boolean b = model.forgetPassword("programmer.ajayraghu@gmail.com");

			System.out.println("Suucess : Test Forget Password Success");

		} catch (RecordNotFoundException e) {
			e.printStackTrace();
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Tests resetPassword
	 * 
	 * @throws ParseException
	 */
	public static void testresetPassword() {
		UserDTO dto = new UserDTO();
		try {
			dto = model.findByLogin("programmer.ajayraghu@gmail.com");
			if (dto != null) {
				boolean pass = model.resetPassword(dto);
				if (pass == false) {
					System.out.println("Test Update fail");
				} else {
					System.out.println("Test Update Success");
				}
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}
}
