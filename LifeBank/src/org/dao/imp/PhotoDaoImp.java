package org.dao.imp;

import java.util.List;

import org.dao.PhotoDao;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.model.Photo;
import org.util.HibernateSessionFactory;

public class PhotoDaoImp implements PhotoDao {

	public boolean insert(Long userid, Integer type, Long foreignId,
			List<String> urlList) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			for (String url : urlList) {
				Photo photo = new Photo(userid, type, foreignId, url);
				session.save(photo);
			}
			ts.commit();
			HibernateSessionFactory.closeSession();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean insert(Long userid, Integer type, String url) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			Photo photo = new Photo(userid, type, url);
			session.save(photo);
			ts.commit();
			HibernateSessionFactory.closeSession();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public List<Photo> getPhotos(Long userid, Long foreignId, Integer type) {
		try {
			Session session = HibernateSessionFactory.getSession();
			// Transaction ts = session.beginTransaction();

			Query query = session
					.createQuery("from Photo where userid=? and foreignId=? and type=?");
			query.setParameter(0, userid);
			query.setParameter(1, foreignId);
			query.setParameter(2, type);
			List<Photo> photos = query.list();
			// ts.commit();
			// HibernateSessionFactory.closeSession();

			return photos;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<String> getUrlList(Long userid, Long foreignId, Integer type) {
		try {
			Session session = HibernateSessionFactory.getSession();
			// Transaction ts = session.beginTransaction();

			Query query = session
					.createQuery("select url from Photo where userid=? and foreignId=? and type=?");
			query.setParameter(0, userid);
			query.setParameter(1, foreignId);
			query.setParameter(2, type);
			List<String> urlList = query.list();
			// ts.commit();
			// HibernateSessionFactory.closeSession();

			return urlList;

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

			Query query = session.createQuery("delete from Photo where userid="
					+ userid);
			query.executeUpdate();

			ts.commit();
			HibernateSessionFactory.closeSession();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean delete(Long userid, Integer type) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			Query query = session.createQuery("delete from Photo where userid="
					+ userid + " and type=" + type);
			query.executeUpdate();
			ts.commit();
			HibernateSessionFactory.closeSession();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Photo> getPhotos(Long userid) {
		try {
			Session session = HibernateSessionFactory.getSession();
			// Transaction ts = session.beginTransaction();

			Query query = session.createQuery("from Photo where userid=?");
			query.setParameter(0, userid);
			List<Photo> photos = query.list();
			// ts.commit();
			// HibernateSessionFactory.closeSession();

			return photos;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<String> getUrlList(Long userid) {
		try {
			Session session = HibernateSessionFactory.getSession();
			// Transaction ts = session.beginTransaction();

			Query query = session
					.createQuery("select url from Photo where userid=?");
			query.setParameter(0, userid);
			List<String> urlList = query.list();
			// ts.commit();
			// HibernateSessionFactory.closeSession();

			return urlList;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Photo getPhoto(Long userid, Integer type) {
		try {
			Session session = HibernateSessionFactory.getSession();

			Query query = session
					.createQuery("from Photo where userid=? and type=? order by id DESC");
			query.setParameter(0, userid);
			query.setParameter(1, type);
			query.setMaxResults(1);
			Photo photo = (Photo) query.uniqueResult();
			return photo;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Photo> getUsedHead(Long userid, Integer type) {
		try {
			Session session = HibernateSessionFactory.getSession();

			Query query = session
					.createQuery("from Photo where userid=? and type=? order by id DESC");
			query.setParameter(0, userid);
			query.setParameter(1, type);
			List<Photo> photos = query.list();
			return photos;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<String> getTopAllUrl(Long foreignId) {
		// TODO Auto-generated method stub
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "select p.url from photo p where p.foreign_id in (select c.id from comments c where c.topic_id=?) or p.foreign_id = ?";
			SQLQuery sqlQuery = session.createSQLQuery(sql);
			sqlQuery.setParameter(0, foreignId);
			sqlQuery.setParameter(1, foreignId);
			List<String> urlList = sqlQuery.list();
			return urlList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean delByForeignId(Long foreignId) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			Query query = session
					.createQuery("delete from Photo where foreignId="
							+ foreignId);
			query.executeUpdate();
			ts.commit();
			HibernateSessionFactory.closeSession();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delByUrl(String url) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			Query query = session
					.createQuery("delete from Photo where url="
							+ url);
			query.executeUpdate();
			ts.commit();
			HibernateSessionFactory.closeSession();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<String> getUrlByForeignId(Long foreignId) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Query query = session
					.createQuery("select url from Photo where foreignId = ?");
			query.setParameter(0, foreignId);
			List<String> urlList = query.list();
			return urlList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
