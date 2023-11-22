//package com.depthspace.keywordqa.controller;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.HashSet;
//import java.util.List;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.MultipartConfig;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson2.JSON;
//import com.depthspace.account.model.account.AccountVO;
//import com.depthspace.account.model.account.service.AccountService;
//import com.depthspace.account.model.account.service.AccountServiceImpl;
//import com.depthspace.keywordqa.model.KeywordQaVO;
//import com.depthspace.keywordqa.service.KeywordQaService;
//import com.depthspace.keywordqa.service.KeywordQaServiceImpl;
//import com.depthspace.ticketorders.model.ticketorders.TicketOrdersVO;
//
//@WebServlet("/kw/*")
//@MultipartConfig
//public class KeywordQaServlet extends HttpServlet {
//	private KeywordQaService keywordQaService;
//
//	@Override
//	public void init() throws ServletException {
//		keywordQaService = new KeywordQaServiceImpl();
//	}
//
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		doPost(req, resp);
//	}
//
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		req.setCharacterEncoding("UTF-8");
//		String action = req.getParameter("action");
//		switch (action) {
//		case "add":
//			addKeywordQa(req, resp);
//			break;
//		case "update":
//			updateKeywordQa(req, resp);
//			break;
//		case "del":
//			deleteKeywordQa(req, resp);
//			break;
//		// 取得ajax分帳表會員列表
//		case "list":
//			listKeywordQa(req, resp);
//			break;
//		}
//	}
//
//	private void listKeywordQa(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		List<KeywordQaVO> keywordQas = keywordQaService.getAll();
//		JSONArray arr = JSONArray.parseArray(JSON.toJSONString(keywordQas));
//		resp.setContentType("application/json; charset=UTF-8");
//		PrintWriter out = resp.getWriter();
//		out.print(arr.toString());
//	}
//
//	private void addKeywordQa(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//		String kwTypes = req.getParameter("kwTypes");
//		String kwAns = req.getParameter("kwAns");	
//		KeywordQaVO keywordQa = new KeywordQaVO(null, kwTypes, kwAns);
//		keywordQaService.insert(keywordQa);
//	}
//
//	private void updateKeywordQa(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//		Integer serialId = Integer.parseInt(req.getParameter("accountId"));
//		String kwTypes = req.getParameter("kwTypes");
//		String kwAns = req.getParameter("kwAns");	
//		KeywordQaVO keywordQa = new KeywordQaVO(serialId, kwTypes, kwAns);
//		keywordQaService.update(keywordQa);
//	}
//
//	private void deleteKeywordQa(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//		Integer serialId = Integer.parseInt(req.getParameter("serialId"));
//		keywordQaService.delete(serialId);
//	}
//	
//}
