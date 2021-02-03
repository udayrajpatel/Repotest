package in.co.rays.model;
import java.util.List;
import in.co.rays.dto.FacultyDTO;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DatabaseException;
import in.co.rays.exception.DuplicateRecordException;

/**
 * Data Access Object of faculty
 * 
 * @author uday
 *
 */
public interface FacultyModelInt {

	/**
	 * add a faculty
	 * 
	 * @param dto
	 * @return
	 * @throws ApplicationException
	 * @throws DuplicateRecordException
	 */
	public long add(FacultyDTO dto) throws ApplicationException, DuplicateRecordException;

	/**
	 * Update a faculty
	 * 
	 * @param dto
	 * @throws ApplicationException
	 * @throws DuplicateRecordException : if updated user record is already exist
	 */
	public void update(FacultyDTO dto) throws ApplicationException, DuplicateRecordException;

	/**
	 * Delete a faculty
	 * 
	 * @param dto
	 * @throws ApplicationException
	 */
	public void delete(FacultyDTO dto) throws ApplicationException;

	/**
	 * Find faculty by Name
	 * 
	 * @param name : get parameter
	 * @return dto
	 * @throws ApplicationException
	 */
	public FacultyDTO findByEmail(String name) throws ApplicationException;

	/**
	 * Find faculty by PK
	 * 
	 * @param pk : get parameter
	 * @return dto
	 * @throws ApplicationException
	 */
	public FacultyDTO findByPK(long pk) throws ApplicationException;

	/**
	 * Search faculty with pagination
	 * 
	 * @return list : List of faculty
	 * @param dto      : Search Parameters
	 * @param pageNo   : Current Page No.
	 * @param pageSize : Size of Page
	 * @throws ApplicationException
	 */
	public List search(FacultyDTO dto, int pageNo, int pageSize) throws ApplicationException;

	/**
	 * Search faculty
	 * 
	 * @return list : List of faculty
	 * @param dto : Search Parameters
	 * @throws ApplicationException
	 */
	public List search(FacultyDTO dto) throws ApplicationException;

	/**
	 * Gets List of faculty
	 * 
	 * @return list : List of faculty
	 * @throws DatabaseException
	 */
	public List list() throws ApplicationException;

	/**
	 * get List of faculty with pagination
	 * 
	 * @return list : List of faculty
	 * @param pageNo   : Current Page No.
	 * @param pageSize : Size of Page
	 * @throws ApplicationException
	 */
	public List list(int pageNo, int pageSize) throws ApplicationException;

}
