package com.depthspace.column.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.depthspace.admin.model.AdminVO;
import com.depthspace.column.dao.ColumnArticlesDAO;
import com.depthspace.column.dao.ColumnArticlesDAOImpl;
import com.depthspace.column.model.ColumnArticlesVO;
import com.depthspace.column.model.ColumnTypesVO;
import com.depthspace.utils.HibernateUtil;
import com.depthspace.utils.JedisUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class ColumnArticlesServiceImpl implements ColumnArticlesService {

	public static final int PAGE_MAX_RESULT = 8;
	private ColumnArticlesDAO dao;

	public ColumnArticlesServiceImpl() {
		dao = new ColumnArticlesDAOImpl(HibernateUtil.getSessionFactory());
	}

	@Override
	public ColumnArticlesVO addArt(ColumnArticlesVO columnArticlesVO) {
		dao.insert(columnArticlesVO);
		return columnArticlesVO;
	}

	@Override
	public ColumnArticlesVO updateColumnArticles(ColumnArticlesVO columnArticlesVO) {
		dao.update(columnArticlesVO);
		return columnArticlesVO;
	}

	@Override
	public void deleteEmp(Integer artiId) {
		dao.delete(artiId);
	}

	@Override
	public int getPageTotal() {
		long total = dao.getTotal();

		int pageQty = (int) (total % PAGE_MAX_RESULT == 0 ? (total / PAGE_MAX_RESULT) : (total / PAGE_MAX_RESULT + 1));
		return pageQty;
	}
	
	@Override
	public List<ColumnArticlesVO> getAllArti() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<ColumnArticlesVO> criteriaQuery = criteriaBuilder.createQuery(ColumnArticlesVO.class);
			Root<ColumnArticlesVO> root = criteriaQuery.from(ColumnArticlesVO.class);
			criteriaQuery.select(root);

			Query<ColumnArticlesVO> query = session.createQuery(criteriaQuery);

			return query.getResultList();
		} catch (Exception e) {
			throw new RuntimeException("Error", e);
		}
	}
	
	@Override
	public List<ColumnArticlesVO> getAllArti2(int currentPage) {
		List<ColumnArticlesVO> columnArticles = new ArrayList<>();

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<ColumnArticlesVO> criteriaQuery = criteriaBuilder.createQuery(ColumnArticlesVO.class);
			Root<ColumnArticlesVO> root = criteriaQuery.from(ColumnArticlesVO.class);
			criteriaQuery.select(root);

			Query<ColumnArticlesVO> query = session.createQuery(criteriaQuery);

			// 分頁
			query.setFirstResult((currentPage - 1) * PAGE_MAX_RESULT);
			query.setMaxResults(PAGE_MAX_RESULT);

			columnArticles = query.list();
		} catch (Exception e) {
			throw new RuntimeException("Error", e);
		}

		return columnArticles;
	}
	//根據文章ID取得專欄VO
	@Override
	public ColumnArticlesVO getArtiByArtiId(Integer artiId) {
		return dao.getById(artiId);
	}
	//取得所有專欄類型
	@Override
	public List<ColumnTypesVO> getAllColumnTypes() {
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<ColumnTypesVO> criteriaQuery = criteriaBuilder.createQuery(ColumnTypesVO.class);
			Root<ColumnTypesVO> root = criteriaQuery.from(ColumnTypesVO.class);
			criteriaQuery.select(root);
			
			Query<ColumnTypesVO> query = session.createQuery(criteriaQuery);
			
			return query.getResultList();
		} catch (Exception e) {
			throw new RuntimeException("Error",e);
		}
	}
	//取得所有管理者的管理者VO
	@Override
	public List<AdminVO> getAllAdmins() {
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<AdminVO> criteriaQuery = criteriaBuilder.createQuery(AdminVO.class);
			Root<AdminVO> root = criteriaQuery.from(AdminVO.class);
			criteriaQuery.select(root);
			
			Query<AdminVO> query = session.createQuery(criteriaQuery);
			
			return query.getResultList();
		} catch (Exception e) {
			throw new RuntimeException("Error",e);
		}
	}

	@Override
	public long getTotal() {
		return dao.getTotal();
	}

	@Override
	public List<ColumnArticlesVO> getColumnArticlesByCompositeQuery(Map<String, String[]> queryMap) {
	    Map<String, List<String>> criteriaMap = new HashMap<>();
	    // 遍歷參數，將action非查詢條件的key排除，非空值加入查詢條件(可多個)
	    for (Map.Entry<String, String[]> entry : queryMap.entrySet()) {
	        String key = entry.getKey();
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
	public List<ColumnArticlesVO> getAllColumnTypes(Integer colTypeId) {
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<ColumnArticlesVO> criteriaQuery = criteriaBuilder.createQuery(ColumnArticlesVO.class);
			Root<ColumnArticlesVO> root = criteriaQuery.from(ColumnArticlesVO.class);
			criteriaQuery.where(criteriaBuilder.equal(root.get("colType").get("colTypeId"), colTypeId));
			criteriaQuery.select(root);
			
			Query<ColumnArticlesVO> query = session.createQuery(criteriaQuery);
			
			return query.getResultList();
		} catch (Exception e) {
			throw new RuntimeException("Error",e);
		}
	}

	@Override
	public List<ColumnArticlesVO> getAllSortById(String sort) {
		List<ColumnArticlesVO> allArtis = this.getAllArti();
		if("desc".equalsIgnoreCase(sort)) {
			allArtis.sort((a1, a2) -> a2.getArtiId().compareTo(a1.getArtiId()));			
		} else {
			allArtis.sort(Comparator.comparing(ColumnArticlesVO::getArtiId)); //StreamAPI排序方法 根據屬性，若為Integer從小至大(asc)
		}
		return allArtis;
	}

}