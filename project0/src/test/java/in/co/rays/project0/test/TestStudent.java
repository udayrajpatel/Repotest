package in.co.rays.project0.test;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import in.co.rays.project0.dto.StudentDTO;
import in.co.rays.project0.exception.DuplicateRecordException;
import in.co.rays.project0.service.StudentServiceInt;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/dispatcher-servlet.xml" })
public class TestStudent {

	@Autowired
	private StudentServiceInt model;

	@Test
	public void testAdd() throws DuplicateRecordException, ParseException {
		
		StudentDTO dto = new StudentDTO();
		
		dto.setFirstName("Damini");
		dto.setLastName("Sharma");
		dto.setCollegeId(1L);
		dto.setCollegeName("JaiNarayana");
		dto.setMobileNo("9988677675");
		dto.setDob(new SimpleDateFormat("dd/MM/yyyy").parse("02/07/1992"));
		dto.setEmail("Damini78@gmail.com");
		dto.setCreatedBy("uday23@gmail.com");
		dto.setModifiedBy("uday23@gmail.com");
		dto.setCreatedDateTime(new Timestamp(new Date().getTime()));
		dto.setModifiedDateTime(new Timestamp(new Date().getTime()));
		
		
		model.add(dto);

	}
}
