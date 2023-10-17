package com.depthspace.restaurant.model.membooking;

import java.util.List;

public class MemBookingHibernateDAO {
	public static void main(String[] args) throws Exception {
		MemBookingDAO dao = new MemBookingDAOHibernatelmpl();
		
//		MemBookingVO memBk1 = new MemBookingVO();
//		memBk1.setRestId(1);
//		memBk1.setMemId(1);
//		memBk1.setCheckStatus(1);
//		memBk1.setBookingTime(1);
//		memBk1.setBookingNumber(5);
//		memBk1.setBookingDate(java.sql.Date.valueOf("2023-10-10"));
//		dao.add(memBk1);
		
//		MemBookingVO memBk2 = new MemBookingVO();
//		memBk2.setRestId(2);
//		memBk2.setMemId(2);
//		memBk2.setCheckStatus(0);
//		memBk2.setBookingTime(0);
//		memBk2.setBookingNumber(50);
//		memBk2.setBookingDate(java.sql.Date.valueOf("2023-10-11"));
//		memBk2.setBookingId(22);
//		dao.update(memBk2);
		
//		dao.delete(22);
		
		
//		MemBookingVO memBk3 = dao.findByPK(1);
//		System.out.println(memBk3.getBookingId() + "，");
//		System.out.println(memBk3.getRestId() + "，");
//		System.out.println(memBk3.getMemId() + "，");
//		System.out.println(memBk3.getCheckStatus() + "，");
//		System.out.println(memBk3.getBookingTime() + "，");
//		System.out.println(memBk3.getBookingNumber() + "，");
//		System.out.println(memBk3.getBookingDate());
		
		List<MemBookingVO> list = dao.getAll();
		for (MemBookingVO memBk : list) {
			System.out.println(memBk.getBookingId() + "，");
			System.out.println(memBk.getRestId() + "，");
			System.out.println(memBk.getMemId() + "，");
			System.out.println(memBk.getCheckStatus() + "，");
			System.out.println(memBk.getBookingTime() + "，");
			System.out.println(memBk.getBookingNumber() + "，");
			System.out.println(memBk.getBookingDate());
		}
		
		
	}
		
}
