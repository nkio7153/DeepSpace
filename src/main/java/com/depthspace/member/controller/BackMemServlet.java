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
			case "/list"://使用者會員列表(後台)
				doList(req, resp);
				break;
			case "/edit"://編輯會員
				doEdit(req, resp);
				break;
			case "/modify"://判斷儲存的會員是否有錯誤
				doModify(req, resp);
				break;
			
		}
		
		
	}

	private void doModify(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("成功跳轉");
		
	}

	private void doEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer memId ;
		try {
			memId =  Integer.valueOf(req.getParameter("memId"));
		} catch (Exception e) {
            e.printStackTrace();
            return;
        }
		
		MemVO memvo = hbms.getOneMem(memId);
		
		req.setAttribute("memvo", memvo);
        req.getRequestDispatcher("/memberEnd/memEdit.jsp").forward(req, resp);

		
		
		
	}

	private void doList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<MemVO> list = hbms.getAll();
		req.setAttribute("list", list);
		
		
		req.getRequestDispatcher("/memberEnd/memberEnd.jsp").forward(req, resp);
	}
	
	

}
