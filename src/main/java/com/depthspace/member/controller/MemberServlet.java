package com.depthspace.member.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.depthspace.member.model.MemVO;
import com.depthspace.member.model.jdbc.MemJDBCDAO;
import com.depthspace.member.service.MemberService;
import com.depthspace.ticketshoppingcart.model.TicketInfoVO;
import java.text.SimpleDateFormat;
import java.text.ParseException;

@WebServlet({ "/mem/*" })
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pathInfo = req.getPathInfo();
		if ("/success".equals(pathInfo)) {
			doSuccess(req, resp);
		} else if ("/edit".equals(pathInfo)){
			doEdit(req, resp);
		} else if ("/addMember".equals(pathInfo)){
			doAddMember(req,resp);
		} else if ("/modify".equals(pathInfo)) {
			doModify(req, resp);
		}
	}

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
		
		try{
		String memId = req.getParameter("memId");
		st1 = Integer.valueOf(memId);
		
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
		
		String memPoint = req.getParameter("memPoint");
		st12 = Integer.valueOf(memPoint);
		
		String memImage = req.getParameter("memImage");
		st13 = memImage.getBytes();
		 } catch (NumberFormatException e){
	            e.printStackTrace();
	            return;
	        }
		MemberService m = new MemberService();
		if (!errorMsgs.isEmpty()) {
		MemVO memvo = new MemVO(st1,st2,st3,st4,st5,st6,st7,st8,st9,st10,st11,st12,st13);
		m.updateMember(memvo);
		
		req.setAttribute("memvo", memvo);
		}
		
		req.getRequestDispatcher("/member/modify.jsp").forward(req, resp);
	}
	
	// ===================================================================================
	
	private void doAddMember(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		System.out.println("喔耶");
		req.getRequestDispatcher("/member/addMember.jsp").forward(req, resp);
	}
	
// ===================================================================================
	
	private void doEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		MemberService m = new MemberService();
		Integer memId;
		try {
			memId = Integer.valueOf(req.getParameter("memId"));
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		MemVO memVo = m.findByMemId(memId);
	    if (memVo != null) {
	        byte[] imageBytes = memVo.getMemImage();
	        
	        if (imageBytes != null) {
	            String base64Image = Base64.getEncoder().encodeToString(imageBytes);
	            req.setAttribute("base64Image", base64Image);
	        } else {
	            req.setAttribute("base64Image", null); // 如果 memImage 為 null，設定 base64Image 為 null
	        }
	        
	        req.setAttribute("mem", memVo);
	        req.getRequestDispatcher("/member/revise.jsp").forward(req, resp);
	    } else {
	       System.out.println("失敗QQ");
	    }
		
		// ============================================

//		Integer empno = Integer.valueOf(req.getParameter("empno").trim());
//		String memId = req.getParameter("memId");
//		Integer st1 = Integer.valueOf(memId);
//		if (st1 == null || st1.trim().length() == 0) {
//				errorMsgs.add("會員編號: 請勿空白");
//			}
//		String st2 = req.getParameter("memAcc");
//		System.out.println(st2);
//		if (st2 == null || st2.trim().length() == 0) {
//			errorMsgs.add("帳號請勿空白");
//		}
//		String st3 = req.getParameter("memPwd");
//		if (st3 == null || st3.trim().length() == 0) {
//			errorMsgs.add("密碼請勿空白");
//		}
//		String st4 = req.getParameter("memName");
//		if (st4 == null || st4.trim().length() == 0) {
//			errorMsgs.add("姓名請勿空白");
//		}
//		String st5 = req.getParameter("memIdentity");
//		if (st5 == null || st5.trim().length() == 0) {
//			errorMsgs.add("身分證請勿空白");
//		}
////		java.sql.Date st6 = java.sql.Date.valueOf(req.getParameter("memBth"));
//		java.sql.Date st6 = null;
//		String memBth = req.getParameter("memBth");
//		if (memBth != null && !memBth.isEmpty()) {
//		    try {
//		        st6 = java.sql.Date.valueOf(memBth);
//		    } catch (IllegalArgumentException e) {
//		    	errorMsgs.add("生日請勿空白");
//		    }
//		}
////		if (st6 == null ) {
////			errorMsgs.add("生日請勿空白");
////		}
//		String memSex = req.getParameter("memSex");
////		System.out.println(memSex);
//		byte st7 = Byte.parseByte(memSex);
////		if (st7 == null || st7.trim().length() == 0) {
////			errorMsgs.add("性別請勿空白");
////		}
//		String st8 = req.getParameter("memEmail");
//		if (st8 == null || st8.trim().length() == 0) {
//			errorMsgs.add("Email請勿空白");
//		}
//		String memTel = req.getParameter("memTel");
//		Integer st9 = Integer.valueOf(memTel);
//		if (st9 == null) {
//			errorMsgs.add("電話請勿空白");
//		}
//		String st10 = req.getParameter("memAdd");
//		if (st10 == null || st10.trim().length() == 0) {
//			errorMsgs.add("地址請勿空白");
//		}
////		Byte st11 = req.getParameter("accStatus");
////		if (st11 == null || st11.trim().length() == 0) {
////			errorMsgs.add("狀態請勿空白");
////		}
//		String memPoint = req.getParameter("memPoint");
//		Integer st12 = Integer.valueOf(memPoint);
////		if (st12 == null || st12.trim().length() == 0) {
////			errorMsgs.add("點數請勿空白");
////		}
//		String st13 = req.getParameter("memImage");
//		if (st13 != null && !st13.isEmpty()) {
//			// 將 base64 字串轉換為 byte 陣列
//			MemberService ms = new MemberService();
//			List<MemVO> list = ms.getAll();
//			for (MemVO mem : list) {
//				byte[] imageBytes = mem.getMemImage();
//				if (imageBytes != null) {
//					String base64Image = Base64.getEncoder().encodeToString(imageBytes);
//					mem.setBase64Image(base64Image);
//				}
//			}
//		}
//
//		MemVO memvo = new MemVO();
//		memvo.setMemId(st1);
//		memvo.setMemAcc(st2);
//		memvo.setMemPwd(st3);
//		memvo.setMemName(st4);
//		memvo.setMemIdentity(st5);
//		memvo.setMemBth(st6);
//		memvo.setMemSex(st7);
//		memvo.setMemEmail(st8);
//		memvo.setMemTel(st9);
//		memvo.setMemAdd(st10);
//		memvo.setAccStatus(memvo.getAccStatus());
////		memvo.setAccStatus(st11);
//		memvo.setMemPoint(st12);
//		memvo.setBase64Image(st13);
//
//		MemberService ms = new MemberService();
//		if (!errorMsgs.isEmpty()) {
//			req.setAttribute("memvo", memvo);
//			req.getRequestDispatcher("/member/revise.jsp").forward(req, resp);
//			return;
//		}

	}

