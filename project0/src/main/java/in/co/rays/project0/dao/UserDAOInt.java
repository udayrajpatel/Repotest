package in.co.rays.project0.dao;
import java.util.List;

import org.springframework.dao.DataAccessException;

import in.co.rays.project0.dto.UserDTO;

public interface UserDAOInt {
     
	public long add(UserDTO dto) throws DataAccessException;
	
	public void delete(long id);
	
	public void update(UserDTO dto);
	
	public UserDTO findByid(long id);
	 
    public UserDTO findByPK(long pk);
   
    public UserDTO findByLogin(String emailId);

	public List search(UserDTO dto);
	
	public List search(UserDTO dto, int pageNo, int pageSize);	
    
      
}
