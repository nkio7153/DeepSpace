package com.depthspace.restaurant.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.depthspace.restaurant.model.membooking.MemBookingVO;
import com.depthspace.restaurant.model.restaurant.RestVO;
import com.depthspace.restaurant.service.MemBookingService;
import com.depthspace.restaurant.service.MemBookingServiceImpl;
import com.depthspace.restaurant.service.RestService;
import com.depthspace.restaurant.service.RestServiecImpl;

@WebServlet("/Rest/Rest.do")
public class RestServlet extends HttpServlet {
	
	private RestService restService;
	private MemBookingService membookingService;

	@Override
	public void init() throws ServletException {
		restService = new RestServiecImpl();
		membookingService = new MemBookingServiceImpl();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		String forwardPath = "";
		switch (action) {
			case "getAll":
				forwardPath = getAllRests(req, resp);
				break;
			case "compositeQuery":
				forwardPath = getCompositeEmpsQuery(req, resp);
				break;
			case "add":
				forwardPath = add(req, resp);
				break;
			case "delete":
				forwardPath = delete(req, resp);
				break;
			case "getId_for_update":
				forwardPath = forUpdate(req, resp);
				break;
			case "update":
				forwardPath = update(req, resp);
				break;
			case "getMembooking":
				forwardPath = memBooking(req, resp);
				break;
			default:
				forwardPath = "/Rest/indexDemo.jsp";
		}
		
		resp.setContentType("text/html; charset=UTF-8");
		RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
		dispatcher.forward(req, resp);
	}
	
	private String getAllRests(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<RestVO> restList = restService.getAllRest();
		req.setAttribute("restList", restList);
//		return "/Rest/listAllRests.jsp";
		return "/backend/Rest/demoRestList.jsp";
	}
	
	private String getCompositeEmpsQuery(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String restId = req.getParameter("restId");
		if (restId == null) { 
			return "/backend/Rest/listCompositeQuery.jsp";
		}
		RestVO restList = restService.getRestByRestId(Integer.parseInt(restId));
		req.setAttribute("rest", restList);
		return "/backend/Rest/listCompositeQuery.jsp";
	}
	
	private String add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
		
		String restName = req.getParameter("restName");
		if (restName.isEmpty() || restName == null || restName.trim().length() == 0) {
			errorMsgs.add("餐廳名稱請勿空白");
		}
		String restTel = req.getParameter("restTel");
		if (restTel.isEmpty() || restTel == null || restTel.trim().length() == 0) {
			errorMsgs.add("餐廳電話請勿空白");
		}
		String restAddress = req.getParameter("restAddress");
		if (restAddress.isEmpty() || restAddress == null || restAddress.trim().length() == 0) {
			errorMsgs.add("餐廳電話請勿空白");
		}
		String restType = req.getParameter("restType");
		if (restType.isEmpty() || restType == null || restType.trim().length() == 0) {
			errorMsgs.add("餐廳類型請勿空白");
		}
		String restOpen = req.getParameter("restOpen");
		if (restOpen.isEmpty() || restOpen == null || restOpen.trim().length() == 0) {
			errorMsgs.add("營業時間請勿空白");
		}
		Integer bookingLimit = null;
		try {
			bookingLimit = Integer.valueOf(req.getParameter("bookingLimit").trim());
		} catch (Exception e) {
			errorMsgs.add("可預約組數請勿空白");
			bookingLimit = 0;
		}
		RestVO rest = new RestVO();
		rest.setRestName(restName);
		rest.setRestTel(restTel);
		rest.setRestAddress(restAddress);
		rest.setRestType(restType);
		rest.setRestOpen(restOpen);
		rest.setRestStatus(Integer.valueOf(req.getParameter("restStatus")));
		rest.setBookingLimit(bookingLimit);
		rest.setAdminId(Integer.valueOf(req.getParameter("adminId")));
		restService.addRest(rest);
		return "/backend/Rest/listCompositeQuery.jsp";
	}
	
	private String delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String restId = req.getParameter("restId");
		restService.deleteRest(Integer.parseInt(restId));
		return "/Rest/Rest.do?action=getAll";
	}
	
	private String forUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String restId = req.getParameter("restId");
		RestVO restList = restService.getRestByRestId(Integer.parseInt(restId));
		req.setAttribute("rest", restList);
		return "/backend/Rest/Update_Rest.jsp";
	}
	
	private String update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
		return "/Rest/Rest.do?action=getAll";
	}
	
	private String memBooking(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String restId = req.getParameter("restId");
		List<MemBookingVO> mb = membookingService.getByRestId(Integer.valueOf(restId));
		req.setAttribute("mbList", mb);
		if (mb == null) {
			return "/backend/Rest/demoRestList.jsp";
		}
		System.out.println(mb.toString());
		return "/backend/Rest/membooking.jsp";
	}
}
