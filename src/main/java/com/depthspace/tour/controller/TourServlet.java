package com.depthspace.tour.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.depthspace.tour.model.tour.TourVO;
import com.depthspace.tour.service.TourService;
import com.depthspace.tour.service.TourService_Interface;

@WebServlet({ "/tr/*" })
public class TourServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
	private TourService_Interface ts;
	
	public void init() throws ServletException{
		ts = new TourService();
		System.out.println("成功開啟");
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
		}
		
	}
	//查出該會員所有行程
	private void doTourList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer memId;
//		ts = new TourService();
		try {
            memId = Integer.valueOf(req.getParameter("memId"));
//            System.out.println(memId);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
		List<TourVO> list = ts.getByMemId(memId);
		req.setAttribute("list" , list);
		req.getRequestDispatcher("/tour/index.jsp").forward(req, resp);
	}
}
