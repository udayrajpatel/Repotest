package in.co.rays.model;

import java.sql.Time;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.dto.CourseDTO;
import in.co.rays.dto.SubjectDTO;
import in.co.rays.dto.TimeTableDTO;
import in.co.rays.dto.UserDTO;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.util.DataUtility;
import in.co.rays.util.HibDataSource;

// TODO: Auto-generated Javadoc
/**
 * The Class TTMHibImp.
 */
public abstract class TTMHibImp implements TimeTableModelInt {

	
	
	/** The log. */
	private static Logger log = Logger.getLogger(MarksheetModelHibImpl.class);
	
	
		
	
	
	/*public TimeTableDTO checkByCourseName(long CourseId, Date ExamDate) throws ApplicationException {
	
		    Session session = null;
	        TimeTableDTO dto = null;
	        try {
	            session = HibDataSource.getSession();

	            Criteria criteria=session.createCriteria(TimeTableDTO.class);

	           criteria.add(Restrictions.eq("courseId", CourseId));
	           criteria.add(Restrictions.like("examDate", ExamDate));

	            
	            List list = criteria.list();
	            if(list.size()==1){
	            	
	             dto=(TimeTableDTO)	list.get(0);
	            }

	        } catch (Exception e) {
	            log.error(e);
	            throw new ApplicationException("Exception in  marksheet list"
	                    + e.getMessage());
	        } finally {
	            session.close();
	        }

	        log.debug("Model getMeritList End");
	        return dto;
	    }
	
	
	

	public TimeTableDTO checkBySubjectName(long CourseId, long SubjectId, Date ExamDate) throws ApplicationException {
		Session session = null;
        TimeTableDTO dto = null;
        try {
            session = HibDataSource.getSession();

            Criteria criteria=session.createCriteria(TimeTableDTO.class);

            // if page size is greater than zero then apply pagination
           criteria.add(Restrictions.eq("courseId", CourseId));
           criteria.add(Restrictions.eq("subId", SubjectId));

           criteria.add(Restrictions.like("examDate", ExamDate));

            
            List list = criteria.list();
            if(list.size()==1){
            	
             dto=(TimeTableDTO)	list.get(0);
            }

        } catch (Exception e) {
            log.error(e);
            throw new ApplicationException("Exception in  marksheet list"
                    + e.getMessage());
        } finally {
            session.close();
        }

        log.debug("Model getMeritList End");
        return dto;
	}

	public TimeTableDTO checkBysemester(long CourseId, long SubjectId, String semester, Date ExamDate)
			throws ApplicationException {
		Session session = null;
        TimeTableDTO dto = null;
        try {
            session = HibDataSource.getSession();

            Criteria criteria=session.createCriteria(TimeTableDTO.class);

            // if page size is greater than zero then apply pagination
           criteria.add(Restrictions.eq("courseId", CourseId));
           criteria.add(Restrictions.eq("subId", SubjectId));
           criteria.add(Restrictions.like("semester", semester));

           criteria.add(Restrictions.like("examDate", ExamDate));

            
            List list = criteria.list();
            if(list.size()==1){
            	
             dto=(TimeTableDTO)	list.get(0);
            }

        } catch (Exception e) {
            log.error(e);
            throw new ApplicationException("Exception in  marksheet list"
                    + e.getMessage());
        } finally {
            session.close();
        }

        log.debug("Model getMeritList End");
        return dto;
	}

	public long add(TimeTableDTO dto) throws ApplicationException, DuplicateRecordException {
		log.debug("Model add Started");
		long pk=0;
		
	SubjectModelInt subjectModel=ModelFactory.getInstance().getSubjectModel();
	
	SubjectDTO subjectdto= subjectModel.findByPK(dto.getSubId())	;
	
    dto.setSubName(subjectdto.getSubjectName());
	
	CourseModelInt courseModel=ModelFactory.getInstance().getCourseModel();
	
	CourseDTO coursedto=courseModel.findByPK(dto.getCourseId());
	
	dto.setCourseName(coursedto.getCourseName());
		
		
		
		
		
		
		Session session=HibDataSource.getSession();
		Transaction transaction=null;
		try {
            transaction = session.beginTransaction();
            session.save(dto);
            pk = dto.getId();
            transaction.commit();
        } catch (HibernateException e) {
            log.error("Database Exception..", e);
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("5");
            throw new ApplicationException("Exception in TimeTable Add "
                    + e.getMessage());
        } finally {
            session.close();
        }
        log.debug("Model add End");
        return dto.getId();
	}
	

	public void update(TimeTableDTO dto) throws ApplicationException, DuplicateRecordException {

		
		SubjectModelInt subjectModel=ModelFactory.getInstance().getSubjectModel();
		
		SubjectDTO subjectdto= subjectModel.findByPK(dto.getSubId())	;
		
	    dto.setSubName(subjectdto.getSubjectName());
		
		CourseModelInt courseModel=ModelFactory.getInstance().getCourseModel();
		
		CourseDTO coursedto=courseModel.findByPK(dto.getCourseId());
		
		dto.setCourseName(coursedto.getCourseName());
			

		
		
		
		
		
		
		Session session=HibDataSource.getSession();
		Transaction transaction=null;
		try {
            transaction = session.beginTransaction();
            session.update(dto);
            transaction.commit();
        } catch (HibernateException e) {
            log.error("Database Exception..", e);
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("5");
            throw new ApplicationException("Exception in TimeTable Add "
                    + e.getMessage());
        } finally {
            session.close();
        }
        log.debug("Model add End");	
	}

	public void delete(TimeTableDTO dto) throws ApplicationException {
		Session session=HibDataSource.getSession();
		Transaction transaction=null;
		try {
            transaction = session.beginTransaction();
            session.delete(dto);
            transaction.commit();
        } catch (HibernateException e) {
            log.error("Database Exception..", e);
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("5");
            throw new ApplicationException("Exception in TimeTable Add "
                    + e.getMessage());
        } finally {
            session.close();
        }
        log.debug("Model add End");	
		
	}

	public TimeTableDTO name(String emailId) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	public TimeTableDTO findByPK(long pk) throws ApplicationException {
		log.debug("Model findByPK Started");
		Session session=null;
		TimeTableDTO dto=null;
		try{
			session=HibDataSource.getSession();
			dto=(TimeTableDTO)session.get(TimeTableDTO.class,pk);
		}catch(HibernateException e){
			log.error("Database Exception..", e);
            throw new ApplicationException("Exception : Exception in getting User by pk");
		}finally {
			session.close();
		}
		return dto;

	}

	public List search(TimeTableDTO dto, int pageNo, int pageSize) throws ApplicationException {
		log.debug("Model search Started");
		  Session session=null;
		  List list=null;
		  try{
			  session=HibDataSource.getSession();
			  Criteria criteria=session.createCriteria(TimeTableDTO.class);
			  if (dto.getId() > 0) {
	                criteria.add(Restrictions.eq("id", dto.getId()));
	            }
	            if (dto.getCourseId() > 0) {
	                criteria.add(Restrictions.eq("courseId", dto.getCourseId()));
	            }

	            if (dto.getSubId() > 0) {
	                criteria.add(Restrictions.eq("subId", dto.getSubId()));
	            }
	           if(dto.getExamDate()!=null){
	            	Date date = new Date(dto.getExamDate().getTime());
	            	criteria.add(Restrictions.eq("examDate",date));
	            }
	            if(dto.getCourseName()!=null&&dto.getCourseName().length()>0){
					criteria.add(Restrictions.like("courseName",dto.getCourseName()+"%"));
				
				}
	           

	            
	            if(pageSize>0){
	            	criteria.setFirstResult((pageNo-1)*pageSize);
	            	criteria.setMaxResults(pageSize);
	            }
	            list=criteria.list();
	            }
	            catch (HibernateException e) {
	                log.error("Database Exception..", e);
	                throw new ApplicationException("Exception in user search");
	            } finally {
	                session.close();
	            }

	            log.debug("Model search End");

		  
		return list;
	}

	public List search(TimeTableDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
		return search(dto, 0, 0);
	}

	public List list() throws ApplicationException {
		
		return list(0, 0);
	}

	public List list(int pageNo, int pageSize) throws ApplicationException {
		Session session=null;
		List list=null;
		try{
			session=HibDataSource.getSession();
			Criteria criteria=session.createCriteria(TimeTableDTO.class);
			
			// if page size is greater than zero then apply pagination
            if (pageSize > 0) {
                pageNo = ((pageNo - 1) * pageSize) + 1;
                criteria.setFirstResult(pageNo);
                criteria.setMaxResults(pageSize);
            }

            list = criteria.list();
        } catch (HibernateException e) {
            log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in  Users list");
        } finally {
            session.close();
        }

        log.debug("Model list End");
		
		return list;

	}
*/
	
