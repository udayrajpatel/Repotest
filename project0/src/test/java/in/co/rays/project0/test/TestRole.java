package in.co.rays.project0.test;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import in.co.rays.project0.dto.RoleDTO;
import in.co.rays.project0.exception.DuplicateRecordException;
import in.co.rays.project0.service.RoleServiceInt;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/dispatcher-servlet.xml"})

public class TestRole {

	@Autowired
	RoleServiceInt model;
	
	
	@Ignore
	public void testAdd(){
		
		System.out.println("roletest started");
		
		RoleDTO dto=new RoleDTO();
		dto.setRoleName("Student");
		dto.setDescription("Student");
		dto.setCreatedBy("root");
		dto.setModifiedBy("root");
		dto.setCreatedDateTime(new Timestamp(System.currentTimeMillis()));
		dto.setModifiedDateTime(dto.getModifiedDateTime());
		try {
			
			model.add(dto);
			
		} catch (DuplicateRecordException e) {
			
			e.printStackTrace();
			System.out.println("test fail");
			
		}
	}
	
	@Ignore
	public void testFindByName(){
		System.out.println("roletest started");
		RoleDTO dto=new RoleDTO();
		dto=model.findByName("Admin");
		System.out.println(dto.getRoleName()+" is role name");
	}
	
	@Ignore
	public void testUpdate() throws DuplicateRecordException{
		System.out.println("role update started");
		RoleDTO dto=new RoleDTO();
		dto.setId(1);
		dto.setRoleName("ADMIN");
		model.update(dto);
	}
	
	@Ignore
	public void testDelete(){
		
		System.out.println("delete started");
		
		model.delete(2);
		
		System.out.println("delete ended");
		
	}
	
	@Ignore
	public void testSearch(){
		
		RoleDTO dto=new RoleDTO();
		dto.setId(1);
	    List list=model.search(dto, 0, 0);
	    Iterator it=list.iterator();
	    
	    while (it.hasNext()) {
	    	
			RoleDTO dto1 = (RoleDTO) it.next();
	        System.out.println(dto1.getRoleName());
	        
			
		}
	}
	
}
