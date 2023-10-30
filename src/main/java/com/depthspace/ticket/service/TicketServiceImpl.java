package com.depthspace.ticket.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.depthspace.attractions.model.CityVO;
import com.depthspace.ticket.dao.TicketDAO;
import com.depthspace.ticket.dao.TicketDAOImpl;
import com.depthspace.ticket.model.TicketImagesVO;
import com.depthspace.ticket.model.TicketTypesVO;
import com.depthspace.ticket.model.TicketVO;
import com.depthspace.ticketorders.model.ticketorderdetail.TicketOrderDetailVO;
import com.depthspace.ticketorders.model.ticketorders.TicketOrdersVO;
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

	// 更新票券
	@Override
	public TicketVO updateTicket(TicketVO ticketVO) {
	    TicketVO updatedTicket = dao.update(ticketVO);
	    return updatedTicket;
	}

	
	//刪除票券
	@Override
	public TicketVO deleteTicket(Integer ticketId) {
		dao.delete(ticketId);
        return null;
	}


	//查詢票券
	@Override
	public List<TicketVO> getTicketsByCompositeQuery(Map<String, String[]> map) {
	    Map<String, String> query = new HashMap<>();
	    // Map.Entry即代表一組key-value
	    Set<Map.Entry<String, String[]>> ticketVO = map.entrySet();
	    
	    for (Map.Entry<String, String[]> row : ticketVO) {
	        String key = row.getKey();
	        // 因為請求參數裡包含了action，做個去除動作
	        if ("action".equals(key)) {
	            continue;
	        }
	        // 若是value為空即代表沒有查詢條件，做個去除動作
	        String value = row.getValue()[0];
	        if (value == null || value.isEmpty()) {
	            continue;
	        }
	        query.put(key, value);
	    }
	    
	    System.out.println(query);
	    
	    return dao.getByCompositeQuery(query);
	}

	
	@Override
	public TicketVO getTicketById(Integer ticketId) {
		return dao.getTicketById(ticketId);
	}	
	@Override
	public List<TicketVO> getTicketById2(Integer ticketId) {
		return dao.getTicketById2(ticketId);
	}		
	
	@Override	
	public List<TicketVO> getAllTickets() {	
     
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<TicketVO> criteriaQuery = criteriaBuilder.createQuery(TicketVO.class);
            Root<TicketVO> root = criteriaQuery.from(TicketVO.class);
            criteriaQuery.select(root);

            Query<TicketVO> query = session.createQuery(criteriaQuery);

            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error", e);
        }
    }        
     
	


	@Override
	public List<TicketVO> getAllTickets2(int currentPage) {
	    List<TicketVO> tickets = new ArrayList<>();

	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
	        CriteriaQuery<TicketVO> criteriaQuery = criteriaBuilder.createQuery(TicketVO.class);
	        Root<TicketVO> root = criteriaQuery.from(TicketVO.class);
	        criteriaQuery.select(root);

	        
	        // criteriaQuery.where(criteriaBuilder.equal(root.get("fieldName"), value));

	        Query<TicketVO> query = session.createQuery(criteriaQuery);

	        // 分頁
	        query.setFirstResult((currentPage - 1) * PAGE_MAX_RESULT);
	        query.setMaxResults(PAGE_MAX_RESULT);

	        tickets = query.list();
	    } catch (Exception e) {
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
	
//	//取得票券區域 沒用~~~
//	public List<TicketVO> getTicketsWithCity() {
//		return dao.getAllTicketsWithCity();
//	}

	@Override
	public List<TicketVO> getAllTicketsWithMainImages() {		
		return null;
	}
	
	@Override  //取得總票券數
    public long getTotalTickets() {
        return dao.getTotal();
    }
	
	//取得所有類型
    public List<TicketTypesVO> getAllTicketTypes() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<TicketTypesVO> criteriaQuery = criteriaBuilder.createQuery(TicketTypesVO.class);
            Root<TicketTypesVO> root = criteriaQuery.from(TicketTypesVO.class);
            criteriaQuery.select(root);

            Query<TicketTypesVO> query = session.createQuery(criteriaQuery);

            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error", e);
        }
    }

    //取得所有區域
    public List<CityVO> getAllCities() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<CityVO> criteriaQuery = criteriaBuilder.createQuery(CityVO.class);
            Root<CityVO> root = criteriaQuery.from(CityVO.class);
            criteriaQuery.select(root);

            Query<CityVO> query = session.createQuery(criteriaQuery);

            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error", e);
        }
    }
	
}
