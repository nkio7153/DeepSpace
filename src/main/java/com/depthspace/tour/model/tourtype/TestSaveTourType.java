package com.depthspace.tour.model.tourtype;

import org.hibernate.Session;

import com.depthspace.utils.HibernateUtil;

public class TestSaveTourType {
	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			TourTypeVO tourTypeVO = new TourTypeVO();
			tourTypeVO.setTourTypName("踏青遊");

			session.save(tourTypeVO);
			session.getTransaction().commit();

		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.shutdown();
		}
	}
}
