//package com.depthspace.tour.model;
//
//import org.hibernate.Session;
//
//import com.depthspace.utils.HibernateUtil;
//
//public class TestSaveTourDays {
//	public static void main(String[] args) {
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//		try {
//			session.beginTransaction();
//			TourDaysVO tourDaysVO = new TourDaysVO();
//			tourDaysVO.setTourDays(2);
//			tourDaysVO.setTourId(4);
//
//			session.save(tourDaysVO);
//			session.getTransaction().commit();
//		} catch (Exception e) {
//			session.getTransaction().rollback();
//			e.printStackTrace();
//		} finally {
//			HibernateUtil.shutdown();
//		}
//	}
//}
