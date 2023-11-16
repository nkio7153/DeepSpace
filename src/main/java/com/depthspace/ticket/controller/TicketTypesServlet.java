package com.depthspace.ticket.controller;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.json.JSONObject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.alibaba.fastjson.util.IOUtils;
import com.depthspace.ticket.model.TicketImagesVO;
import com.depthspace.ticket.model.TicketTypesVO;
import com.depthspace.ticket.service.TicketTypesService;
import com.depthspace.ticket.service.TicketTypesServiceImpl;
import com.depthspace.ticketorders.model.ticketorders.TicketOrdersVO;
import com.depthspace.utils.HibernateUtil;

@WebServlet("/tickettypesmg/*")
public class TicketTypesServlet extends HttpServlet {

	private TicketTypesService ticketTypesService;
	Session session;

	@Override
	public void init() throws ServletException {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		session = sessionFactory.openSession();
		ticketTypesService = new TicketTypesServiceImpl();
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
		case "/": 
			res.sendRedirect(req.getContextPath() + "/backend/tickettypes/mg.jsp");
			break;
		case "/list": 
			list(req, res);
			break;
		case "/add": 
			add(req, res);
			break;
		case "/edit":
			edit(req, res);
			break;
		default:
			System.out.println("Path not handled: " + pathInfo);
		}
	}

	/************ 列表 ************/
	private void list(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		List<TicketTypesVO> listAll = ticketTypesService.getAll();
		req.setAttribute("listAll", listAll);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/backend/tickettypes/list.jsp");
		dispatcher.forward(req, res);
	}

	/************ 新增 ************/
	protected void add(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
				
	    StringBuilder jsonBuffer = new StringBuilder();
	    String line;
	    while((line = req.getReader().readLine()) != null) {
	        jsonBuffer.append(line);
	    }
	    String jsonString = jsonBuffer.toString();
	    
	    Gson gson = new Gson();
	    TicketTypesVO ticketType = gson.fromJson(jsonString, TicketTypesVO.class);

	    Integer newTicketType =ticketTypesService.insert(ticketType); //取得新增的PK
	    
	    res.setContentType("application/json");
	    res.setCharacterEncoding("UTF-8");
    	
    	JSONObject jsonResponse = new JSONObject();
    	jsonResponse.put("status", "success");
    	jsonResponse.put("message", "good!!!!!!!!!!");
    	jsonResponse.put("newTicketTypeId", newTicketType);
    	res.getWriter().write(jsonResponse.toString());   
	}
	
	/************ 修改************/
    protected void edit(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

    	StringBuilder jsonBuffer = new StringBuilder();
    	String line;
    	while((line = req.getReader().readLine()) != null) {
    		jsonBuffer.append(line);
    	}
    	String jsonString = jsonBuffer.toString();
    	
    	Gson gson = new Gson();
    	TicketTypesVO updatedTicketType = gson.fromJson(jsonString, TicketTypesVO.class);
		Integer ticketTypeId = updatedTicketType.getTicketTypeId();
	    TicketTypesVO ticketType = ticketTypesService.getOneById(ticketTypeId);
        ticketType.setTypeName(updatedTicketType.getTypeName());
        ticketType.setDescription(updatedTicketType.getDescription());

    	ticketTypesService.update(ticketType);
    	
    	res.setContentType("application/json");
    	res.setCharacterEncoding("UTF-8");
    	
    	JSONObject jsonResponse = new JSONObject();
    	jsonResponse.put("status", "success");
    	jsonResponse.put("message", "good!!!!!!!!!!");
    	res.getWriter().write(jsonResponse.toString());   
	}

}
