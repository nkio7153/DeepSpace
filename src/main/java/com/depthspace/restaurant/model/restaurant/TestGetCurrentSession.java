package com.depthspace.restaurant.model.restaurant;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.depthspace.utils.HibernateUtil;

public class TestGetCurrentSession {
	public static void main(String[] args) {
		// 系統組態檔裡一定要有 <property name="hibernate.current_session_context_class">thread</property> 的設定才可以
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		SessionFactory factory = null;
		
		try {
			
//			session.beginTransaction();
			
//			RestVO rest = s1.get(RestVO.class, 10);
//			System.out.println(rest);
//			
//			tx1.commit();
			
			List<RestVO> list = factory.getCurrentSession().createQuery("from RestVO", RestVO.class).list();
			System.out.println(list);
			
		} catch (Exception e) {
//			e.printStackTrace();
//			session.getTransaction().rollback();
		} finally {
			HibernateUtil.shutdown();
		}

	}
}
