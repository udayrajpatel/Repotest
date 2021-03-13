package test;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import in.co.rays.dto.FacultyDTO;
import in.co.rays.dto.StudentDTO;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.model.FacultyModelHibImpl;
import in.co.rays.model.FacultyModelInt;
import in.co.rays.model.FacultyModelJDBCImpl;


/**
 * Faculty Model Test classes
 * 
 * @author uday
 *
 */


public class FacultyModelTest {
	/**
	 * 
	 * Model object to test
	 * 
	 */

	public static FacultyModelInt model = new FacultyModelHibImpl();

	// public static FacultyModelInt model = new FacultyModelJDBCImpl();

	/**
	 * Main method to call test methods.
	 * 
	 * @param args
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws ParseException {
          
		   testAdd(); 
//         testDelete(); 
//         testUpdate(); 
//         testFindByRollNo(); 
//         testFindByPK(); 
//         testList(); 
//         testSearch(); 
//         testMeritList(); 
		   

	}

	/**
	 * Tests add a Faculty
	 * 
	 * @throws ParseException
	 */

	public static void testAdd() throws ParseException {

		try {

			FacultyDTO dto = new FacultyDTO();
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			// dto.setId(10L);
			dto.setFirstName("uday");
			dto.setLastName("patel");
			dto.setDob(sdf.parse("05/12/1990"));
			dto.setGender("Male");
			dto.setMobileNo("9977665534");
			dto.setEmail("uday8027@gmail.com");
			dto.setCollegeId(2L);
			dto.setCollegeName("LNCT");
			dto.setCourseId(5L);
			dto.setCourseName("pythan");
			dto.setSubjectName("Serializable");
            dto.setSubjectId(7L);
			long pk = model.add(dto);
			
			System.out.println("Test add succsessfuly");
			FacultyDTO addedDto = model.findByPK(pk);
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
	 * 
	 * Tests delete a Faculty
	 * 
	 */
	public static void testDelete() {

		try {

			FacultyDTO dto = new FacultyDTO();
			long pk = 8L;
			dto.setId(pk);
			model.delete(dto);
			FacultyDTO deletedDto = model.findByPK(pk);
			if (deletedDto != null) {

				System.out.println("Test Delete fail");
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Tests update a Faculty
	 * 
	 * @throws ParseException
	 */
	public static void testUpdate() throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		try {
			FacultyDTO dto = model.findByPK(3L);
			dto.setFirstName("Rabeena");
			dto.setLastName("Tandan");
			dto.setDob(sdf.parse("05/12/1990"));
			dto.setGender("Male");
			dto.setMobileNo("9977665534");
			dto.setEmail("uday8027@gmail.com");
			dto.setCollegeId(2L);
			dto.setCollegeName("LNCT");
			dto.setCourseId(5L);

			model.update(dto);
			System.out.println("Test Update ");

			FacultyDTO updatedDTO = model.findByPK(3L);
			if (!"Rabeena".equals(updatedDTO.getFirstName())) {
				System.out.println("Test Update fail");
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		} catch (DuplicateRecordException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Tests find a Faculty by email
	 * 
	 */

	public static void testFindByEmailId() {
		try {
			FacultyDTO dto = new FacultyDTO();

			dto = model.findByEmail("uday@gmail.com");

			if (dto == null) {

				System.out.println("Test Find By EmailId fail");
			}

			System.out.println(dto.getFirstName());
			System.out.println(dto.getLastName());
			System.out.println(dto.getDob());
			System.out.println(dto.getMobileNo());
			System.out.println(dto.getEmail());
			System.out.println(dto.getCollegeId());
			System.out.println(dto.getCollegeName());
			System.out.println(dto.getCourseId());
			System.out.println(dto.getCourseName());
			System.out.println(dto.getGender());

		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Tests find a Faculty by PK.
	 */
	public static void testFindByPK() {
		try {
			FacultyDTO dto = new FacultyDTO();
			long pk = 3L;
			dto = model.findByPK(pk);
			if (dto == null) {
				System.out.println("Test Find By PK fail");
			}
			System.out.println("||" + dto.getCollegeId() + "||" + dto.getCollegeName() + "|" + dto.getCourseId() + "|"
					+ dto.getCourseName() + "|" + dto.getEmail() + "|" + dto.getGender() + "||");
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Tests list of Facultys
	 */
	public static void testList() {
		try {
			FacultyDTO dto = new FacultyDTO();
			List list = new ArrayList();
			list = model.list(1, 30);
			if (list.size() < 0) {
				System.out.println("Test List fail");
			}
			Iterator it = list.iterator();

			while (it.hasNext()) {

				dto = (FacultyDTO) it.next();
				System.out.println("||" + dto.getCollegeId() + "|" + dto.getCollegeName() + "|" + dto.getCourseId()
						+ "|" + dto.getCourseName() + "|" + dto.getDob() + "|" + dto.getEmail() + "|" + "||");
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Tests search a Facultys
	 */

	public static void testSearch() {
		try {
			FacultyDTO dto = new FacultyDTO();
			List list = new ArrayList();
			dto.setFirstName("");
			list = model.search(dto, 1, 10);
			if (list.size() < 0) {
				System.out.println("Test Search fail");
			}
			Iterator it = list.iterator();
			while (it.hasNext()) {
				dto = (FacultyDTO) it.next();
				System.out.println("||" + dto.getCollegeId() + "|" + dto.getCollegeName() + "|" + dto.getCourseId()
						+ "|" + dto.getCourseName() + "|" + dto.getDob() + "|" + dto.getEmail() + "|" + "||");
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

}
