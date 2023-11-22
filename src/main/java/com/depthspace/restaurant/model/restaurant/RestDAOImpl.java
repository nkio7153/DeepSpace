package com.depthspace.restaurant.model.restaurant;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;

public class RestDAOImpl implements RestDAO {
	
	private SessionFactory factory;
	
	public RestDAOImpl(SessionFactory factory) {
		this.factory = factory;
	}
	
	private Session getSession() {
		return factory.getCurrentSession();
	}
	
	@Override
	public int add(RestVO restVO) {
		return (Integer) getSession().save(restVO);
	}

	@Override
	public int update(RestVO restVO) {
		try {
			getSession().update(restVO);
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public int delete(Integer restId) {
		RestVO rest = getSession().get(RestVO.class, restId);
		if (rest != null) {
			getSession().delete(rest);
			return 1;
		} else {
			return -1;
		}
	}

	@Override
	public RestVO findByPK(Integer restId) {
		return getSession().get(RestVO.class, restId);
	}

	@Override
	public List<RestVO> getAll() {
		return getSession().createQuery("from RestVO", RestVO.class).list();
	}

	@Override
	public List<RestVO> showRest() {
		return getSession().createQuery("from RestVO where REST_STATUS = 1", RestVO.class).list();
	}

//	@Override
//	public List<Object> getAllandAdmin() {
//		String sql = "select r.*, a.ADMIN_ACC from RESTAURANT r JOIN ADMIN a on r.admin_id = a.admin_id ";
//		NativeQuery query = getSession().createNativeQuery(sql);
//		List<Object[]> list = query.list();
//		
//		List<Object> listObject = new ArrayList<>();
//		for (Object[] row : list) {
//		    Map<String, Object> rowMap = new LinkedHashMap<>();
//		    rowMap.put("restId", row[0]);
//		    rowMap.put("restName", row[1]);
//		    rowMap.put("restTel", row[2]);
//		    rowMap.put("restAddress", row[3]);
//		    rowMap.put("restOpen", row[4]);
//		    rowMap.put("restStatus", row[5]);
//		    rowMap.put("bookingLimit", row[6]);
//		    rowMap.put("adminId", row[7]);
//		    rowMap.put("restType", row[8]);
//		    rowMap.put("adminAcc", row[9]);
//		    listObject.add(rowMap);
//		}
////		for (Object v : listObject) {
////			System.out.println(v);
////		}
//		return listObject;
//	}

	
	
}
