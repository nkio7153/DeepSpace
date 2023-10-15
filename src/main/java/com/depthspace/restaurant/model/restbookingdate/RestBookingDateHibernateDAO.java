package com.depthspace.restaurant.model.restbookingdate;

import com.depthspace.restaurant.model.restbookingdate.RestBookingDateVO.CompositeDetail;

public class RestBookingDateHibernateDAO {
	public static void main(String[] args) {
		RestBookingDateHibernateDAOImpl dao = new RestBookingDateHibernateDAOImpl();
		
		// ADD
//		RestBookingDateVO rb = new RestBookingDateVO();
//		rb.setRestId(10);
//		rb.setBookingDate(java.sql.Date.valueOf("2023-10-15"));
//		rb.setRestOpen(1);
//		rb.setMorningNum(5);
//		rb.setNoonNum(10);
//		rb.setEveningNum(7);
//		dao.insert(rb);
		
		// UPDATE
		RestBookingDateVO rb = new RestBookingDateVO();
		rb.setCompositeKey(new CompositeDetail(20, java.sql.Date.valueOf("2023-10-20")));
		rb.setRestOpen(1);
		rb.setMorningNum(7);
		rb.setNoonNum(5);
		rb.setEveningNum(9);
		dao.update(rb);
		
	}
}
