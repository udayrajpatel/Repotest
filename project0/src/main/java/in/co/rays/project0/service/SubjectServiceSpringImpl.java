package in.co.rays.project0.service;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import in.co.rays.project0.dao.CourseDAOInt;
import in.co.rays.project0.dao.SubjectDAOInt;
import in.co.rays.project0.dto.CourseDTO;
import in.co.rays.project0.dto.SubjectDTO;
import in.co.rays.project0.exception.DuplicateRecordException;

/**
 * The Class SubjectServiceSpringImpl.
 */
@Service("SubjectService")
public class SubjectServiceSpringImpl implements SubjectServiceInt {

	/** The log. */
	private static Logger log = Logger.getLogger(RoleServiceSpringImpl.class);

	/** The dao. */
	@Autowired
	private SubjectDAOInt dao;
	
	/** The coudao. */
	@Autowired
	private CourseDAOInt coudao;

	/* (non-Javadoc)
	 * @see in.co.rays.project0.service.SubjectServiceInt#add(in.co.rays.project0.dto.SubjectDTO)
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public long add(SubjectDTO dto) throws DuplicateRecordException {
		
		log.debug("Subject Service Add Started");
		CourseDTO coudto = coudao.findByPK(dto.getCourseId());
		dto.setCourseName(coudto.getName());
		SubjectDTO dtoExist = dao.findByName(dto.getName());
		if (dtoExist != null) {
			throw new DuplicateRecordException("Subject Name already exists");
		}
		long pk = dao.add(dto);
		log.debug("Subject Service Add ended");
		return pk;
	}

	/* (non-Javadoc)
	 * @see in.co.rays.project0.service.SubjectServiceInt#update(in.co.rays.project0.dto.SubjectDTO)
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void update(SubjectDTO dto) throws DuplicateRecordException {
		log.debug("Subject Service update Started");
		CourseDTO coudto = coudao.findByPK(dto.getCourseId());
		dto.setCourseName(coudto.getName());
		SubjectDTO dtoExist = dao.findByName(dto.getName());
		if (dtoExist != null && dto.getId() != dtoExist.getId()) {
			throw new DuplicateRecordException("Course Name already exists");
		}

		dao.update(dto);

		log.debug("Subject Service update ended");
	}

	/* (non-Javadoc)
	 * @see in.co.rays.project0.service.SubjectServiceInt#delete(long)
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void delete(long id) {
		
		log.debug("Subject Service delete Started");
		dao.delete(id);
		log.debug("Subject Service delete ended");
		
		
	}

	/* (non-Javadoc)
	 * @see in.co.rays.project0.service.SubjectServiceInt#findByName(java.lang.String)
	 */
	@Transactional(readOnly = true)
	public SubjectDTO findByName(String SubjectName) {
		log.debug("Subject Service findByName Started");
		SubjectDTO dto = dao.findByName(SubjectName);
		log.debug("Subject Service findByName ended");
		return null;
	}

	/* (non-Javadoc)
	 * @see in.co.rays.project0.service.SubjectServiceInt#findById(java.lang.Long)
	 */
	@Transactional(readOnly = true)
	public SubjectDTO findById(Long id) {
		log.debug("Subject Service findById Started");
		SubjectDTO dto = dao.findByPK(id);
		log.debug("Subject Service findById ended");
		return dto;
	}

	/* (non-Javadoc)
	 * @see in.co.rays.project0.service.SubjectServiceInt#search(in.co.rays.project0.dto.SubjectDTO, int, int)
	 */
	@Transactional(readOnly = true)
	public List search(SubjectDTO dto, int pageNo, int pageSize) {
		log.debug("Subject Service search Started");
		log.debug("Subject Service search ended");
		return dao.search(dto, pageNo, pageSize);
	}

	/* (non-Javadoc)
	 * @see in.co.rays.project0.service.SubjectServiceInt#search(in.co.rays.project0.dto.SubjectDTO)
	 */
	@Transactional(readOnly = true)
	public List search(SubjectDTO dto) {
		log.debug("Subject Service search Started");
		log.debug("Subject Service search ended");
		return search(dto, 0, 0);
	}

}
