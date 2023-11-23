package com.depthspace.attractions.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.depthspace.admin.model.AdminVO;
import com.depthspace.attractions.model.AttractionsVO;
import com.depthspace.attractions.model.attractions.hibernate.AttractionsDAOImpl;
import com.depthspace.attractions.model.attractions.hibernate.AttractionsDAO_Interface;
import com.depthspace.column.model.ColumnArticlesVO;
import com.depthspace.column.model.ColumnTypesVO;
import com.depthspace.utils.HibernateUtil;

public class AttractionsService {
	
	public static final int PAGE_MAX_RESULT = 8;
	private AttractionsDAO_Interface dao;
	
	public AttractionsService() {
		dao = new AttractionsDAOImpl(HibernateUtil.getSessionFactory());
	}
	
	public AttractionsVO insert(AttractionsVO entity) {
		dao.insert(entity);
		AttractionsVO attrVO = null;
		attrVO = dao.getLast(entity.getAttractionsId());
		System.out.println("attrVO=" + attrVO);
		List<AttractionsVO> list = new ArrayList<>();
		AttractionsVO attr =  new AttractionsVO();
		attr.setAttractionsId(attrVO.getAttractionsId());
		list.add(attr);
		
		return attrVO;
	}
	
	public AttractionsVO update(AttractionsVO entity) {
		dao.update(entity);
		
		return entity;
	}
	
	//取得所有景點
	public List<AttractionsVO> getAllAttractions(Integer attractionsId){
		List<AttractionsVO> list = dao.getAllAttractions(attractionsId);
		return list;
		
	}
	public List<AttractionsVO> getAll() {
		List<AttractionsVO> list = dao.getAll();
		return list;
	}
	
//	找分頁
	public List<AttractionsVO> getAllPage(int currentPage) {
		List<AttractionsVO> list = new ArrayList<>();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<AttractionsVO> criteriaQuery = criteriaBuilder.createQuery(AttractionsVO.class);
			Root<AttractionsVO> root = criteriaQuery.from(AttractionsVO.class);
			criteriaQuery.select(root);

			Query<AttractionsVO> query = session.createQuery(criteriaQuery);

			// 分頁
			query.setFirstResult((currentPage - 1) * PAGE_MAX_RESULT);
			query.setMaxResults(PAGE_MAX_RESULT);

			list = query.list();
		} catch (Exception e) {
			throw new RuntimeException("Error", e);
		}
		return list;
	}
	
	public int getPageTotal() {
		long total = dao.getTotal();

		int pageQty = (int) (total % PAGE_MAX_RESULT == 0 ? (total / PAGE_MAX_RESULT) : (total / PAGE_MAX_RESULT + 1));
		return pageQty;
	}
	
	//預設為台北市，尋找台北市各個景點
	public List<AttractionsVO> findOneAttractions() {
		List<AttractionsVO> list = dao.findOneAttractions();
		return list;
		
	}
	//尋找其他景點
	public List<AttractionsVO> findOtherAttractions(String cityName) {
		
		List<AttractionsVO> list = dao.findOtherAttractions(cityName);
		return list;
	}
	//依據景點id取的該景點物件
	public AttractionsVO getAttractionsById(Integer attractionsId) {
		return dao.getAttractionsById(attractionsId);
	}
	//依據景點名稱收巡該景點集合
	public List<AttractionsVO> getAttractionsName(String attractionsName) {
		return dao.getAttractionsName(attractionsName);
	}
	
	public List<AttractionsVO> getAllAttrType() {
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<AttractionsVO> criteriaQuery = criteriaBuilder.createQuery(AttractionsVO.class);
			Root<AttractionsVO> root = criteriaQuery.from(AttractionsVO.class);
			criteriaQuery.select(root);
			
			Query<AttractionsVO> query = session.createQuery(criteriaQuery);
			
			return query.getResultList();
		} catch (Exception e) {
			throw new RuntimeException("Error",e);
		}
	}
	
	public List<AttractionsVO> getAllAttrType(Integer attractionsTypeId) {
		return dao.getAllAttrType(attractionsTypeId);
		
//		try(Session session = HibernateUtil.getSessionFactory().openSession()){
//			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
//			CriteriaQuery<AttractionsVO> criteriaQuery = criteriaBuilder.createQuery(AttractionsVO.class);
//			Root<AttractionsVO> root = criteriaQuery.from(AttractionsVO.class);
//			criteriaQuery.where(criteriaBuilder.equal(root.get("attractionsTypeId").get("attrTypeId"), attrTypeId));
//			criteriaQuery.select(root);
//			
//			Query<AttractionsVO> query = session.createQuery(criteriaQuery);
//			
//			return query.getResultList();
//		} catch (Exception e) {
//			throw new RuntimeException("Error",e);
//		}
		
	}
//	public AttractionsVO getById(Integer attractionsId) {
//		return dao.getById(attractionsId);
//		
//	}
	
	public List<AttractionsVO> getAttractionsByCompositeQuery(Map<String, String[]> queryMap){
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
	
	
}
