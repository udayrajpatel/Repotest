package in.co.rays.project0.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import in.co.rays.project0.dao.MarksheetDAOInt;
import in.co.rays.project0.dao.StudentDAOInt;
import in.co.rays.project0.dto.MarksheetDTO;
import in.co.rays.project0.dto.StudentDTO;
import in.co.rays.project0.exception.DuplicateRecordException;

/**
 * The Class MarksheetServiceSpringImpl.
 */
@Service(value="marksheetService")
public class MarksheetServiceSpringImpl implements MarksheetServiceInt {

	/** The dao. */
	@Autowired
	private MarksheetDAOInt dao;
	
	/** The studao. */
	@Autowired
	private StudentDAOInt studao;
	
	/** The student. */
	@Autowired
	private StudentServiceInt student=null;
	
	/* (non-Javadoc)
	 * @see in.co.rays.project0.service.MarksheetServiceInt#add(in.co.rays.project0.dto.MarksheetDTO)
	 */
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public long add(MarksheetDTO dto) throws DuplicateRecordException {
		// TODO Auto-generated method stub
		StudentDTO studto=studao.findByPK(dto.getStudentId());
		dto.setName(studto.getFirstName());
		MarksheetDTO existDto=dao.findByRollNo(dto.getRollNo());
		if(existDto!=null){
			throw new DuplicateRecordException("Roll No already exist");
		}
		
		return dao.add(dto);
		
	}

	/* (non-Javadoc)
	 * @see in.co.rays.project0.service.MarksheetServiceInt#update(in.co.rays.project0.dto.MarksheetDTO)
	 */
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public void update(MarksheetDTO dto) {
		// TODO Auto-generated method stub
		StudentDTO dto1=student.findByPK(dto.getStudentId());
		dto.setName(dto1.getFirstName());
		dao.update(dto);
	}
	
	/* (non-Javadoc)
	 * @see in.co.rays.project0.service.MarksheetServiceInt#delete(long)
	 */
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public void delete(long id) {
		// TODO Auto-generated method stub
		dao.delete(id);
	}
	
	/* (non-Javadoc)
	 * @see in.co.rays.project0.service.MarksheetServiceInt#findByPK(long)
	 */
	@Transactional(readOnly = true)
	public MarksheetDTO findByPK(long pk) {
		// TODO Auto-generated method stub
		return dao.findByPK(pk);
	}
	
	/* (non-Javadoc)
	 * @see in.co.rays.project0.service.MarksheetServiceInt#findByRollNo(java.lang.String)
	 */
	@Transactional(readOnly = true)
     public MarksheetDTO findByRollNo(String rollNo) {
		// TODO Auto-generated method stub
		return dao.findByRollNo(rollNo);
	}
	
	/* (non-Javadoc)
	 * @see in.co.rays.project0.service.MarksheetServiceInt#search(in.co.rays.project0.dto.MarksheetDTO)
	 */
	@Transactional(readOnly = true)
	public List search(MarksheetDTO dto) {
		// TODO Auto-generated method stub
		return dao.search(dto);
	}

	/* (non-Javadoc)
	 * @see in.co.rays.project0.service.MarksheetServiceInt#search(in.co.rays.project0.dto.MarksheetDTO, int, int)
	 */
	@Transactional(readOnly = true)
	public List search(MarksheetDTO dto, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return dao.search(dto, pageNo, pageSize);
	}
	
	/* (non-Javadoc)
	 * @see in.co.rays.project0.service.MarksheetServiceInt#getMeritList(int, int)
	 */
	@Transactional(readOnly = true)
   public List getMeritList(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return dao.getMeritList(pageNo, pageSize);
	}

}
