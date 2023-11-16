package com.depthspace.adminfunclist.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.depthspace.adminfunclist.model.AdminFuncListVO;
import com.depthspace.adminfunclist.service.AdminFuncListService;
import com.depthspace.adminfunclist.service.AdminFuncListServiceImpl;
import com.depthspace.forum.model.articlescollect.ArticlesCollectVO;
import com.depthspace.forum.model.articlescollect.service.ArticlesCollectServiceImpl;

@WebServlet("/AdminFuncListServlet")
public class AdminFuncListServlet extends HttpServlet {
	private AdminFuncListService adminFuncListService;

	@Override
	public void init() throws ServletException {
		adminFuncListService = new AdminFuncListServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		if (action == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "未指定操作");
            return;
        }
        switch (action) {
            case "add":
                addAdminFuncList(req, resp);
                break;
            // 可以添加更多操作，如 "list", "update", "delete" 等
            default:
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "不支持的操作");
        }
    }
	
	
	private void addAdminFuncList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		 Integer adminId = null, funcId = null;
	        try {
	            funcId = Integer.valueOf(req.getParameter("funcId"));
	            HttpSession session = req.getSession(false);
	            if (session != null && session.getAttribute("adminId") != null) {
	                adminId = (Integer) session.getAttribute("adminId");
	            }
	            if (adminId == null || funcId == null) {
	                throw new IllegalArgumentException("管理員 ID 或功能 ID 缺失");
	            }
	            // 檢查用戶是否有權限進行此操作
	            if (!isAdminUser(session)) {
	                resp.sendError(HttpServletResponse.SC_FORBIDDEN, "無權限進行此操作");
	                return;
	            }
	            // 檢查是否已有該功能
	            boolean hasPermission = adminFuncListService.hasPermission(adminId, funcId);
	            AdminFuncListVO acvo = new AdminFuncListVO(adminId, funcId);
	            if (hasPermission) {
	                adminFuncListService.delete(acvo);
	                resp.sendRedirect("somePage.jsp?message=功能已刪除");
	            } else {
	                adminFuncListService.insert(acvo);
	                resp.sendRedirect("somePage.jsp?message=功能已添加");
	            }
	        } catch (NumberFormatException e) {
	            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "無效的數字格式");
	        } catch (IllegalArgumentException e) {
	            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
	        } catch (Exception e) {
	            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "伺服器錯誤");
	        }
	    }
	
	private boolean isAdminUser(HttpSession session) {
        // 根據您的應用邏輯實現管理員身份驗證
        return session != null && "admin".equals(session.getAttribute("userRole"));
    }
}
