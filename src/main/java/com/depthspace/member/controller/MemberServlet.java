package com.depthspace.member.controller;

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

import com.depthspace.member.model.MemVO;
import com.depthspace.member.service.HbMemService;
import com.depthspace.member.service.MemberService;

@WebServlet({ "/mem/*" })
@MultipartConfig
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public int allowUser(String memAcc, String password) {
		MemVO memvo = null;
		HbMemService hbms = new HbMemService();
		MemberService mems = new MemberService();
		System.out.println("memAcc=" + memAcc);
		if (hbms.findByMemAcc(memAcc) == null) {
			System.out.println("沒有此帳號");
			return 1;
		}

		else {
			memvo = mems.getMemberInfo(memAcc);
//	    		System.out.println("2");
		}

		if (memvo.getMemAcc().equals(memAcc) && memvo.getMemPwd().equals(password)) {
//	       	System.out.println("成功登入");
			return 3;

		} else {
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
		case "/login":// 登入
			doLogin(req, resp);
			break;
		case "/logout":// 登出
			doLogout(req, resp);
			break;
		case "/edit":// 修改會員資料
			doEdit(req, resp);
			break;
		case "/modify":// 儲存修改後的資料
			doModify(req, resp);
			break;
		case "/save":// 註冊會員
			doSave(req, resp);
			break;
		case "/memList":// 從首頁點擊我的會員資料時
			doMemList(req, resp);
			break;

		}

	}

	private void doMemList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer memId = null;
		HttpSession session = req.getSession(false);
		memId = (Integer) session.getAttribute("memId");
		HbMemService hbms = new HbMemService();
		MemVO mem = hbms.getOneMem(memId);
		
		//處理圖片
		String base64Image;
		byte[] imageBytes = mem.getMemImage();
		if (imageBytes != null) {
			base64Image = Base64.getEncoder().encodeToString(imageBytes);
			req.setAttribute("base64Image", base64Image);
		} else {
			String webappPath = getServletContext().getRealPath("/");
			// 取得相對路径
			String relativeImagePath = "member/images/1.png";
			String absoluteImagePath = webappPath + relativeImagePath;

			File defaultImageFile = new File(absoluteImagePath);
			String defaultImagePath = defaultImageFile.getPath();
			// 使用ServletContext获取资源流
//			InputStream defaultImageStream = getServletContext().getResourceAsStream(defaultImagePath);
			if (defaultImageFile.exists()) {
				byte[] localImageBytes = Files.readAllBytes(Path.of(defaultImagePath));
				base64Image = Base64.getEncoder().encodeToString(localImageBytes);

				resp.setContentType("text/plain");
				resp.getWriter().write(base64Image);
				req.setAttribute("base64Image", base64Image);
			} else {
				// 如無照片會處理錯誤
				System.out.println("圖不存在");
			}
		}
		
		if (mem.getMemSex() == 1) {
			req.setAttribute("sex", "男");
		} else {
			req.setAttribute("sex", "女");
		}

		if (mem.getAccStatus() == 1) {
			req.setAttribute("status", "正常使用中");
		} else {
			req.setAttribute("status", "此帳號停權");
		}

		req.setAttribute("authenticatedMem", mem);
		req.getRequestDispatcher("/member/success.jsp").forward(req, resp);

	}

	private void doLogout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession session = req.getSession();
		// 移除session
		if (session.getAttribute("authenticatedMem") != null) {
			session.removeAttribute("authenticatedMem");
		}
		if (session.getAttribute("memId") != null) {
			session.removeAttribute("memId");
		}
		if (session.getAttribute("mtoPageQty") != null) {
			session.removeAttribute("mtoPageQty");
		}
		if (session.getAttribute("toMemPageQty") != null) {
			session.removeAttribute("toMemPageQty");
		}

