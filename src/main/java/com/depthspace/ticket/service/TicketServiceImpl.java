package com.depthspace.ticket.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.depthspace.ticket.dao.TicketDAO;
import com.depthspace.ticket.dao.TicketDAOImpl;
import com.depthspace.ticket.model.TicketImagesVO;
import com.depthspace.ticket.model.TicketVO;
import com.depthspace.ticketorders.model.ticketorderdetail.TicketOrderDetailVO;
import com.depthspace.utils.HibernateUtil;

public class TicketServiceImpl implements TicketService {

	public static final int PAGE_MAX_RESULT = 10;
	private TicketDAO dao;

	public TicketServiceImpl() {
		dao = new TicketDAOImpl(HibernateUtil.getSessionFactory());
	}
	
	//新增票券
	@Override
	public void addTicket(TicketVO ticketVO) {
		dao.insert(ticketVO);
	}

	//更新票券
	@Override
	public TicketVO updateTicket(TicketVO ticketVO) {
		dao.update(ticketVO);
        return null;
	}
	
	//刪除票券
	@Override
	public TicketVO deleteTicket(Integer ticketId) {
		dao.delete(ticketId);
        return null;
	}
	
	
//	    Transaction transaction = null;
//	    int deletedCount = 0;
//
//	    try {
//	        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//	        transaction = session.beginTransaction();
//	        
//	        deletedCount = dao.delete(ticketId);
//
//	        transaction.commit();
//	    } catch (Exception e) {
//	        if (transaction != null) {
//	            transaction.rollback();
//	        }
//	        e.printStackTrace();
//	    }
//	}

	//查詢票券
	@Override
	public List<TicketVO> getTicketsByCompositeQuery(Map<String, String[]> map) {
		Map<String, String> query = new HashMap<>();
		// Map.Entry即代表一組key-value
		Set<Map.Entry<String, String[]>> entry = map.entrySet();
		
		for (Map.Entry<String, String[]> row : entry) {
			String key = row.getKey();
			// 因為請求參數裡包含了action，做個去除動作
			if ("action".equals(key)) {
				continue;
			}
			// 若是value為空即代表沒有查詢條件，做個去除動作
			String value = row.getValue()[0];
			if (value.isEmpty() || value == null) {
				continue;
			}
			query.put(key, value);
		}
		
		System.out.println(query);
		
		return dao.getByCompositeQuery(query);
	}
	
	@Override
	public TicketVO getTicketById(Integer ticketId) {
		Transaction transaction = null;
		TicketVO ticket = null;  // 將 ticket 的宣告移到這裡
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			
			ticket = dao.getTicketById(ticketId);  // 賦值
			
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw new RuntimeException("Error", e);
		}

		return ticket;  // 現在你可以正確地返回 ticket
	}	


	@Override	
	public List<TicketVO> getAllTickets() {
		return dao.getAll();
	}


	//取得所有票券
	@Override
	public List<TicketVO> getAllTickets(int currentPage) {
		Transaction transaction = null;
		List<TicketVO> tickets = new ArrayList<>();

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();

			Query<TicketVO> query = session.createQuery("FROM TicketVO", TicketVO.class);

			// 分頁
			query.setFirstResult((currentPage - 1) * PAGE_MAX_RESULT);
			query.setMaxResults(PAGE_MAX_RESULT);

			tickets = query.list();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw new RuntimeException("Error", e);
		}

		return tickets;
	}

	//分頁
	@Override
	public int getPageTotal() {
		long total = dao.getTotal();
		// 計算數量每頁3筆的話總共有幾頁
		int pageQty = (int) (total % PAGE_MAX_RESULT == 0 ? (total / PAGE_MAX_RESULT) : (total / PAGE_MAX_RESULT + 1));
		return pageQty;
	}
	
	//取得票券區域
	public List<TicketVO> getTicketsWithCity() {
		return dao.getAllTicketsWithCity();
	}

	@Override
	public List<TicketVO> getAllTicketsWithMainImages() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override  //取得總票券數
    public long getTotalTickets() {
        return dao.getTotal();
    }
}
