package com.depthspace.restaurant.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.depthspace.restaurant.model.restcollection.RestCollectionVO;
import com.depthspace.restaurant.service.RestcollectionService;
import com.depthspace.restaurant.service.RestcollectionServiceImpl;
import com.google.gson.Gson;

@WebServlet("/RestApi/*")
public class RestApiServlet extends HttpServlet {
	
	private Gson gson;
	private RestcollectionService restcollectionService;
	
	@Override
	public void init() throws ServletException {
		restcollectionService = new RestcollectionServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		String pathInfo = req.getPathInfo();
		switch (pathInfo) {
			case "/getRestCollectionAll":
				getRestCollectionAll(req, resp);
				break;
			case "/RestCollection":
				doRestCollection(req, resp);
				break;
				
		}
		
	}
	
	private void getRestCollectionAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<RestCollectionVO> rcList = restcollectionService.getAll();
//		JSONArray arr = JSONArray.parseArray(JSON.toJSONString(rcList));		
//		resp.setContentType("application/json; charset=UTF-8");
//		PrintWriter out = resp.getWriter();
//		out.print(arr.toString());
		
	}
	
	private void doRestCollection(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		String action = req.getParameter("action");
		if (action.equals("add")) {
			try {
				RestCollectionVO vo = new RestCollectionVO();
				vo.setMemId(Integer.valueOf(req.getParameter("memId")));
				vo.setRestId(Integer.valueOf(req.getParameter("restId")));
				restcollectionService.add(vo);
				out.print("SUCCESS");
			} catch (Exception e) {
				out.print("ERROR");
				e.printStackTrace();
			}
		} else if (action.equals("delete")) {
			try {
				RestCollectionVO vo = new RestCollectionVO();
				vo.setMemId(Integer.valueOf(req.getParameter("memId")));
				vo.setRestId(Integer.valueOf(req.getParameter("restId")));
				restcollectionService.delete(vo);
				out.print("SUCCESS");
			} catch (Exception e) {
				out.print("ERROR");
				e.printStackTrace();
			}
		}
	}
	
	
	
	
	
	
	
}
