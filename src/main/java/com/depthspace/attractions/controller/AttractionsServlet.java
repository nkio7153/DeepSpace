package com.depthspace.attractions.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.depthspace.attractions.model.AttractionsVO;
import com.depthspace.attractions.model.CityVO;
import com.depthspace.attractions.service.AttractionsService;
import com.depthspace.attractions.service.CityService;

@WebServlet({ "/attr/*" })
public class AttractionsServlet  extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AttractionsService attrs;
	private CityService cs;
	
	public void init() throws ServletException {
		attrs = new AttractionsService();
		cs = new CityService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		String pathInfo = req.getPathInfo();
//		System.out.println("pathInfo=" + pathInfo);
		switch (pathInfo) {
//		case "/":
//			doTList(req, resp);
//			break;
		case "/list":
			doList(req, resp);
			break;
		case "/oneList":
			dooneList(req, resp);
			break;
			
		}

	}
	private void dooneList(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("跳轉成功");
		
	}

	private void doList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<AttractionsVO> list = attrs.getAll();
//		System.out.println("景點list=" + list) ;
		
		List<CityVO> city = cs.getAll();
//		System.out.println("景點city=" + city) ;
//		for(AttractionsVO a : list) {
//			System.out.println(a.getAttractionsName());
//		}
		
		req.setAttribute("list", list);
		req.setAttribute("city", city);
		
		req.getRequestDispatcher("/attractions/list.jsp").forward(req, resp);
	}
	
}
