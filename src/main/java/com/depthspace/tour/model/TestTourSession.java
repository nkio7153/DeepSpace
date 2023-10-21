package com.depthspace.tour.model;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.depthspace.member.model.MemVO;
import com.depthspace.utils.HibernateUtil;

public class TestTourSession {
	public static void main(String[] args) {
		Session s1 = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx1 = null;
		try {

			tx1 = s1.beginTransaction();

			TourVO rest = s1.get(TourVO.class, 1);
			System.out.println(rest);

			tx1.commit();

		} catch (Exception e) {
			e.printStackTrace();
			if (tx1 != null)
				tx1.rollback();
		} finally {
			HibernateUtil.shutdown();
		}

	}
}
