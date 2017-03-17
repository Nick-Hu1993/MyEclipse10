package org.dao.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.dao.ReplysDao;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.model.Comments;
import org.model.Replys;
import org.model.Topics;
import org.util.HibernateSessionFactory;

public class ReplysDaoImp implements ReplysDao {

	public List<Map<String, String>> getRepList(String commentId) {
		return null;
	}

	public List<Replys> getReplys1(String commentId) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			String sql = "select * from replys where comment_id=" + commentId +" ORDER BY clock";
			SQLQuery sqlQuery = session.createSQLQuery(sql);
			sqlQuery.addEntity(Replys.class);
			List<Replys> replys = sqlQuery.list();
			ts.commit();
			HibernateSessionFactory.closeSession();
			return replys;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Map<String, String>> getRepList1(String commentId) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			String sql = "select * from replys where comment_id=" + commentId
					+ " ORDER BY clock DESC ";
			SQLQuery sqlQuery = session.createSQLQuery(sql);
			sqlQuery.addEntity(Replys.class);
			List<Replys> replys = sqlQuery.list();
			List<Map<String, String>> list = new ArrayList<Map<String, String>>();
			for (Replys reply : replys) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("id", "" + reply.getId());
				map.put("userid", "" + reply.getUserid());
				map.put("content", "" + reply.getContent());
				map.put("clock", "" + reply.getClock());
				map.put("commentId", "" + reply.getComment().getId());
				list.add(map);
			}
			// JSONArray JsonArry = JSONArray.fromObject(list);
			// JsonConfig jsonConfig = new JsonConfig();
			// jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
			// JSONArray json =JSONArray.fromObject(list, jsonConfig);
			// System.out.println(json.toString());
			// System.out.println(JsonArry.toString());
			ts.commit();
			HibernateSessionFactory.closeSession();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean delReply(String replyId) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();

			Replys reply = (Replys) session.load(Replys.class,
					Long.parseLong(replyId));
			reply.getComment().getReplys().remove(reply);
			reply.setComment(null);

			session.delete(reply);

			ts.commit();
			HibernateSessionFactory.closeSession();

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public Replys getRep(String replyId) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "from Replys where id= ?";
			Query query = session.createQuery(sql);
			query.setParameter(0, Long.parseLong(replyId));
			query.setMaxResults(1);
			Replys reply = (Replys) query.uniqueResult();

			return reply;
		} catch (Exception e) {
			return null;
		}
	}

	public boolean insert(Replys reply) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();

			Replys reply1 = new Replys();
			reply1.setClock(reply.getClock());
			reply1.setContent(reply.getContent());
			reply1.setUserid(reply.getUserid());
			reply1.setComment(reply.getComment());
			session.save(reply1);

			ts.commit();
			HibernateSessionFactory.closeSession();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
