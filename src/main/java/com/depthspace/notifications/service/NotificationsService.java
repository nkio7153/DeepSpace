package com.depthspace.notifications.service;

import java.util.List;

import com.depthspace.notifications.model.NotificationsVO;

public interface NotificationsService {

	void insert(NotificationsVO notificationsVO);
	
	NotificationsVO getOneByNoteId(Integer noteId);
	
	List<NotificationsVO> getByMemId(Integer memId);
	
	List<NotificationsVO> getAll();
	
}
