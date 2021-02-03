package in.co.rays.model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import in.co.rays.dto.CourseDTO;
import in.co.rays.dto.SubjectDTO;
import in.co.rays.dto.TimetableDTO;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DatabaseException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.util.JDBCDataSource;

/**
 * 
 * JDBC Implementation of Timetable Model
 * 
 * @author uday
 *
 */
public class TimetableModelJDBCImpl implements TimetableModelInt {

	private static Logger log = Logger.getLogger(TimetableModelJDBCImpl.class);

	/**
	 * Find next PK of Timetable
	 * 
	 * @throws DatabaseException
	 */
	public Integer nextPK() throws DatabaseException {
		log.debug("Model nextPK Started");
		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM ST_TIMETABLE");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();

		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new DatabaseException("Exception : Exception in getting PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model nextPK End");
		return pk + 1;
	}

	/**
	 * Add a Timetable
	 * 
	 * @param dto
	 * @return
	 * @throws ApplicationException
	 * @throws DuplicateRecordException
	 */
	public long add(TimetableDTO dto) throws ApplicationException, DuplicateRecordException {
		log.debug("Model add Started");
		Connection conn = null;
		int pk = 0;

		// get Course Name
		CourseModelJDBCImpl courseModel = new CourseModelJDBCImpl();
		CourseDTO coursedto = courseModel.findByPK(dto.getCourseId());
		dto.setCourseName(coursedto.getName());

		// get Subject Name
		SubjectModelJDBCImpl subjectModel = new SubjectModelJDBCImpl();
		SubjectDTO subjectdto = subjectModel.findByPK(dto.getSubjectId());
		dto.setSubjectName(subjectdto.getName());

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			// Get auto-generated next primary key
			System.out.println(pk + " in ModelJDBC");
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn
					.prepareStatement("INSERT INTO ST_TIMETABLE VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setLong(2, dto.getCourseId());
			pstmt.setString(3, dto.getCourseName());
			pstmt.setLong(4, dto.getSubjectId());
			pstmt.setString(5, dto.getSubjectName());
			pstmt.setString(6, dto.getSemester());
			pstmt.setString(7, dto.getDescription());
			pstmt.setDate(8, new java.sql.Date(dto.getExamDate().getTime()));
			pstmt.setString(9, dto.getExamTime());
			pstmt.setString(10, dto.getCreatedBy());
			pstmt.setString(11, dto.getModifiedBy());
			pstmt.setTimestamp(12, dto.getCreatedDatetime());
			pstmt.setTimestamp(13, dto.getModifiedDatetime());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in add Timetable");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model add End");
		return pk;
	}

