package test;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import in.co.rays.dto.MarksheetDTO;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.model.MarksheetModelHibImpl;
import in.co.rays.model.MarksheetModelInt;

/**
 * 
 * Marksheet Model Test classes
 * 
 * @author uday
 *
 */
public class MarksheetModelTest {
	/**
	 * Model object to test
	 */

	 public static MarksheetModelInt model = new MarksheetModelHibImpl();

	//public static MarksheetModelInt model = new MarksheetModelJDBCImpl();

	/**
	 * Main method to call test methods.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
         

		   testAdd(); 
//         testDelete();
//         testUpdate();
//         testFindByRollNo(); 
//         testFindByPK(); 
//		   testList();
//         testSearch(); 
//         testMeritList(); 

	}

	/**
	 * Tests add a Marksheet
	 */
	public static void testAdd() {

		try {
			
			MarksheetDTO dto = new MarksheetDTO();
			//dto.setId(10L);
			dto.setRollNo("0817CS16MT05");
			dto.setName("Shusila");
			dto.setPhysics(98);
			dto.setChemistry(97);
			dto.setMaths(96);
			dto.setStudentId(2L);
			
			long pk = model.add(dto);
			
			System.out.println("Test add succ");
			
			MarksheetDTO addedDto = model.findByPK(pk);
			
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
	 * Tests delete a Marksheet
	 */
	public static void testDelete() {

		try {
			
			MarksheetDTO dto = new MarksheetDTO();
			long pk = 8L;
			dto.setId(pk);
			model.delete(dto);
			MarksheetDTO deletedDto = model.findByPK(pk);
			if (deletedDto != null) {
				System.out.println("Test Delete fail");
			
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Tests update a Marksheet
	 */
	public static void testUpdate() {

		try {
			MarksheetDTO dto = model.findByPK(3L);
			dto.setName("new");
			dto.setChemistry(88);
			dto.setMaths(88);
			model.update(dto);
			System.out.println("Test Update ");
			MarksheetDTO updatedDTO = model.findByPK(3L);
			if (!"rk choudhary".equals(updatedDTO.getName())) {
				System.out.println("Test Update fail");
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		} catch (DuplicateRecordException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Tests find a marksheet by Roll No.
	 */

	public static void testFindByRollNo() {

		try {
			MarksheetDTO dto = model.findByRollNo("CS102");
			if (dto == null) {
				System.out.println("Test Find By RollNo fail");
			}
			System.out.println("||" + dto.getId() + "||" + dto.getRollNo() + "|" + dto.getName() + "|"
					+ dto.getPhysics() + "|" + dto.getChemistry() + "|" + dto.getMaths() + "|" + dto.getCreatedBy()
					+ "|" + dto.getCreatedDatetime() + "|" + dto.getModifiedBy() + "|" + dto.getModifiedDatetime()
					+ "||");
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Tests find a marksheet by PK.
	 */
	public static void testFindByPK() {
		try {
			MarksheetDTO dto = new MarksheetDTO();
			long pk = 3L;
			dto = model.findByPK(pk);
			if (dto == null) {
				System.out.println("Test Find By PK fail");
			}
			System.out.println("||" + dto.getId() + "||" + dto.getRollNo() + "|" + dto.getName() + "|"
					+ dto.getPhysics() + "|" + dto.getChemistry() + "|" + dto.getMaths() + "|" + dto.getCreatedBy()
					+ "|" + dto.getCreatedDatetime() + "|" + dto.getModifiedBy() + "|" + dto.getModifiedDatetime()
					+ "||");
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Tests list of Marksheets
	 */
	public static void testList() {
		try {
			MarksheetDTO dto = new MarksheetDTO();
			List list = new ArrayList();
			list = model.list(1, 30);
			if (list.size() < 0) {
				System.out.println("Test List fail");
			}
			
			Iterator it = list.iterator();
			while (it.hasNext()) {
				dto = (MarksheetDTO) it.next();
				System.out.println("||" + dto.getId() + "||" + dto.getRollNo() + "|" + dto.getName() + "|"
						+ dto.getPhysics() + "|" + dto.getChemistry() + "|" + dto.getMaths() + "|" + dto.getCreatedBy()
						+ "|" + dto.getCreatedDatetime() + "|" + dto.getModifiedBy() + "|" + dto.getModifiedDatetime()
						+ "||");
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Tests search a Marksheets
	 */

	public static void testSearch() {
		try {
			MarksheetDTO dto = new MarksheetDTO();
			List list = new ArrayList();
			dto.setName("a");
			list = model.search(dto, 1, 10);
			if (list.size() < 0) {
				System.out.println("Test Search fail");
			}
			Iterator it = list.iterator();
			while (it.hasNext()) {
				dto = (MarksheetDTO) it.next();
				System.out.println("||" + dto.getId() + "||" + dto.getRollNo() + "|" + dto.getName() + "|"
						+ dto.getPhysics() + "|" + dto.getChemistry() + "|" + dto.getMaths() + "|" + dto.getCreatedBy()
						+ "|" + dto.getCreatedDatetime() + "|" + dto.getModifiedBy() + "|" + dto.getModifiedDatetime()
						+ "||");
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Tests get the meritlist of Marksheets
	 */
	public static void testMeritList() {
		try {
			MarksheetDTO dto = new MarksheetDTO();
			List list = new ArrayList();
			list = model.getMeritList(1, 5);
			if (list.size() < 0) {
				System.out.println("Test List fail");
			}
			Iterator it = list.iterator();
			while (it.hasNext()) {
				dto = (MarksheetDTO) it.next();
				System.out.println("||" + dto.getId() + "||" + dto.getRollNo() + "|" + dto.getName() + "|"
						+ dto.getPhysics() + "|" + dto.getChemistry() + "|" + dto.getMaths() + "|" + dto.getCreatedBy()
						+ "|" + dto.getCreatedDatetime() + "|" + dto.getModifiedBy() + "|" + dto.getModifiedDatetime()
						+ "||");
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}
}
