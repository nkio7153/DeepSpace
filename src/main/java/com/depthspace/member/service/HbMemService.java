package com.depthspace.member.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.depthspace.column.model.ColumnArticlesVO;
import com.depthspace.member.model.MemVO;
import com.depthspace.member.model.hibernate.HibernateMemDAOImpl;
import com.depthspace.member.model.hibernate.HibernateMemDAO_Interface;
import com.depthspace.utils.HibernateUtil;

public class HbMemService {
	public static final int PAGE_MAX_RESULT = 8;

	private HibernateMemDAO_Interface dao;
	
	public HbMemService() {
		dao = new HibernateMemDAOImpl(HibernateUtil.getSessionFactory());
	}
	
	public void insert(MemVO memVO) {
		dao.insert(memVO);
	}

	public void update(MemVO memVO) {
		dao.update(memVO);
	}
	
	public void updateStatus(Integer memId , byte status) {
		dao.updateStatus(memId,status);
		
	}

	public void delete(Integer memId) {
		dao.delete(memId);
	}
	
	public MemVO findByMemAcc(String memAcc) {
		return dao.findByMemAcc(memAcc);
	}
	
	public MemVO getById(String memAcc) {
		return dao.getById(memAcc);
	}
	
	public MemVO findOneMem(String memAcc) {
		return dao.findOneMem(memAcc);
	}
	
	public List<MemVO> getAll() {
		return dao.getAll();
	}
	
	public MemVO getOneMem(Integer memId) {
		return dao.getOneMem(memId);
	}
	
	public List<MemVO> searchMembers(String memName) {
		return dao.searchMembers(memName);
	}
	
	public List<MemVO> getMemByCompositeQuery(Map<String, String[]> queryMap) {
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
