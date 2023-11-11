//package com.depthspace.admin.model.controller;
//
//import java.io.IOException;
//import java.util.LinkedHashMap;
//import java.util.Map;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import com.depthspace.admin.model.model.AdminVO;
//import com.depthspace.admin.model.service.AdminService;
//import com.depthspace.admin.model.service.AdminServiceImpl;
//
//@WebServlet("/logout1")
//public class LoginServlet extends HttpServlet {
//
//	private AdminService adminService;
//
//	@Override
//	public void init() throws ServletException {
//		adminService = new AdminServiceImpl();
//	}
//
//	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//
//		// 中文亂碼解決方法
//		res.setContentType("text/html;charset=UTF-8");
//		String action1 = req.getParameter("action1");
//		// =======================登出============================//
//
//		if (action1 != null) {
//			switch (action1) {
//			case "adminlogout":
//
//				HttpSession session1 = req.getSession();
//
//				session1.removeAttribute("admin");
//				res.sendRedirect(req.getContextPath() + "/admin/logout1.jsp");
//				System.out.println(session1.getId() + "刪除");
//				String user = (String) session1.getAttribute("user");
//				if (user == null) {
//					System.out.println("User is not in session.");
//				}
//				return;
//			// =================登入===============================//
//			case "adminlogout1":
//				Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
//				req.setAttribute("errorMsgs", errorMsgs);
//				// 從請求中獲取會員編號和密碼
//				String adminNoStr = req.getParameter("adminAcc");
//				String adminPassword = req.getParameter("adminPwd");
//
//				try {
//					// 驗證帳號和密碼不為空
//					if (adminNoStr != null && !adminNoStr.trim().isEmpty() && adminPassword != null
//							&& !adminPassword.trim().isEmpty()) {
//						// 使用administratorService根據會員編號查找會員
//						AdminVO adminVO = adminService.findByAdminAcc(adminNoStr);
//
//						// 檢查會員是否存在以及密碼是否匹配
//						if (adminVO != null && adminVO.getAdminPwd().equals(adminPassword)) {
//							// 登入成功，將會員信息設置到session中
//							HttpSession session = req.getSession();
//							session.setAttribute("adminVO", adminVO);
//							
//
//							// 轉發到登入成功頁面或者其他操作
//							res.sendRedirect(req.getContextPath() + "/backstage/index.html");
//						} else {
//							// 登入失敗，設置錯誤信息並轉發到登入頁面
//							req.setAttribute("loginError", "帳號或密碼不正確");
//							RequestDispatcher dispatcher = req
//									.getRequestDispatcher("/admin/logout1.jsp");
//							dispatcher.forward(req, res);
//						}
//					} else {
//						// 帳號或密碼為空，設置錯誤信息並轉發到登入頁面
//						req.setAttribute("loginError", "帳號和密碼都不能為空");
//						RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/logout1.jsp");
//						dispatcher.forward(req, res);
//					}
//				} catch (Exception e) {
//					// 處理其他潛在錯誤
//					e.printStackTrace();
//					req.setAttribute("error", "系統錯誤，請聯絡系統管理員。");
//					RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/errorPage.jsp");
//					dispatcher.forward(req, res);
//				}
//			}
//		}
//	}
//
//	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//		this.doPost(request, response);
//	}
//}
//
//
