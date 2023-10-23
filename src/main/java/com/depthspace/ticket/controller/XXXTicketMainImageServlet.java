package com.depthspace.ticket.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.SessionFactory;

import com.depthspace.ticket.*;
import com.depthspace.ticket.model.TicketImagesVO;
import com.depthspace.ticket.model.TicketVO;
import com.depthspace.ticket.service.TicketService;
import com.depthspace.ticket.service.TicketServiceImpl;
import com.depthspace.utils.HibernateUtil;

//@WebServlet("/ticketimage")
//public class XXXTicketMainImageServlet extends HttpServlet {
//    
//    private TicketService ticketService = new TicketServiceImpl();
//    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
// 
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//
//    	String servletPath = req.getServletPath() + req.getPathInfo();
//    	
//    	if("/ticketimage/item".equals(servletPath)) {
//    		readItemImage(req,res);
//    	} else if ("/ticketimage/item".equals(servletPath)) {
//    		readItemImage(req,res);
//    	}
//    	
////        String ticketIdStr = req.getParameter("ticketId");
////        Integer ticketId = Integer.parseInt(ticketIdStr);
////        
////        TicketVO ticket = ticketService.getTicketById(ticketId);
////        TicketImagesVO mainImage = ticket.getMainImage();
////        
////        if(mainImage != null) {
////            byte[] imageData = mainImage.getImage();
////            res.setContentType("image/jpeg");  
////            res.getOutputStream().write(imageData);
////        } else {
////            res.sendError(HttpServletResponse.SC_NOT_FOUND);
//        }
//    
//
//
//	private void readItemImage(HttpServletRequest req, HttpServletResponse res) throws IOException {
//		res.setContentType("image/jpg");
//		ServletOutputStream outputStream = res.getOutputStream();
////		try {
////			int id = Integer.parseInt(req.getParameter("ticketId"));
////			int position = Integer.parseInt(req.getParameter("1"));
////			TicketImagesDAO tickeImagesDAO = new TicketImagesDAO(sessionFactory);
//			
			
//		}
//		
//	}
