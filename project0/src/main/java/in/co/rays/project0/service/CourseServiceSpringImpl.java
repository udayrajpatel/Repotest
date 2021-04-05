package in.co.rays.project0.service;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import in.co.rays.project0.dao.CourseDAOInt;
import in.co.rays.project0.dto.CourseDTO;
import in.co.rays.project0.dto.RoleDTO;
import in.co.rays.project0.exception.DuplicateRecordException;

/**
 * The Class CourseServiceSpringImpl.
 */
@Service("courseService")
public class CourseServiceSpringImpl implements CourseServiceInt {
	
	/** The log. */
	private static Logger log = Logger.getLogger(RoleServiceSpringImpl.class);
	
	/** The dao. */
	@Autowired
	CourseDAOInt dao;

	/* (non-Javadoc)
	 * @see in.co.rays.project0.service.CourseServiceInt#add(in.co.rays.project0.dto.CourseDTO)
	 */
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public long add(CourseDTO dto) throws DuplicateRecordException {
		log.debug("Course Service Add Started");
    	CourseDTO dtoExist = dao.findByName(dto.getName());
        if (dtoExist != null) {
            throw new DuplicateRecordException("Course Name already exists");
        }
        long pk= dao.add(dto);
        log.debug("Course Service Add Ended");
		return pk;
	}

	/* (non-Javadoc)
	 * @see in.co.rays.project0.service.CourseServiceInt#update(in.co.rays.project0.dto.CourseDTO)
	 */
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public void update(CourseDTO dto) throws DuplicateRecordException {
		
		log.debug("Course Service Update Started");
		
    CourseDTO dtoExist = dao.findByName(dto.getName());
   if (dtoExist != null&&dto.getId()!=dtoExist.getId()) {
	   
        throw new DuplicateRecordException("Course Name already exists");
        
   }
    	dao.update(dto);
    	log.debug("Course Service Update Ended");		
	}

	/* (non-Javadoc)
	 * @see in.co.rays.project0.service.CourseServiceInt#delete(long)
	 */
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public void delete(long id) {
		log.debug("Course Service delete Started");
    	dao.delete(id);
    	log.debug("Course Service delete Ended");

		
	}

	/* (non-Javadoc)
	 * @see in.co.rays.project0.service.CourseServiceInt#findByName(java.lang.String)
	 */
	@Transactional(readOnly=true)
	public CourseDTO findByName(String courseName) {
		
		log.debug("Course Service findByName Started");
        CourseDTO dto = dao.findByName(courseName);
        log.debug("Course Service findByName Ended");
        return dto;
        
	}

	/* (non-Javadoc)
	 * @see in.co.rays.project0.service.CourseServiceInt#findById(java.lang.Long)
	 */
	@Transactional(readOnly=true)
	public CourseDTO findById(Long id) {
		
		log.debug("Role Service findByName Started");
    	CourseDTO dto=dao.findByPK(id);
    	log.debug("Role Service findByName Ended");

		return dto;
	}

	/* (non-Javadoc)
	 * @see in.co.rays.project0.service.CourseServiceInt#search(in.co.rays.project0.dto.CourseDTO, int, int)
	 */
	@Transactional(readOnly=true)
	public List search(CourseDTO dto, int pageNo, int pageSize) {
		return dao.search(dto, pageNo, pageSize);
	}
	
	/* (non-Javadoc)
	 * @see in.co.rays.project0.service.CourseServiceInt#search(in.co.rays.project0.dto.CourseDTO)
	 */
	@Transactional(readOnly=true)
	public List search(CourseDTO dto) {
		
		return search(dto, 0, 0);
		
	}

}
