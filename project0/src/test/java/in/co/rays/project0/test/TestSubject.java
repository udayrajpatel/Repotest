package in.co.rays.project0.test;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import in.co.rays.project0.dto.SubjectDTO;
import in.co.rays.project0.exception.DuplicateRecordException;
import in.co.rays.project0.service.SubjectServiceInt;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/dispatcher-servlet.xml"})
public class TestSubject {

	@Autowired
	private SubjectServiceInt model;
	
	@Test
	public void testAdd() throws DuplicateRecordException{
		SubjectDTO dto=new SubjectDTO();
		dto.setCourseId(1);
		dto.setCourseName("English");
		dto.setName("BE");
		dto.setDescription("Hello");
		dto.setCreatedBy("uday@gmail.com");
		dto.setModifiedBy("uday@gmail.com");
		dto.setCreatedDateTime(new Timestamp(new Date().getTime()));
		dto.setModifiedDateTime(new Timestamp(new Date().getTime()));
		model.add(dto);
	}
	
	@Ignore
	public void delete() {

		model.delete(2L);

		System.out.println("subject Delete success");
	
	}

	@Ignore
	public void update() throws ParseException, DuplicateRecordException {
		SubjectDTO dto = new SubjectDTO();

        dto.setCourseId(1L);
        dto.setCourseName("Corporate");
        dto.setDescription("course");
        dto.setName("Java");
		dto.setCreatedBy("root");
	    dto.setModifiedBy("root");
		dto.setCreatedDateTime(new Timestamp(System.currentTimeMillis()));
		dto.setModifiedDateTime(new Timestamp(new Date().getTime()));
		
        
		model.update(dto);
		
		System.out.println("User Update Successfully");

	}

	@Ignore
	public void findByid() {

		SubjectDTO dto = model.findById(1L);
		System.out.println(dto.getCourseName());

	}

	@Ignore
	public void findByName() {

		SubjectDTO dto = model.findByName("Java");

		System.out.println("User FindBy Pk Success=" + dto.getCourseName());
		
	}

	@Ignore
	public void search() {

		SubjectDTO dto = new SubjectDTO();

		List l = model.search(dto);

		System.out.println("Subject Search Success=" + l.size());
        
		
	}
}
