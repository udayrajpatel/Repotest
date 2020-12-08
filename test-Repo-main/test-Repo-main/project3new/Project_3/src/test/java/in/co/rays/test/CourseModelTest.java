package in.co.rays.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import in.co.rays.dto.CollegeDTO;
import in.co.rays.dto.CourseDTO;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.model.CourseModelHibImpl;
import in.co.rays.model.CourseModelInt;

// TODO: Auto-generated Javadoc
/**
 * College Model Test classes
 *  
 * @author 
 *  
 */
public class CourseModelTest {
	 
 	/** Model object to test. */
 public static CourseModelInt model = new CourseModelHibImpl();
 
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
     // testList();

 }

 /**
  * Tests add a Course.
  */
 public static void testAdd(){
	 CourseDTO dto=new CourseDTO();
	 dto.setCourseName("be");
	 dto.setDuration("4");
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
	 CourseDTO dto=new CourseDTO();
	 dto.setId(1L);
	 dto.setCourseName("bee");
	 dto.setDuration("4");
	 dto.setDescription("bear");
	 try {
		model.update(dto);
	} catch (ApplicationException e) {
		e.printStackTrace();
	} catch (DuplicateRecordException e) {
		e.printStackTrace();
	}
 }
 
 /**
  * Test find by name.
  */
 public static void testFindByName(){
	 try {
		CourseDTO dto=model.findByName("bee");
		System.out.println(dto.getCourseName());
	} catch (ApplicationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
 }
 
 /**
  * Test find by PK.
  */
 public static void testFindByPK(){
	 CourseDTO dto;
	try {
		dto = model.findByPK(1L);
		System.out.println(dto.getCourseName());
	} catch (ApplicationException e) {
		e.printStackTrace();
	}
 }
 
 /**
  * Test search.
  */
 public static void testSearch(){
	 CourseDTO dto = new CourseDTO();
	 //dto.setCourseName("bee");
   dto.setId(1L);
	 List list = new ArrayList();
     //dto.setName("Truba");
     try {
		list = model.search(dto);
		System.out.println(list);
	} catch (ApplicationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
          Iterator it = list.iterator();
     while (it.hasNext()) {
         dto = (CourseDTO) it.next();
         System.out.println(dto.getId());
         }
 }
 
 /**
  * Test list.
  */
 public static void testList() {
	 
     try {
         CourseDTO dto = new CourseDTO();
         List list = new ArrayList();
         list = model.list();
         if (list.size() < 0) {
             System.out.println("Test list fail");
         }
         Iterator it = list.iterator();
         while (it.hasNext()) {
             dto = (CourseDTO) it.next();
             System.out.println(dto.getId());
             System.out.println(dto.getCourseName());
             System.out.println(dto.getDescription());
             System.out.println(dto.getDuration());
            
         }

     } catch (ApplicationException e) {
         e.printStackTrace();
     }
 }
}
