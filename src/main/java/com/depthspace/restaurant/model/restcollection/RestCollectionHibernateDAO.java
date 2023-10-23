package com.depthspace.restaurant.model.restcollection;

import java.util.List;

public class RestCollectionHibernateDAO {
	
	public static void main(String[] args) throws Exception {
		RestCollectionDAO_interface dao = new  RestCollectionHibernateDAOImpl();
		
		// ADD
//		RestCollectionVO rc = new RestCollectionVO();
//		rc.setCompositeKey(new CompositeDetail(20, 5));
//		dao.insert(rc);
		
		// DELETE
//		RestCollectionVO rc = new RestCollectionVO();
//		rc.setCompositeKey(new CompositeDetail(20, 5));
//		dao.delete(rc);
		
		// GETBYMEM
		List<RestCollectionVO> list = dao.CountfindByMemId(5);
		for (RestCollectionVO rc : list) {
			System.out.println(rc.getRestId());
			System.out.println(rc.getRestVO().getRestName());
		}
		
		// GETALL
//		List<RestCollectionVO> list = dao.getAll();
//		for (RestCollectionVO rc : list) {
//			System.out.println(rc.getRestId() + ",");
//			System.out.println(rc.getMemId());
//			System.out.println(rc.getCompositeKey());
//		}
		
		
	}
	
	
}
