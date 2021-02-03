package in.co.rays.model;
import in.co.rays.dto.CollegeDTO;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DatabaseException;
import in.co.rays.exception.DuplicateRecordException;
import java.util.List;

/**
 * Data Access Object of College
 * @author uday
 *
 */
public interface CollegeModelInt {

    /**
     * Add a College
     * 
     * @param dto
     * @throws ApplicationException
     * @throws DuplicateRecordException
     *             : throws when college already exists
     */
    public long add(CollegeDTO dto) throws ApplicationException,
            DuplicateRecordException;

    /**
     * Update a College
     * 
     * @param dto
     * @throws ApplicationException
     * @throws DuplicateRecordException
     *             : if updated user record is already exist
     */
    public void update(CollegeDTO dto) throws ApplicationException,
            DuplicateRecordException;

    /**
     * Delete a College
     * 
     * @param dto
     * @throws ApplicationException
     */
    public void delete(CollegeDTO dto) throws ApplicationException;

    /**
     * Find College by Name
     * 
     * @param name
     *            : get parameter
     * @return dto
     * @throws ApplicationException
     */
    public CollegeDTO findByName(String name) throws ApplicationException;

    /**
     * Find College by PK
     * 
     * @param pk
     *            : get parameter
     * @return dto
     * @throws ApplicationException
     */
    public CollegeDTO findByPK(long pk) throws ApplicationException;

    /**
     * Search College with pagination
     * 
     * @return list : List of College
     * @param dto
     *            : Search Parameters
     * @param pageNo
     *            : Current Page No.
     * @param pageSize
     *            : Size of Page
     * @throws ApplicationException
     */
    public List search(CollegeDTO dto, int pageNo, int pageSize)
            throws ApplicationException;

    /**
     * Search College
     * 
     * @return list : List of College
     * @param dto
     *            : Search Parameters
     * @throws ApplicationException
     */
    public List search(CollegeDTO dto) throws ApplicationException;

    /**
     * Gets List of College
     * 
     * @return list : List of College
     * @throws DatabaseException
     */
    public List list() throws ApplicationException;

    /**
     * get List of College with pagination
     * 
     * @return list : List of College
     * @param pageNo
     *            : Current Page No.
     * @param pageSize
     *            : Size of Page
     * @throws ApplicationException
     */
    public List list(int pageNo, int pageSize) throws ApplicationException;

}
