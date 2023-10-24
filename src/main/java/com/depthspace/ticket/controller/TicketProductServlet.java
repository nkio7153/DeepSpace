package com.depthspace.ticket.controller;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.depthspace.ticketorders.model.ticketorders.TicketOrdersVO;

@WebServlet("/ticketproduct/*")
public class TicketProductServlet extends HttpServlet {

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
		case "/": // 票券頁面
			res.sendRedirect(req.getContextPath() + "/ticketProduct/tpindex.jsp");
			break;
		case "/list": // 票券總列表
			doList(req, res);
//			res.sendRedirect(req.getContextPath() + "/ticketProduct/tpList.jsp");
			break;	
//			
//		case "/mgsearch": // 票券查找
//			doSearch(req, res);
//			break;


		default:
			System.out.println("Path not handled: " + pathInfo);
		}
	}

	/************ 票券列表 ************/
	private void doList(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String page = req.getParameter("page");
		int currentPage = (page == null) ? 1 : Integer.parseInt(page);

		//取得所有票券內容(VO)
		List<TicketVO> ticketList = ticketService.getAllTickets(currentPage);
		//取得票券區域
		List<TicketVO> ticketsWithCity = ticketService.getTicketsWithCity();

		if (req.getSession().getAttribute("ticketPageQty") == null) {
			int ticketPageQty = ticketService.getPageTotal();
			req.getSession().setAttribute("ticketPageQty", ticketPageQty);
		}
		long totalTickets = ticketService.getTotalTickets();
		req.setAttribute("totalTickets", totalTickets);

		req.setAttribute("totalTickets", totalTickets);

		req.setAttribute("ticketList", ticketList);
		req.setAttribute("currentPage", currentPage);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/ticketProduct/tpList.jsp");
		dispatcher.forward(req, res);
	}

	
//	/************ 票券更新 ************/	
//	private void doEditSuccess(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

//		Integer ticketId = Integer.valueOf(req.getParameter("ticketId"));
//	    TicketVO ticket = ticketService.getTicketById(ticketId); 
//	    
//		ticket.setTicketName(req.getParameter("ticketName"));
//		ticket.setPrice(Integer.valueOf(req.getParameter("price")));
//		ticket.setStock(Integer.valueOf(req.getParameter("stock")));
////		Integer ticketTypeId = Integer.valueOf(req.getParameter("TICKET_TYPE_ID"));
//		ticket.setValidDays(Integer.valueOf(req.getParameter("validDays")));
//		ticket.setDescription(req.getParameter("description"));
//		ticket.setAddress(req.getParameter("address"));
//		ticket.setLongitude(Double.valueOf(req.getParameter("longitude")));
//		ticket.setLatitude(Double.valueOf(req.getParameter("latitude")));
//		ticket.setStatus(Byte.valueOf(req.getParameter("status")));
//	    
//		req.setAttribute("ticket", ticket);
//		ticketService.updateTicket(ticket);
//	}
	
////	/************ 票券搜尋 ************/	
//	private void doSearch(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//
//		    Map<String, String[]> map = req.getParameterMap();
//
//		    res.setContentType("application/json"); 
//		    res.setCharacterEncoding("UTF-8");
//
//		    PrintWriter out = res.getWriter();
//
//		    try {
//		        List<TicketVO> ticketList = ticketService.getTicketsByCompositeQuery(map);
//		        
//		        StringBuilder json = new StringBuilder("[");
//		        for (TicketVO ticket : ticketList) {
//		            json.append(String.format(
////		                "{\"類型\": \"%s\", \"編號\": \"%s\", \"圖片\": \"%s/ticketimage?ticketId=%s\", \"名稱\": \"%s\", \"價格\": \"%s\", \"數量\": \"%s\", \"描述\": \"%s\", \"發布日\": \"%s\", \"狀況\": \"%s\", \"區域\": \"%s\"},",
//			            "{\"編號\": \"%s\", \"圖片\": \"%s/ticketimage?ticketId=%s\", \"名稱\": \"%s\", \"價格\": \"%s\", \"數量\": \"%s\", \"描述\": \"%s\", \"發布日\": \"%s\", \"狀況\": \"%s\"},",
//
//		            		// TicketVO的屬性
////		                ticket.getTicketType(), //類型
//		                ticket.getTicketId(), //編號
//		                req.getContextPath(), //圖片的前半部分URL
//		                ticket.getTicketId(), //圖片的ticketId參數
//		                ticket.getTicketName(), //名稱
//		                ticket.getPrice(), //價格
//		                ticket.getStock(), //數量
//		                ticket.getDescription(), //描述
//		                ticket.getPublishedDate(), //發布日
//		                ticket.getStatus() //狀況
////		                ticket.getCity().getCityName() //區域
//		            ));
//		        }
//		        json = new StringBuilder(json.toString().replaceAll(",$", "")); 
//		        json.append("]");
//
//		        out.print(json); 
//
//		    } catch (Exception e) { 
//		        e.printStackTrace(); 
//		        out.print("[]");
//		    } finally {
//		        out.flush();
//		    }
//		}
//
//
//
//	/************票券新增************/
//	private void doAddSuccess(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		//下列要有set，才能將前端的資料送進資料庫
//		TicketVO ticket = new TicketVO();
//		ticket.setTicketName(req.getParameter("ticketName"));
//		ticket.setPrice(Integer.valueOf(req.getParameter("price")));
//		ticket.setStock(Integer.valueOf(req.getParameter("stock")));
////		Integer ticketTypeId = Integer.valueOf(req.getParameter("TICKET_TYPE_ID"));
//		ticket.setValidDays(Integer.valueOf(req.getParameter("validDays")));
//		ticket.setDescription(req.getParameter("description"));
//		ticket.setAddress(req.getParameter("address"));
//		ticket.setLongitude(Double.valueOf(req.getParameter("longitude")));
//		ticket.setLatitude(Double.valueOf(req.getParameter("latitude")));
//		ticket.setStatus(Byte.valueOf(req.getParameter("status")));
//		
//		ticketService.addTicket(ticket);
//	}
//	
//	/************ 票券刪除 ************/
//	private void doDel(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		Integer ticketId = Integer.valueOf(req.getParameter("ticketId"));
//		ticketService.deleteTicket(ticketId);
//	}
	

}

