package in.co.rays.project0.test;
import java.sql.Timestamp;
import java.util.Date;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import in.co.rays.project0.service.CollegeServiceInt;
import in.co.rays.project0.dto.CollegeDTO;
import in.co.rays.project0.exception.DuplicateRecordException;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/dispatcher-servlet.xml"})
public class TestCollege {

	@Autowired
	private CollegeServiceInt model;
	
	@Test
	public void testAdd() throws DuplicateRecordException{
		
		
		CollegeDTO dto=new CollegeDTO();
		
		dto.setName("BM");
		dto.setAddress("Indore");
		dto.setCity("Indore");
		dto.setState("MP");
		dto.setPhoneNo("7772967515");
		dto.setCreatedBy("uday@gmail.com");
		dto.setModifiedBy("uday@gmail.com");
		dto.setCreatedDateTime(new Timestamp(new Date().getTime()));
		dto.setModifiedDateTime(dto.getCreatedDateTime());
		model.add(dto);
		
		
		
	}
	
}
