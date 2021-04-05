package in.co.rays.project0.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import in.co.rays.project0.dto.UserDTO;

@Repository(value = "userDao")

public class UserDAOHibImpl implements UserDAOInt {

	/** The log. */
	private static Logger log = Logger.getLogger(UserDAOHibImpl.class);

	@Autowired
	private SessionFactory sessionFactory = null;
	
	/** The dao. */
	@Autowired
	private UserDAOInt dao;

	public long add(UserDTO dto) {

		long pk = 0;

		pk = (Long) sessionFactory.getCurrentSession().save(dto);

		return pk;
	}

	public void update(UserDTO dto) {

		sessionFactory.getCurrentSession().update(dto);

	}

	public UserDTO findByid(long id) {

		UserDTO dto1 = null;

		dto1 = (UserDTO) sessionFactory.getCurrentSession().get(UserDTO.class, id);

		return dto1;
	}

	public UserDTO findByPK(long pk) {

		UserDTO dto = null;

		dto = (UserDTO) sessionFactory.getCurrentSession().get(UserDTO.class, pk);

		return dto;

	}

	public void delete(long id) {

		UserDTO dto = findByPK(id);

		sessionFactory.getCurrentSession().delete(dto);

	}

	public UserDTO findByLogin(String login) {

		UserDTO dto = null;
		List list = sessionFactory.getCurrentSession().createCriteria(UserDTO.class)
				.add(Restrictions.eq("emailId", login)).list();

		if (list.size() == 1) {

			dto = (UserDTO) list.get(0);
		}

		return dto;
	}

	
	public List search(UserDTO dto, int pageNo, int pageSize) {
		Criteria criteria=sessionFactory.getCurrentSession().createCriteria(UserDTO.class);
		if(dto!=null){
			if(dto.getId()>0){
				criteria.add(Restrictions.eq("id", dto.getId()));
			}
			if(dto.getFirstName()!=null&&dto.getFirstName().length()>0){
				criteria.add(Restrictions.like("firstName", dto.getFirstName()+"%"));
			}
			if(dto.getLastName()!=null&&dto.getLastName().length()>0){
				criteria.add(Restrictions.like("lastName", dto.getLastName()+"%"));
			}
			
		}
		
		if(pageSize>0){
			criteria.setFirstResult((pageNo-1)*pageSize);
			criteria.setMaxResults(pageSize);
		}
		List list=criteria.list();
		
		return list;
	}

	
	public List search(UserDTO dto) {
		return search(dto, 0, 0);
	}
	
}
