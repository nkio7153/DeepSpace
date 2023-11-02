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
		case "/addTour":
			addTour(req, resp);
			break;
		}
		
	}
	//新增新行程
	private void addTour(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		System.out.println("success");
		String attraction1 = req.getParameter("attraction1");
		String[] attractions = req.getParameterValues("attraction2[]");
		if (attractions != null) {
		    for (String attraction : attractions) {
//		        System.out.println("attraction" + attraction);
		    }
		}
//		  System.out.println("attraction1" + attraction1);
		String memId = req.getParameter("memId");
		System.out.println("memId=" + memId );
		
		req.getRequestDispatcher("/tour/newTour.jsp").forward(req, resp);
		
	}
	private void showDetail(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		
	}
	//用會員編號查出該會員所有行程
	private void doTourList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer memId;
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
//		System.out.println("memId=" + memId + "," + "tourId=" + tourId);
		List<TourView> list = ts.getOneTourList(tourId,memId);
//		System.out.println(list);
		
		req.setAttribute("list", list);
		
		req.getRequestDispatcher("/tour/memTourList.jsp").forward(req, resp);
	}
	
	
}
