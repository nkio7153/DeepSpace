package com.depthspace.restaurant.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.depthspace.restaurant.model.membooking.MemBookingVO;
import com.depthspace.restaurant.model.restaurant.RestVO;
import com.depthspace.restaurant.model.restcollection.RestCollectionVO;
import com.depthspace.restaurant.service.MemBookingService;
import com.depthspace.restaurant.service.MemBookingServiceImpl;
import com.depthspace.restaurant.service.RestBookingDateService;
import com.depthspace.restaurant.service.RestBookingDateServiceImpl;
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
	private MemBookingService memBookingService;
	private RestBookingDateService restBookingDateService;
	
	@Override
	public void init() throws ServletException {
		restService = new RestServiecImpl();
		restcollectionService = new RestcollectionServiceImpl();
		memBookingService = new MemBookingServiceImpl();
		restBookingDateService = new RestBookingDateServiceImpl();
		gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		
		
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json; charset=UTF-8");
		String pathInfo = req.getPathInfo();
		switch (pathInfo) {
			case "/getRestAll":
				getRestAll(req, resp);
				break;
			case "/getRestCollectionAll":
				getRestCollectionAll(req, resp);
				break;
			case "/getMemCollection":
				getMemCollection(req, resp);
				break;
			case "/getMemBooking":
				getMemBooking(req, resp);
				break;
			case "/RestCollection":
				doRestCollection(req, resp);
				break;
				
		}
		
	}
	
	private void getRestCollectionAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<RestCollectionVO> rcList = restcollectionService.getAll();
		// RestCollectionVO FK RestVO 會導致遞迴請求VO 加上@Expose gson用exclude忽略Expost避免遞迴
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
			}
		}
	
	private void getMemCollection(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<RestCollectionVO> list = restcollectionService.findByMemId(Integer.valueOf(req.getParameter("memId")));
		List<Map<String, String>> listOfMaps = new ArrayList<>();
		for (RestCollectionVO vo : list) {
			// 將查詢結果加上餐廳名稱重新裝成Map後加進List
			Map<String, String> hashMap = new HashMap<>();
			hashMap.put("memId", String.valueOf(vo.getMemId()));
			hashMap.put("restId", String.valueOf(vo.getRestId()));
			hashMap.put("restName", vo.getRestVO().getRestName());
			listOfMaps.add(hashMap);
		}
		PrintWriter out = resp.getWriter();
		out.print(gson.toJson(listOfMaps));
	}
		
	private void getMemBooking(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<MemBookingVO> list = memBookingService.getAllMembooking();
		PrintWriter out = resp.getWriter();
		out.print(gson.toJson(list));
	}
	
	private void getRestAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<RestVO> list = restService.getAllRest();
		PrintWriter out = resp.getWriter();
		out.print(gson.toJson(list));
	}
	
	
}
