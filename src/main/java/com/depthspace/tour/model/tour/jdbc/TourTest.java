package com.depthspace.tour.model.tour.jdbc;

import java.util.List;

import com.depthspace.tour.model.tour.TourVO;

public class TourTest {
	public static void main(String[] args) {

		TourJDBCDAO dao = new TourJDBCDAO();

//	select all
		List<TourVO> list = dao.getAll();
		for (TourVO tour : list) {
			System.out.println(tour.getTourId());
			System.out.println(tour.getMemId());
			System.out.println(tour.getTourName());
			System.out.println(tour.getTourTypeId());
			System.out.println(tour.getAllDays());
			System.out.println(tour.getTourDescription());
			System.out.println(tour.getTourImg());
			System.out.println("已取得全部資料");
		}
	}
}