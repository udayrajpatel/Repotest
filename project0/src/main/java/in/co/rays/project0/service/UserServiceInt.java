package in.co.rays.project0.service;
import java.util.List;
import in.co.rays.project0.dto.RoleDTO;
import in.co.rays.project0.dto.UserDTO;
import in.co.rays.project0.exception.ApplicationException;
import in.co.rays.project0.exception.DuplicateRecordException;

public interface UserServiceInt {
	

	public long add(UserDTO dto) throws DuplicateRecordException;
	
	 public void delete(long id);
	
	public void update(UserDTO dto);
	
	public UserDTO findByid(long id);

    public UserDTO findByPK(long pk);
	
    public UserDTO findByLogin(String login);

	public UserDTO authenticate(UserDTO dto);

	public RoleDTO getRole(UserDTO dto);

	public long registerUser(UserDTO dto) throws DuplicateRecordException ,ApplicationException ;

	public boolean forgetPassword(String login) throws ApplicationException;
	
	public boolean resetPassword(String login) throws ApplicationException;

	public boolean changePassword(Long id, String oldPassword,String newPassword) throws DuplicateRecordException;
    
	public UserDTO updateAccess(UserDTO dto);
	
	public boolean lock(String login);
	
	public List search(UserDTO dto);
	
	public List search(UserDTO dto, int pageNo, int pageSize);
    
}
