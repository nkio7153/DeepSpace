package com.depthspace.restaurant.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.depthspace.restaurant.model.restaurant.RestVO;
import com.depthspace.restaurant.service.RestService;
import com.depthspace.restaurant.service.RestServiecImpl;

@WebServlet("/Rest/Rest.do")
public class RestServlet extends HttpServlet {
	
	private RestService restService;

	@Override
	public void init() throws ServletException {
		restService = new RestServiecImpl();
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
			default:
				forwardPath = "/Rest/index.jsp";
		}
		
		resp.setContentType("text/html; charset=UTF-8");
		RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
		dispatcher.forward(req, resp);
	}
	
	private String getAllRests(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<RestVO> restList = restService.getAllRest();
		req.setAttribute("restList", restList);
		return "/Rest/listAllRests.jsp";
		
	}
	
	private String getCompositeEmpsQuery(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String restId = req.getParameter("restId");
		if (restId == null) { 
			return "/Rest/listCompositeQuery.jsp";
		}
		RestVO restList = restService.getRestByRestId(Integer.parseInt(restId));
		req.setAttribute("rest", restList);
		return "/Rest/listCompositeQuery.jsp";
	}
	
	private String add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RestVO rest = new RestVO();
		rest.setRestName(req.getParameter("restName"));
		rest.setRestTel(req.getParameter("restTel"));
		rest.setRestAddress(req.getParameter("restAddress"));
		rest.setRestOpen(req.getParameter("restOpen"));
		rest.setRestStatus(Integer.valueOf(req.getParameter("restStatus")));
		rest.setBookingLimit(Integer.valueOf(req.getParameter("bookingLimit")));
		rest.setAdminId(Integer.valueOf(req.getParameter("adminId")));
		restService.addRest(rest);
		return "/Rest/listCompositeQuery.jsp";
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
		return "/Rest/Update_Rest.jsp";
	}
	
	private String update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RestVO rest = new RestVO();
		rest.setRestName(req.getParameter("restName"));
		rest.setRestTel(req.getParameter("restTel"));
		rest.setRestAddress(req.getParameter("restAddress"));
		rest.setRestOpen(req.getParameter("restOpen"));
		rest.setRestStatus(Integer.parseInt(req.getParameter("restStatus")));
		rest.setBookingLimit(Integer.parseInt(req.getParameter("bookingLimit")));
		rest.setAdminId(Integer.parseInt(req.getParameter("adminId")));
		rest.setRestId(Integer.parseInt(req.getParameter("restId")));
		restService.updateRest(rest);
		return "/Rest/Rest.do?action=getAll";
	}
}
