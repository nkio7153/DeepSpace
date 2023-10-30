package com.depthspace.restaurant.model.restbookingdate.dao;

import java.util.List;

import com.depthspace.restaurant.model.restbookingdate.RestBookingDateVO;

public class RestBookingDateHibernateDAO {
	public static void main(String[] args) {
		RestBookingDateHibernateDAOImpl dao = new RestBookingDateHibernateDAOImpl();
		
		// ADD
//		RestBookingDateVO rb = new RestBookingDateVO();
//		rb.setRestId(11);
//		rb.setBookingDate(java.sql.Date.valueOf("2023-10-15"));
//		rb.setRestOpen(1);
//		rb.setMorningNum(5);
//		rb.setNoonNum(10);
//		rb.setEveningNum(7);
//		dao.insert(rb);
		
		// UPDATE
//		RestBookingDateVO rb = new RestBookingDateVO();
//		rb.setCompositeKey(new CompositeDetail(20, java.sql.Date.valueOf("2023-10-15")));
//		rb.setRestOpen(0);
//		rb.setMorningNum(7);
//		rb.setNoonNum(5);
//		rb.setEveningNum(9);
//		dao.update(rb);
		
		// DELETE
//		RestBookingDateVO rb = new RestBookingDateVO();
//		rb.setCompositeKey(new CompositeDetail(20, java.sql.Date.valueOf("2023-10-15")));
//		dao.delete(rb);
		
		// findByPrimaryKey
//		RestBookingDateVO rb = new RestBookingDateVO();
//		rb.setCompositeKey(new CompositeDetail(11, java.sql.Date.valueOf("2023-10-15")));
//		rb = dao.findByPrimaryKey(rb);
//		System.out.println(rb.getRestId() + "，");
//		System.out.println(rb.getBookingDate() + "，");
//		System.out.println(rb.getRestOpen() + "，");
//		System.out.println(rb.getMorningNum() + "，");
//		System.out.println(rb.getNoonNum() + "，");
//		System.out.println(rb.getEveningNum());
//		
		
		// GATALL
		List<RestBookingDateVO> list = dao.getAll();
		for (RestBookingDateVO rb : list) {
			System.out.println(rb.getRestId() + "，");
			System.out.println(rb.getBookingDate() + "，");
			System.out.println(rb.getRestOpen() + "，");
			System.out.println(rb.getMorningNum() + "，");
			System.out.println(rb.getNoonNum() + "，");
			System.out.println(rb.getEveningNum());
		}
	}
}
