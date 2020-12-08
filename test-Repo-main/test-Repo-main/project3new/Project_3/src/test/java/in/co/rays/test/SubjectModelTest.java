package in.co.rays.test;

import in.co.rays.dto.CourseDTO;
import in.co.rays.dto.SubjectDTO;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.model.SubjectModelHibImpl;
import in.co.rays.model.SubjectModelInt;

// TODO: Auto-generated Javadoc
/**
 * College Model Test classes
 *  
 * @author 
 *  
 */
public class SubjectModelTest {
	 
 	/** Model object to test. */
 public static SubjectModelInt model = new SubjectModelHibImpl();
 
 /**
  * Main method to call test methods.
  *  
  *
  * @param args the arguments
  */
 public static void main(String[] args) {
     testAdd();
    // testDelete();
    //testUpdate();
  //      testFindByName();
    // testFindByPK();
     //testSearch();
//     testList();

}
 
 /**
  * Tests add a Course.
  */
 public static void testAdd(){
	 SubjectDTO dto=new SubjectDTO();
	 dto.setSubjectName("physics");;
	 dto.setCourseName("bee");
	 dto.setDescription("best");
	 try {
		model.add(dto);
	} catch (ApplicationException e) {
		e.printStackTrace();
	} catch (DuplicateRecordException e) {
		e.printStackTrace();
	}
	 }
	 
 	/**
 	 * Test update.
 	 */
 	public static void testUpdate(){
		 SubjectDTO dto=new SubjectDTO();
	     dto.setId(1L);
		 dto.setSubjectName("ath");;
		 dto.setCourseName("bee");
		 dto.setDescription("best");
		 try {
			model.update(dto);
		} catch (ApplicationException e) {
			e.printStackTrace();
		} catch (DuplicateRecordException e) {
			e.printStackTrace();
		}

 }


}
