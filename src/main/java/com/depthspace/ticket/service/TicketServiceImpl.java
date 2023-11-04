package com.depthspace.ticket.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.Arrays;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import com.depthspace.attractions.model.CityVO;
import com.depthspace.ticket.dao.TicketDAO;
import com.depthspace.ticket.dao.TicketDAOImpl;
import com.depthspace.ticket.model.TicketTypesVO;
import com.depthspace.ticket.model.TicketVO;
import com.depthspace.utils.HibernateUtil;
import com.depthspace.utils.Constants;

public class TicketServiceImpl implements TicketService {

	private TicketDAO dao;

	public TicketServiceImpl() {
		dao = new TicketDAOImpl(HibernateUtil.getSessionFactory());
	}

	// 新增票券
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

	// 刪除票券
	@Override
	public TicketVO deleteTicket(Integer ticketId) {
		dao.delete(ticketId);
		return null;
	}

	@Override
	public List<TicketVO> getTicketsByCompositeQuery(Map<String, String[]> queryMap) {
	    Map<String, List<String>> criteriaMap = new HashMap<>();
	    // 遍歷參數，將action非查詢條件的key排除，非空值加入查詢條件(可多個)
	    for (Map.Entry<String, String[]> entry : queryMap.entrySet()) {
	        String key = entry.getKey();
	        // 排除非查询条件的 key，例如 "action"
	        if ("action".equals(key)) {
	            continue;
	        }
	        String[] values = entry.getValue();
	        
	        if (values == null || values.length == 0) {
	            continue;
	        }
	        
	        criteriaMap.put(key, Arrays.asList(values));
	    }
	    
	    // 將上述查到的條件交由dao的方法查詢
	    return dao.getByCompositeQuery(criteriaMap);
	}

	
	@Override
	public TicketVO getTicketById(Integer ticketId) {
		return dao.getTicketById(ticketId);
	}
	//取得所有票券VO 根據票券ID
	@Override
	public List<TicketVO> getTicketById2(Integer ticketId) {
		return dao.getTicketById2(ticketId);
	}
	//取得所有票券VO 
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
	//取得所有票券VO 根據分頁
	@Override
	public List<TicketVO> getAllTickets2(int currentPage) {
		List<TicketVO> tickets = new ArrayList<>();

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<TicketVO> criteriaQuery = criteriaBuilder.createQuery(TicketVO.class);
			Root<TicketVO> root = criteriaQuery.from(TicketVO.class);
			criteriaQuery.select(root);

			Query<TicketVO> query = session.createQuery(criteriaQuery);

			// 分頁
			query.setFirstResult((currentPage - 1) * Constants.PAGE_MAX_RESULT);
			query.setMaxResults(Constants.PAGE_MAX_RESULT);

			tickets = query.list();
		} catch (Exception e) {
			throw new RuntimeException("Error", e);
		}

		return tickets;
	}

	// 計算數量每頁*筆的話總共有幾頁
	@Override
	public int getPageTotal() {
		long total = dao.getTotal();

		int pageQty = (int) (total % Constants.PAGE_MAX_RESULT == 0 ? (total / Constants.PAGE_MAX_RESULT) : (total / Constants.PAGE_MAX_RESULT + 1));
		return pageQty;
	}


	@Override // 取得總票券數
	public long getTotalTickets() {
		return dao.getTotal();
	}

	// 取得所有類型
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

	public List<TicketVO> getAllTicketTypeIds(Integer ticketTypeId) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<TicketVO> criteriaQuery = criteriaBuilder.createQuery(TicketVO.class);
			Root<TicketVO> root = criteriaQuery.from(TicketVO.class);

			criteriaQuery.where(criteriaBuilder.equal(root.get("ticketType").get("ticketTypeId"), ticketTypeId));
			criteriaQuery.select(root);

			Query<TicketVO> query = session.createQuery(criteriaQuery);

			return query.getResultList();
		} catch (Exception e) {
			throw new RuntimeException("Error", e);
		}
	}

	// 取得所有區域
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

	@Override
	public List<TicketVO> getAllTicketAreaId(Integer areaId) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<TicketVO> criteriaQuery = criteriaBuilder.createQuery(TicketVO.class);
			Root<TicketVO> root = criteriaQuery.from(TicketVO.class);
			//criteriaQuery查詢對象、criteriaBuilder查詢=>從root查詢ticketVO實體中的city屬性→他表的名稱cityId 要equal=,areaId值
			criteriaQuery.where(criteriaBuilder.equal(root.get("city").get("cityId"), areaId));
			criteriaQuery.select(root);

			Query<TicketVO> query = session.createQuery(criteriaQuery);

			return query.getResultList();
		} catch (Exception e) {
			throw new RuntimeException("Error", e);
		}
	}
	
	@Override
	public List<TicketVO> getAllTicketsWithMainImages() {
		// TODO Auto-generated method stub
		return null;
	}
}
