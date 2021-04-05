package in.co.rays.project0.service;
import java.util.List;
import in.co.rays.project0.dto.CollegeDTO;
import in.co.rays.project0.exception.DuplicateRecordException;

/**
 * 
 * @author uday
 * 
 * The Interface CollegeServiceInt.
 * 
 */

public interface CollegeServiceInt {
 
 /**
  * Adds the.
  *
  * @param dto the dto
  * @return the long
  * @throws DuplicateRecordException the duplicate record exception
  */
 public long add(CollegeDTO dto)throws DuplicateRecordException;
 
 /**
  * Delete.
  *
  * @param id the id
  */
 public void delete(long id);
 
 /**
  * Update.
  *
  * @param dto the dto
  */
 public void update(CollegeDTO dto); 
 
 /**
  * Find by name.
  *
  * @param name the name
  * @return the college DTO
  */
 public CollegeDTO findByName(String name);
 
 /**
  * Find by PK.
  *
  * @param pk the pk
  * @return the college DTO
  */
 public CollegeDTO findByPK(long pk);
 
 /**
  * Search.
  *
  * @param dto the dto
  * @return the list
  */
 public List search(CollegeDTO dto);
 
 /**
  * Search.
  *
  * @param dto the dto
  * @param pageNo the page no
  * @param pageSize the page size
  * @return the list
  */
 public List search(CollegeDTO dto,int pageNo,int pageSize);
 
}
