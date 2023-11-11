package com.depthspace.admin.model.filter;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;

import com.depthspace.admin.model.model.AdminVO;

@WebFilter("/admin")
public class LoginFilter1 implements Filter {

	private FilterConfig config;

	public void init(FilterConfig config) {
		this.config = config;
	}

	public void destroy() {
		config = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws ServletException, IOException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		// 【取得 session】
		HttpSession session = req.getSession();
		// 【從 session 判斷此user是否登入過】
		Object admin = session.getAttribute("adminVO");
		
		System.out.println((AdminVO)admin );
		if (admin  == null) {
			session.setAttribute("location", req.getRequestURI());
			res.sendRedirect(req.getContextPath() + "/admin/logout1.jsp");
			return;
		} else {
			chain.doFilter(request, response);
		}
	}
}