package com.depthspace.restaurant.model.restaurant.dao;

import java.util.List;

import com.depthspace.restaurant.model.restaurant.RestDAO;
import com.depthspace.restaurant.model.restaurant.RestDAOImpl;
import com.depthspace.restaurant.model.restaurant.RestVO;
import com.depthspace.utils.HibernateUtil;

public class RestHibernateDAO {
	public static void main(String[] args) throws Exception {
		RestDAO dao = new RestDAOHibernateImpl();
		
//		 ADD
//		RestVO rest1 = new RestVO();
//		rest1.setRestName("餐廳000");
//		rest1.setRestTel("03-1234567");
//		rest1.setRestAddress("中壢");
//		rest1.setRestOpen("周一~週二");
//		rest1.setRestStatus(0);
//		rest1.setBookingLimit(11);
//		rest1.setAdminId(1);
//		dao.add(rest1);
		
		// UPDATE
//		RestVO rest2 = new RestVO();
//		rest2.setRestName("餐廳777");
//		rest2.setRestTel("03-1234888");
//		rest2.setRestAddress("台北");
//		rest2.setRestOpen("周一~週六");
//		rest2.setRestStatus(1);
//		rest2.setBookingLimit(111);
//		rest2.setAdminId(2);
//		rest2.setRestId(28);
//		dao.update(rest2);
		
		// DELETE
//		dao.delete(24);
		
		// SELETE BY PK
//		RestVO rest3 = dao.findByPK(1);
//		System.out.println(rest3.getRestId() + ",");
//		System.out.println(rest3.getRestName() + ",");
//		System.out.println(rest3.getRestTel() + ",");
//		System.out.println(rest3.getRestAddress() + ",");
//		System.out.println(rest3.getRestOpen() + ",");
//		System.out.println(rest3.getRestStatus() + ",");
//		System.out.println(rest3.getBookingLimit() + ",");
//		System.out.println(rest3.getAdminId());
		
		
		List<RestVO> list = dao.getAll();
		for (RestVO rest: list) {
			System.out.println(rest.getRestId() + ",");
			System.out.println(rest.getRestName() + ",");
			System.out.println(rest.getRestTel() + ",");
			System.out.println(rest.getRestAddress() + ",");
			System.out.println(rest.getRestType() + ",");
			System.out.println(rest.getRestOpen() + ",");
			System.out.println(rest.getRestStatus() + ",");
			System.out.println(rest.getBookingLimit() + ",");
			System.out.println(rest.getAdminId());
		}
	}
}
