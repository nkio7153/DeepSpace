package com.depthspace.ticket.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.depthspace.utils.DBUtil;
import com.depthspace.utils.HibernateUtil;

public class TicketHibernateDAO implements TicketDAO_Interface2 {
	
	private SessionFactory factory;
	
	public TicketHibernateDAO(SessionFactory factory) {
		this.factory = factory;		
	}
	//getCurrentSession() 是 SessionFactory 的一個方法，
    //返回當前的 session（如果沒有則創建一個）
	private Session getSession() {
		return factory.getCurrentSession();
	}
	
	@Override
	public int insert(TicketVO ticketVO) {
	    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    
	    try {
	        session.beginTransaction();
	        Integer ticketId = (Integer) session.save(ticketVO);
	        session.getTransaction().commit();
			return ticketId;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;
	}


	@Override
	public int update(TicketVO ticketVO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Integer ticketId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public TicketVO getById(Integer ticketId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TicketVO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TicketVO> getByCompositeQuery(Map<String, String> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TicketVO> getAll(int currentPage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getTotal() {
		// TODO Auto-generated method stub
		return 0;
	}



////////////////////////方法測試/////////////////////
	
    public static void main(String[] args) {
        // 創建一個SessionFactory對象
        SessionFactory factory = HibernateUtil.getSessionFactory();
        
        // 使用該SessionFactory創建TicketHibernateDAO的實例
        TicketHibernateDAO dao = new TicketHibernateDAO(factory);
        
        // 創建一個新的TicketVO對象作為測試數據
        TicketVO ticket01 = new TicketVO();
        // 設置newTicket的新資料
         ticket01.setTicketTypeId(1);
         ticket01.setTicketName("苗栗水族館");
         ticket01.setDescription("好好玩好好玩好好玩好好玩好好玩");
         ticket01.setPrice(400);
         ticket01.setStock(500);
         ticket01.setValidDays(365);
         ticket01.setStatus((byte)1);
         ticket01.setPublishedDate(new java.sql.Timestamp(System.currentTimeMillis()));
         ticket01.setTotalStarRatings(20);
         ticket01.setTotalStars(100);
         
        // 調用insert方法返回的票券ID
        int R = dao.insert(ticket01);
        System.out.println("Inserted new ticket with ID: " + R);
    
	
	
	}
}

