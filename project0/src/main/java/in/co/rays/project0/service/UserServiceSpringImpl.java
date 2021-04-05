package in.co.rays.project0.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import in.co.rays.project0.dao.UserDAOInt;
import in.co.rays.project0.dto.RoleDTO;
import in.co.rays.project0.dto.UserDTO;
import in.co.rays.project0.exception.ApplicationException;
import in.co.rays.project0.exception.DuplicateRecordException;

@Service(value = "userService")

public class UserServiceSpringImpl implements UserServiceInt {

	@Autowired
	private UserDAOInt dao;

	@Autowired
	private RoleServiceInt roleService;

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)

	public long add(UserDTO dto) {

		long pk = dao.add(dto);
		return pk;

	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)

	public void update(UserDTO dto) {

		dao.update(dto);

	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public UserDTO findByid(long id) {

		UserDTO dto1 = dao.findByid(id);

		return dto1;

	}

	@Transactional(readOnly = true)
	public UserDTO findByPK(long pk) {

		UserDTO dto = dao.findByPK(pk);

		return dto;

	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)

	public void delete(long id) {

		dao.delete(id);

	}

	@Transactional(readOnly = true)
	public UserDTO authenticate(UserDTO dto) {

		UserDTO dtoExist = dao.findByLogin(dto.getEmailId());

		if (dtoExist != null && dtoExist.getPassword().equals(dto.getPassword())) {

			return dtoExist;

		}
		return null;
	}

	@Transactional(readOnly = true)
	public UserDTO findByLogin(String login) {

		UserDTO dto = dao.findByLogin(login);

		return dto;

	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)

	public RoleDTO getRole(UserDTO dto) {

		return roleService.findById(dto.getRoleId());

	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public long registerUser(UserDTO dto) {
		System.out.println("service" + dto.getDob());
		long id = add(dto);

		return id;
	}

	public boolean forgetPassword(String login) {

		return false;
	}

	public boolean resetPassword(String login) throws ApplicationException {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean changePassword(Long id, String oldPassword, String newPassword) throws DuplicateRecordException {
		// TODO Auto-generated method stub
		return false;
	}

	public UserDTO updateAccess(UserDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean lock(String login) {
		// TODO Auto-generated method stub
		return false;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)

	public List<UserDTO> search(UserDTO dto) {

		return dao.search(dto);

	}

	@Transactional(readOnly = true)
	public List<UserDTO> search(UserDTO dto, int pageNo, int pageSize) {
		return dao.search(dto, pageNo, pageSize);
	}

	

}
