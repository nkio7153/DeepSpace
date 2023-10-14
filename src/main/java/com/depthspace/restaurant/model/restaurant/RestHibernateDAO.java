package com.depthspace.restaurant.model.restaurant;

import java.util.List;

public class RestHibernateDAO {
	public static void main(String[] args) throws Exception {
		RestDAO dao = new RestDAOHibernateImpl();
		
		// ADD
		RestVO restVO = new RestVO();
		restVO.setRestName("餐廳9999");
		restVO.setRestTel("03-1234567");
		restVO.setRestAddress("台北");
		restVO.setRestOpen("週一~週日");
		restVO.setRestStatus(1);
		restVO.setBookingLimit(10);
		restVO.setAdminId(1);
		dao.add(restVO);
		
		
		
		
//		List<RestVO> list = dao.getAll();
//		for (RestVO rest: list) {
//			System.out.println(rest.getRestId() + ",");
//			System.out.println(rest.getRestName() + ",");
//			System.out.println(rest.getRestTel() + ",");
//			System.out.println(rest.getRestAddress() + ",");
//			System.out.println(rest.getRestOpen() + ",");
//			System.out.println(rest.getRestStatus() + ",");
//			System.out.println(rest.getBookingLimit() + ",");
//			System.out.println(rest.getAdminId());
//		}
	}
}
