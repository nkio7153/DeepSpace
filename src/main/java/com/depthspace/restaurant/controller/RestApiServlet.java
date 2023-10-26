package com.depthspace.restaurant.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.depthspace.restaurant.model.restcollection.RestCollectionVO;
import com.depthspace.restaurant.service.RestcollectionService;
import com.depthspace.restaurant.service.RestcollectionServiceImpl;

@WebServlet("/RestApi/*")
public class RestApiServlet extends HttpServlet {
	
	private RestcollectionService restcollectionService;
	
	@Override
	public void init() throws ServletException {
		restcollectionService = new RestcollectionServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		String pathInfo = req.getPathInfo();
		switch (pathInfo) {
			case "/getRestCollectionAll":
				getRestCollectionAll(req, resp);
				break;
		}
		
	}
	
	private void getRestCollectionAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<RestCollectionVO> rcList = restcollectionService.getAll();
		req.setAttribute("rcList", rcList);
		req.getRequestDispatcher("Rest/ShowList.jsp").forward(req, resp);
		
	}
	
	
	
}
