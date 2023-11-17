package com.depthspace.admin.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.depthspace.admin.service.HbAdminService;
import com.depthspace.admin.model.AdminVO;
import com.depthspace.admin.service.AdminService;

@WebServlet({ "/ad/*" })
@MultipartConfig
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public int allowUser(String adminAcc, String password) {
		AdminVO adminvo = null;
		HbAdminService ms= new HbAdminService();
		AdminService admins= new AdminService();
		System.out.println("adminAcc=" + adminAcc);
		if(ms.findByAdminAcc(adminAcc) == null) {
			System.out.println("沒有此帳號");
			return 1;
		}
		
		else {
			adminvo=admins.getAdminInfo(adminAcc);
	    		System.out.println("2");
	    	}  
	    	
	    if (adminvo.getAdminAcc().equals(adminAcc) && adminvo.getAdminPwd().equals(password)) {
	       	System.out.println("成功登入");
	       	return 3;
	          
	    }else {
	      	System.out.println("密碼錯誤");
	       	return 4; 
	    	  }
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
			case "/login"://登入
				doLogin(req, resp);
				break;
			case "/logout"://登出
				doLogout(req, resp);
				break;
			case "/edit"://修改會員資料
				doEdit(req, resp);
				break;
			case "/modify"://儲存修改後的資料
				doModify(req, resp);
				break;
			case "/save"://新增會員
				doSave(req, resp);
				break;
		}
		
		
	}
	
	private void doLogout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession session=req.getSession();
		//移除session
				if(session.getAttribute("authenticatedAdmin") != null) {
					session.removeAttribute("authenticatedAdmin");
				}
				if(session.getAttribute("adminId") != null) {
					session.removeAttribute("adminId");
				}		
				if(session.getAttribute("mtoPageQty")!=null){
				    session.removeAttribute("mtoPageQty");
				}
				if(session.getAttribute("toAdminPageQty")!=null){
				    session.removeAttribute("toAdminPageQty");
				}

				resp.sendRedirect(req.getContextPath()+"/admin/login.jsp?state=logout");
				
	}


	// ============================================================================================================================================

	private void doSave(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);

		String st2 = null;
		String st3 = null;
		String st4 = null;
		Byte st5 = null;
		Byte st6 = null;
		Byte st7 = null;

		try {
			st2 = req.getParameter("adminAcc");
			if (st2 == null || st2.trim().length() == 0) {
				errorMsgs.add("帳號請勿空白");
			}

			st3 = req.getParameter("adminPwd");
			if (st3 == null || st3.trim().length() == 0) {
				errorMsgs.add("密碼請勿空白");
			}

			st4 = req.getParameter("adminName");
			if (st4 == null || st4.trim().length() == 0) {
				errorMsgs.add("姓名請勿空白");
			}

			String adminStatus = req.getParameter("adminStatus");
			System.out.println("adminStatus=" + adminStatus);
			st5 = Byte.parseByte(adminStatus);
			
			String adminVerifyStatus = req.getParameter("adminVerifyStatus");
			System.out.println("adminVerifyStatus=" + adminVerifyStatus);
			st6 = Byte.parseByte(adminVerifyStatus);
			
			String adminFuncName = req.getParameter("adminFuncName");
			System.out.println("adminFuncName=" + adminFuncName);
			st7 = Byte.parseByte(adminFuncName);
			
			//=======================================================================

			
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return;
		}
		//放入物件
		AdminService m = new AdminService();
		AdminVO adminvo = null;
		if (errorMsgs.isEmpty()) {
			adminvo = new AdminVO(st2, st3, st4, st5, st6, st7);
		}
		m.addAdmin(adminvo);
		
		//=======================================================================================
		
		//抓memId值讓修改頁面可以過
		AdminVO admin = new AdminVO();
		Integer adminId;
		List<AdminVO> a = m.getAll();
		int lastIndex = a.size() - 1; // 找到最後一個元素的索引
		admin = a.get(lastIndex); // 獲得最後一個元素的值
		adminId = admin.getAdminId();
		req.setAttribute("adminId", adminId);
		
		//=======================================================================================
		req.setAttribute("authenticatedAdmin", adminvo);
		
		//判斷帳戶
		if(st5 == 1 ) {
			req.setAttribute("status" , "正常使用中");
		} else {
			req.setAttribute("status", "此帳號停權");
		}
		
		if(st6 == 1 ) {
			req.setAttribute("verifyStatus" , "驗證完畢");
		} else {
			req.setAttribute("verifyStatus", "未驗證");
		}
		if(st7 == 2 ) {
			req.setAttribute("funcName", "總管理員");
		} else if(st7 == 1){
			req.setAttribute("funcName", "餐廳管理員");
		} else {
			req.setAttribute("funcName", "未啟用");
		}
		
		req.getRequestDispatcher("/admin/success.jsp").forward(req, resp);
	}
	
	// ============================================================================================================================================
	
	
	// ============================================================================================================================================

	private void doModify(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);

		Integer st1 = null;
		String st2 = null;
		String st3 = null;
		String st4 = null;
		Byte st5 = null;
		Byte st6 = null;
		Byte st7 = null;

		try {
			//多寫的，先暫時當二次判斷使用
			String adminId = req.getParameter("adminId");
			st1 = Integer.valueOf(adminId);

			st2 = req.getParameter("adminAcc");
			if (st2 == null || st2.trim().length() == 0) {
				errorMsgs.add("帳號請勿空白");
			}

			st3 = req.getParameter("adminPwd");
			if (st3 == null || st3.trim().length() == 0) {
				errorMsgs.add("密碼請勿空白");
			}

			st4 = req.getParameter("adminName");
			if (st4 == null || st4.trim().length() == 0) {
				errorMsgs.add("姓名請勿空白");
			}

			String adminStatus = req.getParameter("adminStatus");
			if ("正常使用中".equals(adminStatus)) {
				st5 = 1;
			} else {
				st5 = 2;
			}
			
			String adminVerifyStatus = req.getParameter("adminVerifyStatus");
			if ("已驗證".equals(adminVerifyStatus)) {
				st6 = 1;
			} else {
				st6 = 0;
			}
			
			String adminFuncName = req.getParameter("adminFuncName");
			if ("餐廳管理員".equals(adminFuncName)) {
				st7 = 1;
			} else if("總管理員".equals(adminFuncName)){
				st7 = 2;
			} else {
				st7 = 0;
			}
			
			AdminService ms = new AdminService();
			AdminVO admin = ms.findByAdminId(st1);
			//========================================================================
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return;
		}
		
		AdminService m = new AdminService();
		AdminVO adminvo = null;
		if (errorMsgs.isEmpty()) {
			adminvo = new AdminVO(st1, st2, st3, st4, st5, st6, st7);
		}
		m.updateAdmin(adminvo);
		req.setAttribute("authenticatedAdmin", adminvo);
		
		if(st5 == 1 ) {
			req.setAttribute("status" , "正常使用中");
		} else {
			req.setAttribute("status", "此帳號停權");
		}
		
		if(st6 == 1 ) {
			req.setAttribute("verifyStatus" , "驗證完畢");
		} else {
			req.setAttribute("verifyStatus", "未驗證");
		}
		if(st7 == 2 ) {
			req.setAttribute("funcName", "總管理員");
		} else if(st7 == 1){
			req.setAttribute("funcName", "餐廳管理員");
		} else {
			req.setAttribute("funcName", "未啟用");
		}
		
		req.getRequestDispatcher("/admin/success.jsp").forward(req, resp);
	}

	// ============================================================================================================================================

	private void doAddAdmin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	//加入會員資料收集
		System.out.println("有跳");
		req.getRequestDispatcher("/admin/success.jsp").forward(req, resp);
	}

	// ============================================================================================================================================

	private void doEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		AdminService m = new AdminService();
		Integer adminId = null;
		String st1 = req.getParameter("adminId");
		
		if (st1 != null && !st1.isEmpty()) {
		    try {
		    	adminId = Integer.valueOf(st1);
		        req.setAttribute("adminId", adminId);
		    } catch (NumberFormatException e) {
		        // 轉換失敗時的處理
		        e.printStackTrace();
		        return;
		    }
		} else {
			AdminVO admin = new AdminVO();
			
			List<AdminVO> a = m.getAll();
			int lastIndex = a.size() - 1; // 找到最後一個元素的索引
			admin = a.get(lastIndex); // 獲得最後一個元素的值
			adminId = admin.getAdminId();
			req.setAttribute("adminId", adminId);
		}
		
		//===================================================================================
		AdminVO adminvo = m.findByAdminId(adminId);
		if (adminvo != null) {
			//處理狀態
			byte accStatus = adminvo.getAdminStatus();
			if(accStatus == 1) {
				req.setAttribute("status" , "正常使用中");
			} else {
				req.setAttribute("status" , "此帳號停權");
			}

			req.setAttribute("admin", adminvo);
			req.getRequestDispatcher("/admin/revise.jsp").forward(req, resp);
		} else {
			System.out.println("失敗QQ");
		}
	}

	// ========================================================================================

	protected void doLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String adminAcc = req.getParameter("adminAcc");
		String password = req.getParameter("password");
		AdminService ms = new AdminService();
		HbAdminService hms = new HbAdminService();
		AdminVO adminvo = null;
		
		String loginLocation =req.getParameter("loginLocation");
		System.out.println("adminAcc="+adminAcc + "password="+ password);	
		
		if(allowUser(adminAcc,password)==1) {
			System.out.println("沒有此帳號");
			String URL=req.getContextPath()+"/admin/login.jsp?error=false&requestURI="+loginLocation;
			resp.sendRedirect(URL);
			return;
		} else {
		HttpSession session=req.getSession();
		
		AdminVO admin = ms.getAdminInfo(adminAcc);
		System.out.println("admin=" + admin);
//		String base64Image;
		if(admin.getAdminAcc().equals(adminAcc) && admin.getAdminPwd().equals(password)) {
				//設定狀態顯示
				byte adminStatus = admin.getAdminStatus();
				if(adminStatus == 1) {
					req.setAttribute("status" , "正常使用中");
				} else {
					req.setAttribute("status" , "此帳號停權");
				}
				System.out.println("servlet="+admin.getAdminId());
				    
				    
			session.setAttribute("admin", admin);//會員物件
			session.setAttribute("adminId", admin.getAdminId());//會員編號
			
			Integer adminno = (Integer) session.getAttribute("adminId");// 測試用(取得存在session會員編號)
//		    System.out.println("測試取得放入session的會員編號" + adminno);// 測試用
			
			req.getRequestDispatcher("/backend/backIndex/index.jsp").forward(req, resp);
		} else {
			String URL=req.getContextPath()+"/admin/login.jsp?error=true&requestURI="+loginLocation;
			resp.sendRedirect(URL);
			return;//程式中斷
		}
	
		}
	}
}