//		Integer memno = (Integer) session.getAttribute("memId");// 測試用(取得存在session會員編號)
//	    System.out.println("測試取得放入session的會員編號" + memno);
		resp.sendRedirect(req.getContextPath() + "/indexpage/index.jsp?state=logout");

	}

	// ============================================================================================================================================
	// 註冊
	private void doSave(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);

		String st2 = null;
		String st3 = null;
		String st4 = null;
		String st5 = null;
		java.sql.Date st6 = null;
		Byte st7 = null;
		String st8 = null;
		Integer st9 = null;
		String st10 = null;
		Byte st11 = null;
		String base64Image;
		byte[] byteArray = null;

		try {
			st2 = req.getParameter("memAcc");
			if (st2 == null || st2.trim().length() == 0) {
				errorMsgs.add("帳號請勿空白");
			}

			st3 = req.getParameter("memPwd");
			if (st3 == null || st3.trim().length() == 0) {
				errorMsgs.add("密碼請勿空白");
			}

			st4 = req.getParameter("memName");
			if (st4 == null || st4.trim().length() == 0) {
				errorMsgs.add("姓名請勿空白");
			}

			st5 = req.getParameter("memIdentity");
			if ((st5 == null || st5.trim().length() == 0) && st5.trim().length() != 10) {
				errorMsgs.add("身分證請勿空白或不等於10位數");
			}

			String memBth = req.getParameter("memBth");
			if (memBth != null && !memBth.isEmpty()) {
				try {
					st6 = java.sql.Date.valueOf(memBth);
				} catch (IllegalArgumentException e) {
					errorMsgs.add("生日請勿空白");
				}
			}

			String memSex = req.getParameter("memSex");
			st7 = Byte.parseByte(memSex);

			st8 = req.getParameter("memEmail");
			if (st8 == null || st8.trim().length() == 0) {
				errorMsgs.add("Email請勿空白");
			}

			String memTel = req.getParameter("memTel");
			st9 = Integer.valueOf(memTel);
			if (st9 == null) {
				errorMsgs.add("電話請勿空白");
			}

			st10 = req.getParameter("memAdd");
			if (st10 == null || st10.trim().length() == 0) {
				errorMsgs.add("地址請勿空白");
			}
			String accStatus = req.getParameter("accStatus");
			st11 = Byte.parseByte(accStatus);

			// 抓取上傳資料並且做base64的轉型
			Part filePart = req.getPart("memImage");
			if (filePart != null && filePart.getSize() > 0) {
				InputStream inputStream = filePart.getInputStream();
				ByteArrayOutputStream buffer = new ByteArrayOutputStream();
				int nRead;
				byte[] data = new byte[1024];
				while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
					buffer.write(data, 0, nRead);
				}
				buffer.flush();
				byteArray = buffer.toByteArray();
				inputStream.close();
				buffer.close();
			} else {
				String webappPath = getServletContext().getRealPath("/");
				// 构建相對路径
				String relativeImagePath = "member/images/1.png";
				String absoluteImagePath = webappPath + relativeImagePath;

				File defaultImageFile = new File(absoluteImagePath);
				String defaultImagePath = defaultImageFile.getPath();
				// 使用ServletContext獲取資源流
//				InputStream defaultImageStream = getServletContext().getResourceAsStream(defaultImagePath);
				if (defaultImageFile.exists()) {
					byte[] localImageBytes = Files.readAllBytes(Path.of(defaultImagePath));
					base64Image = Base64.getEncoder().encodeToString(localImageBytes);

					resp.setContentType("text/plain");
					resp.getWriter().write(base64Image);
					req.setAttribute("base64Image", base64Image);
				} else {
					// 如無照片會處理錯誤
					System.out.println("圖不存在");
				}
			}

			// =======================================================================
