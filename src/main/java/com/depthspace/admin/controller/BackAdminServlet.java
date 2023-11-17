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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet( "/backadmin/*" )
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
			case "/updateStatus"://更新帳號狀態
				doUpdateStatus(req, resp);
				break;
			case "/searchAdmins"://模糊查詢會員姓名
				doSearchAdmins(req, resp);
				break;
		}
		
		
	}
	
	private void doSearchAdmins(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String adminName = req.getParameter("adminName");
		List<AdminVO> searchAdmins = hbms.searchAdmins(adminName);
//		System.out.println("查到的資料=" + searchMembers);
		
//		for (AdminVO admin : searchAdmins) {
//		    if (admin.getAdminPoint() == null) {
//		    	admin.setAdminPoint(0); //如果點數為null就直接設定為0
//		    }
//		}
		
		setJsonResponse(resp , searchAdmins);
	}

	private void doUpdateStatus(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Integer adminId;
		String adminStatus = req.getParameter("adminStatus");
		try {
			adminId =  Integer.valueOf(req.getParameter("adminId"));
		} catch (Exception e) {
            e.printStackTrace();
            return;
        }
		byte status;
		if(adminStatus.equals("啟用")) {
			status = 1;
		} else {
			status = 2;
		}
		hbms.updateStatus(adminId, status);
		
		AdminVO adminVo = hbms.getOneAdmin(adminId);
		
		if(adminVo.getAdminStatus() == 1) {
			adminStatus = "啟用";
		} else {
			adminStatus = "停權";
		}		
		setJsonResponse(resp , adminStatus);
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
	
	// fetch返回json格式
		private void setJsonResponse(HttpServletResponse resp, Object obj) throws IOException {
			Gson gson = new GsonBuilder()
					 .setDateFormat("yyyy-MM-dd") // 設定日期格式
		             .create();
			String jsonData = gson.toJson(obj);
//			System.out.println("jsonData=" + jsonData);
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			resp.getWriter().write(jsonData);
		}

}
