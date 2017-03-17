package org.dao.imp;

import java.util.List;

import org.dao.CellDao;
import org.dao.UserDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.model.SuperUser;
import org.model.User;
import org.model.UserCell;
import org.util.HibernateSessionFactory;

public class UserDaoImp implements UserDao {

	public boolean Register(User user) {
		// TODO Auto-generated method stub
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();

			User user2 = new User();
			user2.setPhone(user.getPhone());
			user2.setUsername(user.getUsername());
			user2.setClock(user.getClock());
			user2.setPassword(user.getPassword());
			session.save(user2);

			ts.commit();
			// HibernateSessionFactory.closeSession();

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public User validateUser(Long userid, String password) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			String sql = "from User where id = ? and password= ?";
			Query query = session.createQuery(sql);
			query.setParameter(0, userid);
			query.setParameter(1, password);
			query.setMaxResults(1);

			User user = (User) query.uniqueResult();

			ts.commit();
			HibernateSessionFactory.closeSession();

			return user;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}

	}

	public User Login(String phone, String password) {
		// TODO Auto-generated method stub
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			String sql = "from User where phone = ? and password= ?";
			Query query = session.createQuery(sql);
			query.setParameter(0, Long.parseLong(phone));
			query.setParameter(1, password);
			query.setMaxResults(1);

			User user = (User) query.uniqueResult();

			ts.commit();
			// HibernateSessionFactory.closeSession();

			return user;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	public boolean update(User user) {
		// TODO Auto-generated method stub
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();

			session.update(user);
			ts.commit();
			HibernateSessionFactory.closeSession();
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public User findUserByPhone(String phone) {
		// TODO Auto-generated method stub
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();

			Query query = session.createQuery("from User where phone=?");
			query.setParameter(0, Long.parseLong(phone));
			query.setMaxResults(1);
			User user = (User) query.uniqueResult();
			ts.commit();
			HibernateSessionFactory.closeSession();

			return user;
		} catch (Exception e) {
			return null;
		}
	}

	public User findUserById(Long id) {
		try {
			Session session = HibernateSessionFactory.getSession();
			// Transaction ts = session.beginTransaction();

			Query query = session.createQuery("from User where id=?");
			query.setParameter(0, id);
			query.setMaxResults(1);
			User user = (User) query.uniqueResult();
			// ts.commit();
			// HibernateSessionFactory.closeSession();

			return user;
		} catch (Exception e) {
			return null;
		}
	}

	public Long getCount() {
		try {
			Session session = HibernateSessionFactory.getSession();
			Query query = session.createQuery("select count(id) from User");
			query.setMaxResults(1);
			Long count = (Long) query.uniqueResult();
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			return (long) 0;
		}
	}

	public List<User> getUserList(Integer position) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			Query query = session.createQuery("from User");
			query.setMaxResults(15);
			query.setFirstResult(position * 15);
			List<User> list = query.list();
			ts.commit();
			HibernateSessionFactory.closeSession();
			return list;
		} catch (Exception e) {
			return null;
		}

	}

	public List<SuperUser> getSuperList() {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			Query query = session.createQuery("from SuperUser");
			List<SuperUser> list = query.list();

			ts.commit();
			HibernateSessionFactory.closeSession();
			return list;
		} catch (Exception e) {
			return null;
		}
	}

	public boolean deleteSuper(SuperUser superUser) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			SuperUser spUser = (SuperUser) session.load(SuperUser.class,
					superUser.getId());
			session.delete(spUser);
			ts.commit();
			HibernateSessionFactory.closeSession();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

	public boolean deleteUser(User user) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			User user2 = (User) session.load(User.class, user.getId());
			session.delete(user2);
			ts.commit();
			HibernateSessionFactory.closeSession();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean editSuper(SuperUser superUser) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();

			session.update(superUser);
			ts.commit();
			HibernateSessionFactory.closeSession();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

	public SuperUser findSuperByName(String username) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();

			Query query = session
					.createQuery("from SuperUser where username=?");
			query.setParameter(0, username);
			query.setMaxResults(1);
			SuperUser superUser = (SuperUser) query.uniqueResult();
			ts.commit();
			HibernateSessionFactory.closeSession();

			return superUser;
		} catch (Exception e) {
			return null;
		}
	}

	public SuperUser validate(String username, String password) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();

			Query query = session
					.createQuery("from SuperUser where username=? and password = ?");
			query.setParameter(0, username);
			query.setParameter(1, password);
			query.setMaxResults(1);
			SuperUser superUser = (SuperUser) query.uniqueResult();
			ts.commit();
			HibernateSessionFactory.closeSession();

			return superUser;
		} catch (Exception e) {
			return null;
		}
	}

	public boolean AddSuper(SuperUser superUser) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			SuperUser superUser2 = new SuperUser();

			superUser2.setUsername(superUser.getUsername());
			superUser2.setPassword(superUser.getPassword());
			superUser2.setAvailable(superUser.getAvailable());
			superUser2.setPriority(superUser.getPriority());
			superUser2.setClock(superUser.getClock());
			session.save(superUser2);

			ts.commit();
			// HibernateSessionFactory.closeSession();

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
