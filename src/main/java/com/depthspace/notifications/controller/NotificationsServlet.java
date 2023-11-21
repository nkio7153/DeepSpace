package com.depthspace.notifications.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Comparator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.depthspace.column.model.ColumnArticlesVO;
import com.depthspace.member.model.MemVO;
import com.depthspace.notifications.model.NotificationsVO;
import com.depthspace.notifications.service.NotificationsService;
import com.depthspace.notifications.service.NotificationsServiceImpl;

import com.depthspace.ticketorders.model.ticketorders.TicketOrdersVO;

@WebServlet("/notifications/*")
public class NotificationsServlet extends HttpServlet {

	private NotificationsService notificationsService;

	public void init() throws ServletException {
		notificationsService = new NotificationsServiceImpl();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String pathInfo = req.getPathInfo();
		if (pathInfo == null) {
			pathInfo = "";
		}

		switch (pathInfo) {
		case "/":
			res.sendRedirect(req.getContextPath() + "/indexpage/index.jsp");
			break;
		case "/list":
			doList(req, res);
			break;
		case "/countUnread":
		    doCountUnread(req, res);
		    break;
		case "/toBeRead":
			toBeRead(req, res);
			break;
		    
		default:
			System.out.println("Path not handled: " + pathInfo);
		}
	}



	/************ 列表 ************/
	protected void doList(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		HttpSession session = req.getSession(false);
		Integer memId = (Integer) session.getAttribute("memId");

		if (memId == null) { //無會員id就無法訪問
			res.sendRedirect(req.getContextPath() + "/indexpage/index.jsp");
			return;
		}
		
		List<NotificationsVO> notifications = notificationsService.getByMemId(memId);
		Integer unreadCount = notificationsService.getUnreadNotificationsCount(memId);
		req.setAttribute("notifications", notifications);
	    req.setAttribute("unreadCount", unreadCount);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/frontend/notifications/list.jsp");
		dispatcher.forward(req, res);
	}
	
	/************ 計算未讀數量 ************/
	protected void doCountUnread(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

	    HttpSession session = req.getSession(false);
	    Integer memId = (Integer) session.getAttribute("memId");

		if (memId == null) { //無會員id就無法訪問
			res.sendRedirect(req.getContextPath() + "/indexpage/index.jsp");
			return;
		}

	    Integer unreadCount = notificationsService.getUnreadNotificationsCount(memId);

	    res.setContentType("application/json");
	    res.setCharacterEncoding("UTF-8");
	    res.getWriter().write("{\"unreadCount\": " + unreadCount + "}");
	}
		
	/************ 未讀變已讀 ************/
	protected void toBeRead(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		String noteIdStr = req.getParameter("noteId");
		System.out.println("YYYYYYYYYYYYYYYYYYYYY??????????????"+noteIdStr);
		if (noteIdStr != null & !noteIdStr.isEmpty()) {
			Integer noteId = Integer.parseInt(noteIdStr);
			notificationsService.toBeRead(noteId);
			System.out.println("YYYYYYYYYYYYYYYYYYYYYYYY"+noteId);
		}
		
		
		res.setContentType("application/json");
		res.setCharacterEncoding("UTF-8");
		res.getWriter().write("{\"status\":\"success\"}");
	}

}
