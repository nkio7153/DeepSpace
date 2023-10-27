package com.depthspace.restaurant.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.depthspace.restaurant.model.restaurant.RestVO;
import com.depthspace.restaurant.model.restcollection.RestCollectionVO;
import com.depthspace.restaurant.service.RestService;
import com.depthspace.restaurant.service.RestServiecImpl;
import com.depthspace.restaurant.service.RestcollectionService;
import com.depthspace.restaurant.service.RestcollectionServiceImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet("/RestApi/*")
public class RestApiServlet extends HttpServlet {
	
	private Gson gson;
	private RestcollectionService restcollectionService;
	private RestService restService;
	
	@Override
	public void init() throws ServletException {
		restcollectionService = new RestcollectionServiceImpl();
		restService = new RestServiecImpl();
		gson = new Gson();
		
		
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json; charset=UTF-8");
		
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
		// RestCollectionVO FK RestVO 會導致遞迴請求VO 加上@Expose gson用exclude忽略Expost避免遞迴
		Gson gson = new GsonBuilder()
				.excludeFieldsWithoutExposeAnnotation()
				.create();
		String json = gson.toJson(rcList);
		PrintWriter out = resp.getWriter();
		out.print(json);
		
	}
	
	private void doRestCollection(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		String action = "";
		action = req.getParameter("action");
		switch (action) {
			case("add"):
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
				break;
			case("delete"):
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
				break;
			case("mem"):
				Gson gson = new GsonBuilder()
					.excludeFieldsWithoutExposeAnnotation()
					.create();
				List<RestCollectionVO> list = restcollectionService.findByMemId(Integer.valueOf(req.getParameter("memId")));
				for (RestCollectionVO vo : list) {
					String name = vo.getRestVO().getRestName();
//					vo.setRestVO(new RestVO().getRestName());
				}
				out.print(gson.toJson(list));
				
				
		}
	
	
	
	
	
	
	}
}
