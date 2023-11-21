package com.depthspace.notifications.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

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

	@Override
	public Integer getUnreadNotificationsCount(Integer memId) {
        Integer unreadCount = 0;
        try {
            String hql = "SELECT COUNT(*) FROM NotificationsVO WHERE memId = :memId AND noteRead = 0";
            Query<Long> query = getSession().createQuery(hql, Long.class);
            query.setParameter("memId", memId);
            unreadCount = query.uniqueResult().intValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return unreadCount;
    }
}
