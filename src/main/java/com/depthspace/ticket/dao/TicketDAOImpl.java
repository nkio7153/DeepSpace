package com.depthspace.ticket.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.depthspace.ticket.model.*;
import com.depthspace.utils.Constants;
import com.depthspace.utils.HibernateUtil;


public class TicketDAOImpl implements TicketDAO {

	private SessionFactory factory;

	public TicketDAOImpl(SessionFactory factory) {
		this.factory = factory;
	}

	private Session getSession() {
		return factory.getCurrentSession();
	}

	// 插入一筆資料
	@Override
	public int insert(TicketVO ticketVO) {
		return (Integer) getSession().save(ticketVO);
	}

	// 更新一筆資料
	@Override
	public TicketVO update(TicketVO ticketVO) {
		try {
			getSession().update(ticketVO);
			return ticketVO; 
		} catch (Exception e) {
			e.printStackTrace();
			return null; 
		}
	}

	// 刪除一筆資料
	@Override
	public int delete(Integer ticketId) {
		TicketVO ticketVO = getSession().get(TicketVO.class, ticketId); // .class說明是要查資料庫，且標註為id的欄位
		if (ticketVO != null) {
			getSession().delete(ticketVO);
			return 1;
		} else {
			return -1;
		}
	}

	// 根據票券ID取得一筆資料
	@Override
	public List<TicketVO> getTicketById2(Integer ticketId) {
		return getSession().createQuery("from TicketVO where ticketId= :ticketId", TicketVO.class)
				.setParameter("ticketId", ticketId).list();

	}

	// 根據票券ID取得一筆資料
	@Override
	public TicketVO getTicketById(Integer ticketId) {
		return getSession().get(TicketVO.class, ticketId); // .class說明是要查資料庫，且標註為id的欄位

	}

	// 取得所有票券資料(分頁)
	@Override
	public List<TicketVO> getAll(int currentPage) {
		int first = (currentPage - 1) * Constants.PAGE_MAX_RESULT; // 計算當前頁面第一條索引
		return getSession().createQuery("from TicketVO", TicketVO.class) // 查詢ticketVO實體 創建新查詢對象createQuery
				.setFirstResult(first).setMaxResults(Constants.PAGE_MAX_RESULT) // 每頁紀錄數量
				.list(); // 查出的資料存於此列表
	}

	// 取得所有票券
	@Override
	public List<TicketVO> getAll() {
		return getSession().createQuery("from TicketVO", TicketVO.class).list();
	}

	// 取得票券區域對應縣市名
	public List<TicketVO> getAllTicketsWithCity() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("from TicketVO", TicketVO.class).list();
		}
	}

	// 取得所有票券數量
	@Override
	public long getTotal() {
		return getSession().createQuery("select count(*) from TicketVO", Long.class).uniqueResult();
	}

	// 萬用查詢
	@Override
	public List<TicketVO> getByCompositeQuery(Map<String, List<String>> map) {
	    
	    if (map.size() == 0) {
	        return getAll();
	    }

	    CriteriaBuilder builder = getSession().getCriteriaBuilder();
	    CriteriaQuery<TicketVO> criteria = builder.createQuery(TicketVO.class);
	    Root<TicketVO> root = criteria.from(TicketVO.class);

	    List<Predicate> predicates = new ArrayList<>();

	    for (Map.Entry<String, List<String>> entry : map.entrySet()) {
	        String key = entry.getKey();
	        List<String> values = entry.getValue();

	        switch (key) {
	            case "ticketName":
	                predicates.add(builder.like(root.get("ticketName"), "%" + values.get(0) + "%"));
	                break;
	            case "ticketTypeId":
	                // 使用 builder.in 构建多值条件
	                CriteriaBuilder.In<Integer> inTicketTypeId = builder.in(root.get("ticketType").get("ticketTypeId"));
	                for (String value : values) {
	                    inTicketTypeId.value(Integer.parseInt(value));
	                }
	                predicates.add(inTicketTypeId);
	                break;
	            case "ticketId":
	                predicates.add(builder.equal(root.get("ticketId"), Integer.parseInt(values.get(0))));
	                break;
	            case "areaId":
	                // 使用 builder.in 构建多值条件
	                CriteriaBuilder.In<Integer> inAreaId = builder.in(root.get("city").get("cityId"));
	                for (String value : values) {
	                    inAreaId.value(Integer.parseInt(value));
	                }
	                predicates.add(inAreaId);
	                break;
	        }
	    }

	    criteria.where(builder.and(predicates.toArray(new Predicate[0])));
	    criteria.orderBy(builder.asc(root.get("ticketId")));
	    TypedQuery<TicketVO> query = getSession().createQuery(criteria);

	    return query.getResultList();
	}

//	public List<TicketVO> getByCompositeQuery(Map<String, String> map) {
//
//		if (map.size() == 0)
//			return getAll();
//
//		CriteriaBuilder builder = getSession().getCriteriaBuilder();
//		CriteriaQuery<TicketVO> criteria = builder.createQuery(TicketVO.class);
//		Root<TicketVO> root = criteria.from(TicketVO.class);
//
//		List<Predicate> predicates = new ArrayList<>();
//
//		for (Map.Entry<String, String> row : map.entrySet()) {
//			if ("ticketName".equals(row.getKey())) {
//				predicates.add(builder.like(root.get("ticketName"), "%" + row.getValue() + "%"));
//			}
//		}
//		
//	    for (Map.Entry<String, String> entry : map.entrySet()) {
//	        String key = entry.getKey();
//	        String value = entry.getValue();
//
//	        switch (key) {
//	            case "ticketName":
//	                predicates.add(builder.like(root.get("ticketName"), "%" + value + "%"));
//	                break;
//	            case "ticketTypeId":
//	                predicates.add(builder.equal(root.get("ticketType").get("ticketTypeId"), Integer.parseInt(value)));
//	                break;
//	            case "ticketId":
//	                predicates.add(builder.equal(root.get("ticketId"), Integer.parseInt(value)));
//	                break;
//	            case "areaId":
//	                predicates.add(builder.equal(root.get("city").get("cityId"), Integer.parseInt(value)));
//	                break;
//	        }
//	    }
//
//		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
//		criteria.orderBy(builder.asc(root.get("ticketId")));
//		TypedQuery<TicketVO> query = getSession().createQuery(criteria);
//
//		return query.getResultList();
//	}

}
