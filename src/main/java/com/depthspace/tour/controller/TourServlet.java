package com.depthspace.tour.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.depthspace.tour.model.tour.TourVO;
import com.depthspace.tour.model.tour.TourView;
import com.depthspace.tour.service.TourService;
import com.depthspace.tour.service.TourService_Interface;

@WebServlet({ "/tr/*" })
public class TourServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
	private TourService ts;
	
	public void init() throws ServletException{
		ts = new TourService();
//		System.out.println("成功開啟");
	}
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		String pathInfo = req.getPathInfo();
//		System.out.println("pathInfo=" + pathInfo);
		switch (pathInfo) {
		case "/tourList":
			doTourList(req, resp);
			 break;
		case "/memTourList":
			domemTourList(req, resp);
			 break;
		case "/showDetail":
			showDetail(req, resp);
			break;
		}
		
	}
	
	private void showDetail(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		
	}
	//用會員編號查出該會員所有行程
	private void doTourList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer memId;
//		String tourId = req.getParameter("tourId");
//		System.out.println("tourId=" + tourId);
		try {
            memId = Integer.valueOf(req.getParameter("memId"));
//          System.out.println(memId);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
		List<TourVO> list = ts.getByMemId(memId);
//		System.out.println(list);
		req.setAttribute("list" , list);
		req.setAttribute("memId" , memId);

//		List<Integer> tourIds = new ArrayList<>(); // 创建一个用于存储tourId的列表
//		for (TourVO tour : list) {
//		    tourIds.add(tour.getTourId()); // 将每个TourVO对象的tourId添加到列表中
//		}
//		req.setAttribute("tourIds", tourIds); // 将tourIds列表设置为请求属性
		req.getRequestDispatcher("/tour/index.jsp").forward(req, resp);
	}
	
	//查詢單獨行程
	private void domemTourList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		Integer memId ;
		Integer tourId ;
		try {
            memId = Integer.valueOf(req.getParameter("memId"));
            tourId = Integer.valueOf( req.getParameter("tourId"));
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
		System.out.println("memId=" + memId + "," + "tourId=" + tourId);
		List<TourView> list = ts.getOneTourList(tourId,memId);
//		System.out.println(list);
		
		req.setAttribute("list", list);
		
		req.getRequestDispatcher("/tour/memTourList.jsp").forward(req, resp);
	}
	
	
}
