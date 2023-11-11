package com.depthspace.admin.model.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.alibaba.fastjson2.JSONObject;
import com.depthspace.admin.model.model.AdminVO;
import com.depthspace.admin.model.service.AdminService;
import com.depthspace.admin.model.service.AdminServiceImpl;
import com.depthspace.forum.model.forumarticles.ForumArticlesVO;

@WebServlet("/admin.do")
@MultipartConfig
public class AdminServlet extends HttpServlet {
	// 一個 servlet 實體對應一個 service 實體
	private AdminService adminService;

	@Override
	public void init() throws ServletException {
		adminService = new AdminServiceImpl();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		String forwardPath = "";
		//前端的action對應到哪個 執行哪個
		switch (action) {
		case "add":
			processAdd(req, res);
			return;
		case "update":
			forwardPath = processUpdate(req, res);
			break;
		case "del":
			processDelete(req, res);
			return;
		case "query":
			processQuery(req, res);
			break;
		default:
			 res.sendError(HttpServletResponse.SC_BAD_REQUEST, "未知的操作");
		        break;
		}
		
		req.getRequestDispatcher(forwardPath).forward(req, res);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
	private void processQuery(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	    // 初始化adminId為null
	    Integer adminId = null;
	    try {
	        String adminIdStr = req.getParameter("ADMIN_ID");
	        // 先檢查字符串是否為空或null
	        if (adminIdStr != null && !adminIdStr.trim().isEmpty()) {
	            // 如果不是，則嘗試解析
	            adminId = Integer.parseInt(adminIdStr);
	        }
	        if (adminId != null) {
	            // 如果adminId有效，繼續後續邏輯
	            AdminVO admin = adminService.findAdminByAdminId(adminId);
	            res.setCharacterEncoding("UTF-8");
	            String str = JSONObject.toJSONString(admin);
	            PrintWriter out = res.getWriter();
	            out.write(str);
	        } else {
	            // 如果adminId無效，處理錯誤
	            // 這裡可以返回錯誤信息給前端
	        }
	    } catch (NumberFormatException e) {
	        // 如果捕獲到NumberFormatException，處理錯誤
	    	res.sendError(HttpServletResponse.SC_BAD_REQUEST, "請求格式錯誤");
	        // 同樣可以返回錯誤信息給前端
	    }
	}


	
	private void processDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	    String adminIdStr = req.getParameter("adminId");
	    
	    try {
	        Integer adminId = Integer.parseInt(adminIdStr);
	        adminService.deleteAdmin(adminId);
	        // 在執行成功的情況下，進行重定向或其他操作
	        String url = req.getContextPath()+"/admin/admin1.jsp";
			res.sendRedirect(url);// 這只是一個示例，你可以根據實際需求進行處理
	    } catch (NumberFormatException e) {
	        // 處理參數不是有效整數的情況
	        // 可以將錯誤信息放入request中，然後轉發到錯誤頁面
	        req.setAttribute("errorMessage", "ADMIN_ID必須是有效的整數");
	        req.getRequestDispatcher("error.jsp").forward(req, res); // 這只是一個示例，你可以根據實際需求進行處理
	    }
	}

	
	private String processUpdate(HttpServletRequest req, HttpServletResponse res){
	    AdminVO admin = new AdminVO();

	    int adminId = Integer.parseInt(req.getParameter("adminId"));
	    // 從請求中獲取其他屬性並設置到AdminVO對象中
	    String adminName = req.getParameter("adminName");
	    String adminAcc = req.getParameter("adminAcc");
	    String adminPwd = req.getParameter("adminPwd");
	    int adminStatus = Integer.parseInt(req.getParameter("adminStatus"));
	    
	    int result = adminService.updateAdmin(adminId, adminName, adminAcc, adminPwd, adminStatus);	

	    if (result > 0) {
			System.out.println("更新成功");
		} else {
			System.out.println("更新失敗");
		}
		
		res.setContentType("application/json;charset=UTF-8");
		return "/admin/admin1.jsp";
	    
	}



	private void processAdd(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
		AdminVO adminVO = new AdminVO();
		//把資料給前端
		 String adminIdStr = req.getParameter("ADMIN_ID");
		    if (adminIdStr != null && !adminIdStr.isEmpty()) {
		        Integer adminId = Integer.parseInt(adminIdStr);
		        adminVO.setAdminId(adminId);
		    }
		adminVO.setAdminName (req.getParameter("ADMIN_NAME"));
		adminVO.setAdminAcc (req.getParameter("ADMIN_ACC"));
		adminVO.setAdminPwd (req.getParameter("ADMIN_PWD"));
		
		// 將ADMIN_STATUS轉換為Integer
	    String adminStatusStr = req.getParameter("ADMIN_STATUS");
	    if (adminStatusStr != null && !adminStatusStr.isEmpty()) {
	        Integer adminStatus = Integer.parseInt(adminStatusStr);
	        adminVO.setAdminStatus(adminStatus);
	    }
		
	    adminService.addAdmin(adminVO);
	    
		//導到指定的URL 頁面上 把請求回應都帶過去
		String url = req.getContextPath()+"/admin/admin1.jsp";
		res.sendRedirect(url);
	}
}
