package com.depthspace.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.depthspace.admin.model.AdminVO;
import com.depthspace.admin.service.HbAdminService;
import com.depthspace.member.model.MemVO;
import com.depthspace.member.service.HbMemService;

@WebServlet({ "/backadmin/*" })
public class BackAdminServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	private HbAdminService hbms;
	
	public void init() throws ServletException {
		hbms = new HbAdminService();
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
			case "/list"://使用者管理員列表(後台)
				doList(req, resp);
				break;
			case "/edit"://編輯管理員
				doEdit(req, resp);
				break;
			case "/modify"://判斷儲存的管理員是否有錯誤
				doModify(req, resp);
				break;
			
		}
		
		
	}

	private void doModify(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("成功跳轉");
		
	}

	private void doEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer adminId ;
		try {
			adminId =  Integer.valueOf(req.getParameter("adminId"));
		} catch (Exception e) {
            e.printStackTrace();
            return;
        }
		
		AdminVO adminvo = hbms.getOneAdmin(adminId);
		
		req.setAttribute("adminvo", adminvo);
        req.getRequestDispatcher("/adminEnd/adminEdit.jsp").forward(req, resp);

		
		
		
	}

	private void doList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<AdminVO> list = hbms.getAll();
		req.setAttribute("list", list);
		
		
		req.getRequestDispatcher("/adminEnd/adminEnd.jsp").forward(req, resp);
	}
	
	

}
