package com.depthspace.notifications.service;

import java.util.List;

import com.depthspace.notifications.model.NotificationsDAO;
import com.depthspace.notifications.model.NotificationsDAOImpl;
import com.depthspace.notifications.model.NotificationsVO;
import com.depthspace.utils.HibernateUtil;

public class NotificationsServiceImpl implements NotificationsService {

	private NotificationsDAO dao;
	
	public NotificationsServiceImpl() {
		dao = new NotificationsDAOImpl(HibernateUtil.getSessionFactory());
	}
	@Override
	public void insert(NotificationsVO notificationsVO) {
		dao.insert(notificationsVO);
	}

	@Override
	public NotificationsVO getOneByNoteId(Integer noteId) {
		return dao.getOneByNoteId(noteId);
	}
	
	@Override
	public List<NotificationsVO> getByMemId(Integer memId) {
		return dao.getByMemId(memId);
	}

	@Override
	public List<NotificationsVO> getAll() {
		return dao.getAll();
	}

}