//		String st1 = req.getParameter("memId");
//		String st2 = req.getParameter("memAcc");
//		String st3 = req.getParameter("memPwd");
//		String st4 = req.getParameter("memName");
//		String st5 = req.getParameter("memIdentity");
//		String st6 = req.getParameter("memBth");
//		String st7 = req.getParameter("memSex");
//		String st8 = req.getParameter("memEmail");
//		String st9 = req.getParameter("memTel");
//		String st10 = req.getParameter("memAdd");
//		String st11 = req.getParameter("accStatus");
//		String st12 = req.getParameter("memPoint");
//		String st13 = req.getParameter("memImage");

//	    Integer memId = null;
//	    String memAcc = null;
//	    String memPwd = null;
//	    String memName = null;
//	    String memIdentity = null;
//	    java.sql.Date memBth = null;
//	    Byte memSex = null;
//	    String memEmail = null;
//	    Integer memTel = null;
//	    String memAdd = null;
//	    Byte accStatus = null;
//	    Integer memPoint = null;
//	    byte[] memImage = null;
//	    try {
//	        if (st1 != null && !st1.isEmpty()) {
//	            memId = Integer.valueOf(st1.trim());
//	        }
//	        if (st2 != null) {
//	            memAcc = st2;
//	        }
//	        if (st3 != null) {
//	            memPwd = st3;
//	        }
//	        if (st4 != null) {
//	            memName = st4;
//	        }
//	        if (st5 != null) {
//	            memIdentity = st5;
//	        }
//	        if (st6 != null && !st6.isEmpty()) {
//	        	st6 = req.getParameter("memBth");
//	        	memBth = java.sql.Date.valueOf(st6);
//
//	        }
////	        if (st6 != null && !st6.isEmpty()) {
////	            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
////	            memBth = sdf.parse(st6);
////	        }
//	        if (st7 != null) {
//	            memSex = Byte.valueOf(st7);
//	        }
//	        if (st8 != null) {
//	            memEmail = st8;
//	        }
//	        if (st9 != null) {
//	            memTel = Integer.valueOf(st9);
//	        }
//	        if (st10 != null) {
//	            memAdd = st10;
//	        }
//	        if (st11 != null) {
//	            accStatus = Byte.valueOf(st11);
//	        }
//	        if (st12 != null && !st12.isEmpty()) {
//	            memPoint = Integer.valueOf(st12.trim());
//	        }
//	        if (st13 != null && !st13.isEmpty()) {
//	            // 將 base64 字串轉換為 byte 陣列
//	            memImage = Base64.getDecoder().decode(st13);
//	        }
//	    } catch (NumberFormatException e) {
//	        e.printStackTrace();
//	        // 在這裡處理轉型失敗的情況
//	        // 例如，設定一個錯誤訊息或執行其他適當的處理
//	    }