	/* (non-Javadoc)
	 * @see in.co.rays.model.TimeTableModelInt#timeTableDuplicacy(long, java.lang.String, java.util.Date)
	 */
	public TimeTableDTO timeTableDuplicacy(long courseId, String semester, Date examDate) throws ApplicationException {
		Session session = null;
		TimeTableDTO dto = null;
		List list = null;
		try {
			session = HibDataSource.getSession();
			Criteria cr = session.createCriteria(TimeTableDTO.class);
			cr.add(Restrictions.eq("courseId", courseId));
			cr.add(Restrictions.eq("semester", semester));
			cr.add(Restrictions.eq("examDate", examDate));
			list = cr.list();
			Iterator itr = list.iterator();
			while (itr.hasNext()) {
				dto = (TimeTableDTO) itr.next();
			}
            System.out.println(dto);
		} catch (HibernateException hb) {
			hb.printStackTrace();
			throw new ApplicationException("Exception in TimeTableDuplicacy of Timetable Model " + hb.getMessage());
		} finally {
			HibDataSource.closeSession(session);
		}
		return dto;
	}

	/* (non-Javadoc)
	 * @see in.co.rays.model.TimeTableModelInt#timeTableDuplicacy(long, long, java.util.Date)
	 */
	public TimeTableDTO timeTableDuplicacy(long courseId, long subjectId, Date examDate) throws ApplicationException {
		Session session = null;
		TimeTableDTO dto = null;
		List list = null;
		try {
			session = HibDataSource.getSession();
			Criteria cr = session.createCriteria(TimeTableDTO.class);
			cr.add(Restrictions.eq("courseId", courseId));
			cr.add(Restrictions.eq("subjectId", subjectId));
			cr.add(Restrictions.eq("examDate", examDate));
			list = cr.list();
			Iterator itr = list.iterator();
			while (itr.hasNext()) {
				dto = (TimeTableDTO) itr.next();
			}
			  System.out.println(dto);
		} catch (HibernateException hb) {
			hb.printStackTrace();
			throw new ApplicationException("Exception in TimeTableDuplicacy of Timetable Model " + hb.getMessage());
		} finally {
			HibDataSource.closeSession(session);
		}
		return dto;
	}

