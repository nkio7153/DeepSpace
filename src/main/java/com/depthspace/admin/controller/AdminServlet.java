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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.mindrot.jbcrypt.BCrypt;

import com.depthspace.admin.service.HbAdminService;
import com.depthspace.member.model.MemVO;
import com.depthspace.member.service.HbMemService;
import com.depthspace.member.service.MemberService;
import com.depthspace.utils.MailService;

import redis.clients.jedis.Jedis;

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
		
		// 正則表達式檢查信箱格式
	    String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
	    Pattern pattern = Pattern.compile(emailRegex);
	    Matcher matcher = pattern.matcher(adminAcc);
		
	    if (!matcher.matches()) {
	        System.out.println("帳號不符合信箱格式");
	        return 6; // 返回一個特定的錯誤碼表示信箱格式錯誤
	    }
	    
		if(ms.findByAdminAcc(adminAcc) == null) {
			System.out.println("沒有此帳號");
			return 1;
		}
		
		else {
			adminvo=admins.getAdminInfo(adminAcc);
	    		System.out.println("2");
	    	}  
	    	
		 // 新增的密碼鹽值格式檢查
	    if (adminvo != null && adminvo.getAdminPwd() != null && adminvo.getAdminPwd().startsWith("$2a$")) {
	        if (BCrypt.checkpw(password, adminvo.getAdminPwd())) {
	            // 檢查帳號狀態
	            if (adminvo.getAdminStatus() == 2) {
	                return 5; // 帳號狀態問題
	            } else {
	                return 3; // 登入成功
	            }
	        } else {
	            System.out.println("密碼錯誤");
	            return 4; // 密碼錯誤
	        }
	    } else {
	        System.out.println("儲存的密碼鹽值格式不正確");
	        return 7; // 或其他錯誤碼
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
			case "/adminList":// 從首頁點擊我的會員資料時
				doAdminList(req, resp);
				break;
			case "/forgetPassword":// 忘記密碼
				doForgetPassword(req, resp);
				break;
			case "/checkVerify":// 驗證碼
				doCheckVerify(req, resp);
				break;
				
		}
		
	}
	
	private void doCheckVerify(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String adminAcc = req.getParameter("adminAcc");
		String password = req.getParameter("password");
		
		 // 在這裡應該要去Redis取得驗證碼的值，並進行比對
	    Jedis jedis = new Jedis("localhost", 6379);
	    jedis.select(13); // 切換到第13個資料庫，請確保這是你存放驗證碼的資料庫

	    String redisKey = jedis.get(adminAcc);
//	    System.out.println("redisKey= " + redisKey + "," + "password= "+ password);
	    if (redisKey != null && redisKey.equals(password)) {
	    	
	    	//===================================================
	    	HttpSession session = req.getSession();
	    	AdminService ms = new AdminService();
			AdminVO admin = ms.getAdminInfo(adminAcc);
//			System.out.println("mem=" + mem);
//			if (mem.getMemAcc().equals(memAcc) && mem.getMemPwd().equals(password)) {

			// 設定狀態顯示
			byte adminStatus = admin.getAdminStatus();
			if (adminStatus == 1) {
				session.setAttribute("status", "正常使用中");
			} else {
				session.setAttribute("status", "此帳號停權");
			}
			
			// 設定驗證碼狀態顯示
			byte adminVerifyStatus = admin.getAdminVerifyStatus();
			if (adminVerifyStatus == 1) {
				session.setAttribute("status", "已驗證");
			} else {
				session.setAttribute("status", "未驗證");
			}
			
			// 設定權限顯示
			byte adminFuncName = admin.getAdminFuncName();
			if (adminFuncName == 2){
	    	session.setAttribute("status", "總管理員");
			} else if (adminFuncName == 1) {
				session.setAttribute("status", "餐廳管理員");
			}
			

			session.setAttribute("admin", admin);// 會員物件
			session.setAttribute("adminId", admin.getAdminId());// 會員編號
	    	//===================================================
	    	
	        // 在這裡清除Redis中的驗證碼，因為已經使用過了
	        jedis.del(adminAcc);
	        jedis.close();

	        // 回傳成功訊息
	        resp.getWriter().write("success");
	    } else {
	        // 驗證碼錯誤，回傳錯誤訊息
	        resp.getWriter().write("error");
	    }
		
	}
	
	//用ajax傳遞請求
		private void doForgetPassword(HttpServletRequest req, HttpServletResponse resp) throws IOException {
			String adminAcc = req.getParameter("adminAcc");
//			System.out.println("adminAcc"+ adminAcc);
			HbAdminService hbms = new HbAdminService();
			AdminVO admin = hbms.findByAdminAcc(adminAcc);
			if(admin == null) {
				String URL = req.getContextPath() + "/admin/forgetPassword.jsp?error=true";
				resp.sendRedirect(URL);
			} else if(admin.getAdminAcc().equals(adminAcc)){
				String to = admin.getAdminAcc();
				String subject = "DepthSpace會員密碼通知函";
				String ch_name = admin.getAdminName();
				String random = "1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
				//生成驗證碼
				String passRandom = ""; 
				for(int i =1 ; i <= 8 ; i++) {
					int b =  (int)(Math.random()*62);
					passRandom = passRandom + random.charAt(b);
				}
				
				 // 將臨時密碼加密
		        String hashedPassword = BCrypt.hashpw(passRandom, BCrypt.gensalt());

		        // 更新資料庫中的密碼
		        admin.setAdminPwd(hashedPassword);
		        hbms.update(admin); // 假設這是更新資料庫的方法
			
//				System.out.println("驗證碼為:"+ passRandom);
//				存到Redis裡面
				Jedis jedis = new Jedis("localhost", 6379);
				// 切換到第13個資料庫
		        jedis.select(13);
		        jedis.set(to, passRandom);
		        jedis.expire(to, 600);
		        
		        jedis.close();
		        
		        //寄到電子信箱
//				String messageText = "Hello! " + ch_name + " 請謹記此密碼: " + passRandom;
				// 使用MimeMultipart 將HTML放入MimeBodyPart中
				Multipart multipart = new MimeMultipart();
				MimeBodyPart bodyPart = new MimeBodyPart();
				// 將要發送的內容用HTML的格式
				StringBuffer msg = new StringBuffer();
				msg.append("Hello! " + ch_name + "您的臨時密碼為" + passRandom + "， 請謹記此密碼，並在10分鐘內驗證完畢。" );
				try {
					bodyPart.setContent(msg.toString(), "text/html; charset=UTF-8");
					multipart.addBodyPart(bodyPart);
				} catch (MessagingException e) {
					e.printStackTrace();
				}
				
				MailService mailService = new MailService();
				try {
					mailService.sendMail(to, subject, multipart);
//					System.out.println("驗證碼已寄出");
				} catch (Exception e) {
					System.out.println("驗證碼寄出失敗");
				}
		        
		        resp.getWriter().write("success");
			} else {
				resp.getWriter().write("error");
			}
			
			
			
		}
		
		private void doAdminList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			Integer adminId = null;
			HttpSession session = req.getSession(false);
			adminId = (Integer) session.getAttribute("adminId");
			HbAdminService hbms = new HbAdminService();
			AdminVO admin = hbms.getOneAdmin(adminId);

			if (admin.getAdminStatus() == 1) {
				req.setAttribute("status", "正常使用中");
			} else {
				req.setAttribute("status", "此帳號停權");
			}

			if (admin.getAdminVerifyStatus() == 1) {
				req.setAttribute("status", "已驗證");
			} else {
				req.setAttribute("status", "未驗證");
			}

			if (admin.getAdminFuncName() == 2) {
				req.setAttribute("status", "總管理員");
			} else if (admin.getAdminFuncName() == 1){
				req.setAttribute("status", "餐廳管理員");
			} 
			req.setAttribute("authenticatedAdmin", admin);
			req.getRequestDispatcher("/admin/success.jsp").forward(req, resp);

		}
		
	
	
	
	private void doLogout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession session=req.getSession(false);
		//移除session
				if(session.getAttribute("admin") != null) {
					session.removeAttribute("admin");
				}
				if(session.getAttribute("adminId") != null) {
					session.removeAttribute("adminId");
				}		

				resp.sendRedirect(req.getContextPath()+"/admin/login.jsp");
				
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
			System.out.println("adminAcc: " + st2); // 应该是输入的帐号
			if (st2 == null || st2.trim().length() == 0) {
				errorMsgs.add("管理員信箱請勿空白");
			}else {
	            // 檢查信箱格式
	            String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
	            Pattern pattern = Pattern.compile(emailRegex);
	            Matcher matcher = pattern.matcher(st2);
	            if (!matcher.matches()) {
	                errorMsgs.add("信箱格式不正確");
	            }
	        }


			st3 = req.getParameter("adminPwd");
			if (st3 == null || st3.trim().length() == 0) {
				errorMsgs.add("密碼請勿空白");
			}else {
			    // 使用 bcrypt 進行密碼加密
			    String hashedPassword = BCrypt.hashpw(st3, BCrypt.gensalt());
			    st3 = hashedPassword;
			}

			st4 = req.getParameter("adminName");
			System.out.println("adminName: " + st4); // 应该是输入的姓名
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
		}
		
		req.getRequestDispatcher("/admin/login.jsp").forward(req, resp);
	}else {
		String revise = "請修正以下資訊";
		req.setAttribute("errorMsgs", errorMsgs);
		req.setAttribute("revise", revise);
		RequestDispatcher failureView = req.getRequestDispatcher("/admin/addAdmin.jsp");
		failureView.forward(req, resp);
	}
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
		AdminVO admin=null;

		try {
			//多寫的，先暫時當二次判斷使用
			String adminId = req.getParameter("adminId");
			st1 = Integer.valueOf(adminId);

			st2 = req.getParameter("adminName");
			if (st2 == null || st2.trim().length() == 0) {
				errorMsgs.add("姓名請勿空白");
			}

			st3 = req.getParameter("adminAcc");
			if (st3 == null || st3.trim().length() == 0) {
				errorMsgs.add("帳號請勿空白");
			}

			st4 = req.getParameter("adminPwd");
	        if (st4 != null && !st4.trim().isEmpty()) {
	            // 使用 bcrypt 進行密碼加密
	            String hashedPassword = BCrypt.hashpw(st4, BCrypt.gensalt());
	            st4 = hashedPassword;
	        } else {
	            errorMsgs.add("密碼請勿空白");
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
//			System.out.println(adminFuncName);
			if ("2".equals(adminFuncName)) {
				
				st7 = 2;
			} else if("1".equals(adminFuncName)){
				st7 = 1;
			} else {
				st7 = 0;
			}
			
			AdminService ms = new AdminService();
			admin = ms.findByAdminId(st1);
			admin.setAdminName(st2);
			admin.setAdminPwd(st4);
			admin.setAdminFuncName(st7);
			//========================================================================
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return;
		}
		
		AdminService m = new AdminService();
		AdminVO adminvo = null;
		if (errorMsgs.isEmpty()) {
//			adminvo = new AdminVO(st1, st2, st3, st4, st5, st6, st7);
			admin.setAdminName(st2);
			admin.setAdminPwd(st4);
			admin.setAdminFuncName(st7);
		}
//		m.updateAdmin(adminvo);
		m.updateAdmin(admin);
//		req.setAttribute("authenticatedAdmin", adminvo);
		req.setAttribute("authenticatedAdmin", admin);
		
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
		}
		
		req.getRequestDispatcher("/backadmin/list").forward(req, resp);
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
			byte verifyStatus = adminvo.getAdminVerifyStatus();
			byte funcName = adminvo.getAdminFuncName();
			
			if(accStatus == 1) {
				req.setAttribute("status" , "正常使用中");
			} else {
				req.setAttribute("status" , "此帳號停權");
			}
			if(verifyStatus == 1 ) {
				req.setAttribute("verifyStatus" , "驗證完畢");
			} else {
				req.setAttribute("verifyStatus", "未驗證");
			}
			if(funcName == 2 ) {
				req.setAttribute("funcName", "總管理員");
			} else if(funcName == 1){
				req.setAttribute("funcName", "餐廳管理員");
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
	    String loginLocation = req.getParameter("loginLocation");

	    // 只調用一次allowUser
	    int loginResult = allowUser(adminAcc, password);

	    if (loginResult == 1) {
	        System.out.println("沒有此帳號");
	        resp.sendRedirect(req.getContextPath() + "/admin/login.jsp?error=false&requestURI=" + loginLocation);
	    } else if (loginResult == 3) {
	        HttpSession session = req.getSession();
	        AdminVO admin = ms.getAdminInfo(adminAcc);

	        // 設定狀態顯示
	        byte adminStatus = admin.getAdminStatus();
	        if(adminStatus == 1) {
	            req.setAttribute("status" , "正常使用中");
	        } else {
	            req.setAttribute("status" , "此帳號停權");
	        }
	        System.out.println("servlet="+admin.getAdminId());

	        session.setAttribute("admin", admin); // 會員物件
	        session.setAttribute("adminId", admin.getAdminId()); // 會員編號

	        req.getRequestDispatcher("/backend/backIndex/index.jsp").forward(req, resp);
	    } else if (loginResult == 4) {
	        resp.sendRedirect(req.getContextPath() + "/admin/login.jsp?error=true&requestURI=" + loginLocation);
	    } else if (loginResult == 5) {
	        resp.sendRedirect(req.getContextPath() + "/admin/login.jsp?error=nostatus&requestURI=" + loginLocation);
	    } else if (loginResult == 6) {
	    	resp.sendRedirect(req.getContextPath() + "/admin/login.jsp?error=nostatus&requestURI=" + loginLocation);
	    } else if (loginResult == 7) {
	    	resp.sendRedirect(req.getContextPath() + "/admin/login.jsp?error=nostatus&requestURI=" + loginLocation);
	    }
	}

}