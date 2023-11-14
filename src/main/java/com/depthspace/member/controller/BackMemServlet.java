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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
			case "/updateStatus"://更新帳號狀態
				doUpdateStatus(req, resp);
				break;
			case "/searchMembers"://模糊查詢會員姓名
				doSearchMembers(req, resp);
				break;
			
		}		
	}

	private void doSearchMembers(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String memName = req.getParameter("memName");
		List<MemVO> searchMembers = hbms.searchMembers(memName);
//		System.out.println("查到的資料=" + searchMembers);
		
		for (MemVO mem : searchMembers) {
		    if (mem.getMemPoint() == null) {
		        mem.setMemPoint(0); //如果點數為null就直接設定為0
		    }
		}
		
		setJsonResponse(resp , searchMembers);
	}

	private void doUpdateStatus(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Integer memId;
		String accStatus = req.getParameter("accStatus");
		try {
			memId =  Integer.valueOf(req.getParameter("memId"));
//			System.out.println("memId=" + memId + "memStatus=" + accStatus );
		} catch (Exception e) {
            e.printStackTrace();
            return;
        }
		byte status;
		if(accStatus.equals("啟用")) {
//			System.out.println("啟用成功");
			status = 1;
		} else {
			status = 2;
		}
		hbms.updateStatus(memId, status);
		
		MemVO memVo = hbms.getOneMem(memId);
		
		if(memVo.getAccStatus() == 1) {
			accStatus = "啟用";
		} else {
			accStatus = "停權";
		}		
		setJsonResponse(resp , accStatus);
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
	
	// fetch返回json格式
	private void setJsonResponse(HttpServletResponse resp, Object obj) throws IOException {
		Gson gson = new GsonBuilder()
				 .setDateFormat("yyyy-MM-dd") // 設定日期格式
	             .create();
		String jsonData = gson.toJson(obj);
//		System.out.println("jsonData=" + jsonData);
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().write(jsonData);
	}

}