//			InputStream inputStream = filePart.getInputStream();
//	        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
//	        int nRead;
//	        byte[] data = new byte[1024];
//	        while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
//	            buffer.write(data, 0, nRead);
//	        }
//	        buffer.flush();
//	        byteArray = buffer.toByteArray();
//	        inputStream.close();
//	        buffer.close();

		} catch (NumberFormatException e) {
			e.printStackTrace();
			return;
		}

		// 放入物件
		MemberService m = new MemberService();
		MemVO memvo = null;
		if (errorMsgs.isEmpty()) {
			memvo = new MemVO(st2, st3, st4, st5, st6, st7, st8, st9, st10, st11, byteArray);

			m.addMember(memvo);
			// =======================================================================================

			// 抓memId值讓修改頁面可以過
			MemVO mem = new MemVO();
			Integer memId;
			String email = memvo.getMemEmail();
//		System.out.println("email=" + email);
			List<MemVO> a = m.getAll();
			int lastIndex = a.size() - 1; // 找到最後一個元素的索引
			mem = a.get(lastIndex); // 獲得最後一個元素的值
//		System.out.println("mem=" + mem);
			memId = mem.getMemId();
//		System.out.println(memId);
			req.setAttribute("memId", memId);
//		authenticatedMem.setMemId(mem.getMemId());
//		try {
//			memId = mem.getMemId();
//			System.out.println(memId);
//		} catch (Exception e1) {
//			e1.printStackTrace();
//			return;
//		}

			// =======================================================================================
			req.setAttribute("authenticatedMem", memvo);

			// 判斷是否要給預設圖
			if (byteArray != null) {
				String base64Image2 = Base64.getEncoder().encodeToString(byteArray);
				req.setAttribute("base64Image", base64Image2);
			} else {// 讓他保持預設圖
			}

			// 判斷男女
			if (st7 == 1) {
				req.setAttribute("sex", "男");
			} else {
				req.setAttribute("sex", "女");
			}

			// 判斷帳戶
			if (st11 == 1) {
				req.setAttribute("status", "正常使用中");
			} else {
				req.setAttribute("status", "此帳號停權");
			}

			req.getRequestDispatcher("/member/login.jsp").forward(req, resp);
		} else {
			String revise = "請修正以下資訊";
			req.setAttribute("errorMsgs", errorMsgs);
			req.setAttribute("revise", revise);
			RequestDispatcher failureView = req.getRequestDispatcher("/member/addmember.jsp");
			failureView.forward(req, resp);
		}
	}
	// 儲存修改後的資料
	private void doModify(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);

		Integer st1 = null;
		String st2 = null;
		String st3 = null;
		String st4 = null;
		String st5 = null;
		java.sql.Date st6 = null;
		Byte st7 = null;
		String st8 = null;
		Integer st9 = null;
		String st10 = null;
		Byte st11 = null;
		Integer st12 = null;
		byte[] st13 = null;

		try {
			// 多寫的，先暫時當二次判斷使用
			String memId = req.getParameter("memId");
			st1 = Integer.valueOf(memId);
//			System.out.println("st1=" + st1);

			st2 = req.getParameter("memAcc");
			if (st2 == null || st2.trim().length() == 0) {
				errorMsgs.add("帳號請勿空白");
			}

			st3 = req.getParameter("memPwd");
			if (st3 == null || st3.trim().length() == 0) {
				errorMsgs.add("密碼請勿空白");
			}

			st4 = req.getParameter("memName");
			if (st4 == null || st4.trim().length() == 0) {
				errorMsgs.add("姓名請勿空白");
			}

			st5 = req.getParameter("memIdentity");
			if (st5 == null || st5.trim().length() == 0) {
				errorMsgs.add("身分證請勿空白");
			}

			st6 = null;
			String memBth = req.getParameter("memBth");
			if (memBth != null && !memBth.isEmpty()) {
				try {
					st6 = java.sql.Date.valueOf(memBth);
				} catch (IllegalArgumentException e) {
					errorMsgs.add("生日請勿空白");
				}
			}

			String memSex = req.getParameter("memSex");
			if ("男".equals(memSex)) {
				st7 = 1; // 如果 memSex 是 "男"，則將 memSexByte 設置為 1
			} else {
				st7 = 2; // 否則，將 memSexByte 設置為 2
			}

			st8 = req.getParameter("memEmail");
			if (st8 == null || st8.trim().length() == 0) {
				errorMsgs.add("Email請勿空白");
			}

			String memTel = req.getParameter("memTel");
			st9 = Integer.valueOf(memTel);
			if (st9 == null) {
				errorMsgs.add("電話請勿空白");
			}

			st10 = req.getParameter("memAdd");
			if (st10 == null || st10.trim().length() == 0) {
				errorMsgs.add("地址請勿空白");
			}
			String accStatus = req.getParameter("accStatus");
			if ("正常使用中".equals(accStatus)) {
				st11 = 1;
			} else {
				st11 = 2;
			}

			MemberService ms = new MemberService();
			MemVO mem = ms.findByMemId(st1);
			st12 = mem.getMemPoint();

			// ========================================================================
//			String memImage = req.getParameter("memImage");
//			轉型成byte[]陣列
//			st13 = memImage.getBytes();
//			System.out.println("Bytes64=" + st13);
//			解析byte[]變成Base64字串並存在st13裡
//			st13 = Base64.getDecoder().decode(memImage);
//			System.out.println("Base64=" + st13);
			// ========================================================================

//			修改圖片
			Part filePart = req.getPart("memImage");
//			System.out.println("filePart=" + filePart);
			if (filePart != null && filePart.getSize() > 0) {
				InputStream inputStream = filePart.getInputStream();
				st13 = new byte[inputStream.available()];
				inputStream.read(st13);
				inputStream.close();
			} else {
				String memImage = req.getParameter("memImage");
//				轉型成byte[]陣列
				st13 = memImage.getBytes();
//				System.out.println("Bytes64=" + st13);
//				解析byte[]變成Base64字串並存在st13裡
				st13 = Base64.getDecoder().decode(memImage);
			}

//	        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
//	        int nRead;
//	        byte[] data = new byte[1024];
//	        while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
//	            buffer.write(data, 0, nRead);
//	        }
//	        buffer.flush();
//	        st13 = buffer.toByteArray();
//	        System.out.println("st13=" + st13);
//	        System.out.println("st13.length=" + st13.length);
//	        inputStream.close();
//	        buffer.close();

		} catch (NumberFormatException e) {
			e.printStackTrace();
			return;
		}

		MemberService m = new MemberService();
		MemVO memvo = null;
		if (errorMsgs.isEmpty()) {
			memvo = new MemVO(st1, st2, st3, st4, st5, st6, st7, st8, st9, st10, st11, st12, st13);
		}
		m.updateMember(memvo);
		req.setAttribute("authenticatedMem", memvo);

