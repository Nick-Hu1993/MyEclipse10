package org.dao.imp;

import org.dao.UserDetailDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.model.UserDetail;
import org.util.HibernateSessionFactory;

public class UserDetailDaoImp implements UserDetailDao {

	public boolean insert(String username, Long userid) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			UserDetail userDetail = new UserDetail(userid, username);
			session.save(userDetail);
			ts.commit();
			HibernateSessionFactory.closeSession();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(UserDetail userDetail) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			session.update(userDetail);
			ts.commit();
			HibernateSessionFactory.closeSession();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public UserDetail getDetail(Long userid) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			Query query = session.createQuery("from UserDetail where userid=?");
			query.setParameter(0, userid);
			query.setMaxResults(1);
			UserDetail userDetail = (UserDetail) query.uniqueResult();
			
			ts.commit();
			HibernateSessionFactory.closeSession();
			return userDetail;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean delete(Long userid) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();

			Query query = session.createQuery("delete from UserDetail where userid = ?");
			query.setParameter(0, userid);
			query.executeUpdate();
			
			ts.commit();
			HibernateSessionFactory.closeSession();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
