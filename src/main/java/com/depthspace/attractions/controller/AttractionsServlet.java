package com.depthspace.attractions.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.depthspace.attractions.model.AttractionsVO;
import com.depthspace.attractions.service.AttractionsService;

@WebServlet({ "/attractions/*" })
public class AttractionsServlet  extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AttractionsService as;
	
	public void init() throws ServletException {
		as = new AttractionsService();
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
			doTList(req, resp);
			break;
			
		}

	}
	private void doTList(HttpServletRequest req, HttpServletResponse resp) {
		List<AttractionsVO> list = as.getAll();
		System.out.println("景點list=" + list) ;
//		req.setAttribute("list", list);
		
	}
	
}
