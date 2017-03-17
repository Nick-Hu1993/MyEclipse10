package org.dao.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;

import org.dao.CommentsDao;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.model.Comments;
import org.model.Replys;
import org.model.Topics;
import org.util.HibernateSessionFactory;

public class CommentsDaoImp implements CommentsDao {
	private final static Integer Max = 15;

	public List<Map<String, String>> getComList1(String topicId) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			String sql = "select * from comments where topic_id=" + topicId
					+ " ORDER BY clock DESC ";
			SQLQuery sqlQuery = session.createSQLQuery(sql);
			sqlQuery.addEntity(Comments.class);
			List<Comments> comments = sqlQuery.list();
			List<Map<String, String>> list = new ArrayList<Map<String, String>>();
			for (Comments comment : comments) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("id", "" + comment.getId());
				map.put("userid", "" + comment.getUserid());
				map.put("content", "" + comment.getContent());
				map.put("clock", "" + comment.getClock());
				map.put("topicId", "" + comment.getTopic().getId());
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

	public List<Map<String, String>> getComList(String topicId) {
		// TODO Auto-generated method stub

		return null;
	}

	public List<Comments> getComments(String topicId, Integer position) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();

			String sql = "select * from comments where topic_id=" + topicId
					+ " ORDER BY clock ";
			SQLQuery sqlQuery = session.createSQLQuery(sql);
			sqlQuery.addEntity(Comments.class);
//			sqlQuery.setMaxResults(Max);
			sqlQuery.setFirstResult(position);
			List<Comments> comments = sqlQuery.list();
			ts.commit();
			HibernateSessionFactory.closeSession();
			return comments;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public List<Comments> getComments1(String topicId, Integer position) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();

			String sql = "select * from comments where topic_id=" + topicId
					+ " ORDER BY clock ";
			SQLQuery sqlQuery = session.createSQLQuery(sql);
			sqlQuery.addEntity(Comments.class);
			sqlQuery.setMaxResults(Max);
			sqlQuery.setFirstResult(position);
			List<Comments> comments = sqlQuery.list();
			ts.commit();
			HibernateSessionFactory.closeSession();
			return comments;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean delComment(String commentId) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();

			Comments comment = (Comments) session.load(Comments.class,
					Long.parseLong(commentId));
			comment.getTopic().getComments().remove(comment);
			comment.setTopic(null);
			session.delete(comment);
			
			ts.commit();
			HibernateSessionFactory.closeSession();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Comments getCom(String commentId) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "from Comments where id= ?";
			Query query = session.createQuery(sql);
			query.setParameter(0, Long.parseLong(commentId));
			query.setMaxResults(1);
			Comments comment = (Comments) query.uniqueResult();

			return comment;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Comments getCom(Long userid,Integer clock) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "from Comments where userid= ? and clock=?";
			Query query = session.createQuery(sql);
			query.setParameter(0, userid);
			query.setParameter(1, clock);
			query.setMaxResults(1);
			Comments comment = (Comments) query.uniqueResult();

			return comment;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean insert(Comments comment){
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			
			Comments comment1 = new Comments();
			comment1.setClock(comment.getClock());
			comment1.setContent(comment.getContent());
			comment1.setUserid(comment.getUserid());
			comment1.setTopic(comment.getTopic());
			session.save(comment1);
			
			ts.commit();
			HibernateSessionFactory.closeSession();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
