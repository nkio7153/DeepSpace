//package com.depthspace.adminfunclist.controller;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.List;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import com.depthspace.adminfunclist.service.AdminFuncListService;
//import com.depthspace.adminfunclist.service.AdminFuncListServiceImpl;
//import com.depthspace.forum.model.articlescollect.ArticlesCollectVO;
//import com.depthspace.forum.model.articlescollect.service.ArticlesCollectService;
//import com.depthspace.forum.model.articlescollect.service.ArticlesCollectServiceImpl;
//import com.google.gson.Gson;
//
//@WebServlet("/funclist/*")
//public class AdminFuncListServlet extends HttpServlet {
//	private AdminFuncListService adminFuncListService;
//
//	@Override
//	public void init() throws ServletException {
//		adminFuncListService = new AdminFuncListServiceImpl();
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
//			addAdminFuncList(req, resp);
//			break;
//		}
//
//	}
//
//	private void addAdminFuncList(HttpServletRequest req, HttpServletResponse resp) {
//		Integer adminId = null;
//		try {
//			adminId = Integer.valueOf(req.getParameter("articleId"));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		Integer memId = null;
//		HttpSession session = req.getSession(false);
//		if (session.getAttribute("memId") != null) {
//			memId = (Integer) session.getAttribute("memId");
//		}
//		System.out.println("memId" + memId);
//		System.out.println("articleId" + articleId);
//		// 检查是否已收藏
//		boolean isCollected = articlesCollectService.isCollect(articleId, memId);
//		ArticlesCollectVO acvo = new ArticlesCollectVO(articleId, memId);
//		if (isCollected) {
//			articlesCollectService.delete(acvo);
//			setJsonResponse(resp, "取消收藏");
//		} else {
//			articlesCollectService.insert(acvo);
//			setJsonResponse(resp, "添加收藏");
//		}
//	}
//
//	private void setJsonResponse(HttpServletResponse resp, String message) {
//		resp.setContentType("application/json");
//		resp.setCharacterEncoding("UTF-8");
//		try {
//			PrintWriter out = resp.getWriter();
//			out.print("{\"message\":\"" + message + "\"}");
//			out.flush();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//}