	/* (non-Javadoc)
	 * @see in.co.rays.model.TimeTableModelInt#timeTableDuplicacy(long, java.lang.String, long)
	 */
	public TimeTableDTO timeTableDuplicacy(long courseId, String semester, long subjectId) throws ApplicationException {
		Session session = null;
		TimeTableDTO dto = null;
		List list = null;
		try {
			session = HibDataSource.getSession();
			Criteria cr = session.createCriteria(TimeTableDTO.class);
			cr.add(Restrictions.eq("courseId", courseId));
			cr.add(Restrictions.eq("semester", semester));
			cr.add(Restrictions.eq("subjectId", subjectId));
			list = cr.list();
			Iterator itr = list.iterator();
			while (itr.hasNext()) {
				dto = (TimeTableDTO) itr.next();
			}
			  System.out.println("hello"+dto);
		} catch (HibernateException hb) {
			hb.printStackTrace();
			throw new ApplicationException("Exception in TimeTableDuplicacy of Timetable Model " + hb.getMessage());
		} finally {
			HibDataSource.closeSession(session);
		}
		return dto;
	}


	
	/* (non-Javadoc)
	 * @see in.co.rays.model.TimeTableModelInt#add(in.co.rays.dto.TimeTableDTO)
	 */
	public long add(TimeTableDTO dto) throws ApplicationException, DuplicateRecordException {
		long pk = 0;
		Session session = null;
		Transaction tx = null;
		CourseDTO cDto = null;
		SubjectDTO sDto = null;
		CourseModelHibImpl cModel = new CourseModelHibImpl();
		SubjectModelHibImpl sModel = new SubjectModelHibImpl();
		try {

			
			dto.setCourseName(cDto.getCourseName());
			dto.setSubName(sDto.getSubjectName());
			/*semChecker(cDto, dto);*/
			TimeTableDTO duplicatename = timeTableDuplicacy(dto.getCourseId(), dto.getSemester(), dto.getExamDate());

			TimeTableDTO duplicatename1 = timeTableDuplicacy(dto.getCourseId(), dto.getSemester(), dto.getSubId());

			TimeTableDTO duplicatename2 = timeTableDuplicacy(dto.getCourseId(), dto.getSubId(), dto.getExamDate());
			if (duplicatename1 != null || duplicatename != null || duplicatename2 != null) {
				throw new DuplicateRecordException("Time Table already exist");

			}

			session = HibDataSource.getSession();
			tx = session.beginTransaction();
			session.save(dto);
			tx.commit();
		} catch (HibernateException hb) {
			hb.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			HibDataSource.closeSession(session);
		}
		return pk;

	}

