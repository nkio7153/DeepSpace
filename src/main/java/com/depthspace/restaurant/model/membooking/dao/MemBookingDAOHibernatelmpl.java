package com.depthspace.restaurant.model.membooking.dao;

import java.util.List;

import org.hibernate.Session;

import com.depthspace.restaurant.model.membooking.MemBookingDAO;
import com.depthspace.restaurant.model.membooking.MemBookingVO;
import com.depthspace.utils.HibernateUtil;

public class MemBookingDAOHibernatelmpl implements MemBookingDAO {

	@Override
	public List<MemBookingVO> findByRestID(Integer restId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int add(MemBookingVO memBookingVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Integer bookingId = (Integer) session.save(memBookingVO);
			session.getTransaction().commit();
			return bookingId;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public int update(MemBookingVO memBookingVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.update(memBookingVO);
			session.getTransaction().commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public int delete(Integer bookingId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			MemBookingVO memBookingVO = session.get(MemBookingVO.class, bookingId);
			if (memBookingVO != null) {
				session.delete(memBookingVO);
			}
			session.getTransaction().commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public MemBookingVO findByPK(Integer bookingId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			MemBookingVO memBookingVO = session.get(MemBookingVO.class, bookingId);
			session.getTransaction().commit();
			return memBookingVO;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}

	@Override
	public List<MemBookingVO> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			List<MemBookingVO> list = session.createQuery("FROM MemBookingVO", MemBookingVO.class).list();
			session.getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}
	
}
