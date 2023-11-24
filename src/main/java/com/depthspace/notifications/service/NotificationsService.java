package com.depthspace.notifications.service;

import java.util.List;

import com.depthspace.member.model.MemVO;
import com.depthspace.notifications.model.NotificationsVO;
import com.depthspace.restaurant.model.membooking.MemBookingVO;
import com.depthspace.ticketorders.model.ticketorders.TicketOrdersVO;

public interface NotificationsService {

	void insert(NotificationsVO notificationsVO);
	
	NotificationsVO getOneByNoteId(Integer noteId);
	
	List<NotificationsVO> getByMemId(Integer memId);
	
	List<NotificationsVO> getAll();
	
	Integer getUnreadNotificationsCount(Integer memId);
	
	void ticketOrderNotification(TicketOrdersVO order, MemVO member);
	
	void RestOrderNotification(MemBookingVO mb, MemVO member);
	
	void toBeRead(Integer noteId);
	
}
