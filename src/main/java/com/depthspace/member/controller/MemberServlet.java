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
		} else if ("/edit".equals(pathInfo)) {
			doEdit(req, resp);
		} else if ("/addMember".equals(pathInfo)) {
			doAddMember(req, resp);
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

		try {
			String memId = req.getParameter("memId");
			System.out.println("1" + memId);
			st1 = Integer.valueOf(memId);
			System.out.println("2" + st1);

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

			String memImage = req.getParameter("base64Image");
			st13 = memImage.getBytes();
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
		System.out.println("3" + memvo.getMemName());
		req.setAttribute("authenticatedMem", memvo);
		req.setAttribute("base64Image", memvo.getMemImage());
		req.getRequestDispatcher("/member/success.jsp").forward(req, resp);
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
	}

	// ============================================

	protected void doSuccess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		System.out.println("4" + email);

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
