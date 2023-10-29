package com.depthspace.restaurant.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
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
import com.depthspace.restaurant.model.restbookingdate.RestBookingDateVO;
import com.depthspace.restaurant.model.restbookingdate.RestBookingDateVO.CompositeDetail;
import com.depthspace.restaurant.model.restbookingdate.dao.RestBookingDateHibernateDAOImpl;
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
		gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
								.setDateFormat("yyyy-MM-dd").create();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getPathInfo().startsWith("/get")) {
			doPost(req, resp);
		} else {
			resp.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
	        resp.getWriter().println("Method Not Allowed");
		}
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
			case "/getRestBookingDate":
				getRestBookingDate(req, resp);
				break;
			case "/doRestCollection":
				doRestCollection(req, resp);
				break;
			case "/doRest":
				doRest(req, resp);
				break;
			case "/doMemBooking":
				doMemBooking(req, resp);
				break;
			case "/doRestBookingDate":
				doRestBookingDate(req, resp);
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
		PrintWriter out = resp.getWriter();
		if (req.getParameter("bookingId") != null) {
			MemBookingVO vo = memBookingService.getByMembookingId(Integer.valueOf(req.getParameter("bookingId")));
			out.print(gson.toJson(vo));
		} else if (req.getParameter("memId") != null) {
			List<MemBookingVO> list = memBookingService.getByMemId(Integer.valueOf(req.getParameter("memId")));
			out.print(gson.toJson(list));
		} else if (req.getParameter("restId") != null) {
			List<MemBookingVO> list = memBookingService.getByRestId(Integer.valueOf(req.getParameter("restId")));
			out.print(gson.toJson(list));
		} else {
			List<MemBookingVO> list = memBookingService.getAllMembooking();
			out.print(gson.toJson(list));
		}
		
	}
	
	private void getRestAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getParameter("restId") != null) {
			RestVO vo = restService.getRestByRestId(Integer.parseInt(req.getParameter("restId")));
			PrintWriter out = resp.getWriter();
			out.print(gson.toJson(vo));
		} else {
			List<RestVO> list = restService.getAllRest();
			PrintWriter out = resp.getWriter();
			out.print(gson.toJson(list));
		}
	}
	
	private void getRestBookingDate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getParameter("restId") != null && req.getParameter("bookingDate") != null) {
			RestBookingDateVO vo = new RestBookingDateVO();
			vo.setRestId(Integer.valueOf(req.getParameter("restId")));
			vo.setBookingDate(java.sql.Date.valueOf(req.getParameter("bookingDate")));
			List<RestBookingDateVO> list = restBookingDateService.findByPK(vo);
			PrintWriter out = resp.getWriter();
			out.print(gson.toJson(list));
		} else {
			List<RestBookingDateVO> list = restBookingDateService.getAll();
			PrintWriter out = resp.getWriter();
			out.print(gson.toJson(list));
		}
			
	}
	
	private void doRest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		String action = "";
		action = req.getParameter("action");
		switch (action) {
			case("add"):
				try {
					String restName = req.getParameter("restName");
					String restTel = req.getParameter("restTel");
					String restAddress = req.getParameter("restAddress");
					String restType = req.getParameter("restType");
					String restOpen = req.getParameter("restOpen");
					Integer	bookingLimit = Integer.valueOf(req.getParameter("bookingLimit").trim());
					RestVO rest = new RestVO();
					rest.setRestName(restName);
					rest.setRestTel(restTel);
					rest.setRestAddress(restAddress);
					rest.setRestType(restType);
					rest.setRestOpen(restOpen);
					rest.setRestStatus(Integer.valueOf(req.getParameter("restStatus")));
					rest.setBookingLimit(bookingLimit);
					rest.setAdminId(Integer.valueOf(req.getParameter("adminId")));
					out.print("SUCCESS" + " " + restService.addRest(rest));
				} catch (Exception e) {
					out.print("ERROR");
					e.printStackTrace();
				}
				break;
			case("update"):
				try {
					RestVO rest = new RestVO();
					rest.setRestName(req.getParameter("restName"));
					rest.setRestTel(req.getParameter("restTel"));
					rest.setRestAddress(req.getParameter("restAddress"));
					rest.setRestType(req.getParameter("restType"));
					rest.setRestOpen(req.getParameter("restOpen"));
					rest.setRestStatus(Integer.parseInt(req.getParameter("restStatus")));
					rest.setBookingLimit(Integer.parseInt(req.getParameter("bookingLimit")));
					rest.setAdminId(Integer.parseInt(req.getParameter("adminId")));
					rest.setRestId(Integer.parseInt(req.getParameter("restId")));
					restService.updateRest(rest);
					out.print("SUCCESS");
				} catch (Exception e) {
					out.print("ERROR");
					e.printStackTrace();
				}
				break;
			case("delete"):
				try {
					String restId = req.getParameter("restId");
					restService.deleteRest(Integer.parseInt(restId));
					out.print("SUCCESS");
				} catch (Exception e) {
					out.print("ERROR");
					e.printStackTrace();
				}
				break;
		}
	}
	
	private void doMemBooking(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		String action = "";
		action = req.getParameter("action");
		switch (action) {
			case("add"):
				try {
					MemBookingVO vo = new MemBookingVO();
					vo.setRestId(Integer.parseInt(req.getParameter("restId")));
					vo.setMemId(Integer.parseInt(req.getParameter("memId")));
					vo.setCheckStatus(Integer.parseInt(req.getParameter("checkStatus")));
					vo.setBookingTime(Integer.parseInt(req.getParameter("bookingTime")));
					vo.setBookingNumber(Integer.parseInt(req.getParameter("bookingNumber")));
					vo.setBookingDate(java.sql.Date.valueOf(req.getParameter("bookingDate")));
					memBookingService.add(vo);
					out.print("SUCCESS");
				} catch (Exception e) {
					out.print("ERROR");
					e.printStackTrace();
				}
				break;
			case("update"):
				try {
					MemBookingVO vo = new MemBookingVO();
					vo.setRestId(Integer.parseInt(req.getParameter("restId")));
					vo.setMemId(Integer.parseInt(req.getParameter("memId")));
					vo.setCheckStatus(Integer.parseInt(req.getParameter("checkStatus")));
					vo.setBookingTime(Integer.parseInt(req.getParameter("bookingTime")));
					vo.setBookingNumber(Integer.parseInt(req.getParameter("bookingNumber")));
					vo.setBookingDate(java.sql.Date.valueOf(req.getParameter("bookingDate")));
					vo.setBookingId(Integer.parseInt(req.getParameter("bookingId")));
					memBookingService.update(vo);
					out.print("SUCCESS");
				} catch (Exception e) {
					out.print("ERROR");
					e.printStackTrace();
				}
				break;
			case("delete"):
				try {
					memBookingService.delete(Integer.parseInt(req.getParameter("bookingId")));
					out.print("SUCCESS");
				} catch (Exception e) {
					out.print("ERROR");
					e.printStackTrace();
				}
				break;
		}
		
	}
	
	private void doRestBookingDate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		String action = "";
		action = req.getParameter("action");
		switch (action) {
			case("add"):
				try {
					RestBookingDateVO vo = new RestBookingDateVO();
					vo.setRestId(Integer.valueOf(req.getParameter("restId")));
					vo.setBookingDate(java.sql.Date.valueOf(req.getParameter("bookingDate")));
					vo.setRestOpen(Integer.valueOf(req.getParameter("restOpen")));
					vo.setMorningNum(Integer.valueOf(req.getParameter("morningNum")));
					vo.setNoonNum(Integer.valueOf(req.getParameter("noonNum")));
					vo.setEveningNum(Integer.valueOf(req.getParameter("eveningNum")));
					restBookingDateService.add(vo);
					out.print("SUCCESS");
				} catch (Exception e) {
					out.print("ERROR");
					e.printStackTrace();
				}
				break;
			case("update"):
				
				break;
			case("delete"):
				try {
					RestBookingDateVO vo = new RestBookingDateVO();
					vo.setRestId(Integer.valueOf(req.getParameter("restId")));
					vo.setBookingDate(java.sql.Date.valueOf(req.getParameter("bookingDate")));
					restBookingDateService.delete(vo);
					out.print("SUCCESS");
				} catch (Exception e) {
					out.print("ERROR");
					e.printStackTrace();
				}
				break;
		}
	}
	
	
	
	
	
	
}
