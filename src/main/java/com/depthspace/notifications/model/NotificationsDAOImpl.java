package com.depthspace.notifications.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class NotificationsDAOImpl implements NotificationsDAO {

	private SessionFactory factory;
	
	public NotificationsDAOImpl(SessionFactory factory) {
		this.factory = factory;
	}
	
	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public void insert(NotificationsVO notificationsVO) {
		getSession().save(notificationsVO);
	}
	
	@Override
	public NotificationsVO getOneByNoteId(Integer noteId) {
		return getSession().get(NotificationsVO.class, noteId);
	}
	
	@Override
	public List<NotificationsVO> getByMemId(Integer memId) {
	    return getSession().createQuery("FROM NotificationsVO n WHERE n.memId = :memId", NotificationsVO.class)
	                       .setParameter("memId", memId)
	                       .list();
	}
	

	@Override
	public List<NotificationsVO> getAll() {
		return getSession().createQuery("from NotificationsVO", NotificationsVO.class).list();	
	}
	
}
