package com.depthspace.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.depthspace.member.model.MemVO;
import com.depthspace.member.service.HbMemService;

@WebServlet({ "/backmem/*" })
public class BackMemServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	private HbMemService hbms;
	
	public void init() throws ServletException {
		hbms = new HbMemService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		String pathInfo = req.getPathInfo();
		switch (pathInfo) {
			case "/list"://登入
				doList(req, resp);
				break;
			
		}
		
		
	}

	private void doList(HttpServletRequest req, HttpServletResponse resp) {
		List<MemVO> list = hbms.getAll();
		System.out.println("list=" + list);
		req.setAttribute("list", list);
	}
	
	

}
