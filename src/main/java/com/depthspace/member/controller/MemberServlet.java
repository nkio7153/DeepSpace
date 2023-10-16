package com.depthspace.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.depthspace.member.model.MemVO;
import com.depthspace.member.service.MemberService;

//@WebServlet({"/tsc/*"})
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		String pathInfo = req.getPathInfo();
//		
//		super.doGet(req, resp);
//	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		MemberService ms = new MemberService();
		List<MemVO> list = ms.getAll();
		boolean isAuthenticated = false;
		for (MemVO mem : list) {
			if (mem.getMemAcc().equals(username) && mem.getMemPwd().equals(password)) {
				isAuthenticated = true;
				break;
			}
		}

		if (isAuthenticated) {
			// 帳號和密碼匹配，執行登入成功後的動作
			// 這裡可以設置登入成功後的跳轉頁面或其他處理
			resp.sendRedirect("loginSuccess.jsp");
		} else {
			// 帳號和密碼不匹配，執行登入失敗後的動作
			// 這裡可以設置登入失敗後的跳轉頁面或其他處理
			resp.sendRedirect("loginFailure.jsp");
		}

	}
}