	/**
	 * Delete a Timetable
	 * 
	 * @param dto
	 * @throws ApplicationException
	 */
	public void delete(TimetableDTO dto) throws ApplicationException {
		log.debug("Model delete Started");
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM ST_TIMETABLE WHERE ID=?");
			pstmt.setLong(1, dto.getId());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();

		} catch (Exception e) {
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in delete Timetable");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model delete Started");
	}

	/**
	 * @param examTime
	 * @return
	 * @throws ApplicationException
	 */
	public TimetableDTO checkByExamTime(Long courseId, Long subjectId, String semester, Date examDate, String examTime) throws ApplicationException {
		log.debug("Model checkByExamTime Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_TIMETABLE WHERE COURSE_ID=? AND SUBJECT_ID=? AND SEMESTER=? AND EXAM_DATE=? AND EXAM_TIME=?");
		TimetableDTO dto = null;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, courseId);
			pstmt.setLong(2, subjectId);
			pstmt.setString(3, semester);
			pstmt.setDate(4, new java.sql.Date(examDate.getTime()));
			pstmt.setString(5, examTime);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				dto = new TimetableDTO();
				dto.setId(rs.getLong(1));
				dto.setCourseId(rs.getLong(2));
				dto.setCourseName(rs.getString(3));
				dto.setSubjectId(rs.getLong(4));
				dto.setSubjectName(rs.getString(5));
				dto.setSemester(rs.getString(6));
				dto.setDescription(rs.getString(7));
				dto.setExamDate(rs.getDate(8));
				dto.setExamTime(rs.getString(9));
				dto.setCreatedBy(rs.getString(10));
				dto.setModifiedBy(rs.getString(11));
				dto.setCreatedDatetime(rs.getTimestamp(12));
				dto.setModifiedDatetime(rs.getTimestamp(13));

			}
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in get Timetable");

		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model checkByExamTime End");
		return dto;
	}

	/**
	 * @param courseId
	 * @param examDate
	 * @return
	 * @throws ApplicationException
	 */
	public TimetableDTO checkByCourseName(Long courseId, Date examDate) throws ApplicationException {
		log.debug("Model checkByCourseName Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_TIMETABLE WHERE COURSE_ID=? AND EXAM_DATE=?");
		TimetableDTO dto = null;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, courseId);
			pstmt.setDate(2, new java.sql.Date(examDate.getTime()));

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				dto = new TimetableDTO();
				dto.setId(rs.getLong(1));
				dto.setCourseId(rs.getLong(2));
				dto.setCourseName(rs.getString(3));
				dto.setSubjectId(rs.getLong(4));
				dto.setSubjectName(rs.getString(5));
				dto.setSemester(rs.getString(6));
				dto.setDescription(rs.getString(7));
				dto.setExamDate(rs.getDate(8));
				dto.setExamTime(rs.getString(9));
				dto.setCreatedBy(rs.getString(10));
				dto.setModifiedBy(rs.getString(11));
				dto.setCreatedDatetime(rs.getTimestamp(12));
				dto.setModifiedDatetime(rs.getTimestamp(13));

			}
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in get Timetable");

		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model checkByCourseName End");
		return dto;
	}

	/**
	 * @param courseId
	 * @param subjectId
	 * @param examDate
	 * @return
	 * @throws ApplicationException
	 */
	public TimetableDTO checkBySubjectName(Long courseId, Long subjectId, Date examDate) throws ApplicationException {
		log.debug("Model checkBySubjectName Started");
		StringBuffer sql = new StringBuffer(
				"SELECT * FROM ST_TIMETABLE WHERE COURSE_ID=? AND SUBJECT_ID=? AND EXAM_DATE=?");
		TimetableDTO dto = null;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, courseId);
			pstmt.setLong(2, subjectId);
			pstmt.setDate(3, new java.sql.Date(examDate.getTime()));

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				dto = new TimetableDTO();
				dto.setId(rs.getLong(1));
				dto.setCourseId(rs.getLong(2));
				dto.setCourseName(rs.getString(3));
				dto.setSubjectId(rs.getLong(4));
				dto.setSubjectName(rs.getString(5));
				dto.setSemester(rs.getString(6));
				dto.setDescription(rs.getString(7));
				dto.setExamDate(rs.getDate(8));
				dto.setExamTime(rs.getString(9));
				dto.setCreatedBy(rs.getString(10));
				dto.setModifiedBy(rs.getString(11));
				dto.setCreatedDatetime(rs.getTimestamp(12));
				dto.setModifiedDatetime(rs.getTimestamp(13));

			}
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in get Timetable");

		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model checkBySubjectName End");
		return dto;
	}

	/**
	 * @param courseId
	 * @param subjectId
	 * @param semester
	 * @param examDate
	 * @return
	 * @throws ApplicationException
	 */
	public TimetableDTO checkBySemester(Long courseId, Long subjectId, String semester, Date examDate)
			throws ApplicationException {
		log.debug("Model checkBySemester Started");
		StringBuffer sql = new StringBuffer(
				"SELECT * FROM ST_TIMETABLE WHERE COURSE_ID=? AND SUBJECT_ID=? AND SEMESTER=? AND EXAM_DATE=?");
		TimetableDTO dto = null;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, courseId);
			pstmt.setLong(2, subjectId);
			pstmt.setString(3, semester);
			pstmt.setDate(4, new java.sql.Date(examDate.getTime()));

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				dto = new TimetableDTO();
				dto.setId(rs.getLong(1));
				dto.setCourseId(rs.getLong(2));
				dto.setCourseName(rs.getString(3));
				dto.setSubjectId(rs.getLong(4));
				dto.setSubjectName(rs.getString(5));
				dto.setSemester(rs.getString(6));
				dto.setDescription(rs.getString(7));
				dto.setExamDate(rs.getDate(8));
				dto.setExamTime(rs.getString(9));
				dto.setCreatedBy(rs.getString(10));
				dto.setModifiedBy(rs.getString(11));
				dto.setCreatedDatetime(rs.getTimestamp(12));
				dto.setModifiedDatetime(rs.getTimestamp(13));

			}
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in get Timetable");

		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model checkBySemester End");
		return dto;
	}

	/**
	 * Find Timetable by PK
	 * 
	 * @param pk
	 *            : get parameter
	 * @return dto
	 * @throws ApplicationException
	 */

	public TimetableDTO findByPK(long pk) throws ApplicationException {
		log.debug("Model findByPK Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_TIMETABLE WHERE ID=?");
		TimetableDTO dto = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				dto = new TimetableDTO();
				dto.setId(rs.getLong(1));
				dto.setCourseId(rs.getLong(2));
				dto.setCourseName(rs.getString(3));
				dto.setSubjectId(rs.getLong(4));
				dto.setSubjectName(rs.getString(5));
				dto.setSemester(rs.getString(6));
				dto.setDescription(rs.getString(7));
				dto.setExamDate(rs.getDate(8));
				dto.setExamTime(rs.getString(9));
				dto.setCreatedBy(rs.getString(10));
				dto.setModifiedBy(rs.getString(11));
				dto.setCreatedDatetime(rs.getTimestamp(12));
				dto.setModifiedDatetime(rs.getTimestamp(13));
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting Timetable by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByPK End");
		return dto;
	}

	/**
	 * Update a Timetable
	 * 
	 * @param dto
	 * @throws ApplicationException
	 * @throws DuplicateRecordException
	 */
	public void update(TimetableDTO dto) throws ApplicationException, DuplicateRecordException {
		log.debug("Model update Started");
		Connection conn = null;

		// get Course Name
		CourseModelJDBCImpl courseModel = new CourseModelJDBCImpl();
		CourseDTO coursedto = courseModel.findByPK(dto.getCourseId());
		dto.setCourseName(coursedto.getName());

		// get Subject Name
		SubjectModelJDBCImpl subjectModel = new SubjectModelJDBCImpl();
		SubjectDTO subjectdto = subjectModel.findByPK(dto.getSubjectId());
		dto.setSubjectName(subjectdto.getName());

		try {
			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE ST_TIMETABLE SET COURSE_ID=?,COURSE_NAME=?,SUBJECT_ID=?,SUBJECT_NAME=?,SEMESTER=?,DESCRIPTION=?,EXAM_DATE=?,EXAM_TIME=?,CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? WHERE ID=?");

			pstmt.setLong(1, dto.getCourseId());
			pstmt.setString(2, dto.getCourseName());
			pstmt.setLong(3, dto.getSubjectId());
			pstmt.setString(4, dto.getSubjectName());
			pstmt.setString(5, dto.getSemester());
			pstmt.setString(6, dto.getDescription());
			pstmt.setDate(7, new java.sql.Date(dto.getExamDate().getTime()));
			pstmt.setString(8, dto.getExamTime());
			pstmt.setString(9, dto.getCreatedBy());
			pstmt.setString(10, dto.getModifiedBy());
			pstmt.setTimestamp(11, dto.getCreatedDatetime());
			pstmt.setTimestamp(12, dto.getModifiedDatetime());
			pstmt.setLong(13, dto.getId());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception in updating Timetable ");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model update End");
	}

	/**
	 * Search Timetable
	 * 
	 * @param dto
	 *            : Search Parameters
	 * @throws ApplicationException
	 */

	public List<TimetableDTO> search(TimetableDTO dto) throws ApplicationException {
		return search(dto, 0, 0);
	}

	/**
	 * Search Timetable with pagination
	 * 
	 * @return list : List of Timetable
	 * @param dto
	 *            : Search Parameters
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * 
	 * @throws ApplicationException
	 */

	@SuppressWarnings("deprecation")
	public List<TimetableDTO> search(TimetableDTO dto, int pageNo, int pageSize) throws ApplicationException {
		log.debug("Model search Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_TIMETABLE WHERE 1=1");

		if (dto != null) {
			if (dto.getId() > 0) {
				sql.append(" AND ID = " + dto.getId());
			}
			if (dto.getCourseId() > 0) {
				sql.append(" AND COURSE_ID = " + dto.getCourseId());
			}
			if (dto.getCourseName() != null && dto.getCourseName().length() > 0) {
				sql.append(" AND COURSE_NAME like '" + dto.getCourseName() + "%'");
			}
			if (dto.getSubjectId() > 0) {
				sql.append(" AND SUBJECT_ID = " + dto.getSubjectId());
			}
			if (dto.getSubjectName() != null && dto.getSubjectName().length() > 0) {
				sql.append(" AND SUBJECT_NAME like '" + dto.getSubjectName() + "%'");
			}
			if (dto.getSemester() != null && dto.getSemester().length() > 0) {
				sql.append(" AND SEMESTER like '" + dto.getSemester() + "%'");
			}
			if (dto.getDescription() != null && dto.getDescription().length() > 0) {
				sql.append(" AND DESCRIPTION like '" + dto.getDescription() + "%'");
			}
			if (dto.getExamDate() != null && dto.getExamDate().getDate() > 0) {
				sql.append(" AND EXAM_DATE = " + dto.getExamDate());
			}
			if (dto.getExamTime() != null && dto.getExamTime().length() > 0) {
				sql.append(" AND EXAM_TIME like '" + dto.getExamTime() + "%'");
			}

		}

		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;

			sql.append(" Limit " + pageNo + ", " + pageSize);
			// sql.append(" limit " + pageNo + "," + pageSize);
		}

		ArrayList<TimetableDTO> list = new ArrayList<TimetableDTO>();
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				dto = new TimetableDTO();
				dto.setId(rs.getLong(1));
				dto.setCourseId(rs.getLong(2));
				dto.setCourseName(rs.getString(3));
				dto.setSubjectId(rs.getLong(4));
				dto.setSubjectName(rs.getString(5));
				dto.setSemester(rs.getString(6));
				dto.setDescription(rs.getString(7));
				dto.setExamDate(rs.getDate(8));
				dto.setExamTime(rs.getString(9));
				dto.setCreatedBy(rs.getString(10));
				dto.setModifiedBy(rs.getString(11));
				dto.setCreatedDatetime(rs.getTimestamp(12));
				dto.setModifiedDatetime(rs.getTimestamp(13));
				list.add(dto);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in search Timetable");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model search End");
		return list;
	}

	/**
	 * Get List of Timetable
	 * 
	 * @return list : List of Timetable
	 * @throws ApplicationException
	 */

	public List<TimetableDTO> list() throws ApplicationException {
		return list(0, 0);
	}

	/**
	 * Get List of Timetable with pagination
	 * 
	 * @return list : List of Timetable
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * @throws ApplicationException
	 */

	public List<TimetableDTO> list(int pageNo, int pageSize) throws ApplicationException {
		log.debug("Model list Started");
		ArrayList<TimetableDTO> list = new ArrayList<TimetableDTO>();
		StringBuffer sql = new StringBuffer("select * from ST_TIMETABLE ORDER BY COURSE_NAME");
		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + "," + pageSize);
		}

		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				TimetableDTO dto = new TimetableDTO();
				dto.setId(rs.getLong(1));
				dto.setCourseId(rs.getLong(2));
				dto.setCourseName(rs.getString(3));
				dto.setSubjectId(rs.getLong(4));
				dto.setSubjectName(rs.getString(5));
				dto.setSemester(rs.getString(6));
				dto.setDescription(rs.getString(7));
				dto.setExamDate(rs.getDate(8));
				dto.setExamTime(rs.getString(9));
				dto.setCreatedBy(rs.getString(10));
				dto.setModifiedBy(rs.getString(11));
				dto.setCreatedDatetime(rs.getTimestamp(12));
				dto.setModifiedDatetime(rs.getTimestamp(13));
				list.add(dto);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting list of Timetable");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model list End");
		return list;

	}
}