//		st13 = mem.getMemImage();
//		String base64Image = Base64.getEncoder().encodeToString(st13);
//		System.out.println("base64Image=" + base64Image);
//		req.setAttribute("base64Image", base64Image);

//		byte[] imageBytes = memvo.getMemImage();
//		System.out.println("byte[]："+imageBytes);
//		String base64Image = Base64.getEncoder().encodeToString(imageBytes);
//		req.setAttribute("base64Image", base64Image);
//		req.setAttribute("imageBytes", imageBytes);

		String base64Image = Base64.getEncoder().encodeToString(st13);
		req.setAttribute("base64Image", base64Image);

		if (st7 == 1) {
			req.setAttribute("sex", "男");
		} else {
			req.setAttribute("sex", "女");
		}

		if (st11 == 1) {
			req.setAttribute("status", "正常使用中");
		} else {
			req.setAttribute("status", "此帳號停權");
		}

		req.getRequestDispatcher("/member/success.jsp").forward(req, resp);
	}

	// ============================================================================================================================================

	private void doAddMember(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 加入會員資料收集
//		System.out.println("有跳");
		req.getRequestDispatcher("/member/success.jsp").forward(req, resp);
	}

	// ============================================================================================================================================

	private void doEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MemberService m = new MemberService();
		Integer memId = null;
		String st1 = req.getParameter("memId");

		if (st1 != null && !st1.isEmpty()) {
			try {
				memId = Integer.valueOf(st1);
				req.setAttribute("memId", memId);
			} catch (NumberFormatException e) {
				// 轉換失敗時的處理
				e.printStackTrace();
				return;
			}
		} else {
			MemVO mem = new MemVO();

			List<MemVO> a = m.getAll();
			int lastIndex = a.size() - 1; // 找到最後一個元素的索引
			mem = a.get(lastIndex); // 獲得最後一個元素的值
			memId = mem.getMemId();
			req.setAttribute("memId", memId);
		}

		MemVO memvo = m.findByMemId(memId);
		if (memvo != null) {
			// 處理圖片
			byte[] imageBytes = memvo.getMemImage();
			if (imageBytes != null) {
				String base64Image = Base64.getEncoder().encodeToString(imageBytes);
				req.setAttribute("imageBytes", imageBytes);
				req.setAttribute("base64Image", base64Image);
			} else {
				req.setAttribute("base64Image", null); // 如果 memImage 為 null，設定 base64Image 為 null
			}
			// 處理性別
			byte memSex = memvo.getMemSex();
			if (memSex == 1) {
				req.setAttribute("sex", "男");
			} else {
				req.setAttribute("sex", "女");
			}

			// 處理狀態
			byte accStatus = memvo.getAccStatus();
			if (accStatus == 1) {
				req.setAttribute("status", "正常使用中");
			} else {
				req.setAttribute("status", "此帳號停權");
			}

			req.setAttribute("mem", memvo);
			req.getRequestDispatcher("/member/revise.jsp").forward(req, resp);
		} else {
			System.out.println("失敗QQ");
		}
	}

	// ========================================================================================

	protected void doLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String memAcc = req.getParameter("memAcc");
		String password = req.getParameter("password");
		MemberService ms = new MemberService();
		HbMemService hms = new HbMemService();
		MemVO memVo = null;

		String loginLocation = req.getParameter("loginLocation");
		System.out.println("loginLocation=" + loginLocation);