	/* (non-Javadoc)
	 * @see in.co.rays.model.TimeTableModelInt#findByPK(long)
	 */
	public TimeTableDTO findByPK(long pk) throws ApplicationException {
		TimeTableDTO dto = null;
		Session session = null;
		try {
			session = HibDataSource.getSession();
			dto = (TimeTableDTO) session.get(TimeTableDTO.class, pk);
		} catch (HibernateException hb) {
			hb.printStackTrace();
			//throw new ApplicationException("Exception in findByPk of TimeTableModel " + hb.getMessage());
		} finally {
			HibDataSource.closeSession(session);
		}
		return dto;
	}

	/* (non-Javadoc)
	 * @see in.co.rays.model.TimeTableModelInt#update(in.co.rays.dto.TimeTableDTO)
	 */
	public void update(TimeTableDTO dto) throws ApplicationException, DuplicateRecordException  {
		Session session = null;
		Transaction tx = null;
		CourseDTO cDto = null;
		SubjectDTO sDto = null;
		CourseModelHibImpl cModel = new CourseModelHibImpl();
		SubjectModelHibImpl sModel = new SubjectModelHibImpl();
		try {

			
			dto.setCourseName(cDto.getCourseName());
			dto.setSubName(sDto.getSubjectName());
			/*semChecker(cDto, dto);*/
			TimeTableDTO duplicatename = timeTableDuplicacy(dto.getCourseId(), dto.getSemester(), dto.getExamDate());

			TimeTableDTO duplicatename1 = timeTableDuplicacy(dto.getCourseId(), dto.getSemester(), dto.getSubId());

			TimeTableDTO duplicatename2 = timeTableDuplicacy(dto.getCourseId(), dto.getSubId(), dto.getExamDate());
			if (duplicatename1 != null || duplicatename != null || duplicatename2 != null) {
				throw new DuplicateRecordException("Time Table already exist");

			}

			session = HibDataSource.getSession();
			tx = session.beginTransaction();
			session.update(dto);
			tx.commit();

		} catch (HibernateException hb) {
			hb.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
			throw new ApplicationException("Exception in update of timetable Model " + hb.getMessage());
		} finally {
			HibDataSource.closeSession(session);
		}
	}

	
	
