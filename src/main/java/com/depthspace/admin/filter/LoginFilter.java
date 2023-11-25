package com.depthspace.admin.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter{

	private FilterConfig config;

	public void init(FilterConfig config) {
		this.config = config;
	}

	public void destroy() {
		config = null;
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		// 【取得 session】
		HttpSession session = req.getSession();
		// 【從 session 取得adminId屬性】
		Object adminId = session.getAttribute("adminId");	
		System.out.println("adminId="+adminId);
		
		if (adminId == null) {//如果adminId是null，表示使用者未登入
			String requestURI = req.getRequestURI();
			String contextPath = req.getContextPath();
	        String location = requestURI.substring(contextPath.length());
			session.setAttribute("location", location);//將當前請求的URI存入Session中，以便稍後重定向後能夠知道用戶原來要訪問的頁面。
			System.out.println("非登入會員時，所在的location="+location);
			res.sendRedirect(req.getContextPath() + "/admin/login.jsp");
			return;//return表示不再執行過濾器鏈，即結束過濾器的執行。
		} else {
			chain.doFilter(request, response);
		}
		
	}

}