//		System.out.println("存session成功"+ "memAcc= " + memAcc );

		if (allowUser(memAcc, password) == 1) {
			System.out.println("沒有此帳號");
			String URL = req.getContextPath() + "/member/login.jsp?error=false&requestURI=" + loginLocation;
			resp.sendRedirect(URL);
			return;
		} else {
			HttpSession session = req.getSession();

			MemVO mem = ms.getMemberInfo(memAcc);
//		System.out.println("mem=" + mem);
			String base64Image;
			if (mem.getMemAcc().equals(memAcc) && mem.getMemPwd().equals(password)) {
				// 創建一個 MemVO 物件並設定它的屬性
//			MemVO authenticatedMem = new MemVO();

//			authenticatedMem.setMemId(mem.getMemId());
//			authenticatedMem.setMemAcc(mem.getMemAcc());
//			authenticatedMem.setMemPwd(mem.getMemPwd());
//			authenticatedMem.setMemName(mem.getMemName());
//			authenticatedMem.setMemIdentity(mem.getMemIdentity());
//			authenticatedMem.setMemBth(mem.getMemBth());
//			authenticatedMem.setMemSex(mem.getMemSex());
//			authenticatedMem.setMemEmail(mem.getMemEmail());
//			authenticatedMem.setMemTel(mem.getMemTel());
//			authenticatedMem.setMemAdd(mem.getMemAdd());
//			authenticatedMem.setAccStatus(mem.getAccStatus());
//			authenticatedMem.setMemPoint(mem.getMemPoint());
				byte[] imageBytes = mem.getMemImage();
				if (imageBytes != null) {
					base64Image = Base64.getEncoder().encodeToString(imageBytes);
					req.setAttribute("base64Image", base64Image);
				} else {
					String webappPath = getServletContext().getRealPath("/");
					// 取得相對路径
					String relativeImagePath = "member/images/1.png";
					String absoluteImagePath = webappPath + relativeImagePath;

					File defaultImageFile = new File(absoluteImagePath);
					String defaultImagePath = defaultImageFile.getPath();
					// 使用ServletContext获取资源流
//					InputStream defaultImageStream = getServletContext().getResourceAsStream(defaultImagePath);
					if (defaultImageFile.exists()) {
						byte[] localImageBytes = Files.readAllBytes(Path.of(defaultImagePath));
						base64Image = Base64.getEncoder().encodeToString(localImageBytes);

						resp.setContentType("text/plain");
						resp.getWriter().write(base64Image);
						req.setAttribute("base64Image", base64Image);
					} else {
						// 如無照片會處理錯誤
						System.out.println("圖不存在");
					}
				}

				// 設定男女顯示
				byte memSexBytes = mem.getMemSex();
				if (memSexBytes == 1) {
					req.setAttribute("sex", "男");
				} else if (memSexBytes == 2) {
					req.setAttribute("sex", "女");
				}
				// 設定狀態顯示
				byte accStatus = mem.getAccStatus();
				if (accStatus == 1) {
					req.setAttribute("status", "正常使用中");
				} else {
					req.setAttribute("status", "此帳號停權");
				}

				session.setAttribute("authenticatedMem", mem);// 會員物件
				session.setAttribute("memId", mem.getMemId());// 會員編號

				Integer memno = (Integer) session.getAttribute("memId");// 測試用(取得存在session會員編號)
//		    System.out.println("測試取得放入session的會員編號" + memno);// 測試用

				req.getRequestDispatcher("/member/success.jsp").forward(req, resp);
//			resp.sendRedirect(req.getContextPath()+"/indexpage/index.jsp");

			} else {
//			System.out.println("帳號密碼錯誤");
//			String errorMsgs = "帳號或密碼錯誤";
//			req.setAttribute("errorMsgs", errorMsgs);
//			RequestDispatcher failureView = req.getRequestDispatcher("/member/login.jsp");
//			failureView.forward(req, resp);
				String URL = req.getContextPath() + "/member/login.jsp?error=true&requestURI=" + loginLocation;
				resp.sendRedirect(URL);
				return;// 程式中斷
			}

		}
	}
}