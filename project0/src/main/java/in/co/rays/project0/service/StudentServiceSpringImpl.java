package in.co.rays.project0.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import in.co.rays.project0.dao.CollegeDAOInt;
import in.co.rays.project0.dao.StudentDAOInt;
import in.co.rays.project0.dto.CollegeDTO;
import in.co.rays.project0.dto.StudentDTO;
import in.co.rays.project0.exception.DuplicateRecordException;

/**
 * The Class StudentServiceSpringImpl.
 * @author uday
 *
 */
@Service("studentService")
public class StudentServiceSpringImpl implements StudentServiceInt {

	/** The dao. */
	@Autowired
	private StudentDAOInt dao;
	
	/** The cdao. */
	@Autowired
	private CollegeDAOInt cdao;

	/* (non-Javadoc)
	 * @see in.co.rays.project0.service.StudentServiceInt#add(in.co.rays.project0.dto.StudentDTO)
	 */
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public long add(StudentDTO dto) throws DuplicateRecordException {
		// TODO Auto-generated method stub
		System.out.println("in service method"+dto);
		
		CollegeDTO cdto=cdao.findByPK(dto.getCollegeId());
		
		dto.setCollegeName(cdto.getName());
		
		StudentDTO existdto=dao.findByEmail(dto.getEmail());
		
		System.out.println("in exist dto"+existdto);
	
		if(existdto!=null){
			throw new DuplicateRecordException("student id already exist");
		}
		return dao.add(dto);
	}
	
	/* (non-Javadoc)
	 * @see in.co.rays.project0.service.StudentServiceInt#update(in.co.rays.project0.dto.StudentDTO)
	 */
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public void update(StudentDTO dto) {
		
		// TODO Auto-generated method stub
		dao.update(dto);
		
	}
	
	/* (non-Javadoc)
	 * @see in.co.rays.project0.service.StudentServiceInt#delete(long)
	 */
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public void delete(long id) {
		
		// TODO Auto-generated method stub
		dao.delete(id);
		
	}
	
	/* (non-Javadoc)
	 * @see in.co.rays.project0.service.StudentServiceInt#findByPK(long)
	 */
	@Transactional(readOnly=true)
	public StudentDTO findByPK(long pk) {
		
		// TODO Auto-generated method stub
		return dao.findByPK(pk);
		
	}
	
	/* (non-Javadoc)
	 * @see in.co.rays.project0.service.StudentServiceInt#findByEmail(java.lang.String)
	 */
	@Transactional(readOnly=true)
	public StudentDTO findByEmail(String email) {
		// TODO Auto-generated method stub
		return dao.findByEmail(email);
	}
	
	/* (non-Javadoc)
	 * @see in.co.rays.project0.service.StudentServiceInt#search(in.co.rays.project0.dto.StudentDTO)
	 */
	@Transactional(readOnly=true)
	public List search(StudentDTO dto) {
		// TODO Auto-generated method stub
		return dao.search(dto);
	}

	/* (non-Javadoc)
	 * @see in.co.rays.project0.service.StudentServiceInt#search(in.co.rays.project0.dto.StudentDTO, int, int)
	 */
	@Transactional(readOnly = true)
	public List search(StudentDTO dto, int pageNo, int pageSize) {
		
		// TODO Auto-generated method stub
		return dao.search(dto,pageNo,pageSize);
		
	}
	
}
