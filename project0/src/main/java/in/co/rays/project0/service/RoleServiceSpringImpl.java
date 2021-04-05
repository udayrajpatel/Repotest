package in.co.rays.project0.service;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import in.co.rays.project0.dao.RoleDAOInt;
import in.co.rays.project0.dto.RoleDTO;
import in.co.rays.project0.exception.DuplicateRecordException;

/**
 * @author uday
 * 
 * The Class RoleServiceSpringImpl.
 */


@Service("roleService")
public class RoleServiceSpringImpl implements RoleServiceInt {

	/** The dao. */
	@Autowired
	private RoleDAOInt dao;

	/** The log. */
	private static Logger log = Logger.getLogger(RoleServiceSpringImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * in.co.rays.project0.service.RoleServiceInt#add(in.co.rays.project0.dto.
	 * RoleDTO)
	 */
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)

	public long add(RoleDTO dto) throws DuplicateRecordException {
	
		log.debug("Role Service Add Started");
		
		RoleDTO dtoExist = dao.findByName(dto.getRoleName());
		
		System.out.println("exist dto" + dtoExist);
		
		if (dtoExist != null) {
			
			throw new DuplicateRecordException("Role Name already exists");
			
		}
		long pk = dao.add(dto);
		
		log.debug("Role Service Add Ended");
		
		return pk;
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * in.co.rays.project0.service.RoleServiceInt#update(in.co.rays.project0.dto.
	 * RoleDTO)
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	
	public void update(RoleDTO dto) throws DuplicateRecordException {
		log.debug("Role Service Update Started");
		RoleDTO dtoExist = dao.findByName(dto.getRoleName());
		if (dtoExist != null && dto.getId() != dtoExist.getId()) {
			throw new DuplicateRecordException("Role Name already exists");
		}
		dao.update(dto);
		log.debug("Role Service Update Ended");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.co.rays.project0.service.RoleServiceInt#delete(long)
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void delete(long id) {
		
		log.debug("Role Service delete Started");
		dao.delete(id);
		log.debug("Role Service delete Ended");
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.co.rays.project0.service.RoleServiceInt#findByName(java.lang.String)
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public RoleDTO findByName(String roleName) {
		log.debug("Role Service findByName Started");
		RoleDTO dto = dao.findByName(roleName);
		log.debug("Role Service findByName Ended");
		return dto;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.co.rays.project0.service.RoleServiceInt#findById(java.lang.Long)
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public RoleDTO findById(Long id) {
		log.debug("Role Service findByName Started");
		RoleDTO dto = dao.findByPK(id);
		log.debug("Role Service findByName Ended");
		return dto;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * in.co.rays.project0.service.RoleServiceInt#search(in.co.rays.project0.dto.
	 * RoleDTO, int, int)
	 */
	
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List search(RoleDTO dto, int pageNo, int pageSize) {
		
		return dao.search(dto, pageNo, pageSize);
		
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * in.co.rays.project0.service.RoleServiceInt#search(in.co.rays.project0.dto.
	 * RoleDTO)
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List search(RoleDTO dto) {
		// TODO Auto-generated method stub
		return search(dto, 0, 0);
	}

}
