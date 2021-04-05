package in.co.rays.project0.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import in.co.rays.project0.dao.CollegeDAOInt;
import in.co.rays.project0.dao.CourseDAOInt;
import in.co.rays.project0.dao.FacultyDAOInt;
import in.co.rays.project0.dao.SubjectDAOInt;
import in.co.rays.project0.dto.CollegeDTO;
import in.co.rays.project0.dto.CourseDTO;
import in.co.rays.project0.dto.FacultyDTO;
import in.co.rays.project0.dto.SubjectDTO;
import in.co.rays.project0.exception.DuplicateRecordException;

/**
 * The Class FacultyServiceSpringImpl.
 */

@Service(value="faultyService")
public class FacultyServiceSpringImpl implements FacultyServiceInt{

	/** The dao. */
	@Autowired
	private FacultyDAOInt dao;
	
	/** The cdao. */
	@Autowired
	private CollegeDAOInt cdao;
	
	/** The subdao. */
	@Autowired
	private SubjectDAOInt subdao;
	
	/** The coudao. */
	@Autowired
	private CourseDAOInt coudao;
	
	/* (non-Javadoc)
	 * @see in.co.rays.project0.service.FacultyServiceInt#add(in.co.rays.project0.dto.FacultyDTO)
	 */
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	
	public long add(FacultyDTO dto) throws DuplicateRecordException {
		// TODO Auto-generated method stub
		
		CollegeDTO cdto=cdao.findByPK(dto.getCollegeId());
		dto.setCollegeName(cdto.getName());
		SubjectDTO subdto=subdao.findByPK(dto.getSubjectId());
		dto.setSubjectName(subdto.getName());
		
		
		CourseDTO coudto=coudao.findByPK(dto.getCourseId());
		dto.setCourseName(coudto.getName());
		FacultyDTO dto1=dao.findByEmail(dto.getEmail());
		if(dto1!=null){
			throw new DuplicateRecordException("faculty already exist");
			
		}
		return dao.add(dto);
	}

	/* (non-Javadoc)
	 * @see in.co.rays.project0.service.FacultyServiceInt#update(in.co.rays.project0.dto.FacultyDTO)
	 */
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public void update(FacultyDTO dto) {
		// TODO Auto-generated method stub
		CollegeDTO cdto=cdao.findByPK(dto.getCollegeId());
		dto.setCollegeName(cdto.getName());
		SubjectDTO subdto=subdao.findByPK(dto.getSubjectId());
		dto.setSubjectName(subdto.getName());
		
		
		CourseDTO coudto=coudao.findByPK(dto.getCourseId());
		dto.setCourseName(coudto.getName());
		dao.update(dto);
	}

	/* (non-Javadoc)
	 * @see in.co.rays.project0.service.FacultyServiceInt#delete(long)
	 */
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public void delete(long id) {
		// TODO Auto-generated method stub
		dao.delete(id);
	}
	 
 	/* (non-Javadoc)
 	 * @see in.co.rays.project0.service.FacultyServiceInt#findByPk(long)
 	 */
 	@Transactional(readOnly=true)
	public FacultyDTO findByPk(long pk) {
		// TODO Auto-generated method stub
		return dao.findByPK(pk);
	}
	 
 	/* (non-Javadoc)
 	 * @see in.co.rays.project0.service.FacultyServiceInt#findByEmail(java.lang.String)
 	 */
 	@Transactional(readOnly=true)
	public FacultyDTO findByEmail(String email) {
		// TODO Auto-generated method stub
		return dao.findByEmail(email);
	}
	 
 	/* (non-Javadoc)
 	 * @see in.co.rays.project0.service.FacultyServiceInt#search(in.co.rays.project0.dto.FacultyDTO)
 	 */
 	@Transactional(readOnly=true)
	public List search(FacultyDTO dto) {
 		
		// TODO Auto-generated method stub
		return dao.search(dto);
		
	}
	 
 	/* (non-Javadoc)
 	 * @see in.co.rays.project0.service.FacultyServiceInt#search(in.co.rays.project0.dto.FacultyDTO, int, int)
 	 */
 	@Transactional(readOnly=true)
	public List search(FacultyDTO dto, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return dao.search(dto, pageNo, pageSize);
	}

}
