package com.depthspace.notifications.service;

import java.sql.Timestamp;
import java.util.List;

import javax.websocket.Session;

import com.depthspace.member.model.MemVO;
import com.depthspace.notifications.model.NotificationsDAO;
import com.depthspace.notifications.model.NotificationsDAOImpl;
import com.depthspace.notifications.model.NotificationsVO;
import com.depthspace.ticketorders.model.ticketorders.TicketOrdersVO;
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

	@Override
  	public void ticketOrderNotification(TicketOrdersVO order, MemVO member) {
        NotificationsVO notification = new NotificationsVO();
        notification.setMemId(member.getMemId());
        notification.setNoteType("票券通知");
        notification.setNoteContent("您的訂單已成功建立！訂單號碼為" + order.getOrderId());
        notification.setNoteCreated(new Timestamp(System.currentTimeMillis()));
        notification.setNoteRead((byte) 0);

        dao.insert(notification);
        
        // WebSocket 
        }
    

}
