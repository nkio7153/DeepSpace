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
import com.depthspace.ticketshoppingcart.model.TicketInfoVO;
import com.depthspace.ticket.model.TicketInfo;
import com.depthspace.ticket.model.TicketVO;

@WebServlet("/ticketproduct/*")
public class TicketServlet extends HttpServlet {

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) 
    		throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo == null || "/".equals(pathInfo)) {
            res.sendRedirect(req.getContextPath() + "/ticket/index.jsp");
        } else if ("/listAll".equals(pathInfo)) {
            doList(req, res);
        } else {
            System.out.println("Path not handled: " + pathInfo);  
        }
    }


	
	protected void doList(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		TicketService ticketService = new TicketService();
		List<TicketInfo> list = ticketService.getTicketsInfo(null);
		
        for (TicketInfo img : list) {
            byte[] imageBytes = img.getImage();
            if (imageBytes != null) {
                String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                img.setBase64Image(base64Image);
            }
        }
        
		req.setAttribute("list", list); //取出物件
		String url = "/ticket/listAllTicket.jsp";
		RequestDispatcher successView = req.getRequestDispatcher(url);
		successView.forward(req, res);
		System.out.println("列表大小: " + list.size());

	}
}
		



