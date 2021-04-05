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
import in.co.rays.project0.dto.TimetableDTO;
import in.co.rays.project0.exception.DuplicateRecordException;
import in.co.rays.project0.service.TimetableServiceInt;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/Dispatcher-servlet.xml")

public class Timetable {

	@Autowired
	TimetableServiceInt timetableService;

	@Test
	public void add() throws ParseException, DuplicateRecordException {
		TimetableDTO dto = new TimetableDTO();

		dto.setCourseId(2L);
		dto.setCourseName("CrushCourse");
		dto.setSubjectId(2L);
		dto.setSubjectName("Maths");
		dto.setExamDate(new SimpleDateFormat("dd/MM/yyyy").parse("05/10/2022"));
		dto.setExamTime("8:00:PM");
		dto.setSemester("5");
		
		dto.setCreatedBy("root");
		dto.setModifiedBy("root");
		dto.setCreatedDateTime(new Timestamp(System.currentTimeMillis()));
		dto.setModifiedDateTime(dto.getModifiedDateTime());
		
        
		long pk = timetableService.add(dto);

		System.out.println("timetable Add Sucess ID=" + pk);

	}

	@Ignore
	public void delete() {

		timetableService.delete(2L);

		System.out.println("timetable Delete success");
	
	}

	@Ignore
	public void update() throws ParseException, DuplicateRecordException {
		TimetableDTO dto = new TimetableDTO();

		dto.setId(2);
		
		dto.setModifiedBy("root");
		dto.setCreatedDateTime(new Timestamp(System.currentTimeMillis()));
		dto.setModifiedDateTime(new Timestamp(new Date().getTime()));
		
        
		timetableService.update(dto);
		
		System.out.println("timetable Update Successfully");

	}

	
	@Ignore
	public void findByPk() {

		TimetableDTO dto = timetableService.findByPK(1);

		System.out.println("timetable FindBy Pk Success=" + dto.getSubjectName());
		
	}

	@Ignore
	public void search() {

		TimetableDTO dto = new TimetableDTO();

		List l = timetableService.search(dto);

		System.out.println("timetable Search Success=" + l.size());
        
		
	}

}
