package com.depthspace.restaurant.model.restaurant;

import java.util.List;

public class RestHibernateDAO {
	public static void main(String[] args) throws Exception {
		RestDAO dao = new RestDAOHibernateImpl();
		
		
		
		List<RestVO> list = dao.getAll();
		for (RestVO rest: list) {
			System.out.println(rest.getRestId() + ",");
			System.out.println(rest.getRestName() + ",");
			System.out.println(rest.getRestTel() + ",");
			System.out.println(rest.getRestAddress() + ",");
			System.out.println(rest.getRestOpen() + ",");
			System.out.println(rest.getRestStatus() + ",");
			System.out.println(rest.getBookingLimit() + ",");
			System.out.println(rest.getAdminId());
		}
	}
}
