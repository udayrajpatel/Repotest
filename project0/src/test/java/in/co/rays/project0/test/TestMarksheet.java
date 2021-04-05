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
import in.co.rays.project0.dto.MarksheetDTO;
import in.co.rays.project0.exception.DuplicateRecordException;
import in.co.rays.project0.service.MarksheetServiceInt;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/Dispatcher-servlet.xml")

public class TestMarksheet {

	@Autowired
	MarksheetServiceInt marksheetService;

	@Test
	public void add() throws ParseException, DuplicateRecordException {
		MarksheetDTO dto = new MarksheetDTO();
        
		dto.setStudentId(1L);
		dto.setRollNo("08CS108");
		dto.setName("Sonali");
		dto.setPhysics(89);
		dto.setMaths(89);
		dto.setChemistry(98);
		
		dto.setCreatedBy("root");
		dto.setModifiedBy("root");
		dto.setCreatedDateTime(new Timestamp(System.currentTimeMillis()));
		dto.setModifiedDateTime(dto.getModifiedDateTime());
	
        
		long pk = marksheetService.add(dto);

		System.out.println("User Add Sucess ID=" + pk);

	}

	@Ignore
	public void delete() {

		marksheetService.delete(2L);

		System.out.println("Marksheet Delete success");
	
	}

	@Ignore
	public void update() throws ParseException {
		MarksheetDTO dto = new MarksheetDTO();

		dto.setId(2);
		dto.setStudentId(1L);
		dto.setRollNo("08CS108");
		dto.setName("Sonali");
		dto.setPhysics(89);
		dto.setMaths(89);
		dto.setChemistry(98);
		dto.setCreatedBy("root");
		dto.setModifiedBy("root");
		dto.setCreatedDateTime(new Timestamp(System.currentTimeMillis()));
		dto.setModifiedDateTime(dto.getModifiedDateTime());
        
		marksheetService.update(dto);
		
		System.out.println("marksheet Update Successfully");

	}

	@Ignore
	public void findByid() {

		MarksheetDTO dto = marksheetService.findByRollNo("08CS108");
		System.out.println(dto.getName());

	}

	@Ignore
	public void findByPk() {

		MarksheetDTO dto = marksheetService.findByPK(1);

		System.out.println("marksheet FindBy Pk Success=" + dto.getName());
		
	}

	@Ignore
	public void search() {

		MarksheetDTO dto = new MarksheetDTO();

		List l = marksheetService.search(dto);

		System.out.println("marksheet Search Success=" + l.size());
        
		
	}

}