	/* (non-Javadoc)
	 * @see in.co.rays.model.TimeTableModelInt#delete(in.co.rays.dto.TimeTableDTO)
	 */
	public void delete(TimeTableDTO dto) throws ApplicationException {

		Session session = null;
		Transaction tx = null;
		try {
			session = HibDataSource.getSession();
			tx = session.beginTransaction();
			session.delete(dto);
			tx.commit();

		} catch (HibernateException hb) {
			hb.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
			throw new ApplicationException("Exception in delete of time table Model " + hb.getMessage());
		} finally {
			HibDataSource.closeSession(session);
		}

	}

	/* (non-Javadoc)
	 * @see in.co.rays.model.TimeTableModelInt#list()
	 */
	public List list() throws ApplicationException {
		// TODO Auto-generated method stub
		return list(0, 0);
	}

	/* (non-Javadoc)
	 * @see in.co.rays.model.TimeTableModelInt#list(int, int)
	 */
	public List list(int pageNo, int pageSize) throws ApplicationException {

		Session session = null;
		List list = null;
		try {
			session = HibDataSource.getSession();
			Criteria cr = session.createCriteria(TimeTableDTO.class);
			if (pageSize > 0) {
				cr.setFirstResult(((pageNo - 1) * pageSize));
				cr.setMaxResults(pageSize);

			}
			list = cr.list();

		} catch (HibernateException hb) {
			hb.printStackTrace();
			throw new ApplicationException("Excpetion in getting List " + hb.getMessage());
		} finally {
			HibDataSource.closeSession(session);
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see in.co.rays.model.TimeTableModelInt#search(in.co.rays.dto.TimeTableDTO, int, int)
	 */
	public List search(TimeTableDTO dto, int pageNo, int pageSize) throws ApplicationException {

		Session session = null;
		List list = null;
		try {
			session = HibDataSource.getSession();
			Criteria cr = session.createCriteria(TimeTableDTO.class);

			if (dto != null) {
				if (dto.getId() > 0) {
					cr.add(Restrictions.eq("id", dto.getId()));
				}
				if (dto.getCourseId() > 0) {
					cr.add(Restrictions.eq("courseId", dto.getCourseId()));
				}
				if (dto.getSubId() > 0) {
					cr.add(Restrictions.eq("subjectId", dto.getSubId()));
				}
				if (dto.getExamDate() != null) {
					System.out.println("in date");
					cr.add(Restrictions.eq("examDate", dto.getExamDate()));
				}
				if (dto.getSemester() != null && dto.getSemester().trim().length() > 0) {
					cr.add(Restrictions.eq("semester", dto.getSemester()));
				}
			
			}
			if (pageSize > 0) {
				cr.setFirstResult(((pageNo - 1) * pageSize));
				cr.setMaxResults(pageSize);
			}
			list = cr.list();
		} catch (HibernateException hb) {

			System.out.println(hb.getMessage());
			hb.printStackTrace();
			throw new ApplicationException("Exception in searching of TimeTable Model");
		} finally {
			HibDataSource.closeSession(session);
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see in.co.rays.model.TimeTableModelInt#search(in.co.rays.dto.TimeTableDTO)
	 */
	public List search(TimeTableDTO dto) throws ApplicationException {

		return search(dto, 0, 0);
	}

	/*public void semChecker(CourseDTO cDto, TimetableDTO tDto) throws RecordNotFoundException {
		String duration = cDto.getDuration();

		if (duration.equalsIgnoreCase("3 Years")
				&& (tDto.getSemester().equalsIgnoreCase("VII") || tDto.getSemester().equalsIgnoreCase("VIII"))) {

			throw new RecordNotFoundException("This Semester is not available for this course");

		} else if (duration.equalsIgnoreCase("2 Years")
				&& (tDto.getSemester().equalsIgnoreCase("V") || tDto.getSemester().equalsIgnoreCase("VI")
						|| tDto.getSemester().equalsIgnoreCase("VII") || tDto.getSemester().equalsIgnoreCase("VIII"))) {
			throw new RecordNotFoundException("This Semester is not available for this course");

		} else {
		}
	}
*/
}
