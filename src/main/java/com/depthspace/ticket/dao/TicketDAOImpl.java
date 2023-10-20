package com.depthspace.ticket.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Predicate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.depthspace.ticket.model.TicketVO;
import com.depthspace.utils.DBUtil;
import com.depthspace.utils.HibernateUtil;
//import com.depthspace.utils.Constants.PAGE_MAX_RESULT;

public class TicketDAOImpl implements TicketDAO {
	
	private static final int PAGE_MAX_RESULT = 10;
	private SessionFactory factory;
	
	public TicketDAOImpl(SessionFactory factory) {
		this.factory = factory;		
	}
	
	//避免執行緒共用同個Session
	private Session getSession() {
		return factory.getCurrentSession();
	}
	
	@Override
	public int insert(TicketVO ticketVO) {
		
		return (Integer) getSession().save(ticketVO); 
		//回傳新增成功的ID
	}
	
	
	@Override
	public int update(TicketVO ticketVO) {
		try {
			getSession().update(ticketVO);
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}
	
	@Override
	public int delete(Integer id) {
		TicketVO ticketVO =getSession().get(TicketVO.class, id);  //.class說明是要查資料庫，且標註為id的欄位
		if (ticketVO != null) {
			getSession().delete(ticketVO);
			return 1;
		} else {
			return -1;
		}
	}
	
	@Override
	public TicketVO getById(Integer id) {
		return getSession().get(TicketVO.class, id); //.class說明是要查資料庫，且標註為id的欄位
	}
	
	@Override
	public List<TicketVO> getAll(int currentPage) {
		int first = (currentPage - 1) * PAGE_MAX_RESULT; //計算當前頁面第一條索引
		return getSession().createQuery("from TicketVO",TicketVO.class) //查詢ticketvo實體 創建新查詢對象createQuery
				.setFirstResult(first)
				.setMaxResults(PAGE_MAX_RESULT) //每頁紀錄數量
				.list(); //查出的資料存於此列表
	
	}
	

	
	
//	@Override
	public List<TicketVO> getByCompositeQuery(Map<String, String> map) {
//		if(map.size() == 0) //如果map傳入條件為0則回傳全部票券
//			return getAll();
//		
//		CriteriaBuilder builder = getSession().getCriteriaBuilder(); //初始化建構標準化查詢
//		CriteriaQuery<TicketVO> criteria = builder.createQuery(TicketVO.class); //criteriaquery為一個特定查詢
//		Root<TicketVO> root = criteria.from(TicketVO.class); //創建存放所有查詢條件的根實例
//		
//		List<Predicate> predicates = new ArrayList<>(); //條件列表初始化
//		
//		//票券ID範圍查詢
//		if(map.containsKey("startTicketId") && map.containsKey("endTicketId")) {
//			predicates.add(builder.between(root.get("ticketId"),
//					Integer.valueOf(map.get("startTicketId")),
//					Integer.valueOf(map.get("endTicketId"))));
//		}
//		
//		//票券名稱模糊查詢
//		if(map.containsKey(""))
	
		return null;
	
	
	}
	@Override
	public List<TicketVO> findTicketsByPartialName(String partialName) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int getTotal() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<TicketVO> getAll() {
		return getSession().createQuery("from TicketVO",TicketVO.class).list();
	}
	


//
//////////////////////////方法測試/////////////////////
//	
//    public static void main(String[] args) {
//        // 創建一個SessionFactory對象
//        SessionFactory factory = HibernateUtil.getSessionFactory();
//        
//        // 使用該SessionFactory創建TicketHibernateDAO的實例
//        TicketDAOlmpl dao = new TicketDAOlmpl(factory);
//        
//        // 創建一個新的TicketVO對象作為測試數據
//        TicketVO ticket01 = new TicketVO();
//        // 設置newTicket的新資料
//         ticket01.setTicketTypeId(1);
//         ticket01.setTicketName("苗栗水族館");
//         ticket01.setDescription("好好玩好好玩好好玩好好玩好好玩");
//         ticket01.setPrice(400);
//         ticket01.setStock(500);
//         ticket01.setValidDays(365);
//         ticket01.setStatus((byte)1);
//         ticket01.setPublishedDate(new java.sql.Timestamp(System.currentTimeMillis()));
//         ticket01.setTotalStarRatings(20);
//         ticket01.setTotalStars(100);
//         
//        // 調用insert方法返回的票券ID
//        int R = dao.insert(ticket01);
//        System.out.println("Inserted new ticket with ID: " + R);
//    
//	
	
//	}
}

