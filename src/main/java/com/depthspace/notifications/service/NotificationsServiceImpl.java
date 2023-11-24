package com.depthspace.notifications.service;

import java.sql.Timestamp;
import java.util.List;

import javax.websocket.Session;

import com.depthspace.member.model.MemVO;
import com.depthspace.notifications.controller.NotificationsWS;
import com.depthspace.notifications.model.NotificationsDAO;
import com.depthspace.notifications.model.NotificationsDAOImpl;
import com.depthspace.notifications.model.NotificationsVO;
import com.depthspace.restaurant.model.membooking.MemBookingVO;
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
	
	public Integer getUnreadNotificationsCount(Integer memId) {
        return dao.getUnreadNotificationsCount(memId);
    }

	@Override
  	public void ticketOrderNotification(TicketOrdersVO order, MemVO member) {
        NotificationsVO notification = new NotificationsVO();
        notification.setMemId(member.getMemId());
        notification.setNoteType("票券通知");

        String orderLink = "http://depthspace.ddns.net/DepthSpace/tod/frontList?orderId="+order.getOrderId();
        notification.setNoteContent("您的訂單已成功建立！訂單號碼為 " + order.getOrderId() +
                                    "。 <a href='" + orderLink + "'>點擊此處查看訂單詳情</a>");
    
        notification.setNoteCreated(new Timestamp(System.currentTimeMillis()));
        notification.setNoteRead((byte) 0);

        dao.insert(notification);
        
        }

	@Override
  	public void RestOrderNotification(MemBookingVO mb, MemVO member) {
//        NotificationsVO notification = new NotificationsVO();
//        notification.setMemId(member.getMemId());
//        notification.setNoteType("餐廳通知");
//
////        String orderLink = "/tod/frontList?orderId="+order.getOrderId();
////        notification.setNoteContent("您的訂單已成功建立！訂單號碼為 " + order.getOrderId() +
////                                    "。 <a href='" + orderLink + "'>點擊此處查看訂單詳情</a>");
//    
//        notification.setNoteContent("您的訂單已成功建立！訂單號碼為 " + order.getOrderId());
//        notification.setNoteCreated(new Timestamp(System.currentTimeMillis()));
//        notification.setNoteRead((byte) 0);
//
//        dao.insert(notification);
//        
////        // WebSocket 
////        NotificationsWS webSocket = new NotificationsWS();
////        String message = "您有新的" + notification.getNoteType();
////        webSocket.sendNotification(message);
////        System.out.println(message);
        }
	
	@Override
	public void toBeRead(Integer noteId) {
		NotificationsVO notification = getOneByNoteId(noteId);
		if(notification != null && notification.getNoteRead() == 0 ) {
	 		notification.setNoteRead((byte) 1);
			dao.update(notification);
		}
		
		
	}
    

}
