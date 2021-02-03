package test;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import in.co.rays.dto.CourseDTO;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.model.CourseModelHibImpl;
import in.co.rays.model.CourseModelInt;

public class CourseModelTest {

	/**
	 * Model object to test
	 */

	public static CourseModelInt model = new CourseModelHibImpl();

	// public static CourseModelInt model = new RoleModelJDBCImpl();

	/**
	 * Main method to call test methods.
	 * 
	 * @param args
	 * @throws ParseException
	 */

	public static void main(String[] args) {

		// testAdd();
		// testDelete();
		//testUpdate();
		// testFindByPK();
		// testFindByLogin();
		// testSearch();
		// testList();

	}

	/**
	 * Tests add a User
	 * 
	 * @throws ParseException
	 */
	public static void testAdd() {

		try {

			CourseDTO dto = new CourseDTO();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

			dto.setId(3L);
			dto.setDuration("40h");
			dto.setName("javaScript");
			dto.setDescription("DesigningCourse");

			
			/*
			 * dto.setCreatedBy("Admin"); dto.setModifiedBy("Admin");
			 * 
			 * dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
			 * dto.setModifiedDatetime(new Timestamp(new Date().getTime()));
			 */

			long pk = model.add(dto);

			System.out.println("Test add succ");
			CourseDTO addedDto = model.findByPK(pk);
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

			CourseDTO dto = new CourseDTO();
			long pk = 3L;
			dto.setId(pk);
			model.delete(dto);
			System.out.println("Test Delete succ");
			CourseDTO deletedDto = model.findByPK(pk);
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
	public static void testUpdate() {

		try {

			CourseDTO dto = model.findByPK(1L);
			dto.setName("HOD");
			dto.setDuration("50h");
			dto.setDescription("Head of Development");
			model.update(dto);

			CourseDTO updatedDTO = model.findByPK(1L);
			System.out.println("Test Update ");
			if (!"HOD".equals(updatedDTO.getName())) {

				System.out.println("Test Update fail");
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
			CourseDTO dto = new CourseDTO();
			long pk = 1L;
			dto = model.findByPK(pk);
			if (dto == null) {

				System.out.println("Test Find By PK fail");
			}
			System.out.println(dto.getId());
			System.out.println(dto.getName());
			System.out.println(dto.getDescription());
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Tests find a User by Login.
	 */
	public static void testFindByLogin() {
		try {
			CourseDTO dto = new CourseDTO();
			dto = model.findByName("HOD");
			if (dto == null) {
				
				System.out.println("Test Find by Login fail");
				
			}
			System.out.println(dto.getId());
			System.out.println(dto.getName());
			System.out.println(dto.getDescription());
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Tests get Search
	 */
	public static void testSearch() {

		try {
			
			CourseDTO dto = new CourseDTO();
			
			List list = new ArrayList();
			dto.setName("HOD");
			list = model.search(dto, 0, 0);
			if (list.size() < 0) {
				
				System.out.println("Test Serach fail");
			}
			Iterator it = list.iterator();
			while (it.hasNext()) {
				dto = (CourseDTO) it.next();
				
				System.out.println("||" + dto.getId() + "||" + dto.getName() + "|" + dto.getDescription() + "||");
			}

		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Tests get List.
	 */
	public static void testList() {

		try {

			CourseDTO dto = new CourseDTO();

			List list = new ArrayList();

			list = model.list(0, 10);

			if (list.size() < 0) {

				System.out.println("Test list fail");

			}
			Iterator it = list.iterator();

			while (it.hasNext()) {

				dto = (CourseDTO) it.next();

				System.out.println("||" + dto.getId() + "||" + dto.getName() + "|" + dto.getDescription() + "||");

			}

		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

}
