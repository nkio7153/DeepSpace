package com.depthspace.ticket.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	        case "/":  //票券管理頁面
	            res.sendRedirect(req.getContextPath() + "/ticket/mg.jsp");
	            break;
	        case "/mglist": //票券總列表
	            doList(req, res); 
	            break;
	        case "/mgedit": //票券修改
	            doEdit(req, res); 
	            break;
	        case "/mgsearch": //票券查找
	            doSearch(req, res); 
	            break;   
	        case "/mgadd": //票券新增
	            doAdd(req, res); 
	            break;   
	        default:
	            System.out.println("Path not handled: " + pathInfo);
	    }
	}


	private void doList(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String page = req.getParameter("page");
		int currentPage = (page == null) ? 1 : Integer.parseInt(page);
		
		List<TicketVO> ticketList = ticketService.getAllTickets(currentPage);

		if (req.getSession().getAttribute("ticketPageQty") == null) {
			int ticketPageQty = ticketService.getPageTotal();
			req.getSession().setAttribute("ticketPageQty", ticketPageQty);
		}
		
		req.setAttribute("ticketList", ticketList);
		req.setAttribute("currentPage", currentPage);
		
	    RequestDispatcher dispatcher = req.getRequestDispatcher("/ticket/mglist.jsp");
	    dispatcher.forward(req, res); 
	}

	private void doEdit(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

	    RequestDispatcher dispatcher = req.getRequestDispatcher("/ticket/mgedit.jsp");
	    dispatcher.forward(req, res); 		
	}
	
	private void doSearch(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

	    RequestDispatcher dispatcher = req.getRequestDispatcher("/ticket/mgsearch.jsp");
	    dispatcher.forward(req, res); 
		
	}	
	
	private void doAdd(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

	    RequestDispatcher dispatcher = req.getRequestDispatcher("/ticket/mgadd.jsp");
	    dispatcher.forward(req, res); 
		
	}
	
//	@Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse res) 
//    		throws ServletException, IOException {
//        String pathInfo = req.getPathInfo();
//        if (pathInfo == null || "/".equals(pathInfo)) {
//            res.sendRedirect(req.getContextPath() + "/ticket/index.jsp");
//        } else if ("/listAll".equals(pathInfo)) {
//            doList(req, res);
//        } else {
//            System.out.println("Path not handled: " + pathInfo);  
//        }
//    }
//
//
//	
//	protected void doList(HttpServletRequest req, HttpServletResponse res)
//			throws ServletException, IOException {
//		List<TicketVO> listAllTickets = ticketService.getAllTickets(1);
//		 req.setAttribute("list", listAllTickets); 
//		 RequestDispatcher dispatcher = req.getRequestDispatcher("/ticket/listAllTicket.jsp"); 
//		 System.out.println("Ticket list size: " + listAllTickets.size());
//
//	}
      
        
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
	



