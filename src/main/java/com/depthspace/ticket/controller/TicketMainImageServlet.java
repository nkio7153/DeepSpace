package com.depthspace.ticket.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.depthspace.ticket.*;
import com.depthspace.ticket.model.TicketImagesVO;
import com.depthspace.ticket.model.TicketVO;
import com.depthspace.ticket.service.TicketService;
import com.depthspace.ticket.service.TicketServiceImpl;

@WebServlet("/ticketimage")
public class TicketMainImageServlet extends HttpServlet {
    
    private TicketService ticketService = new TicketServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String ticketIdStr = req.getParameter("ticketId");
        Integer ticketId = Integer.parseInt(ticketIdStr);
        
        TicketVO ticket = ticketService.getTicketById(ticketId);
        TicketImagesVO mainImage = ticket.getMainImage();
        
        if(mainImage != null) {
            byte[] imageData = mainImage.getImage();
            res.setContentType("image/jpeg");  
            res.getOutputStream().write(imageData);
        } else {
            res.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
