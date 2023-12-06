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
import com.depthspace.attractions.model.CityVO;
import com.depthspace.attractions.service.CityService;
import com.depthspace.ticketshoppingcart.service.RedisCartServiceImpl;
import com.depthspace.utils.JedisUtil;
import com.depthspace.utils.MailService;
import com.google.gson.Gson;

import redis.clients.jedis.Jedis;

import com.depthspace.admin.model.AdminVO;
import com.depthspace.admin.service.AdminService;

@WebServlet({ "/ad/*" })
@MultipartConfig
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private HbAdminService hbms;

	// 重寫init方法，用於初始化
	@Override
	public void init() throws ServletException {
		// 初始化HbAdminService的實體
		hbms = new HbAdminService();
	}

	// 定義一個允許用戶登入的方法，接受管理員帳號和密碼作為參數
	public int allowUser(String adminAcc, String password) {
		// 宣告一個AdminVO類型的變數，用於儲存查詢到的管理員資訊
		AdminVO adminvo = null;
		// 創建一個新的HbAdminService實例
		HbAdminService ms = new HbAdminService();
		// 創建一個新的AdminService實例
		AdminService admins = new AdminService();

		// 正則表達式檢查信箱格式
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
		Pattern pattern = Pattern.compile(emailRegex);
		// 使用剛定義的正則表達式來檢查傳入的adminAcc
		Matcher matcher = pattern.matcher(adminAcc);

		// 如果adminAcc不符合電子郵件格式
		if (!matcher.matches()) {
			System.out.println("帳號不符合信箱格式");
			return 6; // 返回一個特定的錯誤碼表示信箱格式錯誤
		}

		// 檢查是否存在該帳號
		if (ms.findByAdminAcc(adminAcc) == null) {
			System.out.println("沒有此帳號");
			return 1; // 帳號不存在
		}

		else {
			// 如果帳號存在，獲取該帳號的管理員資訊
			adminvo = admins.getAdminInfo(adminAcc);
			System.out.println("2");
		}

		 // 檢查儲存的密碼是否以特定格式開頭（鹽值加密）
		if (adminvo != null && adminvo.getAdminPwd() != null && adminvo.getAdminPwd().startsWith("$2a$")) {
			if (BCrypt.checkpw(password, adminvo.getAdminPwd())) {
				// 使用BCrypt檢查密碼
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
			return 7;  // 儲存的密碼鹽值格式不正確或其他錯誤
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 設置請求的字符編碼為UTF-8，以處理中文亂碼問題
		req.setCharacterEncoding("UTF-8");
		 // 設置回應的內容類型和編碼為HTML和UTF-8
		resp.setContentType("text/html;charset=UTF-8");
		// 獲取請求的路徑信息
		String pathInfo = req.getPathInfo();
		// 使用switch語句根據路徑信息執行不同的操作
		switch (pathInfo) {
		case "/login":// 登入
			doLogin(req, resp);
			break;
		case "/logout":// 登出
			doLogout(req, resp);
			break;
		case "/edit":// 修改管理員資料
			doEdit(req, resp);
			break;
		case "/modify":// 儲存修改後的資料
			doModify(req, resp);
			break;
		case "/save":// 新增管理員
			doSave(req, resp);
			break;
		case "/adminList":// 從首頁點擊我的管理員資料時
			doAdminList(req, resp);
			break;
		case "/forgetPassword":// 忘記密碼
			doForgetPassword(req, resp);
			break;
		case "/checkVerify":// 驗證碼
			doCheckVerify(req, resp);
			break;
		case "/checkAccount":// 確認重複帳號
			doCheckAccount(req, resp);
			break;
		case "/changePassword":// 變更密碼
			doChangePassword(req, resp);
			break;
		}
	}

	// 定義一個處理變更密碼請求的方法
	private void doChangePassword(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// 從請求中獲取現有的HTTP會話（session），若不存在則不創建新的會話
		HttpSession session = req.getSession(false);
		// 宣告一個整數變數用於存儲管理員ID
		Integer adminId = null;
		// 從會話中獲取管理員ID
		adminId = (Integer) session.getAttribute("adminId");
		// 通過ID獲取管理員的詳細資訊
		AdminVO adminvo = hbms.getOneAdmin(adminId);

		// 從請求中獲取更改後的密碼
		String changePassword = req.getParameter("changePassword");
		System.out.println("changePassword= " + changePassword);
		// 使用BCrypt進行密碼加密
		String hashedPassword = BCrypt.hashpw(changePassword, BCrypt.gensalt());

		// 設定管理員物件的密碼為新的加密後密碼
		adminvo.setAdminPwd(hashedPassword);
		// 更新管理員資料庫中的密碼資訊
		hbms.update(adminvo);

		// 從資料庫重新獲取管理員資訊以確保更新後的資訊被讀取
		AdminVO admin = hbms.getOneAdmin(adminId);
		System.out.println("admin= " + admin);

		// 根據管理員狀態設定會話屬性
		byte adminStatus = admin.getAdminStatus();
		if (adminStatus == 1) {
			session.setAttribute("status", "正常使用中");
		} else {
			session.setAttribute("status", "此帳號停權");
		}

		// 設定權限顯示
		byte adminFuncName = admin.getAdminFuncName();
		if (adminFuncName == 2) {
			session.setAttribute("status", "總管理員");
		} else if (adminFuncName == 1) {
			session.setAttribute("status", "餐廳管理員");
		}

		// 在會話中設定管理員物件和ID
		session.setAttribute("authenticatedAdmin", admin);// 管理員物件
		session.setAttribute("adminId", admin.getAdminId());// 管理員編號

		// 向客戶端發送成功響應
		resp.getWriter().write("success");
	}

	// 定義一個處理檢查帳號是否存在的方法
	private void doCheckAccount(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// 從請求中獲取提交的adminAcc參數
		String adminAcc = req.getParameter("adminAcc");
		System.out.println("adminAcc=" + adminAcc);
		// 創建一個HbAdminService的實例
		HbAdminService hbms = new HbAdminService();
		// 從HbAdminService中獲取所有管理員的列表
		List<AdminVO> list = hbms.getAll();
		// 定義一個布林變數用於標記帳號是否存在
		boolean account = false;

		// 遍歷管理員列表
		for (AdminVO adminVO : list) {
			// 獲取每個管理員的帳號
			String adminAllAcc = adminVO.getAdminAcc();

			// 檢查提交的帳號是否與列表中的某個帳號相同
			if (adminAcc != null && adminAcc.equals(adminAllAcc)) {
				account = true; // 若找到相同帳號，標記為true
				break; // 找到相符的帳號後即可跳出迴圈
			}

		}

		// 根據帳號是否存在來設定響應
		if (account) {
			String data = "false"; // 帳號存在，設定響應內容為"false"
			setJsonResponse(resp, data); // 設定JSON格式的響應
			System.out.println("帳號已存在，帳號不可使用");
		} else {
			String data = "true"; // 帳號不存在，設定響應內容為"true"
			setJsonResponse(resp, data);
			System.out.println("無此帳號，帳號可用");
		}
	}

	// 定義一個處理檢查驗證碼的方法
	private void doCheckVerify(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// 從請求中獲取提交的adminAcc和password參數
		String adminAcc = req.getParameter("adminAcc");
		String password = req.getParameter("password");

		// 使用Jedis連接到Redis伺服器
		Jedis jedis = new Jedis("localhost", 6379);
		jedis.select(13); // 切換到第13個資料庫，請確保這是你存放驗證碼的資料庫

		// 從Redis中獲取與adminAcc相對應的值
		String redisKey = jedis.get(adminAcc);
		// 進行驗證碼的比對
		if (redisKey != null && redisKey.equals(password)) {

			// 獲取或創建HTTP會話
			HttpSession session = req.getSession();
			AdminService ms = new AdminService();
			AdminVO admin = ms.getAdminInfo(adminAcc);
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
			if (adminFuncName == 2) {
				session.setAttribute("status", "總管理員");
			} else if (adminFuncName == 1) {
				session.setAttribute("status", "餐廳管理員");
			}

			// 在會話中設定管理員物件和ID
			session.setAttribute("admin", admin);// 管理員物件
			session.setAttribute("adminId", admin.getAdminId());// 管理員編號
			// ===================================================

			// 在這裡清除Redis中的驗證碼，因為已經使用過了
			jedis.del(adminAcc);
			jedis.close();

			// 向客戶端發送成功響應
			resp.getWriter().write("success");
		} else {
			// 如果驗證碼錯誤，向客戶端發送錯誤訊息
			resp.getWriter().write("error");
		}

	}

	// 定義一個處理忘記密碼請求的方法
	private void doForgetPassword(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// 從請求中獲取提交的adminAcc參數
		String adminAcc = req.getParameter("adminAcc");
//			System.out.println("adminAcc"+ adminAcc);
		HbAdminService hbms = new HbAdminService();
		// 使用adminAcc查詢管理員資料
		AdminVO admin = hbms.findByAdminAcc(adminAcc);
		
		// 檢查是否存在該管理員資料
		if (admin == null) {
			 // 如果管理員資料不存在，重定向到錯誤頁面
			String URL = req.getContextPath() + "/admin/forgetPassword.jsp?error=true";
			resp.sendRedirect(URL);
		} else if (admin.getAdminAcc().equals(adminAcc)) {
			
			// 獲取管理員帳號作為郵件接收者
			String to = admin.getAdminAcc();
			String subject = "DepthSpace會員密碼通知函";
			String ch_name = admin.getAdminName();
			// 生成隨機的臨時密碼
			String random = "1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
			String passRandom = "";
			for (int i = 1; i <= 8; i++) {
				int b = (int) (Math.random() * 62);
				passRandom = passRandom + random.charAt(b);
			}

			// 將臨時密碼加密
			String hashedPassword = BCrypt.hashpw(passRandom, BCrypt.gensalt());

			// 更新資料庫中的密碼
			admin.setAdminPwd(hashedPassword);
			hbms.update(admin); // 更新資料庫

			// 使用Jedis連接到Redis伺服器
			Jedis jedis = new Jedis("localhost", 6379);
			// 切換到第13個資料庫
			jedis.select(13);
			jedis.set(to, passRandom);
			jedis.expire(to, 600);

			jedis.close();

			// 寄到電子信箱
//				String messageText = "Hello! " + ch_name + " 請謹記此密碼: " + passRandom;
			// 使用MimeMultipart 將HTML放入MimeBodyPart中
			Multipart multipart = new MimeMultipart();
			MimeBodyPart bodyPart = new MimeBodyPart();
			// 將要發送的內容用HTML的格式
			StringBuffer msg = new StringBuffer();
			msg.append("Hello! " + ch_name + "您的臨時密碼為" + passRandom + "， 請謹記此密碼，並在10分鐘內驗證完畢。");
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

	// 處理顯示管理員列表的請求
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
		} else if (admin.getAdminFuncName() == 1) {
			req.setAttribute("status", "餐廳管理員");
		}
		req.setAttribute("authenticatedAdmin", admin);
		req.getRequestDispatcher("/admin/success.jsp").forward(req, resp);

	}

	private void doLogout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession session = req.getSession(false);
		// 移除session
		if (session.getAttribute("admin") != null) {
			session.removeAttribute("admin");
		}
		if (session.getAttribute("adminId") != null) {
			session.removeAttribute("adminId");
		}

		resp.sendRedirect(req.getContextPath() + "/admin/login.jsp");

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
			System.out.println("adminAcc: " + st2); // 應該是輸入的帳號
			if (st2 == null || st2.trim().length() < 16 || st2.trim().length() > 40) {
				errorMsgs.add("帳號長度必須在 6 到 30 個字元之間");
			} else {
				// 檢查信箱格式
				String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
				Pattern pattern = Pattern.compile(emailRegex);
				Matcher matcher = pattern.matcher(st2);
				if (!matcher.matches()) {
					errorMsgs.add("信箱格式不正確");
				} else {
					// 檢查帳號是否已存在
					AdminService adminService = new AdminService();
					if (adminService.getAdminInfo(st2) != null) {
						errorMsgs.add("帳號已存在");
					}
				}
			}

			st3 = req.getParameter("adminPwd");
			if (st3 == null || st3.trim().length() == 0) {
				errorMsgs.add("密碼請勿空白");
			} else {
				// 使用 bcrypt 進行密碼加密
				String hashedPassword = BCrypt.hashpw(st3, BCrypt.gensalt());
				st3 = hashedPassword;
			}

			st4 = req.getParameter("adminName");
			System.out.println("adminName: " + st4); // 應該是輸入的姓名
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

			// =======================================================================

		} catch (NumberFormatException e) {
			e.printStackTrace();
			return;
		}
		// 放入物件
		AdminService m = new AdminService();
		AdminVO adminvo = null;
		if (errorMsgs.isEmpty()) {
			adminvo = new AdminVO(st2, st3, st4, st5, st6, st7);

			m.addAdmin(adminvo);

			// =======================================================================================

			// 抓adminId值讓修改頁面可以過
			AdminVO admin = new AdminVO();
			Integer adminId;
			List<AdminVO> a = m.getAll();
			int lastIndex = a.size() - 1; // 找到最後一個元素的索引
			admin = a.get(lastIndex); // 獲得最後一個元素的值
			adminId = admin.getAdminId();
			req.setAttribute("adminId", adminId);

			// =======================================================================================
			req.setAttribute("authenticatedAdmin", adminvo);

			// 判斷帳戶
			if (st5 == 1) {
				req.setAttribute("status", "正常使用中");
			} else {
				req.setAttribute("status", "此帳號停權");
			}

			if (st6 == 1) {
				req.setAttribute("verifyStatus", "驗證完畢");
			} else {
				req.setAttribute("verifyStatus", "未驗證");
			}
			if (st7 == 2) {
				req.setAttribute("funcName", "總管理員");
			} else if (st7 == 1) {
				req.setAttribute("funcName", "餐廳管理員");
			}

			req.getRequestDispatcher("/backadmin/list").forward(req, resp);
		} else {
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
		AdminVO admin = null;

		try {
			// 多寫的，先暫時當二次判斷使用
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
			} else if ("1".equals(adminFuncName)) {
				st7 = 1;
			} else {
				st7 = 0;
			}

			AdminService ms = new AdminService();
			admin = ms.findByAdminId(st1);
			admin.setAdminName(st2);
			admin.setAdminPwd(st4);
			admin.setAdminFuncName(st7);
			// ========================================================================
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

		if (st5 == 1) {
			req.setAttribute("status", "正常使用中");
		} else {
			req.setAttribute("status", "此帳號停權");
		}

		if (st6 == 1) {
			req.setAttribute("verifyStatus", "驗證完畢");
		} else {
			req.setAttribute("verifyStatus", "未驗證");
		}
		if (st7 == 2) {
			req.setAttribute("funcName", "總管理員");
		} else if (st7 == 1) {
			req.setAttribute("funcName", "餐廳管理員");
		}

		req.getRequestDispatcher("/backadmin/list").forward(req, resp);
	}

	// ============================================================================================================================================

	private void doAddAdmin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 加入管理員資料收集
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

		// ===================================================================================
		AdminVO adminvo = m.findByAdminId(adminId);
		if (adminvo != null) {
			// 處理狀態
			byte accStatus = adminvo.getAdminStatus();
			byte verifyStatus = adminvo.getAdminVerifyStatus();
			byte funcName = adminvo.getAdminFuncName();

			if (accStatus == 1) {
				req.setAttribute("status", "正常使用中");
			} else {
				req.setAttribute("status", "此帳號停權");
			}
			if (verifyStatus == 1) {
				req.setAttribute("verifyStatus", "驗證完畢");
			} else {
				req.setAttribute("verifyStatus", "未驗證");
			}
			if (funcName == 2) {
				req.setAttribute("funcName", "總管理員");
			} else if (funcName == 1) {
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
		HttpSession session = req.getSession();
		String loginLocation = (String) session.getAttribute("location");
		System.out.println("loginLocation1:" + loginLocation);

		// 只調用一次allowUser
		int loginResult = allowUser(adminAcc, password);

		if (loginResult == 1) {
			System.out.println("沒有此帳號");
			resp.sendRedirect(req.getContextPath() + "/admin/login.jsp?error=false&requestURI=" + loginLocation);
		} else if (loginResult == 3) {
			AdminVO admin = ms.getAdminInfo(adminAcc);

			// 設定狀態顯示
			byte adminStatus = admin.getAdminStatus();
			if (adminStatus == 1) {
				req.setAttribute("status", "正常使用中");
			} else {
				req.setAttribute("status", "此帳號停權");
			}
			System.out.println("servlet=" + admin.getAdminId());

			session.setAttribute("admin", admin); // 管理員物件
			session.setAttribute("adminId", admin.getAdminId()); // 管理員編號
			
			if (loginLocation != null && !loginLocation.isEmpty()) {
				System.out.println("loginLocation:" + loginLocation);
				resp.sendRedirect("/DepthSpace/"+loginLocation);
			} else {
				req.getRequestDispatcher("/backend/backIndex/index.jsp").forward(req, resp);
				System.out.println(3);
			}
		} else if (loginResult == 4) {
			String URL = req.getContextPath() + "/admin/login.jsp?error=true&requestURI=" + loginLocation;
			resp.sendRedirect(URL);
		} else if (loginResult == 5) {
			String URL = req.getContextPath() + "/admin/login.jsp?error=nostatus&requestURI=" + loginLocation;
			resp.sendRedirect(URL);
		} else if (loginResult == 6) {
			String URL = req.getContextPath() + "/admin/login.jsp?error=noacc&requestURI=" + loginLocation;
			resp.sendRedirect(URL);
		} else if (loginResult == 7) {
			String URL = req.getContextPath() + "/admin/login.jsp?error=oldacc&requestURI=" + loginLocation;
			resp.sendRedirect(URL);
		}
	}

	// fetch返回json格式
	private void setJsonResponse(HttpServletResponse resp, Object obj) throws IOException {
		Gson gson = new Gson();
		String jsonData = gson.toJson(obj);
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().write(jsonData);
	}
}