package com.depthspace.admin.model.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
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
		//前端的action對應到哪個 執行哪個
		switch (action) {
		case "add":
			processAdd(req, res);
			break;
		case "update":
			processUpdate(req, res);
			break;
		case "del":
			processDelete(req, res);
			break;
		case "query":
			processQuery(req, res);
			break;
		default:
			 res.sendError(HttpServletResponse.SC_BAD_REQUEST, "未知的操作");
		        break;
		}
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
	    try {
	        // 將String轉換為Integer
	        Integer adminId = Integer.parseInt(req.getParameter("ADMIN_ID"));
	        adminService.deleteAdmin(adminId);
	    } catch (NumberFormatException e) {
	        // 處理錯誤情況
	    	 res.sendError(HttpServletResponse.SC_BAD_REQUEST, "請求格式錯誤");
	        // 這裡可以添加錯誤處理邏輯，例如向用戶顯示錯誤信息
	    }
	}
	
	private void processUpdate(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	    AdminVO admin = new AdminVO();

	    String adminIdStr = req.getParameter("ADMIN_ID");
	    if (adminIdStr == null || adminIdStr.trim().isEmpty()) {
	        // 管理員ID為空或者格式不正確，返回錯誤響應
	        res.sendError(HttpServletResponse.SC_BAD_REQUEST, "ADMIN_ID不能為空");
	        return;
	    }

	    Integer adminId;
	    try {
	        adminId = Integer.parseInt(adminIdStr);
	    } catch (NumberFormatException e) {
	        // 管理員ID不是有效的整數，返回錯誤響應
	        res.sendError(HttpServletResponse.SC_BAD_REQUEST, "ADMIN_ID格式不正確");
	        return;
	    }
	    admin.setAdminId(adminId); // 現在可以安全地設置ADMIN_ID

	    // 從請求中獲取其他屬性並設置到AdminVO對象中
	    admin.setAdminName(req.getParameter("ADMIN_NAME"));
	    admin.setAdminAcc(req.getParameter("ADMIN_ACC"));
	    admin.setAdminPwd(req.getParameter("ADMIN_PWD"));

	    // 同樣的處理對於ADMIN_STATUS
	    Integer adminStatus;
	    try {
	        adminStatus = Integer.parseInt(req.getParameter("ADMIN_STATUS"));
	    } catch (NumberFormatException e) {
	        // ADMIN_STATUS不是數字，返回錯誤響應並中止處理
	        res.sendError(HttpServletResponse.SC_BAD_REQUEST, "ADMIN_STATUS格式不正確");
	        return;
	    }
	    admin.setAdminStatus(adminStatus);

	    // 呼叫service層的更新方法，進行數據持久化操作
	    try {
	        adminService.updateAdmin(admin); // 假設這個方法會將admin對象的變化保存到數據庫
	        // 更新成功後重定向到管理員列表頁面
	        res.sendRedirect(req.getContextPath() + "/admin/admin1.jsp");
	    } catch (Exception e) {
	        // 處理更新過程中出現的任何異常
	        e.printStackTrace();
	        // 返回內部服務器錯誤響應
	        res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "更新過程中發生錯誤");
	    }
	}



	private void processAdd(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		AdminVO admin = new AdminVO();

	    // 設置其他字串型別的屬性
	    admin.setAdminName(req.getParameter("ADMIN_NAME"));
	    admin.setAdminAcc(req.getParameter("ADMIN_ACC"));
	    admin.setAdminPwd(req.getParameter("ADMIN_PWD"));

	    try {
	        // 將String轉換為Integer，並設置AdminStatus
	        Integer adminStatus = Integer.parseInt(req.getParameter("ADMIN_STATUS"));
	        admin.setAdminStatus(adminStatus);
	    } catch (NumberFormatException e) {
	        // 如果ADMIN_STATUS不是數字，處理錯誤
	        e.printStackTrace();
	        // 在這裡添加錯誤處理邏輯，比如設置adminStatus為null或預設值
	    }

	    adminService.addAdmin(admin); // 假設這個方法會將 admin 對象保存到數據庫
	    res.sendRedirect(req.getContextPath() + "/admin/admin1.jsp");
	}

}