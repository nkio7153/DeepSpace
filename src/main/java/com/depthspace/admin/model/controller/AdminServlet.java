package com.depthspace.admin.model.controller;

import com.depthspace.admin.model.service.AdminService;
import com.depthspace.admin.model.service.AdminServiceImpl;

import com.depthspace.admin.model.model.AdminVO;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/login")
public class AdminServlet extends HttpServlet {

    private AdminService adminService;

    public void init() {
        adminService = new AdminServiceImpl();
    }

    @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		String forwardPath = "";
		switch (action) {
			case "getAll":
				forwardPath = getAllEmps(req, res);
				break;
//			case "compositeQuery":
//				forwardPath = getCompositeEmpsQuery(req, res);
//				break;
			default:
				forwardPath = "/index.jsp";
		}
		
		res.setContentType("text/html; charset=UTF-8");
		RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
		dispatcher.forward(req, res);
	}

	private String getAllEmps(HttpServletRequest req, HttpServletResponse res) {
		String page = req.getParameter("page");
		int currentPage = (page == null) ? 1 : Integer.parseInt(page);
		
		List<AdminVO> adminList = adminService.getAllAdmin(currentPage);

		if (req.getSession().getAttribute("adminPageQty") == null) {
			int empPageQty = adminService.getPageTotal();
			req.getSession().setAttribute("adminPageQty", empPageQty);
		}
		
		req.setAttribute("adminList", adminList);
		req.setAttribute("currentPage", currentPage);
		
		return "/emp/listAllEmps.jsp";
	}
	
//	private String getCompositeEmpsQuery(HttpServletRequest req, HttpServletResponse res) {
//		Map<String, String[]> map = req.getParameterMap();
//		if (map != null) {
//			List<AdminVO> empList = adminService.getEmpsByCompositeQuery(map);
//			req.setAttribute("adminList", adminList);
//		} else {
//			return "/index.jsp";
//		}
//		return "/emp/listCompositeQueryEmps.jsp";
//	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
}
