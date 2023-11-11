package com.depthspace.member.model.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.depthspace.member.model.MemVO;
import com.depthspace.member.model.jdbc.MemDAO_Interface;
import com.depthspace.tour.model.tourtype.TourTypeVO;

public class HibernateMemDAOImpl implements HibernateMemDAO_Interface {
	private SessionFactory factory;

	public HibernateMemDAOImpl(SessionFactory factory) {
		this.factory = factory;
	}

	private Session getSession() {
		return factory.getCurrentSession();
	}

	public HibernateMemDAOImpl() {
		super();
	}

	@Override
	public int insert(MemVO entity) {
		return (Integer) getSession().save(entity);
	}

	@Override
	public int update(MemVO entity) {
		try {
			getSession().update(entity);
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public int delete(Integer memId) {
		MemVO memVO = getSession().get(MemVO.class, memId);
		if (memVO != null) {
			getSession().delete(memVO);
			return 1;
		} else {
			return -1;
		}
	}

	@Override
	public MemVO getById(Integer memId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MemVO> getAll() {
		return getSession().createQuery("from MemVO", MemVO.class).list();
	}

	@Override
	public List<MemVO> getAll(int currentPage) {
		return null;
	}
	
	@Override
	public MemVO findByMemAcc(String memAcc) {
		return getSession().get(MemVO.class, memAcc);
	}
	@Override
	public MemVO getById(String memAcc) {
		return getSession().get(MemVO.class, memAcc);
	}
	@Override
	public MemVO findOneMem(String memAcc) {
		return getSession().get(MemVO.class, memAcc);
	}

}
