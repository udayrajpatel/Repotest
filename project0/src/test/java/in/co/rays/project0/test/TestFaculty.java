package in.co.rays.project0.test;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import in.co.rays.project0.dto.FacultyDTO;
import in.co.rays.project0.exception.DuplicateRecordException;
import in.co.rays.project0.service.FacultyServiceInt;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/Dispatcher-servlet.xml")

public class TestFaculty {

	@Autowired
	FacultyServiceInt facultyService;

	@Test
	public void add() throws ParseException, DuplicateRecordException {
		FacultyDTO dto = new FacultyDTO();

		dto.setFirstName("Prinka");
		dto.setLastName("Mehta");
		dto.setCollegeId(1L);
		dto.setCollegeName("ChameliDevi");
		dto.setCourseId(1L);
		dto.setCourseName("MCA");
		dto.setEmail("prin23@gmail.com");
		dto.setGender("FeMale");
		dto.setSubjectId(1L);
		dto.setSubjectName("Maths");
		dto.setDob(new SimpleDateFormat("dd/MM/yyyy").parse("09/10/1992"));
		dto.setMobileNo("9907001756");
		dto.setCreatedBy("root");
		dto.setModifiedBy("root");
		dto.setCreatedDateTime(new Timestamp(System.currentTimeMillis()));
		dto.setModifiedDateTime(dto.getModifiedDateTime());
		
        
		long pk = facultyService.add(dto);

		System.out.println("Faculty Add Sucess ID=" + pk);

	}

	@Ignore
	public void delete() {

		facultyService.delete(2L);

		System.out.println("Faculty Delete success");
	
	}

	@Ignore
	public void update() throws ParseException {
		FacultyDTO dto = new FacultyDTO();

		dto.setFirstName("Prinka");
		dto.setLastName("Mehta");
		dto.setCollegeId(1L);
		dto.setCollegeName("ChameliDevi");
		dto.setCourseId(1L);
		dto.setCourseName("MCA");
		dto.setEmail("prin23@gmail.com");
		dto.setGender("FeMale");
		dto.setSubjectId(1L);
		dto.setSubjectName("Maths");
		
		dto.setDob(new SimpleDateFormat("dd/MM/yyyy").parse("09/10/1992"));
		
		dto.setCreatedBy("root");
		dto.setMobileNo("9907001756");
		dto.setCreatedBy("root");
		dto.setModifiedBy("root");
		dto.setCreatedDateTime(new Timestamp(System.currentTimeMillis()));
		dto.setModifiedDateTime(dto.getModifiedDateTime());
		
        
		facultyService.update(dto);
		
		System.out.println("User Update Successfully");

	}

	@Ignore
	public void findByEmail() {

		FacultyDTO dto = facultyService.findByEmail("uday8027@gmail.com");
		System.out.println(dto.getFirstName());

	}

	@Ignore
	public void findByPk() {

		FacultyDTO dto = facultyService.findByPk(1L);

		System.out.println("Faculty FindBy Pk Success=" + dto.getFirstName());
		
	}

	@Ignore
	public void search() {

		FacultyDTO dto = new FacultyDTO();

		List l = facultyService.search(dto);

		System.out.println("faculty Search Success=" + l.size());
        
		
	}

}
