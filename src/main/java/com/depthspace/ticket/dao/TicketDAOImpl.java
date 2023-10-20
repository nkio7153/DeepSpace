package com.depthspace.ticket.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;


import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Predicate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.depthspace.attractions.model.CityVO;
import com.depthspace.ticket.model.TicketVO;
import com.depthspace.utils.DBUtil;
import com.depthspace.utils.HibernateUtil;
//import com.depthspace.utils.Constants.PAGE_MAX_RESULT;


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
		return getSession().createQuery("from TicketVO",TicketVO.class) //查詢ticketvo實體 創建新查詢對象createQuery
				.setFirstResult(first)
				.setMaxResults(PAGE_MAX_RESULT) //每頁紀錄數量
				.list(); //查出的資料存於此列表
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
		            // 如果需要，可以关闭session，但这取决于您的session管理策略
		             session.close();
		        }
		    }

		    return count;
		}

	

	@Override
	public List<TicketVO> getAll() {
		return getSession().createQuery("from TicketVO", TicketVO.class).list();
////		return getSession().createQuery("from TicketVO",TicketVO.class).list();
//	    // 获取当前的Hibernate会话
//	    Session session = getSession();
//
//	    // 创建HQL查询
//	    String hql = "FROM TicketVO";  // "TicketVO" 是您的实体类名
//	    Query query = session.createQuery(hql);
//
//	    // 执行查询并获取结果
//	    List<TicketVO> list = query.list();
//	    return list;
	}

	@Override
	public List<TicketVO> getTicketCityName() {
	    Session session = factory.getCurrentSession();
	    Transaction transaction = null;

	    try {
	        transaction = session.beginTransaction();

	        // 查询所有的票据
	        String hqlTickets = "FROM TicketVO";
	        Query<TicketVO> queryTickets = session.createQuery(hqlTickets, TicketVO.class);
	        List<TicketVO> tickets = queryTickets.list();

	        // 查询所有的城市
	        String hqlCities = "FROM CityVO";
	        Query<CityVO> queryCities = session.createQuery(hqlCities, CityVO.class);
	        List<CityVO> cities = queryCities.list();

	        // 创建城市ID到城市名称的映射
	        Map<Integer, String> cityMap = new HashMap<>();
	        for (CityVO city : cities) {
	            cityMap.put(city.getCityId(), city.getCityName());
	        }

	        // 对于tickets列表中的每个票据，设置相应的城市名称
	        for (TicketVO ticket : tickets) {
	            // 假设CityId是城市的外键，存储在TicketVO的某个字段中
	            String cityId = ticket.getCityName(); // 这里需要根据您的字段名进行调整
	            if (cityId != null) {
	                ticket.setCityName(cityMap.get(cityId));
	            }
	        }

	        transaction.commit();
	        return tickets;
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        throw new RuntimeException("Error fetching ticket and city names", e);
//	    } finally {
//	        if (session != null) {
//	            session.close();
//	        }
	    }
	}
}

