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
import in.co.rays.project0.dto.UserDTO;
import in.co.rays.project0.exception.DuplicateRecordException;
import in.co.rays.project0.service.UserServiceInt;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/Dispatcher-servlet.xml")

public class TestUser {

	@Autowired
	UserServiceInt userService;

	@Ignore
	public void add() throws ParseException, DuplicateRecordException {
		UserDTO dto = new UserDTO();

		dto.setFirstName("prachi");
		dto.setLastName("jain");
		dto.setEmailId("prachi23@gmail.com");
		dto.setGender("Female");
		dto.setDob(new SimpleDateFormat("dd/MM/yyyy").parse("09/10/1996"));
		dto.setPassword("prachi@123");
		dto.setConfirmPassword("prachi@123");
		dto.setCreatedBy("root");
		dto.setMobileNo("9907001756");
		dto.setCreatedBy("root");
		dto.setModifiedBy("root");
		dto.setCreatedDateTime(new Timestamp(System.currentTimeMillis()));
		dto.setModifiedDateTime(dto.getModifiedDateTime());
		dto.setRoleId(1);
        dto.setRoleName("Admin");
        
		long pk = userService.add(dto);

		System.out.println("User Add Sucess ID=" + pk);

	}

	@Ignore
	public void delete() {

		userService.delete(2L);

		System.out.println("User Delete success");
	
	}

	@Ignore
	public void update() throws ParseException {
		UserDTO dto = new UserDTO();

		dto.setId(2);
		dto.setFirstName("prachi");
		dto.setLastName("sharma");
		dto.setEmailId("prachi@gmail.com");
		dto.setGender("Female");
		dto.setDob(new SimpleDateFormat("dd/MM/yyyy").parse("05/05/1995"));
		dto.setPassword("prachi@123");
		dto.setConfirmPassword("prachi@123");
		dto.setCreatedBy("root");
		dto.setMobileNo("8435725286");
		dto.setModifiedBy("root");
		dto.setCreatedDateTime(new Timestamp(System.currentTimeMillis()));
		dto.setModifiedDateTime(new Timestamp(new Date().getTime()));
		dto.setRoleId(1);
        dto.setRoleName("Admin");
        
		userService.update(dto);
		
		System.out.println("User Update Successfully");

	}

	@Ignore
	public void findByid() {

		UserDTO dto = userService.findByid(1);
		System.out.println(dto.getFirstName());

	}

	@Ignore
	public void findByPk() {

		UserDTO dto = userService.findByPK(1);

		System.out.println("User FindBy Pk Success=" + dto.getFirstName());
		
	}

	@Ignore
	public void search() {

		UserDTO dto = new UserDTO();

		List l = userService.search(dto);

		System.out.println("User Search Success=" + l.size());
        
		
	}

}
