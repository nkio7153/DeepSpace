package com.depthspace.ticket.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.depthspace.ticket.service.TicketService;
import com.depthspace.ticket.service.TicketServiceImpl;


@WebServlet("/ticketproduct/*")
public class TicketServlet extends HttpServlet {

	// 一個 servlet 實體對應一個 service 實體
	private TicketService ticketService;

	@Override
	public void init() throws ServletException {
		ticketService = new TicketServiceImpl();
	}

//	@Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse res) 
//    		throws ServletException, IOException {
//        String pathInfo = req.getPathInfo();
//        if (pathInfo == null || "/".equals(pathInfo)) {
//            res.sendRedirect(req.getContextPath() + "/ticket/index.jsp");
//        } else if ("/listAll".equals(pathInfo)) {
//            doList(req, res);
//        } else if (pathInfo.startsWith("/image/")) { //startsWith為String取得前綴詞方法
//            outputImage(req, res);    
//        } else {
//            System.out.println("Path not handled: " + pathInfo);  
//        }
//    }
//
//
//	
//	protected void doList(HttpServletRequest req, HttpServletResponse res)
//			throws ServletException, IOException {
//		TicketServicelmpl ticketService = new TicketServicelmpl();
////		List<TicketInfo> list = ticketService.getTicketsInfo(null);
//		
////        for (TicketInfo img : list) { //若要每張票進行處理可在此進行
////            byte[] imageBytes = img.getImage();
////        }
////		req.setAttribute("list", list); //取出物件
////		String url = "/ticket/listAllTicket.jsp";
////		RequestDispatcher successView = req.getRequestDispatcher(url);
////		successView.forward(req, res);
////		System.out.println("列表大小: " + list.size());
//	}
//      
//        
//    protected void outputImage(HttpServletRequest req, HttpServletResponse res) 
//              throws ServletException, IOException {
//          String ticketId = req.getPathInfo().substring(7); // 從 "/image/{ticketId}" 中取得 ticketId
//
//          TicketServicelmpl ticketService = new TicketServicelmpl();
//          byte[] imageBytes = ticketService.getImageByTicketId(ticketId); 
//
//            res.setContentType("image/jpeg");  // 假設圖片是 JPEG 格式，如有需要請修改
//            res.getOutputStream().write(imageBytes);
//        }
//    
//


}
	



