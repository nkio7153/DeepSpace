package com.depthspace.ticket.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.persistence.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.depthspace.attractions.model.CityVO;
import com.depthspace.ticket.model.*;
import com.depthspace.utils.DBUtil;
import com.depthspace.utils.HibernateUtil;



public class TicketDAOImpl implements TicketDAO {
	
	public static final int PAGE_MAX_RESULT = 10;
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
		return getSession().createQuery("from TicketVO",TicketVO.class) //查詢ticketVO實體 創建新查詢對象createQuery
				.setFirstResult(first)
				.setMaxResults(PAGE_MAX_RESULT) //每頁紀錄數量
				.list(); //查出的資料存於此列表
	}
	
	//取得票券區域對應縣市名
    public List<TicketVO> getAllTicketsWithCity() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from TicketVO", TicketVO.class).list();
        }
    }
	
	//取得票券對應主圖片
	@Override
    public List<TicketVO> getAllTicketsWithMainImages() {		
        List<TicketVO> tickets = null;
        Session session = factory.getCurrentSession(); 

        try {
            String hql = "SELECT distinct t FROM Ticket t JOIN FETCH t.images i WHERE i.isMainImage = :value";

            Query<TicketVO> query = session.createQuery(hql, TicketVO.class);
            query.setParameter("value", (byte) 1); 

            tickets = query.getResultList(); 

        } catch (Exception e) {
            e.printStackTrace(); 
        }
        return tickets;
    }

	
//	@Override
//	public List<TicketVO> getByCompositeQuery(Map<String, String> map) {
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
//	    // 依照縣市查詢
//	    if (map.containsKey("areaId")) {
//	        predicates.add(builder.equal(root.get("areaId"), map.get("areaId")));
//	    }
//	    // 依照票券類型查詢
//	    if (map.containsKey("ticketType")) {
//	        predicates.add(builder.equal(root.get("ticketType"), map.get("ticketType")));
//	    }
//		
//		for (Map.Entry<String, String> row : map.entrySet()) {
//			
//			//票券名稱模糊查詢
//			if ("ticketName".equals(row.getKey())) {
//				predicates.add(builder.like(root.get("ticketName"), "%" + row.getValue() + "%"));
//			}
//			if ("areaId".equals(row.getKey())) {
//				predicates.add(builder.equal(root.get("areaId"), row.getValue()));
//			}
//			if ("ticketType".equals(row.getKey())) {
//				predicates.add(builder.equal(root.get("ticketType"), row.getValue()));
//			}
//		}
//
//		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
//		criteria.orderBy(builder.asc(root.get("ticketId")));
//		TypedQuery<TicketVO> query = getSession().createQuery(criteria);
//
//		return query.getResultList();
//	}
	
	
	@Override
	public long getTotal() {
//		return getSession().createQuery("select count(*) from TicketVO", Long.class).uniqueResult();

		    Session session = null;
		    Transaction transaction = null;
		    long count = 0;

		    try {
		        session = getSession(); 
		        transaction = session.beginTransaction(); 
		        
		        Query<Long> countQuery = session.createQuery("select count(*) from TicketVO", Long.class);
		        count = countQuery.uniqueResult(); 
		        
		        transaction.commit(); 
		    } catch (RuntimeException e) {
		        if (transaction != null) {
		            transaction.rollback();
		        }
		        throw e; 
		    } finally {
		        if (session != null) {
		             session.close();
		        }
		    }

		    return count;
		}

	

	@Override
	public List<TicketVO> getAll() {
		return getSession().createQuery("from TicketVO", TicketVO.class).list();
////		return getSession().createQuery("from TicketVO",TicketVO.class).list();
//	    
//	    Session session = getSession();
//
//	    // 创建HQL查询
//	    String hql = "FROM TicketVO"; 
//	    Query query = session.createQuery(hql);
//
//	    List<TicketVO> list = query.list();
//	    return list;
	}

}

