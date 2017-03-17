package org.dao.imp;

import java.util.ArrayList;
import java.util.List;

import org.dao.CellDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.model.Cell;
import org.model.User;
import org.model.UserCell;
import org.util.HibernateSessionFactory;

public class CellDaoImp implements CellDao {

	public List<UserCell> getCellList(Long userid) {
		try {
			Session session = HibernateSessionFactory.getSession();

			Query query = session.createQuery("from UserCell where userid = ?");
			query.setParameter(0, userid);
			List<UserCell> list = query.list();

			return list;
		} catch (Exception e) {
			return  new ArrayList<UserCell>();
		}
	}

	public UserCell getUserCell(Long cellid) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();

			Query query = session.createQuery("from UserCell where cellid = ?");
			query.setParameter(0, cellid);
			query.setMaxResults(1);
			UserCell userCell = (UserCell) query.uniqueResult();
			ts.commit();
			HibernateSessionFactory.closeSession();
			return userCell;
		} catch (Exception e) {
			return null;
		}
	}

	public Cell getCell(Long cellid) {

		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "from Cell where id = ?";
			Query query = session.createQuery(sql);
			query.setParameter(0, cellid);
			query.setMaxResults(1);
			Cell cell = (Cell) query.uniqueResult();
			return cell;
		} catch (Exception e) {
			return null;
		}
	}

	public boolean insert(UserCell userCell) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			UserCell userCell2 = new UserCell();
			userCell2.setCellid(userCell.getCellid());
			userCell2.setUserid(userCell.getUserid());
			session.save(userCell2);

			ts.commit();
			HibernateSessionFactory.closeSession();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean insert(Cell cell) {

		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			Cell cell2 = new Cell();
			cell2.setClock(cell.getClock());
			cell2.setName(cell.getName());
			cell2.setNum(cell.getNum());
			cell2.setSerial(cell.getSerial());
			session.save(cell2);

			ts.commit();
			HibernateSessionFactory.closeSession();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Long getMaxId() {
		try {
			Session session = HibernateSessionFactory.getSession();
			Query query = session.createQuery("select Max(id) from Cell");
			query.setMaxResults(1);

			Long maxid = (Long) query.uniqueResult();

			return maxid;

		} catch (Exception e) {
			return null;
		}

	}

	public boolean deleteUserCell(Long userid) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Query q = session.createQuery("delete from UserCell where userid="
					+ userid);
			q.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean deleteCells(String[] cellids) {
		String sql = "";
		for (int i = 0; i < cellids.length; i++) {
			if (i == 0) {
				sql = "id=" + cellids[i];
			} else {
				sql = sql + " or id=" + cellids[i];
			}
		}
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			Query q = session.createQuery("delete from Cell where " + sql);
			q.executeUpdate();
			ts.commit();
			HibernateSessionFactory.closeSession();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Object[]> getCells(Integer position) {
		// TODO Auto-generated method stub
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();

			Query query = session
					.createQuery("select c.id,u.username,u.phone,c.name,c.num,c.serial,c.clock from Cell c,UserCell uc,User u where c.id=uc.cellid and u.id=uc.userid");
			query.setMaxResults(15);
			query.setFirstResult(position * 15);
			List<Object[]> cells = query.list();
			ts.commit();
			HibernateSessionFactory.closeSession();

			return cells;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Long getCellCount() {
		// TODO Auto-generated method stub
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();

			Query query = session.createQuery("select count(id) from Cell");
			Long count = (Long) query.uniqueResult();
			ts.commit();
			HibernateSessionFactory.closeSession();

			return count;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return (long) 0;
		}
	}

	public boolean delete(Cell cell) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			session.delete(cell);
			ts.commit();
			HibernateSessionFactory.closeSession();

			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Cell findCell(String name, String clock, String serial) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "from Cell where name = ? and clock=? and serial=?";
			Query query = session.createQuery(sql);
			query.setParameter(0, name);
			query.setParameter(1, Integer.parseInt(clock));
			query.setParameter(2, serial);
			query.setMaxResults(1);
			Cell cell = (Cell) query.uniqueResult();
			return cell;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
