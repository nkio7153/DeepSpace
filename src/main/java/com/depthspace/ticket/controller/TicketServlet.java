package com.depthspace.ticket.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.depthspace.account.model.account.AccountVO;
import com.depthspace.restaurant.model.restaurant.RestVO;
import com.depthspace.ticket.model.TicketTypesVO;
import com.depthspace.ticket.model.TicketVO;
import com.depthspace.ticket.service.TicketService;
import com.depthspace.ticket.service.TicketServiceImpl;

@WebServlet("/backendticket/*")
public class TicketServlet extends HttpServlet {

	// 一個 servlet 實體對應一個 service 實體
	private TicketService ticketService;

	@Override
	public void init() throws ServletException {
		ticketService = new TicketServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String pathInfo = req.getPathInfo();
		if (pathInfo == null) {
			pathInfo = "";
		}

		switch (pathInfo) {
		case "/": // 票券管理頁面
			res.sendRedirect(req.getContextPath() + "/ticket/mg.jsp");
			break;
		case "/mglist": // 票券總列表
			doList(req, res);
			break;
		case "/mgedit": // 票券更新頁面
			res.sendRedirect(req.getContextPath() + "/ticket/mgEdit.jsp");
			break;
		case "/mgeditsuccess": // 票券更新成功
			doEditSuccess(req, res);
			break;
		case "/mgsearch": // 票券查找
			doSearch(req, res);
			break;
		case "/mgadd": // 票券新增頁面
			res.sendRedirect(req.getContextPath() + "/ticket/mgAdd.jsp");
			break;
		case "/mgaddsuccess": // 票券新增成功
			doAddSuccess(req, res);
			break;
		case "/mgdel": // 票券刪除
			doDel(req, res);
			break;

		default:
			System.out.println("Path not handled: " + pathInfo);
		}
	}

	/************ 票券列表 ************/
	private void doList(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String page = req.getParameter("page");
		int currentPage = (page == null) ? 1 : Integer.parseInt(page);

		List<TicketVO> ticketList = ticketService.getAllTickets(currentPage);
		List<TicketVO> ticketsWithCity = ticketService.getTicketsWithCity();
		if (req.getSession().getAttribute("ticketPageQty") == null) {
			int ticketPageQty = ticketService.getPageTotal();
			req.getSession().setAttribute("ticketPageQty", ticketPageQty);
		}

		req.setAttribute("ticketList", ticketList);
		req.setAttribute("currentPage", currentPage);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/ticket/mgList.jsp");
		dispatcher.forward(req, res);
	}

	
	/************ 票券更新 ************/	
	private void doEditSuccess(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//	    Integer ticketId = Integer.valueOf(req.getParameter("ticketId"));
//	    TicketVO ticket = ticketService.getTicketById(ticketId); 
//		
////		TicketVO ticket = new TicketVO();
////		TicketVO ticket = ticketService.getTicketById(ticketId);

	    Integer ticketId = Integer.valueOf(req.getParameter("ticketId"));
	    TicketVO ticket = ticketService.getTicketById(ticketId); 
	    
		ticket.setTicketName(req.getParameter("ticketName"));
		ticket.setPrice(Integer.valueOf(req.getParameter("price")));
		ticket.setStock(Integer.valueOf(req.getParameter("stock")));
//		Integer ticketTypeId = Integer.valueOf(req.getParameter("TICKET_TYPE_ID"));
		ticket.setValidDays(Integer.valueOf(req.getParameter("validDays")));
		ticket.setDescription(req.getParameter("description"));
		ticket.setAddress(req.getParameter("address"));
		ticket.setLongitude(Double.valueOf(req.getParameter("longitude")));
		ticket.setLatitude(Double.valueOf(req.getParameter("latitude")));
		ticket.setStatus(Byte.valueOf(req.getParameter("status")));
	    
		req.setAttribute("ticket", ticket);
		ticketService.updateTicket(ticket);

	}
	
//	/************ 票券搜尋 ************/	
	private void doSearch(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		RequestDispatcher dispatcher = req.getRequestDispatcher("/ticket/mgSearch.jsp");
		dispatcher.forward(req, res);

	}

	/************票券新增************/
	private void doAddSuccess(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//下列要有set，才能將前端的資料送進資料庫
		TicketVO ticket = new TicketVO();
		ticket.setTicketName(req.getParameter("ticketName"));
		ticket.setPrice(Integer.valueOf(req.getParameter("price")));
		ticket.setStock(Integer.valueOf(req.getParameter("stock")));
//		Integer ticketTypeId = Integer.valueOf(req.getParameter("TICKET_TYPE_ID"));
		ticket.setValidDays(Integer.valueOf(req.getParameter("validDays")));
		ticket.setDescription(req.getParameter("description"));
		ticket.setAddress(req.getParameter("address"));
		ticket.setLongitude(Double.valueOf(req.getParameter("longitude")));
		ticket.setLatitude(Double.valueOf(req.getParameter("latitude")));
		ticket.setStatus(Byte.valueOf(req.getParameter("status")));
		
		ticketService.addTicket(ticket);
	}
	
	/************ 票券刪除 ************/
	private void doDel(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Integer ticketId = Integer.valueOf(req.getParameter("ticketId"));
		ticketService.deleteTicket(ticketId);
	}
	

	
//    protected void outputImage(HttpServletRequest req, HttpServletResponse res) 
//              throws ServletException, IOException {
//          String ticketId = req.getPathInfo().substring(7); // 從 "/image/{ticketId}" 中取得 ticketId
//
//          TicketServiceImpl ticketService = new TicketServiceImpl();
//          byte[] imageBytes = ticketService.getImageByTicketId(ticketId); 
//
//            res.setContentType("image/jpeg");  // 假設圖片是 JPEG 格式，如有需要請修改
//            res.getOutputStream().write(imageBytes);

	}