//		MemberService ms = new MemberService();
//		List<MemVO> list = ms.getAll();		
//		if (memAcc != null && memPwd != null && memName != null && memIdentity != null && memBth != null && memSex != null && memEmail != null && memTel != null && memAdd != null &&
//		        accStatus != null && memPoint != null && memImage != null) {
//		        // 執行操作
//			MemVO memVO = new MemVO(memId,memAcc,memPwd,memName,memIdentity,memBth,
//					memSex,memEmail,memTel,memAdd,accStatus,memPoint,memImage);
//			ms.updateMember(memVO);
//		}

//		req.setAttribute("list", list);
//        req.setAttribute("memId",memId);
//		for (MemVO mem : list) {
//				// 創建一個 MemVO 物件並設定它的屬性
//				MemVO authenticatedMem = new MemVO();
//				authenticatedMem.setMemId(mem.getMemId());
//				authenticatedMem.setMemAcc(mem.getMemAcc());
//				authenticatedMem.setMemPwd(mem.getMemPwd());
//				authenticatedMem.setMemName(mem.getMemName());
//				authenticatedMem.setMemIdentity(mem.getMemIdentity());
//				authenticatedMem.setMemBth(mem.getMemBth());
//				authenticatedMem.setMemSex(mem.getMemSex());
//				authenticatedMem.setMemEmail(mem.getMemEmail());
//				authenticatedMem.setMemTel(mem.getMemTel());
//				authenticatedMem.setMemAdd(mem.getMemAdd());
//				authenticatedMem.setAccStatus(mem.getAccStatus());
//				authenticatedMem.setMemPoint(mem.getMemPoint());
//				authenticatedMem.setBase64Image(mem.getBase64Image());		
//				// 設定 MemVO 物件到 request 屬性，以便在 JSP 中使用
//				req.setAttribute("authenticatedMem", authenticatedMem);
////				System.out.println(authenticatedMem);
//			
//		}

	protected void doSuccess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");

		MemberService ms = new MemberService();
		List<MemVO> list = ms.getAll();
		String base64Image;

		for (MemVO mem : list) {
			if (mem.getMemEmail().equals(email) && mem.getMemPwd().equals(password)) {
				// 創建一個 MemVO 物件並設定它的屬性
				MemVO authenticatedMem = new MemVO();
				authenticatedMem.setMemId(mem.getMemId());
				authenticatedMem.setMemAcc(mem.getMemAcc());
				authenticatedMem.setMemPwd(mem.getMemPwd());
				authenticatedMem.setMemName(mem.getMemName());
				authenticatedMem.setMemIdentity(mem.getMemIdentity());
				authenticatedMem.setMemBth(mem.getMemBth());
				authenticatedMem.setMemSex(mem.getMemSex());
				authenticatedMem.setMemEmail(mem.getMemEmail());
				authenticatedMem.setMemTel(mem.getMemTel());
				authenticatedMem.setMemAdd(mem.getMemAdd());
				authenticatedMem.setAccStatus(mem.getAccStatus());
				authenticatedMem.setMemPoint(mem.getMemPoint());
				byte[] imageBytes = mem.getMemImage();
				base64Image = Base64.getEncoder().encodeToString(imageBytes);
				req.setAttribute("authenticatedMem", authenticatedMem);
				req.setAttribute("base64Image", base64Image);
			}
		}
		req.getRequestDispatcher("/member/success.jsp").forward(req, resp);
	}
}
//		if (isAuthenticated) {
//			// 帳號和密碼匹配，執行登入成功後的動作
//			// 這裡可以設置登入成功後的跳轉頁面或其他處理
//			resp.sendRedirect("loginSuccess.jsp");
//		} else {
//			// 帳號和密碼不匹配，執行登入失敗後的動作
//			// 這裡可以設置登入失敗後的跳轉頁面或其他處理
//			resp.sendRedirect("loginFailure.jsp");
//		}

//			req.setAttribute("email",email);
