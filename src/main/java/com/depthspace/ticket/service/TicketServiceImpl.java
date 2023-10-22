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
import com.depthspace.utils.HibernateUtil;

public class TicketServiceImpl implements TicketService {

	public static final int PAGE_MAX_RESULT = 10;
	private TicketDAO dao;

	public TicketServiceImpl() {
		dao = new TicketDAOImpl(HibernateUtil.getSessionFactory());
	}

	@Override
	public int addTicket(TicketVO ticketVO) {
		return dao.insert(ticketVO);
	}

	@Override
	public TicketVO updateTicket(TicketVO ticketVO) {
		dao.update(ticketVO);
		return ticketVO;
	}

	@Override
	public int deleteTicket(Integer ticketId) {
		return dao.delete(ticketId);
	}

	@Override
	public TicketVO getTicketById(Integer ticketId) {
		dao.getById(ticketId);
		return null;
	}
	

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
			throw new RuntimeException("Error fetching tickets", e);
		}

		return tickets;
	}

	public List<TicketVO> getTicketsWithCity() {
		return dao.getAllTicketsWithCity();
	}

	@Override
	public int getPageTotal() {
		long total = dao.getTotal();
		// 計算數量每頁3筆的話總共有幾頁
		int pageQty = (int) (total % PAGE_MAX_RESULT == 0 ? (total / PAGE_MAX_RESULT) : (total / PAGE_MAX_RESULT + 1));
		return pageQty;
	}

	// 找票券主圖
	@Override
	public List<TicketVO> getTicketsWithMainImage() {
		return dao.getAllTicketsWithMainImages();
	}

//	@Override
//	public List<TicketVO> getTicketsByCompositeQuery(Map<String, String[]> map) {		
//		Map<String, String> query = new HashMap<>();
//	// Map.Entry即代表一組key-value
//	Set<Map.Entry<String, String[]>> entry = map.entrySet();
//	
//	for (Map.Entry<String, String[]> row : entry) {
//		String key = row.getKey();
//		// 因為請求參數裡包含了action，做個去除動作
//		if ("action".equals(key)) {
//			continue;
//		}
//		// 若是value為空即代表沒有查詢條件，做個去除動作
//		String value = row.getValue()[0];
//		if (value.isEmpty() || value == null) {
//			continue;
//		}
//		query.put(key, value);
//	}
//	
//	System.out.println(query);
//	
//	return dao.getByCompositeQuery(query);
//	}
}
