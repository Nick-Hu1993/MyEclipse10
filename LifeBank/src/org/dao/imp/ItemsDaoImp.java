package org.dao.imp;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import org.dao.ItemsDao;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.model.Can;
import org.model.Items;
import org.util.HibernateSessionFactory;

public class ItemsDaoImp implements ItemsDao {

	public boolean insert(List<String> r) {
		if (r.size() < 11) {
			return false;
		}
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			Items i = new Items(r.get(2), r.get(3), r.get(4), r.get(5),
					r.get(6), r.get(7), r.get(8), r.get(9), r.get(10),
					r.get(11), r.get(12), r.get(13), Integer.parseInt(r.get(14)));
			session.save(i);
			ts.commit();
			HibernateSessionFactory.closeSession();
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public Items getLastItemsBySerial(String Serial) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			String sql = "from Items where serial = ? ORDER BY clock DESC";
			Query query = session.createQuery(sql);
			query.setParameter(0, Serial);
			query.setMaxResults(1);
			Items items = (Items) query.uniqueResult();

			ts.commit();
			HibernateSessionFactory.closeSession();
			return items;
		} catch (Exception e) {
			return null;
		}
	}

	public List<Items> getItemListBySerial(String Serial) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			String sql = "from Items where serial = ? ORDER BY clock DESC";
			Query query = session.createQuery(sql);
			query.setParameter(0, Serial);
			List<Items> items = query.list();

			ts.commit();
			HibernateSessionFactory.closeSession();
			return items;
		} catch (Exception e) {
			return null;
		}
	}

	public List<Items> getItemList(Integer position) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			
			String sql = "from Items ORDER BY clock DESC GROUP BY serial";
			Query query = session.createQuery(sql);
			if(position==-2){
				System.out.println(position);
			}else {
				query.setMaxResults(15);
				query.setFirstResult(position*15);
			}
			List<Items> items = query.list();

			ts.commit();
			HibernateSessionFactory.closeSession();
			return items;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	public List getItemList() {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			
			String sql = "select i.*,c.address from items i,can c where c.serial=i.serial ORDER BY i.clock DESC; ";
			SQLQuery sqlQuery = session.createSQLQuery(sql);
			sqlQuery.addEntity("i",Items.class).addEntity("c",Can.class);
			List list= sqlQuery.list();

			ts.commit();
			HibernateSessionFactory.closeSession();
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	

	public boolean deleteItem(String id) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();

			Items item = (Items) session.load(Items.class, Long.parseLong(id));
			session.delete(item);
			ts.commit();
			HibernateSessionFactory.closeSession();
			return true;

		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

	public List<Items> getItemsByPeriod(Integer start, Integer end,Integer position) {
		try {
			Session session = HibernateSessionFactory.getSession();
//			Transaction ts = session.beginTransaction();

			Query query = session.createQuery("from Items where clock<" + end
					+ "and clock>" + start);
			query.setMaxResults(15);
			query.setFirstResult(15*position);
			List<Items> list = query.list();
			
//			ts.commit();
//			HibernateSessionFactory.closeSession();
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	public boolean delete(String[] itemids) {
		String sql = "";
		for (int i = 0; i < itemids.length; i++) {
			if (i == 0) {
				sql = "id=" + itemids[i];
			} else {
				sql = sql + " or id=" + itemids[i];
			}
		}
		try {
			Session session = HibernateSessionFactory.getSession();
			Query q = session.createQuery("delete from Items where " + sql);
			q.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean delete(Integer start_clock, Integer end_clock) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Query q = session
					.createQuery("delete from Items where clock>? and clock<?");
			q.setParameter(0, start_clock);
			q.setParameter(1, end_clock);
			q.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Long getCount() {
		// TODO Auto-generated method stub
		try {
			Session session = HibernateSessionFactory.getSession();
			Query query = session.createQuery("select count(id) from Items");
			query.setMaxResults(1);
			Long count = (Long) query.uniqueResult();
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			return (long) 0;
		}
	}

	@Override
	public Long getCount(Integer start_clock,Integer end_clock) {
		// TODO Auto-generated method stub
		try {
			Session session = HibernateSessionFactory.getSession();
			Query query = session.createQuery("select count(id) from Items where clock>? and clock <?");
			query.setParameter(0, start_clock);
			query.setParameter(1, end_clock);
			query.setMaxResults(1);
			Long count = (Long) query.uniqueResult();
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			return (long) 0;
		}
	}

}
